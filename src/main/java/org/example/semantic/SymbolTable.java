package org.example.semantic;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SymbolTable {

    private Map<String, Symbol> symbolTable = new HashMap<>();
    public SymbolTable() {
        this.symbolTable = new HashMap<>();
    }

    public void addSymbol(String name, Symbol symbol){
        symbolTable.put(name, symbol);
    }

    public Symbol getSymbol(String name){
        return symbolTable.get(name);
    }

    public Map<String, Symbol> getAllSymbols(){
        return symbolTable;
    }

    public boolean containsSymbol(String name){
        return symbolTable.containsKey(name);
    }

    public HashMap<String, Symbol> getALlSymbols(){
        return (HashMap<String, Symbol>) symbolTable;
    }

    public void printSymbols(){
        System.out.println(symbolTable);
    }

}
