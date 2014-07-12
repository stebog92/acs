// $ANTLR 3.2 Sep 23, 2009 12:02:23 src/LCPLTreeChecker.g 2013-11-12 22:45:06

    import java.util.LinkedList;
    import ro.pub.cs.lcpl.*;
    import vector.*;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class LCPLTreeChecker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PROGRAM", "ATTRIBUTE_ARRAY", "CLASSDEFINITION", "CONSTANT_INT", "FORMAL_PARAM", "CONSTANT_STRING", "SYMBOL", "EQ_COMP", "DECL_SECTION", "UNARY_MINUS", "PAR_EXPR", "BLOCK", "OBJ", "EXPR", "EMPTY_DISPATCH", "LOCAL_DECL", "DISPATCH", "DISPATCH_ARGUMENTS", "DECLARATION_SECTION", "EMPTY_METHOD", "ARRAY_TYPE", "DISPATCH_ARGS", "MEMBER", "MEMBERS", "ATTRIBUTE", "ASSIGNMENT", "ATTR", "EXPRESSION", "TYPE", "INT", "STRING", "CLASS", "INHERITS", "NEW", "METHOD", "STATEMENT", "END", "NULL", "LOCAL", "DISPATCH_S", "ID", "VOID_CONSTANT", "CAST", "STRING_CONST", "IF", "WHILE", "SELF", "SUBSTR", "ARRAY", "UNARY_BRACKET", "EQ", "EQUAL", "LTEQ", "LT", "PLUS", "SUB", "STAR", "DIV", "NOT", "INTEGER", "STRING_LITERAL", "ESCAPE_SEQUENCE", "LINE_COMMENT", "WS", "';'", "':'", "'->'", "','", "'['", "'.'", "']'", "'self.'", "'var'", "'[]'", "'then'", "'else'", "'loop'", "'{'", "'}'", "'('", "')'"
    };
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


        public LCPLTreeChecker(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public LCPLTreeChecker(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return LCPLTreeChecker.tokenNames; }
    public String getGrammarFileName() { return "src/LCPLTreeChecker.g"; }


        LinkedList<LCPLClass> classes = new LinkedList<LCPLClass>();



    // $ANTLR start "program"
    // src/LCPLTreeChecker.g:18:1: program returns [Program result] : ^( PROGRAM ( class_definition )+ ) ;
    public final Program program() throws RecognitionException {
        Program result = null;

        CommonTree PROGRAM1=null;

        try {
            // src/LCPLTreeChecker.g:19:5: ( ^( PROGRAM ( class_definition )+ ) )
            // src/LCPLTreeChecker.g:19:7: ^( PROGRAM ( class_definition )+ )
            {
            PROGRAM1=(CommonTree)match(input,PROGRAM,FOLLOW_PROGRAM_in_program56); 

            match(input, Token.DOWN, null); 
            // src/LCPLTreeChecker.g:19:17: ( class_definition )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==CLASSDEFINITION) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // src/LCPLTreeChecker.g:19:17: class_definition
            	    {
            	    pushFollow(FOLLOW_class_definition_in_program58);
            	    class_definition();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            match(input, Token.UP, null); 
             
                    result =new Program((PROGRAM1!=null?PROGRAM1.getLine():0), classes); 
                

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "program"


    // $ANTLR start "class_definition"
    // src/LCPLTreeChecker.g:24:1: class_definition : ^( CLASSDEFINITION classdef ) ;
    public final void class_definition() throws RecognitionException {
        LCPLClass classdef2 = null;


        try {
            // src/LCPLTreeChecker.g:25:1: ( ^( CLASSDEFINITION classdef ) )
            // src/LCPLTreeChecker.g:25:2: ^( CLASSDEFINITION classdef )
            {
            match(input,CLASSDEFINITION,FOLLOW_CLASSDEFINITION_in_class_definition78); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_classdef_in_class_definition80);
            classdef2=classdef();

            state._fsp--;


            match(input, Token.UP, null); 

            	classes.add(classdef2);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "class_definition"


    // $ANTLR start "classdef"
    // src/LCPLTreeChecker.g:31:1: classdef returns [LCPLClass result] : ( ^( CLASS name= ID parent= ID ( members )? ) | ^( CLASS name= ID ( members )? ) );
    public final LCPLClass classdef() throws RecognitionException {
        LCPLClass result = null;

        CommonTree name=null;
        CommonTree parent=null;
        CommonTree CLASS4=null;
        CommonTree CLASS6=null;
        ArrayList<Feature> members3 = null;

        ArrayList<Feature> members5 = null;


        try {
            // src/LCPLTreeChecker.g:32:5: ( ^( CLASS name= ID parent= ID ( members )? ) | ^( CLASS name= ID ( members )? ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==CLASS) ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==DOWN) ) {
                    int LA4_2 = input.LA(3);

                    if ( (LA4_2==ID) ) {
                        int LA4_3 = input.LA(4);

                        if ( (LA4_3==ID) ) {
                            alt4=1;
                        }
                        else if ( (LA4_3==UP||LA4_3==MEMBERS) ) {
                            alt4=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 4, 3, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // src/LCPLTreeChecker.g:32:9: ^( CLASS name= ID parent= ID ( members )? )
                    {
                    CLASS4=(CommonTree)match(input,CLASS,FOLLOW_CLASS_in_classdef103); 

                    match(input, Token.DOWN, null); 
                    name=(CommonTree)match(input,ID,FOLLOW_ID_in_classdef107); 
                    parent=(CommonTree)match(input,ID,FOLLOW_ID_in_classdef111); 
                    // src/LCPLTreeChecker.g:32:35: ( members )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==MEMBERS) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // src/LCPLTreeChecker.g:32:35: members
                            {
                            pushFollow(FOLLOW_members_in_classdef113);
                            members3=members();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                        	ArrayList<Feature> features = (members3 != null) ? members3 : new ArrayList<Feature>();
                            result = new LCPLClass((CLASS4!=null?CLASS4.getLine():0), (name!=null?name.getText():null), (parent!=null?parent.getText():null), features);
                        

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:37:7: ^( CLASS name= ID ( members )? )
                    {
                    CLASS6=(CommonTree)match(input,CLASS,FOLLOW_CLASS_in_classdef130); 

                    match(input, Token.DOWN, null); 
                    name=(CommonTree)match(input,ID,FOLLOW_ID_in_classdef134); 
                    // src/LCPLTreeChecker.g:37:23: ( members )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==MEMBERS) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // src/LCPLTreeChecker.g:37:23: members
                            {
                            pushFollow(FOLLOW_members_in_classdef136);
                            members5=members();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                        	ArrayList<Feature> features = (members5 != null) ? members5 : new ArrayList<Feature>();
                        	result = new LCPLClass((CLASS6!=null?CLASS6.getLine():0), (name!=null?name.getText():null), null, features);
                        

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "classdef"

    protected static class method_scope {
        ArrayList<FormalParam> parameters;
    }
    protected Stack method_stack = new Stack();


    // $ANTLR start "method"
    // src/LCPLTreeChecker.g:44:1: method returns [Method result] : ( ^( EMPTY_METHOD ID ) | ^( METHOD ID block ) | ^( METHOD ID (param= formal_param )+ (t= type )? block ) | ^( METHOD ID type block ) );
    public final Method method() throws RecognitionException {
        method_stack.push(new method_scope());
        Method result = null;

        CommonTree EMPTY_METHOD7=null;
        CommonTree ID8=null;
        CommonTree METHOD9=null;
        CommonTree ID10=null;
        CommonTree METHOD12=null;
        CommonTree ID13=null;
        CommonTree METHOD15=null;
        CommonTree ID16=null;
        FormalParam param = null;

        String t = null;

        Block block11 = null;

        Block block14 = null;

        String type17 = null;

        Block block18 = null;



        		((method_scope)method_stack.peek()).parameters = new ArrayList<FormalParam>();
        	
        try {
            // src/LCPLTreeChecker.g:51:5: ( ^( EMPTY_METHOD ID ) | ^( METHOD ID block ) | ^( METHOD ID (param= formal_param )+ (t= type )? block ) | ^( METHOD ID type block ) )
            int alt7=4;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==EMPTY_METHOD) ) {
                alt7=1;
            }
            else if ( (LA7_0==METHOD) ) {
                int LA7_2 = input.LA(2);

                if ( (LA7_2==DOWN) ) {
                    int LA7_3 = input.LA(3);

                    if ( (LA7_3==ID) ) {
                        switch ( input.LA(4) ) {
                        case BLOCK:
                            {
                            alt7=2;
                            }
                            break;
                        case TYPE:
                            {
                            alt7=4;
                            }
                            break;
                        case FORMAL_PARAM:
                            {
                            alt7=3;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 7, 4, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // src/LCPLTreeChecker.g:51:9: ^( EMPTY_METHOD ID )
                    {
                    EMPTY_METHOD7=(CommonTree)match(input,EMPTY_METHOD,FOLLOW_EMPTY_METHOD_in_method181); 

                    match(input, Token.DOWN, null); 
                    ID8=(CommonTree)match(input,ID,FOLLOW_ID_in_method183); 

                    match(input, Token.UP, null); 

                            result = new Method((EMPTY_METHOD7!=null?EMPTY_METHOD7.getLine():0), (ID8!=null?ID8.getText():null), new ArrayList<FormalParam>(), "void", new Block(0, new ArrayList<Expression>()));
                        

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:55:9: ^( METHOD ID block )
                    {
                    METHOD9=(CommonTree)match(input,METHOD,FOLLOW_METHOD_in_method201); 

                    match(input, Token.DOWN, null); 
                    ID10=(CommonTree)match(input,ID,FOLLOW_ID_in_method203); 
                    pushFollow(FOLLOW_block_in_method205);
                    block11=block();

                    state._fsp--;


                    match(input, Token.UP, null); 

                        	result = new Method((METHOD9!=null?METHOD9.getLine():0), (ID10!=null?ID10.getText():null), new ArrayList<FormalParam>(), "void", block11);
                        

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:59:7: ^( METHOD ID (param= formal_param )+ (t= type )? block )
                    {
                    METHOD12=(CommonTree)match(input,METHOD,FOLLOW_METHOD_in_method221); 

                    match(input, Token.DOWN, null); 
                    ID13=(CommonTree)match(input,ID,FOLLOW_ID_in_method223); 
                    // src/LCPLTreeChecker.g:59:19: (param= formal_param )+
                    int cnt5=0;
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==FORMAL_PARAM) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // src/LCPLTreeChecker.g:59:20: param= formal_param
                    	    {
                    	    pushFollow(FOLLOW_formal_param_in_method230);
                    	    param=formal_param();

                    	    state._fsp--;

                    	    ((method_scope)method_stack.peek()).parameters.add(param);

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt5 >= 1 ) break loop5;
                                EarlyExitException eee =
                                    new EarlyExitException(5, input);
                                throw eee;
                        }
                        cnt5++;
                    } while (true);

                    // src/LCPLTreeChecker.g:59:87: (t= type )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==TYPE) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // src/LCPLTreeChecker.g:59:87: t= type
                            {
                            pushFollow(FOLLOW_type_in_method240);
                            t=type();

                            state._fsp--;


                            }
                            break;

                    }

                    pushFollow(FOLLOW_block_in_method243);
                    block14=block();

                    state._fsp--;


                    match(input, Token.UP, null); 

                        	String type;
                        	if (t == null) {
                        		type = "void";
                        	} else {
                        		type = t;
                        	}
                        	result = new Method((METHOD12!=null?METHOD12.getLine():0), (ID13!=null?ID13.getText():null), ((method_scope)method_stack.peek()).parameters, type, block14);
                        

                    }
                    break;
                case 4 :
                    // src/LCPLTreeChecker.g:69:7: ^( METHOD ID type block )
                    {
                    METHOD15=(CommonTree)match(input,METHOD,FOLLOW_METHOD_in_method259); 

                    match(input, Token.DOWN, null); 
                    ID16=(CommonTree)match(input,ID,FOLLOW_ID_in_method261); 
                    pushFollow(FOLLOW_type_in_method263);
                    type17=type();

                    state._fsp--;

                    pushFollow(FOLLOW_block_in_method265);
                    block18=block();

                    state._fsp--;


                    match(input, Token.UP, null); 

                        	result = new Method((METHOD15!=null?METHOD15.getLine():0), (ID16!=null?ID16.getText():null), new ArrayList<FormalParam>(), type17, block18);
                        

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            method_stack.pop();
        }
        return result;
    }
    // $ANTLR end "method"

    protected static class local_decl_scope {
        ArrayList<Attribute> exprs;
    }
    protected Stack local_decl_stack = new Stack();


    // $ANTLR start "local_decl"
    // src/LCPLTreeChecker.g:75:1: local_decl returns [LocalDefinition result] : ^( LOCAL (a= attribute )+ ( block )? ) ;
    public final LocalDefinition local_decl() throws RecognitionException {
        local_decl_stack.push(new local_decl_scope());
        LocalDefinition result = null;

        CommonTree LOCAL19=null;
        Attribute a = null;

        Block block20 = null;



        		((local_decl_scope)local_decl_stack.peek()).exprs = new ArrayList<Attribute>();
        	
        try {
            // src/LCPLTreeChecker.g:82:2: ( ^( LOCAL (a= attribute )+ ( block )? ) )
            // src/LCPLTreeChecker.g:82:4: ^( LOCAL (a= attribute )+ ( block )? )
            {
            LOCAL19=(CommonTree)match(input,LOCAL,FOLLOW_LOCAL_in_local_decl306); 

            match(input, Token.DOWN, null); 
            // src/LCPLTreeChecker.g:82:12: (a= attribute )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==ATTRIBUTE_ARRAY||LA8_0==ATTRIBUTE) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // src/LCPLTreeChecker.g:82:13: a= attribute
            	    {
            	    pushFollow(FOLLOW_attribute_in_local_decl313);
            	    a=attribute();

            	    state._fsp--;

            	    ((local_decl_scope)local_decl_stack.peek()).exprs.add(a); 

            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);

            // src/LCPLTreeChecker.g:82:67: ( block )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==BLOCK) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // src/LCPLTreeChecker.g:82:67: block
                    {
                    pushFollow(FOLLOW_block_in_local_decl319);
                    block20=block();

                    state._fsp--;


                    }
                    break;

            }


            match(input, Token.UP, null); 

            		int i = ((local_decl_scope)local_decl_stack.peek()).exprs.size() - 1;
            		int line = (LOCAL19!=null?LOCAL19.getLine():0);
            		Expression scope = block20;
            		if (scope == null) {
            			scope = new Block(0, new ArrayList<Expression>());
            		}
            		while (i >= 0) {
            			Attribute attr = ((local_decl_scope)local_decl_stack.peek()).exprs.get(i);
            			LocalDefinition localDefinition = new LocalDefinition(attr.getLineNumber(),
            										attr.getName(),
            										attr.getType(),
            										attr.getInit(),
            										scope);
            			scope = localDefinition;
            			i--;
            		}
            		result = (LocalDefinition)scope;
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            local_decl_stack.pop();
        }
        return result;
    }
    // $ANTLR end "local_decl"

    protected static class block_scope {
        ArrayList<Expression> exprs;
    }
    protected Stack block_stack = new Stack();


    // $ANTLR start "block"
    // src/LCPLTreeChecker.g:103:1: block returns [Block result] : ( ^( BLOCK (e= expression )+ (l= local_decl )? ) | ^( BLOCK local_decl ) );
    public final Block block() throws RecognitionException {
        block_stack.push(new block_scope());
        Block result = null;

        CommonTree BLOCK21=null;
        CommonTree BLOCK23=null;
        Expression e = null;

        LocalDefinition l = null;

        LocalDefinition local_decl22 = null;



        		((block_scope)block_stack.peek()).exprs = new ArrayList<Expression>();
        	
        try {
            // src/LCPLTreeChecker.g:110:2: ( ^( BLOCK (e= expression )+ (l= local_decl )? ) | ^( BLOCK local_decl ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==BLOCK) ) {
                int LA12_1 = input.LA(2);

                if ( (LA12_1==DOWN) ) {
                    int LA12_2 = input.LA(3);

                    if ( (LA12_2==LOCAL) ) {
                        alt12=2;
                    }
                    else if ( (LA12_2==EXPRESSION) ) {
                        alt12=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 12, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // src/LCPLTreeChecker.g:110:4: ^( BLOCK (e= expression )+ (l= local_decl )? )
                    {
                    BLOCK21=(CommonTree)match(input,BLOCK,FOLLOW_BLOCK_in_block353); 

                    match(input, Token.DOWN, null); 
                    // src/LCPLTreeChecker.g:110:12: (e= expression )+
                    int cnt10=0;
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==EXPRESSION) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // src/LCPLTreeChecker.g:110:13: e= expression
                    	    {
                    	    pushFollow(FOLLOW_expression_in_block360);
                    	    e=expression();

                    	    state._fsp--;

                    	     ((block_scope)block_stack.peek()).exprs.add(e) ;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt10 >= 1 ) break loop10;
                                EarlyExitException eee =
                                    new EarlyExitException(10, input);
                                throw eee;
                        }
                        cnt10++;
                    } while (true);

                    // src/LCPLTreeChecker.g:110:64: (l= local_decl )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==LOCAL) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // src/LCPLTreeChecker.g:110:65: l= local_decl
                            {
                            pushFollow(FOLLOW_local_decl_in_block371);
                            l=local_decl();

                            state._fsp--;

                            ((block_scope)block_stack.peek()).exprs.add(l);

                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                    		result = new Block((BLOCK21!=null?BLOCK21.getLine():0), ((block_scope)block_stack.peek()).exprs);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:114:4: ^( BLOCK local_decl )
                    {
                    BLOCK23=(CommonTree)match(input,BLOCK,FOLLOW_BLOCK_in_block385); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_local_decl_in_block387);
                    local_decl22=local_decl();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		((block_scope)block_stack.peek()).exprs.add(local_decl22);
                    		result = new Block((BLOCK23!=null?BLOCK23.getLine():0), ((block_scope)block_stack.peek()).exprs);
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            block_stack.pop();
        }
        return result;
    }
    // $ANTLR end "block"

    protected static class members_scope {
        ArrayList<Feature> local_features;
    }
    protected Stack members_stack = new Stack();


    // $ANTLR start "members"
    // src/LCPLTreeChecker.g:120:1: members returns [ArrayList<Feature> features] : ^( MEMBERS ( member )+ ) ;
    public final ArrayList<Feature> members() throws RecognitionException {
        members_stack.push(new members_scope());
        ArrayList<Feature> features = null;


        		((members_scope)members_stack.peek()).local_features = new ArrayList<Feature>();
        	
        try {
            // src/LCPLTreeChecker.g:127:2: ( ^( MEMBERS ( member )+ ) )
            // src/LCPLTreeChecker.g:127:4: ^( MEMBERS ( member )+ )
            {
            match(input,MEMBERS,FOLLOW_MEMBERS_in_members419); 

            match(input, Token.DOWN, null); 
            // src/LCPLTreeChecker.g:127:14: ( member )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==MEMBER) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // src/LCPLTreeChecker.g:127:14: member
            	    {
            	    pushFollow(FOLLOW_member_in_members421);
            	    member();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);


            match(input, Token.UP, null); 

            		features = ((members_scope)members_stack.peek()).local_features;
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            members_stack.pop();
        }
        return features;
    }
    // $ANTLR end "members"


    // $ANTLR start "member"
    // src/LCPLTreeChecker.g:132:1: member : ( ^( MEMBER decl_section ) | ^( MEMBER method ) );
    public final void member() throws RecognitionException {
        ArrayList<Feature> decl_section24 = null;

        Method method25 = null;


        try {
            // src/LCPLTreeChecker.g:132:8: ( ^( MEMBER decl_section ) | ^( MEMBER method ) )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==MEMBER) ) {
                int LA14_1 = input.LA(2);

                if ( (LA14_1==DOWN) ) {
                    int LA14_2 = input.LA(3);

                    if ( (LA14_2==EMPTY_METHOD||LA14_2==METHOD) ) {
                        alt14=2;
                    }
                    else if ( (LA14_2==DECL_SECTION) ) {
                        alt14=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 14, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // src/LCPLTreeChecker.g:132:10: ^( MEMBER decl_section )
                    {
                    match(input,MEMBER,FOLLOW_MEMBER_in_member436); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_decl_section_in_member438);
                    decl_section24=decl_section();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		((members_scope)members_stack.peek()).local_features.addAll(decl_section24);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:136:4: ^( MEMBER method )
                    {
                    match(input,MEMBER,FOLLOW_MEMBER_in_member448); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_method_in_member450);
                    method25=method();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		((members_scope)members_stack.peek()).local_features.add(method25);
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "member"


    // $ANTLR start "formal_param"
    // src/LCPLTreeChecker.g:141:1: formal_param returns [FormalParam result] : ^( FORMAL_PARAM type ID ) ;
    public final FormalParam formal_param() throws RecognitionException {
        FormalParam result = null;

        CommonTree ID26=null;
        String type27 = null;


        try {
            // src/LCPLTreeChecker.g:142:2: ( ^( FORMAL_PARAM type ID ) )
            // src/LCPLTreeChecker.g:142:4: ^( FORMAL_PARAM type ID )
            {
            match(input,FORMAL_PARAM,FOLLOW_FORMAL_PARAM_in_formal_param469); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_formal_param471);
            type27=type();

            state._fsp--;

            ID26=(CommonTree)match(input,ID,FOLLOW_ID_in_formal_param473); 

            match(input, Token.UP, null); 

            		result = new FormalParam((ID26!=null?ID26.getText():null), type27);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "formal_param"


    // $ANTLR start "dispatch"
    // src/LCPLTreeChecker.g:147:1: dispatch returns [Expression result] : ( ^( DISPATCH (o= primaryExpression )? (t= type )? ID (b= dispatch_arguments )? ) | ^( DISPATCH_S ID (t= type )? (b= dispatch_arguments )? ) );
    public final Expression dispatch() throws RecognitionException {
        Expression result = null;

        CommonTree DISPATCH28=null;
        CommonTree ID29=null;
        CommonTree DISPATCH_S30=null;
        CommonTree ID31=null;
        Expression o = null;

        String t = null;

        ArrayList<Expression> b = null;


        try {
            // src/LCPLTreeChecker.g:148:2: ( ^( DISPATCH (o= primaryExpression )? (t= type )? ID (b= dispatch_arguments )? ) | ^( DISPATCH_S ID (t= type )? (b= dispatch_arguments )? ) )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==DISPATCH) ) {
                alt20=1;
            }
            else if ( (LA20_0==DISPATCH_S) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // src/LCPLTreeChecker.g:148:4: ^( DISPATCH (o= primaryExpression )? (t= type )? ID (b= dispatch_arguments )? )
                    {
                    DISPATCH28=(CommonTree)match(input,DISPATCH,FOLLOW_DISPATCH_in_dispatch491); 

                    match(input, Token.DOWN, null); 
                    // src/LCPLTreeChecker.g:148:15: (o= primaryExpression )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==CONSTANT_INT||(LA15_0>=CONSTANT_STRING && LA15_0<=SYMBOL)||LA15_0==PAR_EXPR||LA15_0==DISPATCH||LA15_0==NEW||LA15_0==DISPATCH_S||(LA15_0>=VOID_CONSTANT && LA15_0<=CAST)||(LA15_0>=IF && LA15_0<=SELF)) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // src/LCPLTreeChecker.g:148:16: o= primaryExpression
                            {
                            pushFollow(FOLLOW_primaryExpression_in_dispatch498);
                            o=primaryExpression();

                            state._fsp--;


                            }
                            break;

                    }

                    // src/LCPLTreeChecker.g:148:40: (t= type )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==TYPE) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // src/LCPLTreeChecker.g:148:41: t= type
                            {
                            pushFollow(FOLLOW_type_in_dispatch507);
                            t=type();

                            state._fsp--;


                            }
                            break;

                    }

                    ID29=(CommonTree)match(input,ID,FOLLOW_ID_in_dispatch511); 
                    // src/LCPLTreeChecker.g:148:55: (b= dispatch_arguments )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==DISPATCH_ARGUMENTS) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // src/LCPLTreeChecker.g:148:56: b= dispatch_arguments
                            {
                            pushFollow(FOLLOW_dispatch_arguments_in_dispatch518);
                            b=dispatch_arguments();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                    		ArrayList<Expression> args = ((b != null) ? b : new ArrayList<Expression>());
                    		if (t != null) {
                    			result = new StaticDispatch((DISPATCH28!=null?DISPATCH28.getLine():0), o, t, (ID29!=null?ID29.getText():null), args);
                    		} else {
                    			result = new Dispatch((DISPATCH28!=null?DISPATCH28.getLine():0), o, (ID29!=null?ID29.getText():null), args);
                    		}
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:157:4: ^( DISPATCH_S ID (t= type )? (b= dispatch_arguments )? )
                    {
                    DISPATCH_S30=(CommonTree)match(input,DISPATCH_S,FOLLOW_DISPATCH_S_in_dispatch530); 

                    match(input, Token.DOWN, null); 
                    ID31=(CommonTree)match(input,ID,FOLLOW_ID_in_dispatch532); 
                    // src/LCPLTreeChecker.g:157:20: (t= type )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==TYPE) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // src/LCPLTreeChecker.g:157:21: t= type
                            {
                            pushFollow(FOLLOW_type_in_dispatch539);
                            t=type();

                            state._fsp--;


                            }
                            break;

                    }

                    // src/LCPLTreeChecker.g:157:33: (b= dispatch_arguments )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==DISPATCH_ARGUMENTS) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // src/LCPLTreeChecker.g:157:33: b= dispatch_arguments
                            {
                            pushFollow(FOLLOW_dispatch_arguments_in_dispatch546);
                            b=dispatch_arguments();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                    		ArrayList<Expression> args = ((b != null) ? b : new ArrayList<Expression>());
                    		if (t != null) {
                    			result = new StaticDispatch((DISPATCH_S30!=null?DISPATCH_S30.getLine():0), new Symbol((ID31!=null?ID31.getLine():0), "self"), t, (ID31!=null?ID31.getText():null), args);
                    		} else {
                    			result = new Dispatch((DISPATCH_S30!=null?DISPATCH_S30.getLine():0), new Symbol((ID31!=null?ID31.getLine():0), "self"), (ID31!=null?ID31.getText():null), args);
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
        }
        return result;
    }
    // $ANTLR end "dispatch"

    protected static class dispatch_arguments_scope {
        ArrayList<Expression> exprs;
    }
    protected Stack dispatch_arguments_stack = new Stack();


    // $ANTLR start "dispatch_arguments"
    // src/LCPLTreeChecker.g:168:1: dispatch_arguments returns [ArrayList<Expression> args] : ^( DISPATCH_ARGUMENTS (i= expression )+ ) ;
    public final ArrayList<Expression> dispatch_arguments() throws RecognitionException {
        dispatch_arguments_stack.push(new dispatch_arguments_scope());
        ArrayList<Expression> args = null;

        Expression i = null;



        		((dispatch_arguments_scope)dispatch_arguments_stack.peek()).exprs = new ArrayList<Expression>();
        	
        try {
            // src/LCPLTreeChecker.g:175:2: ( ^( DISPATCH_ARGUMENTS (i= expression )+ ) )
            // src/LCPLTreeChecker.g:175:4: ^( DISPATCH_ARGUMENTS (i= expression )+ )
            {
            match(input,DISPATCH_ARGUMENTS,FOLLOW_DISPATCH_ARGUMENTS_in_dispatch_arguments578); 

            match(input, Token.DOWN, null); 
            // src/LCPLTreeChecker.g:175:25: (i= expression )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==EXPRESSION) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // src/LCPLTreeChecker.g:175:26: i= expression
            	    {
            	    pushFollow(FOLLOW_expression_in_dispatch_arguments585);
            	    i=expression();

            	    state._fsp--;

            	     ((dispatch_arguments_scope)dispatch_arguments_stack.peek()).exprs.add(i);

            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
            } while (true);


            match(input, Token.UP, null); 

            		args = ((dispatch_arguments_scope)dispatch_arguments_stack.peek()).exprs;
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            dispatch_arguments_stack.pop();
        }
        return args;
    }
    // $ANTLR end "dispatch_arguments"

    protected static class decl_section_scope {
        ArrayList<Feature> local_features;
    }
    protected Stack decl_section_stack = new Stack();


    // $ANTLR start "decl_section"
    // src/LCPLTreeChecker.g:180:1: decl_section returns [ArrayList<Feature> features] : ^( DECL_SECTION (a= attribute )+ ) ;
    public final ArrayList<Feature> decl_section() throws RecognitionException {
        decl_section_stack.push(new decl_section_scope());
        ArrayList<Feature> features = null;

        Attribute a = null;



        		((decl_section_scope)decl_section_stack.peek()).local_features = new ArrayList<Feature>();
        	
        try {
            // src/LCPLTreeChecker.g:187:2: ( ^( DECL_SECTION (a= attribute )+ ) )
            // src/LCPLTreeChecker.g:187:4: ^( DECL_SECTION (a= attribute )+ )
            {
            match(input,DECL_SECTION,FOLLOW_DECL_SECTION_in_decl_section618); 

            match(input, Token.DOWN, null); 
            // src/LCPLTreeChecker.g:187:19: (a= attribute )+
            int cnt22=0;
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==ATTRIBUTE_ARRAY||LA22_0==ATTRIBUTE) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // src/LCPLTreeChecker.g:187:20: a= attribute
            	    {
            	    pushFollow(FOLLOW_attribute_in_decl_section625);
            	    a=attribute();

            	    state._fsp--;

            	    ((decl_section_scope)decl_section_stack.peek()).local_features.add(a);

            	    }
            	    break;

            	default :
            	    if ( cnt22 >= 1 ) break loop22;
                        EarlyExitException eee =
                            new EarlyExitException(22, input);
                        throw eee;
                }
                cnt22++;
            } while (true);


            match(input, Token.UP, null); 

              		features = ((decl_section_scope)decl_section_stack.peek()).local_features;
              	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            decl_section_stack.pop();
        }
        return features;
    }
    // $ANTLR end "decl_section"


    // $ANTLR start "attribute"
    // src/LCPLTreeChecker.g:192:1: attribute returns [Attribute result] : ( ^( ATTRIBUTE type ID ) | ^( ATTRIBUTE type ID e= expression ) | ^( ATTRIBUTE_ARRAY type ID ) | ^( ATTRIBUTE_ARRAY type ID e= expression ) );
    public final Attribute attribute() throws RecognitionException {
        Attribute result = null;

        CommonTree ATTRIBUTE32=null;
        CommonTree ID33=null;
        CommonTree ATTRIBUTE35=null;
        CommonTree ID36=null;
        CommonTree ATTRIBUTE_ARRAY38=null;
        CommonTree ID39=null;
        CommonTree ATTRIBUTE_ARRAY41=null;
        CommonTree ID42=null;
        Expression e = null;

        String type34 = null;

        String type37 = null;

        String type40 = null;

        String type43 = null;


        try {
            // src/LCPLTreeChecker.g:193:2: ( ^( ATTRIBUTE type ID ) | ^( ATTRIBUTE type ID e= expression ) | ^( ATTRIBUTE_ARRAY type ID ) | ^( ATTRIBUTE_ARRAY type ID e= expression ) )
            int alt23=4;
            alt23 = dfa23.predict(input);
            switch (alt23) {
                case 1 :
                    // src/LCPLTreeChecker.g:193:4: ^( ATTRIBUTE type ID )
                    {
                    ATTRIBUTE32=(CommonTree)match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_attribute652); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_type_in_attribute654);
                    type34=type();

                    state._fsp--;

                    ID33=(CommonTree)match(input,ID,FOLLOW_ID_in_attribute656); 

                    match(input, Token.UP, null); 

                    		result = new Attribute((ATTRIBUTE32!=null?ATTRIBUTE32.getLine():0), (ID33!=null?ID33.getText():null), type34, null);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:197:4: ^( ATTRIBUTE type ID e= expression )
                    {
                    ATTRIBUTE35=(CommonTree)match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_attribute666); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_type_in_attribute668);
                    type37=type();

                    state._fsp--;

                    ID36=(CommonTree)match(input,ID,FOLLOW_ID_in_attribute670); 
                    pushFollow(FOLLOW_expression_in_attribute676);
                    e=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new Attribute((ATTRIBUTE35!=null?ATTRIBUTE35.getLine():0), (ID36!=null?ID36.getText():null), type37, e);
                    	

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:201:4: ^( ATTRIBUTE_ARRAY type ID )
                    {
                    ATTRIBUTE_ARRAY38=(CommonTree)match(input,ATTRIBUTE_ARRAY,FOLLOW_ATTRIBUTE_ARRAY_in_attribute686); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_type_in_attribute688);
                    type40=type();

                    state._fsp--;

                    ID39=(CommonTree)match(input,ID,FOLLOW_ID_in_attribute690); 

                    match(input, Token.UP, null); 

                    		result = new AttributeArray((ATTRIBUTE_ARRAY38!=null?ATTRIBUTE_ARRAY38.getLine():0), (ID39!=null?ID39.getText():null), type40, null);
                    	

                    }
                    break;
                case 4 :
                    // src/LCPLTreeChecker.g:205:4: ^( ATTRIBUTE_ARRAY type ID e= expression )
                    {
                    ATTRIBUTE_ARRAY41=(CommonTree)match(input,ATTRIBUTE_ARRAY,FOLLOW_ATTRIBUTE_ARRAY_in_attribute700); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_type_in_attribute702);
                    type43=type();

                    state._fsp--;

                    ID42=(CommonTree)match(input,ID,FOLLOW_ID_in_attribute704); 
                    pushFollow(FOLLOW_expression_in_attribute710);
                    e=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new AttributeArray((ATTRIBUTE_ARRAY41!=null?ATTRIBUTE_ARRAY41.getLine():0), (ID42!=null?ID42.getText():null), type43, e);
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "attribute"


    // $ANTLR start "statement"
    // src/LCPLTreeChecker.g:211:1: statement returns [Expression result] : ( ^( IF cond= expression ifBlock= block (thenBlock= block )? ) | ^( WHILE cond= expression loopBody= block ) );
    public final Expression statement() throws RecognitionException {
        Expression result = null;

        CommonTree IF44=null;
        CommonTree WHILE45=null;
        Expression cond = null;

        Block ifBlock = null;

        Block thenBlock = null;

        Block loopBody = null;


        try {
            // src/LCPLTreeChecker.g:212:2: ( ^( IF cond= expression ifBlock= block (thenBlock= block )? ) | ^( WHILE cond= expression loopBody= block ) )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==IF) ) {
                alt25=1;
            }
            else if ( (LA25_0==WHILE) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // src/LCPLTreeChecker.g:212:4: ^( IF cond= expression ifBlock= block (thenBlock= block )? )
                    {
                    IF44=(CommonTree)match(input,IF,FOLLOW_IF_in_statement730); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_statement734);
                    cond=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_block_in_statement740);
                    ifBlock=block();

                    state._fsp--;

                    // src/LCPLTreeChecker.g:212:51: (thenBlock= block )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==BLOCK) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // src/LCPLTreeChecker.g:212:51: thenBlock= block
                            {
                            pushFollow(FOLLOW_block_in_statement746);
                            thenBlock=block();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                    		result = new IfStatement((IF44!=null?IF44.getLine():0), cond, ifBlock, thenBlock);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:216:4: ^( WHILE cond= expression loopBody= block )
                    {
                    WHILE45=(CommonTree)match(input,WHILE,FOLLOW_WHILE_in_statement757); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_statement763);
                    cond=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_block_in_statement769);
                    loopBody=block();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new WhileStatement((WHILE45!=null?WHILE45.getLine():0), cond, loopBody);
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "statement"


    // $ANTLR start "cast"
    // src/LCPLTreeChecker.g:222:1: cast returns [Expression result] : ^( CAST type e= expression ) ;
    public final Expression cast() throws RecognitionException {
        Expression result = null;

        CommonTree CAST46=null;
        Expression e = null;

        String type47 = null;


        try {
            // src/LCPLTreeChecker.g:223:2: ( ^( CAST type e= expression ) )
            // src/LCPLTreeChecker.g:223:4: ^( CAST type e= expression )
            {
            CAST46=(CommonTree)match(input,CAST,FOLLOW_CAST_in_cast788); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_cast790);
            type47=type();

            state._fsp--;

            pushFollow(FOLLOW_expression_in_cast796);
            e=expression();

            state._fsp--;


            match(input, Token.UP, null); 

            		result = new Cast((CAST46!=null?CAST46.getLine():0), type47, e);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "cast"


    // $ANTLR start "expression"
    // src/LCPLTreeChecker.g:227:1: expression returns [Expression result] : ^( EXPRESSION e= assignmentExpression ) ;
    public final Expression expression() throws RecognitionException {
        Expression result = null;

        Expression e = null;


        try {
            // src/LCPLTreeChecker.g:228:2: ( ^( EXPRESSION e= assignmentExpression ) )
            // src/LCPLTreeChecker.g:228:4: ^( EXPRESSION e= assignmentExpression )
            {
            match(input,EXPRESSION,FOLLOW_EXPRESSION_in_expression813); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_assignmentExpression_in_expression817);
            e=assignmentExpression();

            state._fsp--;


            match(input, Token.UP, null); 

            		result = e;
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "expression"


    // $ANTLR start "assignmentExpression"
    // src/LCPLTreeChecker.g:233:1: assignmentExpression returns [Expression result] : ( ^( EQ e= equalityExpression b= assignmentExpression ) | ^( EQ 'self.' ID b= assignmentExpression ) | e= equalityExpression );
    public final Expression assignmentExpression() throws RecognitionException {
        Expression result = null;

        CommonTree EQ48=null;
        CommonTree ID49=null;
        CommonTree EQ50=null;
        Expression e = null;

        Expression b = null;


        try {
            // src/LCPLTreeChecker.g:234:2: ( ^( EQ e= equalityExpression b= assignmentExpression ) | ^( EQ 'self.' ID b= assignmentExpression ) | e= equalityExpression )
            int alt26=3;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==EQ) ) {
                int LA26_1 = input.LA(2);

                if ( (LA26_1==DOWN) ) {
                    int LA26_3 = input.LA(3);

                    if ( (LA26_3==75) ) {
                        alt26=2;
                    }
                    else if ( (LA26_3==CONSTANT_INT||(LA26_3>=CONSTANT_STRING && LA26_3<=SYMBOL)||(LA26_3>=UNARY_MINUS && LA26_3<=PAR_EXPR)||LA26_3==DISPATCH||LA26_3==NEW||LA26_3==DISPATCH_S||(LA26_3>=VOID_CONSTANT && LA26_3<=CAST)||(LA26_3>=IF && LA26_3<=SUBSTR)||LA26_3==UNARY_BRACKET||(LA26_3>=EQUAL && LA26_3<=NOT)) ) {
                        alt26=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 26, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 26, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA26_0==CONSTANT_INT||(LA26_0>=CONSTANT_STRING && LA26_0<=SYMBOL)||(LA26_0>=UNARY_MINUS && LA26_0<=PAR_EXPR)||LA26_0==DISPATCH||LA26_0==NEW||LA26_0==DISPATCH_S||(LA26_0>=VOID_CONSTANT && LA26_0<=CAST)||(LA26_0>=IF && LA26_0<=SUBSTR)||LA26_0==UNARY_BRACKET||(LA26_0>=EQUAL && LA26_0<=NOT)) ) {
                alt26=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // src/LCPLTreeChecker.g:234:4: ^( EQ e= equalityExpression b= assignmentExpression )
                    {
                    EQ48=(CommonTree)match(input,EQ,FOLLOW_EQ_in_assignmentExpression835); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_equalityExpression_in_assignmentExpression841);
                    e=equalityExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_assignmentExpression_in_assignmentExpression845);
                    b=assignmentExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		String name = "";
                    		if (e instanceof ArrayAccess) {
                    			Expression type = ((ArrayAccess)e).getExpression();
                    			Expression index = ((ArrayAccess)e).getValue();
                    			result = new ArrayAssignment((EQ48!=null?EQ48.getLine():0), type, index, b); 
                    		} else {
                    			name = ((Symbol)e).getName();
                    			result = new Assignment((EQ48!=null?EQ48.getLine():0), name, b);
                    		}
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:246:4: ^( EQ 'self.' ID b= assignmentExpression )
                    {
                    EQ50=(CommonTree)match(input,EQ,FOLLOW_EQ_in_assignmentExpression855); 

                    match(input, Token.DOWN, null); 
                    match(input,75,FOLLOW_75_in_assignmentExpression857); 
                    ID49=(CommonTree)match(input,ID,FOLLOW_ID_in_assignmentExpression859); 
                    pushFollow(FOLLOW_assignmentExpression_in_assignmentExpression865);
                    b=assignmentExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		String name = "self." + (ID49!=null?ID49.getText():null);
                    		result = new Assignment((EQ50!=null?EQ50.getLine():0), name, b);
                    	

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:251:4: e= equalityExpression
                    {
                    pushFollow(FOLLOW_equalityExpression_in_assignmentExpression878);
                    e=equalityExpression();

                    state._fsp--;


                    		result = e;
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "assignmentExpression"


    // $ANTLR start "equalityExpression"
    // src/LCPLTreeChecker.g:258:1: equalityExpression returns [Expression result] : ( ^( EQUAL a= equalityExpression b= relationalExpression ) | e= relationalExpression );
    public final Expression equalityExpression() throws RecognitionException {
        Expression result = null;

        CommonTree EQUAL51=null;
        Expression a = null;

        Expression b = null;

        Expression e = null;


        try {
            // src/LCPLTreeChecker.g:259:2: ( ^( EQUAL a= equalityExpression b= relationalExpression ) | e= relationalExpression )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==EQUAL) ) {
                alt27=1;
            }
            else if ( (LA27_0==CONSTANT_INT||(LA27_0>=CONSTANT_STRING && LA27_0<=SYMBOL)||(LA27_0>=UNARY_MINUS && LA27_0<=PAR_EXPR)||LA27_0==DISPATCH||LA27_0==NEW||LA27_0==DISPATCH_S||(LA27_0>=VOID_CONSTANT && LA27_0<=CAST)||(LA27_0>=IF && LA27_0<=SUBSTR)||LA27_0==UNARY_BRACKET||(LA27_0>=LTEQ && LA27_0<=NOT)) ) {
                alt27=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // src/LCPLTreeChecker.g:259:4: ^( EQUAL a= equalityExpression b= relationalExpression )
                    {
                    EQUAL51=(CommonTree)match(input,EQUAL,FOLLOW_EQUAL_in_equalityExpression898); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_equalityExpression_in_equalityExpression902);
                    a=equalityExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_relationalExpression_in_equalityExpression907);
                    b=relationalExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new EqualComparison((EQUAL51!=null?EQUAL51.getLine():0), a, b);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:263:4: e= relationalExpression
                    {
                    pushFollow(FOLLOW_relationalExpression_in_equalityExpression920);
                    e=relationalExpression();

                    state._fsp--;


                    		result = e;
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "equalityExpression"


    // $ANTLR start "relationalExpression"
    // src/LCPLTreeChecker.g:268:1: relationalExpression returns [Expression result] : ( ^( LTEQ a= relationalExpression b= additiveExpression ) | ^( LT a= relationalExpression b= additiveExpression ) | e= additiveExpression );
    public final Expression relationalExpression() throws RecognitionException {
        Expression result = null;

        CommonTree LTEQ52=null;
        CommonTree LT53=null;
        Expression a = null;

        Expression b = null;

        Expression e = null;


        try {
            // src/LCPLTreeChecker.g:269:2: ( ^( LTEQ a= relationalExpression b= additiveExpression ) | ^( LT a= relationalExpression b= additiveExpression ) | e= additiveExpression )
            int alt28=3;
            switch ( input.LA(1) ) {
            case LTEQ:
                {
                alt28=1;
                }
                break;
            case LT:
                {
                alt28=2;
                }
                break;
            case CONSTANT_INT:
            case CONSTANT_STRING:
            case SYMBOL:
            case UNARY_MINUS:
            case PAR_EXPR:
            case DISPATCH:
            case NEW:
            case DISPATCH_S:
            case VOID_CONSTANT:
            case CAST:
            case IF:
            case WHILE:
            case SELF:
            case SUBSTR:
            case UNARY_BRACKET:
            case PLUS:
            case SUB:
            case STAR:
            case DIV:
            case NOT:
                {
                alt28=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }

            switch (alt28) {
                case 1 :
                    // src/LCPLTreeChecker.g:269:4: ^( LTEQ a= relationalExpression b= additiveExpression )
                    {
                    LTEQ52=(CommonTree)match(input,LTEQ,FOLLOW_LTEQ_in_relationalExpression937); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_relationalExpression_in_relationalExpression941);
                    a=relationalExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_additiveExpression_in_relationalExpression945);
                    b=additiveExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new LessThanEqual((LTEQ52!=null?LTEQ52.getLine():0), a, b);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:273:4: ^( LT a= relationalExpression b= additiveExpression )
                    {
                    LT53=(CommonTree)match(input,LT,FOLLOW_LT_in_relationalExpression955); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_relationalExpression_in_relationalExpression959);
                    a=relationalExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_additiveExpression_in_relationalExpression963);
                    b=additiveExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    	
                    		result = new LessThan((LT53!=null?LT53.getLine():0), a, b);
                    	

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:277:4: e= additiveExpression
                    {
                    pushFollow(FOLLOW_additiveExpression_in_relationalExpression976);
                    e=additiveExpression();

                    state._fsp--;


                    		result = e;
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "relationalExpression"


    // $ANTLR start "additiveExpression"
    // src/LCPLTreeChecker.g:282:1: additiveExpression returns [Expression result] : ( ^( PLUS a= additiveExpression b= multiplicativeExpression ) | ^( SUB a= additiveExpression b= multiplicativeExpression ) | e= multiplicativeExpression );
    public final Expression additiveExpression() throws RecognitionException {
        Expression result = null;

        CommonTree PLUS54=null;
        CommonTree SUB55=null;
        Expression a = null;

        Expression b = null;

        Expression e = null;


        try {
            // src/LCPLTreeChecker.g:283:2: ( ^( PLUS a= additiveExpression b= multiplicativeExpression ) | ^( SUB a= additiveExpression b= multiplicativeExpression ) | e= multiplicativeExpression )
            int alt29=3;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt29=1;
                }
                break;
            case SUB:
                {
                alt29=2;
                }
                break;
            case CONSTANT_INT:
            case CONSTANT_STRING:
            case SYMBOL:
            case UNARY_MINUS:
            case PAR_EXPR:
            case DISPATCH:
            case NEW:
            case DISPATCH_S:
            case VOID_CONSTANT:
            case CAST:
            case IF:
            case WHILE:
            case SELF:
            case SUBSTR:
            case UNARY_BRACKET:
            case STAR:
            case DIV:
            case NOT:
                {
                alt29=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }

            switch (alt29) {
                case 1 :
                    // src/LCPLTreeChecker.g:283:4: ^( PLUS a= additiveExpression b= multiplicativeExpression )
                    {
                    PLUS54=(CommonTree)match(input,PLUS,FOLLOW_PLUS_in_additiveExpression993); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_additiveExpression_in_additiveExpression999);
                    a=additiveExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression1005);
                    b=multiplicativeExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new Addition((PLUS54!=null?PLUS54.getLine():0), a, b);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:287:4: ^( SUB a= additiveExpression b= multiplicativeExpression )
                    {
                    SUB55=(CommonTree)match(input,SUB,FOLLOW_SUB_in_additiveExpression1015); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_additiveExpression_in_additiveExpression1021);
                    a=additiveExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression1027);
                    b=multiplicativeExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new Subtraction((SUB55!=null?SUB55.getLine():0), a, b);
                    	

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:291:4: e= multiplicativeExpression
                    {
                    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression1040);
                    e=multiplicativeExpression();

                    state._fsp--;


                    		result = e;
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "additiveExpression"


    // $ANTLR start "multiplicativeExpression"
    // src/LCPLTreeChecker.g:296:1: multiplicativeExpression returns [Expression result] : ( ^( STAR a= multiplicativeExpression b= unaryExpression ) | ^( DIV a= multiplicativeExpression b= unaryExpression ) | e= unaryExpression );
    public final Expression multiplicativeExpression() throws RecognitionException {
        Expression result = null;

        CommonTree STAR56=null;
        CommonTree DIV57=null;
        Expression a = null;

        Expression b = null;

        Expression e = null;


        try {
            // src/LCPLTreeChecker.g:297:2: ( ^( STAR a= multiplicativeExpression b= unaryExpression ) | ^( DIV a= multiplicativeExpression b= unaryExpression ) | e= unaryExpression )
            int alt30=3;
            switch ( input.LA(1) ) {
            case STAR:
                {
                alt30=1;
                }
                break;
            case DIV:
                {
                alt30=2;
                }
                break;
            case CONSTANT_INT:
            case CONSTANT_STRING:
            case SYMBOL:
            case UNARY_MINUS:
            case PAR_EXPR:
            case DISPATCH:
            case NEW:
            case DISPATCH_S:
            case VOID_CONSTANT:
            case CAST:
            case IF:
            case WHILE:
            case SELF:
            case SUBSTR:
            case UNARY_BRACKET:
            case NOT:
                {
                alt30=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // src/LCPLTreeChecker.g:297:4: ^( STAR a= multiplicativeExpression b= unaryExpression )
                    {
                    STAR56=(CommonTree)match(input,STAR,FOLLOW_STAR_in_multiplicativeExpression1058); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_multiplicativeExpression_in_multiplicativeExpression1064);
                    a=multiplicativeExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression1070);
                    b=unaryExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new Multiplication((STAR56!=null?STAR56.getLine():0), a, b);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:301:4: ^( DIV a= multiplicativeExpression b= unaryExpression )
                    {
                    DIV57=(CommonTree)match(input,DIV,FOLLOW_DIV_in_multiplicativeExpression1080); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_multiplicativeExpression_in_multiplicativeExpression1086);
                    a=multiplicativeExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression1092);
                    b=unaryExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new Division((DIV57!=null?DIV57.getLine():0), a, b);
                    	

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:305:4: e= unaryExpression
                    {
                    pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression1105);
                    e=unaryExpression();

                    state._fsp--;


                    		result = e;
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "multiplicativeExpression"


    // $ANTLR start "unaryExpression"
    // src/LCPLTreeChecker.g:311:1: unaryExpression returns [Expression result] : ( ^( UNARY_MINUS e= unaryExpression ) | simpleUnaryExpression );
    public final Expression unaryExpression() throws RecognitionException {
        Expression result = null;

        CommonTree UNARY_MINUS58=null;
        Expression e = null;

        Expression simpleUnaryExpression59 = null;


        try {
            // src/LCPLTreeChecker.g:312:2: ( ^( UNARY_MINUS e= unaryExpression ) | simpleUnaryExpression )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==UNARY_MINUS) ) {
                alt31=1;
            }
            else if ( (LA31_0==CONSTANT_INT||(LA31_0>=CONSTANT_STRING && LA31_0<=SYMBOL)||LA31_0==PAR_EXPR||LA31_0==DISPATCH||LA31_0==NEW||LA31_0==DISPATCH_S||(LA31_0>=VOID_CONSTANT && LA31_0<=CAST)||(LA31_0>=IF && LA31_0<=SUBSTR)||LA31_0==UNARY_BRACKET||LA31_0==NOT) ) {
                alt31=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // src/LCPLTreeChecker.g:312:4: ^( UNARY_MINUS e= unaryExpression )
                    {
                    UNARY_MINUS58=(CommonTree)match(input,UNARY_MINUS,FOLLOW_UNARY_MINUS_in_unaryExpression1124); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression1130);
                    e=unaryExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new UnaryMinus((UNARY_MINUS58!=null?UNARY_MINUS58.getLine():0), e);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:316:4: simpleUnaryExpression
                    {
                    pushFollow(FOLLOW_simpleUnaryExpression_in_unaryExpression1139);
                    simpleUnaryExpression59=simpleUnaryExpression();

                    state._fsp--;


                    		result = simpleUnaryExpression59;
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "unaryExpression"


    // $ANTLR start "simpleUnaryExpression"
    // src/LCPLTreeChecker.g:321:1: simpleUnaryExpression returns [Expression result] : ( ^( NOT e= unaryExpression ) | ^( SUBSTR e= simpleUnaryExpression x= expression ) | ^( UNARY_BRACKET e= simpleUnaryExpression s= expression ) | primaryExpression );
    public final Expression simpleUnaryExpression() throws RecognitionException {
        Expression result = null;

        CommonTree NOT60=null;
        CommonTree SUBSTR61=null;
        CommonTree UNARY_BRACKET62=null;
        Expression e = null;

        Expression x = null;

        Expression s = null;

        Expression primaryExpression63 = null;


        try {
            // src/LCPLTreeChecker.g:322:2: ( ^( NOT e= unaryExpression ) | ^( SUBSTR e= simpleUnaryExpression x= expression ) | ^( UNARY_BRACKET e= simpleUnaryExpression s= expression ) | primaryExpression )
            int alt32=4;
            switch ( input.LA(1) ) {
            case NOT:
                {
                alt32=1;
                }
                break;
            case SUBSTR:
                {
                alt32=2;
                }
                break;
            case UNARY_BRACKET:
                {
                alt32=3;
                }
                break;
            case CONSTANT_INT:
            case CONSTANT_STRING:
            case SYMBOL:
            case PAR_EXPR:
            case DISPATCH:
            case NEW:
            case DISPATCH_S:
            case VOID_CONSTANT:
            case CAST:
            case IF:
            case WHILE:
            case SELF:
                {
                alt32=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }

            switch (alt32) {
                case 1 :
                    // src/LCPLTreeChecker.g:322:4: ^( NOT e= unaryExpression )
                    {
                    NOT60=(CommonTree)match(input,NOT,FOLLOW_NOT_in_simpleUnaryExpression1157); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_unaryExpression_in_simpleUnaryExpression1163);
                    e=unaryExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new LogicalNegation((NOT60!=null?NOT60.getLine():0), e);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:326:4: ^( SUBSTR e= simpleUnaryExpression x= expression )
                    {
                    SUBSTR61=(CommonTree)match(input,SUBSTR,FOLLOW_SUBSTR_in_simpleUnaryExpression1173); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_simpleUnaryExpression_in_simpleUnaryExpression1179);
                    e=simpleUnaryExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_simpleUnaryExpression1185);
                    x=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new SubString((SUBSTR61!=null?SUBSTR61.getLine():0), ((UnaryBracket)e).getExpression(),
                    						 ((UnaryBracket)e).getValue(), x);
                    	

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:331:4: ^( UNARY_BRACKET e= simpleUnaryExpression s= expression )
                    {
                    UNARY_BRACKET62=(CommonTree)match(input,UNARY_BRACKET,FOLLOW_UNARY_BRACKET_in_simpleUnaryExpression1195); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_simpleUnaryExpression_in_simpleUnaryExpression1202);
                    e=simpleUnaryExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_simpleUnaryExpression1208);
                    s=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		if (e instanceof NewObject) {
                    			result = new NewObjectArray((UNARY_BRACKET62!=null?UNARY_BRACKET62.getLine():0), e, s);
                    		} else {
                    			result = new ArrayAccess((UNARY_BRACKET62!=null?UNARY_BRACKET62.getLine():0), e, s);
                    		}
                    	

                    }
                    break;
                case 4 :
                    // src/LCPLTreeChecker.g:339:4: primaryExpression
                    {
                    pushFollow(FOLLOW_primaryExpression_in_simpleUnaryExpression1217);
                    primaryExpression63=primaryExpression();

                    state._fsp--;


                    		result = primaryExpression63;
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "simpleUnaryExpression"


    // $ANTLR start "primaryExpression"
    // src/LCPLTreeChecker.g:344:1: primaryExpression returns [Expression result] : ( ^( PAR_EXPR e= expression ) | literal | dispatch | new_object | cast | statement );
    public final Expression primaryExpression() throws RecognitionException {
        Expression result = null;

        Expression e = null;

        Expression literal64 = null;

        Expression dispatch65 = null;

        NewObject new_object66 = null;

        Expression cast67 = null;

        Expression statement68 = null;


        try {
            // src/LCPLTreeChecker.g:345:2: ( ^( PAR_EXPR e= expression ) | literal | dispatch | new_object | cast | statement )
            int alt33=6;
            switch ( input.LA(1) ) {
            case PAR_EXPR:
                {
                alt33=1;
                }
                break;
            case CONSTANT_INT:
            case CONSTANT_STRING:
            case SYMBOL:
            case VOID_CONSTANT:
            case SELF:
                {
                alt33=2;
                }
                break;
            case DISPATCH:
            case DISPATCH_S:
                {
                alt33=3;
                }
                break;
            case NEW:
                {
                alt33=4;
                }
                break;
            case CAST:
                {
                alt33=5;
                }
                break;
            case IF:
            case WHILE:
                {
                alt33=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }

            switch (alt33) {
                case 1 :
                    // src/LCPLTreeChecker.g:345:4: ^( PAR_EXPR e= expression )
                    {
                    match(input,PAR_EXPR,FOLLOW_PAR_EXPR_in_primaryExpression1234); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_primaryExpression1240);
                    e=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = e;
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:349:4: literal
                    {
                    pushFollow(FOLLOW_literal_in_primaryExpression1249);
                    literal64=literal();

                    state._fsp--;


                    		result = literal64;
                    	

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:353:4: dispatch
                    {
                    pushFollow(FOLLOW_dispatch_in_primaryExpression1257);
                    dispatch65=dispatch();

                    state._fsp--;


                    		result = dispatch65;
                    	

                    }
                    break;
                case 4 :
                    // src/LCPLTreeChecker.g:357:4: new_object
                    {
                    pushFollow(FOLLOW_new_object_in_primaryExpression1265);
                    new_object66=new_object();

                    state._fsp--;


                    		result = new_object66;
                    	

                    }
                    break;
                case 5 :
                    // src/LCPLTreeChecker.g:361:4: cast
                    {
                    pushFollow(FOLLOW_cast_in_primaryExpression1273);
                    cast67=cast();

                    state._fsp--;


                    		result = cast67;
                    	

                    }
                    break;
                case 6 :
                    // src/LCPLTreeChecker.g:365:4: statement
                    {
                    pushFollow(FOLLOW_statement_in_primaryExpression1281);
                    statement68=statement();

                    state._fsp--;


                    		result = statement68;
                    	

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "primaryExpression"


    // $ANTLR start "literal"
    // src/LCPLTreeChecker.g:370:1: literal returns [Expression result] : ( integer | string | ^( SYMBOL type ) | VOID_CONSTANT | SELF );
    public final Expression literal() throws RecognitionException {
        Expression result = null;

        CommonTree SYMBOL71=null;
        CommonTree VOID_CONSTANT73=null;
        CommonTree SELF74=null;
        IntConstant integer69 = null;

        StringConstant string70 = null;

        String type72 = null;


        try {
            // src/LCPLTreeChecker.g:371:2: ( integer | string | ^( SYMBOL type ) | VOID_CONSTANT | SELF )
            int alt34=5;
            switch ( input.LA(1) ) {
            case CONSTANT_INT:
                {
                alt34=1;
                }
                break;
            case CONSTANT_STRING:
                {
                alt34=2;
                }
                break;
            case SYMBOL:
                {
                alt34=3;
                }
                break;
            case VOID_CONSTANT:
                {
                alt34=4;
                }
                break;
            case SELF:
                {
                alt34=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // src/LCPLTreeChecker.g:371:4: integer
                    {
                    pushFollow(FOLLOW_integer_in_literal1296);
                    integer69=integer();

                    state._fsp--;

                    result = integer69;

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:372:4: string
                    {
                    pushFollow(FOLLOW_string_in_literal1304);
                    string70=string();

                    state._fsp--;

                    result = string70;

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:373:4: ^( SYMBOL type )
                    {
                    SYMBOL71=(CommonTree)match(input,SYMBOL,FOLLOW_SYMBOL_in_literal1313); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_type_in_literal1315);
                    type72=type();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    result = new Symbol((SYMBOL71!=null?SYMBOL71.getLine():0), type72);

                    }
                    break;
                case 4 :
                    // src/LCPLTreeChecker.g:374:4: VOID_CONSTANT
                    {
                    VOID_CONSTANT73=(CommonTree)match(input,VOID_CONSTANT,FOLLOW_VOID_CONSTANT_in_literal1323); 
                    result = new VoidConstant((VOID_CONSTANT73!=null?VOID_CONSTANT73.getLine():0));

                    }
                    break;
                case 5 :
                    // src/LCPLTreeChecker.g:375:4: SELF
                    {
                    SELF74=(CommonTree)match(input,SELF,FOLLOW_SELF_in_literal1330); 
                    result = new Symbol((SELF74!=null?SELF74.getLine():0), "self");

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "literal"


    // $ANTLR start "integer"
    // src/LCPLTreeChecker.g:378:1: integer returns [IntConstant result] : ^( CONSTANT_INT value= INTEGER ) ;
    public final IntConstant integer() throws RecognitionException {
        IntConstant result = null;

        CommonTree value=null;
        CommonTree CONSTANT_INT75=null;

        try {
            // src/LCPLTreeChecker.g:379:2: ( ^( CONSTANT_INT value= INTEGER ) )
            // src/LCPLTreeChecker.g:379:4: ^( CONSTANT_INT value= INTEGER )
            {
            CONSTANT_INT75=(CommonTree)match(input,CONSTANT_INT,FOLLOW_CONSTANT_INT_in_integer1351); 

            match(input, Token.DOWN, null); 
            value=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_integer1355); 

            match(input, Token.UP, null); 

            		result = new IntConstant((CONSTANT_INT75!=null?CONSTANT_INT75.getLine():0), Integer.valueOf((value!=null?value.getText():null)));
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "integer"


    // $ANTLR start "string"
    // src/LCPLTreeChecker.g:384:1: string returns [StringConstant result] : ^( CONSTANT_STRING value= STRING_LITERAL ) ;
    public final StringConstant string() throws RecognitionException {
        StringConstant result = null;

        CommonTree value=null;
        CommonTree CONSTANT_STRING76=null;

        try {
            // src/LCPLTreeChecker.g:385:2: ( ^( CONSTANT_STRING value= STRING_LITERAL ) )
            // src/LCPLTreeChecker.g:385:4: ^( CONSTANT_STRING value= STRING_LITERAL )
            {
            CONSTANT_STRING76=(CommonTree)match(input,CONSTANT_STRING,FOLLOW_CONSTANT_STRING_in_string1374); 

            match(input, Token.DOWN, null); 
            value=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_string1378); 

            match(input, Token.UP, null); 

            		String s = (value!=null?value.getText():null).substring(1, (value!=null?value.getText():null).length() - 1);
            		StringBuilder newString = new StringBuilder();
            		for (int i = 0; i < s.length(); i++) {
            			char c = s.charAt(i);
            			if (c == '\\' && (i + 1) < s.length()) {
            				c = s.charAt(i + 1);
            				i++;
            				if (c == 'n') {
            					newString.append('\n');
            				} else if (c == 'r') {
            					newString.append('\r');
            				} else if (c == 't') {
            					newString.append('\t');
            				} else {
            					newString.append(c);
            				}
            			} else if (c != '\\') {
            				newString.append(c);
            			}
            		}
            		result = new StringConstant((CONSTANT_STRING76!=null?CONSTANT_STRING76.getLine():0), newString.toString());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "string"


    // $ANTLR start "new_object"
    // src/LCPLTreeChecker.g:410:1: new_object returns [NewObject result] : ^( NEW type ) ;
    public final NewObject new_object() throws RecognitionException {
        NewObject result = null;

        CommonTree NEW77=null;
        String type78 = null;


        try {
            // src/LCPLTreeChecker.g:411:2: ( ^( NEW type ) )
            // src/LCPLTreeChecker.g:411:4: ^( NEW type )
            {
            NEW77=(CommonTree)match(input,NEW,FOLLOW_NEW_in_new_object1395); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_new_object1397);
            type78=type();

            state._fsp--;


            match(input, Token.UP, null); 

            		result = new NewObject((NEW77!=null?NEW77.getLine():0), type78);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "new_object"


    // $ANTLR start "type"
    // src/LCPLTreeChecker.g:416:1: type returns [String result] : ( ^( TYPE INT ) | ^( TYPE STRING ) | ^( TYPE ID ) );
    public final String type() throws RecognitionException {
        String result = null;

        CommonTree ID79=null;

        try {
            // src/LCPLTreeChecker.g:417:2: ( ^( TYPE INT ) | ^( TYPE STRING ) | ^( TYPE ID ) )
            int alt35=3;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==TYPE) ) {
                int LA35_1 = input.LA(2);

                if ( (LA35_1==DOWN) ) {
                    switch ( input.LA(3) ) {
                    case INT:
                        {
                        alt35=1;
                        }
                        break;
                    case STRING:
                        {
                        alt35=2;
                        }
                        break;
                    case ID:
                        {
                        alt35=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 35, 2, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 35, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // src/LCPLTreeChecker.g:417:4: ^( TYPE INT )
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_type1416); 

                    match(input, Token.DOWN, null); 
                    match(input,INT,FOLLOW_INT_in_type1418); 

                    match(input, Token.UP, null); 
                     result = "Int"; 

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:418:4: ^( TYPE STRING )
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_type1427); 

                    match(input, Token.DOWN, null); 
                    match(input,STRING,FOLLOW_STRING_in_type1429); 

                    match(input, Token.UP, null); 
                    result = "String"; 

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:419:4: ^( TYPE ID )
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_type1438); 

                    match(input, Token.DOWN, null); 
                    ID79=(CommonTree)match(input,ID,FOLLOW_ID_in_type1440); 

                    match(input, Token.UP, null); 
                     result = (ID79!=null?ID79.getText():null);   

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return result;
    }
    // $ANTLR end "type"

    // Delegated rules


    protected DFA23 dfa23 = new DFA23(this);
    static final String DFA23_eotS =
        "\33\uffff";
    static final String DFA23_eofS =
        "\33\uffff";
    static final String DFA23_minS =
        "\1\5\2\2\2\40\2\2\2\41\6\3\6\54\2\3\4\uffff";
    static final String DFA23_maxS =
        "\1\34\2\2\2\40\2\2\2\54\6\3\6\54\2\37\4\uffff";
    static final String DFA23_acceptS =
        "\27\uffff\1\1\1\2\1\3\1\4";
    static final String DFA23_specialS =
        "\33\uffff}>";
    static final String[] DFA23_transitionS = {
            "\1\2\26\uffff\1\1",
            "\1\3",
            "\1\4",
            "\1\5",
            "\1\6",
            "\1\7",
            "\1\10",
            "\1\11\1\12\11\uffff\1\13",
            "\1\14\1\15\11\uffff\1\16",
            "\1\17",
            "\1\20",
            "\1\21",
            "\1\22",
            "\1\23",
            "\1\24",
            "\1\25",
            "\1\25",
            "\1\25",
            "\1\26",
            "\1\26",
            "\1\26",
            "\1\27\33\uffff\1\30",
            "\1\31\33\uffff\1\32",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA23_eot = DFA.unpackEncodedString(DFA23_eotS);
    static final short[] DFA23_eof = DFA.unpackEncodedString(DFA23_eofS);
    static final char[] DFA23_min = DFA.unpackEncodedStringToUnsignedChars(DFA23_minS);
    static final char[] DFA23_max = DFA.unpackEncodedStringToUnsignedChars(DFA23_maxS);
    static final short[] DFA23_accept = DFA.unpackEncodedString(DFA23_acceptS);
    static final short[] DFA23_special = DFA.unpackEncodedString(DFA23_specialS);
    static final short[][] DFA23_transition;

    static {
        int numStates = DFA23_transitionS.length;
        DFA23_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA23_transition[i] = DFA.unpackEncodedString(DFA23_transitionS[i]);
        }
    }

    class DFA23 extends DFA {

        public DFA23(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 23;
            this.eot = DFA23_eot;
            this.eof = DFA23_eof;
            this.min = DFA23_min;
            this.max = DFA23_max;
            this.accept = DFA23_accept;
            this.special = DFA23_special;
            this.transition = DFA23_transition;
        }
        public String getDescription() {
            return "192:1: attribute returns [Attribute result] : ( ^( ATTRIBUTE type ID ) | ^( ATTRIBUTE type ID e= expression ) | ^( ATTRIBUTE_ARRAY type ID ) | ^( ATTRIBUTE_ARRAY type ID e= expression ) );";
        }
    }
 

    public static final BitSet FOLLOW_PROGRAM_in_program56 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_class_definition_in_program58 = new BitSet(new long[]{0x0000000000000048L});
    public static final BitSet FOLLOW_CLASSDEFINITION_in_class_definition78 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_classdef_in_class_definition80 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CLASS_in_classdef103 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_classdef107 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_classdef111 = new BitSet(new long[]{0x0000000008000008L});
    public static final BitSet FOLLOW_members_in_classdef113 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CLASS_in_classdef130 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_classdef134 = new BitSet(new long[]{0x0000000008000008L});
    public static final BitSet FOLLOW_members_in_classdef136 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EMPTY_METHOD_in_method181 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_method183 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_METHOD_in_method201 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_method203 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_block_in_method205 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_METHOD_in_method221 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_method223 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_formal_param_in_method230 = new BitSet(new long[]{0x0000000100008100L});
    public static final BitSet FOLLOW_type_in_method240 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_block_in_method243 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_METHOD_in_method259 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_method261 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_type_in_method263 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_block_in_method265 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOCAL_in_local_decl306 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_attribute_in_local_decl313 = new BitSet(new long[]{0x0000000010008028L});
    public static final BitSet FOLLOW_block_in_local_decl319 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLOCK_in_block353 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_block360 = new BitSet(new long[]{0x0000040080000008L});
    public static final BitSet FOLLOW_local_decl_in_block371 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLOCK_in_block385 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_local_decl_in_block387 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MEMBERS_in_members419 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_member_in_members421 = new BitSet(new long[]{0x0000000004000008L});
    public static final BitSet FOLLOW_MEMBER_in_member436 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_decl_section_in_member438 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MEMBER_in_member448 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_method_in_member450 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FORMAL_PARAM_in_formal_param469 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_formal_param471 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_formal_param473 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DISPATCH_in_dispatch491 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_primaryExpression_in_dispatch498 = new BitSet(new long[]{0x0000100100000000L});
    public static final BitSet FOLLOW_type_in_dispatch507 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_dispatch511 = new BitSet(new long[]{0x0000000000200008L});
    public static final BitSet FOLLOW_dispatch_arguments_in_dispatch518 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DISPATCH_S_in_dispatch530 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_dispatch532 = new BitSet(new long[]{0x0000000100200008L});
    public static final BitSet FOLLOW_type_in_dispatch539 = new BitSet(new long[]{0x0000000000200008L});
    public static final BitSet FOLLOW_dispatch_arguments_in_dispatch546 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DISPATCH_ARGUMENTS_in_dispatch_arguments578 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_dispatch_arguments585 = new BitSet(new long[]{0x0000040080000008L});
    public static final BitSet FOLLOW_DECL_SECTION_in_decl_section618 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_attribute_in_decl_section625 = new BitSet(new long[]{0x0000000010008028L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_attribute652 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_attribute654 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_attribute656 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_attribute666 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_attribute668 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_attribute670 = new BitSet(new long[]{0x0000040080000008L});
    public static final BitSet FOLLOW_expression_in_attribute676 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ATTRIBUTE_ARRAY_in_attribute686 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_attribute688 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_attribute690 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ATTRIBUTE_ARRAY_in_attribute700 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_attribute702 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_attribute704 = new BitSet(new long[]{0x0000040080000008L});
    public static final BitSet FOLLOW_expression_in_attribute710 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_statement730 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statement734 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_block_in_statement740 = new BitSet(new long[]{0x0000000000008008L});
    public static final BitSet FOLLOW_block_in_statement746 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_WHILE_in_statement757 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statement763 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_block_in_statement769 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CAST_in_cast788 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_cast790 = new BitSet(new long[]{0x0000040080000008L});
    public static final BitSet FOLLOW_expression_in_cast796 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXPRESSION_in_expression813 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_assignmentExpression_in_expression817 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EQ_in_assignmentExpression835 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_equalityExpression_in_assignmentExpression841 = new BitSet(new long[]{0x7FEF682000106680L});
    public static final BitSet FOLLOW_assignmentExpression_in_assignmentExpression845 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EQ_in_assignmentExpression855 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_75_in_assignmentExpression857 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_assignmentExpression859 = new BitSet(new long[]{0x7FEF682000106680L});
    public static final BitSet FOLLOW_assignmentExpression_in_assignmentExpression865 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_equalityExpression_in_assignmentExpression878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUAL_in_equalityExpression898 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_equalityExpression_in_equalityExpression902 = new BitSet(new long[]{0x7FEF682000106680L});
    public static final BitSet FOLLOW_relationalExpression_in_equalityExpression907 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_relationalExpression_in_equalityExpression920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LTEQ_in_relationalExpression937 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_relationalExpression_in_relationalExpression941 = new BitSet(new long[]{0x7FEF682000106680L});
    public static final BitSet FOLLOW_additiveExpression_in_relationalExpression945 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_relationalExpression955 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_relationalExpression_in_relationalExpression959 = new BitSet(new long[]{0x7FEF682000106680L});
    public static final BitSet FOLLOW_additiveExpression_in_relationalExpression963 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_additiveExpression_in_relationalExpression976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_additiveExpression993 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_additiveExpression_in_additiveExpression999 = new BitSet(new long[]{0x7FEF682000106680L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1005 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SUB_in_additiveExpression1015 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_additiveExpression_in_additiveExpression1021 = new BitSet(new long[]{0x7FEF682000106680L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1027 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_multiplicativeExpression1058 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_multiplicativeExpression1064 = new BitSet(new long[]{0x7FEF682000106680L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression1070 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DIV_in_multiplicativeExpression1080 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_multiplicativeExpression1086 = new BitSet(new long[]{0x7FEF682000106680L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression1092 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression1105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNARY_MINUS_in_unaryExpression1124 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression1130 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_simpleUnaryExpression_in_unaryExpression1139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_simpleUnaryExpression1157 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_unaryExpression_in_simpleUnaryExpression1163 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SUBSTR_in_simpleUnaryExpression1173 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_simpleUnaryExpression_in_simpleUnaryExpression1179 = new BitSet(new long[]{0x0000040080000008L});
    public static final BitSet FOLLOW_expression_in_simpleUnaryExpression1185 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_UNARY_BRACKET_in_simpleUnaryExpression1195 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_simpleUnaryExpression_in_simpleUnaryExpression1202 = new BitSet(new long[]{0x0000040080000008L});
    public static final BitSet FOLLOW_expression_in_simpleUnaryExpression1208 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_primaryExpression_in_simpleUnaryExpression1217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PAR_EXPR_in_primaryExpression1234 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_primaryExpression1240 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_literal_in_primaryExpression1249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dispatch_in_primaryExpression1257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new_object_in_primaryExpression1265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cast_in_primaryExpression1273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_primaryExpression1281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_integer_in_literal1296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_literal1304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SYMBOL_in_literal1313 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_literal1315 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_VOID_CONSTANT_in_literal1323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELF_in_literal1330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONSTANT_INT_in_integer1351 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INTEGER_in_integer1355 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CONSTANT_STRING_in_string1374 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_string1378 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEW_in_new_object1395 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_new_object1397 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TYPE_in_type1416 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INT_in_type1418 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TYPE_in_type1427 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_type1429 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TYPE_in_type1438 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_type1440 = new BitSet(new long[]{0x0000000000000008L});

}