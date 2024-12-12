package org.example.semantic;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.example.parser.GrammarBaseVisitor;
import org.example.parser.GrammarParser;

public class SymbolTableBuilderVisitor extends GrammarBaseVisitor<Void> {

    private SymbolTable symbolTable;

    public SymbolTableBuilderVisitor(SymbolTable symbolTable) {
        this.symbolTable = new SymbolTable();
    }

    @Override
    public Void visitPROCEDUREWITHOUTDECLARATIONS(GrammarParser.PROCEDUREWITHOUTDECLARATIONSContext ctx) {
        visit(ctx.proc_head());

        System.out.println(ctx.proc_head().PIDENTIFIER().getText());
        visit(ctx.proc_head().args_decl());



        return null;
    }
}
