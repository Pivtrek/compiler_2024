package org.example.memory;

import java.util.Map;

public class MemCell {
    private String name, scope;
    private int registerNumber, value;
    private boolean isArray, isRegister;
    private Map<Integer, Integer> arrayIndexes;

    public MemCell(String name, String scope, int registerNumber, boolean isArray, boolean isRegister) {
        this.name = name;
        this.scope = scope;
        this.registerNumber = registerNumber;
        this.isArray = isArray;
        this.isRegister = isRegister;
    }
}