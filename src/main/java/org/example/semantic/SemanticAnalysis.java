package org.example.semantic;

public class SemanticAnalysis {
    private final SymbolTable symbolTable;
    private final ErrorColector errorColector;

    public SemanticAnalysis(SymbolTable symbolTable, ErrorColector errorColector) {
        this.symbolTable = symbolTable;
        this.errorColector = errorColector;
    }

    public void analyze(){
        checkForWrongLocalVariables();
    }

    //Checking if local variables in procedure are the same as arguments
    private void checkForWrongLocalVariables(){
        for (Symbol symbol: symbolTable.getALlSymbols()) {
            System.out.println(symbol);
            if (symbol.getType() == Symbol.SymbolType.PROCEDURE_WITH_LOCAL_VARIABLES){
                for (Symbol parameter : symbol.getParameters()){
                    for (Symbol local_variable : symbol.getLocalVariables()){
                        if (parameter.getName().equals(local_variable.getName())){
                            errorColector.reportError("dupa", 1); //TODO: BRAK LINI WIĘC TRZEBA TO ZAIMPLEMENTOWAĆ PRZY TWORZENIU PROCEDURY
                        }
                    }
                }
            }
        }
    }

}
