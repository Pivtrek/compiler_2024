package org.example.memory;

import java.util.Map;

public class MemCell {
    private String name, scope;
    private int registerNumber, value;
    private Map<Integer, Integer> arrayIndex;

    public enum inputType{
        ARRAY,
        INTEGER,
        REGISTER
    }

    public MemCell(String name, String scope, int registerNumber) {
        this.name = name;
        this.scope = scope;
        this.registerNumber = registerNumber;
    }
}