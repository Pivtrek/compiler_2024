package org.example.codegen;

import org.antlr.v4.runtime.tree.ParseTree;
import org.example.memory.Memory;

public class CodeGenerator {
    private Memory memory;
    private ParseTree tree;
    private InstructionList instructionList;
    private String code;

    public CodeGenerator(Memory memory, ParseTree tree) {
        this.memory = memory;
        this.tree = tree;
        this.instructionList = new InstructionList();
    }

    public void genereteCode(){
        traverse(tree);
    }

    private void traverse(ParseTree node){
        //Traversing
    }

    public String getCode(){
        return code;
    }

}
