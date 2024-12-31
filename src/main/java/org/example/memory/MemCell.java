package org.example.memory;
public class MemCell {
    private String name, scope;
    private int registerNumber, value;
    private inputType type;

    public enum inputType{
        ARRAY,
        INTEGER,
        REGISTER
    }

    public MemCell(String name, String scope, inputType type, int registerNumber, int value) {
        this.name = name;
        this.scope = scope;
        this.type = type;
        this.registerNumber = registerNumber;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getScope() {
        return scope;
    }

    public int getRegisterNumber() {
        return registerNumber;
    }

    public int getValue() {
        return value;
    }

    public inputType getType() {
        return type;
    }
}