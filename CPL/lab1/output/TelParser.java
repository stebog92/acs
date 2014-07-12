// $ANTLR 3.4 /home/mihai/CPL/lab1/Tel.g 2013-10-13 21:19:13

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.debug.*;
import java.io.IOException;
@SuppressWarnings({"all", "warnings", "unchecked"})
public class TelParser extends DebugParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "VARIABLE", "'*'", "'+'", "'-'", "'='"
    };

    public static final int EOF=-1;
    public static final int T__5=5;
    public static final int T__6=6;
    public static final int T__7=7;
    public static final int T__8=8;
    public static final int VARIABLE=4;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


public static final String[] ruleNames = new String[] {
    "invalidRule", "prod", "expr", "mult", "stat"
};

public static final boolean[] decisionCanBacktrack = new boolean[] {
    false, // invalid decision
    false, false, false
};

 
    public int ruleLevel = 0;
    public int getRuleLevel() { return ruleLevel; }
    public void incRuleLevel() { ruleLevel++; }
    public void decRuleLevel() { ruleLevel--; }
    public TelParser(TokenStream input) {
        this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT, new RecognizerSharedState());
    }
    public TelParser(TokenStream input, int port, RecognizerSharedState state) {
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

public TelParser(TokenStream input, DebugEventListener dbg) {
    super(input, dbg, new RecognizerSharedState());
}

protected boolean evalPredicate(boolean result, String predicate) {
    dbg.semanticPredicate(result, predicate);
    return result;
}

    public String[] getTokenNames() { return TelParser.tokenNames; }
    public String getGrammarFileName() { return "/home/mihai/CPL/lab1/Tel.g"; }



    // $ANTLR start "stat"
    // /home/mihai/CPL/lab1/Tel.g:3:1: stat : VARIABLE '=' expr ;
    public final void stat() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "stat");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(3, 0);

        try {
            // /home/mihai/CPL/lab1/Tel.g:3:6: ( VARIABLE '=' expr )
            dbg.enterAlt(1);

            // /home/mihai/CPL/lab1/Tel.g:3:8: VARIABLE '=' expr
            {
            dbg.location(3,8);
            match(input,VARIABLE,FOLLOW_VARIABLE_in_stat10); 
            dbg.location(3,17);
            match(input,8,FOLLOW_8_in_stat12); 
            dbg.location(3,20);
            pushFollow(FOLLOW_expr_in_stat13);
            expr();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        dbg.location(3, 23);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "stat");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "stat"



    // $ANTLR start "expr"
    // /home/mihai/CPL/lab1/Tel.g:4:1: expr : mult ( '+' mult | '-' mult )* ;
    public final void expr() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "expr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(4, 0);

        try {
            // /home/mihai/CPL/lab1/Tel.g:4:6: ( mult ( '+' mult | '-' mult )* )
            dbg.enterAlt(1);

            // /home/mihai/CPL/lab1/Tel.g:4:9: mult ( '+' mult | '-' mult )*
            {
            dbg.location(4,9);
            pushFollow(FOLLOW_mult_in_expr21);
            mult();

            state._fsp--;

            dbg.location(4,13);
            // /home/mihai/CPL/lab1/Tel.g:4:13: ( '+' mult | '-' mult )*
            try { dbg.enterSubRule(1);

            loop1:
            do {
                int alt1=3;
                try { dbg.enterDecision(1, decisionCanBacktrack[1]);

                int LA1_0 = input.LA(1);

                if ( (LA1_0==6) ) {
                    alt1=1;
                }
                else if ( (LA1_0==7) ) {
                    alt1=2;
                }


                } finally {dbg.exitDecision(1);}

                switch (alt1) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /home/mihai/CPL/lab1/Tel.g:4:14: '+' mult
            	    {
            	    dbg.location(4,14);
            	    match(input,6,FOLLOW_6_in_expr23); 
            	    dbg.location(4,17);
            	    pushFollow(FOLLOW_mult_in_expr24);
            	    mult();

            	    state._fsp--;


            	    }
            	    break;
            	case 2 :
            	    dbg.enterAlt(2);

            	    // /home/mihai/CPL/lab1/Tel.g:4:22: '-' mult
            	    {
            	    dbg.location(4,22);
            	    match(input,7,FOLLOW_7_in_expr26); 
            	    dbg.location(4,25);
            	    pushFollow(FOLLOW_mult_in_expr27);
            	    mult();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);
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
        dbg.location(4, 30);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "expr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "expr"



    // $ANTLR start "mult"
    // /home/mihai/CPL/lab1/Tel.g:5:1: mult : VARIABLE ( '*' VARIABLE )* ;
    public final void mult() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "mult");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(5, 0);

        try {
            // /home/mihai/CPL/lab1/Tel.g:5:6: ( VARIABLE ( '*' VARIABLE )* )
            dbg.enterAlt(1);

            // /home/mihai/CPL/lab1/Tel.g:5:8: VARIABLE ( '*' VARIABLE )*
            {
            dbg.location(5,8);
            match(input,VARIABLE,FOLLOW_VARIABLE_in_mult36); 
            dbg.location(5,17);
            // /home/mihai/CPL/lab1/Tel.g:5:17: ( '*' VARIABLE )*
            try { dbg.enterSubRule(2);

            loop2:
            do {
                int alt2=2;
                try { dbg.enterDecision(2, decisionCanBacktrack[2]);

                int LA2_0 = input.LA(1);

                if ( (LA2_0==5) ) {
                    alt2=1;
                }


                } finally {dbg.exitDecision(2);}

                switch (alt2) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /home/mihai/CPL/lab1/Tel.g:5:18: '*' VARIABLE
            	    {
            	    dbg.location(5,18);
            	    match(input,5,FOLLOW_5_in_mult39); 
            	    dbg.location(5,22);
            	    match(input,VARIABLE,FOLLOW_VARIABLE_in_mult41); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);
            } finally {dbg.exitSubRule(2);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        dbg.location(5, 31);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "mult");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "mult"



    // $ANTLR start "prod"
    // /home/mihai/CPL/lab1/Tel.g:6:1: prod : ( VARIABLE | ( VARIABLE '*' ) );
    public final void prod() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "prod");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(6, 0);

        try {
            // /home/mihai/CPL/lab1/Tel.g:6:6: ( VARIABLE | ( VARIABLE '*' ) )
            int alt3=2;
            try { dbg.enterDecision(3, decisionCanBacktrack[3]);

            int LA3_0 = input.LA(1);

            if ( (LA3_0==VARIABLE) ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==5) ) {
                    alt3=2;
                }
                else if ( (LA3_1==EOF) ) {
                    alt3=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;

            }
            } finally {dbg.exitDecision(3);}

            switch (alt3) {
                case 1 :
                    dbg.enterAlt(1);

                    // /home/mihai/CPL/lab1/Tel.g:6:8: VARIABLE
                    {
                    dbg.location(6,8);
                    match(input,VARIABLE,FOLLOW_VARIABLE_in_prod50); 

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /home/mihai/CPL/lab1/Tel.g:7:4: ( VARIABLE '*' )
                    {
                    dbg.location(7,4);
                    // /home/mihai/CPL/lab1/Tel.g:7:4: ( VARIABLE '*' )
                    dbg.enterAlt(1);

                    // /home/mihai/CPL/lab1/Tel.g:7:5: VARIABLE '*'
                    {
                    dbg.location(7,5);
                    match(input,VARIABLE,FOLLOW_VARIABLE_in_prod56); 
                    dbg.location(7,13);
                    match(input,5,FOLLOW_5_in_prod57); 

                    }


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
        dbg.location(7, 16);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "prod");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "prod"

    // Delegated rules


 

    public static final BitSet FOLLOW_VARIABLE_in_stat10 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_stat12 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_expr_in_stat13 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mult_in_expr21 = new BitSet(new long[]{0x00000000000000C2L});
    public static final BitSet FOLLOW_6_in_expr23 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_mult_in_expr24 = new BitSet(new long[]{0x00000000000000C2L});
    public static final BitSet FOLLOW_7_in_expr26 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_mult_in_expr27 = new BitSet(new long[]{0x00000000000000C2L});
    public static final BitSet FOLLOW_VARIABLE_in_mult36 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_5_in_mult39 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_VARIABLE_in_mult41 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_VARIABLE_in_prod50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARIABLE_in_prod56 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_5_in_prod57 = new BitSet(new long[]{0x0000000000000002L});

}