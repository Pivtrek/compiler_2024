// Generated from org\example\parser\Grammar.g4 by ANTLR 4.13.0
package org.example.parser;

    package org.example.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class GrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		PIDENTIFIER=39, NUM=40, NOTEQUAL=41, LHBRACK=42, RHBRACK=43, WS=44, COMMENT=45;
	public static final int
		RULE_program_all = 0, RULE_procedures = 1, RULE_main = 2, RULE_commands = 3, 
		RULE_command = 4, RULE_proc_head = 5, RULE_proc_call = 6, RULE_declarations = 7, 
		RULE_args_decl = 8, RULE_args = 9, RULE_expression = 10, RULE_condition = 11, 
		RULE_value = 12, RULE_identifier = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"program_all", "procedures", "main", "commands", "command", "proc_head", 
			"proc_call", "declarations", "args_decl", "args", "expression", "condition", 
			"value", "identifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'PROCEDURE'", "'IS'", "'BEGIN'", "'END'", "'PROGRAM'", "':='", 
			"';'", "'IF'", "'THEN'", "'ELSE'", "'ENDIF'", "'WHILE'", "'DO'", "'ENDWHILE'", 
			"'REPEAT'", "'UNTIL'", "'FOR'", "'FROM'", "'TO'", "'ENDFOR'", "'DOWNTO'", 
			"'READ'", "'WRITE'", "'('", "')'", "','", "':'", "'T'", "'+'", "'-'", 
			"'*'", "'/'", "'%'", "'='", "'>'", "'<'", "'>='", "'<='", null, null, 
			"'!='", "'['", "']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "PIDENTIFIER", "NUM", "NOTEQUAL", "LHBRACK", "RHBRACK", 
			"WS", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Program_allContext extends ParserRuleContext {
		public ProceduresContext procedures() {
			return getRuleContext(ProceduresContext.class,0);
		}
		public MainContext main() {
			return getRuleContext(MainContext.class,0);
		}
		public Program_allContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program_all; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterProgram_all(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitProgram_all(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitProgram_all(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Program_allContext program_all() throws RecognitionException {
		Program_allContext _localctx = new Program_allContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program_all);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			procedures();
			setState(29);
			main();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProceduresContext extends ParserRuleContext {
		public ProceduresContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedures; }
	 
		public ProceduresContext() { }
		public void copyFrom(ProceduresContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PROCEDUREWITHOUTDECLARATIONSContext extends ProceduresContext {
		public Proc_headContext proc_head() {
			return getRuleContext(Proc_headContext.class,0);
		}
		public CommandsContext commands() {
			return getRuleContext(CommandsContext.class,0);
		}
		public PROCEDUREWITHOUTDECLARATIONSContext(ProceduresContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterPROCEDUREWITHOUTDECLARATIONS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitPROCEDUREWITHOUTDECLARATIONS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitPROCEDUREWITHOUTDECLARATIONS(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PROCEDUREWITHDECLARATIONSContext extends ProceduresContext {
		public Proc_headContext proc_head() {
			return getRuleContext(Proc_headContext.class,0);
		}
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public CommandsContext commands() {
			return getRuleContext(CommandsContext.class,0);
		}
		public PROCEDUREWITHDECLARATIONSContext(ProceduresContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterPROCEDUREWITHDECLARATIONS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitPROCEDUREWITHDECLARATIONS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitPROCEDUREWITHDECLARATIONS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProceduresContext procedures() throws RecognitionException {
		ProceduresContext _localctx = new ProceduresContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_procedures);
		try {
			setState(46);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				_localctx = new PROCEDUREWITHDECLARATIONSContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(31);
				match(T__0);
				setState(32);
				proc_head();
				setState(33);
				match(T__1);
				setState(34);
				declarations(0);
				setState(35);
				match(T__2);
				setState(36);
				commands();
				setState(37);
				match(T__3);
				}
				break;
			case 2:
				_localctx = new PROCEDUREWITHOUTDECLARATIONSContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				match(T__0);
				setState(40);
				proc_head();
				setState(41);
				match(T__1);
				setState(42);
				match(T__2);
				setState(43);
				commands();
				setState(44);
				match(T__3);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MainContext extends ParserRuleContext {
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public CommandsContext commands() {
			return getRuleContext(CommandsContext.class,0);
		}
		public MainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_main; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainContext main() throws RecognitionException {
		MainContext _localctx = new MainContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_main);
		try {
			setState(61);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				match(T__4);
				setState(49);
				match(T__1);
				setState(50);
				declarations(0);
				setState(51);
				match(T__2);
				setState(52);
				commands();
				setState(53);
				match(T__3);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				match(T__4);
				setState(56);
				match(T__1);
				setState(57);
				match(T__2);
				setState(58);
				commands();
				setState(59);
				match(T__3);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CommandsContext extends ParserRuleContext {
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public CommandsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commands; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterCommands(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitCommands(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitCommands(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandsContext commands() throws RecognitionException {
		CommandsContext _localctx = new CommandsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_commands);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(63);
				command();
				}
				}
				setState(66); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 549768564992L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CommandContext extends ParserRuleContext {
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
	 
		public CommandContext() { }
		public void copyFrom(CommandContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class READContext extends CommandContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public READContext(CommandContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterREAD(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitREAD(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitREAD(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IFELSEContext extends CommandContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<CommandsContext> commands() {
			return getRuleContexts(CommandsContext.class);
		}
		public CommandsContext commands(int i) {
			return getRuleContext(CommandsContext.class,i);
		}
		public IFELSEContext(CommandContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterIFELSE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitIFELSE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitIFELSE(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WHILEContext extends CommandContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public CommandsContext commands() {
			return getRuleContext(CommandsContext.class,0);
		}
		public WHILEContext(CommandContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterWHILE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitWHILE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitWHILE(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FORUPContext extends CommandContext {
		public TerminalNode PIDENTIFIER() { return getToken(GrammarParser.PIDENTIFIER, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public CommandsContext commands() {
			return getRuleContext(CommandsContext.class,0);
		}
		public FORUPContext(CommandContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFORUP(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFORUP(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitFORUP(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FORDOWNTOContext extends CommandContext {
		public TerminalNode PIDENTIFIER() { return getToken(GrammarParser.PIDENTIFIER, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public CommandsContext commands() {
			return getRuleContext(CommandsContext.class,0);
		}
		public FORDOWNTOContext(CommandContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFORDOWNTO(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFORDOWNTO(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitFORDOWNTO(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ASSIGNContext extends CommandContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ASSIGNContext(CommandContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterASSIGN(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitASSIGN(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitASSIGN(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class REPEATUNTILContext extends CommandContext {
		public CommandsContext commands() {
			return getRuleContext(CommandsContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public REPEATUNTILContext(CommandContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterREPEATUNTIL(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitREPEATUNTIL(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitREPEATUNTIL(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CALLPROCContext extends CommandContext {
		public Proc_callContext proc_call() {
			return getRuleContext(Proc_callContext.class,0);
		}
		public CALLPROCContext(CommandContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterCALLPROC(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitCALLPROC(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitCALLPROC(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IFContext extends CommandContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public CommandsContext commands() {
			return getRuleContext(CommandsContext.class,0);
		}
		public IFContext(CommandContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterIF(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitIF(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitIF(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WRITEContext extends CommandContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public WRITEContext(CommandContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterWRITE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitWRITE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitWRITE(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_command);
		try {
			setState(130);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new ASSIGNContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				identifier();
				setState(69);
				match(T__5);
				setState(70);
				expression();
				setState(71);
				match(T__6);
				}
				break;
			case 2:
				_localctx = new IFELSEContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				match(T__7);
				setState(74);
				condition();
				setState(75);
				match(T__8);
				setState(76);
				commands();
				setState(77);
				match(T__9);
				setState(78);
				commands();
				setState(79);
				match(T__10);
				}
				break;
			case 3:
				_localctx = new IFContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(81);
				match(T__7);
				setState(82);
				condition();
				setState(83);
				match(T__8);
				setState(84);
				commands();
				setState(85);
				match(T__10);
				}
				break;
			case 4:
				_localctx = new WHILEContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(87);
				match(T__11);
				setState(88);
				condition();
				setState(89);
				match(T__12);
				setState(90);
				commands();
				setState(91);
				match(T__13);
				}
				break;
			case 5:
				_localctx = new REPEATUNTILContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(93);
				match(T__14);
				setState(94);
				commands();
				setState(95);
				match(T__15);
				setState(96);
				condition();
				setState(97);
				match(T__6);
				}
				break;
			case 6:
				_localctx = new FORUPContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(99);
				match(T__16);
				setState(100);
				match(PIDENTIFIER);
				setState(101);
				match(T__17);
				setState(102);
				value();
				setState(103);
				match(T__18);
				setState(104);
				value();
				setState(105);
				match(T__12);
				setState(106);
				commands();
				setState(107);
				match(T__19);
				}
				break;
			case 7:
				_localctx = new FORDOWNTOContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(109);
				match(T__16);
				setState(110);
				match(PIDENTIFIER);
				setState(111);
				match(T__17);
				setState(112);
				value();
				setState(113);
				match(T__20);
				setState(114);
				value();
				setState(115);
				match(T__12);
				setState(116);
				commands();
				setState(117);
				match(T__19);
				}
				break;
			case 8:
				_localctx = new CALLPROCContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(119);
				proc_call();
				setState(120);
				match(T__6);
				}
				break;
			case 9:
				_localctx = new READContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(122);
				match(T__21);
				setState(123);
				identifier();
				setState(124);
				match(T__6);
				}
				break;
			case 10:
				_localctx = new WRITEContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(126);
				match(T__22);
				setState(127);
				value();
				setState(128);
				match(T__6);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Proc_headContext extends ParserRuleContext {
		public TerminalNode PIDENTIFIER() { return getToken(GrammarParser.PIDENTIFIER, 0); }
		public Args_declContext args_decl() {
			return getRuleContext(Args_declContext.class,0);
		}
		public Proc_headContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proc_head; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterProc_head(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitProc_head(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitProc_head(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Proc_headContext proc_head() throws RecognitionException {
		Proc_headContext _localctx = new Proc_headContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_proc_head);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(PIDENTIFIER);
			setState(133);
			match(T__23);
			setState(134);
			args_decl();
			setState(135);
			match(T__24);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Proc_callContext extends ParserRuleContext {
		public TerminalNode PIDENTIFIER() { return getToken(GrammarParser.PIDENTIFIER, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public Proc_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proc_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterProc_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitProc_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitProc_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Proc_callContext proc_call() throws RecognitionException {
		Proc_callContext _localctx = new Proc_callContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_proc_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(PIDENTIFIER);
			setState(138);
			match(T__23);
			setState(139);
			args();
			setState(140);
			match(T__24);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationsContext extends ParserRuleContext {
		public DeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarations; }
	 
		public DeclarationsContext() { }
		public void copyFrom(DeclarationsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MULTISINGLEDECLARATIONContext extends DeclarationsContext {
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public TerminalNode PIDENTIFIER() { return getToken(GrammarParser.PIDENTIFIER, 0); }
		public MULTISINGLEDECLARATIONContext(DeclarationsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMULTISINGLEDECLARATION(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMULTISINGLEDECLARATION(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMULTISINGLEDECLARATION(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MULTIARRAYDECLARATIONContext extends DeclarationsContext {
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public TerminalNode PIDENTIFIER() { return getToken(GrammarParser.PIDENTIFIER, 0); }
		public TerminalNode LHBRACK() { return getToken(GrammarParser.LHBRACK, 0); }
		public List<TerminalNode> NUM() { return getTokens(GrammarParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(GrammarParser.NUM, i);
		}
		public TerminalNode RHBRACK() { return getToken(GrammarParser.RHBRACK, 0); }
		public MULTIARRAYDECLARATIONContext(DeclarationsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMULTIARRAYDECLARATION(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMULTIARRAYDECLARATION(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMULTIARRAYDECLARATION(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SINGLEDECLARATIONContext extends DeclarationsContext {
		public TerminalNode PIDENTIFIER() { return getToken(GrammarParser.PIDENTIFIER, 0); }
		public SINGLEDECLARATIONContext(DeclarationsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterSINGLEDECLARATION(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitSINGLEDECLARATION(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitSINGLEDECLARATION(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ARRAYDECLARATIONContext extends DeclarationsContext {
		public TerminalNode PIDENTIFIER() { return getToken(GrammarParser.PIDENTIFIER, 0); }
		public TerminalNode LHBRACK() { return getToken(GrammarParser.LHBRACK, 0); }
		public List<TerminalNode> NUM() { return getTokens(GrammarParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(GrammarParser.NUM, i);
		}
		public TerminalNode RHBRACK() { return getToken(GrammarParser.RHBRACK, 0); }
		public ARRAYDECLARATIONContext(DeclarationsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterARRAYDECLARATION(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitARRAYDECLARATION(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitARRAYDECLARATION(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationsContext declarations() throws RecognitionException {
		return declarations(0);
	}

	private DeclarationsContext declarations(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DeclarationsContext _localctx = new DeclarationsContext(_ctx, _parentState);
		DeclarationsContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_declarations, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				_localctx = new SINGLEDECLARATIONContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(143);
				match(PIDENTIFIER);
				}
				break;
			case 2:
				{
				_localctx = new ARRAYDECLARATIONContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(144);
				match(PIDENTIFIER);
				setState(145);
				match(LHBRACK);
				setState(146);
				match(NUM);
				setState(147);
				match(T__26);
				setState(148);
				match(NUM);
				setState(149);
				match(RHBRACK);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(165);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(163);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new MULTISINGLEDECLARATIONContext(new DeclarationsContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_declarations);
						setState(152);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(153);
						match(T__25);
						setState(154);
						match(PIDENTIFIER);
						}
						break;
					case 2:
						{
						_localctx = new MULTIARRAYDECLARATIONContext(new DeclarationsContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_declarations);
						setState(155);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(156);
						match(T__25);
						setState(157);
						match(PIDENTIFIER);
						setState(158);
						match(LHBRACK);
						setState(159);
						match(NUM);
						setState(160);
						match(T__26);
						setState(161);
						match(NUM);
						setState(162);
						match(RHBRACK);
						}
						break;
					}
					} 
				}
				setState(167);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Args_declContext extends ParserRuleContext {
		public List<TerminalNode> PIDENTIFIER() { return getTokens(GrammarParser.PIDENTIFIER); }
		public TerminalNode PIDENTIFIER(int i) {
			return getToken(GrammarParser.PIDENTIFIER, i);
		}
		public Args_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterArgs_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitArgs_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitArgs_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Args_declContext args_decl() throws RecognitionException {
		Args_declContext _localctx = new Args_declContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_args_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__27 || _la==PIDENTIFIER) {
				{
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__27) {
					{
					setState(168);
					match(T__27);
					}
				}

				setState(171);
				match(PIDENTIFIER);
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__25) {
					{
					{
					setState(172);
					match(T__25);
					setState(174);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__27) {
						{
						setState(173);
						match(T__27);
						}
					}

					setState(176);
					match(PIDENTIFIER);
					}
					}
					setState(181);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgsContext extends ParserRuleContext {
		public List<TerminalNode> PIDENTIFIER() { return getTokens(GrammarParser.PIDENTIFIER); }
		public TerminalNode PIDENTIFIER(int i) {
			return getToken(GrammarParser.PIDENTIFIER, i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(PIDENTIFIER);
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__25) {
				{
				{
				setState(185);
				match(T__25);
				setState(186);
				match(PIDENTIFIER);
				}
				}
				setState(191);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DIVContext extends ExpressionContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public DIVContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterDIV(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitDIV(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitDIV(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ADDContext extends ExpressionContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public ADDContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterADD(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitADD(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitADD(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SUBContext extends ExpressionContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public SUBContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterSUB(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitSUB(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitSUB(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MODContext extends ExpressionContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public MODContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMOD(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMOD(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMOD(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VALEXPRContext extends ExpressionContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public VALEXPRContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterVALEXPR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitVALEXPR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitVALEXPR(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MULContext extends ExpressionContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public MULContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMUL(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMUL(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMUL(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_expression);
		try {
			setState(213);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new VALEXPRContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(192);
				value();
				}
				break;
			case 2:
				_localctx = new ADDContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(193);
				value();
				setState(194);
				match(T__28);
				setState(195);
				value();
				}
				break;
			case 3:
				_localctx = new SUBContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(197);
				value();
				setState(198);
				match(T__29);
				setState(199);
				value();
				}
				break;
			case 4:
				_localctx = new MULContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(201);
				value();
				setState(202);
				match(T__30);
				setState(203);
				value();
				}
				break;
			case 5:
				_localctx = new DIVContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(205);
				value();
				setState(206);
				match(T__31);
				setState(207);
				value();
				}
				break;
			case 6:
				_localctx = new MODContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(209);
				value();
				setState(210);
				match(T__32);
				setState(211);
				value();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionContext extends ParserRuleContext {
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
	 
		public ConditionContext() { }
		public void copyFrom(ConditionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GEQContext extends ConditionContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public GEQContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterGEQ(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitGEQ(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitGEQ(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LTContext extends ConditionContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public LTContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterLT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitLT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitLT(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LEQContext extends ConditionContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public LEQContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterLEQ(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitLEQ(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitLEQ(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NEQContext extends ConditionContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode NOTEQUAL() { return getToken(GrammarParser.NOTEQUAL, 0); }
		public NEQContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterNEQ(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitNEQ(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitNEQ(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EQContext extends ConditionContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public EQContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterEQ(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitEQ(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitEQ(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GTContext extends ConditionContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public GTContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterGT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitGT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitGT(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_condition);
		try {
			setState(239);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new EQContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(215);
				value();
				setState(216);
				match(T__33);
				setState(217);
				value();
				}
				break;
			case 2:
				_localctx = new NEQContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
				value();
				setState(220);
				match(NOTEQUAL);
				setState(221);
				value();
				}
				break;
			case 3:
				_localctx = new GTContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(223);
				value();
				setState(224);
				match(T__34);
				setState(225);
				value();
				}
				break;
			case 4:
				_localctx = new LTContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(227);
				value();
				setState(228);
				match(T__35);
				setState(229);
				value();
				}
				break;
			case 5:
				_localctx = new GEQContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(231);
				value();
				setState(232);
				match(T__36);
				setState(233);
				value();
				}
				break;
			case 6:
				_localctx = new LEQContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(235);
				value();
				setState(236);
				match(T__37);
				setState(237);
				value();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(GrammarParser.NUM, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_value);
		try {
			setState(243);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUM:
				enterOuterAlt(_localctx, 1);
				{
				setState(241);
				match(NUM);
				}
				break;
			case PIDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(242);
				identifier();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ParserRuleContext {
		public List<TerminalNode> PIDENTIFIER() { return getTokens(GrammarParser.PIDENTIFIER); }
		public TerminalNode PIDENTIFIER(int i) {
			return getToken(GrammarParser.PIDENTIFIER, i);
		}
		public TerminalNode LHBRACK() { return getToken(GrammarParser.LHBRACK, 0); }
		public List<TerminalNode> RHBRACK() { return getTokens(GrammarParser.RHBRACK); }
		public TerminalNode RHBRACK(int i) {
			return getToken(GrammarParser.RHBRACK, i);
		}
		public TerminalNode NUM() { return getToken(GrammarParser.NUM, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_identifier);
		try {
			setState(254);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(245);
				match(PIDENTIFIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(246);
				match(PIDENTIFIER);
				setState(247);
				match(LHBRACK);
				setState(248);
				match(PIDENTIFIER);
				setState(249);
				match(RHBRACK);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(250);
				match(PIDENTIFIER);
				setState(251);
				match(RHBRACK);
				setState(252);
				match(NUM);
				setState(253);
				match(RHBRACK);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return declarations_sempred((DeclarationsContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean declarations_sempred(DeclarationsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001-\u0101\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001/\b\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		">\b\u0002\u0001\u0003\u0004\u0003A\b\u0003\u000b\u0003\f\u0003B\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0003\u0004\u0083\b\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0097\b\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u00a4"+
		"\b\u0007\n\u0007\f\u0007\u00a7\t\u0007\u0001\b\u0003\b\u00aa\b\b\u0001"+
		"\b\u0001\b\u0001\b\u0003\b\u00af\b\b\u0001\b\u0005\b\u00b2\b\b\n\b\f\b"+
		"\u00b5\t\b\u0003\b\u00b7\b\b\u0001\t\u0001\t\u0001\t\u0005\t\u00bc\b\t"+
		"\n\t\f\t\u00bf\t\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00d6\b\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u00f0\b\u000b\u0001\f\u0001\f\u0003\f\u00f4\b\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00ff\b\r\u0001"+
		"\r\u0000\u0001\u000e\u000e\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u0000\u0000\u0113\u0000\u001c\u0001\u0000\u0000"+
		"\u0000\u0002.\u0001\u0000\u0000\u0000\u0004=\u0001\u0000\u0000\u0000\u0006"+
		"@\u0001\u0000\u0000\u0000\b\u0082\u0001\u0000\u0000\u0000\n\u0084\u0001"+
		"\u0000\u0000\u0000\f\u0089\u0001\u0000\u0000\u0000\u000e\u0096\u0001\u0000"+
		"\u0000\u0000\u0010\u00b6\u0001\u0000\u0000\u0000\u0012\u00b8\u0001\u0000"+
		"\u0000\u0000\u0014\u00d5\u0001\u0000\u0000\u0000\u0016\u00ef\u0001\u0000"+
		"\u0000\u0000\u0018\u00f3\u0001\u0000\u0000\u0000\u001a\u00fe\u0001\u0000"+
		"\u0000\u0000\u001c\u001d\u0003\u0002\u0001\u0000\u001d\u001e\u0003\u0004"+
		"\u0002\u0000\u001e\u0001\u0001\u0000\u0000\u0000\u001f \u0005\u0001\u0000"+
		"\u0000 !\u0003\n\u0005\u0000!\"\u0005\u0002\u0000\u0000\"#\u0003\u000e"+
		"\u0007\u0000#$\u0005\u0003\u0000\u0000$%\u0003\u0006\u0003\u0000%&\u0005"+
		"\u0004\u0000\u0000&/\u0001\u0000\u0000\u0000\'(\u0005\u0001\u0000\u0000"+
		"()\u0003\n\u0005\u0000)*\u0005\u0002\u0000\u0000*+\u0005\u0003\u0000\u0000"+
		"+,\u0003\u0006\u0003\u0000,-\u0005\u0004\u0000\u0000-/\u0001\u0000\u0000"+
		"\u0000.\u001f\u0001\u0000\u0000\u0000.\'\u0001\u0000\u0000\u0000/\u0003"+
		"\u0001\u0000\u0000\u000001\u0005\u0005\u0000\u000012\u0005\u0002\u0000"+
		"\u000023\u0003\u000e\u0007\u000034\u0005\u0003\u0000\u000045\u0003\u0006"+
		"\u0003\u000056\u0005\u0004\u0000\u00006>\u0001\u0000\u0000\u000078\u0005"+
		"\u0005\u0000\u000089\u0005\u0002\u0000\u00009:\u0005\u0003\u0000\u0000"+
		":;\u0003\u0006\u0003\u0000;<\u0005\u0004\u0000\u0000<>\u0001\u0000\u0000"+
		"\u0000=0\u0001\u0000\u0000\u0000=7\u0001\u0000\u0000\u0000>\u0005\u0001"+
		"\u0000\u0000\u0000?A\u0003\b\u0004\u0000@?\u0001\u0000\u0000\u0000AB\u0001"+
		"\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000"+
		"C\u0007\u0001\u0000\u0000\u0000DE\u0003\u001a\r\u0000EF\u0005\u0006\u0000"+
		"\u0000FG\u0003\u0014\n\u0000GH\u0005\u0007\u0000\u0000H\u0083\u0001\u0000"+
		"\u0000\u0000IJ\u0005\b\u0000\u0000JK\u0003\u0016\u000b\u0000KL\u0005\t"+
		"\u0000\u0000LM\u0003\u0006\u0003\u0000MN\u0005\n\u0000\u0000NO\u0003\u0006"+
		"\u0003\u0000OP\u0005\u000b\u0000\u0000P\u0083\u0001\u0000\u0000\u0000"+
		"QR\u0005\b\u0000\u0000RS\u0003\u0016\u000b\u0000ST\u0005\t\u0000\u0000"+
		"TU\u0003\u0006\u0003\u0000UV\u0005\u000b\u0000\u0000V\u0083\u0001\u0000"+
		"\u0000\u0000WX\u0005\f\u0000\u0000XY\u0003\u0016\u000b\u0000YZ\u0005\r"+
		"\u0000\u0000Z[\u0003\u0006\u0003\u0000[\\\u0005\u000e\u0000\u0000\\\u0083"+
		"\u0001\u0000\u0000\u0000]^\u0005\u000f\u0000\u0000^_\u0003\u0006\u0003"+
		"\u0000_`\u0005\u0010\u0000\u0000`a\u0003\u0016\u000b\u0000ab\u0005\u0007"+
		"\u0000\u0000b\u0083\u0001\u0000\u0000\u0000cd\u0005\u0011\u0000\u0000"+
		"de\u0005\'\u0000\u0000ef\u0005\u0012\u0000\u0000fg\u0003\u0018\f\u0000"+
		"gh\u0005\u0013\u0000\u0000hi\u0003\u0018\f\u0000ij\u0005\r\u0000\u0000"+
		"jk\u0003\u0006\u0003\u0000kl\u0005\u0014\u0000\u0000l\u0083\u0001\u0000"+
		"\u0000\u0000mn\u0005\u0011\u0000\u0000no\u0005\'\u0000\u0000op\u0005\u0012"+
		"\u0000\u0000pq\u0003\u0018\f\u0000qr\u0005\u0015\u0000\u0000rs\u0003\u0018"+
		"\f\u0000st\u0005\r\u0000\u0000tu\u0003\u0006\u0003\u0000uv\u0005\u0014"+
		"\u0000\u0000v\u0083\u0001\u0000\u0000\u0000wx\u0003\f\u0006\u0000xy\u0005"+
		"\u0007\u0000\u0000y\u0083\u0001\u0000\u0000\u0000z{\u0005\u0016\u0000"+
		"\u0000{|\u0003\u001a\r\u0000|}\u0005\u0007\u0000\u0000}\u0083\u0001\u0000"+
		"\u0000\u0000~\u007f\u0005\u0017\u0000\u0000\u007f\u0080\u0003\u0018\f"+
		"\u0000\u0080\u0081\u0005\u0007\u0000\u0000\u0081\u0083\u0001\u0000\u0000"+
		"\u0000\u0082D\u0001\u0000\u0000\u0000\u0082I\u0001\u0000\u0000\u0000\u0082"+
		"Q\u0001\u0000\u0000\u0000\u0082W\u0001\u0000\u0000\u0000\u0082]\u0001"+
		"\u0000\u0000\u0000\u0082c\u0001\u0000\u0000\u0000\u0082m\u0001\u0000\u0000"+
		"\u0000\u0082w\u0001\u0000\u0000\u0000\u0082z\u0001\u0000\u0000\u0000\u0082"+
		"~\u0001\u0000\u0000\u0000\u0083\t\u0001\u0000\u0000\u0000\u0084\u0085"+
		"\u0005\'\u0000\u0000\u0085\u0086\u0005\u0018\u0000\u0000\u0086\u0087\u0003"+
		"\u0010\b\u0000\u0087\u0088\u0005\u0019\u0000\u0000\u0088\u000b\u0001\u0000"+
		"\u0000\u0000\u0089\u008a\u0005\'\u0000\u0000\u008a\u008b\u0005\u0018\u0000"+
		"\u0000\u008b\u008c\u0003\u0012\t\u0000\u008c\u008d\u0005\u0019\u0000\u0000"+
		"\u008d\r\u0001\u0000\u0000\u0000\u008e\u008f\u0006\u0007\uffff\uffff\u0000"+
		"\u008f\u0097\u0005\'\u0000\u0000\u0090\u0091\u0005\'\u0000\u0000\u0091"+
		"\u0092\u0005*\u0000\u0000\u0092\u0093\u0005(\u0000\u0000\u0093\u0094\u0005"+
		"\u001b\u0000\u0000\u0094\u0095\u0005(\u0000\u0000\u0095\u0097\u0005+\u0000"+
		"\u0000\u0096\u008e\u0001\u0000\u0000\u0000\u0096\u0090\u0001\u0000\u0000"+
		"\u0000\u0097\u00a5\u0001\u0000\u0000\u0000\u0098\u0099\n\u0004\u0000\u0000"+
		"\u0099\u009a\u0005\u001a\u0000\u0000\u009a\u00a4\u0005\'\u0000\u0000\u009b"+
		"\u009c\n\u0003\u0000\u0000\u009c\u009d\u0005\u001a\u0000\u0000\u009d\u009e"+
		"\u0005\'\u0000\u0000\u009e\u009f\u0005*\u0000\u0000\u009f\u00a0\u0005"+
		"(\u0000\u0000\u00a0\u00a1\u0005\u001b\u0000\u0000\u00a1\u00a2\u0005(\u0000"+
		"\u0000\u00a2\u00a4\u0005+\u0000\u0000\u00a3\u0098\u0001\u0000\u0000\u0000"+
		"\u00a3\u009b\u0001\u0000\u0000\u0000\u00a4\u00a7\u0001\u0000\u0000\u0000"+
		"\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000"+
		"\u00a6\u000f\u0001\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000"+
		"\u00a8\u00aa\u0005\u001c\u0000\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000"+
		"\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000"+
		"\u00ab\u00b3\u0005\'\u0000\u0000\u00ac\u00ae\u0005\u001a\u0000\u0000\u00ad"+
		"\u00af\u0005\u001c\u0000\u0000\u00ae\u00ad\u0001\u0000\u0000\u0000\u00ae"+
		"\u00af\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0"+
		"\u00b2\u0005\'\u0000\u0000\u00b1\u00ac\u0001\u0000\u0000\u0000\u00b2\u00b5"+
		"\u0001\u0000\u0000\u0000\u00b3\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b4"+
		"\u0001\u0000\u0000\u0000\u00b4\u00b7\u0001\u0000\u0000\u0000\u00b5\u00b3"+
		"\u0001\u0000\u0000\u0000\u00b6\u00a9\u0001\u0000\u0000\u0000\u00b6\u00b7"+
		"\u0001\u0000\u0000\u0000\u00b7\u0011\u0001\u0000\u0000\u0000\u00b8\u00bd"+
		"\u0005\'\u0000\u0000\u00b9\u00ba\u0005\u001a\u0000\u0000\u00ba\u00bc\u0005"+
		"\'\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bc\u00bf\u0001\u0000"+
		"\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000"+
		"\u0000\u0000\u00be\u0013\u0001\u0000\u0000\u0000\u00bf\u00bd\u0001\u0000"+
		"\u0000\u0000\u00c0\u00d6\u0003\u0018\f\u0000\u00c1\u00c2\u0003\u0018\f"+
		"\u0000\u00c2\u00c3\u0005\u001d\u0000\u0000\u00c3\u00c4\u0003\u0018\f\u0000"+
		"\u00c4\u00d6\u0001\u0000\u0000\u0000\u00c5\u00c6\u0003\u0018\f\u0000\u00c6"+
		"\u00c7\u0005\u001e\u0000\u0000\u00c7\u00c8\u0003\u0018\f\u0000\u00c8\u00d6"+
		"\u0001\u0000\u0000\u0000\u00c9\u00ca\u0003\u0018\f\u0000\u00ca\u00cb\u0005"+
		"\u001f\u0000\u0000\u00cb\u00cc\u0003\u0018\f\u0000\u00cc\u00d6\u0001\u0000"+
		"\u0000\u0000\u00cd\u00ce\u0003\u0018\f\u0000\u00ce\u00cf\u0005 \u0000"+
		"\u0000\u00cf\u00d0\u0003\u0018\f\u0000\u00d0\u00d6\u0001\u0000\u0000\u0000"+
		"\u00d1\u00d2\u0003\u0018\f\u0000\u00d2\u00d3\u0005!\u0000\u0000\u00d3"+
		"\u00d4\u0003\u0018\f\u0000\u00d4\u00d6\u0001\u0000\u0000\u0000\u00d5\u00c0"+
		"\u0001\u0000\u0000\u0000\u00d5\u00c1\u0001\u0000\u0000\u0000\u00d5\u00c5"+
		"\u0001\u0000\u0000\u0000\u00d5\u00c9\u0001\u0000\u0000\u0000\u00d5\u00cd"+
		"\u0001\u0000\u0000\u0000\u00d5\u00d1\u0001\u0000\u0000\u0000\u00d6\u0015"+
		"\u0001\u0000\u0000\u0000\u00d7\u00d8\u0003\u0018\f\u0000\u00d8\u00d9\u0005"+
		"\"\u0000\u0000\u00d9\u00da\u0003\u0018\f\u0000\u00da\u00f0\u0001\u0000"+
		"\u0000\u0000\u00db\u00dc\u0003\u0018\f\u0000\u00dc\u00dd\u0005)\u0000"+
		"\u0000\u00dd\u00de\u0003\u0018\f\u0000\u00de\u00f0\u0001\u0000\u0000\u0000"+
		"\u00df\u00e0\u0003\u0018\f\u0000\u00e0\u00e1\u0005#\u0000\u0000\u00e1"+
		"\u00e2\u0003\u0018\f\u0000\u00e2\u00f0\u0001\u0000\u0000\u0000\u00e3\u00e4"+
		"\u0003\u0018\f\u0000\u00e4\u00e5\u0005$\u0000\u0000\u00e5\u00e6\u0003"+
		"\u0018\f\u0000\u00e6\u00f0\u0001\u0000\u0000\u0000\u00e7\u00e8\u0003\u0018"+
		"\f\u0000\u00e8\u00e9\u0005%\u0000\u0000\u00e9\u00ea\u0003\u0018\f\u0000"+
		"\u00ea\u00f0\u0001\u0000\u0000\u0000\u00eb\u00ec\u0003\u0018\f\u0000\u00ec"+
		"\u00ed\u0005&\u0000\u0000\u00ed\u00ee\u0003\u0018\f\u0000\u00ee\u00f0"+
		"\u0001\u0000\u0000\u0000\u00ef\u00d7\u0001\u0000\u0000\u0000\u00ef\u00db"+
		"\u0001\u0000\u0000\u0000\u00ef\u00df\u0001\u0000\u0000\u0000\u00ef\u00e3"+
		"\u0001\u0000\u0000\u0000\u00ef\u00e7\u0001\u0000\u0000\u0000\u00ef\u00eb"+
		"\u0001\u0000\u0000\u0000\u00f0\u0017\u0001\u0000\u0000\u0000\u00f1\u00f4"+
		"\u0005(\u0000\u0000\u00f2\u00f4\u0003\u001a\r\u0000\u00f3\u00f1\u0001"+
		"\u0000\u0000\u0000\u00f3\u00f2\u0001\u0000\u0000\u0000\u00f4\u0019\u0001"+
		"\u0000\u0000\u0000\u00f5\u00ff\u0005\'\u0000\u0000\u00f6\u00f7\u0005\'"+
		"\u0000\u0000\u00f7\u00f8\u0005*\u0000\u0000\u00f8\u00f9\u0005\'\u0000"+
		"\u0000\u00f9\u00ff\u0005+\u0000\u0000\u00fa\u00fb\u0005\'\u0000\u0000"+
		"\u00fb\u00fc\u0005+\u0000\u0000\u00fc\u00fd\u0005(\u0000\u0000\u00fd\u00ff"+
		"\u0005+\u0000\u0000\u00fe\u00f5\u0001\u0000\u0000\u0000\u00fe\u00f6\u0001"+
		"\u0000\u0000\u0000\u00fe\u00fa\u0001\u0000\u0000\u0000\u00ff\u001b\u0001"+
		"\u0000\u0000\u0000\u0010.=B\u0082\u0096\u00a3\u00a5\u00a9\u00ae\u00b3"+
		"\u00b6\u00bd\u00d5\u00ef\u00f3\u00fe";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}