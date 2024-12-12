package org.example.semantic;
import org.antlr.v4.runtime.tree.ParseTree;
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
        GrammarParser.Args_declContext argsCtx = ctx.proc_head().args_decl();
        if (argsCtx != null) {
            for (ParseTree child : argsCtx.children) {
                if (child instanceof GrammarParser.ARGSARRDECLContext arrayArg) {
                    symbol.addParameter(new Symbol(arrayArg.PIDENTIFIER().getText(), Symbol.SymbolType.ARRAY));
                } else if (!child.getText().equals(",")) {
                    //System.out.println(child.getText());
                    symbol.addParameter(new Symbol(child.getText(), Symbol.SymbolType.INT));
                }
            }
        }

        GrammarParser.DeclarationsContext declarationsContext = ctx.declarations();

        System.out.println(declarationsContext.getClass());



        //adding procedure to table
        symbolTable.addSymbol(symbol);
    }

    private void processDeclarations(GrammarParser.DeclarationsContext ctx) {
        // Obsłuż rekurencję w deklaracjach
        if (ctx instanceof GrammarParser.MULTISINGLEDECLARATIONContext) {
            GrammarParser.MULTISINGLEDECLARATIONContext multiDecl = (GrammarParser.MULTISINGLEDECLARATIONContext) ctx;

            // Przetwarzaj wcześniejsze deklaracje
            processDeclarations(multiDecl.declarations());

            // Pobierz aktualny identyfikator
            String variableName = multiDecl.PIDENTIFIER().getText();
            System.out.println("Zmienna: " + variableName);
            //symbolTable.addSymbol(variableName, new Symbol(variableName, "INT")); // Dodaj do tablicy symboli
        } else if (ctx instanceof GrammarParser.MULTIARRAYDECLARATIONContext) {
            GrammarParser.MULTIARRAYDECLARATIONContext multiArrayDecl = (GrammarParser.MULTIARRAYDECLARATIONContext) ctx;

            // Przetwarzaj wcześniejsze deklaracje
            processDeclarations(multiArrayDecl.declarations());

            // Pobierz aktualną tablicę
            String arrayName = multiArrayDecl.PIDENTIFIER().getText();
            String lowerBound = multiArrayDecl.NUM(0).getText();
            String upperBound = multiArrayDecl.NUM(1).getText();
            System.out.println("Tablica: " + arrayName + " [" + lowerBound + ":" + upperBound + "]");
            //symbolTable.addSymbol(arrayName, new Symbol(arrayName, "ARRAY")); // Dodaj do tablicy symboli
        } else if (ctx instanceof GrammarParser.SINGLEDECLARATIONContext) {
            GrammarParser.SINGLEDECLARATIONContext singleDecl = (GrammarParser.SINGLEDECLARATIONContext) ctx;

            // Pojedyncza zmienna
            String variableName = singleDecl.PIDENTIFIER().getText();
            System.out.println("Zmienna: " + variableName);
            //symbolTable.addSymbol(variableName, new Symbol(variableName, "INT")); // Dodaj do tablicy symboli
        } else if (ctx instanceof GrammarParser.ARRAYDECLARATIONContext) {
            GrammarParser.ARRAYDECLARATIONContext arrayDecl = (GrammarParser.ARRAYDECLARATIONContext) ctx;

            // Pojedyncza tablica
            String arrayName = arrayDecl.PIDENTIFIER().getText();
            String lowerBound = arrayDecl.NUM(0).getText();
            String upperBound = arrayDecl.NUM(1).getText();
            System.out.println("Tablica: " + arrayName + " [" + lowerBound + ":" + upperBound + "]");
            //symbolTable.addSymbol(arrayName, new Symbol(arrayName, "ARRAY")); // Dodaj do tablicy symboli
        }
    }

    @Override
    public void enterPROCEDUREWITHOUTDECLARATIONS(GrammarParser.PROCEDUREWITHOUTDECLARATIONSContext ctx) {

        //Name of procedure
        Symbol symbol = new Symbol(ctx.proc_head().PIDENTIFIER().getText(), Symbol.SymbolType.PROCEDURE_WITHOUT_LOCAL_VARIABLES);
        //Arguments
        /*
        for (TerminalNode parameter: ctx.proc_head().args_decl().PIDENTIFIER()){
            symbol.addParameter(new Symbol(parameter.getText(), Symbol.SymbolType.INT));
        }

         */
        symbolTable.addSymbol(symbol);
    }

    //DECLARATIONS


    @Override
    public void enterMULTISINGLEDECLARATION(GrammarParser.MULTISINGLEDECLARATIONContext ctx) {
        //System.out.println(ctx.declarations());
    }

    @Override
    public void enterSINGLEDECLARATION(GrammarParser.SINGLEDECLARATIONContext ctx) {
        //System.out.println(ctx.PIDENTIFIER());
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
