// Generated from Grammar.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#program_all}.
	 * @param ctx the parse tree
	 */
	void enterProgram_all(GrammarParser.Program_allContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#program_all}.
	 * @param ctx the parse tree
	 */
	void exitProgram_all(GrammarParser.Program_allContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#procedures}.
	 * @param ctx the parse tree
	 */
	void enterProcedures(GrammarParser.ProceduresContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#procedures}.
	 * @param ctx the parse tree
	 */
	void exitProcedures(GrammarParser.ProceduresContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(GrammarParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(GrammarParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#commands}.
	 * @param ctx the parse tree
	 */
	void enterCommands(GrammarParser.CommandsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#commands}.
	 * @param ctx the parse tree
	 */
	void exitCommands(GrammarParser.CommandsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ASSIGN}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void enterASSIGN(GrammarParser.ASSIGNContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ASSIGN}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void exitASSIGN(GrammarParser.ASSIGNContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IFELSE}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void enterIFELSE(GrammarParser.IFELSEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IFELSE}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void exitIFELSE(GrammarParser.IFELSEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IF}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void enterIF(GrammarParser.IFContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IF}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void exitIF(GrammarParser.IFContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WHILE}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void enterWHILE(GrammarParser.WHILEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WHILE}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void exitWHILE(GrammarParser.WHILEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code REPEATUNTIL}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void enterREPEATUNTIL(GrammarParser.REPEATUNTILContext ctx);
	/**
	 * Exit a parse tree produced by the {@code REPEATUNTIL}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void exitREPEATUNTIL(GrammarParser.REPEATUNTILContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FORUP}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void enterFORUP(GrammarParser.FORUPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FORUP}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void exitFORUP(GrammarParser.FORUPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FORDOWNTO}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void enterFORDOWNTO(GrammarParser.FORDOWNTOContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FORDOWNTO}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void exitFORDOWNTO(GrammarParser.FORDOWNTOContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CALLPROC}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCALLPROC(GrammarParser.CALLPROCContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CALLPROC}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCALLPROC(GrammarParser.CALLPROCContext ctx);
	/**
	 * Enter a parse tree produced by the {@code READ}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void enterREAD(GrammarParser.READContext ctx);
	/**
	 * Exit a parse tree produced by the {@code READ}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void exitREAD(GrammarParser.READContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WRITE}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void enterWRITE(GrammarParser.WRITEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WRITE}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void exitWRITE(GrammarParser.WRITEContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#proc_head}.
	 * @param ctx the parse tree
	 */
	void enterProc_head(GrammarParser.Proc_headContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#proc_head}.
	 * @param ctx the parse tree
	 */
	void exitProc_head(GrammarParser.Proc_headContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#proc_call}.
	 * @param ctx the parse tree
	 */
	void enterProc_call(GrammarParser.Proc_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#proc_call}.
	 * @param ctx the parse tree
	 */
	void exitProc_call(GrammarParser.Proc_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#declarations}.
	 * @param ctx the parse tree
	 */
	void enterDeclarations(GrammarParser.DeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#declarations}.
	 * @param ctx the parse tree
	 */
	void exitDeclarations(GrammarParser.DeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#args_decl}.
	 * @param ctx the parse tree
	 */
	void enterArgs_decl(GrammarParser.Args_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#args_decl}.
	 * @param ctx the parse tree
	 */
	void exitArgs_decl(GrammarParser.Args_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(GrammarParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(GrammarParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VALEXPR}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVALEXPR(GrammarParser.VALEXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VALEXPR}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVALEXPR(GrammarParser.VALEXPRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ADD}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterADD(GrammarParser.ADDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ADD}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitADD(GrammarParser.ADDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SUB}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSUB(GrammarParser.SUBContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SUB}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSUB(GrammarParser.SUBContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MUL}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMUL(GrammarParser.MULContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MUL}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMUL(GrammarParser.MULContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DIV}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDIV(GrammarParser.DIVContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DIV}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDIV(GrammarParser.DIVContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MOD}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMOD(GrammarParser.MODContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MOD}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMOD(GrammarParser.MODContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EQ}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterEQ(GrammarParser.EQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EQ}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitEQ(GrammarParser.EQContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NEQ}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterNEQ(GrammarParser.NEQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NEQ}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitNEQ(GrammarParser.NEQContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GT}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterGT(GrammarParser.GTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GT}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitGT(GrammarParser.GTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LT}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterLT(GrammarParser.LTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LT}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitLT(GrammarParser.LTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GEQ}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterGEQ(GrammarParser.GEQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GEQ}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitGEQ(GrammarParser.GEQContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LEQ}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterLEQ(GrammarParser.LEQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LEQ}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitLEQ(GrammarParser.LEQContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(GrammarParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(GrammarParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(GrammarParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(GrammarParser.IdentifierContext ctx);
}