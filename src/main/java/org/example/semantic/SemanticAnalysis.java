package org.example.semantic;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.parser.GrammarBaseVisitor;
import org.example.parser.GrammarParser;

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

        return null;
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
    private boolean isInForLoop(ParserRuleContext context){
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
            if (command instanceof GrammarParser.ASSIGNContext assignContext){
                if (assignContext.identifier().PIDENTIFIER(0).getText().equals(iterator)){
                    errorColector.reportError("Próba modyfikacji iteratora " + iterator, assignContext.identifier().PIDENTIFIER(0).getSymbol().getLine());
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
                if (assignContext.identifier().PIDENTIFIER(0).getText().equals(iterator)){
                    errorColector.reportError("Próba modyfikacji iteratora " + iterator, assignContext.identifier().PIDENTIFIER(0).getSymbol().getLine());
                }
            }
        }
        return null;
    }
}
