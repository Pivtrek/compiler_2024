package org.example.codegen;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.memory.Memory;
import org.example.parser.GrammarParser;

import java.util.List;

public class CodeGenerator {
    private Memory memory;
    private ParseTree tree;
    private InstructionList instructionList;

    public CodeGenerator(Memory memory, ParseTree tree) {
        this.memory = memory;
        this.tree = tree;
        this.instructionList = new InstructionList();
    }

    public void genereteCode(){
        traverse(tree);
        instructionList.addInstruction(new Instruction("HALT"));
    }

    public InstructionList getInstructionList() {
        return instructionList;
    }

    private void traverse(ParseTree node){
        if (node==null) return;
        
        if (node instanceof GrammarParser.READContext){
            generateRead((GrammarParser.READContext) node);
        } else if (node instanceof GrammarParser.WRITEContext) {
            generateWrite((GrammarParser.WRITEContext) node);
        } else if (node instanceof GrammarParser.ASSIGNContext){
            generateAssign((GrammarParser.ASSIGNContext) node);
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

    private void generateWrite(GrammarParser.WRITEContext writeContext){

        String scope = findEnclosingScope(writeContext);
        String name = writeContext.value().identifier().getText();
        int registerNumber = memory.resolveMemory(name, scope);

        instructionList.addInstruction(new Instruction("PUT", registerNumber));
    }

    private void generateAssign(GrammarParser.ASSIGNContext assignContext){
        String targetName = assignContext.identifier().getText();
        String scope = findEnclosingScope(assignContext);
        int registerNumber = memory.resolveMemory(targetName, scope);

        GrammarParser.ExpressionContext expressionContext = assignContext.expression();
        handleExpressionContext(expressionContext);

        //after completing handling right hand side of assign, value of it its in p0 and goes to variable
        instructionList.addInstruction(new Instruction("STORE", registerNumber));
    }

    //Handling rhs of assign, storing result to acc
    private void handleExpressionContext(GrammarParser.ExpressionContext expressionContext){
        if (expressionContext instanceof GrammarParser.VALEXPRContext valexprContext){
            //number or variable
            GrammarParser.ValueContext value = valexprContext.value();
            //its number
            if (value.NUM() != null){
                int num = Integer.parseInt(value.NUM().getText());
                instructionList.addInstruction(new Instruction("SET", num));
            } else if (value.identifier() != null) { //variable
                String varName = value.identifier().getText();
                String scope = findEnclosingScope(valexprContext);
                int registerNumber = memory.resolveMemory(varName, scope);
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            }


        } else if (expressionContext instanceof GrammarParser.ADDContext) {

        }
        else if (expressionContext instanceof GrammarParser.SUBContext) {

        }
        else if (expressionContext instanceof GrammarParser.MULContext) {

        }
        else if (expressionContext instanceof GrammarParser.DIVContext) {

        }
        else if (expressionContext instanceof GrammarParser.MODContext) {

        }

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
