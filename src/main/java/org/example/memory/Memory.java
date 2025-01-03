package org.example.memory;
import org.example.semantic.Symbol;
import org.example.semantic.SymbolTable;

import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

public class Memory {
    private Map<String, MemCell> memory;
    private Map<String, MemCell> register;
    int nextFreeAdress = 8;

    public Memory(SymbolTable symbolTable) {
        this.memory = new HashMap<>();
        this.register = new HashMap<>(7);
        initializeFromSymbolTable(symbolTable);
    }


    //Key format for name in memory 'name:scope'
    private void initializeFromSymbolTable(SymbolTable symbolTable){
        for (Map.Entry<String, Symbol> entry: symbolTable.getALlSymbols().entrySet()){
            Symbol procedure = entry.getValue();

            if (procedure.getLocalVariables() != null){
                for (Symbol localVariable : procedure.getLocalVariables()){

                }
            }
            if (procedure.getParameters() != null){
                for (Symbol parameter : procedure.getParameters()){

                }
            }
        }
    }

    private void addMemCell(String name, String scope, MemCell.inputType inputType, ){
        String memName = name + ":" + scope;
        memory.put(memName, new MemCell(name, scope, inputType, nextFreeAdress, null));
    }
}
