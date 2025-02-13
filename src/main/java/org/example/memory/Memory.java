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
    private int nextFreeAdress = 13;
    private Integer callProcNumber;
    private Integer maxLowerBound;
    private Integer maxUpperBound;

    public Memory(SymbolTable symbolTable, Integer callProcNumber) {
        this.memory = new HashMap<>();
        this.register = new HashMap<>(nextFreeAdress);
        this.callProcNumber = callProcNumber;
        findWidestBounds(symbolTable);
        initializeRegister();
        initializeFromSymbolTable(symbolTable);
        initializeStack();
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
                        for (int i=localVariable.getLowerBound(); i<=localVariable.getUpperBound();i++){
                            String name = localVariable.getName() + "[" + i + "]";
                            addMemCell(name, entry.getKey(), MemCell.inputType.ARRAY, null);
                        }

                        //We will store one extra parameter for array, it will be memory address of t[0], even if it doesn't exist,
                        //because with any iterator given in array we can easily get register cell for given index of array

                        String nameIndex0 = localVariable.getName() + "[0]:" + entry.getKey();
                        String baseAddressName = localVariable.getName() + ":baseAddress";
                        if (memory.containsKey(nameIndex0)){
                            MemCell indexMemCell = memory.get(nameIndex0);
                            addMemCell(baseAddressName, entry.getKey(), MemCell.inputType.ARRAY, indexMemCell.getRegisterNumber());
                        }else {
                            //example t[1:5], or t[-10:-1]. Works equally
                            String firstArrayName = localVariable.getName() + "[" + localVariable.getLowerBound() + "]";
                            MemCell firstArrayRegister = getMemCell(firstArrayName, entry.getKey());
                            addMemCell(baseAddressName, entry.getKey(), MemCell.inputType.ARRAY, firstArrayRegister.getRegisterNumber()-localVariable.getLowerBound());
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
                        addMemCell(nameLowerBound, entry.getKey(), MemCell.inputType.ARRAY, maxLowerBound);
                        for (int i=maxLowerBound; i<=maxUpperBound;i++){
                            String name = parameter.getName() + "[" + i + "]";
                            addMemCell(name, entry.getKey(), MemCell.inputType.ARRAY, null);
                        }
                        String nameIndex0 = parameter.getName() + "[0]:" + entry.getKey();
                        String baseAddressName = parameter.getName() + ":baseAddress";
                        if (memory.containsKey(nameIndex0)){
                            MemCell indexMemCell = memory.get(nameIndex0);
                            addMemCell(baseAddressName, entry.getKey(), MemCell.inputType.ARRAY, indexMemCell.getRegisterNumber());
                        }else {
                            //example t[1:5], or t[-10:-1]. Works equally
                            String firstArrayName = parameter.getName() + "[" + maxLowerBound + "]";
                            MemCell firstArrayRegister = getMemCell(firstArrayName, entry.getKey());
                            addMemCell(baseAddressName, entry.getKey(), MemCell.inputType.ARRAY, firstArrayRegister.getRegisterNumber()-maxLowerBound);
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

    private void initializeStack(){
        for (int i=0; i<=callProcNumber; i++){
            String name = "stack:" + String.valueOf(i);
            addMemCell(name, "GLOBAL", MemCell.inputType.INTEGER, null);
        }
    }

    public void addMemCell(String name, String scope, MemCell.inputType inputType, Integer value){
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
    private void findWidestBounds(SymbolTable symbolTable) {
        for (Map.Entry<String, Symbol> entry : symbolTable.getALlSymbols().entrySet()) {
            String procedureName = entry.getKey();
            Symbol procedure = entry.getValue();
            if (procedure.getLocalVariables() != null) {
                for (Symbol localVariable : procedure.getLocalVariables()) {
                    if (maxUpperBound == null || (localVariable.getUpperBound() != null && localVariable.getUpperBound() > maxUpperBound)) {
                        maxUpperBound = localVariable.getUpperBound();
                    }
                    if (maxLowerBound == null || (localVariable.getLowerBound() != null && localVariable.getLowerBound() < maxLowerBound)) {
                        maxLowerBound = localVariable.getLowerBound();
                    }
                }
            }
        }
    }
}
