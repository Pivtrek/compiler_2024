package org.example.memory;

import java.util.Map;

public class Memory {
    private Map<Integer, MemCell> memory;
    private Map<Integer, MemCell> register;
    int nextFreeAdress;

    public Memory() {
    }
}
