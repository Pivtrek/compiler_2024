// Generated from Grammar.g4 by ANTLR 4.13.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarParser#program_all}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram_all(GrammarParser.Program_allContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#procedures}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedures(GrammarParser.ProceduresContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(GrammarParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#commands}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommands(GrammarParser.CommandsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ASSIGN}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitASSIGN(GrammarParser.ASSIGNContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IFELSE}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIFELSE(GrammarParser.IFELSEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IF}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIF(GrammarParser.IFContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WHILE}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWHILE(GrammarParser.WHILEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code REPEATUNTIL}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitREPEATUNTIL(GrammarParser.REPEATUNTILContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FORUP}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFORUP(GrammarParser.FORUPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FORDOWNTO}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFORDOWNTO(GrammarParser.FORDOWNTOContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CALLPROC}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCALLPROC(GrammarParser.CALLPROCContext ctx);
	/**
	 * Visit a parse tree produced by the {@code READ}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitREAD(GrammarParser.READContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WRITE}
	 * labeled alternative in {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWRITE(GrammarParser.WRITEContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#proc_head}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_head(GrammarParser.Proc_headContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#proc_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProc_call(GrammarParser.Proc_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#declarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarations(GrammarParser.DeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#args_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs_decl(GrammarParser.Args_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(GrammarParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VALEXPR}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVALEXPR(GrammarParser.VALEXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ADD}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitADD(GrammarParser.ADDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SUB}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSUB(GrammarParser.SUBContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MUL}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMUL(GrammarParser.MULContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DIV}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDIV(GrammarParser.DIVContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MOD}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMOD(GrammarParser.MODContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EQ}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEQ(GrammarParser.EQContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NEQ}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNEQ(GrammarParser.NEQContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GT}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGT(GrammarParser.GTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LT}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLT(GrammarParser.LTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GEQ}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGEQ(GrammarParser.GEQContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LEQ}
	 * labeled alternative in {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLEQ(GrammarParser.LEQContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(GrammarParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(GrammarParser.IdentifierContext ctx);
}