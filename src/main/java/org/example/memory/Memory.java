package org.example.memory;
import org.antlr.v4.runtime.ParserRuleContext;
import org.example.semantic.Symbol;
import org.example.semantic.SymbolTable;

import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

public class Memory {
    private Map<String, MemCell> memory;
    private Map<String, MemCell> referenceMap;
    private Map<String, MemCell> register;
    int nextFreeAdress = 8;

    public Memory(SymbolTable symbolTable) {
        this.memory = new HashMap<>();
        this.register = new HashMap<>(8);
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
                        for (int i=localVariable.getLowerBound(); i<=localVariable.getUpperBound();i++){
                            String name = localVariable.getName() + "[" + i + "]";
                            addMemCell(name, entry.getKey(), MemCell.inputType.ARRAY, null);
                        }
                    }
                }
            }
            if (procedure.getParameters() != null){
                for (Symbol parameter : procedure.getParameters()){
                    //TODO: Mapping parameters to other MemCells
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
    public int resolveMemory(String name, String scope, ParserRuleContext context){
        if (!name.contains("[")){
            String key = name + ":" + scope;
            MemCell memCell = memory.get(key);

            if (memCell == null){
                throw new RuntimeException("Variable " + name + " not found in scope: " + scope);
            }

            return memCell.getRegisterNumber();
        }
        //handling array acces with variable or num a[b] or a[2]
        String arrayName = name.substring(0, name.indexOf("["));
    }
}
