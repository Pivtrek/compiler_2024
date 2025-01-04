package org.example.codegen;

import org.antlr.v4.runtime.ParserRuleContext;
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

    private void generateRead(GrammarParser.READContext readContext){

        String scope = findEnclosingScope(readContext);
        String name = readContext.identifier().getText();
        int registerNumber = memory.resolveMemory(name, scope);

        instructionList.addInstruction(new Instruction("GET", registerNumber));
    }

    public String getCode(){
        return code;
    }

    public static String findEnclosingScope(ParserRuleContext context){
        ParserRuleContext current = context;
        while (current != null){
            if (current instanceof GrammarParser.PROCEDUREWITHDECLARATIONSContext){
                return ((GrammarParser.PROCEDUREWITHDECLARATIONSContext) current).proc_head().PIDENTIFIER().getText();
            }
            if (current instanceof GrammarParser.PROCEDUREWITHOUTDECLARATIONSContext){
                return ((GrammarParser.PROCEDUREWITHOUTDECLARATIONSContext) current).proc_head().PIDENTIFIER().getText();
            }
            if (current instanceof GrammarParser.MAINDECLARATIONSContext){
                return "PROGRAM_IS_DECLARATIONS";
            }
            if (current instanceof GrammarParser.MAINWITHOUTDECLARATIONSContext){
                return "PROGRAM_IS";
            }
            current = current.getParent();
        }
        return "UKNOWN SCOPE";
    }

}
