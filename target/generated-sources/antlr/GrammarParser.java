// Generated from Grammar.g4 by ANTLR 4.13.0
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
		public List<Proc_headContext> proc_head() {
			return getRuleContexts(Proc_headContext.class);
		}
		public Proc_headContext proc_head(int i) {
			return getRuleContext(Proc_headContext.class,i);
		}
		public List<DeclarationsContext> declarations() {
			return getRuleContexts(DeclarationsContext.class);
		}
		public DeclarationsContext declarations(int i) {
			return getRuleContext(DeclarationsContext.class,i);
		}
		public List<CommandsContext> commands() {
			return getRuleContexts(CommandsContext.class);
		}
		public CommandsContext commands(int i) {
			return getRuleContext(CommandsContext.class,i);
		}
		public ProceduresContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedures; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterProcedures(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitProcedures(this);
		}
	}

	public final ProceduresContext procedures() throws RecognitionException {
		ProceduresContext _localctx = new ProceduresContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_procedures);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				setState(46);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
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
				setState(50);
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
	}

	public final MainContext main() throws RecognitionException {
		MainContext _localctx = new MainContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_main);
		try {
			setState(64);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				match(T__4);
				setState(52);
				match(T__1);
				setState(53);
				declarations(0);
				setState(54);
				match(T__2);
				setState(55);
				commands();
				setState(56);
				match(T__3);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				match(T__4);
				setState(59);
				match(T__1);
				setState(60);
				match(T__2);
				setState(61);
				commands();
				setState(62);
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
	}

	public final CommandsContext commands() throws RecognitionException {
		CommandsContext _localctx = new CommandsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_commands);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(66);
				command();
				}
				}
				setState(69); 
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
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_command);
		try {
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new ASSIGNContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				identifier();
				setState(72);
				match(T__5);
				setState(73);
				expression();
				setState(74);
				match(T__6);
				}
				break;
			case 2:
				_localctx = new IFELSEContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				match(T__7);
				setState(77);
				condition();
				setState(78);
				match(T__8);
				setState(79);
				commands();
				setState(80);
				match(T__9);
				setState(81);
				commands();
				setState(82);
				match(T__10);
				}
				break;
			case 3:
				_localctx = new IFContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(84);
				match(T__7);
				setState(85);
				condition();
				setState(86);
				match(T__8);
				setState(87);
				commands();
				setState(88);
				match(T__10);
				}
				break;
			case 4:
				_localctx = new WHILEContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(90);
				match(T__11);
				setState(91);
				condition();
				setState(92);
				match(T__12);
				setState(93);
				commands();
				setState(94);
				match(T__13);
				}
				break;
			case 5:
				_localctx = new REPEATUNTILContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(96);
				match(T__14);
				setState(97);
				commands();
				setState(98);
				match(T__15);
				setState(99);
				condition();
				setState(100);
				match(T__6);
				}
				break;
			case 6:
				_localctx = new FORUPContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(102);
				match(T__16);
				setState(103);
				match(PIDENTIFIER);
				setState(104);
				match(T__17);
				setState(105);
				value();
				setState(106);
				match(T__18);
				setState(107);
				value();
				setState(108);
				match(T__12);
				setState(109);
				commands();
				setState(110);
				match(T__19);
				}
				break;
			case 7:
				_localctx = new FORDOWNTOContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(112);
				match(T__16);
				setState(113);
				match(PIDENTIFIER);
				setState(114);
				match(T__17);
				setState(115);
				value();
				setState(116);
				match(T__20);
				setState(117);
				value();
				setState(118);
				match(T__12);
				setState(119);
				commands();
				setState(120);
				match(T__19);
				}
				break;
			case 8:
				_localctx = new CALLPROCContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(122);
				proc_call();
				setState(123);
				match(T__6);
				}
				break;
			case 9:
				_localctx = new READContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(125);
				match(T__21);
				setState(126);
				identifier();
				setState(127);
				match(T__6);
				}
				break;
			case 10:
				_localctx = new WRITEContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(129);
				match(T__22);
				setState(130);
				value();
				setState(131);
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
	}

	public final Proc_headContext proc_head() throws RecognitionException {
		Proc_headContext _localctx = new Proc_headContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_proc_head);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(PIDENTIFIER);
			setState(136);
			match(T__23);
			setState(137);
			args_decl();
			setState(138);
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
	}

	public final Proc_callContext proc_call() throws RecognitionException {
		Proc_callContext _localctx = new Proc_callContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_proc_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(PIDENTIFIER);
			setState(141);
			match(T__23);
			setState(142);
			args();
			setState(143);
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
		public TerminalNode PIDENTIFIER() { return getToken(GrammarParser.PIDENTIFIER, 0); }
		public TerminalNode LHBRACK() { return getToken(GrammarParser.LHBRACK, 0); }
		public List<TerminalNode> NUM() { return getTokens(GrammarParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(GrammarParser.NUM, i);
		}
		public TerminalNode RHBRACK() { return getToken(GrammarParser.RHBRACK, 0); }
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public DeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterDeclarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitDeclarations(this);
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
			setState(153);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(146);
				match(PIDENTIFIER);
				}
				break;
			case 2:
				{
				setState(147);
				match(PIDENTIFIER);
				setState(148);
				match(LHBRACK);
				setState(149);
				match(NUM);
				setState(150);
				match(T__26);
				setState(151);
				match(NUM);
				setState(152);
				match(RHBRACK);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(168);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(166);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new DeclarationsContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_declarations);
						setState(155);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(156);
						match(T__25);
						setState(157);
						match(PIDENTIFIER);
						}
						break;
					case 2:
						{
						_localctx = new DeclarationsContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_declarations);
						setState(158);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(159);
						match(T__25);
						setState(160);
						match(PIDENTIFIER);
						setState(161);
						match(LHBRACK);
						setState(162);
						match(NUM);
						setState(163);
						match(T__26);
						setState(164);
						match(NUM);
						setState(165);
						match(RHBRACK);
						}
						break;
					}
					} 
				}
				setState(170);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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
	}

	public final Args_declContext args_decl() throws RecognitionException {
		Args_declContext _localctx = new Args_declContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_args_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__27 || _la==PIDENTIFIER) {
				{
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__27) {
					{
					setState(171);
					match(T__27);
					}
				}

				setState(174);
				match(PIDENTIFIER);
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__25) {
					{
					{
					setState(175);
					match(T__25);
					setState(177);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__27) {
						{
						setState(176);
						match(T__27);
						}
					}

					setState(179);
					match(PIDENTIFIER);
					}
					}
					setState(184);
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
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(PIDENTIFIER);
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__25) {
				{
				{
				setState(188);
				match(T__25);
				setState(189);
				match(PIDENTIFIER);
				}
				}
				setState(194);
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
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_expression);
		try {
			setState(216);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new VALEXPRContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				value();
				}
				break;
			case 2:
				_localctx = new ADDContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(196);
				value();
				setState(197);
				match(T__28);
				setState(198);
				value();
				}
				break;
			case 3:
				_localctx = new SUBContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(200);
				value();
				setState(201);
				match(T__29);
				setState(202);
				value();
				}
				break;
			case 4:
				_localctx = new MULContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(204);
				value();
				setState(205);
				match(T__30);
				setState(206);
				value();
				}
				break;
			case 5:
				_localctx = new DIVContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(208);
				value();
				setState(209);
				match(T__31);
				setState(210);
				value();
				}
				break;
			case 6:
				_localctx = new MODContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(212);
				value();
				setState(213);
				match(T__32);
				setState(214);
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
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_condition);
		try {
			setState(242);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new EQContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				value();
				setState(219);
				match(T__33);
				setState(220);
				value();
				}
				break;
			case 2:
				_localctx = new NEQContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(222);
				value();
				setState(223);
				match(NOTEQUAL);
				setState(224);
				value();
				}
				break;
			case 3:
				_localctx = new GTContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(226);
				value();
				setState(227);
				match(T__34);
				setState(228);
				value();
				}
				break;
			case 4:
				_localctx = new LTContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(230);
				value();
				setState(231);
				match(T__35);
				setState(232);
				value();
				}
				break;
			case 5:
				_localctx = new GEQContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(234);
				value();
				setState(235);
				match(T__36);
				setState(236);
				value();
				}
				break;
			case 6:
				_localctx = new LEQContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(238);
				value();
				setState(239);
				match(T__37);
				setState(240);
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
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_value);
		try {
			setState(246);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUM:
				enterOuterAlt(_localctx, 1);
				{
				setState(244);
				match(NUM);
				}
				break;
			case PIDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(245);
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
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_identifier);
		try {
			setState(257);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				match(PIDENTIFIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(249);
				match(PIDENTIFIER);
				setState(250);
				match(LHBRACK);
				setState(251);
				match(PIDENTIFIER);
				setState(252);
				match(RHBRACK);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(253);
				match(PIDENTIFIER);
				setState(254);
				match(RHBRACK);
				setState(255);
				match(NUM);
				setState(256);
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
		"\u0004\u0001-\u0104\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0005\u0001/\b\u0001\n\u0001\f\u00012\t\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002A\b\u0002\u0001\u0003\u0004\u0003D\b\u0003\u000b"+
		"\u0003\f\u0003E\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u0086\b\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003"+
		"\u0007\u009a\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0005\u0007\u00a7\b\u0007\n\u0007\f\u0007\u00aa\t\u0007\u0001\b"+
		"\u0003\b\u00ad\b\b\u0001\b\u0001\b\u0001\b\u0003\b\u00b2\b\b\u0001\b\u0005"+
		"\b\u00b5\b\b\n\b\f\b\u00b8\t\b\u0003\b\u00ba\b\b\u0001\t\u0001\t\u0001"+
		"\t\u0005\t\u00bf\b\t\n\t\f\t\u00c2\t\t\u0001\n\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\u00d9\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u00f3\b\u000b\u0001\f\u0001\f\u0003\f\u00f7\b"+
		"\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0003\r\u0102\b\r\u0001\r\u0000\u0001\u000e\u000e\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u0000\u0000\u0117"+
		"\u0000\u001c\u0001\u0000\u0000\u0000\u00020\u0001\u0000\u0000\u0000\u0004"+
		"@\u0001\u0000\u0000\u0000\u0006C\u0001\u0000\u0000\u0000\b\u0085\u0001"+
		"\u0000\u0000\u0000\n\u0087\u0001\u0000\u0000\u0000\f\u008c\u0001\u0000"+
		"\u0000\u0000\u000e\u0099\u0001\u0000\u0000\u0000\u0010\u00b9\u0001\u0000"+
		"\u0000\u0000\u0012\u00bb\u0001\u0000\u0000\u0000\u0014\u00d8\u0001\u0000"+
		"\u0000\u0000\u0016\u00f2\u0001\u0000\u0000\u0000\u0018\u00f6\u0001\u0000"+
		"\u0000\u0000\u001a\u0101\u0001\u0000\u0000\u0000\u001c\u001d\u0003\u0002"+
		"\u0001\u0000\u001d\u001e\u0003\u0004\u0002\u0000\u001e\u0001\u0001\u0000"+
		"\u0000\u0000\u001f \u0005\u0001\u0000\u0000 !\u0003\n\u0005\u0000!\"\u0005"+
		"\u0002\u0000\u0000\"#\u0003\u000e\u0007\u0000#$\u0005\u0003\u0000\u0000"+
		"$%\u0003\u0006\u0003\u0000%&\u0005\u0004\u0000\u0000&/\u0001\u0000\u0000"+
		"\u0000\'(\u0005\u0001\u0000\u0000()\u0003\n\u0005\u0000)*\u0005\u0002"+
		"\u0000\u0000*+\u0005\u0003\u0000\u0000+,\u0003\u0006\u0003\u0000,-\u0005"+
		"\u0004\u0000\u0000-/\u0001\u0000\u0000\u0000.\u001f\u0001\u0000\u0000"+
		"\u0000.\'\u0001\u0000\u0000\u0000/2\u0001\u0000\u0000\u00000.\u0001\u0000"+
		"\u0000\u000001\u0001\u0000\u0000\u00001\u0003\u0001\u0000\u0000\u0000"+
		"20\u0001\u0000\u0000\u000034\u0005\u0005\u0000\u000045\u0005\u0002\u0000"+
		"\u000056\u0003\u000e\u0007\u000067\u0005\u0003\u0000\u000078\u0003\u0006"+
		"\u0003\u000089\u0005\u0004\u0000\u00009A\u0001\u0000\u0000\u0000:;\u0005"+
		"\u0005\u0000\u0000;<\u0005\u0002\u0000\u0000<=\u0005\u0003\u0000\u0000"+
		"=>\u0003\u0006\u0003\u0000>?\u0005\u0004\u0000\u0000?A\u0001\u0000\u0000"+
		"\u0000@3\u0001\u0000\u0000\u0000@:\u0001\u0000\u0000\u0000A\u0005\u0001"+
		"\u0000\u0000\u0000BD\u0003\b\u0004\u0000CB\u0001\u0000\u0000\u0000DE\u0001"+
		"\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000"+
		"F\u0007\u0001\u0000\u0000\u0000GH\u0003\u001a\r\u0000HI\u0005\u0006\u0000"+
		"\u0000IJ\u0003\u0014\n\u0000JK\u0005\u0007\u0000\u0000K\u0086\u0001\u0000"+
		"\u0000\u0000LM\u0005\b\u0000\u0000MN\u0003\u0016\u000b\u0000NO\u0005\t"+
		"\u0000\u0000OP\u0003\u0006\u0003\u0000PQ\u0005\n\u0000\u0000QR\u0003\u0006"+
		"\u0003\u0000RS\u0005\u000b\u0000\u0000S\u0086\u0001\u0000\u0000\u0000"+
		"TU\u0005\b\u0000\u0000UV\u0003\u0016\u000b\u0000VW\u0005\t\u0000\u0000"+
		"WX\u0003\u0006\u0003\u0000XY\u0005\u000b\u0000\u0000Y\u0086\u0001\u0000"+
		"\u0000\u0000Z[\u0005\f\u0000\u0000[\\\u0003\u0016\u000b\u0000\\]\u0005"+
		"\r\u0000\u0000]^\u0003\u0006\u0003\u0000^_\u0005\u000e\u0000\u0000_\u0086"+
		"\u0001\u0000\u0000\u0000`a\u0005\u000f\u0000\u0000ab\u0003\u0006\u0003"+
		"\u0000bc\u0005\u0010\u0000\u0000cd\u0003\u0016\u000b\u0000de\u0005\u0007"+
		"\u0000\u0000e\u0086\u0001\u0000\u0000\u0000fg\u0005\u0011\u0000\u0000"+
		"gh\u0005\'\u0000\u0000hi\u0005\u0012\u0000\u0000ij\u0003\u0018\f\u0000"+
		"jk\u0005\u0013\u0000\u0000kl\u0003\u0018\f\u0000lm\u0005\r\u0000\u0000"+
		"mn\u0003\u0006\u0003\u0000no\u0005\u0014\u0000\u0000o\u0086\u0001\u0000"+
		"\u0000\u0000pq\u0005\u0011\u0000\u0000qr\u0005\'\u0000\u0000rs\u0005\u0012"+
		"\u0000\u0000st\u0003\u0018\f\u0000tu\u0005\u0015\u0000\u0000uv\u0003\u0018"+
		"\f\u0000vw\u0005\r\u0000\u0000wx\u0003\u0006\u0003\u0000xy\u0005\u0014"+
		"\u0000\u0000y\u0086\u0001\u0000\u0000\u0000z{\u0003\f\u0006\u0000{|\u0005"+
		"\u0007\u0000\u0000|\u0086\u0001\u0000\u0000\u0000}~\u0005\u0016\u0000"+
		"\u0000~\u007f\u0003\u001a\r\u0000\u007f\u0080\u0005\u0007\u0000\u0000"+
		"\u0080\u0086\u0001\u0000\u0000\u0000\u0081\u0082\u0005\u0017\u0000\u0000"+
		"\u0082\u0083\u0003\u0018\f\u0000\u0083\u0084\u0005\u0007\u0000\u0000\u0084"+
		"\u0086\u0001\u0000\u0000\u0000\u0085G\u0001\u0000\u0000\u0000\u0085L\u0001"+
		"\u0000\u0000\u0000\u0085T\u0001\u0000\u0000\u0000\u0085Z\u0001\u0000\u0000"+
		"\u0000\u0085`\u0001\u0000\u0000\u0000\u0085f\u0001\u0000\u0000\u0000\u0085"+
		"p\u0001\u0000\u0000\u0000\u0085z\u0001\u0000\u0000\u0000\u0085}\u0001"+
		"\u0000\u0000\u0000\u0085\u0081\u0001\u0000\u0000\u0000\u0086\t\u0001\u0000"+
		"\u0000\u0000\u0087\u0088\u0005\'\u0000\u0000\u0088\u0089\u0005\u0018\u0000"+
		"\u0000\u0089\u008a\u0003\u0010\b\u0000\u008a\u008b\u0005\u0019\u0000\u0000"+
		"\u008b\u000b\u0001\u0000\u0000\u0000\u008c\u008d\u0005\'\u0000\u0000\u008d"+
		"\u008e\u0005\u0018\u0000\u0000\u008e\u008f\u0003\u0012\t\u0000\u008f\u0090"+
		"\u0005\u0019\u0000\u0000\u0090\r\u0001\u0000\u0000\u0000\u0091\u0092\u0006"+
		"\u0007\uffff\uffff\u0000\u0092\u009a\u0005\'\u0000\u0000\u0093\u0094\u0005"+
		"\'\u0000\u0000\u0094\u0095\u0005*\u0000\u0000\u0095\u0096\u0005(\u0000"+
		"\u0000\u0096\u0097\u0005\u001b\u0000\u0000\u0097\u0098\u0005(\u0000\u0000"+
		"\u0098\u009a\u0005+\u0000\u0000\u0099\u0091\u0001\u0000\u0000\u0000\u0099"+
		"\u0093\u0001\u0000\u0000\u0000\u009a\u00a8\u0001\u0000\u0000\u0000\u009b"+
		"\u009c\n\u0004\u0000\u0000\u009c\u009d\u0005\u001a\u0000\u0000\u009d\u00a7"+
		"\u0005\'\u0000\u0000\u009e\u009f\n\u0003\u0000\u0000\u009f\u00a0\u0005"+
		"\u001a\u0000\u0000\u00a0\u00a1\u0005\'\u0000\u0000\u00a1\u00a2\u0005*"+
		"\u0000\u0000\u00a2\u00a3\u0005(\u0000\u0000\u00a3\u00a4\u0005\u001b\u0000"+
		"\u0000\u00a4\u00a5\u0005(\u0000\u0000\u00a5\u00a7\u0005+\u0000\u0000\u00a6"+
		"\u009b\u0001\u0000\u0000\u0000\u00a6\u009e\u0001\u0000\u0000\u0000\u00a7"+
		"\u00aa\u0001\u0000\u0000\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000\u00a8"+
		"\u00a9\u0001\u0000\u0000\u0000\u00a9\u000f\u0001\u0000\u0000\u0000\u00aa"+
		"\u00a8\u0001\u0000\u0000\u0000\u00ab\u00ad\u0005\u001c\u0000\u0000\u00ac"+
		"\u00ab\u0001\u0000\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad"+
		"\u00ae\u0001\u0000\u0000\u0000\u00ae\u00b6\u0005\'\u0000\u0000\u00af\u00b1"+
		"\u0005\u001a\u0000\u0000\u00b0\u00b2\u0005\u001c\u0000\u0000\u00b1\u00b0"+
		"\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2\u00b3"+
		"\u0001\u0000\u0000\u0000\u00b3\u00b5\u0005\'\u0000\u0000\u00b4\u00af\u0001"+
		"\u0000\u0000\u0000\u00b5\u00b8\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001"+
		"\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u00ba\u0001"+
		"\u0000\u0000\u0000\u00b8\u00b6\u0001\u0000\u0000\u0000\u00b9\u00ac\u0001"+
		"\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba\u0011\u0001"+
		"\u0000\u0000\u0000\u00bb\u00c0\u0005\'\u0000\u0000\u00bc\u00bd\u0005\u001a"+
		"\u0000\u0000\u00bd\u00bf\u0005\'\u0000\u0000\u00be\u00bc\u0001\u0000\u0000"+
		"\u0000\u00bf\u00c2\u0001\u0000\u0000\u0000\u00c0\u00be\u0001\u0000\u0000"+
		"\u0000\u00c0\u00c1\u0001\u0000\u0000\u0000\u00c1\u0013\u0001\u0000\u0000"+
		"\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c3\u00d9\u0003\u0018\f\u0000"+
		"\u00c4\u00c5\u0003\u0018\f\u0000\u00c5\u00c6\u0005\u001d\u0000\u0000\u00c6"+
		"\u00c7\u0003\u0018\f\u0000\u00c7\u00d9\u0001\u0000\u0000\u0000\u00c8\u00c9"+
		"\u0003\u0018\f\u0000\u00c9\u00ca\u0005\u001e\u0000\u0000\u00ca\u00cb\u0003"+
		"\u0018\f\u0000\u00cb\u00d9\u0001\u0000\u0000\u0000\u00cc\u00cd\u0003\u0018"+
		"\f\u0000\u00cd\u00ce\u0005\u001f\u0000\u0000\u00ce\u00cf\u0003\u0018\f"+
		"\u0000\u00cf\u00d9\u0001\u0000\u0000\u0000\u00d0\u00d1\u0003\u0018\f\u0000"+
		"\u00d1\u00d2\u0005 \u0000\u0000\u00d2\u00d3\u0003\u0018\f\u0000\u00d3"+
		"\u00d9\u0001\u0000\u0000\u0000\u00d4\u00d5\u0003\u0018\f\u0000\u00d5\u00d6"+
		"\u0005!\u0000\u0000\u00d6\u00d7\u0003\u0018\f\u0000\u00d7\u00d9\u0001"+
		"\u0000\u0000\u0000\u00d8\u00c3\u0001\u0000\u0000\u0000\u00d8\u00c4\u0001"+
		"\u0000\u0000\u0000\u00d8\u00c8\u0001\u0000\u0000\u0000\u00d8\u00cc\u0001"+
		"\u0000\u0000\u0000\u00d8\u00d0\u0001\u0000\u0000\u0000\u00d8\u00d4\u0001"+
		"\u0000\u0000\u0000\u00d9\u0015\u0001\u0000\u0000\u0000\u00da\u00db\u0003"+
		"\u0018\f\u0000\u00db\u00dc\u0005\"\u0000\u0000\u00dc\u00dd\u0003\u0018"+
		"\f\u0000\u00dd\u00f3\u0001\u0000\u0000\u0000\u00de\u00df\u0003\u0018\f"+
		"\u0000\u00df\u00e0\u0005)\u0000\u0000\u00e0\u00e1\u0003\u0018\f\u0000"+
		"\u00e1\u00f3\u0001\u0000\u0000\u0000\u00e2\u00e3\u0003\u0018\f\u0000\u00e3"+
		"\u00e4\u0005#\u0000\u0000\u00e4\u00e5\u0003\u0018\f\u0000\u00e5\u00f3"+
		"\u0001\u0000\u0000\u0000\u00e6\u00e7\u0003\u0018\f\u0000\u00e7\u00e8\u0005"+
		"$\u0000\u0000\u00e8\u00e9\u0003\u0018\f\u0000\u00e9\u00f3\u0001\u0000"+
		"\u0000\u0000\u00ea\u00eb\u0003\u0018\f\u0000\u00eb\u00ec\u0005%\u0000"+
		"\u0000\u00ec\u00ed\u0003\u0018\f\u0000\u00ed\u00f3\u0001\u0000\u0000\u0000"+
		"\u00ee\u00ef\u0003\u0018\f\u0000\u00ef\u00f0\u0005&\u0000\u0000\u00f0"+
		"\u00f1\u0003\u0018\f\u0000\u00f1\u00f3\u0001\u0000\u0000\u0000\u00f2\u00da"+
		"\u0001\u0000\u0000\u0000\u00f2\u00de\u0001\u0000\u0000\u0000\u00f2\u00e2"+
		"\u0001\u0000\u0000\u0000\u00f2\u00e6\u0001\u0000\u0000\u0000\u00f2\u00ea"+
		"\u0001\u0000\u0000\u0000\u00f2\u00ee\u0001\u0000\u0000\u0000\u00f3\u0017"+
		"\u0001\u0000\u0000\u0000\u00f4\u00f7\u0005(\u0000\u0000\u00f5\u00f7\u0003"+
		"\u001a\r\u0000\u00f6\u00f4\u0001\u0000\u0000\u0000\u00f6\u00f5\u0001\u0000"+
		"\u0000\u0000\u00f7\u0019\u0001\u0000\u0000\u0000\u00f8\u0102\u0005\'\u0000"+
		"\u0000\u00f9\u00fa\u0005\'\u0000\u0000\u00fa\u00fb\u0005*\u0000\u0000"+
		"\u00fb\u00fc\u0005\'\u0000\u0000\u00fc\u0102\u0005+\u0000\u0000\u00fd"+
		"\u00fe\u0005\'\u0000\u0000\u00fe\u00ff\u0005+\u0000\u0000\u00ff\u0100"+
		"\u0005(\u0000\u0000\u0100\u0102\u0005+\u0000\u0000\u0101\u00f8\u0001\u0000"+
		"\u0000\u0000\u0101\u00f9\u0001\u0000\u0000\u0000\u0101\u00fd\u0001\u0000"+
		"\u0000\u0000\u0102\u001b\u0001\u0000\u0000\u0000\u0011.0@E\u0085\u0099"+
		"\u00a6\u00a8\u00ac\u00b1\u00b6\u00b9\u00c0\u00d8\u00f2\u00f6\u0101";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}