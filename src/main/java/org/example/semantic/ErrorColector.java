package org.example.semantic;

import java.util.ArrayList;
import java.util.List;

public class ErrorColector {
    public static class SemanticError{
        private final String message;
        private final int line;

        public SemanticError(String message, int line) {
            this.message = message;
            this.line = line;
        }

        @Override
        public String toString() {
            return "BŁĄD w linii: " + line + " : " + message;
        }
    }

    private final List<SemanticError> errors = new ArrayList<>();

    public void addError(String message, int line){
        errors.add(new SemanticError(message, line));
    }

    public boolean hasError(){
        return !errors.isEmpty();
    }

    public List<SemanticError> getErrors(){
        return errors;
    }
}
