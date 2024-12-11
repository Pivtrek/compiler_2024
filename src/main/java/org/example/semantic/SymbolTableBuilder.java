package org.example.semantic;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.example.parser.GrammarBaseListener;
import org.example.parser.GrammarParser;

public class SymbolTableBuilder extends GrammarBaseListener {

    private SymbolTable symbolTable;

    public SymbolTableBuilder(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    //PROCEDURES
    @Override
    public void enterPROCEDUREWITHDECLARATIONS(GrammarParser.PROCEDUREWITHDECLARATIONSContext ctx) {
        Symbol symbol = new Symbol(ctx.proc_head().PIDENTIFIER().getText(), Symbol.SymbolType.PROCEDURE);
        for (TerminalNode parameter: ctx.proc_head().args_decl().PIDENTIFIER()){
            symbol.addParameter(new Symbol(parameter.getText(), Symbol.SymbolType.INT));
        }
        //for (TerminalNode local_variable: ctx.declarations()){

        //}
        symbolTable.addSymbol(symbol);
        //TODO: local_variables obsługa!!!!
        System.out.println(ctx.declarations().getText().split(","));
    }

    @Override
    public void enterPROCEDUREWITHOUTDECLARATIONS(GrammarParser.PROCEDUREWITHOUTDECLARATIONSContext ctx) {
        //System.out.println();
    }

    //DECLARATIONS


    @Override
    public void enterMULTISINGLEDECLARATION(GrammarParser.MULTISINGLEDECLARATIONContext ctx) {
    }

    @Override
    public void enterSINGLEDECLARATION(GrammarParser.SINGLEDECLARATIONContext ctx) {

    }

    @Override
    public void enterMULTIARRAYDECLARATION(GrammarParser.MULTIARRAYDECLARATIONContext ctx) {
        super.enterMULTIARRAYDECLARATION(ctx);
    }

    @Override
    public void enterARRAYDECLARATION(GrammarParser.ARRAYDECLARATIONContext ctx) {
        super.enterARRAYDECLARATION(ctx);
    }

}
