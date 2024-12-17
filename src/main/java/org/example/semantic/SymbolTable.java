package org.example.semantic;
import java.util.HashSet;

public class SymbolTable {

    private HashSet<Symbol> symbolTable;
    public SymbolTable() {
        this.symbolTable = new HashSet<Symbol>();
    }

    public void addSymbol(Symbol symbol){
        symbolTable.add(symbol);
    }

    public boolean containsSymbol(Symbol symbol){
        return symbolTable.contains(symbol);
    }

    public HashSet<Symbol> getALlSymbols(){
        return symbolTable;
    }

    public void printSymbols(){
        System.out.println(symbolTable);
    }

}
