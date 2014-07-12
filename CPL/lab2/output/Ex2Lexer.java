// $ANTLR 3.5 /home/mihai/CPL/lab2/Ex2.g 2013-11-03 10:41:59

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class Ex2Lexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__9=9;
	public static final int T__10=10;
	public static final int T__11=11;
	public static final int T__12=12;
	public static final int T__13=13;
	public static final int T__14=14;
	public static final int T__15=15;
	public static final int FLOAT=4;
	public static final int ID=5;
	public static final int INT=6;
	public static final int NEWLINE=7;
	public static final int WS=8;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public Ex2Lexer() {} 
	public Ex2Lexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public Ex2Lexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "/home/mihai/CPL/lab2/Ex2.g"; }

	// $ANTLR start "T__9"
	public final void mT__9() throws RecognitionException {
		try {
			int _type = T__9;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/mihai/CPL/lab2/Ex2.g:2:6: ( '(' )
			// /home/mihai/CPL/lab2/Ex2.g:2:8: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__9"

	// $ANTLR start "T__10"
	public final void mT__10() throws RecognitionException {
		try {
			int _type = T__10;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/mihai/CPL/lab2/Ex2.g:3:7: ( ')' )
			// /home/mihai/CPL/lab2/Ex2.g:3:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__10"

	// $ANTLR start "T__11"
	public final void mT__11() throws RecognitionException {
		try {
			int _type = T__11;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/mihai/CPL/lab2/Ex2.g:4:7: ( '*' )
			// /home/mihai/CPL/lab2/Ex2.g:4:9: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__11"

	// $ANTLR start "T__12"
	public final void mT__12() throws RecognitionException {
		try {
			int _type = T__12;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/mihai/CPL/lab2/Ex2.g:5:7: ( '+' )
			// /home/mihai/CPL/lab2/Ex2.g:5:9: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__12"

	// $ANTLR start "T__13"
	public final void mT__13() throws RecognitionException {
		try {
			int _type = T__13;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/mihai/CPL/lab2/Ex2.g:6:7: ( '-' )
			// /home/mihai/CPL/lab2/Ex2.g:6:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__13"

	// $ANTLR start "T__14"
	public final void mT__14() throws RecognitionException {
		try {
			int _type = T__14;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/mihai/CPL/lab2/Ex2.g:7:7: ( '/' )
			// /home/mihai/CPL/lab2/Ex2.g:7:9: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__14"

	// $ANTLR start "T__15"
	public final void mT__15() throws RecognitionException {
		try {
			int _type = T__15;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/mihai/CPL/lab2/Ex2.g:8:7: ( '=' )
			// /home/mihai/CPL/lab2/Ex2.g:8:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__15"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/mihai/CPL/lab2/Ex2.g:110:4: ( ( 'a' .. 'z' | 'A' .. 'Z' )+ )
			// /home/mihai/CPL/lab2/Ex2.g:110:6: ( 'a' .. 'z' | 'A' .. 'Z' )+
			{
			// /home/mihai/CPL/lab2/Ex2.g:110:6: ( 'a' .. 'z' | 'A' .. 'Z' )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= 'A' && LA1_0 <= 'Z')||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /home/mihai/CPL/lab2/Ex2.g:
					{
					if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/mihai/CPL/lab2/Ex2.g:111:5: ( ( '0' .. '9' )+ )
			// /home/mihai/CPL/lab2/Ex2.g:111:7: ( '0' .. '9' )+
			{
			// /home/mihai/CPL/lab2/Ex2.g:111:7: ( '0' .. '9' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /home/mihai/CPL/lab2/Ex2.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "FLOAT"
	public final void mFLOAT() throws RecognitionException {
		try {
			int _type = FLOAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/mihai/CPL/lab2/Ex2.g:112:7: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )+ )
			// /home/mihai/CPL/lab2/Ex2.g:112:9: ( '0' .. '9' )+ '.' ( '0' .. '9' )+
			{
			// /home/mihai/CPL/lab2/Ex2.g:112:9: ( '0' .. '9' )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /home/mihai/CPL/lab2/Ex2.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			match('.'); 
			// /home/mihai/CPL/lab2/Ex2.g:112:23: ( '0' .. '9' )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// /home/mihai/CPL/lab2/Ex2.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FLOAT"

	// $ANTLR start "NEWLINE"
	public final void mNEWLINE() throws RecognitionException {
		try {
			int _type = NEWLINE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/mihai/CPL/lab2/Ex2.g:113:8: ( ( '\\r' )? '\\n' )
			// /home/mihai/CPL/lab2/Ex2.g:113:9: ( '\\r' )? '\\n'
			{
			// /home/mihai/CPL/lab2/Ex2.g:113:9: ( '\\r' )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='\r') ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// /home/mihai/CPL/lab2/Ex2.g:113:9: '\\r'
					{
					match('\r'); 
					}
					break;

			}

			match('\n'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NEWLINE"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/mihai/CPL/lab2/Ex2.g:114:4: ( ( ' ' | '\\t' )+ )
			// /home/mihai/CPL/lab2/Ex2.g:114:6: ( ' ' | '\\t' )+
			{
			// /home/mihai/CPL/lab2/Ex2.g:114:6: ( ' ' | '\\t' )+
			int cnt6=0;
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0=='\t'||LA6_0==' ') ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// /home/mihai/CPL/lab2/Ex2.g:
					{
					if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt6 >= 1 ) break loop6;
					EarlyExitException eee = new EarlyExitException(6, input);
					throw eee;
				}
				cnt6++;
			}

			skip();
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// /home/mihai/CPL/lab2/Ex2.g:1:8: ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | ID | INT | FLOAT | NEWLINE | WS )
		int alt7=12;
		alt7 = dfa7.predict(input);
		switch (alt7) {
			case 1 :
				// /home/mihai/CPL/lab2/Ex2.g:1:10: T__9
				{
				mT__9(); 

				}
				break;
			case 2 :
				// /home/mihai/CPL/lab2/Ex2.g:1:15: T__10
				{
				mT__10(); 

				}
				break;
			case 3 :
				// /home/mihai/CPL/lab2/Ex2.g:1:21: T__11
				{
				mT__11(); 

				}
				break;
			case 4 :
				// /home/mihai/CPL/lab2/Ex2.g:1:27: T__12
				{
				mT__12(); 

				}
				break;
			case 5 :
				// /home/mihai/CPL/lab2/Ex2.g:1:33: T__13
				{
				mT__13(); 

				}
				break;
			case 6 :
				// /home/mihai/CPL/lab2/Ex2.g:1:39: T__14
				{
				mT__14(); 

				}
				break;
			case 7 :
				// /home/mihai/CPL/lab2/Ex2.g:1:45: T__15
				{
				mT__15(); 

				}
				break;
			case 8 :
				// /home/mihai/CPL/lab2/Ex2.g:1:51: ID
				{
				mID(); 

				}
				break;
			case 9 :
				// /home/mihai/CPL/lab2/Ex2.g:1:54: INT
				{
				mINT(); 

				}
				break;
			case 10 :
				// /home/mihai/CPL/lab2/Ex2.g:1:58: FLOAT
				{
				mFLOAT(); 

				}
				break;
			case 11 :
				// /home/mihai/CPL/lab2/Ex2.g:1:64: NEWLINE
				{
				mNEWLINE(); 

				}
				break;
			case 12 :
				// /home/mihai/CPL/lab2/Ex2.g:1:72: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA7 dfa7 = new DFA7(this);
	static final String DFA7_eotS =
		"\11\uffff\1\14\4\uffff";
	static final String DFA7_eofS =
		"\16\uffff";
	static final String DFA7_minS =
		"\1\11\10\uffff\1\56\4\uffff";
	static final String DFA7_maxS =
		"\1\172\10\uffff\1\71\4\uffff";
	static final String DFA7_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\uffff\1\13\1\14\1\11\1\12";
	static final String DFA7_specialS =
		"\16\uffff}>";
	static final String[] DFA7_transitionS = {
			"\1\13\1\12\2\uffff\1\12\22\uffff\1\13\7\uffff\1\1\1\2\1\3\1\4\1\uffff"+
			"\1\5\1\uffff\1\6\12\11\3\uffff\1\7\3\uffff\32\10\6\uffff\32\10",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\15\1\uffff\12\11",
			"",
			"",
			"",
			""
	};

	static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
	static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
	static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
	static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
	static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
	static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
	static final short[][] DFA7_transition;

	static {
		int numStates = DFA7_transitionS.length;
		DFA7_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
		}
	}

	protected class DFA7 extends DFA {

		public DFA7(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 7;
			this.eot = DFA7_eot;
			this.eof = DFA7_eof;
			this.min = DFA7_min;
			this.max = DFA7_max;
			this.accept = DFA7_accept;
			this.special = DFA7_special;
			this.transition = DFA7_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | ID | INT | FLOAT | NEWLINE | WS );";
		}
	}

}
