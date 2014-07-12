// $ANTLR 3.5 /home/mihai/CPL/lab2/Ex2.g 2013-11-03 10:41:59

        import java.util.HashMap;
    

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.debug.*;
import java.io.IOException;
@SuppressWarnings("all")
public class Ex2Parser extends DebugParser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "FLOAT", "ID", "INT", "NEWLINE", 
		"WS", "'('", "')'", "'*'", "'+'", "'-'", "'/'", "'='"
	};
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
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public static final String[] ruleNames = new String[] {
		"invalidRule", "prog", "term", "expr", "stat", "fact"
	};

	public static final boolean[] decisionCanBacktrack = new boolean[] {
		false, // invalid decision
		false, false, false, false, false
	};

 
	public int ruleLevel = 0;
	public int getRuleLevel() { return ruleLevel; }
	public void incRuleLevel() { ruleLevel++; }
	public void decRuleLevel() { ruleLevel--; }
	public Ex2Parser(TokenStream input) {
		this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT, new RecognizerSharedState());
	}
	public Ex2Parser(TokenStream input, int port, RecognizerSharedState state) {
		super(input, state);
		DebugEventSocketProxy proxy =
			new DebugEventSocketProxy(this, port, null);

		setDebugListener(proxy);
		try {
			proxy.handshake();
		}
		catch (IOException ioe) {
			reportError(ioe);
		}
	}

	public Ex2Parser(TokenStream input, DebugEventListener dbg) {
		super(input, dbg, new RecognizerSharedState());
	}

	protected boolean evalPredicate(boolean result, String predicate) {
		dbg.semanticPredicate(result, predicate);
		return result;
	}

	@Override public String[] getTokenNames() { return Ex2Parser.tokenNames; }
	@Override public String getGrammarFileName() { return "/home/mihai/CPL/lab2/Ex2.g"; }


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
	    


	// $ANTLR start "prog"
	// /home/mihai/CPL/lab2/Ex2.g:21:1: prog : ( stat )+ ;
	public final void prog() throws  {
		try { dbg.enterRule(getGrammarFileName(), "prog");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(21, 0);

		try {
			// /home/mihai/CPL/lab2/Ex2.g:21:5: ( ( stat )+ )
			dbg.enterAlt(1);

			// /home/mihai/CPL/lab2/Ex2.g:21:7: ( stat )+
			{
			dbg.location(21,7);
			// /home/mihai/CPL/lab2/Ex2.g:21:7: ( stat )+
			int cnt1=0;
			try { dbg.enterSubRule(1);

			loop1:
			while (true) {
				int alt1=2;
				try { dbg.enterDecision(1, decisionCanBacktrack[1]);

				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= FLOAT && LA1_0 <= NEWLINE)||LA1_0==9) ) {
					alt1=1;
				}

				} finally {dbg.exitDecision(1);}

				switch (alt1) {
				case 1 :
					dbg.enterAlt(1);

					// /home/mihai/CPL/lab2/Ex2.g:21:7: stat
					{
					dbg.location(21,7);
					pushFollow(FOLLOW_stat_in_prog18);
					stat();
					state._fsp--;

					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					dbg.recognitionException(eee);

					throw eee;
				}
				cnt1++;
			}
			} finally {dbg.exitSubRule(1);}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(21, 12);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "prog");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

	}
	// $ANTLR end "prog"



	// $ANTLR start "stat"
	// /home/mihai/CPL/lab2/Ex2.g:23:1: stat : ( expr NEWLINE | ID '=' expr NEWLINE | NEWLINE );
	public final void stat() throws  {
		Token ID2=null;
		Value expr1 =null;
		Value expr3 =null;

		try { dbg.enterRule(getGrammarFileName(), "stat");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(23, 0);

		try {
			// /home/mihai/CPL/lab2/Ex2.g:24:5: ( expr NEWLINE | ID '=' expr NEWLINE | NEWLINE )
			int alt2=3;
			try { dbg.enterDecision(2, decisionCanBacktrack[2]);

			switch ( input.LA(1) ) {
			case FLOAT:
			case INT:
			case 9:
				{
				alt2=1;
				}
				break;
			case ID:
				{
				int LA2_2 = input.LA(2);
				if ( (LA2_2==15) ) {
					alt2=2;
				}
				else if ( (LA2_2==NEWLINE||(LA2_2 >= 11 && LA2_2 <= 14)) ) {
					alt2=1;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 2, 2, input);
						dbg.recognitionException(nvae);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case NEWLINE:
				{
				alt2=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				dbg.recognitionException(nvae);
				throw nvae;
			}
			} finally {dbg.exitDecision(2);}

			switch (alt2) {
				case 1 :
					dbg.enterAlt(1);

					// /home/mihai/CPL/lab2/Ex2.g:24:7: expr NEWLINE
					{
					dbg.location(24,7);
					pushFollow(FOLLOW_expr_in_stat32);
					expr1=expr();
					state._fsp--;
					dbg.location(24,12);
					match(input,NEWLINE,FOLLOW_NEWLINE_in_stat34); dbg.location(24,20);
					System.out.println(expr1.type == "int"? expr1.intValue : expr1.floatValue);
					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /home/mihai/CPL/lab2/Ex2.g:25:7: ID '=' expr NEWLINE
					{
					dbg.location(25,7);
					ID2=(Token)match(input,ID,FOLLOW_ID_in_stat44); dbg.location(25,10);
					match(input,15,FOLLOW_15_in_stat46); dbg.location(25,14);
					pushFollow(FOLLOW_expr_in_stat48);
					expr3=expr();
					state._fsp--;
					dbg.location(25,19);
					match(input,NEWLINE,FOLLOW_NEWLINE_in_stat50); dbg.location(26,9);
					 memory.put((ID2!=null?ID2.getText():null), expr3); 
					}
					break;
				case 3 :
					dbg.enterAlt(3);

					// /home/mihai/CPL/lab2/Ex2.g:27:7: NEWLINE
					{
					dbg.location(27,7);
					match(input,NEWLINE,FOLLOW_NEWLINE_in_stat68); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(28, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "stat");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

	}
	// $ANTLR end "stat"



	// $ANTLR start "expr"
	// /home/mihai/CPL/lab2/Ex2.g:30:1: expr returns [Value value] : e= term ( '+' e= term | '-' e= term )* ;
	public final Value expr() throws  {
		Value value = null;


		Value e =null;

		try { dbg.enterRule(getGrammarFileName(), "expr");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(30, 0);

		try {
			// /home/mihai/CPL/lab2/Ex2.g:31:5: (e= term ( '+' e= term | '-' e= term )* )
			dbg.enterAlt(1);

			// /home/mihai/CPL/lab2/Ex2.g:31:7: e= term ( '+' e= term | '-' e= term )*
			{
			dbg.location(31,8);
			pushFollow(FOLLOW_term_in_expr91);
			e=term();
			state._fsp--;
			dbg.location(31,14);
			value = e;dbg.location(32,5);
			// /home/mihai/CPL/lab2/Ex2.g:32:5: ( '+' e= term | '-' e= term )*
			try { dbg.enterSubRule(3);

			loop3:
			while (true) {
				int alt3=3;
				try { dbg.enterDecision(3, decisionCanBacktrack[3]);

				int LA3_0 = input.LA(1);
				if ( (LA3_0==12) ) {
					alt3=1;
				}
				else if ( (LA3_0==13) ) {
					alt3=2;
				}

				} finally {dbg.exitDecision(3);}

				switch (alt3) {
				case 1 :
					dbg.enterAlt(1);

					// /home/mihai/CPL/lab2/Ex2.g:32:7: '+' e= term
					{
					dbg.location(32,7);
					match(input,12,FOLLOW_12_in_expr101); dbg.location(32,12);
					pushFollow(FOLLOW_term_in_expr105);
					e=term();
					state._fsp--;
					dbg.location(32,18);

					    			if (value.type == "int") {
					    				if (e.type == "int"){
					    					value = new Value(value.intValue + e.intValue);
					    				}else {
					    					value = new Value(value.intValue + e.floatValue);
					    				}
					    	 		} else {
					    	 			if (e.type == "int"){
					    					value = new Value(value.floatValue + e.intValue);
					    				}else {
					    					value = new Value(value.floatValue + e.floatValue);
					    				}
					    			}
					    		
					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /home/mihai/CPL/lab2/Ex2.g:47:9: '-' e= term
					{
					dbg.location(47,9);
					match(input,13,FOLLOW_13_in_expr117); dbg.location(47,14);
					pushFollow(FOLLOW_term_in_expr121);
					e=term();
					state._fsp--;
					dbg.location(47,20);

					      			if (value.type == "int") {
					    				if (e.type == "int"){
					    					value = new Value(value.intValue - e.intValue);
					    				}else {
					    					value = new Value(value.intValue - e.floatValue);
					    				}
					    	 		} else {
					    	 			if (e.type == "int"){
					    					value = new Value(value.floatValue - e.intValue);
					    				}else {
					    					value = new Value(value.floatValue - e.floatValue);
					    				}
					    			}
					      			
					}
					break;

				default :
					break loop3;
				}
			}
			} finally {dbg.exitSubRule(3);}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(62, 6);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "expr");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return value;
	}
	// $ANTLR end "expr"



	// $ANTLR start "term"
	// /home/mihai/CPL/lab2/Ex2.g:64:1: term returns [Value value] : e= fact ( '*' e= fact | '/' e= fact )* ;
	public final Value term() throws  {
		Value value = null;


		Value e =null;

		try { dbg.enterRule(getGrammarFileName(), "term");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(64, 0);

		try {
			// /home/mihai/CPL/lab2/Ex2.g:65:5: (e= fact ( '*' e= fact | '/' e= fact )* )
			dbg.enterAlt(1);

			// /home/mihai/CPL/lab2/Ex2.g:65:7: e= fact ( '*' e= fact | '/' e= fact )*
			{
			dbg.location(65,8);
			pushFollow(FOLLOW_fact_in_term148);
			e=fact();
			state._fsp--;
			dbg.location(65,14);
			value = e; dbg.location(66,5);
			// /home/mihai/CPL/lab2/Ex2.g:66:5: ( '*' e= fact | '/' e= fact )*
			try { dbg.enterSubRule(4);

			loop4:
			while (true) {
				int alt4=3;
				try { dbg.enterDecision(4, decisionCanBacktrack[4]);

				int LA4_0 = input.LA(1);
				if ( (LA4_0==11) ) {
					alt4=1;
				}
				else if ( (LA4_0==14) ) {
					alt4=2;
				}

				} finally {dbg.exitDecision(4);}

				switch (alt4) {
				case 1 :
					dbg.enterAlt(1);

					// /home/mihai/CPL/lab2/Ex2.g:66:7: '*' e= fact
					{
					dbg.location(66,7);
					match(input,11,FOLLOW_11_in_term158); dbg.location(66,12);
					pushFollow(FOLLOW_fact_in_term162);
					e=fact();
					state._fsp--;
					dbg.location(66,18);

					    			if (value.type == "int") {
					    				if (e.type == "int"){
					    					value = new Value(value.intValue * e.intValue);
					    				}else {
					    					value = new Value(value.intValue * e.floatValue);
					    				}
					    	 		} else {
					    	 			if (e.type == "int"){
					    					value = new Value(value.floatValue * e.intValue);
					    				}else {
					    					value = new Value(value.floatValue * e.floatValue);
					    				}
					    			}
					    		
					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /home/mihai/CPL/lab2/Ex2.g:81:9: '/' e= fact
					{
					dbg.location(81,9);
					match(input,14,FOLLOW_14_in_term174); dbg.location(81,14);
					pushFollow(FOLLOW_fact_in_term178);
					e=fact();
					state._fsp--;
					dbg.location(81,20);

					      			if (value.type == "int") {
					    				if (e.type == "int"){
					    					value = new Value(value.intValue / e.intValue);
					    				}else {
					    					value = new Value(value.intValue / e.floatValue);
					    				}
					    	 		} else {
					    	 			if (e.type == "int"){
					    					value = new Value(value.floatValue / e.intValue);
					    				}else {
					    					value = new Value(value.floatValue / e.floatValue);
					    				}
					    			}
					    		
					}
					break;

				default :
					break loop4;
				}
			}
			} finally {dbg.exitSubRule(4);}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(96, 6);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "term");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return value;
	}
	// $ANTLR end "term"



	// $ANTLR start "fact"
	// /home/mihai/CPL/lab2/Ex2.g:98:1: fact returns [Value value] : ( INT | FLOAT | ID | '(' expr ')' );
	public final Value fact() throws  {
		Value value = null;


		Token INT4=null;
		Token FLOAT5=null;
		Token ID6=null;
		Value expr7 =null;

		try { dbg.enterRule(getGrammarFileName(), "fact");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(98, 0);

		try {
			// /home/mihai/CPL/lab2/Ex2.g:99:5: ( INT | FLOAT | ID | '(' expr ')' )
			int alt5=4;
			try { dbg.enterDecision(5, decisionCanBacktrack[5]);

			switch ( input.LA(1) ) {
			case INT:
				{
				alt5=1;
				}
				break;
			case FLOAT:
				{
				alt5=2;
				}
				break;
			case ID:
				{
				alt5=3;
				}
				break;
			case 9:
				{
				alt5=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				dbg.recognitionException(nvae);
				throw nvae;
			}
			} finally {dbg.exitDecision(5);}

			switch (alt5) {
				case 1 :
					dbg.enterAlt(1);

					// /home/mihai/CPL/lab2/Ex2.g:99:7: INT
					{
					dbg.location(99,7);
					INT4=(Token)match(input,INT,FOLLOW_INT_in_fact203); dbg.location(99,11);
					value = new Value(Integer.parseInt((INT4!=null?INT4.getText():null)));
					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /home/mihai/CPL/lab2/Ex2.g:100:7: FLOAT
					{
					dbg.location(100,7);
					FLOAT5=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_fact213); dbg.location(100,13);
					value = new Value(Float.parseFloat((FLOAT5!=null?FLOAT5.getText():null)));
					}
					break;
				case 3 :
					dbg.enterAlt(3);

					// /home/mihai/CPL/lab2/Ex2.g:101:7: ID
					{
					dbg.location(101,7);
					ID6=(Token)match(input,ID,FOLLOW_ID_in_fact223); dbg.location(102,9);

					            Value v = (Value)memory.get((ID6!=null?ID6.getText():null));
					            if ( v!=null ) value = v;
					            else System.err.println("undefined variable "+(ID6!=null?ID6.getText():null));
					        
					}
					break;
				case 4 :
					dbg.enterAlt(4);

					// /home/mihai/CPL/lab2/Ex2.g:107:7: '(' expr ')'
					{
					dbg.location(107,7);
					match(input,9,FOLLOW_9_in_fact241); dbg.location(107,11);
					pushFollow(FOLLOW_expr_in_fact243);
					expr7=expr();
					state._fsp--;
					dbg.location(107,16);
					match(input,10,FOLLOW_10_in_fact245); dbg.location(107,20);
					value = expr7;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(108, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "fact");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return value;
	}
	// $ANTLR end "fact"

	// Delegated rules



	public static final BitSet FOLLOW_stat_in_prog18 = new BitSet(new long[]{0x00000000000002F2L});
	public static final BitSet FOLLOW_expr_in_stat32 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_NEWLINE_in_stat34 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_stat44 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_stat46 = new BitSet(new long[]{0x0000000000000270L});
	public static final BitSet FOLLOW_expr_in_stat48 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_NEWLINE_in_stat50 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEWLINE_in_stat68 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_term_in_expr91 = new BitSet(new long[]{0x0000000000003002L});
	public static final BitSet FOLLOW_12_in_expr101 = new BitSet(new long[]{0x0000000000000270L});
	public static final BitSet FOLLOW_term_in_expr105 = new BitSet(new long[]{0x0000000000003002L});
	public static final BitSet FOLLOW_13_in_expr117 = new BitSet(new long[]{0x0000000000000270L});
	public static final BitSet FOLLOW_term_in_expr121 = new BitSet(new long[]{0x0000000000003002L});
	public static final BitSet FOLLOW_fact_in_term148 = new BitSet(new long[]{0x0000000000004802L});
	public static final BitSet FOLLOW_11_in_term158 = new BitSet(new long[]{0x0000000000000270L});
	public static final BitSet FOLLOW_fact_in_term162 = new BitSet(new long[]{0x0000000000004802L});
	public static final BitSet FOLLOW_14_in_term174 = new BitSet(new long[]{0x0000000000000270L});
	public static final BitSet FOLLOW_fact_in_term178 = new BitSet(new long[]{0x0000000000004802L});
	public static final BitSet FOLLOW_INT_in_fact203 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FLOAT_in_fact213 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_fact223 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_fact241 = new BitSet(new long[]{0x0000000000000270L});
	public static final BitSet FOLLOW_expr_in_fact243 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_fact245 = new BitSet(new long[]{0x0000000000000002L});
}
