package org.example.semantic;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.example.parser.GrammarBaseVisitor;
import org.example.parser.GrammarParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class SymbolTableBuilderVisitor extends GrammarBaseVisitor<Void> {

    private SymbolTable symbolTable;
    private ErrorColector errorColector;

    public SymbolTableBuilderVisitor(SymbolTable symbolTable, ErrorColector errorColector) {
        this.symbolTable = symbolTable;
        this.errorColector = errorColector;
    }

    @Override
    public Void visitPROCEDUREWITHOUTDECLARATIONS(GrammarParser.PROCEDUREWITHOUTDECLARATIONSContext ctx) {
        visit(ctx.proc_head());
        //Procedure name
        String procedure_name = ctx.proc_head().PIDENTIFIER().getText();
        Symbol procedure_without_variables = new Symbol(procedure_name, Symbol.SymbolType.PROCEDURE_WITHOUT_LOCAL_VARIABLES);

        visit(ctx.proc_head().args_decl());

        GrammarParser.Args_declContext argsCtx = ctx.proc_head().args_decl();

        if (argsCtx != null) {
            processArguments(argsCtx, procedure_without_variables);
        }

        //Not declared procedure error handling
        visit(ctx.commands());
        GrammarParser.CommandsContext commandsContext = ctx.commands();
        checkForUndefinedProcedureUsage(commandsContext, symbolTable, procedure_without_variables);

        //reversing parameters to be in the right order
        Collections.reverse(procedure_without_variables.getParameters());

        checkForIdentifierUsage(commandsContext, procedure_without_variables);

        symbolTable.addSymbol(procedure_name,procedure_without_variables);

        return null;
    }

    @Override
    public Void visitPROCEDUREWITHDECLARATIONS(GrammarParser.PROCEDUREWITHDECLARATIONSContext ctx) {
        visit(ctx.proc_head());
        //Procedure name
        String procedure_name = ctx.proc_head().PIDENTIFIER().getText();
        Symbol procedure_with_variables = new Symbol(procedure_name, Symbol.SymbolType.PROCEDURE_WITH_LOCAL_VARIABLES);

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

        //Not declared procedure error handling
        visit(ctx.commands());
        GrammarParser.CommandsContext commandsContext = ctx.commands();
        checkForUndefinedProcedureUsage(commandsContext, symbolTable, procedure_with_variables);

        //reversing parameters to be in the right order
        Collections.reverse(procedure_with_variables.getParameters());

        //Check for proper variables usage
        checkForIdentifierUsage(commandsContext, procedure_with_variables);

        symbolTable.addSymbol(procedure_name, procedure_with_variables);

        return null;
    }

    @Override
    public Void visitMAINDECLARATIONS(GrammarParser.MAINDECLARATIONSContext ctx) {
        Symbol main_with_declarations = new Symbol("PROGRAM_IS_DECLARATIONS", Symbol.SymbolType.MAIN_WITH_LOCAL_VARIABLES);

        visit(ctx.declarations());

        GrammarParser.DeclarationsContext declCtx = ctx.declarations();

        if (declCtx != null){
            processDeclarations(declCtx, main_with_declarations);
        }


        //Not declared procedure error handling
        visit(ctx.commands());
        GrammarParser.CommandsContext commandsContext = ctx.commands();
        //TODO FIX MAINDECLARATIONS
        checkForUndefinedProcedureUsage(commandsContext, symbolTable, main_with_declarations);
        checkForIdentifierUsage(commandsContext, main_with_declarations);
        symbolTable.addSymbol("PROGRAM_IS_DECLARATIONS",main_with_declarations);

        return null;
    }

    @Override
    public Void visitMAINWITHOUTDECLARATIONS(GrammarParser.MAINWITHOUTDECLARATIONSContext ctx) {
        Symbol main_without_declaratations = new Symbol("PROGRAM_IS", Symbol.SymbolType.MAIN_WITHOUT_LOCAL_VARIABLES);

        //Not declared procedure error handling
        visit(ctx.commands());
        GrammarParser.CommandsContext commandsContext = ctx.commands();
        checkForUndefinedProcedureUsage(commandsContext, symbolTable, main_without_declaratations);
        checkForIdentifierUsage(commandsContext, main_without_declaratations);
        symbolTable.addSymbol("PROGRAM_IS",main_without_declaratations);

        return null;
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

            //Error handling of using same name of declaration as parameter
            if (procedure.getType().equals(Symbol.SymbolType.PROCEDURE_WITH_LOCAL_VARIABLES)){
                for (Symbol parameter: procedure.getParameters()){
                    if (parameter.getName().equals(argument_context.PIDENTIFIER().getText())){
                        errorColector.reportError("Powtórne użycie identyfikatora " + argument_context.PIDENTIFIER().getText(), argument_context.PIDENTIFIER().getSymbol().getLine());
                    }
                }
            }
            //Done error handling

            procedure.addLocalVariable(new Symbol(argument_context.PIDENTIFIER().getText(), Symbol.SymbolType.INT));
            processDeclarations(argument_context.declarations(), procedure);
        } else if (ctx instanceof GrammarParser.SINGLEDECLARATIONContext argument_context) {

            //Error handling of using same name of declaration as parameter
            if (procedure.getType() == Symbol.SymbolType.PROCEDURE_WITH_LOCAL_VARIABLES){
                for (Symbol parameter: procedure.getLocalVariables()){
                    if (parameter.getName().equals(argument_context.PIDENTIFIER().getText())){
                        errorColector.reportError("Powtórne użycie identyfikatora " + argument_context.PIDENTIFIER().getText(), argument_context.PIDENTIFIER().getSymbol().getLine());
                    }
                }
            }
            //Done error handling

            procedure.addLocalVariable(new Symbol(argument_context.PIDENTIFIER().getText(), Symbol.SymbolType.INT));
        } else if (ctx instanceof GrammarParser.MULTIARRAYDECLARATIONContext array_context) {
            Symbol array  = new Symbol(array_context.PIDENTIFIER().getText(), Symbol.SymbolType.ARRAY);

            //Error handling of declaring wrong range of array d[10:1] for example
            int lower_bound = Integer.parseInt(array_context.NUM(0).toString());
            int upper_bound = Integer.parseInt(array_context.NUM(1).toString());
            if (lower_bound >= upper_bound){ //Error handling of declaring wrong range of array d[10:1] for example
                errorColector.reportError("Niepoprawna deklaracja zasięgu tablicy " + array_context.PIDENTIFIER().getText(), array_context.PIDENTIFIER().getSymbol().getLine());
            }
            //Done Error handling

            //Error handling of using same name of declaration as parameter
            if (procedure.getType().equals(Symbol.SymbolType.PROCEDURE_WITH_LOCAL_VARIABLES)){
                for (Symbol parameter: procedure.getParameters()){
                    if (parameter.getName().equals(array_context.PIDENTIFIER().getText())){
                        errorColector.reportError("Powtórne użycie identyfikatora " + (array_context.PIDENTIFIER().getText()) , array_context.PIDENTIFIER().getSymbol().getLine());
                    }
                }
            }
            //Done Error handling

            array.setArrayBounds(lower_bound, upper_bound);
            procedure.addLocalVariable(array);
            processDeclarations(array_context.declarations(), procedure);
        } else if (ctx instanceof GrammarParser.ARRAYDECLARATIONContext array_context) {
            Symbol array  = new Symbol(array_context.PIDENTIFIER().getText(), Symbol.SymbolType.ARRAY);

            //Error handling of declaring wrong range of array d[10:1] for example
            int lower_bound = Integer.parseInt(array_context.NUM(0).toString());
            int upper_bound = Integer.parseInt(array_context.NUM(1).toString());
            if (lower_bound >= upper_bound){ //Error handling of declaring wrong range of array d[10:1] for example
                errorColector.reportError("Niepoprawna deklaracja zasięgu tablicy " + array_context.PIDENTIFIER().getText(), array_context.PIDENTIFIER().getSymbol().getLine());
            }
            //Done Error handling

            //Error handling of using same name of declaration as parameter
            if (procedure.getType().equals(Symbol.SymbolType.PROCEDURE_WITH_LOCAL_VARIABLES)){
                for (Symbol parameter: procedure.getParameters()){
                    if (parameter.getName().equals(array_context.PIDENTIFIER().getText())){
                        errorColector.reportError("Powtórne użycie identyfikatora " + (array_context.PIDENTIFIER().getText()) , array_context.PIDENTIFIER().getSymbol().getLine());
                    }
                }
            }
            //Done Error handling

            array.setArrayBounds(lower_bound, upper_bound);
            procedure.addLocalVariable(array);
        }
    }

    private void checkForUndefinedProcedureUsage(GrammarParser.CommandsContext commandsContext, SymbolTable symbolTable, Symbol procedure){

        ArrayList<Symbol> localVariablesAndParameters = new ArrayList<>();
        if (procedure.getParameters() != null){
            localVariablesAndParameters.addAll(procedure.getParameters());
        }
        if (procedure.getLocalVariables() != null){
            localVariablesAndParameters.addAll(procedure.getLocalVariables());
        }

        for (int i=0; i<commandsContext.command().size();i++){
            //CALPROC
            if (commandsContext.command(i) instanceof GrammarParser.CALLPROCContext){
                //Check if procedure is defined before calling
                String procedure_name = (((GrammarParser.CALLPROCContext) commandsContext.command(i)).proc_call().PIDENTIFIER()).toString();
                if (!(symbolTable.containsSymbol(procedure_name))){
                    errorColector.reportError("Użycie niezdefiniowanej procedury " + procedure_name, ((GrammarParser.CALLPROCContext) commandsContext.command(i)).proc_call().PIDENTIFIER().getSymbol().getLine());
                }
                //Check if arguments in procedure are propper type
                for (int j=0; j<((GrammarParser.CALLPROCContext) commandsContext.command(i)).proc_call().args().PIDENTIFIER().size();j++){
                    String argumet_name = String.valueOf(((GrammarParser.CALLPROCContext) commandsContext.command(i)).proc_call().args().PIDENTIFIER(j));
                    for(Symbol local_variable: localVariablesAndParameters){
                        if(local_variable.getName().equals(argumet_name)){
                            if (local_variable.getType() != symbolTable.getSymbol(procedure_name).getParameters().get(j).getType()){
                                errorColector.reportError("Niewłaściwy parametr procedury " + procedure_name, ((GrammarParser.CALLPROCContext) commandsContext.command(i)).proc_call().PIDENTIFIER().getSymbol().getLine());
                            }
                        }
                    }
                }
            }
            //IF
            if (commandsContext.command(i) instanceof GrammarParser.IFContext ifContext){
                checkForUndefinedProcedureUsage(ifContext.commands(), symbolTable, procedure);
            }
            //IF ELSE
            if (commandsContext.command(i) instanceof GrammarParser.IFELSEContext ifelseContext){
                checkForUndefinedProcedureUsage(ifelseContext.commands(0), symbolTable, procedure);
                checkForUndefinedProcedureUsage(ifelseContext.commands(1), symbolTable, procedure);
            }
            //WHILE
            if (commandsContext.command(i) instanceof GrammarParser.WHILEContext whileContext){
                checkForUndefinedProcedureUsage(whileContext.commands(),symbolTable, procedure);
            }
            //REPEAT UNTIL
            if (commandsContext.command(i) instanceof GrammarParser.REPEATUNTILContext repeatuntilContext){
                checkForUndefinedProcedureUsage(repeatuntilContext.commands(), symbolTable, procedure);
            }
        }
    }
    private void checkForIdentifierUsage(GrammarParser.CommandsContext commandsContext, Symbol procedure){

        ArrayList<Symbol> localVariablesAndParameters = new ArrayList<>();
        if (procedure.getParameters() != null){
            localVariablesAndParameters.addAll(procedure.getParameters());
        }
        if (procedure.getLocalVariables() != null){
            localVariablesAndParameters.addAll(procedure.getLocalVariables());
        }


        for (int i=0;i<commandsContext.command().size();i++){
            if (commandsContext.command(i) instanceof GrammarParser.ASSIGNContext assignContext){
                //ARRAY USAGE
                if (assignContext.identifier().PIDENTIFIER().size() == 1){
                    for (Symbol parameter : localVariablesAndParameters){
                        if (Objects.equals(parameter.getName(), assignContext.identifier().PIDENTIFIER(0).toString()) && (!(parameter.getType().equals(Symbol.SymbolType.INT)))){
                            errorColector.reportError("Niewłaściwe użycie tablicy", assignContext.identifier().PIDENTIFIER(0).getSymbol().getLine());
                        }

                    }
                }
                //INT USAGE
                else if (assignContext.identifier().PIDENTIFIER().size() == 2){
                    for (Symbol parameter : localVariablesAndParameters){
                        //int usage as array
                        if (Objects.equals(parameter.getName(), assignContext.identifier().PIDENTIFIER(0).toString()) && (!(parameter.getType().equals(Symbol.SymbolType.ARRAY)))){
                            errorColector.reportError("Niewłaściwe użycie zmiennej", assignContext.identifier().PIDENTIFIER(0).getSymbol().getLine());
                        }
                        //array usage inside array
                        else if (Objects.equals(parameter.getName(), assignContext.identifier().PIDENTIFIER(1).toString()) && (!(parameter.getType().equals(Symbol.SymbolType.INT)))){
                            errorColector.reportError("Niewłaściwe użycie tablicy", assignContext.identifier().PIDENTIFIER(1).getSymbol().getLine());
                        }
                    }
                }
            } else if (commandsContext.command(i) instanceof GrammarParser.WRITEContext writeContext) {
                if (writeContext.value().identifier().PIDENTIFIER().size() == 1){
                    for (Symbol parameter : localVariablesAndParameters){
                        if (Objects.equals(parameter.getName(), writeContext.value().identifier().PIDENTIFIER(0).toString()) && (!(parameter.getType().equals(Symbol.SymbolType.INT)))){
                            errorColector.reportError("Niewłaściwe użycie tablicy", writeContext.value().identifier().PIDENTIFIER(0).getSymbol().getLine());
                        }
                    }
                }
                else if (writeContext.value().identifier().PIDENTIFIER().size() == 2){
                    for (Symbol parameter : localVariablesAndParameters){
                        //int usage as array
                        if (Objects.equals(parameter.getName(), writeContext.value().identifier().PIDENTIFIER(0).toString()) && (!(parameter.getType().equals(Symbol.SymbolType.ARRAY)))){
                            errorColector.reportError("Niewłaściwe użycie zmiennej", writeContext.value().identifier().PIDENTIFIER(0).getSymbol().getLine());
                        }
                        //array usage inside array
                        else if (Objects.equals(parameter.getName(), writeContext.value().identifier().PIDENTIFIER(1).toString()) && (!(parameter.getType().equals(Symbol.SymbolType.INT)))){
                            errorColector.reportError("Niewłaściwe użycie tablicy", writeContext.value().identifier().PIDENTIFIER(1).getSymbol().getLine());
                        }
                    }
                }
            }
        }
    }

    @Override
    public Void visitASSIGN(GrammarParser.ASSIGNContext ctx) {

        System.out.println(ctx.getText());

        return null;
    }

    private String findEnclosingScope(ParserRuleContext context){


        return "UKNOWN SCOPE";
    }

    //TODO: its has to be done diffriently. Lets use visit assign function to get every assign
    //TODO: and create function that can get procedure name, so we can procced all assignes
    //TODO: with created symbol table, HINT!: case where we are entering loop its also an iterator
    //TODO: as extra variable so we can already check if its changing or not and also, we can map variables
    //TODO: so we know if we are assigning them with some value before using and all semantic analysis will be ready then!
}
