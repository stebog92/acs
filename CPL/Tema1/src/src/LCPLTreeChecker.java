// $ANTLR 3.2 Sep 23, 2009 12:02:23 src/LCPLTreeChecker.g 2013-11-11 12:56:05

    import java.util.LinkedList;
    import ro.pub.cs.lcpl.*;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class LCPLTreeChecker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PROGRAM", "CLASSDEFINITION", "CONSTANT_INT", "FORMAL_PARAM", "CONSTANT_STRING", "SYMBOL", "EQ_COMP", "DECL_SECTION", "UNARY_MINUS", "PAR_EXPR", "BLOCK", "OBJ", "EXPR", "EMPTY_DISPATCH", "LOCAL_DECL", "DISPATCH", "DISPATCH_ARGUMENTS", "DECLARATION_SECTION", "EMPTY_METHOD", "DISPATCH_ARGS", "MEMBER", "MEMBERS", "ATTRIBUTE", "ASSIGNMENT", "ATTR", "EXPRESSION", "TYPE", "INT", "STRING", "CLASS", "INHERITS", "NEW", "METHOD", "STATEMENT", "END", "NULL", "LOCAL", "ID", "VOID_CONSTANT", "CAST", "STRING_CONST", "IF", "WHILE", "SUBSTR", "EQ", "EQUAL", "LTEQ", "LT", "PLUS", "SUB", "STAR", "DIV", "NOT", "INTEGER", "STRING_LITERAL", "ESCAPE_SEQUENCE", "LINE_COMMENT", "WS", "';'", "':'", "'->'", "','", "'['", "'.'", "']'", "'var'", "'then'", "'else'", "'loop'", "'{'", "'}'", "'('", "')'"
    };
    public static final int CAST=43;
    public static final int T__68=68;
    public static final int CLASSDEFINITION=5;
    public static final int T__69=69;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int CLASS=33;
    public static final int LT=51;
    public static final int MEMBER=24;
    public static final int DISPATCH_ARGUMENTS=20;
    public static final int T__64=64;
    public static final int STAR=54;
    public static final int T__65=65;
    public static final int T__62=62;
    public static final int WHILE=46;
    public static final int T__63=63;
    public static final int DISPATCH=19;
    public static final int LTEQ=50;
    public static final int ATTRIBUTE=26;
    public static final int NEW=35;
    public static final int SUB=53;
    public static final int NOT=56;
    public static final int VOID_CONSTANT=42;
    public static final int ID=41;
    public static final int EOF=-1;
    public static final int STATEMENT=37;
    public static final int IF=45;
    public static final int TYPE=30;
    public static final int ESCAPE_SEQUENCE=59;
    public static final int STRING_LITERAL=58;
    public static final int EXPR=16;
    public static final int DISPATCH_ARGS=23;
    public static final int INHERITS=34;
    public static final int SUBSTR=47;
    public static final int OBJ=15;
    public static final int LOCAL_DECL=18;
    public static final int EMPTY_METHOD=22;
    public static final int STRING_CONST=44;
    public static final int EQUAL=49;
    public static final int PLUS=52;
    public static final int EQ=48;
    public static final int EXPRESSION=29;
    public static final int ATTR=28;
    public static final int INTEGER=57;
    public static final int CONSTANT_INT=6;
    public static final int SYMBOL=9;
    public static final int LINE_COMMENT=60;
    public static final int NULL=39;
    public static final int MEMBERS=25;
    public static final int UNARY_MINUS=12;
    public static final int INT=31;
    public static final int DECL_SECTION=11;
    public static final int LOCAL=40;
    public static final int PAR_EXPR=13;
    public static final int EQ_COMP=10;
    public static final int FORMAL_PARAM=7;
    public static final int T__71=71;
    public static final int WS=61;
    public static final int T__72=72;
    public static final int T__70=70;
    public static final int DECLARATION_SECTION=21;
    public static final int BLOCK=14;
    public static final int CONSTANT_STRING=8;
    public static final int PROGRAM=4;
    public static final int ASSIGNMENT=27;
    public static final int DIV=55;
    public static final int T__76=76;
    public static final int END=38;
    public static final int T__75=75;
    public static final int T__74=74;
    public static final int T__73=73;
    public static final int METHOD=36;
    public static final int EMPTY_DISPATCH=17;
    public static final int STRING=32;

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
    // src/LCPLTreeChecker.g:17:1: program returns [Program result] : ^( PROGRAM ( class_definition )+ ) ;
    public final Program program() throws RecognitionException {
        Program result = null;

        CommonTree PROGRAM1=null;

        try {
            // src/LCPLTreeChecker.g:18:5: ( ^( PROGRAM ( class_definition )+ ) )
            // src/LCPLTreeChecker.g:18:7: ^( PROGRAM ( class_definition )+ )
            {
            PROGRAM1=(CommonTree)match(input,PROGRAM,FOLLOW_PROGRAM_in_program56); 

            match(input, Token.DOWN, null); 
            // src/LCPLTreeChecker.g:18:17: ( class_definition )+
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
            	    // src/LCPLTreeChecker.g:18:17: class_definition
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
    // src/LCPLTreeChecker.g:23:1: class_definition : ^( CLASSDEFINITION classdef ) ;
    public final void class_definition() throws RecognitionException {
        LCPLClass classdef2 = null;


        try {
            // src/LCPLTreeChecker.g:24:1: ( ^( CLASSDEFINITION classdef ) )
            // src/LCPLTreeChecker.g:24:2: ^( CLASSDEFINITION classdef )
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
    // src/LCPLTreeChecker.g:30:1: classdef returns [LCPLClass result] : ( ^( CLASS name= ID parent= ID ( members )? ) | ^( CLASS name= ID ( members )? ) );
    public final LCPLClass classdef() throws RecognitionException {
        LCPLClass result = null;

        CommonTree name=null;
        CommonTree parent=null;
        CommonTree CLASS4=null;
        CommonTree CLASS6=null;
        ArrayList<Feature> members3 = null;

        ArrayList<Feature> members5 = null;


        try {
            // src/LCPLTreeChecker.g:31:5: ( ^( CLASS name= ID parent= ID ( members )? ) | ^( CLASS name= ID ( members )? ) )
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
                    // src/LCPLTreeChecker.g:31:9: ^( CLASS name= ID parent= ID ( members )? )
                    {
                    CLASS4=(CommonTree)match(input,CLASS,FOLLOW_CLASS_in_classdef103); 

                    match(input, Token.DOWN, null); 
                    name=(CommonTree)match(input,ID,FOLLOW_ID_in_classdef107); 
                    parent=(CommonTree)match(input,ID,FOLLOW_ID_in_classdef111); 
                    // src/LCPLTreeChecker.g:31:35: ( members )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==MEMBERS) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // src/LCPLTreeChecker.g:31:35: members
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
                    // src/LCPLTreeChecker.g:36:7: ^( CLASS name= ID ( members )? )
                    {
                    CLASS6=(CommonTree)match(input,CLASS,FOLLOW_CLASS_in_classdef130); 

                    match(input, Token.DOWN, null); 
                    name=(CommonTree)match(input,ID,FOLLOW_ID_in_classdef134); 
                    // src/LCPLTreeChecker.g:36:23: ( members )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==MEMBERS) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // src/LCPLTreeChecker.g:36:23: members
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
    // src/LCPLTreeChecker.g:43:1: method returns [Method result] : ( ^( EMPTY_METHOD ID ) | ^( METHOD ID block ) | ^( METHOD ID (param= formal_param )+ (t= type )? block ) | ^( METHOD ID type block ) );
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
            // src/LCPLTreeChecker.g:50:5: ( ^( EMPTY_METHOD ID ) | ^( METHOD ID block ) | ^( METHOD ID (param= formal_param )+ (t= type )? block ) | ^( METHOD ID type block ) )
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
                    // src/LCPLTreeChecker.g:50:9: ^( EMPTY_METHOD ID )
                    {
                    EMPTY_METHOD7=(CommonTree)match(input,EMPTY_METHOD,FOLLOW_EMPTY_METHOD_in_method181); 

                    match(input, Token.DOWN, null); 
                    ID8=(CommonTree)match(input,ID,FOLLOW_ID_in_method183); 

                    match(input, Token.UP, null); 

                            result = new Method((EMPTY_METHOD7!=null?EMPTY_METHOD7.getLine():0), (ID8!=null?ID8.getText():null), new ArrayList<FormalParam>(), "void", new Block(0, new ArrayList<Expression>()));
                        

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:54:9: ^( METHOD ID block )
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
                    // src/LCPLTreeChecker.g:58:7: ^( METHOD ID (param= formal_param )+ (t= type )? block )
                    {
                    METHOD12=(CommonTree)match(input,METHOD,FOLLOW_METHOD_in_method221); 

                    match(input, Token.DOWN, null); 
                    ID13=(CommonTree)match(input,ID,FOLLOW_ID_in_method223); 
                    // src/LCPLTreeChecker.g:58:19: (param= formal_param )+
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
                    	    // src/LCPLTreeChecker.g:58:20: param= formal_param
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

                    // src/LCPLTreeChecker.g:58:87: (t= type )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==TYPE) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // src/LCPLTreeChecker.g:58:87: t= type
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
                    // src/LCPLTreeChecker.g:68:7: ^( METHOD ID type block )
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
    // src/LCPLTreeChecker.g:74:1: local_decl returns [LocalDefinition result] : ^( LOCAL (a= attribute )+ ( block )? ) ;
    public final LocalDefinition local_decl() throws RecognitionException {
        local_decl_stack.push(new local_decl_scope());
        LocalDefinition result = null;

        CommonTree LOCAL19=null;
        Attribute a = null;

        Block block20 = null;



        		((local_decl_scope)local_decl_stack.peek()).exprs = new ArrayList<Attribute>();
        	
        try {
            // src/LCPLTreeChecker.g:81:2: ( ^( LOCAL (a= attribute )+ ( block )? ) )
            // src/LCPLTreeChecker.g:81:4: ^( LOCAL (a= attribute )+ ( block )? )
            {
            LOCAL19=(CommonTree)match(input,LOCAL,FOLLOW_LOCAL_in_local_decl306); 

            match(input, Token.DOWN, null); 
            // src/LCPLTreeChecker.g:81:12: (a= attribute )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==ATTRIBUTE) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // src/LCPLTreeChecker.g:81:13: a= attribute
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

            // src/LCPLTreeChecker.g:81:67: ( block )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==BLOCK) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // src/LCPLTreeChecker.g:81:67: block
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
            		System.out.println("local def " + line);
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
    // src/LCPLTreeChecker.g:147:1: dispatch returns [Expression result] : ^( DISPATCH (o= primaryExpression )? (t= type )? ID (b= dispatch_arguments )? ) ;
    public final Expression dispatch() throws RecognitionException {
        Expression result = null;

        CommonTree DISPATCH28=null;
        CommonTree ID29=null;
        Expression o = null;

        String t = null;

        ArrayList<Expression> b = null;


        try {
            // src/LCPLTreeChecker.g:148:2: ( ^( DISPATCH (o= primaryExpression )? (t= type )? ID (b= dispatch_arguments )? ) )
            // src/LCPLTreeChecker.g:148:4: ^( DISPATCH (o= primaryExpression )? (t= type )? ID (b= dispatch_arguments )? )
            {
            DISPATCH28=(CommonTree)match(input,DISPATCH,FOLLOW_DISPATCH_in_dispatch491); 

            match(input, Token.DOWN, null); 
            // src/LCPLTreeChecker.g:148:15: (o= primaryExpression )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==CONSTANT_INT||(LA15_0>=CONSTANT_STRING && LA15_0<=SYMBOL)||LA15_0==PAR_EXPR||LA15_0==DISPATCH||LA15_0==NEW||(LA15_0>=VOID_CONSTANT && LA15_0<=CAST)||(LA15_0>=IF && LA15_0<=WHILE)) ) {
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

            ID29=(CommonTree)match(input,ID,FOLLOW_ID_in_dispatch510); 
            // src/LCPLTreeChecker.g:148:54: (b= dispatch_arguments )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==DISPATCH_ARGUMENTS) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // src/LCPLTreeChecker.g:148:55: b= dispatch_arguments
                    {
                    pushFollow(FOLLOW_dispatch_arguments_in_dispatch517);
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
    // src/LCPLTreeChecker.g:159:1: dispatch_arguments returns [ArrayList<Expression> args] : ^( DISPATCH_ARGUMENTS (i= expression )+ ) ;
    public final ArrayList<Expression> dispatch_arguments() throws RecognitionException {
        dispatch_arguments_stack.push(new dispatch_arguments_scope());
        ArrayList<Expression> args = null;

        Expression i = null;



        		((dispatch_arguments_scope)dispatch_arguments_stack.peek()).exprs = new ArrayList<Expression>();
        	
        try {
            // src/LCPLTreeChecker.g:166:2: ( ^( DISPATCH_ARGUMENTS (i= expression )+ ) )
            // src/LCPLTreeChecker.g:166:4: ^( DISPATCH_ARGUMENTS (i= expression )+ )
            {
            match(input,DISPATCH_ARGUMENTS,FOLLOW_DISPATCH_ARGUMENTS_in_dispatch_arguments550); 

            match(input, Token.DOWN, null); 
            // src/LCPLTreeChecker.g:166:25: (i= expression )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==EXPRESSION) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // src/LCPLTreeChecker.g:166:26: i= expression
            	    {
            	    pushFollow(FOLLOW_expression_in_dispatch_arguments557);
            	    i=expression();

            	    state._fsp--;

            	     ((dispatch_arguments_scope)dispatch_arguments_stack.peek()).exprs.add(i);

            	    }
            	    break;

            	default :
            	    if ( cnt18 >= 1 ) break loop18;
                        EarlyExitException eee =
                            new EarlyExitException(18, input);
                        throw eee;
                }
                cnt18++;
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
    // src/LCPLTreeChecker.g:171:1: decl_section returns [ArrayList<Feature> features] : ^( DECL_SECTION (a= attribute )+ ) ;
    public final ArrayList<Feature> decl_section() throws RecognitionException {
        decl_section_stack.push(new decl_section_scope());
        ArrayList<Feature> features = null;

        Attribute a = null;



        		((decl_section_scope)decl_section_stack.peek()).local_features = new ArrayList<Feature>();
        	
        try {
            // src/LCPLTreeChecker.g:178:2: ( ^( DECL_SECTION (a= attribute )+ ) )
            // src/LCPLTreeChecker.g:178:4: ^( DECL_SECTION (a= attribute )+ )
            {
            match(input,DECL_SECTION,FOLLOW_DECL_SECTION_in_decl_section590); 

            match(input, Token.DOWN, null); 
            // src/LCPLTreeChecker.g:178:19: (a= attribute )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==ATTRIBUTE) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // src/LCPLTreeChecker.g:178:20: a= attribute
            	    {
            	    pushFollow(FOLLOW_attribute_in_decl_section597);
            	    a=attribute();

            	    state._fsp--;

            	    ((decl_section_scope)decl_section_stack.peek()).local_features.add(a);

            	    }
            	    break;

            	default :
            	    if ( cnt19 >= 1 ) break loop19;
                        EarlyExitException eee =
                            new EarlyExitException(19, input);
                        throw eee;
                }
                cnt19++;
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
    // src/LCPLTreeChecker.g:183:1: attribute returns [Attribute result] : ( ^( ATTRIBUTE type ID ) | ^( ATTRIBUTE type ID e= expression ) );
    public final Attribute attribute() throws RecognitionException {
        Attribute result = null;

        CommonTree ATTRIBUTE30=null;
        CommonTree ID31=null;
        CommonTree ATTRIBUTE33=null;
        CommonTree ID34=null;
        Expression e = null;

        String type32 = null;

        String type35 = null;


        try {
            // src/LCPLTreeChecker.g:184:2: ( ^( ATTRIBUTE type ID ) | ^( ATTRIBUTE type ID e= expression ) )
            int alt20=2;
            alt20 = dfa20.predict(input);
            switch (alt20) {
                case 1 :
                    // src/LCPLTreeChecker.g:184:4: ^( ATTRIBUTE type ID )
                    {
                    ATTRIBUTE30=(CommonTree)match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_attribute624); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_type_in_attribute626);
                    type32=type();

                    state._fsp--;

                    ID31=(CommonTree)match(input,ID,FOLLOW_ID_in_attribute628); 

                    match(input, Token.UP, null); 

                    		result = new Attribute((ATTRIBUTE30!=null?ATTRIBUTE30.getLine():0), (ID31!=null?ID31.getText():null), type32, null);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:188:4: ^( ATTRIBUTE type ID e= expression )
                    {
                    ATTRIBUTE33=(CommonTree)match(input,ATTRIBUTE,FOLLOW_ATTRIBUTE_in_attribute638); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_type_in_attribute640);
                    type35=type();

                    state._fsp--;

                    ID34=(CommonTree)match(input,ID,FOLLOW_ID_in_attribute642); 
                    pushFollow(FOLLOW_expression_in_attribute648);
                    e=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new Attribute((ATTRIBUTE33!=null?ATTRIBUTE33.getLine():0), (ID34!=null?ID34.getText():null), type35, e);
                    	

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
    // src/LCPLTreeChecker.g:193:1: statement returns [Expression result] : ( ^( IF cond= expression ifBlock= block (thenBlock= block )? ) | ^( WHILE cond= expression loopBody= block ) );
    public final Expression statement() throws RecognitionException {
        Expression result = null;

        CommonTree IF36=null;
        CommonTree WHILE37=null;
        Expression cond = null;

        Block ifBlock = null;

        Block thenBlock = null;

        Block loopBody = null;


        try {
            // src/LCPLTreeChecker.g:194:2: ( ^( IF cond= expression ifBlock= block (thenBlock= block )? ) | ^( WHILE cond= expression loopBody= block ) )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==IF) ) {
                alt22=1;
            }
            else if ( (LA22_0==WHILE) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // src/LCPLTreeChecker.g:194:4: ^( IF cond= expression ifBlock= block (thenBlock= block )? )
                    {
                    IF36=(CommonTree)match(input,IF,FOLLOW_IF_in_statement666); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_statement670);
                    cond=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_block_in_statement676);
                    ifBlock=block();

                    state._fsp--;

                    // src/LCPLTreeChecker.g:194:51: (thenBlock= block )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==BLOCK) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // src/LCPLTreeChecker.g:194:51: thenBlock= block
                            {
                            pushFollow(FOLLOW_block_in_statement682);
                            thenBlock=block();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input, Token.UP, null); 

                    		result = new IfStatement((IF36!=null?IF36.getLine():0), cond, ifBlock, thenBlock);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:198:4: ^( WHILE cond= expression loopBody= block )
                    {
                    WHILE37=(CommonTree)match(input,WHILE,FOLLOW_WHILE_in_statement693); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_statement699);
                    cond=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_block_in_statement705);
                    loopBody=block();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new WhileStatement((WHILE37!=null?WHILE37.getLine():0), cond, loopBody);
                    	

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
    // src/LCPLTreeChecker.g:204:1: cast returns [Expression result] : ^( CAST type e= expression ) ;
    public final Expression cast() throws RecognitionException {
        Expression result = null;

        CommonTree CAST38=null;
        Expression e = null;

        String type39 = null;


        try {
            // src/LCPLTreeChecker.g:205:2: ( ^( CAST type e= expression ) )
            // src/LCPLTreeChecker.g:205:4: ^( CAST type e= expression )
            {
            CAST38=(CommonTree)match(input,CAST,FOLLOW_CAST_in_cast724); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_cast726);
            type39=type();

            state._fsp--;

            pushFollow(FOLLOW_expression_in_cast732);
            e=expression();

            state._fsp--;


            match(input, Token.UP, null); 

            		result = new Cast((CAST38!=null?CAST38.getLine():0), type39, e);
            	

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
    // src/LCPLTreeChecker.g:209:1: expression returns [Expression result] : ^( EXPRESSION e= assignmentExpression ) ;
    public final Expression expression() throws RecognitionException {
        Expression result = null;

        Expression e = null;


        try {
            // src/LCPLTreeChecker.g:210:2: ( ^( EXPRESSION e= assignmentExpression ) )
            // src/LCPLTreeChecker.g:210:4: ^( EXPRESSION e= assignmentExpression )
            {
            match(input,EXPRESSION,FOLLOW_EXPRESSION_in_expression749); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_assignmentExpression_in_expression753);
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
    // src/LCPLTreeChecker.g:215:1: assignmentExpression returns [Expression result] : ( ^( EQ e= equalityExpression b= assignmentExpression ) | e= equalityExpression );
    public final Expression assignmentExpression() throws RecognitionException {
        Expression result = null;

        CommonTree EQ40=null;
        Expression e = null;

        Expression b = null;


        try {
            // src/LCPLTreeChecker.g:216:2: ( ^( EQ e= equalityExpression b= assignmentExpression ) | e= equalityExpression )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==EQ) ) {
                alt23=1;
            }
            else if ( (LA23_0==CONSTANT_INT||(LA23_0>=CONSTANT_STRING && LA23_0<=SYMBOL)||(LA23_0>=UNARY_MINUS && LA23_0<=PAR_EXPR)||LA23_0==DISPATCH||LA23_0==NEW||(LA23_0>=VOID_CONSTANT && LA23_0<=CAST)||(LA23_0>=IF && LA23_0<=SUBSTR)||(LA23_0>=EQUAL && LA23_0<=NOT)) ) {
                alt23=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // src/LCPLTreeChecker.g:216:4: ^( EQ e= equalityExpression b= assignmentExpression )
                    {
                    EQ40=(CommonTree)match(input,EQ,FOLLOW_EQ_in_assignmentExpression771); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_equalityExpression_in_assignmentExpression777);
                    e=equalityExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_assignmentExpression_in_assignmentExpression781);
                    b=assignmentExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new Assignment((EQ40!=null?EQ40.getLine():0), ((Symbol)e).getName(), b);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:220:4: e= equalityExpression
                    {
                    pushFollow(FOLLOW_equalityExpression_in_assignmentExpression794);
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
    // src/LCPLTreeChecker.g:227:1: equalityExpression returns [Expression result] : ( ^( EQUAL a= equalityExpression b= relationalExpression ) | e= relationalExpression );
    public final Expression equalityExpression() throws RecognitionException {
        Expression result = null;

        CommonTree EQUAL41=null;
        Expression a = null;

        Expression b = null;

        Expression e = null;


        try {
            // src/LCPLTreeChecker.g:228:2: ( ^( EQUAL a= equalityExpression b= relationalExpression ) | e= relationalExpression )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==EQUAL) ) {
                alt24=1;
            }
            else if ( (LA24_0==CONSTANT_INT||(LA24_0>=CONSTANT_STRING && LA24_0<=SYMBOL)||(LA24_0>=UNARY_MINUS && LA24_0<=PAR_EXPR)||LA24_0==DISPATCH||LA24_0==NEW||(LA24_0>=VOID_CONSTANT && LA24_0<=CAST)||(LA24_0>=IF && LA24_0<=SUBSTR)||(LA24_0>=LTEQ && LA24_0<=NOT)) ) {
                alt24=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // src/LCPLTreeChecker.g:228:4: ^( EQUAL a= equalityExpression b= relationalExpression )
                    {
                    EQUAL41=(CommonTree)match(input,EQUAL,FOLLOW_EQUAL_in_equalityExpression814); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_equalityExpression_in_equalityExpression818);
                    a=equalityExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_relationalExpression_in_equalityExpression823);
                    b=relationalExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new EqualComparison((EQUAL41!=null?EQUAL41.getLine():0), a, b);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:232:4: e= relationalExpression
                    {
                    pushFollow(FOLLOW_relationalExpression_in_equalityExpression836);
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
    // src/LCPLTreeChecker.g:237:1: relationalExpression returns [Expression result] : ( ^( LTEQ a= relationalExpression b= additiveExpression ) | ^( LT a= relationalExpression b= additiveExpression ) | e= additiveExpression );
    public final Expression relationalExpression() throws RecognitionException {
        Expression result = null;

        CommonTree LTEQ42=null;
        CommonTree LT43=null;
        Expression a = null;

        Expression b = null;

        Expression e = null;


        try {
            // src/LCPLTreeChecker.g:238:2: ( ^( LTEQ a= relationalExpression b= additiveExpression ) | ^( LT a= relationalExpression b= additiveExpression ) | e= additiveExpression )
            int alt25=3;
            switch ( input.LA(1) ) {
            case LTEQ:
                {
                alt25=1;
                }
                break;
            case LT:
                {
                alt25=2;
                }
                break;
            case CONSTANT_INT:
            case CONSTANT_STRING:
            case SYMBOL:
            case UNARY_MINUS:
            case PAR_EXPR:
            case DISPATCH:
            case NEW:
            case VOID_CONSTANT:
            case CAST:
            case IF:
            case WHILE:
            case SUBSTR:
            case PLUS:
            case SUB:
            case STAR:
            case DIV:
            case NOT:
                {
                alt25=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // src/LCPLTreeChecker.g:238:4: ^( LTEQ a= relationalExpression b= additiveExpression )
                    {
                    LTEQ42=(CommonTree)match(input,LTEQ,FOLLOW_LTEQ_in_relationalExpression853); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_relationalExpression_in_relationalExpression857);
                    a=relationalExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_additiveExpression_in_relationalExpression861);
                    b=additiveExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new LessThanEqual((LTEQ42!=null?LTEQ42.getLine():0), a, b);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:242:4: ^( LT a= relationalExpression b= additiveExpression )
                    {
                    LT43=(CommonTree)match(input,LT,FOLLOW_LT_in_relationalExpression871); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_relationalExpression_in_relationalExpression875);
                    a=relationalExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_additiveExpression_in_relationalExpression879);
                    b=additiveExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    	
                    		result = new LessThan((LT43!=null?LT43.getLine():0), a, b);
                    	

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:246:4: e= additiveExpression
                    {
                    pushFollow(FOLLOW_additiveExpression_in_relationalExpression892);
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
    // src/LCPLTreeChecker.g:251:1: additiveExpression returns [Expression result] : ( ^( PLUS a= additiveExpression b= multiplicativeExpression ) | ^( SUB a= additiveExpression b= multiplicativeExpression ) | e= multiplicativeExpression );
    public final Expression additiveExpression() throws RecognitionException {
        Expression result = null;

        CommonTree PLUS44=null;
        CommonTree SUB45=null;
        Expression a = null;

        Expression b = null;

        Expression e = null;


        try {
            // src/LCPLTreeChecker.g:252:2: ( ^( PLUS a= additiveExpression b= multiplicativeExpression ) | ^( SUB a= additiveExpression b= multiplicativeExpression ) | e= multiplicativeExpression )
            int alt26=3;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt26=1;
                }
                break;
            case SUB:
                {
                alt26=2;
                }
                break;
            case CONSTANT_INT:
            case CONSTANT_STRING:
            case SYMBOL:
            case UNARY_MINUS:
            case PAR_EXPR:
            case DISPATCH:
            case NEW:
            case VOID_CONSTANT:
            case CAST:
            case IF:
            case WHILE:
            case SUBSTR:
            case STAR:
            case DIV:
            case NOT:
                {
                alt26=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // src/LCPLTreeChecker.g:252:4: ^( PLUS a= additiveExpression b= multiplicativeExpression )
                    {
                    PLUS44=(CommonTree)match(input,PLUS,FOLLOW_PLUS_in_additiveExpression909); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_additiveExpression_in_additiveExpression915);
                    a=additiveExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression921);
                    b=multiplicativeExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new Addition((PLUS44!=null?PLUS44.getLine():0), a, b);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:256:4: ^( SUB a= additiveExpression b= multiplicativeExpression )
                    {
                    SUB45=(CommonTree)match(input,SUB,FOLLOW_SUB_in_additiveExpression931); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_additiveExpression_in_additiveExpression937);
                    a=additiveExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression943);
                    b=multiplicativeExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new Subtraction((SUB45!=null?SUB45.getLine():0), a, b);
                    	

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:260:4: e= multiplicativeExpression
                    {
                    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression956);
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
    // src/LCPLTreeChecker.g:265:1: multiplicativeExpression returns [Expression result] : ( ^( STAR a= multiplicativeExpression b= unaryExpression ) | ^( DIV a= multiplicativeExpression b= unaryExpression ) | e= unaryExpression );
    public final Expression multiplicativeExpression() throws RecognitionException {
        Expression result = null;

        CommonTree STAR46=null;
        CommonTree DIV47=null;
        Expression a = null;

        Expression b = null;

        Expression e = null;


        try {
            // src/LCPLTreeChecker.g:266:2: ( ^( STAR a= multiplicativeExpression b= unaryExpression ) | ^( DIV a= multiplicativeExpression b= unaryExpression ) | e= unaryExpression )
            int alt27=3;
            switch ( input.LA(1) ) {
            case STAR:
                {
                alt27=1;
                }
                break;
            case DIV:
                {
                alt27=2;
                }
                break;
            case CONSTANT_INT:
            case CONSTANT_STRING:
            case SYMBOL:
            case UNARY_MINUS:
            case PAR_EXPR:
            case DISPATCH:
            case NEW:
            case VOID_CONSTANT:
            case CAST:
            case IF:
            case WHILE:
            case SUBSTR:
            case NOT:
                {
                alt27=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }

            switch (alt27) {
                case 1 :
                    // src/LCPLTreeChecker.g:266:4: ^( STAR a= multiplicativeExpression b= unaryExpression )
                    {
                    STAR46=(CommonTree)match(input,STAR,FOLLOW_STAR_in_multiplicativeExpression974); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_multiplicativeExpression_in_multiplicativeExpression980);
                    a=multiplicativeExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression986);
                    b=unaryExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new Multiplication((STAR46!=null?STAR46.getLine():0), a, b);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:270:4: ^( DIV a= multiplicativeExpression b= unaryExpression )
                    {
                    DIV47=(CommonTree)match(input,DIV,FOLLOW_DIV_in_multiplicativeExpression996); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_multiplicativeExpression_in_multiplicativeExpression1002);
                    a=multiplicativeExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression1008);
                    b=unaryExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new Division((DIV47!=null?DIV47.getLine():0), a, b);
                    	

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:274:4: e= unaryExpression
                    {
                    pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression1021);
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
    // src/LCPLTreeChecker.g:280:1: unaryExpression returns [Expression result] : ( ^( UNARY_MINUS e= unaryExpression ) | simpleUnaryExpression );
    public final Expression unaryExpression() throws RecognitionException {
        Expression result = null;

        CommonTree UNARY_MINUS48=null;
        Expression e = null;

        Expression simpleUnaryExpression49 = null;


        try {
            // src/LCPLTreeChecker.g:281:2: ( ^( UNARY_MINUS e= unaryExpression ) | simpleUnaryExpression )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==UNARY_MINUS) ) {
                alt28=1;
            }
            else if ( (LA28_0==CONSTANT_INT||(LA28_0>=CONSTANT_STRING && LA28_0<=SYMBOL)||LA28_0==PAR_EXPR||LA28_0==DISPATCH||LA28_0==NEW||(LA28_0>=VOID_CONSTANT && LA28_0<=CAST)||(LA28_0>=IF && LA28_0<=SUBSTR)||LA28_0==NOT) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // src/LCPLTreeChecker.g:281:4: ^( UNARY_MINUS e= unaryExpression )
                    {
                    UNARY_MINUS48=(CommonTree)match(input,UNARY_MINUS,FOLLOW_UNARY_MINUS_in_unaryExpression1040); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression1046);
                    e=unaryExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new UnaryMinus((UNARY_MINUS48!=null?UNARY_MINUS48.getLine():0), e);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:285:4: simpleUnaryExpression
                    {
                    pushFollow(FOLLOW_simpleUnaryExpression_in_unaryExpression1055);
                    simpleUnaryExpression49=simpleUnaryExpression();

                    state._fsp--;


                    		result = simpleUnaryExpression49;
                    	

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
    // src/LCPLTreeChecker.g:290:1: simpleUnaryExpression returns [Expression result] : ( ^( NOT e= unaryExpression ) | ^( SUBSTR e= simpleUnaryExpression start= expression end= expression ) | primaryExpression );
    public final Expression simpleUnaryExpression() throws RecognitionException {
        Expression result = null;

        CommonTree NOT50=null;
        CommonTree SUBSTR51=null;
        Expression e = null;

        Expression start = null;

        Expression end = null;

        Expression primaryExpression52 = null;


        try {
            // src/LCPLTreeChecker.g:291:2: ( ^( NOT e= unaryExpression ) | ^( SUBSTR e= simpleUnaryExpression start= expression end= expression ) | primaryExpression )
            int alt29=3;
            switch ( input.LA(1) ) {
            case NOT:
                {
                alt29=1;
                }
                break;
            case SUBSTR:
                {
                alt29=2;
                }
                break;
            case CONSTANT_INT:
            case CONSTANT_STRING:
            case SYMBOL:
            case PAR_EXPR:
            case DISPATCH:
            case NEW:
            case VOID_CONSTANT:
            case CAST:
            case IF:
            case WHILE:
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
                    // src/LCPLTreeChecker.g:291:4: ^( NOT e= unaryExpression )
                    {
                    NOT50=(CommonTree)match(input,NOT,FOLLOW_NOT_in_simpleUnaryExpression1073); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_unaryExpression_in_simpleUnaryExpression1079);
                    e=unaryExpression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new LogicalNegation((NOT50!=null?NOT50.getLine():0), e);
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:295:4: ^( SUBSTR e= simpleUnaryExpression start= expression end= expression )
                    {
                    SUBSTR51=(CommonTree)match(input,SUBSTR,FOLLOW_SUBSTR_in_simpleUnaryExpression1089); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_simpleUnaryExpression_in_simpleUnaryExpression1095);
                    e=simpleUnaryExpression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_simpleUnaryExpression1101);
                    start=expression();

                    state._fsp--;

                    pushFollow(FOLLOW_expression_in_simpleUnaryExpression1107);
                    end=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = new SubString((SUBSTR51!=null?SUBSTR51.getLine():0), e, start, end);
                    	

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:299:4: primaryExpression
                    {
                    pushFollow(FOLLOW_primaryExpression_in_simpleUnaryExpression1116);
                    primaryExpression52=primaryExpression();

                    state._fsp--;


                    		result = primaryExpression52;
                    	

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
    // src/LCPLTreeChecker.g:304:1: primaryExpression returns [Expression result] : ( ^( PAR_EXPR e= expression ) | literal | dispatch | new_object | cast | statement );
    public final Expression primaryExpression() throws RecognitionException {
        Expression result = null;

        Expression e = null;

        Expression literal53 = null;

        Expression dispatch54 = null;

        NewObject new_object55 = null;

        Expression cast56 = null;

        Expression statement57 = null;


        try {
            // src/LCPLTreeChecker.g:305:2: ( ^( PAR_EXPR e= expression ) | literal | dispatch | new_object | cast | statement )
            int alt30=6;
            switch ( input.LA(1) ) {
            case PAR_EXPR:
                {
                alt30=1;
                }
                break;
            case CONSTANT_INT:
            case CONSTANT_STRING:
            case SYMBOL:
            case VOID_CONSTANT:
                {
                alt30=2;
                }
                break;
            case DISPATCH:
                {
                alt30=3;
                }
                break;
            case NEW:
                {
                alt30=4;
                }
                break;
            case CAST:
                {
                alt30=5;
                }
                break;
            case IF:
            case WHILE:
                {
                alt30=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // src/LCPLTreeChecker.g:305:4: ^( PAR_EXPR e= expression )
                    {
                    match(input,PAR_EXPR,FOLLOW_PAR_EXPR_in_primaryExpression1133); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expression_in_primaryExpression1139);
                    e=expression();

                    state._fsp--;


                    match(input, Token.UP, null); 

                    		result = e;
                    	

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:309:4: literal
                    {
                    pushFollow(FOLLOW_literal_in_primaryExpression1148);
                    literal53=literal();

                    state._fsp--;


                    		result = literal53;
                    	

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:313:4: dispatch
                    {
                    pushFollow(FOLLOW_dispatch_in_primaryExpression1156);
                    dispatch54=dispatch();

                    state._fsp--;


                    		result = dispatch54;
                    	

                    }
                    break;
                case 4 :
                    // src/LCPLTreeChecker.g:317:4: new_object
                    {
                    pushFollow(FOLLOW_new_object_in_primaryExpression1164);
                    new_object55=new_object();

                    state._fsp--;


                    		result = new_object55;
                    	

                    }
                    break;
                case 5 :
                    // src/LCPLTreeChecker.g:321:4: cast
                    {
                    pushFollow(FOLLOW_cast_in_primaryExpression1172);
                    cast56=cast();

                    state._fsp--;


                    		result = cast56;
                    	

                    }
                    break;
                case 6 :
                    // src/LCPLTreeChecker.g:325:4: statement
                    {
                    pushFollow(FOLLOW_statement_in_primaryExpression1180);
                    statement57=statement();

                    state._fsp--;


                    		result = statement57;
                    	

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
    // src/LCPLTreeChecker.g:330:1: literal returns [Expression result] : ( integer | string | ^( SYMBOL type ) | VOID_CONSTANT );
    public final Expression literal() throws RecognitionException {
        Expression result = null;

        CommonTree SYMBOL60=null;
        CommonTree VOID_CONSTANT62=null;
        IntConstant integer58 = null;

        StringConstant string59 = null;

        String type61 = null;


        try {
            // src/LCPLTreeChecker.g:331:2: ( integer | string | ^( SYMBOL type ) | VOID_CONSTANT )
            int alt31=4;
            switch ( input.LA(1) ) {
            case CONSTANT_INT:
                {
                alt31=1;
                }
                break;
            case CONSTANT_STRING:
                {
                alt31=2;
                }
                break;
            case SYMBOL:
                {
                alt31=3;
                }
                break;
            case VOID_CONSTANT:
                {
                alt31=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }

            switch (alt31) {
                case 1 :
                    // src/LCPLTreeChecker.g:331:4: integer
                    {
                    pushFollow(FOLLOW_integer_in_literal1195);
                    integer58=integer();

                    state._fsp--;

                    result = integer58;

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:332:4: string
                    {
                    pushFollow(FOLLOW_string_in_literal1202);
                    string59=string();

                    state._fsp--;

                    result = string59;

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:333:4: ^( SYMBOL type )
                    {
                    SYMBOL60=(CommonTree)match(input,SYMBOL,FOLLOW_SYMBOL_in_literal1210); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_type_in_literal1212);
                    type61=type();

                    state._fsp--;


                    match(input, Token.UP, null); 
                    result = new Symbol((SYMBOL60!=null?SYMBOL60.getLine():0), type61);

                    }
                    break;
                case 4 :
                    // src/LCPLTreeChecker.g:334:4: VOID_CONSTANT
                    {
                    VOID_CONSTANT62=(CommonTree)match(input,VOID_CONSTANT,FOLLOW_VOID_CONSTANT_in_literal1220); 
                    result = new VoidConstant((VOID_CONSTANT62!=null?VOID_CONSTANT62.getLine():0));

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
    // src/LCPLTreeChecker.g:336:1: integer returns [IntConstant result] : ^( CONSTANT_INT value= INTEGER ) ;
    public final IntConstant integer() throws RecognitionException {
        IntConstant result = null;

        CommonTree value=null;
        CommonTree CONSTANT_INT63=null;

        try {
            // src/LCPLTreeChecker.g:337:2: ( ^( CONSTANT_INT value= INTEGER ) )
            // src/LCPLTreeChecker.g:337:4: ^( CONSTANT_INT value= INTEGER )
            {
            CONSTANT_INT63=(CommonTree)match(input,CONSTANT_INT,FOLLOW_CONSTANT_INT_in_integer1237); 

            match(input, Token.DOWN, null); 
            value=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_integer1241); 

            match(input, Token.UP, null); 

            		result = new IntConstant((CONSTANT_INT63!=null?CONSTANT_INT63.getLine():0), Integer.valueOf((value!=null?value.getText():null)));
            	

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
    // src/LCPLTreeChecker.g:342:1: string returns [StringConstant result] : ^( CONSTANT_STRING value= STRING_LITERAL ) ;
    public final StringConstant string() throws RecognitionException {
        StringConstant result = null;

        CommonTree value=null;
        CommonTree CONSTANT_STRING64=null;

        try {
            // src/LCPLTreeChecker.g:343:2: ( ^( CONSTANT_STRING value= STRING_LITERAL ) )
            // src/LCPLTreeChecker.g:343:4: ^( CONSTANT_STRING value= STRING_LITERAL )
            {
            CONSTANT_STRING64=(CommonTree)match(input,CONSTANT_STRING,FOLLOW_CONSTANT_STRING_in_string1260); 

            match(input, Token.DOWN, null); 
            value=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_string1264); 

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
            		result = new StringConstant((CONSTANT_STRING64!=null?CONSTANT_STRING64.getLine():0), newString.toString());
            	

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
    // src/LCPLTreeChecker.g:368:1: new_object returns [NewObject result] : ^( NEW type ) ;
    public final NewObject new_object() throws RecognitionException {
        NewObject result = null;

        CommonTree NEW65=null;
        String type66 = null;


        try {
            // src/LCPLTreeChecker.g:369:2: ( ^( NEW type ) )
            // src/LCPLTreeChecker.g:369:4: ^( NEW type )
            {
            NEW65=(CommonTree)match(input,NEW,FOLLOW_NEW_in_new_object1281); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_new_object1283);
            type66=type();

            state._fsp--;


            match(input, Token.UP, null); 

            		result = new NewObject((NEW65!=null?NEW65.getLine():0), type66);
            	

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
    // src/LCPLTreeChecker.g:373:1: type returns [String result] : ( ^( TYPE INT ) | ^( TYPE STRING ) | ^( TYPE ID ) );
    public final String type() throws RecognitionException {
        String result = null;

        CommonTree ID67=null;

        try {
            // src/LCPLTreeChecker.g:374:2: ( ^( TYPE INT ) | ^( TYPE STRING ) | ^( TYPE ID ) )
            int alt32=3;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==TYPE) ) {
                int LA32_1 = input.LA(2);

                if ( (LA32_1==DOWN) ) {
                    switch ( input.LA(3) ) {
                    case INT:
                        {
                        alt32=1;
                        }
                        break;
                    case STRING:
                        {
                        alt32=2;
                        }
                        break;
                    case ID:
                        {
                        alt32=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 32, 2, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 32, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // src/LCPLTreeChecker.g:374:4: ^( TYPE INT )
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_type1300); 

                    match(input, Token.DOWN, null); 
                    match(input,INT,FOLLOW_INT_in_type1302); 

                    match(input, Token.UP, null); 
                     result = "Int"; 

                    }
                    break;
                case 2 :
                    // src/LCPLTreeChecker.g:375:4: ^( TYPE STRING )
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_type1311); 

                    match(input, Token.DOWN, null); 
                    match(input,STRING,FOLLOW_STRING_in_type1313); 

                    match(input, Token.UP, null); 
                    result = "String"; 

                    }
                    break;
                case 3 :
                    // src/LCPLTreeChecker.g:376:4: ^( TYPE ID )
                    {
                    match(input,TYPE,FOLLOW_TYPE_in_type1322); 

                    match(input, Token.DOWN, null); 
                    ID67=(CommonTree)match(input,ID,FOLLOW_ID_in_type1324); 

                    match(input, Token.UP, null); 
                    result = (ID67!=null?ID67.getText():null); 

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


    protected DFA20 dfa20 = new DFA20(this);
    static final String DFA20_eotS =
        "\16\uffff";
    static final String DFA20_eofS =
        "\16\uffff";
    static final String DFA20_minS =
        "\1\32\1\2\1\36\1\2\1\37\3\3\3\51\1\3\2\uffff";
    static final String DFA20_maxS =
        "\1\32\1\2\1\36\1\2\1\51\3\3\3\51\1\35\2\uffff";
    static final String DFA20_acceptS =
        "\14\uffff\1\1\1\2";
    static final String DFA20_specialS =
        "\16\uffff}>";
    static final String[] DFA20_transitionS = {
            "\1\1",
            "\1\2",
            "\1\3",
            "\1\4",
            "\1\5\1\6\10\uffff\1\7",
            "\1\10",
            "\1\11",
            "\1\12",
            "\1\13",
            "\1\13",
            "\1\13",
            "\1\14\31\uffff\1\15",
            "",
            ""
    };

    static final short[] DFA20_eot = DFA.unpackEncodedString(DFA20_eotS);
    static final short[] DFA20_eof = DFA.unpackEncodedString(DFA20_eofS);
    static final char[] DFA20_min = DFA.unpackEncodedStringToUnsignedChars(DFA20_minS);
    static final char[] DFA20_max = DFA.unpackEncodedStringToUnsignedChars(DFA20_maxS);
    static final short[] DFA20_accept = DFA.unpackEncodedString(DFA20_acceptS);
    static final short[] DFA20_special = DFA.unpackEncodedString(DFA20_specialS);
    static final short[][] DFA20_transition;

    static {
        int numStates = DFA20_transitionS.length;
        DFA20_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA20_transition[i] = DFA.unpackEncodedString(DFA20_transitionS[i]);
        }
    }

    class DFA20 extends DFA {

        public DFA20(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 20;
            this.eot = DFA20_eot;
            this.eof = DFA20_eof;
            this.min = DFA20_min;
            this.max = DFA20_max;
            this.accept = DFA20_accept;
            this.special = DFA20_special;
            this.transition = DFA20_transition;
        }
        public String getDescription() {
            return "183:1: attribute returns [Attribute result] : ( ^( ATTRIBUTE type ID ) | ^( ATTRIBUTE type ID e= expression ) );";
        }
    }
 

    public static final BitSet FOLLOW_PROGRAM_in_program56 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_class_definition_in_program58 = new BitSet(new long[]{0x0000000000000028L});
    public static final BitSet FOLLOW_CLASSDEFINITION_in_class_definition78 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_classdef_in_class_definition80 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CLASS_in_classdef103 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_classdef107 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ID_in_classdef111 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_members_in_classdef113 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CLASS_in_classdef130 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_classdef134 = new BitSet(new long[]{0x0000000002000008L});
    public static final BitSet FOLLOW_members_in_classdef136 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EMPTY_METHOD_in_method181 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_method183 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_METHOD_in_method201 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_method203 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_method205 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_METHOD_in_method221 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_method223 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_formal_param_in_method230 = new BitSet(new long[]{0x0000000040004080L});
    public static final BitSet FOLLOW_type_in_method240 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_method243 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_METHOD_in_method259 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_method261 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_type_in_method263 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_method265 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LOCAL_in_local_decl306 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_attribute_in_local_decl313 = new BitSet(new long[]{0x0000000004004008L});
    public static final BitSet FOLLOW_block_in_local_decl319 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLOCK_in_block353 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_block360 = new BitSet(new long[]{0x0000010020000008L});
    public static final BitSet FOLLOW_local_decl_in_block371 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLOCK_in_block385 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_local_decl_in_block387 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MEMBERS_in_members419 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_member_in_members421 = new BitSet(new long[]{0x0000000001000008L});
    public static final BitSet FOLLOW_MEMBER_in_member436 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_decl_section_in_member438 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MEMBER_in_member448 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_method_in_member450 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FORMAL_PARAM_in_formal_param469 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_formal_param471 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ID_in_formal_param473 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DISPATCH_in_dispatch491 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_primaryExpression_in_dispatch498 = new BitSet(new long[]{0x0000020040000000L});
    public static final BitSet FOLLOW_type_in_dispatch507 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ID_in_dispatch510 = new BitSet(new long[]{0x0000000000100008L});
    public static final BitSet FOLLOW_dispatch_arguments_in_dispatch517 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DISPATCH_ARGUMENTS_in_dispatch_arguments550 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_dispatch_arguments557 = new BitSet(new long[]{0x0000010020000008L});
    public static final BitSet FOLLOW_DECL_SECTION_in_decl_section590 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_attribute_in_decl_section597 = new BitSet(new long[]{0x0000000004004008L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_attribute624 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_attribute626 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ID_in_attribute628 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_attribute638 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_attribute640 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ID_in_attribute642 = new BitSet(new long[]{0x0000010020000008L});
    public static final BitSet FOLLOW_expression_in_attribute648 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_statement666 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statement670 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_statement676 = new BitSet(new long[]{0x0000000000004008L});
    public static final BitSet FOLLOW_block_in_statement682 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_WHILE_in_statement693 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_statement699 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_statement705 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CAST_in_cast724 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_cast726 = new BitSet(new long[]{0x0000010020000008L});
    public static final BitSet FOLLOW_expression_in_cast732 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EXPRESSION_in_expression749 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_assignmentExpression_in_expression753 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EQ_in_assignmentExpression771 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_equalityExpression_in_assignmentExpression777 = new BitSet(new long[]{0x01FFEC0800083340L});
    public static final BitSet FOLLOW_assignmentExpression_in_assignmentExpression781 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_equalityExpression_in_assignmentExpression794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUAL_in_equalityExpression814 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_equalityExpression_in_equalityExpression818 = new BitSet(new long[]{0x01FFEC0800083340L});
    public static final BitSet FOLLOW_relationalExpression_in_equalityExpression823 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_relationalExpression_in_equalityExpression836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LTEQ_in_relationalExpression853 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_relationalExpression_in_relationalExpression857 = new BitSet(new long[]{0x01FFEC0800083340L});
    public static final BitSet FOLLOW_additiveExpression_in_relationalExpression861 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_relationalExpression871 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_relationalExpression_in_relationalExpression875 = new BitSet(new long[]{0x01FFEC0800083340L});
    public static final BitSet FOLLOW_additiveExpression_in_relationalExpression879 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_additiveExpression_in_relationalExpression892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_additiveExpression909 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_additiveExpression_in_additiveExpression915 = new BitSet(new long[]{0x01FFEC0800083340L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression921 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SUB_in_additiveExpression931 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_additiveExpression_in_additiveExpression937 = new BitSet(new long[]{0x01FFEC0800083340L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression943 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_multiplicativeExpression974 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_multiplicativeExpression980 = new BitSet(new long[]{0x01FFEC0800083340L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression986 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DIV_in_multiplicativeExpression996 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_multiplicativeExpression1002 = new BitSet(new long[]{0x01FFEC0800083340L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression1008 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression1021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNARY_MINUS_in_unaryExpression1040 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression1046 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_simpleUnaryExpression_in_unaryExpression1055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_simpleUnaryExpression1073 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_unaryExpression_in_simpleUnaryExpression1079 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SUBSTR_in_simpleUnaryExpression1089 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_simpleUnaryExpression_in_simpleUnaryExpression1095 = new BitSet(new long[]{0x0000010020000008L});
    public static final BitSet FOLLOW_expression_in_simpleUnaryExpression1101 = new BitSet(new long[]{0x0000010020000008L});
    public static final BitSet FOLLOW_expression_in_simpleUnaryExpression1107 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_primaryExpression_in_simpleUnaryExpression1116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PAR_EXPR_in_primaryExpression1133 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_primaryExpression1139 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_literal_in_primaryExpression1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dispatch_in_primaryExpression1156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new_object_in_primaryExpression1164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cast_in_primaryExpression1172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_primaryExpression1180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_integer_in_literal1195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_literal1202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SYMBOL_in_literal1210 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_literal1212 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_VOID_CONSTANT_in_literal1220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONSTANT_INT_in_integer1237 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INTEGER_in_integer1241 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CONSTANT_STRING_in_string1260 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_string1264 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEW_in_new_object1281 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_new_object1283 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TYPE_in_type1300 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INT_in_type1302 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TYPE_in_type1311 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STRING_in_type1313 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TYPE_in_type1322 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_type1324 = new BitSet(new long[]{0x0000000000000008L});

}