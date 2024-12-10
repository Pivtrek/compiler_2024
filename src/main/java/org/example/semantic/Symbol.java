package org.example.semantic;

public class Symbol {
    public enum SymbolType{
        INT,
        ARRAY,
        PROCEDURE,
        ITERATOR
    }
    private String name;
    private String type;

    public Symbol(String name, String type){
        this.name = name;
        this.type = type;
    }
}
