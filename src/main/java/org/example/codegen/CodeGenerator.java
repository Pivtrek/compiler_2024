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
        } else if (node instanceof GrammarParser.IFContext) {
            generateIf((GrammarParser.IFContext) node);
            return; //without return commands inside if commands are produced twice
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

    private void generateIf(GrammarParser.IFContext ifContext){

        //if acc >0 we do skip, condition not true, if acc =0 we do the condition
        generateCondition(ifContext.condition());
        instructionList.addInstruction(new Instruction("JPOS", 1));
        int skipIfLabel = instructionList.getInstructions().size();
        traverse(ifContext.commands());
        int afterIfIndex = instructionList.getInstructions().size();
        instructionList.getInstructions().set(skipIfLabel-1, new Instruction("JPOS", afterIfIndex-skipIfLabel+1));

    }

    private void generateCondition(GrammarParser.ConditionContext conditionContext){

        //first value stored in r1, second in acc
        //if acc >0 we do skip, condition not true, if acc =0 we do the condition

        if (conditionContext instanceof GrammarParser.EQContext eqContext){
            if (eqContext.value(0).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(eqContext.value(0).NUM().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
            }
            else {
                String scopeOfVariable = findEnclosingScope(eqContext.value(0));
                int registerNumber = memory.resolveMemory(eqContext.value(0).identifier().getText(), scopeOfVariable, eqContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));

            }
            if (eqContext.value(1).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(eqContext.value(1).NUM().getText())));
            }
            else {
                String scopeOfVariable = findEnclosingScope(eqContext.value(1));
                int registerNumber = memory.resolveMemory(eqContext.value(1).identifier().getText(), scopeOfVariable, eqContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            }

            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("JZERO", 2));
            instructionList.addInstruction(new Instruction("SET", 1));


        } else if (conditionContext instanceof GrammarParser.NEQContext neqContext) {
            if (neqContext.value(0).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(neqContext.value(0).NUM().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
            }
            else {
                String scopeOfVariable = findEnclosingScope(neqContext.value(0));
                int registerNumber = memory.resolveMemory(neqContext.value(0).identifier().getText(), scopeOfVariable, neqContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));

            }
            if (neqContext.value(1).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(neqContext.value(1).NUM().getText())));
            }
            else {
                String scopeOfVariable = findEnclosingScope(neqContext.value(1));
                int registerNumber = memory.resolveMemory(neqContext.value(1).identifier().getText(), scopeOfVariable, neqContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            }
            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("JZERO", 3));
            instructionList.addInstruction(new Instruction("SET", 0));
            instructionList.addInstruction(new Instruction("JZERO", 2));
            instructionList.addInstruction(new Instruction("SET", 1));
            
        }else if (conditionContext instanceof GrammarParser.GTContext gtContext) {
            if (gtContext.value(0).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(gtContext.value(0).NUM().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
            }
            else {
                String scopeOfVariable = findEnclosingScope(gtContext.value(0));
                int registerNumber = memory.resolveMemory(gtContext.value(0).identifier().getText(), scopeOfVariable, gtContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));

            }
            if (gtContext.value(1).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(gtContext.value(1).NUM().getText())));
            }
            else {
                String scopeOfVariable = findEnclosingScope(gtContext.value(1));
                int registerNumber = memory.resolveMemory(gtContext.value(1).identifier().getText(), scopeOfVariable, gtContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            }

            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("JNEG", 3));
            instructionList.addInstruction(new Instruction("SET", 1));
            instructionList.addInstruction(new Instruction("JPOS", 2));
            instructionList.addInstruction(new Instruction("SET", 0));

        }else if (conditionContext instanceof GrammarParser.LTContext ltContext) {
            if (ltContext.value(0).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(ltContext.value(0).NUM().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
            }
            else {
                String scopeOfVariable = findEnclosingScope(ltContext.value(0));
                int registerNumber = memory.resolveMemory(ltContext.value(0).identifier().getText(), scopeOfVariable, ltContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));

            }
            if (ltContext.value(1).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(ltContext.value(1).NUM().getText())));
            }
            else {
                String scopeOfVariable = findEnclosingScope(ltContext.value(1));
                int registerNumber = memory.resolveMemory(ltContext.value(1).identifier().getText(), scopeOfVariable, ltContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            }

            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("JPOS", 3));
            instructionList.addInstruction(new Instruction("SET", 1));
            instructionList.addInstruction(new Instruction("JPOS", 2));
            instructionList.addInstruction(new Instruction("SET", 0));

        }else if (conditionContext instanceof GrammarParser.GEQContext geqContext) {
            if (geqContext.value(0).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(geqContext.value(0).NUM().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
            }
            else {
                String scopeOfVariable = findEnclosingScope(geqContext.value(0));
                int registerNumber = memory.resolveMemory(geqContext.value(0).identifier().getText(), scopeOfVariable, geqContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));

            }
            if (geqContext.value(1).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(geqContext.value(1).NUM().getText())));
            }
            else {
                String scopeOfVariable = findEnclosingScope(geqContext.value(1));
                int registerNumber = memory.resolveMemory(geqContext.value(1).identifier().getText(), scopeOfVariable, geqContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            }

            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("JZERO", 5)); //jump to set 0, 0 is already in acc just jump out of condition
            instructionList.addInstruction(new Instruction("JNEG", 3)); //jump to set 0
            instructionList.addInstruction(new Instruction("SET", 1));
            instructionList.addInstruction(new Instruction("JPOS", 2));
            instructionList.addInstruction(new Instruction("SET", 0));


        }else if (conditionContext instanceof GrammarParser.LEQContext leqContext) {
            if (leqContext.value(0).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(leqContext.value(0).NUM().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
            }
            else {
                String scopeOfVariable = findEnclosingScope(leqContext.value(0));
                int registerNumber = memory.resolveMemory(leqContext.value(0).identifier().getText(), scopeOfVariable, leqContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));

            }
            if (leqContext.value(1).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(leqContext.value(1).NUM().getText())));
            }
            else {
                String scopeOfVariable = findEnclosingScope(leqContext.value(1));
                int registerNumber = memory.resolveMemory(leqContext.value(1).identifier().getText(), scopeOfVariable, leqContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            }

            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("JZERO", 5)); //jump to set 0, 0 is already in acc just jump out of condition
            instructionList.addInstruction(new Instruction("JPOS", 3)); //jump to set 0
            instructionList.addInstruction(new Instruction("SET", 1));
            instructionList.addInstruction(new Instruction("JPOS", 2));
            instructionList.addInstruction(new Instruction("SET", 0));
        }
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
            r4 - =0 r1 is positive =-1 is negative
            r4 - =0 r2 is positive =-1 is negative
            r6 - if 0 result should be positive, else negative
            at the end result goes to acc - r0
             */

            boolean first = false, second = false;
            int firstV=0,secondV = 0;
            //setting result as 0
            instructionList.addInstruction(new Instruction("SET", 0));//setting result as 0
            instructionList.addInstruction(new Instruction("STORE", 3));

            //First part, storing multiplier and multiplicand to r1 and r2
            //TODO: OPTIMALIZATION --- while m1 and m2 in acc check for multiplying by 0 and 1 and jump to the end with correct result
            if (mulContext.value(0).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(mulContext.value(0).NUM().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
                //saving information about sign of number to register
                if (Integer.parseInt(mulContext.value(0).NUM().getText()) < 0){
                    instructionList.addInstruction(new Instruction("SET", -1));
                    instructionList.addInstruction(new Instruction("LOAD", 4));
                }
                else {
                    instructionList.addInstruction(new Instruction("LOAD", 3));
                    instructionList.addInstruction(new Instruction("STORE", 4));
                }
                first = true;
                firstV = Integer.parseInt(mulContext.value(0).NUM().getText());
            }
            else {
                String scopeOfVariable = findEnclosingScope(mulContext.value(0));
                int registerNumber = memory.resolveMemory(mulContext.value(0).identifier().getText(), scopeOfVariable, mulContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));
                instructionList.addInstruction(new Instruction("JNEG", 4));
                instructionList.addInstruction(new Instruction("LOAD", 3));
                instructionList.addInstruction(new Instruction("STORE", 4));
                instructionList.addInstruction(new Instruction("JUMP", 3));
                instructionList.addInstruction(new Instruction("SET", -1));
                instructionList.addInstruction(new Instruction("STORE", 4));


                if (memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue() != null){
                    first = true;
                    firstV = memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue();
                }
            }
            if (mulContext.value(1).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(mulContext.value(1).NUM().getText())));
                instructionList.addInstruction(new Instruction("STORE", 2));
                //saving information about sign of number to register
                if (Integer.parseInt(mulContext.value(1).NUM().getText()) < 0){
                    instructionList.addInstruction(new Instruction("SET", -1));
                    instructionList.addInstruction(new Instruction("LOAD", 5));
                }
                else {
                    instructionList.addInstruction(new Instruction("LOAD", 3));
                    instructionList.addInstruction(new Instruction("STORE", 5));
                }
                second = true;
                secondV = Integer.parseInt(mulContext.value(1).NUM().getText());
            }
            else {
                String scopeOfVariable = findEnclosingScope(mulContext.value(1));
                int registerNumber = memory.resolveMemory(mulContext.value(1).identifier().getText(), scopeOfVariable, mulContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 2));
                instructionList.addInstruction(new Instruction("JNEG", 4));
                instructionList.addInstruction(new Instruction("LOAD", 3));
                instructionList.addInstruction(new Instruction("STORE", 5));
                instructionList.addInstruction(new Instruction("JUMP", 3));
                instructionList.addInstruction(new Instruction("SET", -1));
                instructionList.addInstruction(new Instruction("STORE", 5));
                if (memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue() != null){
                    second = true;
                    secondV = memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue();
                }
            }
            //If we know values of both m's we save result to the variable in compiler memory
            if (first && second){
                memory.getMemCell(assignContext.identifier(), findEnclosingScope(assignContext)).setValue(firstV*secondV);
            }
            //Checking if result of multiplying should be + or - and saving it to r6
            instructionList.addInstruction(new Instruction("LOAD", 4));
            instructionList.addInstruction(new Instruction("SUB", 5));
            instructionList.addInstruction(new Instruction("STORE", 6));
            instructionList.addInstruction(new Instruction("LOAD", 1)); //check r1 if negative change its value to positive
            instructionList.addInstruction(new Instruction("JZERO", 5));
            instructionList.addInstruction(new Instruction("JPOS", 4));
            instructionList.addInstruction(new Instruction("LOAD", 3));
            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("STORE", 1));
            instructionList.addInstruction(new Instruction("LOAD", 2));
            instructionList.addInstruction(new Instruction("JZERO", 5));
            instructionList.addInstruction(new Instruction("JPOS", 4));
            instructionList.addInstruction(new Instruction("LOAD", 3));
            instructionList.addInstruction(new Instruction("SUB", 2));
            instructionList.addInstruction(new Instruction("STORE", 2));
            //Multiplying
            instructionList.addInstruction(new Instruction("LOAD", 1));
            instructionList.addInstruction(new Instruction("JZERO", 15));//exit number
            instructionList.addInstruction(new Instruction("HALF"));
            instructionList.addInstruction(new Instruction("ADD", 0));
            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("JZERO", 4)); //r0 = 0, not odd do not
            instructionList.addInstruction(new Instruction("LOAD", 3));
            instructionList.addInstruction(new Instruction("ADD", 2));
            instructionList.addInstruction(new Instruction("STORE", 3));
            instructionList.addInstruction(new Instruction("LOAD", 2));//double r2
            instructionList.addInstruction(new Instruction("ADD", 0));
            instructionList.addInstruction(new Instruction("STORE", 2));
            instructionList.addInstruction(new Instruction("LOAD", 1));//Halve r1
            instructionList.addInstruction(new Instruction("HALF"));
            instructionList.addInstruction(new Instruction("STORE", 1));
            instructionList.addInstruction(new Instruction("JUMP", (-15)));
            //Checking if result should be with + or - and saving it to acc
            instructionList.addInstruction(new Instruction("LOAD", 6));
            instructionList.addInstruction(new Instruction("JZERO", 4)); //Jump to exit of mul, result is positive
            instructionList.addInstruction(new Instruction("SET", 0));
            instructionList.addInstruction(new Instruction("SUB", 3));
            instructionList.addInstruction(new Instruction("STORE", 3));
            instructionList.addInstruction(new Instruction("LOAD", 3));//exit from loop here, loading result to acc
        }
        else if (assignContext.expression() instanceof GrammarParser.DIVContext divContext) {
            /*
            r0 - acc
            r1 - dividend
            r2 - divisor
            r3 - result
            r4 - =0 r1 is positive =-1 is negative
            r4 - =0 r2 is positive =-1 is negative
            r6 - if 0 result should be positive, else negative
            at the end result goes to acc - r0
             */

            boolean first = false, second = false;
            int firstV=0,secondV = 0;
            //setting result as 0
            instructionList.addInstruction(new Instruction("SET", 0));//setting result as 0
            instructionList.addInstruction(new Instruction("STORE", 3));

            //First part, storing multiplier and multiplicand to r1 and r2
            //TODO: OPTIMALIZATION --- while r1 and r2 in acc check for division by 0 and 1 and jump to the end with correct result
            if (divContext.value(0).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(divContext.value(0).NUM().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
                //saving information about sign of number to register
                if (Integer.parseInt(divContext.value(0).NUM().getText()) < 0){
                    instructionList.addInstruction(new Instruction("SET", -1));
                    instructionList.addInstruction(new Instruction("LOAD", 4));
                }
                else {
                    instructionList.addInstruction(new Instruction("LOAD", 3));
                    instructionList.addInstruction(new Instruction("STORE", 4));
                }
                first = true;
                firstV = Integer.parseInt(divContext.value(0).NUM().getText());
            }
            else {
                String scopeOfVariable = findEnclosingScope(divContext.value(0));
                int registerNumber = memory.resolveMemory(divContext.value(0).identifier().getText(), scopeOfVariable, divContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));
                instructionList.addInstruction(new Instruction("JNEG", 4));
                instructionList.addInstruction(new Instruction("LOAD", 3));
                instructionList.addInstruction(new Instruction("STORE", 4));
                instructionList.addInstruction(new Instruction("JUMP", 3));
                instructionList.addInstruction(new Instruction("SET", -1));
                instructionList.addInstruction(new Instruction("STORE", 4));


                if (memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue() != null){
                    first = true;
                    firstV = memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue();
                }
            }
            if (divContext.value(1).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(divContext.value(1).NUM().getText())));
                instructionList.addInstruction(new Instruction("STORE", 2));
                //saving information about sign of number to register
                if (Integer.parseInt(divContext.value(1).NUM().getText()) < 0){
                    instructionList.addInstruction(new Instruction("SET", -1));
                    instructionList.addInstruction(new Instruction("LOAD", 5));
                }
                else {
                    instructionList.addInstruction(new Instruction("LOAD", 3));
                    instructionList.addInstruction(new Instruction("STORE", 5));
                }
                second = true;
                secondV = Integer.parseInt(divContext.value(1).NUM().getText());
            }
            else {
                String scopeOfVariable = findEnclosingScope(divContext.value(1));
                int registerNumber = memory.resolveMemory(divContext.value(1).identifier().getText(), scopeOfVariable, divContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 2));
                instructionList.addInstruction(new Instruction("JNEG", 4));
                instructionList.addInstruction(new Instruction("LOAD", 3));
                instructionList.addInstruction(new Instruction("STORE", 5));
                instructionList.addInstruction(new Instruction("JUMP", 3));
                instructionList.addInstruction(new Instruction("SET", -1));
                instructionList.addInstruction(new Instruction("STORE", 5));
                if (memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue() != null){
                    second = true;
                    secondV = memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue();
                }
            }
            //If we know values of both m's we save result to the variable in compiler memory
            if (first && second){
                memory.getMemCell(assignContext.identifier(), findEnclosingScope(assignContext)).setValue(firstV*secondV);
            }

            //Checking if result of division should be + or - and saving it to r6, changing r1 and r2 to + if neccesary
            instructionList.addInstruction(new Instruction("LOAD", 4));
            instructionList.addInstruction(new Instruction("SUB", 5));
            instructionList.addInstruction(new Instruction("STORE", 6));
            instructionList.addInstruction(new Instruction("LOAD", 1)); //check r1 if negative change its value to positive
            instructionList.addInstruction(new Instruction("JZERO", 5));
            instructionList.addInstruction(new Instruction("JPOS", 4));
            instructionList.addInstruction(new Instruction("LOAD", 3));
            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("STORE", 1));
            instructionList.addInstruction(new Instruction("LOAD", 2));
            instructionList.addInstruction(new Instruction("JZERO", 5));
            instructionList.addInstruction(new Instruction("JPOS", 4));
            instructionList.addInstruction(new Instruction("LOAD", 3));
            instructionList.addInstruction(new Instruction("SUB", 2));
            instructionList.addInstruction(new Instruction("STORE", 2));
            //Division

            instructionList.addInstruction(new Instruction("SET", 1));
            instructionList.addInstruction(new Instruction("STORE", 4));

            instructionList.addInstruction(new Instruction("LOAD", 2));
            instructionList.addInstruction(new Instruction("STORE", 7));
            instructionList.addInstruction(new Instruction("LOAD", 1));
            instructionList.addInstruction(new Instruction("SUB", 7));
            instructionList.addInstruction(new Instruction("JNEG", 8));
            instructionList.addInstruction(new Instruction("LOAD", 7));
            instructionList.addInstruction(new Instruction("ADD", 7));
            instructionList.addInstruction(new Instruction("STORE", 7));
            instructionList.addInstruction(new Instruction("LOAD", 4));
            instructionList.addInstruction(new Instruction("ADD", 4));
            instructionList.addInstruction(new Instruction("STORE", 4));
            instructionList.addInstruction(new Instruction("JUMP", -9));

            instructionList.addInstruction(new Instruction("LOAD", 7));
            instructionList.addInstruction(new Instruction("HALF"));
            instructionList.addInstruction(new Instruction("STORE", 7));

            instructionList.addInstruction(new Instruction("LOAD", 4));
            instructionList.addInstruction(new Instruction("HALF"));
            instructionList.addInstruction(new Instruction("STORE", 4));

            instructionList.addInstruction(new Instruction("LOAD", 7));
            instructionList.addInstruction(new Instruction("JZERO", 24)); //JUMP OUT OF DIVISION
            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("JPOS", 14));
            instructionList.addInstruction(new Instruction("LOAD", 1));
            instructionList.addInstruction(new Instruction("SUB", 7));
            instructionList.addInstruction(new Instruction("STORE", 1));
            instructionList.addInstruction(new Instruction("LOAD", 3));
            instructionList.addInstruction(new Instruction("ADD", 4));
            instructionList.addInstruction(new Instruction("STORE", 3));
            instructionList.addInstruction(new Instruction("LOAD", 4));
            instructionList.addInstruction(new Instruction("HALF"));
            instructionList.addInstruction(new Instruction("STORE", 4));
            instructionList.addInstruction(new Instruction("LOAD", 7));
            instructionList.addInstruction(new Instruction("HALF"));
            instructionList.addInstruction(new Instruction("STORE", 7));
            instructionList.addInstruction(new Instruction("JUMP", -16));
            instructionList.addInstruction(new Instruction("LOAD", 7));
            instructionList.addInstruction(new Instruction("HALF"));
            instructionList.addInstruction(new Instruction("STORE", 7));
            instructionList.addInstruction(new Instruction("LOAD", 4));
            instructionList.addInstruction(new Instruction("HALF"));
            instructionList.addInstruction(new Instruction("STORE", 4));
            instructionList.addInstruction(new Instruction("JUMP", -23));

            //Checking if result should be with + or - and saving it to acc
            instructionList.addInstruction(new Instruction("LOAD", 6));
            instructionList.addInstruction(new Instruction("JZERO", 4)); //Jump to exit of mul, result is positive
            instructionList.addInstruction(new Instruction("SET", 0));
            instructionList.addInstruction(new Instruction("SUB", 3));
            instructionList.addInstruction(new Instruction("STORE", 3));
            instructionList.addInstruction(new Instruction("LOAD", 3));//exit from loop here, loading result to acc

            //TODO: cant provoke to negative result TO DEBUG, WHY THATS NOT WORK ?
        }
        else if (assignContext.expression() instanceof GrammarParser.MODContext modContext) {
             /*
            r0 - acc
            r1 - multiplier
            r2 - multiplicand
            r3 - result
            r4 - =0 r1 is positive =-1 is negative
            r4 - =0 r2 is positive =-1 is negative
            r6 - if 0 result should be positive, else negative
            at the end result goes to acc - r0
             */

            boolean first = false, second = false;
            int firstV=0,secondV = 0;
            //setting result as 0
            instructionList.addInstruction(new Instruction("SET", 0));//setting result as 0
            instructionList.addInstruction(new Instruction("STORE", 3));

            //First part, storing multiplier and multiplicand to r1 and r2
            //TODO: OPTIMALIZATION --- while m1 and m2 in acc check for multiplying by 0 and 1 and jump to the end with correct result
            if (modContext.value(0).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(modContext.value(0).NUM().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
                //saving information about sign of number to register
                if (Integer.parseInt(modContext.value(0).NUM().getText()) < 0){
                    instructionList.addInstruction(new Instruction("SET", -1));
                    instructionList.addInstruction(new Instruction("LOAD", 4));
                }
                else {
                    instructionList.addInstruction(new Instruction("LOAD", 3));
                    instructionList.addInstruction(new Instruction("STORE", 4));
                }
                first = true;
                firstV = Integer.parseInt(modContext.value(0).NUM().getText());
            }
            else {
                String scopeOfVariable = findEnclosingScope(modContext.value(0));
                int registerNumber = memory.resolveMemory(modContext.value(0).identifier().getText(), scopeOfVariable, modContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));
                instructionList.addInstruction(new Instruction("JNEG", 4));
                instructionList.addInstruction(new Instruction("LOAD", 3));
                instructionList.addInstruction(new Instruction("STORE", 4));
                instructionList.addInstruction(new Instruction("JUMP", 3));
                instructionList.addInstruction(new Instruction("SET", -1));
                instructionList.addInstruction(new Instruction("STORE", 4));


                if (memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue() != null){
                    first = true;
                    firstV = memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue();
                }
            }
            if (modContext.value(1).NUM() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(modContext.value(1).NUM().getText())));
                instructionList.addInstruction(new Instruction("STORE", 2));
                //saving information about sign of number to register
                if (Integer.parseInt(modContext.value(1).NUM().getText()) < 0){
                    instructionList.addInstruction(new Instruction("SET", -1));
                    instructionList.addInstruction(new Instruction("LOAD", 5));
                }
                else {
                    instructionList.addInstruction(new Instruction("LOAD", 3));
                    instructionList.addInstruction(new Instruction("STORE", 5));
                }
                second = true;
                secondV = Integer.parseInt(modContext.value(1).NUM().getText());
            }
            else {
                String scopeOfVariable = findEnclosingScope(modContext.value(1));
                int registerNumber = memory.resolveMemory(modContext.value(1).identifier().getText(), scopeOfVariable, modContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 2));
                instructionList.addInstruction(new Instruction("JNEG", 4));
                instructionList.addInstruction(new Instruction("LOAD", 3));
                instructionList.addInstruction(new Instruction("STORE", 5));
                instructionList.addInstruction(new Instruction("JUMP", 3));
                instructionList.addInstruction(new Instruction("SET", -1));
                instructionList.addInstruction(new Instruction("STORE", 5));
                if (memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue() != null){
                    second = true;
                    secondV = memory.getMemCell(assignContext.identifier(), scopeOfVariable).getValue();
                }
            }
            //If we know values of both m's we save result to the variable in compiler memory
            if (first && second){
                memory.getMemCell(assignContext.identifier(), findEnclosingScope(assignContext)).setValue(firstV*secondV);
            }
            //Checking if result of multiplying should be + or - and saving it to r6
            instructionList.addInstruction(new Instruction("LOAD", 4));
            instructionList.addInstruction(new Instruction("SUB", 5));
            instructionList.addInstruction(new Instruction("STORE", 6));
            instructionList.addInstruction(new Instruction("LOAD", 1)); //check r1 if negative change its value to positive
            instructionList.addInstruction(new Instruction("JZERO", 5));
            instructionList.addInstruction(new Instruction("JPOS", 4));
            instructionList.addInstruction(new Instruction("LOAD", 3));
            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("STORE", 1));
            instructionList.addInstruction(new Instruction("LOAD", 2));
            instructionList.addInstruction(new Instruction("JZERO", 5));
            instructionList.addInstruction(new Instruction("JPOS", 4));
            instructionList.addInstruction(new Instruction("LOAD", 3));
            instructionList.addInstruction(new Instruction("SUB", 2));
            instructionList.addInstruction(new Instruction("STORE", 2));


            //Saving r1 to r5
            instructionList.addInstruction(new Instruction("LOAD", 1));
            instructionList.addInstruction(new Instruction("STORE", 5));

            //division

            instructionList.addInstruction(new Instruction("SET", 1));
            instructionList.addInstruction(new Instruction("STORE", 4));

            instructionList.addInstruction(new Instruction("LOAD", 2));
            instructionList.addInstruction(new Instruction("STORE", 7));
            instructionList.addInstruction(new Instruction("LOAD", 1));
            instructionList.addInstruction(new Instruction("SUB", 7));
            instructionList.addInstruction(new Instruction("JNEG", 8));
            instructionList.addInstruction(new Instruction("LOAD", 7));
            instructionList.addInstruction(new Instruction("ADD", 7));
            instructionList.addInstruction(new Instruction("STORE", 7));
            instructionList.addInstruction(new Instruction("LOAD", 4));
            instructionList.addInstruction(new Instruction("ADD", 4));
            instructionList.addInstruction(new Instruction("STORE", 4));
            instructionList.addInstruction(new Instruction("JUMP", -9));

            instructionList.addInstruction(new Instruction("LOAD", 7));
            instructionList.addInstruction(new Instruction("HALF"));
            instructionList.addInstruction(new Instruction("STORE", 7));

            instructionList.addInstruction(new Instruction("LOAD", 4));
            instructionList.addInstruction(new Instruction("HALF"));
            instructionList.addInstruction(new Instruction("STORE", 4));

            instructionList.addInstruction(new Instruction("LOAD", 7));
            instructionList.addInstruction(new Instruction("JZERO", 24)); //JUMP OUT OF DIVISION
            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("JPOS", 14));
            instructionList.addInstruction(new Instruction("LOAD", 1));
            instructionList.addInstruction(new Instruction("SUB", 7));
            instructionList.addInstruction(new Instruction("STORE", 1));
            instructionList.addInstruction(new Instruction("LOAD", 3));
            instructionList.addInstruction(new Instruction("ADD", 4));
            instructionList.addInstruction(new Instruction("STORE", 3));
            instructionList.addInstruction(new Instruction("LOAD", 4));
            instructionList.addInstruction(new Instruction("HALF"));
            instructionList.addInstruction(new Instruction("STORE", 4));
            instructionList.addInstruction(new Instruction("LOAD", 7));
            instructionList.addInstruction(new Instruction("HALF"));
            instructionList.addInstruction(new Instruction("STORE", 7));
            instructionList.addInstruction(new Instruction("JUMP", -16));
            instructionList.addInstruction(new Instruction("LOAD", 7));
            instructionList.addInstruction(new Instruction("HALF"));
            instructionList.addInstruction(new Instruction("STORE", 7));
            instructionList.addInstruction(new Instruction("LOAD", 4));
            instructionList.addInstruction(new Instruction("HALF"));
            instructionList.addInstruction(new Instruction("STORE", 4));
            instructionList.addInstruction(new Instruction("JUMP", -23));

            instructionList.addInstruction(new Instruction("LOAD", 6));
            instructionList.addInstruction(new Instruction("JZERO", 4)); //Jump to exit of mul, result is positive
            instructionList.addInstruction(new Instruction("SET", 0));
            instructionList.addInstruction(new Instruction("SUB", 3));
            instructionList.addInstruction(new Instruction("STORE", 3));
            instructionList.addInstruction(new Instruction("LOAD", 3));//exit from loop here, loading result to acc


            // a/b -- a-r5 b-r2, a/b floor is in r3


            instructionList.addInstruction(new Instruction("STORE", 1));


            // r1 - multiplier
            // r2 - multiplicand
            // r3 - result

            instructionList.addInstruction(new Instruction("SET", 0));
            instructionList.addInstruction(new Instruction("STORE", 3));


            //Multiplying
            instructionList.addInstruction(new Instruction("LOAD", 1));
            instructionList.addInstruction(new Instruction("JZERO", 15));//exit number
            instructionList.addInstruction(new Instruction("HALF"));
            instructionList.addInstruction(new Instruction("ADD", 0));
            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("JZERO", 4)); //r0 = 0, not odd do not
            instructionList.addInstruction(new Instruction("LOAD", 3));
            instructionList.addInstruction(new Instruction("ADD", 2));
            instructionList.addInstruction(new Instruction("STORE", 3));
            instructionList.addInstruction(new Instruction("LOAD", 2));//double r2
            instructionList.addInstruction(new Instruction("ADD", 0));
            instructionList.addInstruction(new Instruction("STORE", 2));
            instructionList.addInstruction(new Instruction("LOAD", 1));//Halve r1
            instructionList.addInstruction(new Instruction("HALF"));
            instructionList.addInstruction(new Instruction("STORE", 1));
            instructionList.addInstruction(new Instruction("JUMP", (-15)));

            instructionList.addInstruction(new Instruction("LOAD", 5));
            instructionList.addInstruction(new Instruction("SUB", 3));


            //TODO negative result, using 0, and using bigger divisor than dividend

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
