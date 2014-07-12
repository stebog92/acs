// Generated from Ex2.g by ANTLR 4.1

        import java.util.HashMap;
    
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Ex2Parser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__6=1, T__5=2, T__4=3, T__3=4, T__2=5, T__1=6, T__0=7, ID=8, INT=9, FLOAT=10, 
		NEWLINE=11, WS=12;
	public static final String[] tokenNames = {
		"<INVALID>", "')'", "'+'", "'-'", "'*'", "'('", "'/'", "'='", "ID", "INT", 
		"FLOAT", "NEWLINE", "WS"
	};
	public static final int
		RULE_prog = 0, RULE_stat = 1, RULE_expr = 2, RULE_term = 3, RULE_fact = 4;
	public static final String[] ruleNames = {
		"prog", "stat", "expr", "term", "fact"
	};

	@Override
	public String getGrammarFileName() { return "Ex2.g"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


		public class Value{
			public int intValue;
			public float floatValue;
			public String type = "";
			public Value(int val) {
				this.intValue = val;
				type = "int";
			}
			public Value(float val) {
				this.floatValue = val;
				type = "float";
			}
		}
	        HashMap memory = new HashMap();
	    
	public Ex2Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Ex2Listener ) ((Ex2Listener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Ex2Listener ) ((Ex2Listener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(10); stat();
				}
				}
				setState(13); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 5) | (1L << ID) | (1L << INT) | (1L << FLOAT) | (1L << NEWLINE))) != 0) );
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

	public static class StatContext extends ParserRuleContext {
		public ExprContext expr;
		public Token ID;
		public TerminalNode NEWLINE() { return getToken(Ex2Parser.NEWLINE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(Ex2Parser.ID, 0); }
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Ex2Listener ) ((Ex2Listener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Ex2Listener ) ((Ex2Listener)listener).exitStat(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		try {
			setState(26);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(15); ((StatContext)_localctx).expr = expr();
				setState(16); match(NEWLINE);
				System.out.println(((StatContext)_localctx).expr.value.type == "int"? ((StatContext)_localctx).expr.value.intValue : ((StatContext)_localctx).expr.value.floatValue);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(19); ((StatContext)_localctx).ID = match(ID);
				setState(20); match(7);
				setState(21); ((StatContext)_localctx).expr = expr();
				setState(22); match(NEWLINE);
				 memory.put((((StatContext)_localctx).ID!=null?((StatContext)_localctx).ID.getText():null), ((StatContext)_localctx).expr.value); 
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(25); match(NEWLINE);
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

	public static class ExprContext extends ParserRuleContext {
		public Value value;
		public TermContext e;
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Ex2Listener ) ((Ex2Listener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Ex2Listener ) ((Ex2Listener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28); ((ExprContext)_localctx).e = term();
			((ExprContext)_localctx).value =  ((ExprContext)_localctx).e.value;
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==2 || _la==3) {
				{
				setState(38);
				switch (_input.LA(1)) {
				case 2:
					{
					setState(30); match(2);
					setState(31); ((ExprContext)_localctx).e = term();

					    			if (_localctx.value.type == "int") {
					    				if (((ExprContext)_localctx).e.value.type == "int"){
					    					((ExprContext)_localctx).value =  new Value(_localctx.value.intValue + ((ExprContext)_localctx).e.value.intValue);
					    				}else {
					    					((ExprContext)_localctx).value =  new Value(_localctx.value.intValue + ((ExprContext)_localctx).e.value.floatValue);
					    				}
					    	 		} else {
					    	 			if (((ExprContext)_localctx).e.value.type == "int"){
					    					((ExprContext)_localctx).value =  new Value(_localctx.value.floatValue + ((ExprContext)_localctx).e.value.intValue);
					    				}else {
					    					((ExprContext)_localctx).value =  new Value(_localctx.value.floatValue + ((ExprContext)_localctx).e.value.floatValue);
					    				}
					    			}
					    		
					}
					break;
				case 3:
					{
					setState(34); match(3);
					setState(35); ((ExprContext)_localctx).e = term();

					      			if (_localctx.value.type == "int") {
					    				if (((ExprContext)_localctx).e.value.type == "int"){
					    					((ExprContext)_localctx).value =  new Value(_localctx.value.intValue - ((ExprContext)_localctx).e.value.intValue);
					    				}else {
					    					((ExprContext)_localctx).value =  new Value(_localctx.value.intValue - ((ExprContext)_localctx).e.value.floatValue);
					    				}
					    	 		} else {
					    	 			if (((ExprContext)_localctx).e.value.type == "int"){
					    					((ExprContext)_localctx).value =  new Value(_localctx.value.floatValue - ((ExprContext)_localctx).e.value.intValue);
					    				}else {
					    					((ExprContext)_localctx).value =  new Value(_localctx.value.floatValue - ((ExprContext)_localctx).e.value.floatValue);
					    				}
					    			}
					      			
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(42);
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

	public static class TermContext extends ParserRuleContext {
		public Value value;
		public FactContext e;
		public List<FactContext> fact() {
			return getRuleContexts(FactContext.class);
		}
		public FactContext fact(int i) {
			return getRuleContext(FactContext.class,i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Ex2Listener ) ((Ex2Listener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Ex2Listener ) ((Ex2Listener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43); ((TermContext)_localctx).e = fact();
			((TermContext)_localctx).value =  ((TermContext)_localctx).e.value; 
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4 || _la==6) {
				{
				setState(53);
				switch (_input.LA(1)) {
				case 4:
					{
					setState(45); match(4);
					setState(46); ((TermContext)_localctx).e = fact();

					    			if (_localctx.value.type == "int") {
					    				if (((TermContext)_localctx).e.value.type == "int"){
					    					((TermContext)_localctx).value =  new Value(_localctx.value.intValue * ((TermContext)_localctx).e.value.intValue);
					    				}else {
					    					((TermContext)_localctx).value =  new Value(_localctx.value.intValue * ((TermContext)_localctx).e.value.floatValue);
					    				}
					    	 		} else {
					    	 			if (((TermContext)_localctx).e.value.type == "int"){
					    					((TermContext)_localctx).value =  new Value(_localctx.value.floatValue * ((TermContext)_localctx).e.value.intValue);
					    				}else {
					    					((TermContext)_localctx).value =  new Value(_localctx.value.floatValue * ((TermContext)_localctx).e.value.floatValue);
					    				}
					    			}
					    		
					}
					break;
				case 6:
					{
					setState(49); match(6);
					setState(50); ((TermContext)_localctx).e = fact();

					      			if (_localctx.value.type == "int") {
					    				if (((TermContext)_localctx).e.value.type == "int"){
					    					((TermContext)_localctx).value =  new Value(_localctx.value.intValue / ((TermContext)_localctx).e.value.intValue);
					    				}else {
					    					((TermContext)_localctx).value =  new Value(_localctx.value.intValue / ((TermContext)_localctx).e.value.floatValue);
					    				}
					    	 		} else {
					    	 			if (((TermContext)_localctx).e.value.type == "int"){
					    					((TermContext)_localctx).value =  new Value(_localctx.value.floatValue / ((TermContext)_localctx).e.value.intValue);
					    				}else {
					    					((TermContext)_localctx).value =  new Value(_localctx.value.floatValue / ((TermContext)_localctx).e.value.floatValue);
					    				}
					    			}
					    		
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(57);
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

	public static class FactContext extends ParserRuleContext {
		public Value value;
		public Token INT;
		public Token FLOAT;
		public Token ID;
		public ExprContext expr;
		public TerminalNode FLOAT() { return getToken(Ex2Parser.FLOAT, 0); }
		public TerminalNode INT() { return getToken(Ex2Parser.INT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(Ex2Parser.ID, 0); }
		public FactContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fact; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Ex2Listener ) ((Ex2Listener)listener).enterFact(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Ex2Listener ) ((Ex2Listener)listener).exitFact(this);
		}
	}

	public final FactContext fact() throws RecognitionException {
		FactContext _localctx = new FactContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fact);
		try {
			setState(69);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(58); ((FactContext)_localctx).INT = match(INT);
				((FactContext)_localctx).value =  new Value(Integer.parseInt((((FactContext)_localctx).INT!=null?((FactContext)_localctx).INT.getText():null)));
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(60); ((FactContext)_localctx).FLOAT = match(FLOAT);
				((FactContext)_localctx).value =  new Value(Float.parseFloat((((FactContext)_localctx).FLOAT!=null?((FactContext)_localctx).FLOAT.getText():null)));
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(62); ((FactContext)_localctx).ID = match(ID);

				            Value v = (Value)memory.get((((FactContext)_localctx).ID!=null?((FactContext)_localctx).ID.getText():null));
				            if ( v!=null ) ((FactContext)_localctx).value =  v;
				            else System.err.println("undefined variable "+(((FactContext)_localctx).ID!=null?((FactContext)_localctx).ID.getText():null));
				        
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 4);
				{
				setState(64); match(5);
				setState(65); ((FactContext)_localctx).expr = expr();
				setState(66); match(1);
				((FactContext)_localctx).value =  ((FactContext)_localctx).expr.value;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\16J\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\6\2\16\n\2\r\2\16\2\17\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\35\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\7\4)\n\4\f\4\16\4,\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\7\58\n\5\f\5\16\5;\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\5\6H\n\6\3\6\2\7\2\4\6\b\n\2\2N\2\r\3\2\2\2\4\34\3\2\2\2\6\36\3\2\2"+
		"\2\b-\3\2\2\2\nG\3\2\2\2\f\16\5\4\3\2\r\f\3\2\2\2\16\17\3\2\2\2\17\r\3"+
		"\2\2\2\17\20\3\2\2\2\20\3\3\2\2\2\21\22\5\6\4\2\22\23\7\r\2\2\23\24\b"+
		"\3\1\2\24\35\3\2\2\2\25\26\7\n\2\2\26\27\7\t\2\2\27\30\5\6\4\2\30\31\7"+
		"\r\2\2\31\32\b\3\1\2\32\35\3\2\2\2\33\35\7\r\2\2\34\21\3\2\2\2\34\25\3"+
		"\2\2\2\34\33\3\2\2\2\35\5\3\2\2\2\36\37\5\b\5\2\37*\b\4\1\2 !\7\4\2\2"+
		"!\"\5\b\5\2\"#\b\4\1\2#)\3\2\2\2$%\7\5\2\2%&\5\b\5\2&\'\b\4\1\2\')\3\2"+
		"\2\2( \3\2\2\2($\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\7\3\2\2\2,*\3"+
		"\2\2\2-.\5\n\6\2.9\b\5\1\2/\60\7\6\2\2\60\61\5\n\6\2\61\62\b\5\1\2\62"+
		"8\3\2\2\2\63\64\7\b\2\2\64\65\5\n\6\2\65\66\b\5\1\2\668\3\2\2\2\67/\3"+
		"\2\2\2\67\63\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:\t\3\2\2\2;9\3\2"+
		"\2\2<=\7\13\2\2=H\b\6\1\2>?\7\f\2\2?H\b\6\1\2@A\7\n\2\2AH\b\6\1\2BC\7"+
		"\7\2\2CD\5\6\4\2DE\7\3\2\2EF\b\6\1\2FH\3\2\2\2G<\3\2\2\2G>\3\2\2\2G@\3"+
		"\2\2\2GB\3\2\2\2H\13\3\2\2\2\t\17\34(*\679G";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}