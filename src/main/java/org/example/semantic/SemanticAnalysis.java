package org.example.semantic;

public class SemanticAnalysis {
    private final SymbolTable symbolTable;
    private final ErrorColector errorColector;

    public SemanticAnalysis(SymbolTable symbolTable, ErrorColector errorColector) {
        this.symbolTable = symbolTable;
        this.errorColector = errorColector;
    }
}
