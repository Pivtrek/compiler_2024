package org.example.semantic;
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
        System.out.println(ctx.proc_head().getText());
    }

    @Override
    public void enterPROCEDUREWITHOUTDECLARATIONS(GrammarParser.PROCEDUREWITHOUTDECLARATIONSContext ctx) {
        super.enterPROCEDUREWITHOUTDECLARATIONS(ctx);
    }

    //DECLARATIONS


    @Override
    public void enterMULTISINGLEDECLARATION(GrammarParser.MULTISINGLEDECLARATIONContext ctx) {
        System.out.println("MULTI SINGLE DECLARATION PIDENTIFIER " + ctx.PIDENTIFIER().getText());
        System.out.println("MULTI SINGLE DECLARATION declarations " + ctx.declarations().getText());
    }

    @Override
    public void enterSINGLEDECLARATION(GrammarParser.SINGLEDECLARATIONContext ctx) {
        System.out.println("Single Declarations" + ctx.PIDENTIFIER().getText());

        //Symbol symbol = new Symbol(ctx.PIDENTIFIER().getText(), Symbol.SymbolType.INT);
        //symbolTable.addSymbol(symbol);
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
