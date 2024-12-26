package org.example.semantic;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.parser.GrammarBaseVisitor;
import org.example.parser.GrammarParser;

import java.util.ArrayList;

public class SemanticAnalysis extends GrammarBaseVisitor<Void> {
    private final SymbolTable symbolTable;
    private final ErrorColector errorColector;

    public SemanticAnalysis(SymbolTable symbolTable, ErrorColector errorColector) {
        this.symbolTable = symbolTable;
        this.errorColector = errorColector;
    }

    public void analyze(ParseTree root){
        visit(root);
    }
    @Override
    public Void visitINTUSAGE(GrammarParser.INTUSAGEContext ctx) {
        checkIdentifierUsage(ctx);
        return super.visitINTUSAGE(ctx);
    }

    @Override
    public Void visitARRAYWITHPIDUSAGE(GrammarParser.ARRAYWITHPIDUSAGEContext ctx) {
        checkArrayUsage(ctx);
        return super.visitARRAYWITHPIDUSAGE(ctx);
    }

    @Override
    public Void visitARRAYWITHNUMUSAGE(GrammarParser.ARRAYWITHNUMUSAGEContext ctx) {
        checkArrayUsage(ctx);
        return super.visitARRAYWITHNUMUSAGE(ctx);
    }

    private String findEnclosingScope(ParserRuleContext context){
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
    private String isInForLoop(ParserRuleContext context){
        ParserRuleContext current = context;

        while (current != null){
            if (current instanceof GrammarParser.FORUPContext || current instanceof GrammarParser.FORDOWNTOContext){
                return true;
            }
            current = current.getParent();
        }
        return false;
    }



    @Override
    public Void visitFORUP(GrammarParser.FORUPContext ctx) {

        String iterator = ctx.PIDENTIFIER().getText();
        GrammarParser.CommandsContext commandsContext = ctx.commands();

        for(GrammarParser.CommandContext command: commandsContext.command()){
            visit(command);
            //TODO: resolve problem with seeing iterator of loop

            if (command instanceof GrammarParser.ASSIGNContext assignContext){
                if (assignContext.identifier().getText().equals(iterator)){
                    //errorColector.reportError("Próba modyfikacji iteratora " + iterator, assignContext.identifier());
                }
            }
        }
        return null;
    }

    @Override
    public Void visitFORDOWNTO(GrammarParser.FORDOWNTOContext ctx) {

        String iterator = ctx.PIDENTIFIER().getText();
        GrammarParser.CommandsContext commandsContext = ctx.commands();

        for(GrammarParser.CommandContext command: commandsContext.command()){
            visit(command);
            System.out.println(command.getText());
            if (command instanceof GrammarParser.ASSIGNContext assignContext){
                //TODO: resolve problem with seeing iterator of loop
                if (assignContext.identifier().getText().equals(iterator)){
                    //errorColector.reportError("Próba modyfikacji iteratora " + iterator, assignContext.identifier().getSymbol().getLine());
                }
            }
        }
        return null;
    }

    private void checkIdentifierUsage(GrammarParser.INTUSAGEContext ctx){
        ArrayList<Symbol> parametersAndLocalVariables = new ArrayList<>();
        String procedure = findEnclosingScope(ctx);
        if (symbolTable.containsSymbol(procedure)){
            if (symbolTable.getSymbol(procedure).getParameters() != null){
                parametersAndLocalVariables.addAll(symbolTable.getSymbol(procedure).getParameters());
            }
            if (symbolTable.getSymbol(procedure).getLocalVariables() != null){
                parametersAndLocalVariables.addAll(symbolTable.getSymbol(procedure).getLocalVariables());
            }
        }
        if (parametersAndLocalVariables.contains(new Symbol(ctx.getText(), Symbol.SymbolType.ARRAY))){
            errorColector.reportError("Niewłaściwe użycie tablicy " + ctx.getText(), ctx.PIDENTIFIER().getSymbol().getLine());
        }

        if (!parametersAndLocalVariables.contains(new Symbol(ctx.getText(), Symbol.SymbolType.INT))){
            errorColector.reportError("Niezadeklarowana zmienna " + ctx.getText(), ctx.PIDENTIFIER().getSymbol().getLine());
        }
    }

    private void checkArrayUsage(Object ctx){
        ArrayList<Symbol> parametersAndLocalVariables = new ArrayList<>();
        String procedure = findEnclosingScope((ParserRuleContext) ctx);
        if (symbolTable.containsSymbol(procedure)){
            if (symbolTable.getSymbol(procedure).getParameters() != null){
                parametersAndLocalVariables.addAll(symbolTable.getSymbol(procedure).getParameters());
            }
            if (symbolTable.getSymbol(procedure).getLocalVariables() != null){
                parametersAndLocalVariables.addAll(symbolTable.getSymbol(procedure).getLocalVariables());
            }
        }

        if (ctx instanceof GrammarParser.ARRAYWITHNUMUSAGEContext context){
            if (parametersAndLocalVariables.contains(new Symbol(context.PIDENTIFIER().getText(), Symbol.SymbolType.INT))){
                errorColector.reportError("Niewłaściwe użycie zmiennej " + context.PIDENTIFIER().getText(), context.PIDENTIFIER().getSymbol().getLine());
            }
            if (!parametersAndLocalVariables.contains(new Symbol(context.PIDENTIFIER().getText(), Symbol.SymbolType.ARRAY))){
                errorColector.reportError("Niezadeklarowana tablica " + context.PIDENTIFIER().getText(), context.PIDENTIFIER().getSymbol().getLine());
            }
        }
        else if(ctx instanceof GrammarParser.ARRAYWITHPIDUSAGEContext context){
            if (parametersAndLocalVariables.contains(new Symbol(context.PIDENTIFIER(0).getText(), Symbol.SymbolType.INT))){
                errorColector.reportError("Niewłaściwe użycie zmiennej " + context.PIDENTIFIER(0).getText(), context.PIDENTIFIER(0).getSymbol().getLine());
            }
            if (!parametersAndLocalVariables.contains(new Symbol(context.PIDENTIFIER(0).getText(), Symbol.SymbolType.ARRAY))){
                errorColector.reportError("Niezadeklarowana tablica " + context.PIDENTIFIER(0).getText(), context.PIDENTIFIER(0).getSymbol().getLine());
            }
            if (parametersAndLocalVariables.contains(new Symbol(context.PIDENTIFIER(1).getText(), Symbol.SymbolType.ARRAY))){
                errorColector.reportError("Niewłaściwe użycie tablicy " + context.PIDENTIFIER(1).getText(), context.PIDENTIFIER(1).getSymbol().getLine());
            }
            if (!parametersAndLocalVariables.contains(new Symbol(context.PIDENTIFIER(1).getText(), Symbol.SymbolType.INT))){
                errorColector.reportError("Niezadeklarowana zmienna " + context.PIDENTIFIER(1).getText(), context.PIDENTIFIER(1).getSymbol().getLine());
            }
        }
    }
}
