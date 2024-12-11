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

        //Name of procedure
        Symbol symbol = new Symbol(ctx.proc_head().PIDENTIFIER().getText(), Symbol.SymbolType.PROCEDURE_WITH_LOCAL_VARIABLES);
        //Arguments
        for (TerminalNode parameter: ctx.proc_head().args_decl().PIDENTIFIER()){
            symbol.addParameter(new Symbol(parameter.getText(), Symbol.SymbolType.INT));
        }
        //Local variables
        GrammarParser.DeclarationsContext declarationsContext = ctx.declarations();
        if (declarationsContext != null){
            GrammarParser.DeclarationsContext currentContext = declarationsContext;

            while (currentContext instanceof GrammarParser.MULTISINGLEDECLARATIONContext multiSingle){
                symbol.addLocalVariable(new Symbol(multiSingle.PIDENTIFIER().getText(), Symbol.SymbolType.INT));

                currentContext = multiSingle.declarations();

            }

            //Last declaration is single declaration
            if (currentContext instanceof GrammarParser.SINGLEDECLARATIONContext singleDeclaration){
                symbol.addLocalVariable(new Symbol(singleDeclaration.PIDENTIFIER().getText(), Symbol.SymbolType.INT));
            }
        }
        //adding procedure to table
        symbolTable.addSymbol(symbol);
    }

    @Override
    public void enterPROCEDUREWITHOUTDECLARATIONS(GrammarParser.PROCEDUREWITHOUTDECLARATIONSContext ctx) {

        //Name of procedure
        Symbol symbol = new Symbol(ctx.proc_head().PIDENTIFIER().getText(), Symbol.SymbolType.PROCEDURE_WITHOUT_LOCAL_VARIABLES);
        //Arguments
        for (TerminalNode parameter: ctx.proc_head().args_decl().PIDENTIFIER()){
            symbol.addParameter(new Symbol(parameter.getText(), Symbol.SymbolType.INT));
        }
        symbolTable.addSymbol(symbol);
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
