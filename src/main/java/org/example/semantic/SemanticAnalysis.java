package org.example.semantic;

public class SemanticAnalysis {
    private final SymbolTable symbolTable;
    private final ErrorColector errorColector;

    public SemanticAnalysis(SymbolTable symbolTable, ErrorColector errorColector) {
        this.symbolTable = symbolTable;
        this.errorColector = errorColector;
    }

    public void analyze(){

    }

    //Checking if local variables in procedure are the same as arguments
    private void checkForWrongLocalVariables(){
        for (Symbol symbol: symbolTable.g)
            //TODO: CHANGE SYMBOLTABLE FOR MAP AND CREATE FUNCTION THAT RETURN A LIST OF ALL SYMBOLS FOR VALIDATION HERE
    }

}
