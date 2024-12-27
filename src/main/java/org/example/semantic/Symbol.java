package org.example.semantic;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Symbol {
    public enum SymbolType{
        INT,
        ARRAY,
        PROCEDURE_WITH_LOCAL_VARIABLES,

        PROCEDURE_WITHOUT_LOCAL_VARIABLES,

        MAIN_WITH_LOCAL_VARIABLES,
        MAIN_WITHOUT_LOCAL_VARIABLES,
        ITERATOR
    }
    private String name;
    private SymbolType type;
    private boolean isInitialized;

    //Fields for array
    private Integer lowerBound;
    private Integer upperBound;

    //Fields for procedures
    private List<Symbol> parameters;
    private List<Symbol> localVariables;

    public Symbol(String name, SymbolType type){
        this.name = name;
        this.type = type;

        if (type == SymbolType.PROCEDURE_WITH_LOCAL_VARIABLES){
            this.parameters = new ArrayList<>();
            this.localVariables = new ArrayList<>();
        } else if (type == SymbolType.PROCEDURE_WITHOUT_LOCAL_VARIABLES) {
            this.parameters = new ArrayList<>();
        } else if (type == SymbolType.MAIN_WITH_LOCAL_VARIABLES) {
            this.localVariables = new ArrayList<>();
        } else if (type == SymbolType.INT) {
            this.isInitialized = false;
        }
    }

    public String getName() {
        return name;
    }

    public SymbolType getType() {
        return type;
    }

    public Integer getLowerBound() {
        return lowerBound;
    }

    public Integer getUpperBound() {
        return upperBound;
    }

    public List<Symbol> getParameters() {
        return parameters;
    }

    public List<Symbol> getLocalVariables() {
        return localVariables;
    }

    public void setArrayBounds(int lowerBound, int upperBound){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    //Procedures methods

    public void addParameter(Symbol parameter) {
        if (type == SymbolType.PROCEDURE_WITH_LOCAL_VARIABLES || type == SymbolType.PROCEDURE_WITHOUT_LOCAL_VARIABLES) {
            parameters.add(parameter);
        } else {
            throw new UnsupportedOperationException("Tylko PROCEDURE może mieć parametry!");
        }
    }

    public void addLocalVariable(Symbol localVariable) {
        if (type == SymbolType.PROCEDURE_WITH_LOCAL_VARIABLES || type == SymbolType.MAIN_WITH_LOCAL_VARIABLES) {
            localVariables.add(localVariable);
        } else {
            throw new UnsupportedOperationException("Tylko PROCEDURE albo MAIN może mieć zmienne lokalne!");
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                   // Ta sama referencja
        if (obj == null || getClass() != obj.getClass()) return false; // Sprawdź typ
        Symbol symbol = (Symbol) obj;
        return Objects.equals(name, symbol.name) &&
        type == symbol.getType();// Porównaj nazwe symbolu
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);                      // Hash tylko na podstawie nazwy
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Symbol{name='" + name + "', type=" + type);
        if (type == SymbolType.ARRAY) {
            sb.append(", lowerBound=").append(lowerBound)
                    .append(", upperBound=").append(upperBound);
        } else if (type == SymbolType.PROCEDURE_WITH_LOCAL_VARIABLES || type == SymbolType.PROCEDURE_WITHOUT_LOCAL_VARIABLES) {
            sb.append(", parameters=").append(parameters)
                    .append(", localVariables=").append(localVariables);
        } else if (type == SymbolType.MAIN_WITH_LOCAL_VARIABLES) {
            sb.append(", localVariables=").append(localVariables);
        }
        sb.append('}');
        return sb.toString();
    }
}
