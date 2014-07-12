// Generated from Parens.g by ANTLR 4.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ParensParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LPAREN=1, RPAREN=2, WORD=3, WS=4;
	public static final String[] tokenNames = {
		"<INVALID>", "'('", "')'", "WORD", "WS"
	};
	public static final int
		RULE_prog = 0;
	public static final String[] ruleNames = {
		"prog"
	};

	@Override
	public String getGrammarFileName() { return "Parens.g"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public ParensParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(ParensParser.WORD, 0); }
		public TerminalNode LPAREN(int i) {
			return getToken(ParensParser.LPAREN, i);
		}
		public TerminalNode RPAREN(int i) {
			return getToken(ParensParser.RPAREN, i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(ParensParser.RPAREN); }
		public List<TerminalNode> LPAREN() { return getTokens(ParensParser.LPAREN); }
		public ProgContext prog(int i) {
			return getRuleContext(ProgContext.class,i);
		}
		public List<ProgContext> prog() {
			return getRuleContexts(ProgContext.class);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParensListener ) ((ParensListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParensListener ) ((ParensListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			setState(12);
			switch (_input.LA(1)) {
			case LPAREN:
			case RPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(8);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LPAREN) {
					{
					{
					setState(2); match(LPAREN);
					setState(3); prog();
					setState(4); match(RPAREN);
					}
					}
					setState(10);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case WORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(11); match(WORD);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\6\21\4\2\t\2\3\2"+
		"\3\2\3\2\3\2\7\2\t\n\2\f\2\16\2\f\13\2\3\2\5\2\17\n\2\3\2\2\3\2\2\2\21"+
		"\2\16\3\2\2\2\4\5\7\3\2\2\5\6\5\2\2\2\6\7\7\4\2\2\7\t\3\2\2\2\b\4\3\2"+
		"\2\2\t\f\3\2\2\2\n\b\3\2\2\2\n\13\3\2\2\2\13\17\3\2\2\2\f\n\3\2\2\2\r"+
		"\17\7\5\2\2\16\n\3\2\2\2\16\r\3\2\2\2\17\3\3\2\2\2\4\n\16";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}