package org.example.semantic;

import java.util.ArrayList;
import java.util.List;

public class ErrorColector {
    public static class SemanticErrorException extends RuntimeException{
        public SemanticErrorException(String message){
            super(message);
        }
    }

    public void reportError(String message, int line){
        String errorMessage = "Błąd semantyczny w linii " + line + " :" + message;
        System.err.println(errorMessage);
        throw new SemanticErrorException(errorMessage);
    }
}
