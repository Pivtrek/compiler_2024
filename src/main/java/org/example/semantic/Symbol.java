package org.example.semantic;


import java.util.List;
import java.util.Objects;

public class Symbol {
    public enum SymbolType{
        INT,
        ARRAY,
        PROCEDURE,
        ITERATOR
    }
    private String name;
    private SymbolType type;

    //Fields for array
    private Integer lowerBound;
    private Integer upperBound;

    //Fields for procedures
    private List<Symbol> parameters;
    private List<Symbol> localVariables;

    public Symbol(String name, SymbolType type){
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public SymbolType getType() {
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

    @Override
    public String toString() {
        return "Symbol{name='" + name + "', type=" + type + "}";
    }
}
