package org.example.memory;
public class MemCell {
    private String name, scope;
    private Integer value;
    private int registerNumber;
    private inputType type;

    public enum inputType{
        ARRAY,
        INTEGER,
        REGISTER
    }

    public MemCell(String name, String scope, inputType type, int registerNumber, Integer value) {
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

    public Integer getValue() {
        return value;
    }

    public inputType getType() {
        return type;
    }

    public void setValue(int value) {
        this.value = value;
    }
}