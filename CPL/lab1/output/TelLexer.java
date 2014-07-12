// $ANTLR 3.4 /home/mihai/CPL/lab1/Tel.g 2013-10-13 21:19:13

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class TelLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__5=5;
    public static final int T__6=6;
    public static final int T__7=7;
    public static final int T__8=8;
    public static final int VARIABLE=4;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public TelLexer() {} 
    public TelLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public TelLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "/home/mihai/CPL/lab1/Tel.g"; }

    // $ANTLR start "T__5"
    public final void mT__5() throws RecognitionException {
        try {
            int _type = T__5;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/mihai/CPL/lab1/Tel.g:2:6: ( '*' )
            // /home/mihai/CPL/lab1/Tel.g:2:8: '*'
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
    // $ANTLR end "T__5"

    // $ANTLR start "T__6"
    public final void mT__6() throws RecognitionException {
        try {
            int _type = T__6;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/mihai/CPL/lab1/Tel.g:3:6: ( '+' )
            // /home/mihai/CPL/lab1/Tel.g:3:8: '+'
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
    // $ANTLR end "T__6"

    // $ANTLR start "T__7"
    public final void mT__7() throws RecognitionException {
        try {
            int _type = T__7;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/mihai/CPL/lab1/Tel.g:4:6: ( '-' )
            // /home/mihai/CPL/lab1/Tel.g:4:8: '-'
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
    // $ANTLR end "T__7"

    // $ANTLR start "T__8"
    public final void mT__8() throws RecognitionException {
        try {
            int _type = T__8;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/mihai/CPL/lab1/Tel.g:5:6: ( '=' )
            // /home/mihai/CPL/lab1/Tel.g:5:8: '='
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
    // $ANTLR end "T__8"

    // $ANTLR start "VARIABLE"
    public final void mVARIABLE() throws RecognitionException {
        try {
            int _type = VARIABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/mihai/CPL/lab1/Tel.g:9:10: ( ( 'a' .. 'z' )* )
            // /home/mihai/CPL/lab1/Tel.g:9:12: ( 'a' .. 'z' )*
            {
            // /home/mihai/CPL/lab1/Tel.g:9:12: ( 'a' .. 'z' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/mihai/CPL/lab1/Tel.g:
            	    {
            	    if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
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
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VARIABLE"

    public void mTokens() throws RecognitionException {
        // /home/mihai/CPL/lab1/Tel.g:1:8: ( T__5 | T__6 | T__7 | T__8 | VARIABLE )
        int alt2=5;
        switch ( input.LA(1) ) {
        case '*':
            {
            alt2=1;
            }
            break;
        case '+':
            {
            alt2=2;
            }
            break;
        case '-':
            {
            alt2=3;
            }
            break;
        case '=':
            {
            alt2=4;
            }
            break;
        default:
            alt2=5;
        }

        switch (alt2) {
            case 1 :
                // /home/mihai/CPL/lab1/Tel.g:1:10: T__5
                {
                mT__5(); 


                }
                break;
            case 2 :
                // /home/mihai/CPL/lab1/Tel.g:1:15: T__6
                {
                mT__6(); 


                }
                break;
            case 3 :
                // /home/mihai/CPL/lab1/Tel.g:1:20: T__7
                {
                mT__7(); 


                }
                break;
            case 4 :
                // /home/mihai/CPL/lab1/Tel.g:1:25: T__8
                {
                mT__8(); 


                }
                break;
            case 5 :
                // /home/mihai/CPL/lab1/Tel.g:1:30: VARIABLE
                {
                mVARIABLE(); 


                }
                break;

        }

    }


 

}