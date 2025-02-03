package org.example.codegen;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.example.memory.MemCell;
import org.example.memory.Memory;
import org.example.parser.GrammarParser;
import org.example.semantic.Symbol;
import org.example.semantic.SymbolTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenerator {
    private Memory memory;
    private ParseTree tree;
    private InstructionList instructionList;
    private Map<String, Integer> procedureAdresses;
    private SymbolTable symbolTable;

    private int stackIndex = 1;

    public CodeGenerator(Memory memory, ParseTree tree, SymbolTable symbolTable) {
        this.memory = memory;
        this.tree = tree;
        this.instructionList = new InstructionList();
        this.procedureAdresses = new HashMap<>();
        this.symbolTable = symbolTable;
    }

    public void genereteCode(){
        instructionList.addInstruction(new Instruction("SET",stackIndex));
        int stackPointerRegister = memory.resolveMemory("stack:0","GLOBAL");
        instructionList.addInstruction(new Instruction("STORE",stackPointerRegister));
        instructionList.addInstruction(new Instruction("JUMP", 0));
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
        } else if (node instanceof GrammarParser.IFELSEContext) {
            generateIfElse((GrammarParser.IFELSEContext) node);
            return; ////without return commands inside if else commands are produced twice
        } else if (node instanceof GrammarParser.FORUPContext){
            generateForUp((GrammarParser.FORUPContext) node);
            return;
        } else if (node instanceof GrammarParser.FORDOWNTOContext) {
            generateForDown((GrammarParser.FORDOWNTOContext) node);
            return;
        } else if (node instanceof GrammarParser.CALLPROCContext) {
            generateProcCall((GrammarParser.CALLPROCContext) node);
        } else if (node instanceof GrammarParser.WHILEContext) {
            generateWhile((GrammarParser.WHILEContext) node);
            return;
        } else if (node instanceof GrammarParser.REPEATUNTILContext) {
            generateRepeatUntil((GrammarParser.REPEATUNTILContext) node);
            return;
        } else if (node instanceof GrammarParser.PROCEDUREWITHDECLARATIONSContext ||
                node instanceof GrammarParser.PROCEDUREWITHOUTDECLARATIONSContext) {
            generateProcedure(node);
            return;
        } else if (node instanceof GrammarParser.MAINDECLARATIONSContext || node instanceof GrammarParser.MAINWITHOUTDECLARATIONSContext) {
            generateMainProgram(node);
            return;
        }
        for (int i = 0; i < node.getChildCount(); i++) {
            traverse(node.getChild(i));
        }
    }

    private void generateMainProgram(ParseTree node){
        int mainProgramJumpAdress = instructionList.getInstructions().size()-2;
        instructionList.getInstructions().set(2,new Instruction("JUMP", mainProgramJumpAdress));
        if (node instanceof GrammarParser.MAINWITHOUTDECLARATIONSContext mainwithoutdeclarationsContext){
            traverse(mainwithoutdeclarationsContext.commands());
        } else if (node instanceof GrammarParser.MAINDECLARATIONSContext maindeclarationsContext) {
            traverse(maindeclarationsContext.commands());
        }
    }

    private void generateProcedure(ParseTree node){
        if (node instanceof GrammarParser.PROCEDUREWITHOUTDECLARATIONSContext procedureContext){
            int procedureStartAdress = instructionList.getInstructions().size();
            procedureAdresses.put(procedureContext.proc_head().PIDENTIFIER().getText(), procedureStartAdress);
            traverse(procedureContext.commands());

            //getting back from procedure
            int stackPointerRegister = memory.resolveMemory("stack:0","GLOBAL");
            instructionList.addInstruction(new Instruction("SET",-1));
            instructionList.addInstruction(new Instruction("ADD",stackPointerRegister));
            instructionList.addInstruction(new Instruction("STORE", stackPointerRegister));
            instructionList.addInstruction(new Instruction("SET", stackPointerRegister));
            instructionList.addInstruction(new Instruction("ADD", stackPointerRegister));
            instructionList.addInstruction(new Instruction("LOADI", 0));
            instructionList.addInstruction(new Instruction("STORE", 10));
            instructionList.addInstruction(new Instruction("RTRN", 10));

        } else if (node instanceof GrammarParser.PROCEDUREWITHDECLARATIONSContext procedureContext) {
            int procedureStartAdress = instructionList.getInstructions().size();
            procedureAdresses.put(procedureContext.proc_head().PIDENTIFIER().getText(), procedureStartAdress);
            traverse(procedureContext.commands());
            //getting back from procedure
            int stackPointerRegister = memory.resolveMemory("stack:0","GLOBAL");
            instructionList.addInstruction(new Instruction("SET",-1));
            instructionList.addInstruction(new Instruction("ADD",stackPointerRegister));
            instructionList.addInstruction(new Instruction("STORE", stackPointerRegister));
            instructionList.addInstruction(new Instruction("SET", stackPointerRegister));
            instructionList.addInstruction(new Instruction("ADD", stackPointerRegister));
            instructionList.addInstruction(new Instruction("LOADI", 0));
            instructionList.addInstruction(new Instruction("STORE", 10));
            instructionList.addInstruction(new Instruction("RTRN", 10));
        }
    }

    private void generateProcCall(GrammarParser.CALLPROCContext callprocContext){
        String procedureName = callprocContext.proc_call().PIDENTIFIER().getText();
        String currentScope = findEnclosingScope(callprocContext);
        int procedureAdress = procedureAdresses.get(procedureName);

        //Set parameters of procedured called with given values
        int lenghtBeforeAssignment = instructionList.getInstructions().size();
        for (int i = 0; i < symbolTable.getSymbol(procedureName).getParameters().size(); i++) {
            Symbol parameter = symbolTable.getSymbol(procedureName).getParameters().get(i);
            if(parameter.getType().equals(Symbol.SymbolType.INT)){
                String argumentName = callprocContext.proc_call().args().PIDENTIFIER(i).getText();
                String parameterName = parameter.getName();
                int argumentRegister = memory.getMemCell(argumentName, currentScope).getRegisterNumber();
                int parameterRegister = memory.getMemCell(parameterName, procedureName).getRegisterNumber();
                instructionList.addInstruction(new Instruction("LOAD", argumentRegister));
                instructionList.addInstruction(new Instruction("STORE", parameterRegister));
            } else if (parameter.getType().equals(Symbol.SymbolType.ARRAY)) {
                String argumentName = callprocContext.proc_call().args().PIDENTIFIER(i).getText();
                String parameterName = parameter.getName();
                for (Symbol array: symbolTable.getSymbol(currentScope).getLocalVariables()) {
                    if (array.getType().equals(Symbol.SymbolType.ARRAY) && array.getName().equals(argumentName)){
                        for (int j = array.getLowerBound(); j <= array.getUpperBound(); j++) {
                            String arrayName = parameterName + "[" + j + "]";
                            String argumentArray = argumentName + "[" + j + "]";
                            int argumentRegister = memory.resolveMemory(argumentArray, currentScope);
                            int parameterRegister = memory.resolveMemory(arrayName, procedureName);
                            instructionList.addInstruction(new Instruction("LOAD", argumentRegister));
                            instructionList.addInstruction(new Instruction("STORE", parameterRegister));
                        }
                    }
                }
            }
        }
        int lengthAfterAssignment = instructionList.getInstructions().size();
        //getting rtrn adress to p10 and stack
        int getBackAdress = instructionList.getInstructions().size() + 10;
        int stackPointerRegister = memory.resolveMemory("stack:0","GLOBAL");
        instructionList.addInstruction(new Instruction("SET", stackPointerRegister));
        instructionList.addInstruction(new Instruction("ADD", stackPointerRegister));
        instructionList.addInstruction(new Instruction("STORE", 9));
        instructionList.addInstruction(new Instruction("SET", getBackAdress));
        instructionList.addInstruction(new Instruction("STORE",10));
        instructionList.addInstruction(new Instruction("STOREI",9));

        //Stack pointer +1
        instructionList.addInstruction(new Instruction("SET",1));
        instructionList.addInstruction(new Instruction("ADD",stackPointerRegister));
        instructionList.addInstruction(new Instruction("STORE", stackPointerRegister));

        //jump to procedure
        int currentLength = instructionList.getInstructions().size();
        instructionList.addInstruction(new Instruction("JUMP", -(currentLength-procedureAdress)));

        //Writing parameters again to arguments in program
        for (int i = lenghtBeforeAssignment; i <lengthAfterAssignment ; i=i+2) {
            int loadRegister = instructionList.getInstructions().get(i).getOperand();
            int storeRegister = instructionList.getInstructions().get(i+1).getOperand();
            instructionList.addInstruction(new Instruction("LOAD", storeRegister));
            instructionList.addInstruction(new Instruction("STORE", loadRegister));
        }
    }
    private void generateRepeatUntil(GrammarParser.REPEATUNTILContext repeatuntilContext){
        //Repeat until condition has condition on the bottom of repeat commands, so it will turn on minimum once
        int beforeCommands = instructionList.getInstructions().size();
        traverse(repeatuntilContext.commands());
        generateCondition(repeatuntilContext.condition());
        int afterCommandsConditions = instructionList.getInstructions().size();
        //if acc=0 -> repeat commands
        instructionList.addInstruction(new Instruction("JPOS", -(afterCommandsConditions - beforeCommands )));
    }

    private void generateWhile(GrammarParser.WHILEContext whileContext){
        //if acc >0 we do skip, condition not true, if acc =0 we do the condition
        int startOfCondition = instructionList.getInstructions().size();
        generateCondition(whileContext.condition());
        instructionList.addInstruction(new Instruction("JPOS", 1));
        int beforeCommands = instructionList.getInstructions().size();
        traverse(whileContext.commands());
        int afterCommands = instructionList.getInstructions().size();
        instructionList.getInstructions().set(beforeCommands-1, new Instruction("JPOS", afterCommands-beforeCommands+2));
        instructionList.addInstruction(new Instruction("JUMP", -(afterCommands - startOfCondition)));
    }

    private void generateRead(GrammarParser.READContext readContext){

        String scope = findEnclosingScope(readContext);
        String name = readContext.identifier().getText();
        int registerNumber = resolveMemory(name, scope, readContext.identifier());

        instructionList.addInstruction(new Instruction("GET", registerNumber));
    }

    private void generateWrite(GrammarParser.WRITEContext writeContext){
        // WRITE 123
        if (writeContext.value().signedNum() != null){

            int number = Integer.parseInt(writeContext.value().signedNum().getText());
            //Setting given number in acc and showing it on console
            instructionList.addInstruction(new Instruction("SET", number));
            instructionList.addInstruction(new Instruction("PUT", 0));
        } else if (writeContext.value().identifier() != null) {
            String scope = findEnclosingScope(writeContext);
            String name = writeContext.value().getText();
            int registerNumber = resolveMemory(name, scope, writeContext.value().identifier());
            instructionList.addInstruction(new Instruction("PUT", registerNumber));
        }
    }

    private void generateAssign(GrammarParser.ASSIGNContext assignContext){

        if (assignContext.identifier() instanceof GrammarParser.ARRAYWITHPIDUSAGEContext arrayContext){
            String targetName = assignContext.identifier().getText();
            String scope = findEnclosingScope(assignContext);

            String baseAdressName = arrayContext.PIDENTIFIER(0).getText() + ":baseAddress";
            int baseAdress = memory.getMemCell(baseAdressName, scope).getValue();
            int iteratorRegister = memory.resolveMemory(arrayContext.PIDENTIFIER(1).getText(),scope);

            instructionList.addInstruction(new Instruction("SET",baseAdress));
            instructionList.addInstruction(new Instruction("ADD",iteratorRegister));
            instructionList.addInstruction(new Instruction("STORE",12));
            handleExpressionContext(assignContext, targetName, scope);
            instructionList.addInstruction(new Instruction("STOREI",12));


        }
        else {
            String targetName = assignContext.identifier().getText();
            String scope = findEnclosingScope(assignContext);
            int registerNumber = resolveMemory(targetName, scope, assignContext.identifier());
            handleExpressionContext(assignContext, targetName, scope);
            //after completing handling right hand side of assign, value of it its in p0 and goes to variable
            instructionList.addInstruction(new Instruction("STORE",registerNumber));
        }
    }

    private void generateForUp(GrammarParser.FORUPContext forupContext){
        //taking iterator and giving him first value
        String scope = findEnclosingScope(forupContext);
        int iteratorRegister = memory.resolveMemory(forupContext.PIDENTIFIER().getText(), scope);
        int forLenRegister = memory.resolveMemory(forupContext.PIDENTIFIER().getText() + "LEN", scope);
        //saving from value to r1 and to value to r2
        if (forupContext.value(0).signedNum() != null){
            instructionList.addInstruction(new Instruction("SET", Integer.parseInt(forupContext.value(0).signedNum().getText())));
            instructionList.addInstruction(new Instruction("STORE", 1));
            memory.getMemCell(forupContext.PIDENTIFIER().getText(), scope).setValue(Integer.parseInt(forupContext.value(0).signedNum().getText()));
        }
        else {
            String scopeOfVariable = findEnclosingScope(forupContext.value(0));
            int registerNumber = resolveMemory(forupContext.value(0).identifier().getText(), scopeOfVariable, forupContext.value(0).identifier());
            instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            instructionList.addInstruction(new Instruction("STORE", 1));
            //TODO: IF STARTING VALUE IS IN MEMORY OF COMPILER ASSIGN IT TO INTEGER
        }
        if (forupContext.value(1).signedNum() != null){
            instructionList.addInstruction(new Instruction("SET", Integer.parseInt(forupContext.value(1).signedNum().getText())));
            instructionList.addInstruction(new Instruction("STORE", 2));
        }
        else {
            String scopeOfVariable = findEnclosingScope(forupContext.value(1));
            int registerNumber = resolveMemory(forupContext.value(1).identifier().getText(), scopeOfVariable, forupContext.value(1).identifier());
            instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            instructionList.addInstruction(new Instruction("STORE", 2));
        }

        //before array there is memory with saved lowerbound of array

        instructionList.addInstruction(new Instruction("SET", 1));
        instructionList.addInstruction(new Instruction("STORE", 8)); //storing value "1" for loop iteration
        instructionList.addInstruction(new Instruction("LOAD", 1));
        instructionList.addInstruction(new Instruction("STORE", iteratorRegister));
        instructionList.addInstruction(new Instruction("LOAD", 2));
        instructionList.addInstruction(new Instruction("SUB", 1));
        instructionList.addInstruction(new Instruction("ADD", 8));
        instructionList.addInstruction(new Instruction("STORE", forLenRegister)); //storing how many times loop should go
        instructionList.addInstruction(new Instruction("JZERO", 1));
        int beforeCommands = instructionList.getInstructions().size();
        traverse(forupContext.commands());
        instructionList.addInstruction(new Instruction("LOAD", iteratorRegister));
        instructionList.addInstruction(new Instruction("ADD", 8));
        instructionList.addInstruction(new Instruction("STORE", iteratorRegister));
        instructionList.addInstruction(new Instruction("LOAD", forLenRegister));
        instructionList.addInstruction(new Instruction("SUB", 8));
        instructionList.addInstruction(new Instruction("STORE", forLenRegister));
        int afterCommands = instructionList.getInstructions().size();
        instructionList.addInstruction(new Instruction("JUMP",-(afterCommands-beforeCommands+1)));
        instructionList.getInstructions().set(beforeCommands-1, new Instruction("JZERO", afterCommands-beforeCommands+2));
    }

    private void generateForDown(GrammarParser.FORDOWNTOContext fordowntoContext){
        //taking iterator and giving him first value
        String scope = findEnclosingScope(fordowntoContext);
        int iteratorRegister = memory.resolveMemory(fordowntoContext.PIDENTIFIER().getText(), scope);
        int forLenRegister = memory.resolveMemory(fordowntoContext.PIDENTIFIER().getText() + "LEN", scope);
        //saving from value to r1 and to value to r2
        if (fordowntoContext.value(0).signedNum() != null){
            instructionList.addInstruction(new Instruction("SET", Integer.parseInt(fordowntoContext.value(0).signedNum().getText())));
            instructionList.addInstruction(new Instruction("STORE", 1));
            memory.getMemCell(fordowntoContext.PIDENTIFIER().getText(), scope).setValue(Integer.parseInt(fordowntoContext.value(0).signedNum().getText()));
        }
        else {
            String scopeOfVariable = findEnclosingScope(fordowntoContext.value(0));
            int registerNumber = resolveMemory(fordowntoContext.value(0).identifier().getText(), scopeOfVariable, fordowntoContext.value(0).identifier());
            instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            instructionList.addInstruction(new Instruction("STORE", 1));
            //TODO: IF STARTING VALUE IS IN MEMORY OF COMPILER ASSIGN IT TO INTEGER
        }
        if (fordowntoContext.value(1).signedNum() != null){
            instructionList.addInstruction(new Instruction("SET", Integer.parseInt(fordowntoContext.value(1).signedNum().getText())));
            instructionList.addInstruction(new Instruction("STORE", 2));
        }
        else {
            String scopeOfVariable = findEnclosingScope(fordowntoContext.value(1));
            int registerNumber = resolveMemory(fordowntoContext.value(1).identifier().getText(), scopeOfVariable, fordowntoContext.value(1).identifier());
            instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            instructionList.addInstruction(new Instruction("STORE", 2));
        }

        instructionList.addInstruction(new Instruction("SET", -1));
        instructionList.addInstruction(new Instruction("STORE", 8)); //storing value "1" for loop iteration
        instructionList.addInstruction(new Instruction("LOAD", 1));
        instructionList.addInstruction(new Instruction("STORE", iteratorRegister));
        instructionList.addInstruction(new Instruction("LOAD", 2));
        instructionList.addInstruction(new Instruction("SUB", 1));
        instructionList.addInstruction(new Instruction("ADD", 8));
        instructionList.addInstruction(new Instruction("STORE", forLenRegister)); //storing how many times loop should go
        instructionList.addInstruction(new Instruction("JZERO", 1));
        int beforeCommands = instructionList.getInstructions().size();
        traverse(fordowntoContext.commands());
        instructionList.addInstruction(new Instruction("LOAD", iteratorRegister));
        instructionList.addInstruction(new Instruction("ADD", 8));
        instructionList.addInstruction(new Instruction("STORE", iteratorRegister));
        instructionList.addInstruction(new Instruction("LOAD", forLenRegister));
        instructionList.addInstruction(new Instruction("SUB", 8));
        instructionList.addInstruction(new Instruction("STORE", forLenRegister));
        int afterCommands = instructionList.getInstructions().size();
        instructionList.addInstruction(new Instruction("JUMP",-(afterCommands-beforeCommands+1)));
        instructionList.getInstructions().set(beforeCommands-1, new Instruction("JZERO", afterCommands-beforeCommands+2));

    }

    private void generateIfElse(GrammarParser.IFELSEContext ifelseContext){
        //if acc >0 we go to else, condition not true, if acc =0 we do the if condition and skip else
        generateCondition(ifelseContext.condition());
        instructionList.addInstruction(new Instruction("JPOS", 1));
        int skipIfLabel = instructionList.getInstructions().size();
        traverse(ifelseContext.commands(0)); //generate if commands
        int afterIfIndex = instructionList.getInstructions().size();//if commands
        instructionList.getInstructions().set(skipIfLabel-1, new Instruction("JPOS", afterIfIndex-skipIfLabel+2));
        instructionList.addInstruction(new Instruction("SET", 0));//on the last step need to set acc to 0 because its telling to skip else
        instructionList.addInstruction(new Instruction("JZERO", 1));
        int skipElseLabel = instructionList.getInstructions().size();
        traverse(ifelseContext.commands(1)); //generate else commands
        int elseIndex = instructionList.getInstructions().size();
        instructionList.getInstructions().set(skipElseLabel-1, new Instruction("JZERO", elseIndex-skipElseLabel+1));
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
            if (eqContext.value(0).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(eqContext.value(0).signedNum().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
            }
            else {
                String scopeOfVariable = findEnclosingScope(eqContext.value(0));
                int registerNumber = resolveMemory(eqContext.value(0).identifier().getText(), scopeOfVariable, eqContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));

            }
            if (eqContext.value(1).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(eqContext.value(1).signedNum().getText())));
            }
            else {
                String scopeOfVariable = findEnclosingScope(eqContext.value(1));
                int registerNumber = resolveMemory(eqContext.value(1).identifier().getText(), scopeOfVariable, eqContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            }

            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("JZERO", 2));
            instructionList.addInstruction(new Instruction("SET", 1));


        } else if (conditionContext instanceof GrammarParser.NEQContext neqContext) {
            if (neqContext.value(0).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(neqContext.value(0).signedNum().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
            }
            else {
                String scopeOfVariable = findEnclosingScope(neqContext.value(0));
                int registerNumber = resolveMemory(neqContext.value(0).identifier().getText(), scopeOfVariable, neqContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));

            }
            if (neqContext.value(1).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(neqContext.value(1).signedNum().getText())));
            }
            else {
                String scopeOfVariable = findEnclosingScope(neqContext.value(1));
                int registerNumber = resolveMemory(neqContext.value(1).identifier().getText(), scopeOfVariable, neqContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            }
            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("JZERO", 3));
            instructionList.addInstruction(new Instruction("SET", 0));
            instructionList.addInstruction(new Instruction("JZERO", 2));
            instructionList.addInstruction(new Instruction("SET", 1));
            
        }else if (conditionContext instanceof GrammarParser.GTContext gtContext) {
            if (gtContext.value(0).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(gtContext.value(0).signedNum().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
            }
            else {
                String scopeOfVariable = findEnclosingScope(gtContext.value(0));
                int registerNumber = resolveMemory(gtContext.value(0).identifier().getText(), scopeOfVariable, gtContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));

            }
            if (gtContext.value(1).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(gtContext.value(1).signedNum().getText())));
            }
            else {
                String scopeOfVariable = findEnclosingScope(gtContext.value(1));
                int registerNumber = resolveMemory(gtContext.value(1).identifier().getText(), scopeOfVariable, gtContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            }

            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("JNEG", 3));
            instructionList.addInstruction(new Instruction("SET", 1));
            instructionList.addInstruction(new Instruction("JPOS", 2));
            instructionList.addInstruction(new Instruction("SET", 0));

        }else if (conditionContext instanceof GrammarParser.LTContext ltContext) {
            if (ltContext.value(0).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(ltContext.value(0).signedNum().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
            }
            else {
                String scopeOfVariable = findEnclosingScope(ltContext.value(0));
                int registerNumber = resolveMemory(ltContext.value(0).identifier().getText(), scopeOfVariable, ltContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));

            }
            if (ltContext.value(1).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(ltContext.value(1).signedNum().getText())));
            }
            else {
                String scopeOfVariable = findEnclosingScope(ltContext.value(1));
                int registerNumber = resolveMemory(ltContext.value(1).identifier().getText(), scopeOfVariable, ltContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            }

            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("JPOS", 3));
            instructionList.addInstruction(new Instruction("SET", 1));
            instructionList.addInstruction(new Instruction("JPOS", 2));
            instructionList.addInstruction(new Instruction("SET", 0));

        }else if (conditionContext instanceof GrammarParser.GEQContext geqContext) {
            if (geqContext.value(0).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(geqContext.value(0).signedNum().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
            }
            else {
                String scopeOfVariable = findEnclosingScope(geqContext.value(0));
                int registerNumber = resolveMemory(geqContext.value(0).identifier().getText(), scopeOfVariable, geqContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));

            }
            if (geqContext.value(1).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(geqContext.value(1).signedNum().getText())));
            }
            else {
                String scopeOfVariable = findEnclosingScope(geqContext.value(1));
                int registerNumber = resolveMemory(geqContext.value(1).identifier().getText(), scopeOfVariable, geqContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            }

            instructionList.addInstruction(new Instruction("SUB", 1));
            instructionList.addInstruction(new Instruction("JZERO", 5)); //jump to set 0, 0 is already in acc just jump out of condition
            instructionList.addInstruction(new Instruction("JNEG", 3)); //jump to set 0
            instructionList.addInstruction(new Instruction("SET", 1));
            instructionList.addInstruction(new Instruction("JPOS", 2));
            instructionList.addInstruction(new Instruction("SET", 0));


        }else if (conditionContext instanceof GrammarParser.LEQContext leqContext) {
            if (leqContext.value(0).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(leqContext.value(0).signedNum().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
            }
            else {
                String scopeOfVariable = findEnclosingScope(leqContext.value(0));
                int registerNumber = resolveMemory(leqContext.value(0).identifier().getText(), scopeOfVariable, leqContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));

            }
            if (leqContext.value(1).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(leqContext.value(1).signedNum().getText())));
            }
            else {
                String scopeOfVariable = findEnclosingScope(leqContext.value(1));
                int registerNumber = resolveMemory(leqContext.value(1).identifier().getText(), scopeOfVariable, leqContext.value(1).identifier());
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
            if (value.signedNum() != null){
                int num = Integer.parseInt(value.signedNum().getText());
                //memory.getMemCell(assignContext.identifier(), scope).setValue(num);
                instructionList.addInstruction(new Instruction("SET", num));
            } else if (value.identifier() != null) { //variable
                String varName = value.identifier().getText();
                String scopeOfVariable = findEnclosingScope(valexprContext);
                int registerNumber = resolveMemory(varName, scopeOfVariable, value.identifier());
//                if (memory.getMemCell(value.identifier(), scopeOfVariable).getValue()!= null){
//                    memory.getMemCell(assignContext.identifier(), scope).setValue(memory.getMemCell(value.identifier(), scopeOfVariable).getValue());
//                }
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
            }
        } else if (assignContext.expression() instanceof GrammarParser.NEGATEContext negateContext) {
            if (negateContext.value().signedNum() != null){
                int num = Integer.parseInt(negateContext.value().signedNum().getText());
                memory.getMemCell(assignContext.identifier(), scope).setValue(-num);
                instructionList.addInstruction(new Instruction("SET", -num));

            } else if (negateContext.value().identifier() != null) {

            }


        } else if (assignContext.expression() instanceof GrammarParser.ADDContext addContext ) {


            if (addContext.value(0).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(addContext.value(0).signedNum().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));

            }
            else {
                String scopeOfVariable = findEnclosingScope(addContext.value(0));
                int registerNumber = resolveMemory(addContext.value(0).identifier().getText(), scopeOfVariable, addContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));

            }
            if (addContext.value(1).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(addContext.value(1).signedNum().getText())));

            }
            else {
                String scopeOfVariable = findEnclosingScope(addContext.value(1));
                int registerNumber = resolveMemory(addContext.value(1).identifier().getText(), scopeOfVariable, addContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));

            }
            instructionList.addInstruction(new Instruction("ADD", 1));

        } else if (assignContext.expression() instanceof GrammarParser.SUBContext subContext) {


            if (subContext.value(1).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(subContext.value(1).signedNum().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));

            }
            else {
                String scopeOfVariable = findEnclosingScope(subContext.value(1));
                int registerNumber = resolveMemory(subContext.value(1).identifier().getText(), scopeOfVariable, subContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));

            }
            if (subContext.value(0).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(subContext.value(0).signedNum().getText())));
            }
            else {
                String scopeOfVariable = findEnclosingScope(subContext.value(0));
                int registerNumber = resolveMemory(subContext.value(0).identifier().getText(), scopeOfVariable, subContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));

            }
            instructionList.addInstruction(new Instruction("SUB", 1));


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

            //If both values are numbers we can calculate result in compiler
            if (mulContext.value(0).signedNum() != null && mulContext.value(1).signedNum() != null){
                int divident = Integer.parseInt(mulContext.value(0).signedNum().getText());
                int divisor = Integer.parseInt(mulContext.value(1).signedNum().getText());
                instructionList.addInstruction(new Instruction("SET", divident*divisor));
            }
            //we multiply by power of 2 with positive number
            else if (mulContext.value(1).signedNum() != null && mulContext.value(0).signedNum() == null && isPowerOfTwo(Integer.parseInt(mulContext.value(1).signedNum().getText()))) {
                String scopeOfVariable = findEnclosingScope(mulContext.value(0));
                int registerNumber = resolveMemory(mulContext.value(0).identifier().getText(), scopeOfVariable, mulContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                int power = (int) (Math.log(Integer.parseInt(mulContext.value(1).signedNum().getText())) / Math.log(2));
                for (int i = 0; i < power; i++) {
                    instructionList.addInstruction(new Instruction("ADD", 0));
                }
            }
            else if (mulContext.value(0).signedNum() != null && mulContext.value(1).signedNum() == null && isPowerOfTwo(Integer.parseInt(mulContext.value(0).signedNum().getText()))) {
                String scopeOfVariable = findEnclosingScope(mulContext.value(1));
                int registerNumber = resolveMemory(mulContext.value(1).identifier().getText(), scopeOfVariable, mulContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                int power = (int) (Math.log(Integer.parseInt(mulContext.value(0).signedNum().getText())) / Math.log(2));
                for (int i = 0; i < power; i++) {
                    instructionList.addInstruction(new Instruction("ADD", 0));
                }
            }
            //we multiply by power of 2 with negative number, has to change sign of result
            else if (mulContext.value(1).signedNum() != null && mulContext.value(0).signedNum() == null && Integer.parseInt(mulContext.value(1).signedNum().getText()) < 0 && isPowerOfTwo(Math.abs(Integer.parseInt(mulContext.value(1).signedNum().getText())))) {
                String scopeOfVariable = findEnclosingScope(mulContext.value(0));
                int registerNumber = resolveMemory(mulContext.value(0).identifier().getText(), scopeOfVariable, mulContext.value(0).identifier());
                int divisor = Math.abs(Integer.parseInt(mulContext.value(1).signedNum().getText()));
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                int power = (int) (Math.log(divisor) / Math.log(2));
                for (int i = 0; i < power; i++) {
                    instructionList.addInstruction(new Instruction("ADD", 0));
                }
                //changing sign of result because in every case we have to change it
                instructionList.addInstruction(new Instruction("STORE", 3));
                instructionList.addInstruction(new Instruction("SET", 0));
                instructionList.addInstruction(new Instruction("SUB", 3));
            }
            else if (mulContext.value(0).signedNum() != null && mulContext.value(1).signedNum() == null && Integer.parseInt(mulContext.value(0).signedNum().getText()) < 0 && isPowerOfTwo(Math.abs(Integer.parseInt(mulContext.value(0).signedNum().getText())))) {
                String scopeOfVariable = findEnclosingScope(mulContext.value(1));
                int registerNumber = resolveMemory(mulContext.value(1).identifier().getText(), scopeOfVariable, mulContext.value(1).identifier());
                int divisor = Math.abs(Integer.parseInt(mulContext.value(0).signedNum().getText()));
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                int power = (int) (Math.log(divisor) / Math.log(2));
                for (int i = 0; i < power; i++) {
                    instructionList.addInstruction(new Instruction("ADD", 0));
                }
                //changing sign of result because in every case we have to change it
                instructionList.addInstruction(new Instruction("STORE", 3));
                instructionList.addInstruction(new Instruction("SET", 0));
                instructionList.addInstruction(new Instruction("SUB", 3));
            }

            else {
                //First part, storing multiplier and multiplicand to r1 and r2
                //TODO: OPTIMALIZATION --- while m1 and m2 in acc check for multiplying by 0 and 1 and jump to the end with correct result
                if (mulContext.value(0).signedNum() != null){
                    instructionList.addInstruction(new Instruction("SET", Integer.parseInt(mulContext.value(0).signedNum().getText())));
                    instructionList.addInstruction(new Instruction("STORE", 1));
                    //saving information about sign of number to register
                    if (Integer.parseInt(mulContext.value(0).signedNum().getText()) < 0){
                        instructionList.addInstruction(new Instruction("SET", -1));
                        instructionList.addInstruction(new Instruction("LOAD", 4));
                    }
                    else {
                        instructionList.addInstruction(new Instruction("LOAD", 3));
                        instructionList.addInstruction(new Instruction("STORE", 4));
                    }
                }
                else {
                    String scopeOfVariable = findEnclosingScope(mulContext.value(0));
                    int registerNumber = resolveMemory(mulContext.value(0).identifier().getText(), scopeOfVariable, mulContext.value(0).identifier());
                    instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                    instructionList.addInstruction(new Instruction("STORE", 1));
                    instructionList.addInstruction(new Instruction("JNEG", 4));
                    instructionList.addInstruction(new Instruction("LOAD", 3));
                    instructionList.addInstruction(new Instruction("STORE", 4));
                    instructionList.addInstruction(new Instruction("JUMP", 3));
                    instructionList.addInstruction(new Instruction("SET", -1));
                    instructionList.addInstruction(new Instruction("STORE", 4));

                }
                if (mulContext.value(1).signedNum() != null){
                    instructionList.addInstruction(new Instruction("SET", Integer.parseInt(mulContext.value(1).signedNum().getText())));
                    instructionList.addInstruction(new Instruction("STORE", 2));
                    //saving information about sign of number to register
                    if (Integer.parseInt(mulContext.value(1).signedNum().getText()) < 0){
                        instructionList.addInstruction(new Instruction("SET", -1));
                        instructionList.addInstruction(new Instruction("LOAD", 5));
                    }
                    else {
                        instructionList.addInstruction(new Instruction("LOAD", 3));
                        instructionList.addInstruction(new Instruction("STORE", 5));
                    }
                }
                else {
                    String scopeOfVariable = findEnclosingScope(mulContext.value(1));
                    int registerNumber = resolveMemory(mulContext.value(1).identifier().getText(), scopeOfVariable, mulContext.value(1).identifier());
                    instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                    instructionList.addInstruction(new Instruction("STORE", 2));
                    instructionList.addInstruction(new Instruction("JNEG", 4));
                    instructionList.addInstruction(new Instruction("LOAD", 3));
                    instructionList.addInstruction(new Instruction("STORE", 5));
                    instructionList.addInstruction(new Instruction("JUMP", 3));
                    instructionList.addInstruction(new Instruction("SET", -1));
                    instructionList.addInstruction(new Instruction("STORE", 5));

                }
                //If we know values of both m's we save result to the variable in compiler memory

                //setting result as 0
                instructionList.addInstruction(new Instruction("SET", 0));//setting result as 0
                instructionList.addInstruction(new Instruction("STORE", 3));

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

                //checking if we are multiplying by 0
                instructionList.addInstruction(new Instruction("LOAD", 1));
                instructionList.addInstruction(new Instruction("JZERO", 19));
                instructionList.addInstruction(new Instruction("LOAD", 2));
                instructionList.addInstruction(new Instruction("JZERO", 17));

                //Multiplying
                instructionList.addInstruction(new Instruction("LOAD", 1));
                instructionList.addInstruction(new Instruction("JZERO", 18));//exit number
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

                //Place for setting result to 0 if result
                instructionList.addInstruction(new Instruction("SET", 0));
                instructionList.addInstruction(new Instruction("STORE", 3));
                instructionList.addInstruction(new Instruction("JUMP", 7));


                //Checking if result should be with + or - and saving it to acc
                instructionList.addInstruction(new Instruction("LOAD", 6));
                instructionList.addInstruction(new Instruction("JZERO", 4)); //Jump to exit of mul, result is positive
                instructionList.addInstruction(new Instruction("SET", 0));
                instructionList.addInstruction(new Instruction("SUB", 3));
                instructionList.addInstruction(new Instruction("STORE", 3));
                instructionList.addInstruction(new Instruction("LOAD", 3));//exit from loop here, loading result to acc
            }
        }
        else if (assignContext.expression() instanceof GrammarParser.DIVContext divContext) {
            /*
            r0 - acc
            r1 - dividend
            r2 - divisor
            r3 - result
            r4 - =0 r1 is positive =-1 is negative
            r5 - =0 r2 is positive =-1 is negative
            r6 - if 0 result should be positive, else negative
            at the end result goes to acc - r0
             */
            //If both values are numbers we can calculate result in compiler
            if (divContext.value(0).signedNum() != null && divContext.value(1).signedNum() != null){
                int divident = Integer.parseInt(divContext.value(0).signedNum().getText());
                int divisor = Integer.parseInt(divContext.value(1).signedNum().getText());
                if(divisor == 0){
                    instructionList.addInstruction(new Instruction("SET", 0));
                }
                else {
                    instructionList.addInstruction(new Instruction("SET", divident / divisor));
                }
            }
            //using half operation when divisor is power of 2
            else if (divContext.value(1).signedNum() != null && divContext.value(0).signedNum() == null && isPowerOfTwo(Integer.parseInt(divContext.value(1).signedNum().getText()))) {
                String scopeOfVariable = findEnclosingScope(divContext.value(0));
                int registerNumber = resolveMemory(divContext.value(0).identifier().getText(), scopeOfVariable, divContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                int power = (int) (Math.log(Integer.parseInt(divContext.value(1).signedNum().getText())) / Math.log(2));
                for (int i = 0; i < power; i++) {
                    instructionList.addInstruction(new Instruction("HALF"));
                }
            } else if (divContext.value(1).signedNum() != null && divContext.value(0).signedNum() == null && Integer.parseInt(divContext.value(1).signedNum().getText()) < 0 && isPowerOfTwo(Math.abs(Integer.parseInt(divContext.value(1).signedNum().getText())))) {
                String scopeOfVariable = findEnclosingScope(divContext.value(0));
                int registerNumber = resolveMemory(divContext.value(0).identifier().getText(), scopeOfVariable, divContext.value(0).identifier());
                int divisor = Math.abs(Integer.parseInt(divContext.value(1).signedNum().getText()));
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                int power = (int) (Math.log(divisor) / Math.log(2));
                for (int i = 0; i < power; i++) {
                    instructionList.addInstruction(new Instruction("HALF"));
                }
                //changing sign of result because in every case we have to change it
                instructionList.addInstruction(new Instruction("STORE", 3));
                instructionList.addInstruction(new Instruction("SET", 0));
                instructionList.addInstruction(new Instruction("SUB", 3));
            }
            else{
                //First part, storing multiplier and multiplicand to r1 and r2
                if (divContext.value(0).signedNum() != null){
                    instructionList.addInstruction(new Instruction("SET", Integer.parseInt(divContext.value(0).signedNum().getText())));
                    instructionList.addInstruction(new Instruction("STORE", 1));
                    //saving information about sign of number to register
                    if (Integer.parseInt(divContext.value(0).signedNum().getText()) < 0){
                        instructionList.addInstruction(new Instruction("SET", -1));
                        instructionList.addInstruction(new Instruction("LOAD", 4));
                    }
                    else {
                        instructionList.addInstruction(new Instruction("LOAD", 3));
                        instructionList.addInstruction(new Instruction("STORE", 4));
                    }
                }
                else {
                    String scopeOfVariable = findEnclosingScope(divContext.value(0));
                    int registerNumber = resolveMemory(divContext.value(0).identifier().getText(), scopeOfVariable, divContext.value(0).identifier());
                    instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                    instructionList.addInstruction(new Instruction("STORE", 1));
                    instructionList.addInstruction(new Instruction("JNEG", 4));
                    instructionList.addInstruction(new Instruction("LOAD", 3));
                    instructionList.addInstruction(new Instruction("STORE", 4));
                    instructionList.addInstruction(new Instruction("JUMP", 3));
                    instructionList.addInstruction(new Instruction("SET", -1));
                    instructionList.addInstruction(new Instruction("STORE", 4));

                }
                if (divContext.value(1).signedNum() != null){
                    instructionList.addInstruction(new Instruction("SET", Integer.parseInt(divContext.value(1).signedNum().getText())));
                    instructionList.addInstruction(new Instruction("STORE", 2));
                    //saving information about sign of number to register
                    if (Integer.parseInt(divContext.value(1).signedNum().getText()) < 0){
                        instructionList.addInstruction(new Instruction("SET", -1));
                        instructionList.addInstruction(new Instruction("LOAD", 5));
                    }
                    else {
                        instructionList.addInstruction(new Instruction("LOAD", 3));
                        instructionList.addInstruction(new Instruction("STORE", 5));
                    }
                }
                else {
                    String scopeOfVariable = findEnclosingScope(divContext.value(1));
                    int registerNumber = resolveMemory(divContext.value(1).identifier().getText(), scopeOfVariable, divContext.value(1).identifier());
                    instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                    instructionList.addInstruction(new Instruction("STORE", 2));
                    instructionList.addInstruction(new Instruction("JNEG", 4));
                    instructionList.addInstruction(new Instruction("LOAD", 3));
                    instructionList.addInstruction(new Instruction("STORE", 5));
                    instructionList.addInstruction(new Instruction("JUMP", 3));
                    instructionList.addInstruction(new Instruction("SET", -1));
                    instructionList.addInstruction(new Instruction("STORE", 5));

                }
                //If we know values of both m's we save result to the variable in compiler memory

                //setting result as 0
                instructionList.addInstruction(new Instruction("SET", 0));
                instructionList.addInstruction(new Instruction("STORE", 3));


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

                //checking if we are multiplying by 0
                instructionList.addInstruction(new Instruction("LOAD", 1));
                instructionList.addInstruction(new Instruction("JZERO", 47));
                instructionList.addInstruction(new Instruction("LOAD", 2));
                instructionList.addInstruction(new Instruction("JZERO", 45));

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
                instructionList.addInstruction(new Instruction("JZERO", 26)); //JUMP OUT OF DIVISION
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

                //Place for setting result to 0 if result
                instructionList.addInstruction(new Instruction("SET", 0));
                instructionList.addInstruction(new Instruction("STORE", 3));
                instructionList.addInstruction(new Instruction("JUMP", 7));

                //Checking if result should be with + or - and saving it to acc
                instructionList.addInstruction(new Instruction("LOAD", 6));
                instructionList.addInstruction(new Instruction("JZERO", 4)); //Jump to exit of mul, result is positive
                instructionList.addInstruction(new Instruction("SET", 0));
                instructionList.addInstruction(new Instruction("SUB", 3));
                instructionList.addInstruction(new Instruction("STORE", 3));
                instructionList.addInstruction(new Instruction("LOAD", 3));//exit from loop here, loading result to acc
            }
        }
        else if (assignContext.expression() instanceof GrammarParser.MODContext modContext) {
             /*
            r0 - acc
            r1 - multiplier
            r2 - multiplicand
            r3 - result
            r4 - =0 r1 is positive =-1 is negative
            r5 - =0 r2 is positive =-1 is negative
            r6 - if 0 result should be positive, else negative
            at the end result goes to acc - r0
             */

            //setting result as 0
            instructionList.addInstruction(new Instruction("SET", 0));//setting result as 0
            instructionList.addInstruction(new Instruction("STORE", 3));

            //First part, storing multiplier and multiplicand to r1 and r2
            //TODO: OPTIMALIZATION --- while m1 and m2 in acc check for multiplying by 0 and 1 and jump to the end with correct result
            if (modContext.value(0).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(modContext.value(0).signedNum().getText())));
                instructionList.addInstruction(new Instruction("STORE", 1));
                //saving information about sign of number to register
                if (Integer.parseInt(modContext.value(0).signedNum().getText()) < 0){
                    instructionList.addInstruction(new Instruction("SET", -1));
                    instructionList.addInstruction(new Instruction("LOAD", 4));
                }
                else {
                    instructionList.addInstruction(new Instruction("LOAD", 3));
                    instructionList.addInstruction(new Instruction("STORE", 4));
                }
            }
            else {
                String scopeOfVariable = findEnclosingScope(modContext.value(0));
                int registerNumber = resolveMemory(modContext.value(0).identifier().getText(), scopeOfVariable, modContext.value(0).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 1));
                instructionList.addInstruction(new Instruction("JNEG", 4));
                instructionList.addInstruction(new Instruction("LOAD", 3));
                instructionList.addInstruction(new Instruction("STORE", 4));
                instructionList.addInstruction(new Instruction("JUMP", 3));
                instructionList.addInstruction(new Instruction("SET", -1));
                instructionList.addInstruction(new Instruction("STORE", 4));

            }
            if (modContext.value(1).signedNum() != null){
                instructionList.addInstruction(new Instruction("SET", Integer.parseInt(modContext.value(1).signedNum().getText())));
                instructionList.addInstruction(new Instruction("STORE", 2));
                //saving information about sign of number to register
                if (Integer.parseInt(modContext.value(1).signedNum().getText()) < 0){
                    instructionList.addInstruction(new Instruction("SET", -1));
                    instructionList.addInstruction(new Instruction("LOAD", 5));
                }
                else {
                    instructionList.addInstruction(new Instruction("LOAD", 3));
                    instructionList.addInstruction(new Instruction("STORE", 5));
                }
            }
            else {
                String scopeOfVariable = findEnclosingScope(modContext.value(1));
                int registerNumber = resolveMemory(modContext.value(1).identifier().getText(), scopeOfVariable, modContext.value(1).identifier());
                instructionList.addInstruction(new Instruction("LOAD", registerNumber));
                instructionList.addInstruction(new Instruction("STORE", 2));
                instructionList.addInstruction(new Instruction("JNEG", 4));
                instructionList.addInstruction(new Instruction("LOAD", 3));
                instructionList.addInstruction(new Instruction("STORE", 5));
                instructionList.addInstruction(new Instruction("JUMP", 3));
                instructionList.addInstruction(new Instruction("SET", -1));
                instructionList.addInstruction(new Instruction("STORE", 5));
            }

            //checking if we are multiplying by 0
            instructionList.addInstruction(new Instruction("LOAD", 1));
            instructionList.addInstruction(new Instruction("JZERO", 93));
            instructionList.addInstruction(new Instruction("LOAD", 2));
            instructionList.addInstruction(new Instruction("JZERO", 95));

            //Checking if result of mod should be + or - and saving it to r6
            instructionList.addInstruction(new Instruction("LOAD", 5));
            instructionList.addInstruction(new Instruction("JZERO", 4));
            instructionList.addInstruction(new Instruction("SET", 1));
            instructionList.addInstruction(new Instruction("STORE", 6));
            instructionList.addInstruction(new Instruction("JUMP", 2));
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

            //modulo

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
            instructionList.addInstruction(new Instruction("STORE", 3));
            instructionList.addInstruction(new Instruction("JUMP", 4));

            //Place for setting result to 0 if result
            instructionList.addInstruction(new Instruction("SET", 0));
            instructionList.addInstruction(new Instruction("STORE", 3));
            instructionList.addInstruction(new Instruction("JUMP", 7));

            //Checking and changing result to negative if neccesery
            //Checking if result should be with + or - and saving it to acc
            instructionList.addInstruction(new Instruction("LOAD", 6));
            instructionList.addInstruction(new Instruction("JZERO", 4)); //Jump to exit of mul, result is positive
            instructionList.addInstruction(new Instruction("SET", 0));
            instructionList.addInstruction(new Instruction("SUB", 3));
            instructionList.addInstruction(new Instruction("STORE", 3));
            instructionList.addInstruction(new Instruction("LOAD", 3));

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

    public int resolveMemory(String name, String scope, GrammarParser.IdentifierContext identifierContext){

        String iterator = isInForLoop(identifierContext);
        if (identifierContext instanceof GrammarParser.ARRAYWITHPIDUSAGEContext arrayContext){
            String scopeOfArray = findEnclosingScope(arrayContext);
            if (arrayContext.PIDENTIFIER(1).getText().equals(iterator) || memory.getMemCell(arrayContext.PIDENTIFIER(1).getText(),scopeOfArray).getValue() == null){
                //Handling array access with iterator, storing value in r11
                String baseAdressName = arrayContext.PIDENTIFIER(0).getText() + ":baseAddress";
                int baseAdress = memory.getMemCell(baseAdressName, scopeOfArray).getValue();
                int iteratorRegister = memory.resolveMemory(arrayContext.PIDENTIFIER(1).getText(),scopeOfArray);
                instructionList.addInstruction(new Instruction("SET",baseAdress));
                instructionList.addInstruction(new Instruction("ADD",iteratorRegister));
                instructionList.addInstruction(new Instruction("LOADI",0));
                instructionList.addInstruction(new Instruction("STORE",11));
                return 11;
            }
            return memory.resolveMemory(name, scope, identifierContext);
        }
        return memory.resolveMemory(name, scope, identifierContext);
    }

    private String isInForLoop(ParserRuleContext context){
        ParserRuleContext current = context;

        while (current != null){
            if (current instanceof GrammarParser.FORUPContext forupContext){
                return forupContext.PIDENTIFIER().getText();

            } if (current instanceof GrammarParser.FORDOWNTOContext fordowntoContext) {
                return fordowntoContext.PIDENTIFIER().getText();
            }
            current = current.getParent();
        }
        return "NO_ITERATOR";
    }
    private static boolean isPowerOfTwo(int n){
        return n > 0 && (n & (n - 1)) == 0;
    }
}