package org.example.codegen;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.memory.MemCell;
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
        int registerNumber = memory.resolveMemory(name, scope, readContext.identifier());

        instructionList.addInstruction(new Instruction("GET", registerNumber));
    }

    private void generateWrite(GrammarParser.WRITEContext writeContext){

        // WRITE 123
        if (writeContext.value().NUM() != null){
            int number = Integer.parseInt(writeContext.value().NUM().getText());
            //Setting given number in acc and showing it on console
            instructionList.addInstruction(new Instruction("SET", number));
            instructionList.addInstruction(new Instruction("PUT", 0));
        } else if (writeContext.value().identifier() != null) {
            String scope = findEnclosingScope(writeContext);
            String name = writeContext.value().getText();
            int registerNumber = memory.resolveMemory(name, scope, writeContext.value().identifier());
            instructionList.addInstruction(new Instruction("PUT", registerNumber));
        }
    }

    private void generateAssign(GrammarParser.ASSIGNContext assignContext){

        String targetName = assignContext.identifier().getText();
        String scope = findEnclosingScope(assignContext);
        int registerNumber = memory.resolveMemory(targetName, scope, assignContext.identifier());
        handleExpressionContext(assignContext, targetName, scope);

        //after completing handling right hand side of assign, value of it its in p0 and goes to variable
        instructionList.addInstruction(new Instruction("STORE", registerNumber));
    }

    //Handling rhs of assign, storing result to acc
    private void handleExpressionContext(GrammarParser.ASSIGNContext assignContext, String targetName, String scope){
        if (assignContext.expression() instanceof GrammarParser.VALEXPRContext valexprContext){
            //number or variable
            GrammarParser.ValueContext value = valexprContext.value();
            //its number
            if (value.NUM() != null){
                int num = Integer.parseInt(value.NUM().getText());
                memory.getMemCell(assignContext.identifier(), scope).setValue(num);
                instructionList.addInstruction(new Instruction("SET", num));
            } else if (value.identifier() != null) { //variable
                String varName = value.identifier().getText();
                String scopeOfVariable = findEnclosingScope(valexprContext);
                int registerNumber = memory.resolveMemory(varName, scopeOfVariable, value.identifier());
                if (memory.getMemCell(value.identifier(), scopeOfVariable).getValue()!= null){
                    memory.getMemCell(assignContext.identifier(), scope).setValue(memory.getMemCell(value.identifier(), scopeOfVariable).getValue());
                }
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            }
        } else if (assignContext.expression() instanceof GrammarParser.ADDContext addContext ) {
            boolean first = false, second = false;
            int firstV=0,secondV = 0;

            if (addContext.value(0).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(addContext.value(0).NUM().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
                first = true;
                firstV = Integer.parseInt(addContext.value(0).NUM().getText());
            }
            else {
                String scopeOfVariable = findEnclosingScope(addContext.value(0));
                int registerNumber = memory.resolveMemory(addContext.value(0).identifier().getText(), scopeOfVariable, addContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));
                if (memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue() != null){
                    first = true;
                    firstV = memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue();
                }
            }
            if (addContext.value(1).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(addContext.value(1).NUM().getText())));
                second = true;
                secondV = Integer.parseInt(addContext.value(1).NUM().getText());
            }
            else {
                String scopeOfVariable = findEnclosingScope(addContext.value(1));
                int registerNumber = memory.resolveMemory(addContext.value(1).identifier().getText(), scopeOfVariable, addContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                if (memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue() != null){
                    second = true;
                    secondV = memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue();
                }
            }
            instructionList.addInstruction(new Instruction("ADD", 1));
            if (first && second){
                memory.getMemCell(assignContext.identifier(), findEnclosingScope(assignContext)).setValue(firstV+secondV);
            }
        } else if (assignContext.expression() instanceof GrammarParser.SUBContext subContext) {
            boolean first = false, second = false;
            int firstV=0,secondV = 0;


            if (subContext.value(1).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(subContext.value(1).NUM().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
                second = true;
                secondV = Integer.parseInt(subContext.value(1).NUM().getText());
            }
            else {
                String scopeOfVariable = findEnclosingScope(subContext.value(1));
                int registerNumber = memory.resolveMemory(subContext.value(1).identifier().getText(), scopeOfVariable, subContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));
                if (memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue() != null){
                    second = true;
                    secondV = memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue();
                }
            }
            if (subContext.value(0).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(subContext.value(0).NUM().getText())));
                first = true;
                firstV = Integer.parseInt(subContext.value(0).NUM().getText());
            }
            else {
                String scopeOfVariable = findEnclosingScope(subContext.value(0));
                int registerNumber = memory.resolveMemory(subContext.value(0).identifier().getText(), scopeOfVariable, subContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                if (memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue() != null){
                    first = true;
                    firstV = memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue();
                }
            }
            instructionList.addInstruction(new Instruction("SUB", 1));
            if (first && second){
                memory.getMemCell(assignContext.identifier(), findEnclosingScope(assignContext)).setValue(firstV-secondV);
            }

        } else if (assignContext.expression() instanceof GrammarParser.MULContext mulContext) {
            /*
            r0 - acc
            r1 - multiplier
            r2 - multiplicand
            r3 - result
            r4-r7 - for temporary calculations
            at the end result goes to acc - r0
             */

            boolean first = false, second = false;
            int firstV=0,secondV = 0;

            //First part, storing multiplier and multiplicand to r1 and r2
            if (mulContext.value(0).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(mulContext.value(0).NUM().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
                first = true;
                firstV = Integer.parseInt(mulContext.value(0).NUM().getText());
            }
            else {
                String scopeOfVariable = findEnclosingScope(mulContext.value(0));
                int registerNumber = memory.resolveMemory(mulContext.value(0).identifier().getText(), scopeOfVariable, mulContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));
                if (memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue() != null){
                    first = true;
                    firstV = memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue();
                }
            }
            if (mulContext.value(1).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(mulContext.value(1).NUM().getText())));
                instructionList.addInstruction(new Instruction("STORE", 2));
                second = true;
                secondV = Integer.parseInt(mulContext.value(1).NUM().getText());
            }
            else {
                String scopeOfVariable = findEnclosingScope(mulContext.value(1));
                int registerNumber = memory.resolveMemory(mulContext.value(1).identifier().getText(), scopeOfVariable, mulContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 2));
                if (memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue() != null){
                    second = true;
                    secondV = memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue();
                }
            }

            //If we know values of both m's we save result to the variable in compiler memory
            if (first && second){
                memory.getMemCell(assignContext.identifier(), findEnclosingScope(assignContext)).setValue(firstV*secondV);
            }

            //Setting r3 result as 0
            instructionList.addInstruction(new Instruction("SET", 0));
            instructionList.addInstruction(new Instruction("STORE", 3));


        }
        else if (assignContext.expression() instanceof GrammarParser.DIVContext) {

        }
        else if (assignContext.expression() instanceof GrammarParser.MODContext) {

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
