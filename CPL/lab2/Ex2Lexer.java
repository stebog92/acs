// Generated from Ex2.g by ANTLR 4.1

        import java.util.HashMap;
    
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Ex2Lexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__6=1, T__5=2, T__4=3, T__3=4, T__2=5, T__1=6, T__0=7, ID=8, INT=9, FLOAT=10, 
		NEWLINE=11, WS=12;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"')'", "'+'", "'-'", "'*'", "'('", "'/'", "'='", "ID", "INT", "FLOAT", 
		"NEWLINE", "WS"
	};
	public static final String[] ruleNames = {
		"T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "ID", "INT", "FLOAT", 
		"NEWLINE", "WS"
	};


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
	    

	public Ex2Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Ex2.g"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 11: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip(); break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\16J\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3"+
		"\b\3\b\3\t\6\t+\n\t\r\t\16\t,\3\n\6\n\60\n\n\r\n\16\n\61\3\13\6\13\65"+
		"\n\13\r\13\16\13\66\3\13\3\13\6\13;\n\13\r\13\16\13<\3\f\5\f@\n\f\3\f"+
		"\3\f\3\r\6\rE\n\r\r\r\16\rF\3\r\3\r\2\16\3\3\1\5\4\1\7\5\1\t\6\1\13\7"+
		"\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\2\3\2\4\4\2C\\c|\4"+
		"\2\13\13\"\"O\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\3\33\3\2\2\2\5\35\3\2\2\2\7\37\3\2\2\2\t!\3\2"+
		"\2\2\13#\3\2\2\2\r%\3\2\2\2\17\'\3\2\2\2\21*\3\2\2\2\23/\3\2\2\2\25\64"+
		"\3\2\2\2\27?\3\2\2\2\31D\3\2\2\2\33\34\7+\2\2\34\4\3\2\2\2\35\36\7-\2"+
		"\2\36\6\3\2\2\2\37 \7/\2\2 \b\3\2\2\2!\"\7,\2\2\"\n\3\2\2\2#$\7*\2\2$"+
		"\f\3\2\2\2%&\7\61\2\2&\16\3\2\2\2\'(\7?\2\2(\20\3\2\2\2)+\t\2\2\2*)\3"+
		"\2\2\2+,\3\2\2\2,*\3\2\2\2,-\3\2\2\2-\22\3\2\2\2.\60\4\62;\2/.\3\2\2\2"+
		"\60\61\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\24\3\2\2\2\63\65\4\62;\2\64"+
		"\63\3\2\2\2\65\66\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\678\3\2\2\28:\7"+
		"\60\2\29;\4\62;\2:9\3\2\2\2;<\3\2\2\2<:\3\2\2\2<=\3\2\2\2=\26\3\2\2\2"+
		">@\7\17\2\2?>\3\2\2\2?@\3\2\2\2@A\3\2\2\2AB\7\f\2\2B\30\3\2\2\2CE\t\3"+
		"\2\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2FG\3\2\2\2GH\3\2\2\2HI\b\r\2\2I\32\3"+
		"\2\2\2\t\2,\61\66<?F";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}