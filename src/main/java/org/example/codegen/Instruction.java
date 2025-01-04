package org.example.codegen;

public class Instruction {
    private String operation;
    private Integer operand;

    public Instruction(String operation, Integer operand) {
        this.operation = operation;
        this.operand = operand;
    }

    public Instruction(String operation){
        this(operation, null);
    }

    public String getOperation() {
        return operation;
    }

    public Integer getOperand() {
        return operand;
    }

    // Overriding toString for clean formatting
    @Override
    public String toString() {
        return operand == null ? operation : operation + " " + operand;
    }
}
