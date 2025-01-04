package org.example.codegen;

import org.antlr.v4.runtime.tree.ParseTree;
import org.example.memory.Memory;
import org.example.parser.GrammarParser;

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
        if (node==null) return;
        
        if (node instanceof GrammarParser.READContext){
            
        } else if (node instanceof GrammarParser.WRITEContext) {

        }

        for (int i = 0; i < node.getChildCount(); i++) {
            traverse(node.getChild(i));
        }
    }

    public String getCode(){
        return code;
    }

}
