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

    public void printSymbols(){
        System.out.println(symbolTable);
    }

}
