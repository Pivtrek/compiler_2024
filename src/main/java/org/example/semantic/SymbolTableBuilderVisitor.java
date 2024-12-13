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

    @Override
    public Void visitPROCEDUREWITHDECLARATIONS(GrammarParser.PROCEDUREWITHDECLARATIONSContext ctx) {
        visit(ctx.proc_head());
        //Procedure name
        Symbol procedure_with_variables = new Symbol(ctx.proc_head().PIDENTIFIER().getText(), Symbol.SymbolType.PROCEDURE_WITH_LOCAL_VARIABLES);

        visit(ctx.proc_head().args_decl());

        //Process arguments
        GrammarParser.Args_declContext argsCtx = ctx.proc_head().args_decl();

        if (argsCtx != null) {
            processArguments(argsCtx, procedure_with_variables);
        }

        //Process local variables

        visit(ctx.declarations());

        GrammarParser.DeclarationsContext declCtx = ctx.declarations();

        if (declCtx != null){
            processDeclarations(declCtx, procedure_with_variables);
        }

        symbolTable.addSymbol(procedure_with_variables);

        return null;
    }

    @Override
    public Void visitMAINDECLARATIONS(GrammarParser.MAINDECLARATIONSContext ctx) {
        return super.visitMAINDECLARATIONS(ctx);
    }

    //Process arguments recursively with recognition of arrays and integers
    private void processArguments(GrammarParser.Args_declContext ctx, Symbol procedure){
        if (ctx instanceof GrammarParser.ARGSMULTIDECLContext argument_context) {
            procedure.addParameter(new Symbol(argument_context.PIDENTIFIER().getText(), Symbol.SymbolType.INT));
            processArguments(argument_context.args_decl(), procedure);
        } else if (ctx instanceof GrammarParser.ARGSDECLContext argument_context) {
            procedure.addParameter(new Symbol(argument_context.PIDENTIFIER().getText(), Symbol.SymbolType.INT));
        } else if (ctx instanceof GrammarParser.ARGSMUTLIARRDECLContext array_context) {
            procedure.addParameter(new Symbol(array_context.PIDENTIFIER().getText(), Symbol.SymbolType.ARRAY));
            processArguments(array_context.args_decl(), procedure);
        } else if (ctx instanceof GrammarParser.ARGSARRDECLContext array_context) {
            procedure.addParameter(new Symbol(array_context.PIDENTIFIER().getText(), Symbol.SymbolType.ARRAY));
        }
    }

    private void processDeclarations(GrammarParser.DeclarationsContext ctx, Symbol procedure){
        if (ctx instanceof GrammarParser.MULTISINGLEDECLARATIONContext argument_context){
            procedure.addLocalVariable(new Symbol(argument_context.PIDENTIFIER().getText(), Symbol.SymbolType.INT));
            processDeclarations(argument_context.declarations(), procedure);
        } else if (ctx instanceof GrammarParser.SINGLEDECLARATIONContext argument_context) {
            procedure.addLocalVariable(new Symbol(argument_context.PIDENTIFIER().getText(), Symbol.SymbolType.INT));
        } else if (ctx instanceof GrammarParser.MULTIARRAYDECLARATIONContext array_context) {
            Symbol array  = new Symbol(array_context.PIDENTIFIER().getText(), Symbol.SymbolType.ARRAY);
            int lower_bound = Integer.parseInt(array_context.NUM(0).toString());
            int upper_bound = Integer.parseInt(array_context.NUM(1).toString());
            array.setArrayBounds(lower_bound, upper_bound);
            procedure.addLocalVariable(array);
            processDeclarations(array_context.declarations(), procedure);
        } else if (ctx instanceof GrammarParser.ARRAYDECLARATIONContext array_context) {
            Symbol array  = new Symbol(array_context.PIDENTIFIER().getText(), Symbol.SymbolType.ARRAY);
            int lower_bound = Integer.parseInt(array_context.NUM(0).toString());
            int upper_bound = Integer.parseInt(array_context.NUM(1).toString());
            array.setArrayBounds(lower_bound, upper_bound);
            procedure.addLocalVariable(array);
        }
    }

}
