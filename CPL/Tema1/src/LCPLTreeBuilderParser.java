// $ANTLR 3.2 Sep 23, 2009 12:02:23 src/LCPLTreeBuilder.g 2013-11-12 22:45:04

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class LCPLTreeBuilderParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PROGRAM", "ATTRIBUTE_ARRAY", "CLASSDEFINITION", "CONSTANT_INT", "FORMAL_PARAM", "CONSTANT_STRING", "SYMBOL", "EQ_COMP", "DECL_SECTION", "UNARY_MINUS", "PAR_EXPR", "BLOCK", "OBJ", "EXPR", "EMPTY_DISPATCH", "LOCAL_DECL", "DISPATCH", "DISPATCH_ARGUMENTS", "DECLARATION_SECTION", "EMPTY_METHOD", "ARRAY_TYPE", "DISPATCH_ARGS", "MEMBER", "MEMBERS", "ATTRIBUTE", "ASSIGNMENT", "ATTR", "EXPRESSION", "TYPE", "INT", "STRING", "CLASS", "INHERITS", "NEW", "METHOD", "STATEMENT", "END", "NULL", "LOCAL", "DISPATCH_S", "ID", "VOID_CONSTANT", "CAST", "STRING_CONST", "IF", "WHILE", "SELF", "SUBSTR", "ARRAY", "UNARY_BRACKET", "EQ", "EQUAL", "LTEQ", "LT", "PLUS", "SUB", "STAR", "DIV", "NOT", "INTEGER", "STRING_LITERAL", "ESCAPE_SEQUENCE", "LINE_COMMENT", "WS", "';'", "':'", "'->'", "','", "'['", "'.'", "']'", "'self.'", "'var'", "'[]'", "'then'", "'else'", "'loop'", "'{'", "'}'", "'('", "')'"
    };
    public static final int CAST=46;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int CLASSDEFINITION=6;
    public static final int LT=57;
    public static final int CLASS=35;
    public static final int STAR=60;
    public static final int MEMBER=26;
    public static final int DISPATCH_ARGUMENTS=21;
    public static final int WHILE=49;
    public static final int DISPATCH=20;
    public static final int LTEQ=56;
    public static final int ATTRIBUTE=28;
    public static final int NEW=37;
    public static final int ARRAY_TYPE=24;
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
    public static final int ARRAY=52;
    public static final int ATTR=30;
    public static final int INTEGER=63;
    public static final int CONSTANT_INT=7;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int SYMBOL=10;
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
    public static final int WS=67;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__70=70;
    public static final int DECLARATION_SECTION=22;
    public static final int BLOCK=15;
    public static final int CONSTANT_STRING=9;
    public static final int PROGRAM=4;
    public static final int ASSIGNMENT=29;
    public static final int DIV=61;
    public static final int END=40;
    public static final int T__76=76;
    public static final int T__75=75;
    public static final int SELF=50;
    public static final int T__74=74;
    public static final int METHOD=38;
    public static final int T__73=73;
    public static final int EMPTY_DISPATCH=18;
    public static final int UNARY_BRACKET=53;
    public static final int T__79=79;
    public static final int STRING=34;
    public static final int T__78=78;
    public static final int T__77=77;

    // delegates
    // delegators


        public LCPLTreeBuilderParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public LCPLTreeBuilderParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return LCPLTreeBuilderParser.tokenNames; }
    public String getGrammarFileName() { return "src/LCPLTreeBuilder.g"; }


    public static class program_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "program"
    // src/LCPLTreeBuilder.g:63:1: program : ( ( class_definition )+ ) -> ^( PROGRAM ( class_definition )+ ) ;
    public final LCPLTreeBuilderParser.program_return program() throws RecognitionException {
        LCPLTreeBuilderParser.program_return retval = new LCPLTreeBuilderParser.program_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        LCPLTreeBuilderParser.class_definition_return class_definition1 = null;


        RewriteRuleSubtreeStream stream_class_definition=new RewriteRuleSubtreeStream(adaptor,"rule class_definition");
        try {
            // src/LCPLTreeBuilder.g:63:9: ( ( ( class_definition )+ ) -> ^( PROGRAM ( class_definition )+ ) )
            // src/LCPLTreeBuilder.g:63:13: ( ( class_definition )+ )
            {
            // src/LCPLTreeBuilder.g:63:13: ( ( class_definition )+ )
            // src/LCPLTreeBuilder.g:63:14: ( class_definition )+
            {
            // src/LCPLTreeBuilder.g:63:14: ( class_definition )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==CLASS) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:63:14: class_definition
            	    {
            	    pushFollow(FOLLOW_class_definition_in_program461);
            	    class_definition1=class_definition();

            	    state._fsp--;

            	    stream_class_definition.add(class_definition1.getTree());

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


            }



            // AST REWRITE
            // elements: class_definition
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 63:33: -> ^( PROGRAM ( class_definition )+ )
            {
                // src/LCPLTreeBuilder.g:63:36: ^( PROGRAM ( class_definition )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(PROGRAM, "PROGRAM"), root_1);

                if ( !(stream_class_definition.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_class_definition.hasNext() ) {
                    adaptor.addChild(root_1, stream_class_definition.nextTree());

                }
                stream_class_definition.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "program"

    public static class class_definition_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "class_definition"
    // src/LCPLTreeBuilder.g:66:1: class_definition : classdef -> ^( CLASSDEFINITION classdef ) ;
    public final LCPLTreeBuilderParser.class_definition_return class_definition() throws RecognitionException {
        LCPLTreeBuilderParser.class_definition_return retval = new LCPLTreeBuilderParser.class_definition_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        LCPLTreeBuilderParser.classdef_return classdef2 = null;


        RewriteRuleSubtreeStream stream_classdef=new RewriteRuleSubtreeStream(adaptor,"rule classdef");
        try {
            // src/LCPLTreeBuilder.g:67:2: ( classdef -> ^( CLASSDEFINITION classdef ) )
            // src/LCPLTreeBuilder.g:67:4: classdef
            {
            pushFollow(FOLLOW_classdef_in_class_definition483);
            classdef2=classdef();

            state._fsp--;

            stream_classdef.add(classdef2.getTree());


            // AST REWRITE
            // elements: classdef
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 67:14: -> ^( CLASSDEFINITION classdef )
            {
                // src/LCPLTreeBuilder.g:67:17: ^( CLASSDEFINITION classdef )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CLASSDEFINITION, "CLASSDEFINITION"), root_1);

                adaptor.addChild(root_1, stream_classdef.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "class_definition"

    public static class classdef_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "classdef"
    // src/LCPLTreeBuilder.g:70:1: classdef : ( CLASS name= ID INHERITS parent= ID ( members )? END ';' -> ^( CLASS $name $parent ( members )? ) | CLASS name= ID ( members )? END ';' -> ^( CLASS $name ( members )? ) );
    public final LCPLTreeBuilderParser.classdef_return classdef() throws RecognitionException {
        LCPLTreeBuilderParser.classdef_return retval = new LCPLTreeBuilderParser.classdef_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token name=null;
        Token parent=null;
        Token CLASS3=null;
        Token INHERITS4=null;
        Token END6=null;
        Token char_literal7=null;
        Token CLASS8=null;
        Token END10=null;
        Token char_literal11=null;
        LCPLTreeBuilderParser.members_return members5 = null;

        LCPLTreeBuilderParser.members_return members9 = null;


        CommonTree name_tree=null;
        CommonTree parent_tree=null;
        CommonTree CLASS3_tree=null;
        CommonTree INHERITS4_tree=null;
        CommonTree END6_tree=null;
        CommonTree char_literal7_tree=null;
        CommonTree CLASS8_tree=null;
        CommonTree END10_tree=null;
        CommonTree char_literal11_tree=null;
        RewriteRuleTokenStream stream_CLASS=new RewriteRuleTokenStream(adaptor,"token CLASS");
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_INHERITS=new RewriteRuleTokenStream(adaptor,"token INHERITS");
        RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
        RewriteRuleSubtreeStream stream_members=new RewriteRuleSubtreeStream(adaptor,"rule members");
        try {
            // src/LCPLTreeBuilder.g:70:10: ( CLASS name= ID INHERITS parent= ID ( members )? END ';' -> ^( CLASS $name $parent ( members )? ) | CLASS name= ID ( members )? END ';' -> ^( CLASS $name ( members )? ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==CLASS) ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==ID) ) {
                    int LA4_2 = input.LA(3);

                    if ( (LA4_2==INHERITS) ) {
                        alt4=1;
                    }
                    else if ( (LA4_2==END||LA4_2==ID||LA4_2==76) ) {
                        alt4=2;
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
                    // src/LCPLTreeBuilder.g:70:13: CLASS name= ID INHERITS parent= ID ( members )? END ';'
                    {
                    CLASS3=(Token)match(input,CLASS,FOLLOW_CLASS_in_classdef503);  
                    stream_CLASS.add(CLASS3);

                    name=(Token)match(input,ID,FOLLOW_ID_in_classdef507);  
                    stream_ID.add(name);

                    INHERITS4=(Token)match(input,INHERITS,FOLLOW_INHERITS_in_classdef509);  
                    stream_INHERITS.add(INHERITS4);

                    parent=(Token)match(input,ID,FOLLOW_ID_in_classdef513);  
                    stream_ID.add(parent);

                    // src/LCPLTreeBuilder.g:70:46: ( members )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==ID||LA2_0==76) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:70:46: members
                            {
                            pushFollow(FOLLOW_members_in_classdef515);
                            members5=members();

                            state._fsp--;

                            stream_members.add(members5.getTree());

                            }
                            break;

                    }

                    END6=(Token)match(input,END,FOLLOW_END_in_classdef518);  
                    stream_END.add(END6);

                    char_literal7=(Token)match(input,68,FOLLOW_68_in_classdef520);  
                    stream_68.add(char_literal7);



                    // AST REWRITE
                    // elements: CLASS, members, parent, name
                    // token labels: name, parent
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_name=new RewriteRuleTokenStream(adaptor,"token name",name);
                    RewriteRuleTokenStream stream_parent=new RewriteRuleTokenStream(adaptor,"token parent",parent);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 70:63: -> ^( CLASS $name $parent ( members )? )
                    {
                        // src/LCPLTreeBuilder.g:70:66: ^( CLASS $name $parent ( members )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_CLASS.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_name.nextNode());
                        adaptor.addChild(root_1, stream_parent.nextNode());
                        // src/LCPLTreeBuilder.g:70:88: ( members )?
                        if ( stream_members.hasNext() ) {
                            adaptor.addChild(root_1, stream_members.nextTree());

                        }
                        stream_members.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // src/LCPLTreeBuilder.g:71:13: CLASS name= ID ( members )? END ';'
                    {
                    CLASS8=(Token)match(input,CLASS,FOLLOW_CLASS_in_classdef549);  
                    stream_CLASS.add(CLASS8);

                    name=(Token)match(input,ID,FOLLOW_ID_in_classdef553);  
                    stream_ID.add(name);

                    // src/LCPLTreeBuilder.g:71:27: ( members )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==ID||LA3_0==76) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:71:27: members
                            {
                            pushFollow(FOLLOW_members_in_classdef555);
                            members9=members();

                            state._fsp--;

                            stream_members.add(members9.getTree());

                            }
                            break;

                    }

                    END10=(Token)match(input,END,FOLLOW_END_in_classdef558);  
                    stream_END.add(END10);

                    char_literal11=(Token)match(input,68,FOLLOW_68_in_classdef560);  
                    stream_68.add(char_literal11);



                    // AST REWRITE
                    // elements: CLASS, members, name
                    // token labels: name
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_name=new RewriteRuleTokenStream(adaptor,"token name",name);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 71:44: -> ^( CLASS $name ( members )? )
                    {
                        // src/LCPLTreeBuilder.g:71:47: ^( CLASS $name ( members )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_CLASS.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_name.nextNode());
                        // src/LCPLTreeBuilder.g:71:61: ( members )?
                        if ( stream_members.hasNext() ) {
                            adaptor.addChild(root_1, stream_members.nextTree());

                        }
                        stream_members.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "classdef"

    public static class method_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "method"
    // src/LCPLTreeBuilder.g:74:1: method : ( ID ':' END ';' -> ^( EMPTY_METHOD ID ) | ID ':' block END ';' -> ^( METHOD ID block ) | ID '->' type ':' block END ';' -> ^( METHOD ID type block ) | ID (f+= formal_param ) ( ',' f+= formal_param )* ( '->' type )? ':' block END ';' -> ^( METHOD ID ( $f)+ ( type )? block ) );
    public final LCPLTreeBuilderParser.method_return method() throws RecognitionException {
        LCPLTreeBuilderParser.method_return retval = new LCPLTreeBuilderParser.method_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ID12=null;
        Token char_literal13=null;
        Token END14=null;
        Token char_literal15=null;
        Token ID16=null;
        Token char_literal17=null;
        Token END19=null;
        Token char_literal20=null;
        Token ID21=null;
        Token string_literal22=null;
        Token char_literal24=null;
        Token END26=null;
        Token char_literal27=null;
        Token ID28=null;
        Token char_literal29=null;
        Token string_literal30=null;
        Token char_literal32=null;
        Token END34=null;
        Token char_literal35=null;
        List list_f=null;
        LCPLTreeBuilderParser.block_return block18 = null;

        LCPLTreeBuilderParser.type_return type23 = null;

        LCPLTreeBuilderParser.block_return block25 = null;

        LCPLTreeBuilderParser.type_return type31 = null;

        LCPLTreeBuilderParser.block_return block33 = null;

        RuleReturnScope f = null;
        CommonTree ID12_tree=null;
        CommonTree char_literal13_tree=null;
        CommonTree END14_tree=null;
        CommonTree char_literal15_tree=null;
        CommonTree ID16_tree=null;
        CommonTree char_literal17_tree=null;
        CommonTree END19_tree=null;
        CommonTree char_literal20_tree=null;
        CommonTree ID21_tree=null;
        CommonTree string_literal22_tree=null;
        CommonTree char_literal24_tree=null;
        CommonTree END26_tree=null;
        CommonTree char_literal27_tree=null;
        CommonTree ID28_tree=null;
        CommonTree char_literal29_tree=null;
        CommonTree string_literal30_tree=null;
        CommonTree char_literal32_tree=null;
        CommonTree END34_tree=null;
        CommonTree char_literal35_tree=null;
        RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_formal_param=new RewriteRuleSubtreeStream(adaptor,"rule formal_param");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // src/LCPLTreeBuilder.g:74:9: ( ID ':' END ';' -> ^( EMPTY_METHOD ID ) | ID ':' block END ';' -> ^( METHOD ID block ) | ID '->' type ':' block END ';' -> ^( METHOD ID type block ) | ID (f+= formal_param ) ( ',' f+= formal_param )* ( '->' type )? ':' block END ';' -> ^( METHOD ID ( $f)+ ( type )? block ) )
            int alt7=4;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==ID) ) {
                switch ( input.LA(2) ) {
                case 69:
                    {
                    int LA7_2 = input.LA(3);

                    if ( (LA7_2==END) ) {
                        alt7=1;
                    }
                    else if ( ((LA7_2>=INT && LA7_2<=STRING)||LA7_2==NEW||LA7_2==LOCAL||(LA7_2>=ID && LA7_2<=VOID_CONSTANT)||(LA7_2>=IF && LA7_2<=SELF)||LA7_2==SUB||(LA7_2>=NOT && LA7_2<=STRING_LITERAL)||LA7_2==72||LA7_2==75||LA7_2==81||LA7_2==83) ) {
                        alt7=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case 70:
                    {
                    alt7=3;
                    }
                    break;
                case INT:
                case STRING:
                case ID:
                    {
                    alt7=4;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

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
                    // src/LCPLTreeBuilder.g:74:11: ID ':' END ';'
                    {
                    ID12=(Token)match(input,ID,FOLLOW_ID_in_method590);  
                    stream_ID.add(ID12);

                    char_literal13=(Token)match(input,69,FOLLOW_69_in_method592);  
                    stream_69.add(char_literal13);

                    END14=(Token)match(input,END,FOLLOW_END_in_method594);  
                    stream_END.add(END14);

                    char_literal15=(Token)match(input,68,FOLLOW_68_in_method596);  
                    stream_68.add(char_literal15);



                    // AST REWRITE
                    // elements: ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 74:26: -> ^( EMPTY_METHOD ID )
                    {
                        // src/LCPLTreeBuilder.g:74:29: ^( EMPTY_METHOD ID )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EMPTY_METHOD, "EMPTY_METHOD"), root_1);

                        adaptor.addChild(root_1, stream_ID.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // src/LCPLTreeBuilder.g:75:4: ID ':' block END ';'
                    {
                    ID16=(Token)match(input,ID,FOLLOW_ID_in_method609);  
                    stream_ID.add(ID16);

                    char_literal17=(Token)match(input,69,FOLLOW_69_in_method611);  
                    stream_69.add(char_literal17);

                    pushFollow(FOLLOW_block_in_method613);
                    block18=block();

                    state._fsp--;

                    stream_block.add(block18.getTree());
                    END19=(Token)match(input,END,FOLLOW_END_in_method615);  
                    stream_END.add(END19);

                    char_literal20=(Token)match(input,68,FOLLOW_68_in_method617);  
                    stream_68.add(char_literal20);



                    // AST REWRITE
                    // elements: block, ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 75:25: -> ^( METHOD ID block )
                    {
                        // src/LCPLTreeBuilder.g:75:28: ^( METHOD ID block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(METHOD, "METHOD"), root_1);

                        adaptor.addChild(root_1, stream_ID.nextNode());
                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // src/LCPLTreeBuilder.g:76:4: ID '->' type ':' block END ';'
                    {
                    ID21=(Token)match(input,ID,FOLLOW_ID_in_method632);  
                    stream_ID.add(ID21);

                    string_literal22=(Token)match(input,70,FOLLOW_70_in_method634);  
                    stream_70.add(string_literal22);

                    pushFollow(FOLLOW_type_in_method636);
                    type23=type();

                    state._fsp--;

                    stream_type.add(type23.getTree());
                    char_literal24=(Token)match(input,69,FOLLOW_69_in_method638);  
                    stream_69.add(char_literal24);

                    pushFollow(FOLLOW_block_in_method640);
                    block25=block();

                    state._fsp--;

                    stream_block.add(block25.getTree());
                    END26=(Token)match(input,END,FOLLOW_END_in_method642);  
                    stream_END.add(END26);

                    char_literal27=(Token)match(input,68,FOLLOW_68_in_method644);  
                    stream_68.add(char_literal27);



                    // AST REWRITE
                    // elements: block, type, ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 76:35: -> ^( METHOD ID type block )
                    {
                        // src/LCPLTreeBuilder.g:76:38: ^( METHOD ID type block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(METHOD, "METHOD"), root_1);

                        adaptor.addChild(root_1, stream_ID.nextNode());
                        adaptor.addChild(root_1, stream_type.nextTree());
                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // src/LCPLTreeBuilder.g:77:4: ID (f+= formal_param ) ( ',' f+= formal_param )* ( '->' type )? ':' block END ';'
                    {
                    ID28=(Token)match(input,ID,FOLLOW_ID_in_method661);  
                    stream_ID.add(ID28);

                    // src/LCPLTreeBuilder.g:77:7: (f+= formal_param )
                    // src/LCPLTreeBuilder.g:77:8: f+= formal_param
                    {
                    pushFollow(FOLLOW_formal_param_in_method668);
                    f=formal_param();

                    state._fsp--;

                    stream_formal_param.add(f.getTree());
                    if (list_f==null) list_f=new ArrayList();
                    list_f.add(f.getTree());


                    }

                    // src/LCPLTreeBuilder.g:77:27: ( ',' f+= formal_param )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==71) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // src/LCPLTreeBuilder.g:77:28: ',' f+= formal_param
                    	    {
                    	    char_literal29=(Token)match(input,71,FOLLOW_71_in_method672);  
                    	    stream_71.add(char_literal29);

                    	    pushFollow(FOLLOW_formal_param_in_method678);
                    	    f=formal_param();

                    	    state._fsp--;

                    	    stream_formal_param.add(f.getTree());
                    	    if (list_f==null) list_f=new ArrayList();
                    	    list_f.add(f.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    // src/LCPLTreeBuilder.g:77:52: ( '->' type )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==70) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:77:53: '->' type
                            {
                            string_literal30=(Token)match(input,70,FOLLOW_70_in_method683);  
                            stream_70.add(string_literal30);

                            pushFollow(FOLLOW_type_in_method685);
                            type31=type();

                            state._fsp--;

                            stream_type.add(type31.getTree());

                            }
                            break;

                    }

                    char_literal32=(Token)match(input,69,FOLLOW_69_in_method689);  
                    stream_69.add(char_literal32);

                    pushFollow(FOLLOW_block_in_method691);
                    block33=block();

                    state._fsp--;

                    stream_block.add(block33.getTree());
                    END34=(Token)match(input,END,FOLLOW_END_in_method693);  
                    stream_END.add(END34);

                    char_literal35=(Token)match(input,68,FOLLOW_68_in_method695);  
                    stream_68.add(char_literal35);



                    // AST REWRITE
                    // elements: block, ID, type, f
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: f
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_f=new RewriteRuleSubtreeStream(adaptor,"token f",list_f);
                    root_0 = (CommonTree)adaptor.nil();
                    // 77:83: -> ^( METHOD ID ( $f)+ ( type )? block )
                    {
                        // src/LCPLTreeBuilder.g:77:86: ^( METHOD ID ( $f)+ ( type )? block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(METHOD, "METHOD"), root_1);

                        adaptor.addChild(root_1, stream_ID.nextNode());
                        if ( !(stream_f.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_f.hasNext() ) {
                            adaptor.addChild(root_1, stream_f.nextTree());

                        }
                        stream_f.reset();
                        // src/LCPLTreeBuilder.g:77:102: ( type )?
                        if ( stream_type.hasNext() ) {
                            adaptor.addChild(root_1, stream_type.nextTree());

                        }
                        stream_type.reset();
                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "method"

    public static class members_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "members"
    // src/LCPLTreeBuilder.g:80:1: members : ( ( member )+ ) -> ^( MEMBERS ( member )+ ) ;
    public final LCPLTreeBuilderParser.members_return members() throws RecognitionException {
        LCPLTreeBuilderParser.members_return retval = new LCPLTreeBuilderParser.members_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        LCPLTreeBuilderParser.member_return member36 = null;


        RewriteRuleSubtreeStream stream_member=new RewriteRuleSubtreeStream(adaptor,"rule member");
        try {
            // src/LCPLTreeBuilder.g:80:9: ( ( ( member )+ ) -> ^( MEMBERS ( member )+ ) )
            // src/LCPLTreeBuilder.g:80:11: ( ( member )+ )
            {
            // src/LCPLTreeBuilder.g:80:11: ( ( member )+ )
            // src/LCPLTreeBuilder.g:80:12: ( member )+
            {
            // src/LCPLTreeBuilder.g:80:12: ( member )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==ID||LA8_0==76) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:80:12: member
            	    {
            	    pushFollow(FOLLOW_member_in_members727);
            	    member36=member();

            	    state._fsp--;

            	    stream_member.add(member36.getTree());

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


            }



            // AST REWRITE
            // elements: member
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 80:21: -> ^( MEMBERS ( member )+ )
            {
                // src/LCPLTreeBuilder.g:80:24: ^( MEMBERS ( member )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(MEMBERS, "MEMBERS"), root_1);

                if ( !(stream_member.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_member.hasNext() ) {
                    adaptor.addChild(root_1, stream_member.nextTree());

                }
                stream_member.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "members"

    public static class member_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "member"
    // src/LCPLTreeBuilder.g:84:1: member : ( decl_section -> ^( MEMBER decl_section ) | method -> ^( MEMBER method ) );
    public final LCPLTreeBuilderParser.member_return member() throws RecognitionException {
        LCPLTreeBuilderParser.member_return retval = new LCPLTreeBuilderParser.member_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        LCPLTreeBuilderParser.decl_section_return decl_section37 = null;

        LCPLTreeBuilderParser.method_return method38 = null;


        RewriteRuleSubtreeStream stream_decl_section=new RewriteRuleSubtreeStream(adaptor,"rule decl_section");
        RewriteRuleSubtreeStream stream_method=new RewriteRuleSubtreeStream(adaptor,"rule method");
        try {
            // src/LCPLTreeBuilder.g:84:8: ( decl_section -> ^( MEMBER decl_section ) | method -> ^( MEMBER method ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==76) ) {
                alt9=1;
            }
            else if ( (LA9_0==ID) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // src/LCPLTreeBuilder.g:84:10: decl_section
                    {
                    pushFollow(FOLLOW_decl_section_in_member749);
                    decl_section37=decl_section();

                    state._fsp--;

                    stream_decl_section.add(decl_section37.getTree());


                    // AST REWRITE
                    // elements: decl_section
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 84:23: -> ^( MEMBER decl_section )
                    {
                        // src/LCPLTreeBuilder.g:84:26: ^( MEMBER decl_section )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(MEMBER, "MEMBER"), root_1);

                        adaptor.addChild(root_1, stream_decl_section.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // src/LCPLTreeBuilder.g:85:4: method
                    {
                    pushFollow(FOLLOW_method_in_member762);
                    method38=method();

                    state._fsp--;

                    stream_method.add(method38.getTree());


                    // AST REWRITE
                    // elements: method
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 85:11: -> ^( MEMBER method )
                    {
                        // src/LCPLTreeBuilder.g:85:14: ^( MEMBER method )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(MEMBER, "MEMBER"), root_1);

                        adaptor.addChild(root_1, stream_method.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "member"

    public static class block_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "block"
    // src/LCPLTreeBuilder.g:88:1: block : ( ( expression ';' )+ ( local_decl )? -> ^( BLOCK ( expression )+ ( local_decl )? ) | local_decl -> ^( BLOCK local_decl ) );
    public final LCPLTreeBuilderParser.block_return block() throws RecognitionException {
        LCPLTreeBuilderParser.block_return retval = new LCPLTreeBuilderParser.block_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal40=null;
        LCPLTreeBuilderParser.expression_return expression39 = null;

        LCPLTreeBuilderParser.local_decl_return local_decl41 = null;

        LCPLTreeBuilderParser.local_decl_return local_decl42 = null;


        CommonTree char_literal40_tree=null;
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_local_decl=new RewriteRuleSubtreeStream(adaptor,"rule local_decl");
        try {
            // src/LCPLTreeBuilder.g:88:7: ( ( expression ';' )+ ( local_decl )? -> ^( BLOCK ( expression )+ ( local_decl )? ) | local_decl -> ^( BLOCK local_decl ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=INT && LA12_0<=STRING)||LA12_0==NEW||(LA12_0>=ID && LA12_0<=VOID_CONSTANT)||(LA12_0>=IF && LA12_0<=SELF)||LA12_0==SUB||(LA12_0>=NOT && LA12_0<=STRING_LITERAL)||LA12_0==72||LA12_0==75||LA12_0==81||LA12_0==83) ) {
                alt12=1;
            }
            else if ( (LA12_0==LOCAL) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // src/LCPLTreeBuilder.g:88:9: ( expression ';' )+ ( local_decl )?
                    {
                    // src/LCPLTreeBuilder.g:88:9: ( expression ';' )+
                    int cnt10=0;
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0>=INT && LA10_0<=STRING)||LA10_0==NEW||(LA10_0>=ID && LA10_0<=VOID_CONSTANT)||(LA10_0>=IF && LA10_0<=SELF)||LA10_0==SUB||(LA10_0>=NOT && LA10_0<=STRING_LITERAL)||LA10_0==72||LA10_0==75||LA10_0==81||LA10_0==83) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // src/LCPLTreeBuilder.g:88:10: expression ';'
                    	    {
                    	    pushFollow(FOLLOW_expression_in_block781);
                    	    expression39=expression();

                    	    state._fsp--;

                    	    stream_expression.add(expression39.getTree());
                    	    char_literal40=(Token)match(input,68,FOLLOW_68_in_block783);  
                    	    stream_68.add(char_literal40);


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

                    // src/LCPLTreeBuilder.g:88:27: ( local_decl )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==LOCAL) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:88:27: local_decl
                            {
                            pushFollow(FOLLOW_local_decl_in_block787);
                            local_decl41=local_decl();

                            state._fsp--;

                            stream_local_decl.add(local_decl41.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: expression, local_decl
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 88:39: -> ^( BLOCK ( expression )+ ( local_decl )? )
                    {
                        // src/LCPLTreeBuilder.g:88:42: ^( BLOCK ( expression )+ ( local_decl )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_1);

                        if ( !(stream_expression.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_expression.hasNext() ) {
                            adaptor.addChild(root_1, stream_expression.nextTree());

                        }
                        stream_expression.reset();
                        // src/LCPLTreeBuilder.g:88:62: ( local_decl )?
                        if ( stream_local_decl.hasNext() ) {
                            adaptor.addChild(root_1, stream_local_decl.nextTree());

                        }
                        stream_local_decl.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // src/LCPLTreeBuilder.g:89:4: local_decl
                    {
                    pushFollow(FOLLOW_local_decl_in_block805);
                    local_decl42=local_decl();

                    state._fsp--;

                    stream_local_decl.add(local_decl42.getTree());


                    // AST REWRITE
                    // elements: local_decl
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 89:15: -> ^( BLOCK local_decl )
                    {
                        // src/LCPLTreeBuilder.g:89:18: ^( BLOCK local_decl )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_1);

                        adaptor.addChild(root_1, stream_local_decl.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "block"

    public static class local_decl_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "local_decl"
    // src/LCPLTreeBuilder.g:92:1: local_decl : LOCAL ( attribute )+ END ';' ( block )? -> ^( LOCAL ( attribute )+ ( block )? ) ;
    public final LCPLTreeBuilderParser.local_decl_return local_decl() throws RecognitionException {
        LCPLTreeBuilderParser.local_decl_return retval = new LCPLTreeBuilderParser.local_decl_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LOCAL43=null;
        Token END45=null;
        Token char_literal46=null;
        LCPLTreeBuilderParser.attribute_return attribute44 = null;

        LCPLTreeBuilderParser.block_return block47 = null;


        CommonTree LOCAL43_tree=null;
        CommonTree END45_tree=null;
        CommonTree char_literal46_tree=null;
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_LOCAL=new RewriteRuleTokenStream(adaptor,"token LOCAL");
        RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
        RewriteRuleSubtreeStream stream_attribute=new RewriteRuleSubtreeStream(adaptor,"rule attribute");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // src/LCPLTreeBuilder.g:93:2: ( LOCAL ( attribute )+ END ';' ( block )? -> ^( LOCAL ( attribute )+ ( block )? ) )
            // src/LCPLTreeBuilder.g:93:4: LOCAL ( attribute )+ END ';' ( block )?
            {
            LOCAL43=(Token)match(input,LOCAL,FOLLOW_LOCAL_in_local_decl825);  
            stream_LOCAL.add(LOCAL43);

            // src/LCPLTreeBuilder.g:93:10: ( attribute )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>=INT && LA13_0<=STRING)||LA13_0==ID) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:93:11: attribute
            	    {
            	    pushFollow(FOLLOW_attribute_in_local_decl828);
            	    attribute44=attribute();

            	    state._fsp--;

            	    stream_attribute.add(attribute44.getTree());

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

            END45=(Token)match(input,END,FOLLOW_END_in_local_decl832);  
            stream_END.add(END45);

            char_literal46=(Token)match(input,68,FOLLOW_68_in_local_decl834);  
            stream_68.add(char_literal46);

            // src/LCPLTreeBuilder.g:93:31: ( block )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=INT && LA14_0<=STRING)||LA14_0==NEW||LA14_0==LOCAL||(LA14_0>=ID && LA14_0<=VOID_CONSTANT)||(LA14_0>=IF && LA14_0<=SELF)||LA14_0==SUB||(LA14_0>=NOT && LA14_0<=STRING_LITERAL)||LA14_0==72||LA14_0==75||LA14_0==81||LA14_0==83) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // src/LCPLTreeBuilder.g:93:31: block
                    {
                    pushFollow(FOLLOW_block_in_local_decl836);
                    block47=block();

                    state._fsp--;

                    stream_block.add(block47.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: attribute, block, LOCAL
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 93:39: -> ^( LOCAL ( attribute )+ ( block )? )
            {
                // src/LCPLTreeBuilder.g:93:42: ^( LOCAL ( attribute )+ ( block )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_LOCAL.nextNode(), root_1);

                if ( !(stream_attribute.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_attribute.hasNext() ) {
                    adaptor.addChild(root_1, stream_attribute.nextTree());

                }
                stream_attribute.reset();
                // src/LCPLTreeBuilder.g:93:61: ( block )?
                if ( stream_block.hasNext() ) {
                    adaptor.addChild(root_1, stream_block.nextTree());

                }
                stream_block.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "local_decl"

    public static class formal_param_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "formal_param"
    // src/LCPLTreeBuilder.g:96:1: formal_param : type ID -> ^( FORMAL_PARAM type ID ) ;
    public final LCPLTreeBuilderParser.formal_param_return formal_param() throws RecognitionException {
        LCPLTreeBuilderParser.formal_param_return retval = new LCPLTreeBuilderParser.formal_param_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ID49=null;
        LCPLTreeBuilderParser.type_return type48 = null;


        CommonTree ID49_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // src/LCPLTreeBuilder.g:97:2: ( type ID -> ^( FORMAL_PARAM type ID ) )
            // src/LCPLTreeBuilder.g:97:4: type ID
            {
            pushFollow(FOLLOW_type_in_formal_param861);
            type48=type();

            state._fsp--;

            stream_type.add(type48.getTree());
            ID49=(Token)match(input,ID,FOLLOW_ID_in_formal_param863);  
            stream_ID.add(ID49);



            // AST REWRITE
            // elements: type, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 97:12: -> ^( FORMAL_PARAM type ID )
            {
                // src/LCPLTreeBuilder.g:97:15: ^( FORMAL_PARAM type ID )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FORMAL_PARAM, "FORMAL_PARAM"), root_1);

                adaptor.addChild(root_1, stream_type.nextTree());
                adaptor.addChild(root_1, stream_ID.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "formal_param"

    public static class dispatch_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dispatch"
    // src/LCPLTreeBuilder.g:100:1: dispatch : ( '[' ( ( primaryExpression '.' ) ( type '.' )? )? ID ( dispatch_arguments )? ']' -> ^( DISPATCH ( primaryExpression )? ( type )? ID ( dispatch_arguments )? ) | '[' 'self.' ( type '.' )? ID ( dispatch_arguments )? ']' -> ^( DISPATCH_S ID ( type )? ( dispatch_arguments )? ) );
    public final LCPLTreeBuilderParser.dispatch_return dispatch() throws RecognitionException {
        LCPLTreeBuilderParser.dispatch_return retval = new LCPLTreeBuilderParser.dispatch_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal50=null;
        Token char_literal52=null;
        Token char_literal54=null;
        Token ID55=null;
        Token char_literal57=null;
        Token char_literal58=null;
        Token string_literal59=null;
        Token char_literal61=null;
        Token ID62=null;
        Token char_literal64=null;
        LCPLTreeBuilderParser.primaryExpression_return primaryExpression51 = null;

        LCPLTreeBuilderParser.type_return type53 = null;

        LCPLTreeBuilderParser.dispatch_arguments_return dispatch_arguments56 = null;

        LCPLTreeBuilderParser.type_return type60 = null;

        LCPLTreeBuilderParser.dispatch_arguments_return dispatch_arguments63 = null;


        CommonTree char_literal50_tree=null;
        CommonTree char_literal52_tree=null;
        CommonTree char_literal54_tree=null;
        CommonTree ID55_tree=null;
        CommonTree char_literal57_tree=null;
        CommonTree char_literal58_tree=null;
        CommonTree string_literal59_tree=null;
        CommonTree char_literal61_tree=null;
        CommonTree ID62_tree=null;
        CommonTree char_literal64_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
        RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
        RewriteRuleSubtreeStream stream_dispatch_arguments=new RewriteRuleSubtreeStream(adaptor,"rule dispatch_arguments");
        RewriteRuleSubtreeStream stream_primaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule primaryExpression");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // src/LCPLTreeBuilder.g:101:2: ( '[' ( ( primaryExpression '.' ) ( type '.' )? )? ID ( dispatch_arguments )? ']' -> ^( DISPATCH ( primaryExpression )? ( type )? ID ( dispatch_arguments )? ) | '[' 'self.' ( type '.' )? ID ( dispatch_arguments )? ']' -> ^( DISPATCH_S ID ( type )? ( dispatch_arguments )? ) )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==72) ) {
                int LA20_1 = input.LA(2);

                if ( (LA20_1==75) ) {
                    alt20=2;
                }
                else if ( ((LA20_1>=INT && LA20_1<=STRING)||LA20_1==NEW||(LA20_1>=ID && LA20_1<=VOID_CONSTANT)||(LA20_1>=IF && LA20_1<=SELF)||(LA20_1>=INTEGER && LA20_1<=STRING_LITERAL)||LA20_1==72||LA20_1==81||LA20_1==83) ) {
                    alt20=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // src/LCPLTreeBuilder.g:101:4: '[' ( ( primaryExpression '.' ) ( type '.' )? )? ID ( dispatch_arguments )? ']'
                    {
                    char_literal50=(Token)match(input,72,FOLLOW_72_in_dispatch885);  
                    stream_72.add(char_literal50);

                    // src/LCPLTreeBuilder.g:101:8: ( ( primaryExpression '.' ) ( type '.' )? )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( ((LA16_0>=INT && LA16_0<=STRING)||LA16_0==NEW||LA16_0==VOID_CONSTANT||(LA16_0>=IF && LA16_0<=SELF)||(LA16_0>=INTEGER && LA16_0<=STRING_LITERAL)||LA16_0==72||LA16_0==81||LA16_0==83) ) {
                        alt16=1;
                    }
                    else if ( (LA16_0==ID) ) {
                        int LA16_2 = input.LA(2);

                        if ( (LA16_2==73) ) {
                            alt16=1;
                        }
                    }
                    switch (alt16) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:101:9: ( primaryExpression '.' ) ( type '.' )?
                            {
                            // src/LCPLTreeBuilder.g:101:9: ( primaryExpression '.' )
                            // src/LCPLTreeBuilder.g:101:10: primaryExpression '.'
                            {
                            pushFollow(FOLLOW_primaryExpression_in_dispatch889);
                            primaryExpression51=primaryExpression();

                            state._fsp--;

                            stream_primaryExpression.add(primaryExpression51.getTree());
                            char_literal52=(Token)match(input,73,FOLLOW_73_in_dispatch890);  
                            stream_73.add(char_literal52);


                            }

                            // src/LCPLTreeBuilder.g:101:32: ( type '.' )?
                            int alt15=2;
                            int LA15_0 = input.LA(1);

                            if ( ((LA15_0>=INT && LA15_0<=STRING)) ) {
                                alt15=1;
                            }
                            else if ( (LA15_0==ID) ) {
                                int LA15_2 = input.LA(2);

                                if ( (LA15_2==73) ) {
                                    alt15=1;
                                }
                            }
                            switch (alt15) {
                                case 1 :
                                    // src/LCPLTreeBuilder.g:101:33: type '.'
                                    {
                                    pushFollow(FOLLOW_type_in_dispatch894);
                                    type53=type();

                                    state._fsp--;

                                    stream_type.add(type53.getTree());
                                    char_literal54=(Token)match(input,73,FOLLOW_73_in_dispatch896);  
                                    stream_73.add(char_literal54);


                                    }
                                    break;

                            }


                            }
                            break;

                    }

                    ID55=(Token)match(input,ID,FOLLOW_ID_in_dispatch903);  
                    stream_ID.add(ID55);

                    // src/LCPLTreeBuilder.g:101:50: ( dispatch_arguments )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( ((LA17_0>=INT && LA17_0<=STRING)||LA17_0==NEW||(LA17_0>=ID && LA17_0<=VOID_CONSTANT)||(LA17_0>=IF && LA17_0<=SELF)||LA17_0==SUB||(LA17_0>=NOT && LA17_0<=STRING_LITERAL)||LA17_0==72||LA17_0==75||LA17_0==81||LA17_0==83) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:101:50: dispatch_arguments
                            {
                            pushFollow(FOLLOW_dispatch_arguments_in_dispatch905);
                            dispatch_arguments56=dispatch_arguments();

                            state._fsp--;

                            stream_dispatch_arguments.add(dispatch_arguments56.getTree());

                            }
                            break;

                    }

                    char_literal57=(Token)match(input,74,FOLLOW_74_in_dispatch908);  
                    stream_74.add(char_literal57);



                    // AST REWRITE
                    // elements: ID, type, primaryExpression, dispatch_arguments
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 102:3: -> ^( DISPATCH ( primaryExpression )? ( type )? ID ( dispatch_arguments )? )
                    {
                        // src/LCPLTreeBuilder.g:102:6: ^( DISPATCH ( primaryExpression )? ( type )? ID ( dispatch_arguments )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DISPATCH, "DISPATCH"), root_1);

                        // src/LCPLTreeBuilder.g:102:17: ( primaryExpression )?
                        if ( stream_primaryExpression.hasNext() ) {
                            adaptor.addChild(root_1, stream_primaryExpression.nextTree());

                        }
                        stream_primaryExpression.reset();
                        // src/LCPLTreeBuilder.g:102:36: ( type )?
                        if ( stream_type.hasNext() ) {
                            adaptor.addChild(root_1, stream_type.nextTree());

                        }
                        stream_type.reset();
                        adaptor.addChild(root_1, stream_ID.nextNode());
                        // src/LCPLTreeBuilder.g:102:46: ( dispatch_arguments )?
                        if ( stream_dispatch_arguments.hasNext() ) {
                            adaptor.addChild(root_1, stream_dispatch_arguments.nextTree());

                        }
                        stream_dispatch_arguments.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // src/LCPLTreeBuilder.g:103:5: '[' 'self.' ( type '.' )? ID ( dispatch_arguments )? ']'
                    {
                    char_literal58=(Token)match(input,72,FOLLOW_72_in_dispatch934);  
                    stream_72.add(char_literal58);

                    string_literal59=(Token)match(input,75,FOLLOW_75_in_dispatch936);  
                    stream_75.add(string_literal59);

                    // src/LCPLTreeBuilder.g:103:17: ( type '.' )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( ((LA18_0>=INT && LA18_0<=STRING)) ) {
                        alt18=1;
                    }
                    else if ( (LA18_0==ID) ) {
                        int LA18_2 = input.LA(2);

                        if ( (LA18_2==73) ) {
                            alt18=1;
                        }
                    }
                    switch (alt18) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:103:18: type '.'
                            {
                            pushFollow(FOLLOW_type_in_dispatch939);
                            type60=type();

                            state._fsp--;

                            stream_type.add(type60.getTree());
                            char_literal61=(Token)match(input,73,FOLLOW_73_in_dispatch941);  
                            stream_73.add(char_literal61);


                            }
                            break;

                    }

                    ID62=(Token)match(input,ID,FOLLOW_ID_in_dispatch945);  
                    stream_ID.add(ID62);

                    // src/LCPLTreeBuilder.g:103:32: ( dispatch_arguments )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( ((LA19_0>=INT && LA19_0<=STRING)||LA19_0==NEW||(LA19_0>=ID && LA19_0<=VOID_CONSTANT)||(LA19_0>=IF && LA19_0<=SELF)||LA19_0==SUB||(LA19_0>=NOT && LA19_0<=STRING_LITERAL)||LA19_0==72||LA19_0==75||LA19_0==81||LA19_0==83) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:103:32: dispatch_arguments
                            {
                            pushFollow(FOLLOW_dispatch_arguments_in_dispatch947);
                            dispatch_arguments63=dispatch_arguments();

                            state._fsp--;

                            stream_dispatch_arguments.add(dispatch_arguments63.getTree());

                            }
                            break;

                    }

                    char_literal64=(Token)match(input,74,FOLLOW_74_in_dispatch950);  
                    stream_74.add(char_literal64);



                    // AST REWRITE
                    // elements: dispatch_arguments, ID, type
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 103:56: -> ^( DISPATCH_S ID ( type )? ( dispatch_arguments )? )
                    {
                        // src/LCPLTreeBuilder.g:103:59: ^( DISPATCH_S ID ( type )? ( dispatch_arguments )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DISPATCH_S, "DISPATCH_S"), root_1);

                        adaptor.addChild(root_1, stream_ID.nextNode());
                        // src/LCPLTreeBuilder.g:103:75: ( type )?
                        if ( stream_type.hasNext() ) {
                            adaptor.addChild(root_1, stream_type.nextTree());

                        }
                        stream_type.reset();
                        // src/LCPLTreeBuilder.g:103:81: ( dispatch_arguments )?
                        if ( stream_dispatch_arguments.hasNext() ) {
                            adaptor.addChild(root_1, stream_dispatch_arguments.nextTree());

                        }
                        stream_dispatch_arguments.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dispatch"

    public static class dispatch_arguments_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dispatch_arguments"
    // src/LCPLTreeBuilder.g:106:1: dispatch_arguments : ar+= expression ( ',' ar+= expression )* -> ^( DISPATCH_ARGUMENTS ( $ar)+ ) ;
    public final LCPLTreeBuilderParser.dispatch_arguments_return dispatch_arguments() throws RecognitionException {
        LCPLTreeBuilderParser.dispatch_arguments_return retval = new LCPLTreeBuilderParser.dispatch_arguments_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal65=null;
        List list_ar=null;
        RuleReturnScope ar = null;
        CommonTree char_literal65_tree=null;
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // src/LCPLTreeBuilder.g:107:2: (ar+= expression ( ',' ar+= expression )* -> ^( DISPATCH_ARGUMENTS ( $ar)+ ) )
            // src/LCPLTreeBuilder.g:107:4: ar+= expression ( ',' ar+= expression )*
            {
            pushFollow(FOLLOW_expression_in_dispatch_arguments977);
            ar=expression();

            state._fsp--;

            stream_expression.add(ar.getTree());
            if (list_ar==null) list_ar=new ArrayList();
            list_ar.add(ar.getTree());

            // src/LCPLTreeBuilder.g:107:19: ( ',' ar+= expression )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==71) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:107:20: ',' ar+= expression
            	    {
            	    char_literal65=(Token)match(input,71,FOLLOW_71_in_dispatch_arguments980);  
            	    stream_71.add(char_literal65);

            	    pushFollow(FOLLOW_expression_in_dispatch_arguments984);
            	    ar=expression();

            	    state._fsp--;

            	    stream_expression.add(ar.getTree());
            	    if (list_ar==null) list_ar=new ArrayList();
            	    list_ar.add(ar.getTree());


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);



            // AST REWRITE
            // elements: ar
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: ar
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_ar=new RewriteRuleSubtreeStream(adaptor,"token ar",list_ar);
            root_0 = (CommonTree)adaptor.nil();
            // 107:41: -> ^( DISPATCH_ARGUMENTS ( $ar)+ )
            {
                // src/LCPLTreeBuilder.g:107:44: ^( DISPATCH_ARGUMENTS ( $ar)+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DISPATCH_ARGUMENTS, "DISPATCH_ARGUMENTS"), root_1);

                if ( !(stream_ar.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_ar.hasNext() ) {
                    adaptor.addChild(root_1, stream_ar.nextTree());

                }
                stream_ar.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dispatch_arguments"

    public static class decl_section_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "decl_section"
    // src/LCPLTreeBuilder.g:110:1: decl_section : 'var' ( attribute )+ 'end' ';' -> ^( DECL_SECTION ( attribute )+ ) ;
    public final LCPLTreeBuilderParser.decl_section_return decl_section() throws RecognitionException {
        LCPLTreeBuilderParser.decl_section_return retval = new LCPLTreeBuilderParser.decl_section_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal66=null;
        Token string_literal68=null;
        Token char_literal69=null;
        LCPLTreeBuilderParser.attribute_return attribute67 = null;


        CommonTree string_literal66_tree=null;
        CommonTree string_literal68_tree=null;
        CommonTree char_literal69_tree=null;
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
        RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
        RewriteRuleSubtreeStream stream_attribute=new RewriteRuleSubtreeStream(adaptor,"rule attribute");
        try {
            // src/LCPLTreeBuilder.g:111:2: ( 'var' ( attribute )+ 'end' ';' -> ^( DECL_SECTION ( attribute )+ ) )
            // src/LCPLTreeBuilder.g:111:4: 'var' ( attribute )+ 'end' ';'
            {
            string_literal66=(Token)match(input,76,FOLLOW_76_in_decl_section1007);  
            stream_76.add(string_literal66);

            // src/LCPLTreeBuilder.g:111:10: ( attribute )+
            int cnt22=0;
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>=INT && LA22_0<=STRING)||LA22_0==ID) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:111:11: attribute
            	    {
            	    pushFollow(FOLLOW_attribute_in_decl_section1010);
            	    attribute67=attribute();

            	    state._fsp--;

            	    stream_attribute.add(attribute67.getTree());

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

            string_literal68=(Token)match(input,END,FOLLOW_END_in_decl_section1014);  
            stream_END.add(string_literal68);

            char_literal69=(Token)match(input,68,FOLLOW_68_in_decl_section1016);  
            stream_68.add(char_literal69);



            // AST REWRITE
            // elements: attribute
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 111:33: -> ^( DECL_SECTION ( attribute )+ )
            {
                // src/LCPLTreeBuilder.g:111:36: ^( DECL_SECTION ( attribute )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DECL_SECTION, "DECL_SECTION"), root_1);

                if ( !(stream_attribute.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_attribute.hasNext() ) {
                    adaptor.addChild(root_1, stream_attribute.nextTree());

                }
                stream_attribute.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "decl_section"

    public static class attribute_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "attribute"
    // src/LCPLTreeBuilder.g:114:1: attribute : ( ( type ID ';' -> ^( ATTRIBUTE type ID ) | type '[]' ID ';' -> ^( ATTRIBUTE_ARRAY type ID ) ) | ( type ID EQ expression ';' -> ^( ATTRIBUTE type ID expression ) | type '[]' ID EQ expression ';' -> ^( ATTRIBUTE_ARRAY type ID expression ) ) );
    public final LCPLTreeBuilderParser.attribute_return attribute() throws RecognitionException {
        LCPLTreeBuilderParser.attribute_return retval = new LCPLTreeBuilderParser.attribute_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ID71=null;
        Token char_literal72=null;
        Token string_literal74=null;
        Token ID75=null;
        Token char_literal76=null;
        Token ID78=null;
        Token EQ79=null;
        Token char_literal81=null;
        Token string_literal83=null;
        Token ID84=null;
        Token EQ85=null;
        Token char_literal87=null;
        LCPLTreeBuilderParser.type_return type70 = null;

        LCPLTreeBuilderParser.type_return type73 = null;

        LCPLTreeBuilderParser.type_return type77 = null;

        LCPLTreeBuilderParser.expression_return expression80 = null;

        LCPLTreeBuilderParser.type_return type82 = null;

        LCPLTreeBuilderParser.expression_return expression86 = null;


        CommonTree ID71_tree=null;
        CommonTree char_literal72_tree=null;
        CommonTree string_literal74_tree=null;
        CommonTree ID75_tree=null;
        CommonTree char_literal76_tree=null;
        CommonTree ID78_tree=null;
        CommonTree EQ79_tree=null;
        CommonTree char_literal81_tree=null;
        CommonTree string_literal83_tree=null;
        CommonTree ID84_tree=null;
        CommonTree EQ85_tree=null;
        CommonTree char_literal87_tree=null;
        RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // src/LCPLTreeBuilder.g:115:2: ( ( type ID ';' -> ^( ATTRIBUTE type ID ) | type '[]' ID ';' -> ^( ATTRIBUTE_ARRAY type ID ) ) | ( type ID EQ expression ';' -> ^( ATTRIBUTE type ID expression ) | type '[]' ID EQ expression ';' -> ^( ATTRIBUTE_ARRAY type ID expression ) ) )
            int alt25=2;
            switch ( input.LA(1) ) {
            case INT:
                {
                int LA25_1 = input.LA(2);

                if ( (LA25_1==77) ) {
                    int LA25_4 = input.LA(3);

                    if ( (LA25_4==ID) ) {
                        int LA25_6 = input.LA(4);

                        if ( (LA25_6==EQ) ) {
                            alt25=2;
                        }
                        else if ( (LA25_6==68) ) {
                            alt25=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 25, 6, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 4, input);

                        throw nvae;
                    }
                }
                else if ( (LA25_1==ID) ) {
                    int LA25_5 = input.LA(3);

                    if ( (LA25_5==68) ) {
                        alt25=1;
                    }
                    else if ( (LA25_5==EQ) ) {
                        alt25=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 5, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 1, input);

                    throw nvae;
                }
                }
                break;
            case STRING:
                {
                int LA25_2 = input.LA(2);

                if ( (LA25_2==77) ) {
                    int LA25_4 = input.LA(3);

                    if ( (LA25_4==ID) ) {
                        int LA25_6 = input.LA(4);

                        if ( (LA25_6==EQ) ) {
                            alt25=2;
                        }
                        else if ( (LA25_6==68) ) {
                            alt25=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 25, 6, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 4, input);

                        throw nvae;
                    }
                }
                else if ( (LA25_2==ID) ) {
                    int LA25_5 = input.LA(3);

                    if ( (LA25_5==68) ) {
                        alt25=1;
                    }
                    else if ( (LA25_5==EQ) ) {
                        alt25=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 5, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 2, input);

                    throw nvae;
                }
                }
                break;
            case ID:
                {
                int LA25_3 = input.LA(2);

                if ( (LA25_3==77) ) {
                    int LA25_4 = input.LA(3);

                    if ( (LA25_4==ID) ) {
                        int LA25_6 = input.LA(4);

                        if ( (LA25_6==EQ) ) {
                            alt25=2;
                        }
                        else if ( (LA25_6==68) ) {
                            alt25=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 25, 6, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 4, input);

                        throw nvae;
                    }
                }
                else if ( (LA25_3==ID) ) {
                    int LA25_5 = input.LA(3);

                    if ( (LA25_5==68) ) {
                        alt25=1;
                    }
                    else if ( (LA25_5==EQ) ) {
                        alt25=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 5, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // src/LCPLTreeBuilder.g:115:4: ( type ID ';' -> ^( ATTRIBUTE type ID ) | type '[]' ID ';' -> ^( ATTRIBUTE_ARRAY type ID ) )
                    {
                    // src/LCPLTreeBuilder.g:115:4: ( type ID ';' -> ^( ATTRIBUTE type ID ) | type '[]' ID ';' -> ^( ATTRIBUTE_ARRAY type ID ) )
                    int alt23=2;
                    switch ( input.LA(1) ) {
                    case INT:
                        {
                        int LA23_1 = input.LA(2);

                        if ( (LA23_1==77) ) {
                            alt23=2;
                        }
                        else if ( (LA23_1==ID) ) {
                            alt23=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 23, 1, input);

                            throw nvae;
                        }
                        }
                        break;
                    case STRING:
                        {
                        int LA23_2 = input.LA(2);

                        if ( (LA23_2==77) ) {
                            alt23=2;
                        }
                        else if ( (LA23_2==ID) ) {
                            alt23=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 23, 2, input);

                            throw nvae;
                        }
                        }
                        break;
                    case ID:
                        {
                        int LA23_3 = input.LA(2);

                        if ( (LA23_3==77) ) {
                            alt23=2;
                        }
                        else if ( (LA23_3==ID) ) {
                            alt23=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 23, 3, input);

                            throw nvae;
                        }
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 23, 0, input);

                        throw nvae;
                    }

                    switch (alt23) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:115:5: type ID ';'
                            {
                            pushFollow(FOLLOW_type_in_attribute1040);
                            type70=type();

                            state._fsp--;

                            stream_type.add(type70.getTree());
                            ID71=(Token)match(input,ID,FOLLOW_ID_in_attribute1042);  
                            stream_ID.add(ID71);

                            char_literal72=(Token)match(input,68,FOLLOW_68_in_attribute1044);  
                            stream_68.add(char_literal72);



                            // AST REWRITE
                            // elements: ID, type
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 115:17: -> ^( ATTRIBUTE type ID )
                            {
                                // src/LCPLTreeBuilder.g:115:20: ^( ATTRIBUTE type ID )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ATTRIBUTE, "ATTRIBUTE"), root_1);

                                adaptor.addChild(root_1, stream_type.nextTree());
                                adaptor.addChild(root_1, stream_ID.nextNode());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;
                            }
                            break;
                        case 2 :
                            // src/LCPLTreeBuilder.g:116:4: type '[]' ID ';'
                            {
                            pushFollow(FOLLOW_type_in_attribute1059);
                            type73=type();

                            state._fsp--;

                            stream_type.add(type73.getTree());
                            string_literal74=(Token)match(input,77,FOLLOW_77_in_attribute1061);  
                            stream_77.add(string_literal74);

                            ID75=(Token)match(input,ID,FOLLOW_ID_in_attribute1063);  
                            stream_ID.add(ID75);

                            char_literal76=(Token)match(input,68,FOLLOW_68_in_attribute1065);  
                            stream_68.add(char_literal76);



                            // AST REWRITE
                            // elements: ID, type
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 116:21: -> ^( ATTRIBUTE_ARRAY type ID )
                            {
                                // src/LCPLTreeBuilder.g:116:24: ^( ATTRIBUTE_ARRAY type ID )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ATTRIBUTE_ARRAY, "ATTRIBUTE_ARRAY"), root_1);

                                adaptor.addChild(root_1, stream_type.nextTree());
                                adaptor.addChild(root_1, stream_ID.nextNode());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;
                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // src/LCPLTreeBuilder.g:117:4: ( type ID EQ expression ';' -> ^( ATTRIBUTE type ID expression ) | type '[]' ID EQ expression ';' -> ^( ATTRIBUTE_ARRAY type ID expression ) )
                    {
                    // src/LCPLTreeBuilder.g:117:4: ( type ID EQ expression ';' -> ^( ATTRIBUTE type ID expression ) | type '[]' ID EQ expression ';' -> ^( ATTRIBUTE_ARRAY type ID expression ) )
                    int alt24=2;
                    switch ( input.LA(1) ) {
                    case INT:
                        {
                        int LA24_1 = input.LA(2);

                        if ( (LA24_1==77) ) {
                            alt24=2;
                        }
                        else if ( (LA24_1==ID) ) {
                            alt24=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 24, 1, input);

                            throw nvae;
                        }
                        }
                        break;
                    case STRING:
                        {
                        int LA24_2 = input.LA(2);

                        if ( (LA24_2==77) ) {
                            alt24=2;
                        }
                        else if ( (LA24_2==ID) ) {
                            alt24=1;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 24, 2, input);

                            throw nvae;
                        }
                        }
                        break;
                    case ID:
                        {
                        int LA24_3 = input.LA(2);

                        if ( (LA24_3==ID) ) {
                            alt24=1;
                        }
                        else if ( (LA24_3==77) ) {
                            alt24=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 24, 3, input);

                            throw nvae;
                        }
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 24, 0, input);

                        throw nvae;
                    }

                    switch (alt24) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:117:5: type ID EQ expression ';'
                            {
                            pushFollow(FOLLOW_type_in_attribute1082);
                            type77=type();

                            state._fsp--;

                            stream_type.add(type77.getTree());
                            ID78=(Token)match(input,ID,FOLLOW_ID_in_attribute1084);  
                            stream_ID.add(ID78);

                            EQ79=(Token)match(input,EQ,FOLLOW_EQ_in_attribute1086);  
                            stream_EQ.add(EQ79);

                            pushFollow(FOLLOW_expression_in_attribute1088);
                            expression80=expression();

                            state._fsp--;

                            stream_expression.add(expression80.getTree());
                            char_literal81=(Token)match(input,68,FOLLOW_68_in_attribute1090);  
                            stream_68.add(char_literal81);



                            // AST REWRITE
                            // elements: expression, ID, type
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 117:31: -> ^( ATTRIBUTE type ID expression )
                            {
                                // src/LCPLTreeBuilder.g:117:34: ^( ATTRIBUTE type ID expression )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ATTRIBUTE, "ATTRIBUTE"), root_1);

                                adaptor.addChild(root_1, stream_type.nextTree());
                                adaptor.addChild(root_1, stream_ID.nextNode());
                                adaptor.addChild(root_1, stream_expression.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;
                            }
                            break;
                        case 2 :
                            // src/LCPLTreeBuilder.g:118:4: type '[]' ID EQ expression ';'
                            {
                            pushFollow(FOLLOW_type_in_attribute1107);
                            type82=type();

                            state._fsp--;

                            stream_type.add(type82.getTree());
                            string_literal83=(Token)match(input,77,FOLLOW_77_in_attribute1109);  
                            stream_77.add(string_literal83);

                            ID84=(Token)match(input,ID,FOLLOW_ID_in_attribute1111);  
                            stream_ID.add(ID84);

                            EQ85=(Token)match(input,EQ,FOLLOW_EQ_in_attribute1113);  
                            stream_EQ.add(EQ85);

                            pushFollow(FOLLOW_expression_in_attribute1115);
                            expression86=expression();

                            state._fsp--;

                            stream_expression.add(expression86.getTree());
                            char_literal87=(Token)match(input,68,FOLLOW_68_in_attribute1117);  
                            stream_68.add(char_literal87);



                            // AST REWRITE
                            // elements: expression, type, ID
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 118:35: -> ^( ATTRIBUTE_ARRAY type ID expression )
                            {
                                // src/LCPLTreeBuilder.g:118:38: ^( ATTRIBUTE_ARRAY type ID expression )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ATTRIBUTE_ARRAY, "ATTRIBUTE_ARRAY"), root_1);

                                adaptor.addChild(root_1, stream_type.nextTree());
                                adaptor.addChild(root_1, stream_ID.nextNode());
                                adaptor.addChild(root_1, stream_expression.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;
                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "attribute"

    public static class statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statement"
    // src/LCPLTreeBuilder.g:121:1: statement : ( IF cond= expression 'then' ifBlock= block ( 'else' elseBlock= block )? END -> ^( IF $cond $ifBlock ( $elseBlock)? ) | WHILE expression 'loop' block END -> ^( WHILE expression block ) );
    public final LCPLTreeBuilderParser.statement_return statement() throws RecognitionException {
        LCPLTreeBuilderParser.statement_return retval = new LCPLTreeBuilderParser.statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IF88=null;
        Token string_literal89=null;
        Token string_literal90=null;
        Token END91=null;
        Token WHILE92=null;
        Token string_literal94=null;
        Token END96=null;
        LCPLTreeBuilderParser.expression_return cond = null;

        LCPLTreeBuilderParser.block_return ifBlock = null;

        LCPLTreeBuilderParser.block_return elseBlock = null;

        LCPLTreeBuilderParser.expression_return expression93 = null;

        LCPLTreeBuilderParser.block_return block95 = null;


        CommonTree IF88_tree=null;
        CommonTree string_literal89_tree=null;
        CommonTree string_literal90_tree=null;
        CommonTree END91_tree=null;
        CommonTree WHILE92_tree=null;
        CommonTree string_literal94_tree=null;
        CommonTree END96_tree=null;
        RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
        RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
        RewriteRuleTokenStream stream_WHILE=new RewriteRuleTokenStream(adaptor,"token WHILE");
        RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
        RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
        RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // src/LCPLTreeBuilder.g:122:2: ( IF cond= expression 'then' ifBlock= block ( 'else' elseBlock= block )? END -> ^( IF $cond $ifBlock ( $elseBlock)? ) | WHILE expression 'loop' block END -> ^( WHILE expression block ) )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==IF) ) {
                alt27=1;
            }
            else if ( (LA27_0==WHILE) ) {
                alt27=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // src/LCPLTreeBuilder.g:122:4: IF cond= expression 'then' ifBlock= block ( 'else' elseBlock= block )? END
                    {
                    IF88=(Token)match(input,IF,FOLLOW_IF_in_statement1142);  
                    stream_IF.add(IF88);

                    pushFollow(FOLLOW_expression_in_statement1148);
                    cond=expression();

                    state._fsp--;

                    stream_expression.add(cond.getTree());
                    string_literal89=(Token)match(input,78,FOLLOW_78_in_statement1150);  
                    stream_78.add(string_literal89);

                    pushFollow(FOLLOW_block_in_statement1154);
                    ifBlock=block();

                    state._fsp--;

                    stream_block.add(ifBlock.getTree());
                    // src/LCPLTreeBuilder.g:122:46: ( 'else' elseBlock= block )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==79) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:122:47: 'else' elseBlock= block
                            {
                            string_literal90=(Token)match(input,79,FOLLOW_79_in_statement1157);  
                            stream_79.add(string_literal90);

                            pushFollow(FOLLOW_block_in_statement1163);
                            elseBlock=block();

                            state._fsp--;

                            stream_block.add(elseBlock.getTree());

                            }
                            break;

                    }

                    END91=(Token)match(input,END,FOLLOW_END_in_statement1167);  
                    stream_END.add(END91);



                    // AST REWRITE
                    // elements: cond, elseBlock, ifBlock, IF
                    // token labels: 
                    // rule labels: retval, ifBlock, elseBlock, cond
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_ifBlock=new RewriteRuleSubtreeStream(adaptor,"rule ifBlock",ifBlock!=null?ifBlock.tree:null);
                    RewriteRuleSubtreeStream stream_elseBlock=new RewriteRuleSubtreeStream(adaptor,"rule elseBlock",elseBlock!=null?elseBlock.tree:null);
                    RewriteRuleSubtreeStream stream_cond=new RewriteRuleSubtreeStream(adaptor,"rule cond",cond!=null?cond.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 123:3: -> ^( IF $cond $ifBlock ( $elseBlock)? )
                    {
                        // src/LCPLTreeBuilder.g:123:6: ^( IF $cond $ifBlock ( $elseBlock)? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_IF.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_cond.nextTree());
                        adaptor.addChild(root_1, stream_ifBlock.nextTree());
                        // src/LCPLTreeBuilder.g:123:26: ( $elseBlock)?
                        if ( stream_elseBlock.hasNext() ) {
                            adaptor.addChild(root_1, stream_elseBlock.nextTree());

                        }
                        stream_elseBlock.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // src/LCPLTreeBuilder.g:124:4: WHILE expression 'loop' block END
                    {
                    WHILE92=(Token)match(input,WHILE,FOLLOW_WHILE_in_statement1191);  
                    stream_WHILE.add(WHILE92);

                    pushFollow(FOLLOW_expression_in_statement1193);
                    expression93=expression();

                    state._fsp--;

                    stream_expression.add(expression93.getTree());
                    string_literal94=(Token)match(input,80,FOLLOW_80_in_statement1195);  
                    stream_80.add(string_literal94);

                    pushFollow(FOLLOW_block_in_statement1197);
                    block95=block();

                    state._fsp--;

                    stream_block.add(block95.getTree());
                    END96=(Token)match(input,END,FOLLOW_END_in_statement1199);  
                    stream_END.add(END96);



                    // AST REWRITE
                    // elements: expression, block, WHILE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 124:38: -> ^( WHILE expression block )
                    {
                        // src/LCPLTreeBuilder.g:124:41: ^( WHILE expression block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_WHILE.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_expression.nextTree());
                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "statement"

    public static class cast_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "cast"
    // src/LCPLTreeBuilder.g:127:1: cast : '{' type expression '}' -> ^( CAST type expression ) ;
    public final LCPLTreeBuilderParser.cast_return cast() throws RecognitionException {
        LCPLTreeBuilderParser.cast_return retval = new LCPLTreeBuilderParser.cast_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal97=null;
        Token char_literal100=null;
        LCPLTreeBuilderParser.type_return type98 = null;

        LCPLTreeBuilderParser.expression_return expression99 = null;


        CommonTree char_literal97_tree=null;
        CommonTree char_literal100_tree=null;
        RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
        RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // src/LCPLTreeBuilder.g:127:6: ( '{' type expression '}' -> ^( CAST type expression ) )
            // src/LCPLTreeBuilder.g:127:8: '{' type expression '}'
            {
            char_literal97=(Token)match(input,81,FOLLOW_81_in_cast1219);  
            stream_81.add(char_literal97);

            pushFollow(FOLLOW_type_in_cast1220);
            type98=type();

            state._fsp--;

            stream_type.add(type98.getTree());
            pushFollow(FOLLOW_expression_in_cast1222);
            expression99=expression();

            state._fsp--;

            stream_expression.add(expression99.getTree());
            char_literal100=(Token)match(input,82,FOLLOW_82_in_cast1224);  
            stream_82.add(char_literal100);



            // AST REWRITE
            // elements: type, expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 127:31: -> ^( CAST type expression )
            {
                // src/LCPLTreeBuilder.g:127:34: ^( CAST type expression )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CAST, "CAST"), root_1);

                adaptor.addChild(root_1, stream_type.nextTree());
                adaptor.addChild(root_1, stream_expression.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "cast"

    public static class expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expression"
    // src/LCPLTreeBuilder.g:130:1: expression : assignmentExpression -> ^( EXPRESSION assignmentExpression ) ;
    public final LCPLTreeBuilderParser.expression_return expression() throws RecognitionException {
        LCPLTreeBuilderParser.expression_return retval = new LCPLTreeBuilderParser.expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        LCPLTreeBuilderParser.assignmentExpression_return assignmentExpression101 = null;


        RewriteRuleSubtreeStream stream_assignmentExpression=new RewriteRuleSubtreeStream(adaptor,"rule assignmentExpression");
        try {
            // src/LCPLTreeBuilder.g:131:2: ( assignmentExpression -> ^( EXPRESSION assignmentExpression ) )
            // src/LCPLTreeBuilder.g:131:4: assignmentExpression
            {
            pushFollow(FOLLOW_assignmentExpression_in_expression1245);
            assignmentExpression101=assignmentExpression();

            state._fsp--;

            stream_assignmentExpression.add(assignmentExpression101.getTree());


            // AST REWRITE
            // elements: assignmentExpression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 131:25: -> ^( EXPRESSION assignmentExpression )
            {
                // src/LCPLTreeBuilder.g:131:28: ^( EXPRESSION assignmentExpression )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(EXPRESSION, "EXPRESSION"), root_1);

                adaptor.addChild(root_1, stream_assignmentExpression.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expression"

    public static class assignmentExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "assignmentExpression"
    // src/LCPLTreeBuilder.g:134:1: assignmentExpression : ( equalityExpression ( EQ assignmentExpression )? | 'self.' ID ( EQ assignmentExpression ) );
    public final LCPLTreeBuilderParser.assignmentExpression_return assignmentExpression() throws RecognitionException {
        LCPLTreeBuilderParser.assignmentExpression_return retval = new LCPLTreeBuilderParser.assignmentExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EQ103=null;
        Token string_literal105=null;
        Token ID106=null;
        Token EQ107=null;
        LCPLTreeBuilderParser.equalityExpression_return equalityExpression102 = null;

        LCPLTreeBuilderParser.assignmentExpression_return assignmentExpression104 = null;

        LCPLTreeBuilderParser.assignmentExpression_return assignmentExpression108 = null;


        CommonTree EQ103_tree=null;
        CommonTree string_literal105_tree=null;
        CommonTree ID106_tree=null;
        CommonTree EQ107_tree=null;

        try {
            // src/LCPLTreeBuilder.g:135:2: ( equalityExpression ( EQ assignmentExpression )? | 'self.' ID ( EQ assignmentExpression ) )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( ((LA29_0>=INT && LA29_0<=STRING)||LA29_0==NEW||(LA29_0>=ID && LA29_0<=VOID_CONSTANT)||(LA29_0>=IF && LA29_0<=SELF)||LA29_0==SUB||(LA29_0>=NOT && LA29_0<=STRING_LITERAL)||LA29_0==72||LA29_0==81||LA29_0==83) ) {
                alt29=1;
            }
            else if ( (LA29_0==75) ) {
                alt29=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // src/LCPLTreeBuilder.g:135:5: equalityExpression ( EQ assignmentExpression )?
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_equalityExpression_in_assignmentExpression1266);
                    equalityExpression102=equalityExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, equalityExpression102.getTree());
                    // src/LCPLTreeBuilder.g:135:24: ( EQ assignmentExpression )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==EQ) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:135:25: EQ assignmentExpression
                            {
                            EQ103=(Token)match(input,EQ,FOLLOW_EQ_in_assignmentExpression1269); 
                            EQ103_tree = (CommonTree)adaptor.create(EQ103);
                            root_0 = (CommonTree)adaptor.becomeRoot(EQ103_tree, root_0);

                            pushFollow(FOLLOW_assignmentExpression_in_assignmentExpression1272);
                            assignmentExpression104=assignmentExpression();

                            state._fsp--;

                            adaptor.addChild(root_0, assignmentExpression104.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // src/LCPLTreeBuilder.g:136:4: 'self.' ID ( EQ assignmentExpression )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    string_literal105=(Token)match(input,75,FOLLOW_75_in_assignmentExpression1279); 
                    string_literal105_tree = (CommonTree)adaptor.create(string_literal105);
                    adaptor.addChild(root_0, string_literal105_tree);

                    ID106=(Token)match(input,ID,FOLLOW_ID_in_assignmentExpression1281); 
                    ID106_tree = (CommonTree)adaptor.create(ID106);
                    adaptor.addChild(root_0, ID106_tree);

                    // src/LCPLTreeBuilder.g:136:15: ( EQ assignmentExpression )
                    // src/LCPLTreeBuilder.g:136:16: EQ assignmentExpression
                    {
                    EQ107=(Token)match(input,EQ,FOLLOW_EQ_in_assignmentExpression1284); 
                    EQ107_tree = (CommonTree)adaptor.create(EQ107);
                    root_0 = (CommonTree)adaptor.becomeRoot(EQ107_tree, root_0);

                    pushFollow(FOLLOW_assignmentExpression_in_assignmentExpression1287);
                    assignmentExpression108=assignmentExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, assignmentExpression108.getTree());

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "assignmentExpression"

    public static class equalityExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "equalityExpression"
    // src/LCPLTreeBuilder.g:140:1: equalityExpression : relationalExpression ( EQUAL relationalExpression )* ;
    public final LCPLTreeBuilderParser.equalityExpression_return equalityExpression() throws RecognitionException {
        LCPLTreeBuilderParser.equalityExpression_return retval = new LCPLTreeBuilderParser.equalityExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EQUAL110=null;
        LCPLTreeBuilderParser.relationalExpression_return relationalExpression109 = null;

        LCPLTreeBuilderParser.relationalExpression_return relationalExpression111 = null;


        CommonTree EQUAL110_tree=null;

        try {
            // src/LCPLTreeBuilder.g:141:2: ( relationalExpression ( EQUAL relationalExpression )* )
            // src/LCPLTreeBuilder.g:141:4: relationalExpression ( EQUAL relationalExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_relationalExpression_in_equalityExpression1302);
            relationalExpression109=relationalExpression();

            state._fsp--;

            adaptor.addChild(root_0, relationalExpression109.getTree());
            // src/LCPLTreeBuilder.g:141:25: ( EQUAL relationalExpression )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==EQUAL) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:141:26: EQUAL relationalExpression
            	    {
            	    EQUAL110=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_equalityExpression1305); 
            	    EQUAL110_tree = (CommonTree)adaptor.create(EQUAL110);
            	    root_0 = (CommonTree)adaptor.becomeRoot(EQUAL110_tree, root_0);

            	    pushFollow(FOLLOW_relationalExpression_in_equalityExpression1308);
            	    relationalExpression111=relationalExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, relationalExpression111.getTree());

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "equalityExpression"

    public static class relationalExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "relationalExpression"
    // src/LCPLTreeBuilder.g:144:1: relationalExpression : additiveExpression ( ( LTEQ | LT ) additiveExpression )* ;
    public final LCPLTreeBuilderParser.relationalExpression_return relationalExpression() throws RecognitionException {
        LCPLTreeBuilderParser.relationalExpression_return retval = new LCPLTreeBuilderParser.relationalExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LTEQ113=null;
        Token LT114=null;
        LCPLTreeBuilderParser.additiveExpression_return additiveExpression112 = null;

        LCPLTreeBuilderParser.additiveExpression_return additiveExpression115 = null;


        CommonTree LTEQ113_tree=null;
        CommonTree LT114_tree=null;

        try {
            // src/LCPLTreeBuilder.g:145:2: ( additiveExpression ( ( LTEQ | LT ) additiveExpression )* )
            // src/LCPLTreeBuilder.g:145:4: additiveExpression ( ( LTEQ | LT ) additiveExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_additiveExpression_in_relationalExpression1322);
            additiveExpression112=additiveExpression();

            state._fsp--;

            adaptor.addChild(root_0, additiveExpression112.getTree());
            // src/LCPLTreeBuilder.g:145:23: ( ( LTEQ | LT ) additiveExpression )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( ((LA32_0>=LTEQ && LA32_0<=LT)) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:145:24: ( LTEQ | LT ) additiveExpression
            	    {
            	    // src/LCPLTreeBuilder.g:145:24: ( LTEQ | LT )
            	    int alt31=2;
            	    int LA31_0 = input.LA(1);

            	    if ( (LA31_0==LTEQ) ) {
            	        alt31=1;
            	    }
            	    else if ( (LA31_0==LT) ) {
            	        alt31=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 31, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt31) {
            	        case 1 :
            	            // src/LCPLTreeBuilder.g:145:25: LTEQ
            	            {
            	            LTEQ113=(Token)match(input,LTEQ,FOLLOW_LTEQ_in_relationalExpression1326); 
            	            LTEQ113_tree = (CommonTree)adaptor.create(LTEQ113);
            	            root_0 = (CommonTree)adaptor.becomeRoot(LTEQ113_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // src/LCPLTreeBuilder.g:145:33: LT
            	            {
            	            LT114=(Token)match(input,LT,FOLLOW_LT_in_relationalExpression1331); 
            	            LT114_tree = (CommonTree)adaptor.create(LT114);
            	            root_0 = (CommonTree)adaptor.becomeRoot(LT114_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_additiveExpression_in_relationalExpression1336);
            	    additiveExpression115=additiveExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, additiveExpression115.getTree());

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "relationalExpression"

    public static class additiveExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "additiveExpression"
    // src/LCPLTreeBuilder.g:148:1: additiveExpression : multiplicativeExpression ( ( PLUS | SUB ) multiplicativeExpression )* ;
    public final LCPLTreeBuilderParser.additiveExpression_return additiveExpression() throws RecognitionException {
        LCPLTreeBuilderParser.additiveExpression_return retval = new LCPLTreeBuilderParser.additiveExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token PLUS117=null;
        Token SUB118=null;
        LCPLTreeBuilderParser.multiplicativeExpression_return multiplicativeExpression116 = null;

        LCPLTreeBuilderParser.multiplicativeExpression_return multiplicativeExpression119 = null;


        CommonTree PLUS117_tree=null;
        CommonTree SUB118_tree=null;

        try {
            // src/LCPLTreeBuilder.g:149:2: ( multiplicativeExpression ( ( PLUS | SUB ) multiplicativeExpression )* )
            // src/LCPLTreeBuilder.g:149:4: multiplicativeExpression ( ( PLUS | SUB ) multiplicativeExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression1349);
            multiplicativeExpression116=multiplicativeExpression();

            state._fsp--;

            adaptor.addChild(root_0, multiplicativeExpression116.getTree());
            // src/LCPLTreeBuilder.g:150:3: ( ( PLUS | SUB ) multiplicativeExpression )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( ((LA34_0>=PLUS && LA34_0<=SUB)) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:150:4: ( PLUS | SUB ) multiplicativeExpression
            	    {
            	    // src/LCPLTreeBuilder.g:150:4: ( PLUS | SUB )
            	    int alt33=2;
            	    int LA33_0 = input.LA(1);

            	    if ( (LA33_0==PLUS) ) {
            	        alt33=1;
            	    }
            	    else if ( (LA33_0==SUB) ) {
            	        alt33=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 33, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt33) {
            	        case 1 :
            	            // src/LCPLTreeBuilder.g:150:5: PLUS
            	            {
            	            PLUS117=(Token)match(input,PLUS,FOLLOW_PLUS_in_additiveExpression1355); 
            	            PLUS117_tree = (CommonTree)adaptor.create(PLUS117);
            	            root_0 = (CommonTree)adaptor.becomeRoot(PLUS117_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // src/LCPLTreeBuilder.g:150:13: SUB
            	            {
            	            SUB118=(Token)match(input,SUB,FOLLOW_SUB_in_additiveExpression1360); 
            	            SUB118_tree = (CommonTree)adaptor.create(SUB118);
            	            root_0 = (CommonTree)adaptor.becomeRoot(SUB118_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression1365);
            	    multiplicativeExpression119=multiplicativeExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, multiplicativeExpression119.getTree());

            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "additiveExpression"

    public static class multiplicativeExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "multiplicativeExpression"
    // src/LCPLTreeBuilder.g:153:1: multiplicativeExpression : unaryExpression ( ( STAR | DIV ) unaryExpression )* ;
    public final LCPLTreeBuilderParser.multiplicativeExpression_return multiplicativeExpression() throws RecognitionException {
        LCPLTreeBuilderParser.multiplicativeExpression_return retval = new LCPLTreeBuilderParser.multiplicativeExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token STAR121=null;
        Token DIV122=null;
        LCPLTreeBuilderParser.unaryExpression_return unaryExpression120 = null;

        LCPLTreeBuilderParser.unaryExpression_return unaryExpression123 = null;


        CommonTree STAR121_tree=null;
        CommonTree DIV122_tree=null;

        try {
            // src/LCPLTreeBuilder.g:154:2: ( unaryExpression ( ( STAR | DIV ) unaryExpression )* )
            // src/LCPLTreeBuilder.g:154:4: unaryExpression ( ( STAR | DIV ) unaryExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression1379);
            unaryExpression120=unaryExpression();

            state._fsp--;

            adaptor.addChild(root_0, unaryExpression120.getTree());
            // src/LCPLTreeBuilder.g:155:3: ( ( STAR | DIV ) unaryExpression )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( ((LA36_0>=STAR && LA36_0<=DIV)) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:155:4: ( STAR | DIV ) unaryExpression
            	    {
            	    // src/LCPLTreeBuilder.g:155:4: ( STAR | DIV )
            	    int alt35=2;
            	    int LA35_0 = input.LA(1);

            	    if ( (LA35_0==STAR) ) {
            	        alt35=1;
            	    }
            	    else if ( (LA35_0==DIV) ) {
            	        alt35=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 35, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt35) {
            	        case 1 :
            	            // src/LCPLTreeBuilder.g:155:5: STAR
            	            {
            	            STAR121=(Token)match(input,STAR,FOLLOW_STAR_in_multiplicativeExpression1385); 
            	            STAR121_tree = (CommonTree)adaptor.create(STAR121);
            	            root_0 = (CommonTree)adaptor.becomeRoot(STAR121_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // src/LCPLTreeBuilder.g:155:13: DIV
            	            {
            	            DIV122=(Token)match(input,DIV,FOLLOW_DIV_in_multiplicativeExpression1390); 
            	            DIV122_tree = (CommonTree)adaptor.create(DIV122);
            	            root_0 = (CommonTree)adaptor.becomeRoot(DIV122_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression1396);
            	    unaryExpression123=unaryExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, unaryExpression123.getTree());

            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "multiplicativeExpression"

    public static class unaryExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "unaryExpression"
    // src/LCPLTreeBuilder.g:158:1: unaryExpression : ( SUB unaryExpression -> ^( UNARY_MINUS unaryExpression ) | simpleUnaryExpression );
    public final LCPLTreeBuilderParser.unaryExpression_return unaryExpression() throws RecognitionException {
        LCPLTreeBuilderParser.unaryExpression_return retval = new LCPLTreeBuilderParser.unaryExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SUB124=null;
        LCPLTreeBuilderParser.unaryExpression_return unaryExpression125 = null;

        LCPLTreeBuilderParser.simpleUnaryExpression_return simpleUnaryExpression126 = null;


        CommonTree SUB124_tree=null;
        RewriteRuleTokenStream stream_SUB=new RewriteRuleTokenStream(adaptor,"token SUB");
        RewriteRuleSubtreeStream stream_unaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule unaryExpression");
        try {
            // src/LCPLTreeBuilder.g:159:2: ( SUB unaryExpression -> ^( UNARY_MINUS unaryExpression ) | simpleUnaryExpression )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==SUB) ) {
                alt37=1;
            }
            else if ( ((LA37_0>=INT && LA37_0<=STRING)||LA37_0==NEW||(LA37_0>=ID && LA37_0<=VOID_CONSTANT)||(LA37_0>=IF && LA37_0<=SELF)||(LA37_0>=NOT && LA37_0<=STRING_LITERAL)||LA37_0==72||LA37_0==81||LA37_0==83) ) {
                alt37=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // src/LCPLTreeBuilder.g:159:4: SUB unaryExpression
                    {
                    SUB124=(Token)match(input,SUB,FOLLOW_SUB_in_unaryExpression1411);  
                    stream_SUB.add(SUB124);

                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression1413);
                    unaryExpression125=unaryExpression();

                    state._fsp--;

                    stream_unaryExpression.add(unaryExpression125.getTree());


                    // AST REWRITE
                    // elements: unaryExpression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 159:24: -> ^( UNARY_MINUS unaryExpression )
                    {
                        // src/LCPLTreeBuilder.g:159:27: ^( UNARY_MINUS unaryExpression )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UNARY_MINUS, "UNARY_MINUS"), root_1);

                        adaptor.addChild(root_1, stream_unaryExpression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // src/LCPLTreeBuilder.g:160:4: simpleUnaryExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_simpleUnaryExpression_in_unaryExpression1426);
                    simpleUnaryExpression126=simpleUnaryExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, simpleUnaryExpression126.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "unaryExpression"

    public static class simpleUnaryExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "simpleUnaryExpression"
    // src/LCPLTreeBuilder.g:163:1: simpleUnaryExpression : ( NOT unaryExpression | (e= primaryExpression -> $e) ( '[' (a= expression -> ^( UNARY_BRACKET $simpleUnaryExpression $a) ) ( ',' b= expression -> ^( SUBSTR $simpleUnaryExpression $b) )? ']' -> $simpleUnaryExpression)* );
    public final LCPLTreeBuilderParser.simpleUnaryExpression_return simpleUnaryExpression() throws RecognitionException {
        LCPLTreeBuilderParser.simpleUnaryExpression_return retval = new LCPLTreeBuilderParser.simpleUnaryExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NOT127=null;
        Token char_literal129=null;
        Token char_literal130=null;
        Token char_literal131=null;
        LCPLTreeBuilderParser.primaryExpression_return e = null;

        LCPLTreeBuilderParser.expression_return a = null;

        LCPLTreeBuilderParser.expression_return b = null;

        LCPLTreeBuilderParser.unaryExpression_return unaryExpression128 = null;


        CommonTree NOT127_tree=null;
        CommonTree char_literal129_tree=null;
        CommonTree char_literal130_tree=null;
        CommonTree char_literal131_tree=null;
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_primaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule primaryExpression");
        try {
            // src/LCPLTreeBuilder.g:164:2: ( NOT unaryExpression | (e= primaryExpression -> $e) ( '[' (a= expression -> ^( UNARY_BRACKET $simpleUnaryExpression $a) ) ( ',' b= expression -> ^( SUBSTR $simpleUnaryExpression $b) )? ']' -> $simpleUnaryExpression)* )
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==NOT) ) {
                alt40=1;
            }
            else if ( ((LA40_0>=INT && LA40_0<=STRING)||LA40_0==NEW||(LA40_0>=ID && LA40_0<=VOID_CONSTANT)||(LA40_0>=IF && LA40_0<=SELF)||(LA40_0>=INTEGER && LA40_0<=STRING_LITERAL)||LA40_0==72||LA40_0==81||LA40_0==83) ) {
                alt40=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }
            switch (alt40) {
                case 1 :
                    // src/LCPLTreeBuilder.g:164:4: NOT unaryExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    NOT127=(Token)match(input,NOT,FOLLOW_NOT_in_simpleUnaryExpression1438); 
                    NOT127_tree = (CommonTree)adaptor.create(NOT127);
                    root_0 = (CommonTree)adaptor.becomeRoot(NOT127_tree, root_0);

                    pushFollow(FOLLOW_unaryExpression_in_simpleUnaryExpression1441);
                    unaryExpression128=unaryExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, unaryExpression128.getTree());

                    }
                    break;
                case 2 :
                    // src/LCPLTreeBuilder.g:165:4: (e= primaryExpression -> $e) ( '[' (a= expression -> ^( UNARY_BRACKET $simpleUnaryExpression $a) ) ( ',' b= expression -> ^( SUBSTR $simpleUnaryExpression $b) )? ']' -> $simpleUnaryExpression)*
                    {
                    // src/LCPLTreeBuilder.g:165:4: (e= primaryExpression -> $e)
                    // src/LCPLTreeBuilder.g:165:5: e= primaryExpression
                    {
                    pushFollow(FOLLOW_primaryExpression_in_simpleUnaryExpression1451);
                    e=primaryExpression();

                    state._fsp--;

                    stream_primaryExpression.add(e.getTree());


                    // AST REWRITE
                    // elements: e
                    // token labels: 
                    // rule labels: retval, e
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_e=new RewriteRuleSubtreeStream(adaptor,"rule e",e!=null?e.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 165:27: -> $e
                    {
                        adaptor.addChild(root_0, stream_e.nextTree());

                    }

                    retval.tree = root_0;
                    }

                    // src/LCPLTreeBuilder.g:166:2: ( '[' (a= expression -> ^( UNARY_BRACKET $simpleUnaryExpression $a) ) ( ',' b= expression -> ^( SUBSTR $simpleUnaryExpression $b) )? ']' -> $simpleUnaryExpression)*
                    loop39:
                    do {
                        int alt39=2;
                        int LA39_0 = input.LA(1);

                        if ( (LA39_0==72) ) {
                            alt39=1;
                        }


                        switch (alt39) {
                    	case 1 :
                    	    // src/LCPLTreeBuilder.g:166:3: '[' (a= expression -> ^( UNARY_BRACKET $simpleUnaryExpression $a) ) ( ',' b= expression -> ^( SUBSTR $simpleUnaryExpression $b) )? ']'
                    	    {
                    	    char_literal129=(Token)match(input,72,FOLLOW_72_in_simpleUnaryExpression1461);  
                    	    stream_72.add(char_literal129);

                    	    // src/LCPLTreeBuilder.g:166:7: (a= expression -> ^( UNARY_BRACKET $simpleUnaryExpression $a) )
                    	    // src/LCPLTreeBuilder.g:166:8: a= expression
                    	    {
                    	    pushFollow(FOLLOW_expression_in_simpleUnaryExpression1468);
                    	    a=expression();

                    	    state._fsp--;

                    	    stream_expression.add(a.getTree());


                    	    // AST REWRITE
                    	    // elements: a, simpleUnaryExpression
                    	    // token labels: 
                    	    // rule labels: retval, a
                    	    // token list labels: 
                    	    // rule list labels: 
                    	    // wildcard labels: 
                    	    retval.tree = root_0;
                    	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    	    RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"rule a",a!=null?a.tree:null);

                    	    root_0 = (CommonTree)adaptor.nil();
                    	    // 166:23: -> ^( UNARY_BRACKET $simpleUnaryExpression $a)
                    	    {
                    	        // src/LCPLTreeBuilder.g:166:26: ^( UNARY_BRACKET $simpleUnaryExpression $a)
                    	        {
                    	        CommonTree root_1 = (CommonTree)adaptor.nil();
                    	        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UNARY_BRACKET, "UNARY_BRACKET"), root_1);

                    	        adaptor.addChild(root_1, stream_retval.nextTree());
                    	        adaptor.addChild(root_1, stream_a.nextTree());

                    	        adaptor.addChild(root_0, root_1);
                    	        }

                    	    }

                    	    retval.tree = root_0;
                    	    }

                    	    // src/LCPLTreeBuilder.g:167:2: ( ',' b= expression -> ^( SUBSTR $simpleUnaryExpression $b) )?
                    	    int alt38=2;
                    	    int LA38_0 = input.LA(1);

                    	    if ( (LA38_0==71) ) {
                    	        alt38=1;
                    	    }
                    	    switch (alt38) {
                    	        case 1 :
                    	            // src/LCPLTreeBuilder.g:167:4: ',' b= expression
                    	            {
                    	            char_literal130=(Token)match(input,71,FOLLOW_71_in_simpleUnaryExpression1486);  
                    	            stream_71.add(char_literal130);

                    	            pushFollow(FOLLOW_expression_in_simpleUnaryExpression1492);
                    	            b=expression();

                    	            state._fsp--;

                    	            stream_expression.add(b.getTree());


                    	            // AST REWRITE
                    	            // elements: simpleUnaryExpression, b
                    	            // token labels: 
                    	            // rule labels: retval, b
                    	            // token list labels: 
                    	            // rule list labels: 
                    	            // wildcard labels: 
                    	            retval.tree = root_0;
                    	            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    	            RewriteRuleSubtreeStream stream_b=new RewriteRuleSubtreeStream(adaptor,"rule b",b!=null?b.tree:null);

                    	            root_0 = (CommonTree)adaptor.nil();
                    	            // 167:23: -> ^( SUBSTR $simpleUnaryExpression $b)
                    	            {
                    	                // src/LCPLTreeBuilder.g:167:26: ^( SUBSTR $simpleUnaryExpression $b)
                    	                {
                    	                CommonTree root_1 = (CommonTree)adaptor.nil();
                    	                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SUBSTR, "SUBSTR"), root_1);

                    	                adaptor.addChild(root_1, stream_retval.nextTree());
                    	                adaptor.addChild(root_1, stream_b.nextTree());

                    	                adaptor.addChild(root_0, root_1);
                    	                }

                    	            }

                    	            retval.tree = root_0;
                    	            }
                    	            break;

                    	    }

                    	    char_literal131=(Token)match(input,74,FOLLOW_74_in_simpleUnaryExpression1510);  
                    	    stream_74.add(char_literal131);



                    	    // AST REWRITE
                    	    // elements: simpleUnaryExpression
                    	    // token labels: 
                    	    // rule labels: retval
                    	    // token list labels: 
                    	    // rule list labels: 
                    	    // wildcard labels: 
                    	    retval.tree = root_0;
                    	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    	    root_0 = (CommonTree)adaptor.nil();
                    	    // 168:7: -> $simpleUnaryExpression
                    	    {
                    	        adaptor.addChild(root_0, stream_retval.nextTree());

                    	    }

                    	    retval.tree = root_0;
                    	    }
                    	    break;

                    	default :
                    	    break loop39;
                        }
                    } while (true);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "simpleUnaryExpression"

    public static class primaryExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "primaryExpression"
    // src/LCPLTreeBuilder.g:171:1: primaryExpression : ( '(' expression ')' -> ^( PAR_EXPR expression ) | dispatch | new_object | cast | statement | literal );
    public final LCPLTreeBuilderParser.primaryExpression_return primaryExpression() throws RecognitionException {
        LCPLTreeBuilderParser.primaryExpression_return retval = new LCPLTreeBuilderParser.primaryExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal132=null;
        Token char_literal134=null;
        LCPLTreeBuilderParser.expression_return expression133 = null;

        LCPLTreeBuilderParser.dispatch_return dispatch135 = null;

        LCPLTreeBuilderParser.new_object_return new_object136 = null;

        LCPLTreeBuilderParser.cast_return cast137 = null;

        LCPLTreeBuilderParser.statement_return statement138 = null;

        LCPLTreeBuilderParser.literal_return literal139 = null;


        CommonTree char_literal132_tree=null;
        CommonTree char_literal134_tree=null;
        RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // src/LCPLTreeBuilder.g:172:2: ( '(' expression ')' -> ^( PAR_EXPR expression ) | dispatch | new_object | cast | statement | literal )
            int alt41=6;
            switch ( input.LA(1) ) {
            case 83:
                {
                alt41=1;
                }
                break;
            case 72:
                {
                alt41=2;
                }
                break;
            case NEW:
                {
                alt41=3;
                }
                break;
            case 81:
                {
                alt41=4;
                }
                break;
            case IF:
            case WHILE:
                {
                alt41=5;
                }
                break;
            case INT:
            case STRING:
            case ID:
            case VOID_CONSTANT:
            case SELF:
            case INTEGER:
            case STRING_LITERAL:
                {
                alt41=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }

            switch (alt41) {
                case 1 :
                    // src/LCPLTreeBuilder.g:172:4: '(' expression ')'
                    {
                    char_literal132=(Token)match(input,83,FOLLOW_83_in_primaryExpression1530);  
                    stream_83.add(char_literal132);

                    pushFollow(FOLLOW_expression_in_primaryExpression1532);
                    expression133=expression();

                    state._fsp--;

                    stream_expression.add(expression133.getTree());
                    char_literal134=(Token)match(input,84,FOLLOW_84_in_primaryExpression1534);  
                    stream_84.add(char_literal134);



                    // AST REWRITE
                    // elements: expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 172:23: -> ^( PAR_EXPR expression )
                    {
                        // src/LCPLTreeBuilder.g:172:26: ^( PAR_EXPR expression )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(PAR_EXPR, "PAR_EXPR"), root_1);

                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // src/LCPLTreeBuilder.g:173:4: dispatch
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_dispatch_in_primaryExpression1547);
                    dispatch135=dispatch();

                    state._fsp--;

                    adaptor.addChild(root_0, dispatch135.getTree());

                    }
                    break;
                case 3 :
                    // src/LCPLTreeBuilder.g:174:4: new_object
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_new_object_in_primaryExpression1552);
                    new_object136=new_object();

                    state._fsp--;

                    adaptor.addChild(root_0, new_object136.getTree());

                    }
                    break;
                case 4 :
                    // src/LCPLTreeBuilder.g:175:4: cast
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_cast_in_primaryExpression1557);
                    cast137=cast();

                    state._fsp--;

                    adaptor.addChild(root_0, cast137.getTree());

                    }
                    break;
                case 5 :
                    // src/LCPLTreeBuilder.g:176:4: statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_statement_in_primaryExpression1562);
                    statement138=statement();

                    state._fsp--;

                    adaptor.addChild(root_0, statement138.getTree());

                    }
                    break;
                case 6 :
                    // src/LCPLTreeBuilder.g:177:4: literal
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_literal_in_primaryExpression1567);
                    literal139=literal();

                    state._fsp--;

                    adaptor.addChild(root_0, literal139.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "primaryExpression"

    public static class literal_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "literal"
    // src/LCPLTreeBuilder.g:180:1: literal : ( integer | string | type -> ^( SYMBOL type ) | VOID_CONSTANT | SELF );
    public final LCPLTreeBuilderParser.literal_return literal() throws RecognitionException {
        LCPLTreeBuilderParser.literal_return retval = new LCPLTreeBuilderParser.literal_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token VOID_CONSTANT143=null;
        Token SELF144=null;
        LCPLTreeBuilderParser.integer_return integer140 = null;

        LCPLTreeBuilderParser.string_return string141 = null;

        LCPLTreeBuilderParser.type_return type142 = null;


        CommonTree VOID_CONSTANT143_tree=null;
        CommonTree SELF144_tree=null;
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // src/LCPLTreeBuilder.g:180:9: ( integer | string | type -> ^( SYMBOL type ) | VOID_CONSTANT | SELF )
            int alt42=5;
            switch ( input.LA(1) ) {
            case INTEGER:
                {
                alt42=1;
                }
                break;
            case STRING_LITERAL:
                {
                alt42=2;
                }
                break;
            case INT:
            case STRING:
            case ID:
                {
                alt42=3;
                }
                break;
            case VOID_CONSTANT:
                {
                alt42=4;
                }
                break;
            case SELF:
                {
                alt42=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }

            switch (alt42) {
                case 1 :
                    // src/LCPLTreeBuilder.g:180:11: integer
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_integer_in_literal1577);
                    integer140=integer();

                    state._fsp--;

                    adaptor.addChild(root_0, integer140.getTree());

                    }
                    break;
                case 2 :
                    // src/LCPLTreeBuilder.g:181:4: string
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_string_in_literal1582);
                    string141=string();

                    state._fsp--;

                    adaptor.addChild(root_0, string141.getTree());

                    }
                    break;
                case 3 :
                    // src/LCPLTreeBuilder.g:182:4: type
                    {
                    pushFollow(FOLLOW_type_in_literal1587);
                    type142=type();

                    state._fsp--;

                    stream_type.add(type142.getTree());


                    // AST REWRITE
                    // elements: type
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 182:9: -> ^( SYMBOL type )
                    {
                        // src/LCPLTreeBuilder.g:182:12: ^( SYMBOL type )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SYMBOL, "SYMBOL"), root_1);

                        adaptor.addChild(root_1, stream_type.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // src/LCPLTreeBuilder.g:183:4: VOID_CONSTANT
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    VOID_CONSTANT143=(Token)match(input,VOID_CONSTANT,FOLLOW_VOID_CONSTANT_in_literal1600); 
                    VOID_CONSTANT143_tree = (CommonTree)adaptor.create(VOID_CONSTANT143);
                    adaptor.addChild(root_0, VOID_CONSTANT143_tree);


                    }
                    break;
                case 5 :
                    // src/LCPLTreeBuilder.g:184:4: SELF
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    SELF144=(Token)match(input,SELF,FOLLOW_SELF_in_literal1605); 
                    SELF144_tree = (CommonTree)adaptor.create(SELF144);
                    adaptor.addChild(root_0, SELF144_tree);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "literal"

    public static class integer_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "integer"
    // src/LCPLTreeBuilder.g:187:1: integer : value= INTEGER -> ^( CONSTANT_INT $value) ;
    public final LCPLTreeBuilderParser.integer_return integer() throws RecognitionException {
        LCPLTreeBuilderParser.integer_return retval = new LCPLTreeBuilderParser.integer_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token value=null;

        CommonTree value_tree=null;
        RewriteRuleTokenStream stream_INTEGER=new RewriteRuleTokenStream(adaptor,"token INTEGER");

        try {
            // src/LCPLTreeBuilder.g:187:9: (value= INTEGER -> ^( CONSTANT_INT $value) )
            // src/LCPLTreeBuilder.g:187:11: value= INTEGER
            {
            value=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_integer1617);  
            stream_INTEGER.add(value);



            // AST REWRITE
            // elements: value
            // token labels: value
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_value=new RewriteRuleTokenStream(adaptor,"token value",value);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 187:25: -> ^( CONSTANT_INT $value)
            {
                // src/LCPLTreeBuilder.g:187:28: ^( CONSTANT_INT $value)
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CONSTANT_INT, "CONSTANT_INT"), root_1);

                adaptor.addChild(root_1, stream_value.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "integer"

    public static class string_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "string"
    // src/LCPLTreeBuilder.g:190:1: string : STRING_LITERAL -> ^( CONSTANT_STRING STRING_LITERAL ) ;
    public final LCPLTreeBuilderParser.string_return string() throws RecognitionException {
        LCPLTreeBuilderParser.string_return retval = new LCPLTreeBuilderParser.string_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token STRING_LITERAL145=null;

        CommonTree STRING_LITERAL145_tree=null;
        RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");

        try {
            // src/LCPLTreeBuilder.g:190:8: ( STRING_LITERAL -> ^( CONSTANT_STRING STRING_LITERAL ) )
            // src/LCPLTreeBuilder.g:190:10: STRING_LITERAL
            {
            STRING_LITERAL145=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_string1636);  
            stream_STRING_LITERAL.add(STRING_LITERAL145);



            // AST REWRITE
            // elements: STRING_LITERAL
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 190:26: -> ^( CONSTANT_STRING STRING_LITERAL )
            {
                // src/LCPLTreeBuilder.g:190:29: ^( CONSTANT_STRING STRING_LITERAL )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CONSTANT_STRING, "CONSTANT_STRING"), root_1);

                adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "string"

    public static class new_object_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "new_object"
    // src/LCPLTreeBuilder.g:193:1: new_object : NEW type -> ^( NEW type ) ;
    public final LCPLTreeBuilderParser.new_object_return new_object() throws RecognitionException {
        LCPLTreeBuilderParser.new_object_return retval = new LCPLTreeBuilderParser.new_object_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NEW146=null;
        LCPLTreeBuilderParser.type_return type147 = null;


        CommonTree NEW146_tree=null;
        RewriteRuleTokenStream stream_NEW=new RewriteRuleTokenStream(adaptor,"token NEW");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // src/LCPLTreeBuilder.g:194:2: ( NEW type -> ^( NEW type ) )
            // src/LCPLTreeBuilder.g:194:4: NEW type
            {
            NEW146=(Token)match(input,NEW,FOLLOW_NEW_in_new_object1656);  
            stream_NEW.add(NEW146);

            pushFollow(FOLLOW_type_in_new_object1658);
            type147=type();

            state._fsp--;

            stream_type.add(type147.getTree());


            // AST REWRITE
            // elements: NEW, type
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 194:13: -> ^( NEW type )
            {
                // src/LCPLTreeBuilder.g:194:16: ^( NEW type )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_NEW.nextNode(), root_1);

                adaptor.addChild(root_1, stream_type.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "new_object"

    public static class type_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "type"
    // src/LCPLTreeBuilder.g:197:1: type : ( INT -> ^( TYPE INT ) | STRING -> ^( TYPE STRING ) | a= ID -> ^( TYPE $a) ) ;
    public final LCPLTreeBuilderParser.type_return type() throws RecognitionException {
        LCPLTreeBuilderParser.type_return retval = new LCPLTreeBuilderParser.type_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token a=null;
        Token INT148=null;
        Token STRING149=null;

        CommonTree a_tree=null;
        CommonTree INT148_tree=null;
        CommonTree STRING149_tree=null;
        RewriteRuleTokenStream stream_INT=new RewriteRuleTokenStream(adaptor,"token INT");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");

        try {
            // src/LCPLTreeBuilder.g:197:6: ( ( INT -> ^( TYPE INT ) | STRING -> ^( TYPE STRING ) | a= ID -> ^( TYPE $a) ) )
            // src/LCPLTreeBuilder.g:197:8: ( INT -> ^( TYPE INT ) | STRING -> ^( TYPE STRING ) | a= ID -> ^( TYPE $a) )
            {
            // src/LCPLTreeBuilder.g:197:8: ( INT -> ^( TYPE INT ) | STRING -> ^( TYPE STRING ) | a= ID -> ^( TYPE $a) )
            int alt43=3;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt43=1;
                }
                break;
            case STRING:
                {
                alt43=2;
                }
                break;
            case ID:
                {
                alt43=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }

            switch (alt43) {
                case 1 :
                    // src/LCPLTreeBuilder.g:197:9: INT
                    {
                    INT148=(Token)match(input,INT,FOLLOW_INT_in_type1677);  
                    stream_INT.add(INT148);



                    // AST REWRITE
                    // elements: INT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 197:13: -> ^( TYPE INT )
                    {
                        // src/LCPLTreeBuilder.g:197:16: ^( TYPE INT )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPE, "TYPE"), root_1);

                        adaptor.addChild(root_1, stream_INT.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // src/LCPLTreeBuilder.g:198:4: STRING
                    {
                    STRING149=(Token)match(input,STRING,FOLLOW_STRING_in_type1690);  
                    stream_STRING.add(STRING149);



                    // AST REWRITE
                    // elements: STRING
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 198:11: -> ^( TYPE STRING )
                    {
                        // src/LCPLTreeBuilder.g:198:14: ^( TYPE STRING )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPE, "TYPE"), root_1);

                        adaptor.addChild(root_1, stream_STRING.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // src/LCPLTreeBuilder.g:199:4: a= ID
                    {
                    a=(Token)match(input,ID,FOLLOW_ID_in_type1707);  
                    stream_ID.add(a);



                    // AST REWRITE
                    // elements: a
                    // token labels: a
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_a=new RewriteRuleTokenStream(adaptor,"token a",a);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 199:11: -> ^( TYPE $a)
                    {
                        // src/LCPLTreeBuilder.g:199:14: ^( TYPE $a)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPE, "TYPE"), root_1);

                        adaptor.addChild(root_1, stream_a.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "type"

    // Delegated rules


 

    public static final BitSet FOLLOW_class_definition_in_program461 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_classdef_in_class_definition483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_classdef503 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_classdef507 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_INHERITS_in_classdef509 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_classdef513 = new BitSet(new long[]{0x0000110000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_members_in_classdef515 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_END_in_classdef518 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_classdef520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_classdef549 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_classdef553 = new BitSet(new long[]{0x0000110000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_members_in_classdef555 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_END_in_classdef558 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_classdef560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_method590 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_method592 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_END_in_method594 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_method596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_method609 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_method611 = new BitSet(new long[]{0xC807342600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_block_in_method613 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_END_in_method615 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_method617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_method632 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_method634 = new BitSet(new long[]{0x0000100600000000L});
    public static final BitSet FOLLOW_type_in_method636 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_method638 = new BitSet(new long[]{0xC807342600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_block_in_method640 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_END_in_method642 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_method644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_method661 = new BitSet(new long[]{0x0000100600000000L});
    public static final BitSet FOLLOW_formal_param_in_method668 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000E0L});
    public static final BitSet FOLLOW_71_in_method672 = new BitSet(new long[]{0x0000100600000000L});
    public static final BitSet FOLLOW_formal_param_in_method678 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000E0L});
    public static final BitSet FOLLOW_70_in_method683 = new BitSet(new long[]{0x0000100600000000L});
    public static final BitSet FOLLOW_type_in_method685 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_method689 = new BitSet(new long[]{0xC807342600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_block_in_method691 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_END_in_method693 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_method695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_member_in_members727 = new BitSet(new long[]{0x0000100000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_decl_section_in_member749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_in_member762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_block781 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_block783 = new BitSet(new long[]{0xC807342600000002L,0x00000000000A0901L});
    public static final BitSet FOLLOW_local_decl_in_block787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_local_decl_in_block805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOCAL_in_local_decl825 = new BitSet(new long[]{0x0000100600000000L});
    public static final BitSet FOLLOW_attribute_in_local_decl828 = new BitSet(new long[]{0x0000110600000000L});
    public static final BitSet FOLLOW_END_in_local_decl832 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_local_decl834 = new BitSet(new long[]{0xC807342600000002L,0x00000000000A0901L});
    public static final BitSet FOLLOW_block_in_local_decl836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formal_param861 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_formal_param863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_dispatch885 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0101L});
    public static final BitSet FOLLOW_primaryExpression_in_dispatch889 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_dispatch890 = new BitSet(new long[]{0x0000100600000000L});
    public static final BitSet FOLLOW_type_in_dispatch894 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_dispatch896 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_dispatch903 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0D01L});
    public static final BitSet FOLLOW_dispatch_arguments_in_dispatch905 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_dispatch908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_dispatch934 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_dispatch936 = new BitSet(new long[]{0x0000100600000000L});
    public static final BitSet FOLLOW_type_in_dispatch939 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_dispatch941 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_dispatch945 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0D01L});
    public static final BitSet FOLLOW_dispatch_arguments_in_dispatch947 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_dispatch950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_dispatch_arguments977 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_dispatch_arguments980 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_expression_in_dispatch_arguments984 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_76_in_decl_section1007 = new BitSet(new long[]{0x0000100600000000L});
    public static final BitSet FOLLOW_attribute_in_decl_section1010 = new BitSet(new long[]{0x0000110600000000L});
    public static final BitSet FOLLOW_END_in_decl_section1014 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_decl_section1016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_attribute1040 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_attribute1042 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_attribute1044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_attribute1059 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_attribute1061 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_attribute1063 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_attribute1065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_attribute1082 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_attribute1084 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_EQ_in_attribute1086 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_expression_in_attribute1088 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_attribute1090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_attribute1107 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_attribute1109 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_attribute1111 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_EQ_in_attribute1113 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_expression_in_attribute1115 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_attribute1117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_statement1142 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_expression_in_statement1148 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_statement1150 = new BitSet(new long[]{0xC807342600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_block_in_statement1154 = new BitSet(new long[]{0x0000010000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_statement1157 = new BitSet(new long[]{0xC807342600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_block_in_statement1163 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_END_in_statement1167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_statement1191 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_expression_in_statement1193 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_statement1195 = new BitSet(new long[]{0xC807342600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_block_in_statement1197 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_END_in_statement1199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_cast1219 = new BitSet(new long[]{0x0000100600000000L});
    public static final BitSet FOLLOW_type_in_cast1220 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_expression_in_cast1222 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_82_in_cast1224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignmentExpression_in_expression1245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_equalityExpression_in_assignmentExpression1266 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_EQ_in_assignmentExpression1269 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_assignmentExpression_in_assignmentExpression1272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_assignmentExpression1279 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_ID_in_assignmentExpression1281 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_EQ_in_assignmentExpression1284 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_assignmentExpression_in_assignmentExpression1287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relationalExpression_in_equalityExpression1302 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_EQUAL_in_equalityExpression1305 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0101L});
    public static final BitSet FOLLOW_relationalExpression_in_equalityExpression1308 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_relationalExpression1322 = new BitSet(new long[]{0x0300000000000002L});
    public static final BitSet FOLLOW_LTEQ_in_relationalExpression1326 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0101L});
    public static final BitSet FOLLOW_LT_in_relationalExpression1331 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0101L});
    public static final BitSet FOLLOW_additiveExpression_in_relationalExpression1336 = new BitSet(new long[]{0x0300000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1349 = new BitSet(new long[]{0x0C00000000000002L});
    public static final BitSet FOLLOW_PLUS_in_additiveExpression1355 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0101L});
    public static final BitSet FOLLOW_SUB_in_additiveExpression1360 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0101L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1365 = new BitSet(new long[]{0x0C00000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression1379 = new BitSet(new long[]{0x3000000000000002L});
    public static final BitSet FOLLOW_STAR_in_multiplicativeExpression1385 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0101L});
    public static final BitSet FOLLOW_DIV_in_multiplicativeExpression1390 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0101L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression1396 = new BitSet(new long[]{0x3000000000000002L});
    public static final BitSet FOLLOW_SUB_in_unaryExpression1411 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0101L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression1413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleUnaryExpression_in_unaryExpression1426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_simpleUnaryExpression1438 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0101L});
    public static final BitSet FOLLOW_unaryExpression_in_simpleUnaryExpression1441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primaryExpression_in_simpleUnaryExpression1451 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_simpleUnaryExpression1461 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_expression_in_simpleUnaryExpression1468 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000480L});
    public static final BitSet FOLLOW_71_in_simpleUnaryExpression1486 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_expression_in_simpleUnaryExpression1492 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_simpleUnaryExpression1510 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_83_in_primaryExpression1530 = new BitSet(new long[]{0xC807302600000000L,0x00000000000A0901L});
    public static final BitSet FOLLOW_expression_in_primaryExpression1532 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_primaryExpression1534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dispatch_in_primaryExpression1547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new_object_in_primaryExpression1552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cast_in_primaryExpression1557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_primaryExpression1562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_primaryExpression1567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_integer_in_literal1577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_literal1582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_literal1587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_CONSTANT_in_literal1600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELF_in_literal1605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_integer1617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_string1636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_new_object1656 = new BitSet(new long[]{0x0000100600000000L});
    public static final BitSet FOLLOW_type_in_new_object1658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_type1677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_type1690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type1707 = new BitSet(new long[]{0x0000000000000002L});

}