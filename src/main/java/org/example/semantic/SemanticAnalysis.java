package org.example.semantic;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.example.parser.GrammarBaseVisitor;
import org.example.parser.GrammarParser;

import java.util.ArrayList;
import java.util.List;

//TODO: example 6,8
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
    public Void visitASSIGN(GrammarParser.ASSIGNContext ctx) {
        String procedure_name = findEnclosingScope(ctx);
        if (ctx.identifier() instanceof GrammarParser.INTUSAGEContext && symbolTable.getSymbol(procedure_name).getLocalVariables() != null){
            for (Symbol localVariable: symbolTable.getSymbol(procedure_name).getLocalVariables()){
                if (localVariable.getName().equals(ctx.identifier().getText())){
                    localVariable.setInitialized(true);
                }
            }
        }
        visit(ctx.identifier());

        if (ctx.expression() != null){
            visit(ctx.expression());
        }

        return null;
    }
    @Override
    public Void visitREAD(GrammarParser.READContext ctx) {
        String procedure_name = findEnclosingScope(ctx);
        if (symbolTable.containsSymbol(procedure_name)){
            if (symbolTable.getSymbol(procedure_name).getLocalVariables() != null){
                for (Symbol symbol : symbolTable.getSymbol(procedure_name).getLocalVariables()){
                    if (symbol.getName().equals(ctx.identifier().getText())){
                        symbol.setInitialized(true);
                    }
                }
            }
        }
        return null;
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
            if (current instanceof GrammarParser.FORUPContext forupContext){
                return forupContext.PIDENTIFIER().getText();

            } if (current instanceof GrammarParser.FORDOWNTOContext fordowntoContext) {
                return fordowntoContext.PIDENTIFIER().getText();
            }
            current = current.getParent();
        }
        return "NO_ITERATOR";
    }



    @Override
    public Void visitFORUP(GrammarParser.FORUPContext ctx) {

        String iterator = ctx.PIDENTIFIER().getText();
        GrammarParser.CommandsContext commandsContext = ctx.commands();
        String procName = findEnclosingScope(ctx);



        //Setting iterator as initialized
        if (symbolTable.getSymbol(procName).getLocalVariables() != null){
            for (Symbol localVariable: symbolTable.getSymbol(procName).getLocalVariables()){
                if (localVariable.getName().equals(iterator)){
                    localVariable.setInitialized(true);
                }
            }
        }

        for(GrammarParser.CommandContext command: commandsContext.command()){
            visit(command);
            if (command instanceof GrammarParser.ASSIGNContext assignContext){
                if (assignContext.identifier().getText().equals(iterator)){
                    errorColector.reportError("Próba modyfikacji iteratora " + iterator, command.getStart().getLine());
                }
            }
        }
        return null;
    }

    @Override
    public Void visitFORDOWNTO(GrammarParser.FORDOWNTOContext ctx) {

        String iterator = ctx.PIDENTIFIER().getText();
        GrammarParser.CommandsContext commandsContext = ctx.commands();
        String procName = findEnclosingScope(ctx);

        //Setting iterator as initialized
        if (symbolTable.getSymbol(procName).getLocalVariables() != null){
            for (Symbol localVariable: symbolTable.getSymbol(procName).getLocalVariables()){
                if (localVariable.getName().equals(iterator)){
                    localVariable.setInitialized(true);
                }
            }
        }

        for(GrammarParser.CommandContext command: commandsContext.command()){
            visit(command);
            if (command instanceof GrammarParser.ASSIGNContext assignContext){
                if (assignContext.identifier().getText().equals(iterator)){
                    errorColector.reportError("Próba modyfikacji iteratora " + iterator, command.getStart().getLine());
                }
            }
        }
        return null;
    }

    @Override
    public Void visitCALLPROC(GrammarParser.CALLPROCContext ctx) {
        String procCallName = ctx.proc_call().PIDENTIFIER().getText();
        String currentProcedureName = findEnclosingScope(ctx);

        if (symbolTable.getSymbol(currentProcedureName).getLocalVariables() != null){
            initializeVariablesByProcCall(procCallName, currentProcedureName, ctx.proc_call().args().PIDENTIFIER());

        }

        return super.visitCALLPROC(ctx);
    }

    private void initializeVariablesByProcCall(String procCallName, String scopeProcName, List<TerminalNode> arguments){

        List<Symbol> parametersOfCallProc = symbolTable.getSymbol(procCallName).getParameters();
        for (int i=0; i<parametersOfCallProc.size();i++){
            if (parametersOfCallProc.get(i).isInitialized()){
                for (Symbol localVariable: symbolTable.getSymbol(scopeProcName).getLocalVariables()) {
                    if (localVariable.getName().equals(arguments.get(i).getText())){
                        localVariable.setInitialized(true);
                    }
                }
            }
        }
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
            if (isInForLoop(ctx).equals(ctx.getText())){

            }
            else {
                errorColector.reportError("Niezadeklarowana zmienna " + ctx.getText(), ctx.PIDENTIFIER().getSymbol().getLine());
            }
        }
        if (parametersAndLocalVariables.contains(new Symbol(ctx.getText(), Symbol.SymbolType.INT)) && symbolTable.getSymbol(procedure).getLocalVariables() != null){
            for (Symbol symbol : parametersAndLocalVariables){
                if (ctx.getText().equals(symbol.getName()) && !symbol.isInitialized()){
                    errorColector.reportError("Niezainicjowana zmienna " + symbol.getName(), ctx.PIDENTIFIER().getSymbol().getLine());
                }
            }
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
                String iterator_name = isInForLoop((ParserRuleContext) ctx);
                if (!iterator_name.equals("NO_ITERATOR")){
                    Symbol iterator = new Symbol(iterator_name, Symbol.SymbolType.INT);
                    iterator.setInitialized(true);
                    symbolTable.getSymbol(procedure).addLocalVariable(iterator);
                }
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
            if (parametersAndLocalVariables.contains(new Symbol(context.PIDENTIFIER(1).getText(), Symbol.SymbolType.INT)) && symbolTable.getSymbol(procedure).getLocalVariables() != null){
                for (Symbol symbol : parametersAndLocalVariables){
                    if (context.PIDENTIFIER(1).getText().equals(symbol.getName()) && !symbol.isInitialized()){
                        errorColector.reportError("Niezainicjowana zmienna " + symbol.getName(), context.PIDENTIFIER(1).getSymbol().getLine());
                    }
                }
            }
        }
    }
}
