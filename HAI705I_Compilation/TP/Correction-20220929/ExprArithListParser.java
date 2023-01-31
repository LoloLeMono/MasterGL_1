// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ExprArithListParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, Number=7, WS=8;
	public static final int
		RULE_listExpr = 0, RULE_expr = 1, RULE_additionExpr = 2, RULE_multiplyExpr = 3, 
		RULE_atomExpr = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"listExpr", "expr", "additionExpr", "multiplyExpr", "atomExpr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'", "'*'", "'/'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "Number", "WS"
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
	public String getGrammarFileName() { return "java-escape"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExprArithListParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListExprContext extends ParserRuleContext {
		public ArrayList<ExprArith> value;
		public ExprContext e;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ListExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprArithListListener ) ((ExprArithListListener)listener).enterListExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprArithListListener ) ((ExprArithListListener)listener).exitListExpr(this);
		}
	}

	public final ListExprContext listExpr() throws RecognitionException {
		ListExprContext _localctx = new ListExprContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_listExpr);
		((ListExprContext)_localctx).value =  new ArrayList<ExprArith>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(10);
				((ListExprContext)_localctx).e = expr();
				_localctx.value.add(((ListExprContext)_localctx).e.value);
				}
				}
				setState(15); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((_la) & ~0x3f) == 0 && ((1L << _la) & 164L) != 0 );
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
	public static class ExprContext extends ParserRuleContext {
		public ExprArith value;
		public AdditionExprContext e;
		public AdditionExprContext additionExpr() {
			return getRuleContext(AdditionExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprArithListListener ) ((ExprArithListListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprArithListListener ) ((ExprArithListListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			((ExprContext)_localctx).e = additionExpr();
			((ExprContext)_localctx).value =  ((ExprContext)_localctx).e.value;
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
	public static class AdditionExprContext extends ParserRuleContext {
		public ExprArith value;
		public MultiplyExprContext e1;
		public MultiplyExprContext e2;
		public List<MultiplyExprContext> multiplyExpr() {
			return getRuleContexts(MultiplyExprContext.class);
		}
		public MultiplyExprContext multiplyExpr(int i) {
			return getRuleContext(MultiplyExprContext.class,i);
		}
		public AdditionExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additionExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprArithListListener ) ((ExprArithListListener)listener).enterAdditionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprArithListListener ) ((ExprArithListListener)listener).exitAdditionExpr(this);
		}
	}

	public final AdditionExprContext additionExpr() throws RecognitionException {
		AdditionExprContext _localctx = new AdditionExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_additionExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			((AdditionExprContext)_localctx).e1 = multiplyExpr();
			((AdditionExprContext)_localctx).value =  ((AdditionExprContext)_localctx).e1.value;
			setState(32);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(30);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__0:
						{
						setState(22);
						match(T__0);
						setState(23);
						((AdditionExprContext)_localctx).e2 = multiplyExpr();
						((AdditionExprContext)_localctx).value =  new Add(_localctx.value, ((AdditionExprContext)_localctx).e2.value);
						}
						break;
					case T__1:
						{
						setState(26);
						match(T__1);
						setState(27);
						((AdditionExprContext)_localctx).e2 = multiplyExpr();
						((AdditionExprContext)_localctx).value =  new Sub(_localctx.value, ((AdditionExprContext)_localctx).e2.value);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(34);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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
	public static class MultiplyExprContext extends ParserRuleContext {
		public ExprArith value;
		public AtomExprContext e1;
		public AtomExprContext e2;
		public List<AtomExprContext> atomExpr() {
			return getRuleContexts(AtomExprContext.class);
		}
		public AtomExprContext atomExpr(int i) {
			return getRuleContext(AtomExprContext.class,i);
		}
		public MultiplyExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplyExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprArithListListener ) ((ExprArithListListener)listener).enterMultiplyExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprArithListListener ) ((ExprArithListListener)listener).exitMultiplyExpr(this);
		}
	}

	public final MultiplyExprContext multiplyExpr() throws RecognitionException {
		MultiplyExprContext _localctx = new MultiplyExprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_multiplyExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			((MultiplyExprContext)_localctx).e1 = atomExpr();
			((MultiplyExprContext)_localctx).value =  ((MultiplyExprContext)_localctx).e1.value;
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2 || _la==T__3) {
				{
				setState(45);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__2:
					{
					setState(37);
					match(T__2);
					setState(38);
					((MultiplyExprContext)_localctx).e2 = atomExpr();
					((MultiplyExprContext)_localctx).value =  new Mul(_localctx.value, ((MultiplyExprContext)_localctx).e2.value);
					}
					break;
				case T__3:
					{
					setState(41);
					match(T__3);
					setState(42);
					((MultiplyExprContext)_localctx).e2 = atomExpr();
					((MultiplyExprContext)_localctx).value =  new Div(_localctx.value, ((MultiplyExprContext)_localctx).e2.value);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(49);
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
	public static class AtomExprContext extends ParserRuleContext {
		public ExprArith value;
		public Token c;
		public AdditionExprContext e1;
		public AtomExprContext e2;
		public TerminalNode Number() { return getToken(ExprArithListParser.Number, 0); }
		public AdditionExprContext additionExpr() {
			return getRuleContext(AdditionExprContext.class,0);
		}
		public AtomExprContext atomExpr() {
			return getRuleContext(AtomExprContext.class,0);
		}
		public AtomExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprArithListListener ) ((ExprArithListListener)listener).enterAtomExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprArithListListener ) ((ExprArithListListener)listener).exitAtomExpr(this);
		}
	}

	public final AtomExprContext atomExpr() throws RecognitionException {
		AtomExprContext _localctx = new AtomExprContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_atomExpr);
		try {
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Number:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				((AtomExprContext)_localctx).c = match(Number);
				((AtomExprContext)_localctx).value =  new Cte(Integer.parseInt((((AtomExprContext)_localctx).c!=null?((AtomExprContext)_localctx).c.getText():null)));
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				match(T__4);
				setState(53);
				((AtomExprContext)_localctx).e1 = additionExpr();
				setState(54);
				match(T__5);
				((AtomExprContext)_localctx).value =  ((AtomExprContext)_localctx).e1.value;
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(57);
				match(T__1);
				setState(58);
				((AtomExprContext)_localctx).e2 = atomExpr();
				((AtomExprContext)_localctx).value =  new Inv(((AtomExprContext)_localctx).e2.value);
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

	public static final String _serializedATN =
		"\u0004\u0001\b@\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0004\u0000\u000e\b\u0000\u000b\u0000\f"+
		"\u0000\u000f\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0005\u0002\u001f\b\u0002\n\u0002\f\u0002\"\t"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003.\b"+
		"\u0003\n\u0003\f\u00031\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0003\u0004>\b\u0004\u0001\u0004\u0000\u0000\u0005"+
		"\u0000\u0002\u0004\u0006\b\u0000\u0000A\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0002\u0011\u0001\u0000\u0000\u0000\u0004\u0014\u0001\u0000\u0000\u0000"+
		"\u0006#\u0001\u0000\u0000\u0000\b=\u0001\u0000\u0000\u0000\n\u000b\u0003"+
		"\u0002\u0001\u0000\u000b\f\u0006\u0000\uffff\uffff\u0000\f\u000e\u0001"+
		"\u0000\u0000\u0000\r\n\u0001\u0000\u0000\u0000\u000e\u000f\u0001\u0000"+
		"\u0000\u0000\u000f\r\u0001\u0000\u0000\u0000\u000f\u0010\u0001\u0000\u0000"+
		"\u0000\u0010\u0001\u0001\u0000\u0000\u0000\u0011\u0012\u0003\u0004\u0002"+
		"\u0000\u0012\u0013\u0006\u0001\uffff\uffff\u0000\u0013\u0003\u0001\u0000"+
		"\u0000\u0000\u0014\u0015\u0003\u0006\u0003\u0000\u0015 \u0006\u0002\uffff"+
		"\uffff\u0000\u0016\u0017\u0005\u0001\u0000\u0000\u0017\u0018\u0003\u0006"+
		"\u0003\u0000\u0018\u0019\u0006\u0002\uffff\uffff\u0000\u0019\u001f\u0001"+
		"\u0000\u0000\u0000\u001a\u001b\u0005\u0002\u0000\u0000\u001b\u001c\u0003"+
		"\u0006\u0003\u0000\u001c\u001d\u0006\u0002\uffff\uffff\u0000\u001d\u001f"+
		"\u0001\u0000\u0000\u0000\u001e\u0016\u0001\u0000\u0000\u0000\u001e\u001a"+
		"\u0001\u0000\u0000\u0000\u001f\"\u0001\u0000\u0000\u0000 \u001e\u0001"+
		"\u0000\u0000\u0000 !\u0001\u0000\u0000\u0000!\u0005\u0001\u0000\u0000"+
		"\u0000\" \u0001\u0000\u0000\u0000#$\u0003\b\u0004\u0000$/\u0006\u0003"+
		"\uffff\uffff\u0000%&\u0005\u0003\u0000\u0000&\'\u0003\b\u0004\u0000\'"+
		"(\u0006\u0003\uffff\uffff\u0000(.\u0001\u0000\u0000\u0000)*\u0005\u0004"+
		"\u0000\u0000*+\u0003\b\u0004\u0000+,\u0006\u0003\uffff\uffff\u0000,.\u0001"+
		"\u0000\u0000\u0000-%\u0001\u0000\u0000\u0000-)\u0001\u0000\u0000\u0000"+
		".1\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000"+
		"\u00000\u0007\u0001\u0000\u0000\u00001/\u0001\u0000\u0000\u000023\u0005"+
		"\u0007\u0000\u00003>\u0006\u0004\uffff\uffff\u000045\u0005\u0005\u0000"+
		"\u000056\u0003\u0004\u0002\u000067\u0005\u0006\u0000\u000078\u0006\u0004"+
		"\uffff\uffff\u00008>\u0001\u0000\u0000\u00009:\u0005\u0002\u0000\u0000"+
		":;\u0003\b\u0004\u0000;<\u0006\u0004\uffff\uffff\u0000<>\u0001\u0000\u0000"+
		"\u0000=2\u0001\u0000\u0000\u0000=4\u0001\u0000\u0000\u0000=9\u0001\u0000"+
		"\u0000\u0000>\t\u0001\u0000\u0000\u0000\u0006\u000f\u001e -/=";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}