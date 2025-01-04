package org.example.codegen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InstructionList {
    private List<Instruction> instructions;

    public InstructionList() {
        this.instructions = new ArrayList<>();
    }

    public void addInstruction(Instruction instruction){
        instructions.add(instruction);
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Instruction instruction : instructions) {
            sb.append(instruction.toString()).append("\n");
        }
        return sb.toString();
    }

    public void writeToFile(String filePath) throws IOException {
        Files.writeString(Paths.get(filePath), toString());
    }
}
