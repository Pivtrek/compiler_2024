package org.example.semantic;


import java.util.Objects;

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

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                   // Ta sama referencja
        if (obj == null || getClass() != obj.getClass()) return false; // Sprawdź typ
        Symbol symbol = (Symbol) obj;
        return Objects.equals(name, symbol.name);       // Porównaj nazwe symbolu
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);                      // Hash tylko na podstawie nazwy
    }
}
