// $ANTLR 3.2 Sep 23, 2009 12:02:23 src/LCPLTreeBuilder.g 2013-11-12 22:45:05

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class LCPLTreeBuilderLexer extends Lexer {
    public static final int CAST=46;
    public static final int T__68=68;
    public static final int CLASSDEFINITION=6;
    public static final int T__69=69;
    public static final int CLASS=35;
    public static final int LT=57;
    public static final int DISPATCH_ARGUMENTS=21;
    public static final int MEMBER=26;
    public static final int STAR=60;
    public static final int WHILE=49;
    public static final int DISPATCH=20;
    public static final int ATTRIBUTE=28;
    public static final int LTEQ=56;
    public static final int ARRAY_TYPE=24;
    public static final int NEW=37;
    public static final int SUB=59;
    public static final int NOT=62;
    public static final int VOID_CONSTANT=45;
    public static final int ID=44;
    public static final int EOF=-1;
    public static final int STATEMENT=39;
    public static final int IF=48;
    public static final int TYPE=32;
    public static final int ESCAPE_SEQUENCE=65;
    public static final int STRING_LITERAL=64;
    public static final int EXPR=17;
    public static final int DISPATCH_ARGS=25;
    public static final int INHERITS=36;
    public static final int SUBSTR=51;
    public static final int ATTRIBUTE_ARRAY=5;
    public static final int OBJ=16;
    public static final int LOCAL_DECL=19;
    public static final int EMPTY_METHOD=23;
    public static final int STRING_CONST=47;
    public static final int EQUAL=55;
    public static final int PLUS=58;
    public static final int DISPATCH_S=43;
    public static final int EQ=54;
    public static final int EXPRESSION=31;
    public static final int ATTR=30;
    public static final int ARRAY=52;
    public static final int INTEGER=63;
    public static final int CONSTANT_INT=7;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int SYMBOL=10;
    public static final int T__83=83;
    public static final int LINE_COMMENT=66;
    public static final int NULL=41;
    public static final int MEMBERS=27;
    public static final int UNARY_MINUS=13;
    public static final int INT=33;
    public static final int DECL_SECTION=12;
    public static final int LOCAL=42;
    public static final int T__84=84;
    public static final int PAR_EXPR=14;
    public static final int EQ_COMP=11;
    public static final int FORMAL_PARAM=8;
    public static final int T__71=71;
    public static final int WS=67;
    public static final int T__72=72;
    public static final int T__70=70;
    public static final int DECLARATION_SECTION=22;
    public static final int BLOCK=15;
    public static final int CONSTANT_STRING=9;
    public static final int PROGRAM=4;
    public static final int ASSIGNMENT=29;
    public static final int DIV=61;
    public static final int T__76=76;
    public static final int END=40;
    public static final int T__75=75;
    public static final int T__74=74;
    public static final int SELF=50;
    public static final int T__73=73;
    public static final int METHOD=38;
    public static final int EMPTY_DISPATCH=18;
    public static final int T__79=79;
    public static final int UNARY_BRACKET=53;
    public static final int T__78=78;
    public static final int STRING=34;
    public static final int T__77=77;

    // delegates
    // delegators

    public LCPLTreeBuilderLexer() {;} 
    public LCPLTreeBuilderLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public LCPLTreeBuilderLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "src/LCPLTreeBuilder.g"; }

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:7:5: ( 'Int' )
            // src/LCPLTreeBuilder.g:7:7: 'Int'
            {
            match("Int"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:8:8: ( 'String' )
            // src/LCPLTreeBuilder.g:8:10: 'String'
            {
            match("String"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "CLASS"
    public final void mCLASS() throws RecognitionException {
        try {
            int _type = CLASS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:9:7: ( 'class' )
            // src/LCPLTreeBuilder.g:9:9: 'class'
            {
            match("class"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLASS"

    // $ANTLR start "INHERITS"
    public final void mINHERITS() throws RecognitionException {
        try {
            int _type = INHERITS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:10:10: ( 'inherits' )
            // src/LCPLTreeBuilder.g:10:12: 'inherits'
            {
            match("inherits"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INHERITS"

    // $ANTLR start "NEW"
    public final void mNEW() throws RecognitionException {
        try {
            int _type = NEW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:11:5: ( 'new' )
            // src/LCPLTreeBuilder.g:11:7: 'new'
            {
            match("new"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEW"

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
        try {
            int _type = END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:12:5: ( 'end' )
            // src/LCPLTreeBuilder.g:12:7: 'end'
            {
            match("end"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:13:6: ( 'null' )
            // src/LCPLTreeBuilder.g:13:8: 'null'
            {
            match("null"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NULL"

    // $ANTLR start "LOCAL"
    public final void mLOCAL() throws RecognitionException {
        try {
            int _type = LOCAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:14:7: ( 'local' )
            // src/LCPLTreeBuilder.g:14:9: 'local'
            {
            match("local"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOCAL"

    // $ANTLR start "VOID_CONSTANT"
    public final void mVOID_CONSTANT() throws RecognitionException {
        try {
            int _type = VOID_CONSTANT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:15:15: ( 'void' )
            // src/LCPLTreeBuilder.g:15:17: 'void'
            {
            match("void"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VOID_CONSTANT"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:16:4: ( 'if' )
            // src/LCPLTreeBuilder.g:16:6: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "WHILE"
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:17:7: ( 'while' )
            // src/LCPLTreeBuilder.g:17:9: 'while'
            {
            match("while"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHILE"

    // $ANTLR start "SELF"
    public final void mSELF() throws RecognitionException {
        try {
            int _type = SELF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:18:6: ( 'self' )
            // src/LCPLTreeBuilder.g:18:8: 'self'
            {
            match("self"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SELF"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:19:7: ( ';' )
            // src/LCPLTreeBuilder.g:19:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:20:7: ( ':' )
            // src/LCPLTreeBuilder.g:20:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:21:7: ( '->' )
            // src/LCPLTreeBuilder.g:21:9: '->'
            {
            match("->"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:22:7: ( ',' )
            // src/LCPLTreeBuilder.g:22:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:23:7: ( '[' )
            // src/LCPLTreeBuilder.g:23:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:24:7: ( '.' )
            // src/LCPLTreeBuilder.g:24:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:25:7: ( ']' )
            // src/LCPLTreeBuilder.g:25:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:26:7: ( 'self.' )
            // src/LCPLTreeBuilder.g:26:9: 'self.'
            {
            match("self."); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:27:7: ( 'var' )
            // src/LCPLTreeBuilder.g:27:9: 'var'
            {
            match("var"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:28:7: ( '[]' )
            // src/LCPLTreeBuilder.g:28:9: '[]'
            {
            match("[]"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:29:7: ( 'then' )
            // src/LCPLTreeBuilder.g:29:9: 'then'
            {
            match("then"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:30:7: ( 'else' )
            // src/LCPLTreeBuilder.g:30:9: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:31:7: ( 'loop' )
            // src/LCPLTreeBuilder.g:31:9: 'loop'
            {
            match("loop"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:32:7: ( '{' )
            // src/LCPLTreeBuilder.g:32:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:33:7: ( '}' )
            // src/LCPLTreeBuilder.g:33:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:34:7: ( '(' )
            // src/LCPLTreeBuilder.g:34:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:35:7: ( ')' )
            // src/LCPLTreeBuilder.g:35:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:203:5: ( '\"' ( ESCAPE_SEQUENCE | ~ ( '\\\\' | '\"' ) )* '\"' )
            // src/LCPLTreeBuilder.g:203:8: '\"' ( ESCAPE_SEQUENCE | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // src/LCPLTreeBuilder.g:203:12: ( ESCAPE_SEQUENCE | ~ ( '\\\\' | '\"' ) )*
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='\\') ) {
                    alt1=1;
                }
                else if ( ((LA1_0>='\u0000' && LA1_0<='!')||(LA1_0>='#' && LA1_0<='[')||(LA1_0>=']' && LA1_0<='\uFFFF')) ) {
                    alt1=2;
                }


                switch (alt1) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:203:14: ESCAPE_SEQUENCE
            	    {
            	    mESCAPE_SEQUENCE(); 

            	    }
            	    break;
            	case 2 :
            	    // src/LCPLTreeBuilder.g:203:32: ~ ( '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING_LITERAL"

    // $ANTLR start "ESCAPE_SEQUENCE"
    public final void mESCAPE_SEQUENCE() throws RecognitionException {
        try {
            int _type = ESCAPE_SEQUENCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:207:5: ( '\\\\' . )
            // src/LCPLTreeBuilder.g:207:9: '\\\\' .
            {
            match('\\'); 
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ESCAPE_SEQUENCE"

    // $ANTLR start "SUB"
    public final void mSUB() throws RecognitionException {
        try {
            int _type = SUB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:211:5: ( '-' )
            // src/LCPLTreeBuilder.g:211:7: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SUB"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:212:6: ( '+' )
            // src/LCPLTreeBuilder.g:212:8: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:213:6: ( '*' )
            // src/LCPLTreeBuilder.g:213:8: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STAR"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:214:5: ( '/' )
            // src/LCPLTreeBuilder.g:214:7: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:215:4: ( '=' )
            // src/LCPLTreeBuilder.g:215:6: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:216:4: ( '<' )
            // src/LCPLTreeBuilder.g:216:6: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:217:5: ( '!' )
            // src/LCPLTreeBuilder.g:217:7: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:218:7: ( '==' )
            // src/LCPLTreeBuilder.g:218:9: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUAL"

    // $ANTLR start "LTEQ"
    public final void mLTEQ() throws RecognitionException {
        try {
            int _type = LTEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:219:6: ( '<=' )
            // src/LCPLTreeBuilder.g:219:8: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LTEQ"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:222:5: ( '#' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // src/LCPLTreeBuilder.g:222:7: '#' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match('#'); 
            // src/LCPLTreeBuilder.g:222:11: (~ ( '\\n' | '\\r' ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='\u0000' && LA2_0<='\t')||(LA2_0>='\u000B' && LA2_0<='\f')||(LA2_0>='\u000E' && LA2_0<='\uFFFF')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:222:11: ~ ( '\\n' | '\\r' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // src/LCPLTreeBuilder.g:222:25: ( '\\r' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\r') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // src/LCPLTreeBuilder.g:222:25: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 
            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LINE_COMMENT"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:225:9: ( '0' | ( '1' .. '9' ) ( '0' .. '9' )* )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='0') ) {
                alt5=1;
            }
            else if ( ((LA5_0>='1' && LA5_0<='9')) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // src/LCPLTreeBuilder.g:225:11: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // src/LCPLTreeBuilder.g:226:4: ( '1' .. '9' ) ( '0' .. '9' )*
                    {
                    // src/LCPLTreeBuilder.g:226:4: ( '1' .. '9' )
                    // src/LCPLTreeBuilder.g:226:5: '1' .. '9'
                    {
                    matchRange('1','9'); 

                    }

                    // src/LCPLTreeBuilder.g:226:15: ( '0' .. '9' )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // src/LCPLTreeBuilder.g:226:16: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTEGER"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:229:5: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // src/LCPLTreeBuilder.g:229:9: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // src/LCPLTreeBuilder.g:229:29: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='0' && LA6_0<='9')||(LA6_0>='A' && LA6_0<='Z')||LA6_0=='_'||(LA6_0>='a' && LA6_0<='z')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/LCPLTreeBuilder.g:232:5: ( ( ' ' | '\\t' | '\\n' | '\\r' ) )
            // src/LCPLTreeBuilder.g:232:9: ( ' ' | '\\t' | '\\n' | '\\r' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // src/LCPLTreeBuilder.g:1:8: ( INT | STRING | CLASS | INHERITS | NEW | END | NULL | LOCAL | VOID_CONSTANT | IF | WHILE | SELF | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | STRING_LITERAL | ESCAPE_SEQUENCE | SUB | PLUS | STAR | DIV | EQ | LT | NOT | EQUAL | LTEQ | LINE_COMMENT | INTEGER | ID | WS )
        int alt7=44;
        alt7 = dfa7.predict(input);
        switch (alt7) {
            case 1 :
                // src/LCPLTreeBuilder.g:1:10: INT
                {
                mINT(); 

                }
                break;
            case 2 :
                // src/LCPLTreeBuilder.g:1:14: STRING
                {
                mSTRING(); 

                }
                break;
            case 3 :
                // src/LCPLTreeBuilder.g:1:21: CLASS
                {
                mCLASS(); 

                }
                break;
            case 4 :
                // src/LCPLTreeBuilder.g:1:27: INHERITS
                {
                mINHERITS(); 

                }
                break;
            case 5 :
                // src/LCPLTreeBuilder.g:1:36: NEW
                {
                mNEW(); 

                }
                break;
            case 6 :
                // src/LCPLTreeBuilder.g:1:40: END
                {
                mEND(); 

                }
                break;
            case 7 :
                // src/LCPLTreeBuilder.g:1:44: NULL
                {
                mNULL(); 

                }
                break;
            case 8 :
                // src/LCPLTreeBuilder.g:1:49: LOCAL
                {
                mLOCAL(); 

                }
                break;
            case 9 :
                // src/LCPLTreeBuilder.g:1:55: VOID_CONSTANT
                {
                mVOID_CONSTANT(); 

                }
                break;
            case 10 :
                // src/LCPLTreeBuilder.g:1:69: IF
                {
                mIF(); 

                }
                break;
            case 11 :
                // src/LCPLTreeBuilder.g:1:72: WHILE
                {
                mWHILE(); 

                }
                break;
            case 12 :
                // src/LCPLTreeBuilder.g:1:78: SELF
                {
                mSELF(); 

                }
                break;
            case 13 :
                // src/LCPLTreeBuilder.g:1:83: T__68
                {
                mT__68(); 

                }
                break;
            case 14 :
                // src/LCPLTreeBuilder.g:1:89: T__69
                {
                mT__69(); 

                }
                break;
            case 15 :
                // src/LCPLTreeBuilder.g:1:95: T__70
                {
                mT__70(); 

                }
                break;
            case 16 :
                // src/LCPLTreeBuilder.g:1:101: T__71
                {
                mT__71(); 

                }
                break;
            case 17 :
                // src/LCPLTreeBuilder.g:1:107: T__72
                {
                mT__72(); 

                }
                break;
            case 18 :
                // src/LCPLTreeBuilder.g:1:113: T__73
                {
                mT__73(); 

                }
                break;
            case 19 :
                // src/LCPLTreeBuilder.g:1:119: T__74
                {
                mT__74(); 

                }
                break;
            case 20 :
                // src/LCPLTreeBuilder.g:1:125: T__75
                {
                mT__75(); 

                }
                break;
            case 21 :
                // src/LCPLTreeBuilder.g:1:131: T__76
                {
                mT__76(); 

                }
                break;
            case 22 :
                // src/LCPLTreeBuilder.g:1:137: T__77
                {
                mT__77(); 

                }
                break;
            case 23 :
                // src/LCPLTreeBuilder.g:1:143: T__78
                {
                mT__78(); 

                }
                break;
            case 24 :
                // src/LCPLTreeBuilder.g:1:149: T__79
                {
                mT__79(); 

                }
                break;
            case 25 :
                // src/LCPLTreeBuilder.g:1:155: T__80
                {
                mT__80(); 

                }
                break;
            case 26 :
                // src/LCPLTreeBuilder.g:1:161: T__81
                {
                mT__81(); 

                }
                break;
            case 27 :
                // src/LCPLTreeBuilder.g:1:167: T__82
                {
                mT__82(); 

                }
                break;
            case 28 :
                // src/LCPLTreeBuilder.g:1:173: T__83
                {
                mT__83(); 

                }
                break;
            case 29 :
                // src/LCPLTreeBuilder.g:1:179: T__84
                {
                mT__84(); 

                }
                break;
            case 30 :
                // src/LCPLTreeBuilder.g:1:185: STRING_LITERAL
                {
                mSTRING_LITERAL(); 

                }
                break;
            case 31 :
                // src/LCPLTreeBuilder.g:1:200: ESCAPE_SEQUENCE
                {
                mESCAPE_SEQUENCE(); 

                }
                break;
            case 32 :
                // src/LCPLTreeBuilder.g:1:216: SUB
                {
                mSUB(); 

                }
                break;
            case 33 :
                // src/LCPLTreeBuilder.g:1:220: PLUS
                {
                mPLUS(); 

                }
                break;
            case 34 :
                // src/LCPLTreeBuilder.g:1:225: STAR
                {
                mSTAR(); 

                }
                break;
            case 35 :
                // src/LCPLTreeBuilder.g:1:230: DIV
                {
                mDIV(); 

                }
                break;
            case 36 :
                // src/LCPLTreeBuilder.g:1:234: EQ
                {
                mEQ(); 

                }
                break;
            case 37 :
                // src/LCPLTreeBuilder.g:1:237: LT
                {
                mLT(); 

                }
                break;
            case 38 :
                // src/LCPLTreeBuilder.g:1:240: NOT
                {
                mNOT(); 

                }
                break;
            case 39 :
                // src/LCPLTreeBuilder.g:1:244: EQUAL
                {
                mEQUAL(); 

                }
                break;
            case 40 :
                // src/LCPLTreeBuilder.g:1:250: LTEQ
                {
                mLTEQ(); 

                }
                break;
            case 41 :
                // src/LCPLTreeBuilder.g:1:255: LINE_COMMENT
                {
                mLINE_COMMENT(); 

                }
                break;
            case 42 :
                // src/LCPLTreeBuilder.g:1:268: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 43 :
                // src/LCPLTreeBuilder.g:1:276: ID
                {
                mID(); 

                }
                break;
            case 44 :
                // src/LCPLTreeBuilder.g:1:279: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\1\uffff\12\41\2\uffff\1\62\1\uffff\1\64\2\uffff\1\41\11\uffff\1"+
        "\67\1\71\5\uffff\4\41\1\76\11\41\4\uffff\1\41\4\uffff\1\112\3\41"+
        "\1\uffff\1\116\1\41\1\120\4\41\1\125\3\41\1\uffff\3\41\1\uffff\1"+
        "\134\1\uffff\1\135\1\41\1\137\1\140\1\uffff\1\41\1\143\1\144\1\41"+
        "\1\146\1\41\2\uffff\1\150\2\uffff\1\151\3\uffff\1\152\1\uffff\1"+
        "\41\3\uffff\1\41\1\155\1\uffff";
    static final String DFA7_eofS =
        "\156\uffff";
    static final String DFA7_minS =
        "\1\11\1\156\1\164\1\154\1\146\1\145\1\154\1\157\1\141\1\150\1\145"+
        "\2\uffff\1\76\1\uffff\1\135\2\uffff\1\150\11\uffff\2\75\5\uffff"+
        "\1\164\1\162\1\141\1\150\1\60\1\167\1\154\1\144\1\163\1\143\1\151"+
        "\1\162\1\151\1\154\4\uffff\1\145\4\uffff\1\60\1\151\1\163\1\145"+
        "\1\uffff\1\60\1\154\1\60\1\145\1\141\1\160\1\144\1\60\1\154\1\146"+
        "\1\156\1\uffff\1\156\1\163\1\162\1\uffff\1\60\1\uffff\1\60\1\154"+
        "\2\60\1\uffff\1\145\1\56\1\60\1\147\1\60\1\151\2\uffff\1\60\2\uffff"+
        "\1\60\3\uffff\1\60\1\uffff\1\164\3\uffff\1\163\1\60\1\uffff";
    static final String DFA7_maxS =
        "\1\175\1\156\1\164\1\154\1\156\1\165\1\156\2\157\1\150\1\145\2\uffff"+
        "\1\76\1\uffff\1\135\2\uffff\1\150\11\uffff\2\75\5\uffff\1\164\1"+
        "\162\1\141\1\150\1\172\1\167\1\154\1\144\1\163\1\157\1\151\1\162"+
        "\1\151\1\154\4\uffff\1\145\4\uffff\1\172\1\151\1\163\1\145\1\uffff"+
        "\1\172\1\154\1\172\1\145\1\141\1\160\1\144\1\172\1\154\1\146\1\156"+
        "\1\uffff\1\156\1\163\1\162\1\uffff\1\172\1\uffff\1\172\1\154\2\172"+
        "\1\uffff\1\145\2\172\1\147\1\172\1\151\2\uffff\1\172\2\uffff\1\172"+
        "\3\uffff\1\172\1\uffff\1\164\3\uffff\1\163\1\172\1\uffff";
    static final String DFA7_acceptS =
        "\13\uffff\1\15\1\16\1\uffff\1\20\1\uffff\1\22\1\23\1\uffff\1\32"+
        "\1\33\1\34\1\35\1\36\1\37\1\41\1\42\1\43\2\uffff\1\46\1\51\1\52"+
        "\1\53\1\54\16\uffff\1\17\1\40\1\26\1\21\1\uffff\1\47\1\44\1\50\1"+
        "\45\4\uffff\1\12\13\uffff\1\1\3\uffff\1\5\1\uffff\1\6\4\uffff\1"+
        "\25\6\uffff\1\7\1\30\1\uffff\1\31\1\11\1\uffff\1\24\1\14\1\27\1"+
        "\uffff\1\3\1\uffff\1\10\1\13\1\2\2\uffff\1\4";
    static final String DFA7_specialS =
        "\156\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\42\2\uffff\1\42\22\uffff\1\42\1\36\1\27\1\37\4\uffff\1\25"+
            "\1\26\1\32\1\31\1\16\1\15\1\20\1\33\12\40\1\14\1\13\1\35\1\34"+
            "\3\uffff\10\41\1\1\11\41\1\2\7\41\1\17\1\30\1\21\3\uffff\2\41"+
            "\1\3\1\41\1\6\3\41\1\4\2\41\1\7\1\41\1\5\4\41\1\12\1\22\1\41"+
            "\1\10\1\11\3\41\1\23\1\uffff\1\24",
            "\1\43",
            "\1\44",
            "\1\45",
            "\1\47\7\uffff\1\46",
            "\1\50\17\uffff\1\51",
            "\1\53\1\uffff\1\52",
            "\1\54",
            "\1\56\15\uffff\1\55",
            "\1\57",
            "\1\60",
            "",
            "",
            "\1\61",
            "",
            "\1\63",
            "",
            "",
            "\1\65",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\66",
            "\1\70",
            "",
            "",
            "",
            "",
            "",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\75",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103\13\uffff\1\104",
            "\1\105",
            "\1\106",
            "\1\107",
            "\1\110",
            "",
            "",
            "",
            "",
            "\1\111",
            "",
            "",
            "",
            "",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\113",
            "\1\114",
            "\1\115",
            "",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\117",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\126",
            "\1\127",
            "\1\130",
            "",
            "\1\131",
            "\1\132",
            "\1\133",
            "",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\136",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\141",
            "\1\142\1\uffff\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32"+
            "\41",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\145",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\147",
            "",
            "",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "",
            "",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "\1\153",
            "",
            "",
            "",
            "\1\154",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
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

    class DFA7 extends DFA {

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
        public String getDescription() {
            return "1:1: Tokens : ( INT | STRING | CLASS | INHERITS | NEW | END | NULL | LOCAL | VOID_CONSTANT | IF | WHILE | SELF | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | STRING_LITERAL | ESCAPE_SEQUENCE | SUB | PLUS | STAR | DIV | EQ | LT | NOT | EQUAL | LTEQ | LINE_COMMENT | INTEGER | ID | WS );";
        }
    }
 

}