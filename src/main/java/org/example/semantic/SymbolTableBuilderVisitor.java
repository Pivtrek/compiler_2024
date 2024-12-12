package org.example.semantic;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.example.parser.GrammarBaseVisitor;
import org.example.parser.GrammarParser;

public class SymbolTableBuilderVisitor extends GrammarBaseVisitor<Void> {

    private SymbolTable symbolTable;

    public SymbolTableBuilderVisitor(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public Void visitPROCEDUREWITHOUTDECLARATIONS(GrammarParser.PROCEDUREWITHOUTDECLARATIONSContext ctx) {
        visit(ctx.proc_head());

        //Procedure name
        Symbol procedure_without_variables = new Symbol(ctx.proc_head().PIDENTIFIER().getText(), Symbol.SymbolType.PROCEDURE_WITHOUT_LOCAL_VARIABLES);

        visit(ctx.proc_head().args_decl());

        GrammarParser.Args_declContext argsCtx = ctx.proc_head().args_decl();

        if (argsCtx != null) {
            processArguments(argsCtx, procedure_without_variables);
        }

        symbolTable.addSymbol(procedure_without_variables);

        return null;
    }
    //Process arguments recursively with recognition of arrays and integers
    private void processArguments(GrammarParser.Args_declContext ctx, Symbol procedure){
        if (ctx instanceof GrammarParser.ARGSMULTIDECLContext argument_context) {
            System.out.println("CIPA");
            procedure.addParameter(new Symbol(argument_context.PIDENTIFIER().getText(), Symbol.SymbolType.INT));
            processArguments(argument_context.args_decl(), procedure);

        } else if (ctx instanceof GrammarParser.ARGSDECLContext argument_context) {
            System.out.println("DUPA");
            procedure.addParameter(new Symbol(argument_context.PIDENTIFIER().getText(), Symbol.SymbolType.INT));

        } else if (ctx instanceof GrammarParser.ARGSMUTLIARRDECLContext array_context) {

            procedure.addParameter(new Symbol(array_context.PIDENTIFIER().getText(), Symbol.SymbolType.ARRAY));
            processArguments(array_context.args_decl(), procedure);

        } else if (ctx instanceof GrammarParser.ARGSARRDECLContext array_context) {
            System.out.println("CIPA2");
            procedure.addParameter(new Symbol(array_context.PIDENTIFIER().getText(), Symbol.SymbolType.ARRAY));
        }
    }
}
