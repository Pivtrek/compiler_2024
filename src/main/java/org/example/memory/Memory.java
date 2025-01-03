package org.example.memory;
import org.example.semantic.SymbolTable;
import java.util.Map;

public class Memory {
    private Map<Integer, MemCell> memory;
    private Map<Integer, MemCell> register;
    int nextFreeAdress = 8;

    public Memory() {
    }


    //Key format for name in memory 'name:scope'
    private void initializeFromSymbolTable(SymbolTable symbolTable){

    }

    private void addMemCell(){

    }
}
