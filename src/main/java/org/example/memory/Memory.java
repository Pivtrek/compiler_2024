package org.example.memory;
import org.antlr.v4.runtime.ParserRuleContext;
import org.example.parser.GrammarParser;
import org.example.semantic.Symbol;
import org.example.semantic.SymbolTable;

import java.util.*;

public class Memory {
    private Map<String, MemCell> memory;
    private Map<String, MemCell> referenceMap;
    private Map<String, MemCell> register;
    private int nextFreeAdress = 11;
    private Integer callProcNumber;

    public Memory(SymbolTable symbolTable, Integer callProcNumber) {
        this.memory = new HashMap<>();
        this.register = new HashMap<>(nextFreeAdress);
        this.callProcNumber = callProcNumber;
        initializeRegister();
        initializeFromSymbolTable(symbolTable);
    }


    //Key format for name in memory 'name:scope'
    private void initializeFromSymbolTable(SymbolTable symbolTable){
        for (Map.Entry<String, Symbol> entry: symbolTable.getALlSymbols().entrySet()){
            Symbol procedure = entry.getValue();

            if (procedure.getLocalVariables() != null){
                for (Symbol localVariable : procedure.getLocalVariables()){
                    if (localVariable.getType().equals(Symbol.SymbolType.INT) || localVariable.getType().equals(Symbol.SymbolType.ITERATOR)){
                        addMemCell(localVariable.getName(), entry.getKey(), MemCell.inputType.INTEGER, null);
                    } else if (localVariable.getType().equals(Symbol.SymbolType.ARRAY)) {
                        String nameLowerBound = localVariable.getName()+":lowerbound";
                        addMemCell(nameLowerBound, entry.getKey(), MemCell.inputType.ARRAY, localVariable.getLowerBound());
                        for (int i=localVariable.getLowerBound(); i<=localVariable.getUpperBound();i++){
                            String name = localVariable.getName() + "[" + i + "]";
                            addMemCell(name, entry.getKey(), MemCell.inputType.ARRAY, null);
                        }
                    }
                }
            }
            if (procedure.getParameters() != null){
                for (Symbol parameter : procedure.getParameters()){
                    if (parameter.getType().equals(Symbol.SymbolType.INT) || parameter.getType().equals(Symbol.SymbolType.ITERATOR)){
                        addMemCell(parameter.getName(), entry.getKey(), MemCell.inputType.INTEGER, null);
                    } else if (parameter.getType().equals(Symbol.SymbolType.ARRAY)) {
                        String nameLowerBound = parameter.getName()+":lowerbound";
                        addMemCell(nameLowerBound, entry.getKey(), MemCell.inputType.ARRAY, parameter.getLowerBound());
                        for (int i=parameter.getLowerBound(); i<=parameter.getUpperBound();i++){
                            String name = parameter.getName() + "[" + i + "]";
                            addMemCell(name, entry.getKey(), MemCell.inputType.ARRAY, null);
                        }
                    }
                }
            }
        }
    }

    private void initializeRegister(){
        for (int i=0; i<nextFreeAdress; i++){
            String regName = "p" + i;
            register.put(regName, new MemCell(regName, "GLOBAL", MemCell.inputType.REGISTER, i, null));
        }
    }

    private void addMemCell(String name, String scope, MemCell.inputType inputType, Integer value){
        String memName = name + ":" + scope;
        memory.put(memName, new MemCell(name, scope, inputType, nextFreeAdress, value));
        nextFreeAdress+=1;
    }

    public MemCell getMemCell(String name, String scope){
        String memName = name + ":" + scope;
        return memory.get(memName);
    }

    public MemCell getMemCell(GrammarParser.IdentifierContext identifierContext, String scope){
        if (identifierContext instanceof GrammarParser.INTUSAGEContext || identifierContext instanceof GrammarParser.ARRAYWITHNUMUSAGEContext){
            String key = identifierContext.getText() + ":" + scope;
            return memory.get(key);
        } else if (identifierContext instanceof GrammarParser.ARRAYWITHPIDUSAGEContext arraywithpidusageContext) {
            String indexKey = arraywithpidusageContext.PIDENTIFIER(1).getText() + ":" + scope;
            String indexValue = String.valueOf(memory.get(indexKey).getValue());
            String arrayKey = arraywithpidusageContext.PIDENTIFIER(0).getText() + "[" + indexValue + "]" + ":" + scope;
            return memory.get(arrayKey);
        }
        return null;
    }

    public int resolveMemory(String name, String scope, GrammarParser.IdentifierContext identifierContext){
        if (identifierContext instanceof GrammarParser.INTUSAGEContext || identifierContext instanceof GrammarParser.ARRAYWITHNUMUSAGEContext){
            String key = name + ":" + scope;
            MemCell memCell = memory.get(key);

            if (memCell == null){
                throw new RuntimeException("Variable " + name + " not found in scope: " + scope);
            }
            return memCell.getRegisterNumber();
        } else if (identifierContext instanceof GrammarParser.ARRAYWITHPIDUSAGEContext arraywithpidusageContext) {
            String indexKey = arraywithpidusageContext.PIDENTIFIER(1).getText() + ":" + scope;
            String indexValue = String.valueOf(memory.get(indexKey).getValue());
            String arrayKey = arraywithpidusageContext.PIDENTIFIER(0).getText() + "[" + indexValue + "]" + ":" + scope;
            MemCell memCell = memory.get(arrayKey);
            if (memCell == null){
                throw new RuntimeException("Variable " + name + " not found in scope: " + scope);
            }
            return memCell.getRegisterNumber();
        }
        throw new RuntimeException("CANNOT RESOLVE MEMORY ACCESS");
    }
    public int resolveMemory(String name, String scope) {
        String key = name + ":" + scope;
        MemCell memCell = memory.get(key);

        if (memCell == null) {
            throw new RuntimeException("Variable " + name + " not found in scope: " + scope);
        }
        return memCell.getRegisterNumber();
    }
}
