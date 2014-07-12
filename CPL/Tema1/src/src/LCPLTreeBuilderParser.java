// $ANTLR 3.2 Sep 23, 2009 12:02:23 src/LCPLTreeBuilder.g 2013-11-11 12:56:04

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class LCPLTreeBuilderParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PROGRAM", "CLASSDEFINITION", "CONSTANT_INT", "FORMAL_PARAM", "CONSTANT_STRING", "SYMBOL", "EQ_COMP", "DECL_SECTION", "UNARY_MINUS", "PAR_EXPR", "BLOCK", "OBJ", "EXPR", "EMPTY_DISPATCH", "LOCAL_DECL", "DISPATCH", "DISPATCH_ARGUMENTS", "DECLARATION_SECTION", "EMPTY_METHOD", "DISPATCH_ARGS", "MEMBER", "MEMBERS", "ATTRIBUTE", "ASSIGNMENT", "ATTR", "EXPRESSION", "TYPE", "INT", "STRING", "CLASS", "INHERITS", "NEW", "METHOD", "STATEMENT", "END", "NULL", "LOCAL", "ID", "VOID_CONSTANT", "CAST", "STRING_CONST", "IF", "WHILE", "SUBSTR", "EQ", "EQUAL", "LTEQ", "LT", "PLUS", "SUB", "STAR", "DIV", "NOT", "INTEGER", "STRING_LITERAL", "ESCAPE_SEQUENCE", "LINE_COMMENT", "WS", "';'", "':'", "'->'", "','", "'['", "'.'", "']'", "'var'", "'then'", "'else'", "'loop'", "'{'", "'}'", "'('", "')'"
    };
    public static final int CAST=43;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int CLASSDEFINITION=5;
    public static final int T__66=66;
    public static final int LT=51;
    public static final int CLASS=33;
    public static final int T__67=67;
    public static final int STAR=54;
    public static final int T__64=64;
    public static final int DISPATCH_ARGUMENTS=20;
    public static final int MEMBER=24;
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
    public static final int WS=61;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__70=70;
    public static final int DECLARATION_SECTION=21;
    public static final int BLOCK=14;
    public static final int CONSTANT_STRING=8;
    public static final int PROGRAM=4;
    public static final int ASSIGNMENT=27;
    public static final int DIV=55;
    public static final int END=38;
    public static final int T__76=76;
    public static final int T__75=75;
    public static final int T__74=74;
    public static final int METHOD=36;
    public static final int T__73=73;
    public static final int EMPTY_DISPATCH=17;
    public static final int STRING=32;

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
    // src/LCPLTreeBuilder.g:57:1: program : ( ( class_definition )+ ) -> ^( PROGRAM ( class_definition )+ ) ;
    public final LCPLTreeBuilderParser.program_return program() throws RecognitionException {
        LCPLTreeBuilderParser.program_return retval = new LCPLTreeBuilderParser.program_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        LCPLTreeBuilderParser.class_definition_return class_definition1 = null;


        RewriteRuleSubtreeStream stream_class_definition=new RewriteRuleSubtreeStream(adaptor,"rule class_definition");
        try {
            // src/LCPLTreeBuilder.g:57:9: ( ( ( class_definition )+ ) -> ^( PROGRAM ( class_definition )+ ) )
            // src/LCPLTreeBuilder.g:57:13: ( ( class_definition )+ )
            {
            // src/LCPLTreeBuilder.g:57:13: ( ( class_definition )+ )
            // src/LCPLTreeBuilder.g:57:14: ( class_definition )+
            {
            // src/LCPLTreeBuilder.g:57:14: ( class_definition )+
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
            	    // src/LCPLTreeBuilder.g:57:14: class_definition
            	    {
            	    pushFollow(FOLLOW_class_definition_in_program415);
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
            // 57:33: -> ^( PROGRAM ( class_definition )+ )
            {
                // src/LCPLTreeBuilder.g:57:36: ^( PROGRAM ( class_definition )+ )
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
    // src/LCPLTreeBuilder.g:59:1: class_definition : classdef -> ^( CLASSDEFINITION classdef ) ;
    public final LCPLTreeBuilderParser.class_definition_return class_definition() throws RecognitionException {
        LCPLTreeBuilderParser.class_definition_return retval = new LCPLTreeBuilderParser.class_definition_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        LCPLTreeBuilderParser.classdef_return classdef2 = null;


        RewriteRuleSubtreeStream stream_classdef=new RewriteRuleSubtreeStream(adaptor,"rule classdef");
        try {
            // src/LCPLTreeBuilder.g:60:2: ( classdef -> ^( CLASSDEFINITION classdef ) )
            // src/LCPLTreeBuilder.g:60:4: classdef
            {
            pushFollow(FOLLOW_classdef_in_class_definition435);
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
            // 60:14: -> ^( CLASSDEFINITION classdef )
            {
                // src/LCPLTreeBuilder.g:60:17: ^( CLASSDEFINITION classdef )
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
    // src/LCPLTreeBuilder.g:62:1: classdef : ( CLASS name= ID INHERITS parent= ID ( members )? END ';' -> ^( CLASS $name $parent ( members )? ) | CLASS name= ID ( members )? END ';' -> ^( CLASS $name ( members )? ) );
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
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_INHERITS=new RewriteRuleTokenStream(adaptor,"token INHERITS");
        RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
        RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
        RewriteRuleSubtreeStream stream_members=new RewriteRuleSubtreeStream(adaptor,"rule members");
        try {
            // src/LCPLTreeBuilder.g:62:10: ( CLASS name= ID INHERITS parent= ID ( members )? END ';' -> ^( CLASS $name $parent ( members )? ) | CLASS name= ID ( members )? END ';' -> ^( CLASS $name ( members )? ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==CLASS) ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==ID) ) {
                    int LA4_2 = input.LA(3);

                    if ( (LA4_2==INHERITS) ) {
                        alt4=1;
                    }
                    else if ( (LA4_2==END||LA4_2==ID||LA4_2==69) ) {
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
                    // src/LCPLTreeBuilder.g:62:13: CLASS name= ID INHERITS parent= ID ( members )? END ';'
                    {
                    CLASS3=(Token)match(input,CLASS,FOLLOW_CLASS_in_classdef453);  
                    stream_CLASS.add(CLASS3);

                    name=(Token)match(input,ID,FOLLOW_ID_in_classdef457);  
                    stream_ID.add(name);

                    INHERITS4=(Token)match(input,INHERITS,FOLLOW_INHERITS_in_classdef459);  
                    stream_INHERITS.add(INHERITS4);

                    parent=(Token)match(input,ID,FOLLOW_ID_in_classdef463);  
                    stream_ID.add(parent);

                    // src/LCPLTreeBuilder.g:62:46: ( members )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==ID||LA2_0==69) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:62:46: members
                            {
                            pushFollow(FOLLOW_members_in_classdef465);
                            members5=members();

                            state._fsp--;

                            stream_members.add(members5.getTree());

                            }
                            break;

                    }

                    END6=(Token)match(input,END,FOLLOW_END_in_classdef468);  
                    stream_END.add(END6);

                    char_literal7=(Token)match(input,62,FOLLOW_62_in_classdef470);  
                    stream_62.add(char_literal7);



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
                    // 62:63: -> ^( CLASS $name $parent ( members )? )
                    {
                        // src/LCPLTreeBuilder.g:63:9: ^( CLASS $name $parent ( members )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_CLASS.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_name.nextNode());
                        adaptor.addChild(root_1, stream_parent.nextNode());
                        // src/LCPLTreeBuilder.g:63:31: ( members )?
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
                    // src/LCPLTreeBuilder.g:64:13: CLASS name= ID ( members )? END ';'
                    {
                    CLASS8=(Token)match(input,CLASS,FOLLOW_CLASS_in_classdef508);  
                    stream_CLASS.add(CLASS8);

                    name=(Token)match(input,ID,FOLLOW_ID_in_classdef512);  
                    stream_ID.add(name);

                    // src/LCPLTreeBuilder.g:64:27: ( members )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==ID||LA3_0==69) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:64:27: members
                            {
                            pushFollow(FOLLOW_members_in_classdef514);
                            members9=members();

                            state._fsp--;

                            stream_members.add(members9.getTree());

                            }
                            break;

                    }

                    END10=(Token)match(input,END,FOLLOW_END_in_classdef517);  
                    stream_END.add(END10);

                    char_literal11=(Token)match(input,62,FOLLOW_62_in_classdef519);  
                    stream_62.add(char_literal11);



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
                    // 64:44: -> ^( CLASS $name ( members )? )
                    {
                        // src/LCPLTreeBuilder.g:65:9: ^( CLASS $name ( members )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_CLASS.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_name.nextNode());
                        // src/LCPLTreeBuilder.g:65:23: ( members )?
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
    // src/LCPLTreeBuilder.g:68:1: method : ( ID ':' END ';' -> ^( EMPTY_METHOD ID ) | ID ':' block END ';' -> ^( METHOD ID block ) | ID '->' type ':' block END ';' -> ^( METHOD ID type block ) | ID (f+= formal_param ) ( ',' f+= formal_param )* ( '->' type )? ':' block END ';' -> ^( METHOD ID ( $f)+ ( type )? block ) );
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
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
        RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
        RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleSubtreeStream stream_formal_param=new RewriteRuleSubtreeStream(adaptor,"rule formal_param");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // src/LCPLTreeBuilder.g:68:9: ( ID ':' END ';' -> ^( EMPTY_METHOD ID ) | ID ':' block END ';' -> ^( METHOD ID block ) | ID '->' type ':' block END ';' -> ^( METHOD ID type block ) | ID (f+= formal_param ) ( ',' f+= formal_param )* ( '->' type )? ':' block END ';' -> ^( METHOD ID ( $f)+ ( type )? block ) )
            int alt7=4;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==ID) ) {
                switch ( input.LA(2) ) {
                case 63:
                    {
                    int LA7_2 = input.LA(3);

                    if ( (LA7_2==END) ) {
                        alt7=1;
                    }
                    else if ( ((LA7_2>=INT && LA7_2<=STRING)||LA7_2==NEW||(LA7_2>=LOCAL && LA7_2<=VOID_CONSTANT)||(LA7_2>=IF && LA7_2<=WHILE)||LA7_2==SUB||(LA7_2>=NOT && LA7_2<=STRING_LITERAL)||LA7_2==66||LA7_2==73||LA7_2==75) ) {
                        alt7=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case 64:
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
                    // src/LCPLTreeBuilder.g:68:11: ID ':' END ';'
                    {
                    ID12=(Token)match(input,ID,FOLLOW_ID_in_method553);  
                    stream_ID.add(ID12);

                    char_literal13=(Token)match(input,63,FOLLOW_63_in_method555);  
                    stream_63.add(char_literal13);

                    END14=(Token)match(input,END,FOLLOW_END_in_method557);  
                    stream_END.add(END14);

                    char_literal15=(Token)match(input,62,FOLLOW_62_in_method559);  
                    stream_62.add(char_literal15);



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
                    // 68:26: -> ^( EMPTY_METHOD ID )
                    {
                        // src/LCPLTreeBuilder.g:68:29: ^( EMPTY_METHOD ID )
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
                    // src/LCPLTreeBuilder.g:69:4: ID ':' block END ';'
                    {
                    ID16=(Token)match(input,ID,FOLLOW_ID_in_method572);  
                    stream_ID.add(ID16);

                    char_literal17=(Token)match(input,63,FOLLOW_63_in_method574);  
                    stream_63.add(char_literal17);

                    pushFollow(FOLLOW_block_in_method576);
                    block18=block();

                    state._fsp--;

                    stream_block.add(block18.getTree());
                    END19=(Token)match(input,END,FOLLOW_END_in_method578);  
                    stream_END.add(END19);

                    char_literal20=(Token)match(input,62,FOLLOW_62_in_method580);  
                    stream_62.add(char_literal20);



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
                    // 69:25: -> ^( METHOD ID block )
                    {
                        // src/LCPLTreeBuilder.g:69:28: ^( METHOD ID block )
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
                    // src/LCPLTreeBuilder.g:70:4: ID '->' type ':' block END ';'
                    {
                    ID21=(Token)match(input,ID,FOLLOW_ID_in_method595);  
                    stream_ID.add(ID21);

                    string_literal22=(Token)match(input,64,FOLLOW_64_in_method597);  
                    stream_64.add(string_literal22);

                    pushFollow(FOLLOW_type_in_method599);
                    type23=type();

                    state._fsp--;

                    stream_type.add(type23.getTree());
                    char_literal24=(Token)match(input,63,FOLLOW_63_in_method601);  
                    stream_63.add(char_literal24);

                    pushFollow(FOLLOW_block_in_method603);
                    block25=block();

                    state._fsp--;

                    stream_block.add(block25.getTree());
                    END26=(Token)match(input,END,FOLLOW_END_in_method605);  
                    stream_END.add(END26);

                    char_literal27=(Token)match(input,62,FOLLOW_62_in_method607);  
                    stream_62.add(char_literal27);



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
                    // 70:35: -> ^( METHOD ID type block )
                    {
                        // src/LCPLTreeBuilder.g:70:38: ^( METHOD ID type block )
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
                    // src/LCPLTreeBuilder.g:71:4: ID (f+= formal_param ) ( ',' f+= formal_param )* ( '->' type )? ':' block END ';'
                    {
                    ID28=(Token)match(input,ID,FOLLOW_ID_in_method624);  
                    stream_ID.add(ID28);

                    // src/LCPLTreeBuilder.g:71:7: (f+= formal_param )
                    // src/LCPLTreeBuilder.g:71:8: f+= formal_param
                    {
                    pushFollow(FOLLOW_formal_param_in_method631);
                    f=formal_param();

                    state._fsp--;

                    stream_formal_param.add(f.getTree());
                    if (list_f==null) list_f=new ArrayList();
                    list_f.add(f.getTree());


                    }

                    // src/LCPLTreeBuilder.g:71:27: ( ',' f+= formal_param )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==65) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // src/LCPLTreeBuilder.g:71:28: ',' f+= formal_param
                    	    {
                    	    char_literal29=(Token)match(input,65,FOLLOW_65_in_method635);  
                    	    stream_65.add(char_literal29);

                    	    pushFollow(FOLLOW_formal_param_in_method641);
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

                    // src/LCPLTreeBuilder.g:71:52: ( '->' type )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==64) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:71:53: '->' type
                            {
                            string_literal30=(Token)match(input,64,FOLLOW_64_in_method646);  
                            stream_64.add(string_literal30);

                            pushFollow(FOLLOW_type_in_method648);
                            type31=type();

                            state._fsp--;

                            stream_type.add(type31.getTree());

                            }
                            break;

                    }

                    char_literal32=(Token)match(input,63,FOLLOW_63_in_method652);  
                    stream_63.add(char_literal32);

                    pushFollow(FOLLOW_block_in_method654);
                    block33=block();

                    state._fsp--;

                    stream_block.add(block33.getTree());
                    END34=(Token)match(input,END,FOLLOW_END_in_method656);  
                    stream_END.add(END34);

                    char_literal35=(Token)match(input,62,FOLLOW_62_in_method658);  
                    stream_62.add(char_literal35);



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
                    // 71:83: -> ^( METHOD ID ( $f)+ ( type )? block )
                    {
                        // src/LCPLTreeBuilder.g:71:86: ^( METHOD ID ( $f)+ ( type )? block )
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
                        // src/LCPLTreeBuilder.g:71:102: ( type )?
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
    // src/LCPLTreeBuilder.g:73:1: members : ( ( member )+ ) -> ^( MEMBERS ( member )+ ) ;
    public final LCPLTreeBuilderParser.members_return members() throws RecognitionException {
        LCPLTreeBuilderParser.members_return retval = new LCPLTreeBuilderParser.members_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        LCPLTreeBuilderParser.member_return member36 = null;


        RewriteRuleSubtreeStream stream_member=new RewriteRuleSubtreeStream(adaptor,"rule member");
        try {
            // src/LCPLTreeBuilder.g:73:9: ( ( ( member )+ ) -> ^( MEMBERS ( member )+ ) )
            // src/LCPLTreeBuilder.g:73:11: ( ( member )+ )
            {
            // src/LCPLTreeBuilder.g:73:11: ( ( member )+ )
            // src/LCPLTreeBuilder.g:73:12: ( member )+
            {
            // src/LCPLTreeBuilder.g:73:12: ( member )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==ID||LA8_0==69) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:73:12: member
            	    {
            	    pushFollow(FOLLOW_member_in_members688);
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
            // 73:21: -> ^( MEMBERS ( member )+ )
            {
                // src/LCPLTreeBuilder.g:73:24: ^( MEMBERS ( member )+ )
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
    // src/LCPLTreeBuilder.g:76:1: member : ( decl_section -> ^( MEMBER decl_section ) | method -> ^( MEMBER method ) );
    public final LCPLTreeBuilderParser.member_return member() throws RecognitionException {
        LCPLTreeBuilderParser.member_return retval = new LCPLTreeBuilderParser.member_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        LCPLTreeBuilderParser.decl_section_return decl_section37 = null;

        LCPLTreeBuilderParser.method_return method38 = null;


        RewriteRuleSubtreeStream stream_decl_section=new RewriteRuleSubtreeStream(adaptor,"rule decl_section");
        RewriteRuleSubtreeStream stream_method=new RewriteRuleSubtreeStream(adaptor,"rule method");
        try {
            // src/LCPLTreeBuilder.g:76:8: ( decl_section -> ^( MEMBER decl_section ) | method -> ^( MEMBER method ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==69) ) {
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
                    // src/LCPLTreeBuilder.g:76:10: decl_section
                    {
                    pushFollow(FOLLOW_decl_section_in_member708);
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
                    // 76:23: -> ^( MEMBER decl_section )
                    {
                        // src/LCPLTreeBuilder.g:76:26: ^( MEMBER decl_section )
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
                    // src/LCPLTreeBuilder.g:77:4: method
                    {
                    pushFollow(FOLLOW_method_in_member721);
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
                    // 77:11: -> ^( MEMBER method )
                    {
                        // src/LCPLTreeBuilder.g:77:14: ^( MEMBER method )
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
    // src/LCPLTreeBuilder.g:79:1: block : ( ( expression ';' )+ ( local_decl )? -> ^( BLOCK ( expression )+ ( local_decl )? ) | local_decl -> ^( BLOCK local_decl ) );
    public final LCPLTreeBuilderParser.block_return block() throws RecognitionException {
        LCPLTreeBuilderParser.block_return retval = new LCPLTreeBuilderParser.block_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal40=null;
        LCPLTreeBuilderParser.expression_return expression39 = null;

        LCPLTreeBuilderParser.local_decl_return local_decl41 = null;

        LCPLTreeBuilderParser.local_decl_return local_decl42 = null;


        CommonTree char_literal40_tree=null;
        RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_local_decl=new RewriteRuleSubtreeStream(adaptor,"rule local_decl");
        try {
            // src/LCPLTreeBuilder.g:79:7: ( ( expression ';' )+ ( local_decl )? -> ^( BLOCK ( expression )+ ( local_decl )? ) | local_decl -> ^( BLOCK local_decl ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=INT && LA12_0<=STRING)||LA12_0==NEW||(LA12_0>=ID && LA12_0<=VOID_CONSTANT)||(LA12_0>=IF && LA12_0<=WHILE)||LA12_0==SUB||(LA12_0>=NOT && LA12_0<=STRING_LITERAL)||LA12_0==66||LA12_0==73||LA12_0==75) ) {
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
                    // src/LCPLTreeBuilder.g:79:9: ( expression ';' )+ ( local_decl )?
                    {
                    // src/LCPLTreeBuilder.g:79:9: ( expression ';' )+
                    int cnt10=0;
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0>=INT && LA10_0<=STRING)||LA10_0==NEW||(LA10_0>=ID && LA10_0<=VOID_CONSTANT)||(LA10_0>=IF && LA10_0<=WHILE)||LA10_0==SUB||(LA10_0>=NOT && LA10_0<=STRING_LITERAL)||LA10_0==66||LA10_0==73||LA10_0==75) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // src/LCPLTreeBuilder.g:79:10: expression ';'
                    	    {
                    	    pushFollow(FOLLOW_expression_in_block738);
                    	    expression39=expression();

                    	    state._fsp--;

                    	    stream_expression.add(expression39.getTree());
                    	    char_literal40=(Token)match(input,62,FOLLOW_62_in_block740);  
                    	    stream_62.add(char_literal40);


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

                    // src/LCPLTreeBuilder.g:79:27: ( local_decl )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==LOCAL) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:79:27: local_decl
                            {
                            pushFollow(FOLLOW_local_decl_in_block744);
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
                    // 79:39: -> ^( BLOCK ( expression )+ ( local_decl )? )
                    {
                        // src/LCPLTreeBuilder.g:79:42: ^( BLOCK ( expression )+ ( local_decl )? )
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
                        // src/LCPLTreeBuilder.g:79:62: ( local_decl )?
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
                    // src/LCPLTreeBuilder.g:80:4: local_decl
                    {
                    pushFollow(FOLLOW_local_decl_in_block762);
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
                    // 80:15: -> ^( BLOCK local_decl )
                    {
                        // src/LCPLTreeBuilder.g:80:18: ^( BLOCK local_decl )
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
    // src/LCPLTreeBuilder.g:81:1: local_decl : LOCAL ( attribute )+ END ';' ( block )? -> ^( LOCAL ( attribute )+ ( block )? ) ;
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
        RewriteRuleTokenStream stream_LOCAL=new RewriteRuleTokenStream(adaptor,"token LOCAL");
        RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
        RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
        RewriteRuleSubtreeStream stream_attribute=new RewriteRuleSubtreeStream(adaptor,"rule attribute");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // src/LCPLTreeBuilder.g:82:2: ( LOCAL ( attribute )+ END ';' ( block )? -> ^( LOCAL ( attribute )+ ( block )? ) )
            // src/LCPLTreeBuilder.g:82:4: LOCAL ( attribute )+ END ';' ( block )?
            {
            LOCAL43=(Token)match(input,LOCAL,FOLLOW_LOCAL_in_local_decl778);  
            stream_LOCAL.add(LOCAL43);

            // src/LCPLTreeBuilder.g:82:10: ( attribute )+
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
            	    // src/LCPLTreeBuilder.g:82:11: attribute
            	    {
            	    pushFollow(FOLLOW_attribute_in_local_decl781);
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

            END45=(Token)match(input,END,FOLLOW_END_in_local_decl785);  
            stream_END.add(END45);

            char_literal46=(Token)match(input,62,FOLLOW_62_in_local_decl787);  
            stream_62.add(char_literal46);

            // src/LCPLTreeBuilder.g:82:31: ( block )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=INT && LA14_0<=STRING)||LA14_0==NEW||(LA14_0>=LOCAL && LA14_0<=VOID_CONSTANT)||(LA14_0>=IF && LA14_0<=WHILE)||LA14_0==SUB||(LA14_0>=NOT && LA14_0<=STRING_LITERAL)||LA14_0==66||LA14_0==73||LA14_0==75) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // src/LCPLTreeBuilder.g:82:31: block
                    {
                    pushFollow(FOLLOW_block_in_local_decl789);
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
            // 82:39: -> ^( LOCAL ( attribute )+ ( block )? )
            {
                // src/LCPLTreeBuilder.g:82:42: ^( LOCAL ( attribute )+ ( block )? )
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
                // src/LCPLTreeBuilder.g:82:61: ( block )?
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
    // src/LCPLTreeBuilder.g:84:1: formal_param : type ID -> ^( FORMAL_PARAM type ID ) ;
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
            // src/LCPLTreeBuilder.g:85:2: ( type ID -> ^( FORMAL_PARAM type ID ) )
            // src/LCPLTreeBuilder.g:85:4: type ID
            {
            pushFollow(FOLLOW_type_in_formal_param812);
            type48=type();

            state._fsp--;

            stream_type.add(type48.getTree());
            ID49=(Token)match(input,ID,FOLLOW_ID_in_formal_param814);  
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
            // 85:12: -> ^( FORMAL_PARAM type ID )
            {
                // src/LCPLTreeBuilder.g:85:15: ^( FORMAL_PARAM type ID )
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
    // src/LCPLTreeBuilder.g:87:1: dispatch : '[' ( ( primaryExpression '.' ) ( type '.' )? )? ID ( dispatch_arguments )? ']' -> ^( DISPATCH ( primaryExpression )? ( type )? ID ( dispatch_arguments )? ) ;
    public final LCPLTreeBuilderParser.dispatch_return dispatch() throws RecognitionException {
        LCPLTreeBuilderParser.dispatch_return retval = new LCPLTreeBuilderParser.dispatch_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal50=null;
        Token char_literal52=null;
        Token char_literal54=null;
        Token ID55=null;
        Token char_literal57=null;
        LCPLTreeBuilderParser.primaryExpression_return primaryExpression51 = null;

        LCPLTreeBuilderParser.type_return type53 = null;

        LCPLTreeBuilderParser.dispatch_arguments_return dispatch_arguments56 = null;


        CommonTree char_literal50_tree=null;
        CommonTree char_literal52_tree=null;
        CommonTree char_literal54_tree=null;
        CommonTree ID55_tree=null;
        CommonTree char_literal57_tree=null;
        RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
        RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_dispatch_arguments=new RewriteRuleSubtreeStream(adaptor,"rule dispatch_arguments");
        RewriteRuleSubtreeStream stream_primaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule primaryExpression");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // src/LCPLTreeBuilder.g:88:2: ( '[' ( ( primaryExpression '.' ) ( type '.' )? )? ID ( dispatch_arguments )? ']' -> ^( DISPATCH ( primaryExpression )? ( type )? ID ( dispatch_arguments )? ) )
            // src/LCPLTreeBuilder.g:88:4: '[' ( ( primaryExpression '.' ) ( type '.' )? )? ID ( dispatch_arguments )? ']'
            {
            char_literal50=(Token)match(input,66,FOLLOW_66_in_dispatch834);  
            stream_66.add(char_literal50);

            // src/LCPLTreeBuilder.g:88:8: ( ( primaryExpression '.' ) ( type '.' )? )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0>=INT && LA16_0<=STRING)||LA16_0==NEW||LA16_0==VOID_CONSTANT||(LA16_0>=IF && LA16_0<=WHILE)||(LA16_0>=INTEGER && LA16_0<=STRING_LITERAL)||LA16_0==66||LA16_0==73||LA16_0==75) ) {
                alt16=1;
            }
            else if ( (LA16_0==ID) ) {
                int LA16_2 = input.LA(2);

                if ( (LA16_2==67) ) {
                    alt16=1;
                }
            }
            switch (alt16) {
                case 1 :
                    // src/LCPLTreeBuilder.g:88:9: ( primaryExpression '.' ) ( type '.' )?
                    {
                    // src/LCPLTreeBuilder.g:88:9: ( primaryExpression '.' )
                    // src/LCPLTreeBuilder.g:88:10: primaryExpression '.'
                    {
                    pushFollow(FOLLOW_primaryExpression_in_dispatch838);
                    primaryExpression51=primaryExpression();

                    state._fsp--;

                    stream_primaryExpression.add(primaryExpression51.getTree());
                    char_literal52=(Token)match(input,67,FOLLOW_67_in_dispatch840);  
                    stream_67.add(char_literal52);


                    }

                    // src/LCPLTreeBuilder.g:88:33: ( type '.' )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( ((LA15_0>=INT && LA15_0<=STRING)) ) {
                        alt15=1;
                    }
                    else if ( (LA15_0==ID) ) {
                        int LA15_2 = input.LA(2);

                        if ( (LA15_2==67) ) {
                            alt15=1;
                        }
                    }
                    switch (alt15) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:88:34: type '.'
                            {
                            pushFollow(FOLLOW_type_in_dispatch844);
                            type53=type();

                            state._fsp--;

                            stream_type.add(type53.getTree());
                            char_literal54=(Token)match(input,67,FOLLOW_67_in_dispatch846);  
                            stream_67.add(char_literal54);


                            }
                            break;

                    }


                    }
                    break;

            }

            ID55=(Token)match(input,ID,FOLLOW_ID_in_dispatch852);  
            stream_ID.add(ID55);

            // src/LCPLTreeBuilder.g:88:50: ( dispatch_arguments )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0>=INT && LA17_0<=STRING)||LA17_0==NEW||(LA17_0>=ID && LA17_0<=VOID_CONSTANT)||(LA17_0>=IF && LA17_0<=WHILE)||LA17_0==SUB||(LA17_0>=NOT && LA17_0<=STRING_LITERAL)||LA17_0==66||LA17_0==73||LA17_0==75) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // src/LCPLTreeBuilder.g:88:50: dispatch_arguments
                    {
                    pushFollow(FOLLOW_dispatch_arguments_in_dispatch854);
                    dispatch_arguments56=dispatch_arguments();

                    state._fsp--;

                    stream_dispatch_arguments.add(dispatch_arguments56.getTree());

                    }
                    break;

            }

            char_literal57=(Token)match(input,68,FOLLOW_68_in_dispatch857);  
            stream_68.add(char_literal57);



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
            // 89:3: -> ^( DISPATCH ( primaryExpression )? ( type )? ID ( dispatch_arguments )? )
            {
                // src/LCPLTreeBuilder.g:89:6: ^( DISPATCH ( primaryExpression )? ( type )? ID ( dispatch_arguments )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DISPATCH, "DISPATCH"), root_1);

                // src/LCPLTreeBuilder.g:89:17: ( primaryExpression )?
                if ( stream_primaryExpression.hasNext() ) {
                    adaptor.addChild(root_1, stream_primaryExpression.nextTree());

                }
                stream_primaryExpression.reset();
                // src/LCPLTreeBuilder.g:89:36: ( type )?
                if ( stream_type.hasNext() ) {
                    adaptor.addChild(root_1, stream_type.nextTree());

                }
                stream_type.reset();
                adaptor.addChild(root_1, stream_ID.nextNode());
                // src/LCPLTreeBuilder.g:89:45: ( dispatch_arguments )?
                if ( stream_dispatch_arguments.hasNext() ) {
                    adaptor.addChild(root_1, stream_dispatch_arguments.nextTree());

                }
                stream_dispatch_arguments.reset();

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
    // $ANTLR end "dispatch"

    public static class dispatch_arguments_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dispatch_arguments"
    // src/LCPLTreeBuilder.g:92:1: dispatch_arguments : ar+= expression ( ',' ar+= expression )* -> ^( DISPATCH_ARGUMENTS ( $ar)+ ) ;
    public final LCPLTreeBuilderParser.dispatch_arguments_return dispatch_arguments() throws RecognitionException {
        LCPLTreeBuilderParser.dispatch_arguments_return retval = new LCPLTreeBuilderParser.dispatch_arguments_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal58=null;
        List list_ar=null;
        RuleReturnScope ar = null;
        CommonTree char_literal58_tree=null;
        RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // src/LCPLTreeBuilder.g:93:2: (ar+= expression ( ',' ar+= expression )* -> ^( DISPATCH_ARGUMENTS ( $ar)+ ) )
            // src/LCPLTreeBuilder.g:93:4: ar+= expression ( ',' ar+= expression )*
            {
            pushFollow(FOLLOW_expression_in_dispatch_arguments889);
            ar=expression();

            state._fsp--;

            stream_expression.add(ar.getTree());
            if (list_ar==null) list_ar=new ArrayList();
            list_ar.add(ar.getTree());

            // src/LCPLTreeBuilder.g:93:19: ( ',' ar+= expression )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==65) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:93:20: ',' ar+= expression
            	    {
            	    char_literal58=(Token)match(input,65,FOLLOW_65_in_dispatch_arguments892);  
            	    stream_65.add(char_literal58);

            	    pushFollow(FOLLOW_expression_in_dispatch_arguments896);
            	    ar=expression();

            	    state._fsp--;

            	    stream_expression.add(ar.getTree());
            	    if (list_ar==null) list_ar=new ArrayList();
            	    list_ar.add(ar.getTree());


            	    }
            	    break;

            	default :
            	    break loop18;
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
            // 93:41: -> ^( DISPATCH_ARGUMENTS ( $ar)+ )
            {
                // src/LCPLTreeBuilder.g:93:44: ^( DISPATCH_ARGUMENTS ( $ar)+ )
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
    // src/LCPLTreeBuilder.g:95:1: decl_section : 'var' ( attribute )+ 'end' ';' -> ^( DECL_SECTION ( attribute )+ ) ;
    public final LCPLTreeBuilderParser.decl_section_return decl_section() throws RecognitionException {
        LCPLTreeBuilderParser.decl_section_return retval = new LCPLTreeBuilderParser.decl_section_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal59=null;
        Token string_literal61=null;
        Token char_literal62=null;
        LCPLTreeBuilderParser.attribute_return attribute60 = null;


        CommonTree string_literal59_tree=null;
        CommonTree string_literal61_tree=null;
        CommonTree char_literal62_tree=null;
        RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
        RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
        RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
        RewriteRuleSubtreeStream stream_attribute=new RewriteRuleSubtreeStream(adaptor,"rule attribute");
        try {
            // src/LCPLTreeBuilder.g:96:2: ( 'var' ( attribute )+ 'end' ';' -> ^( DECL_SECTION ( attribute )+ ) )
            // src/LCPLTreeBuilder.g:96:4: 'var' ( attribute )+ 'end' ';'
            {
            string_literal59=(Token)match(input,69,FOLLOW_69_in_decl_section917);  
            stream_69.add(string_literal59);

            // src/LCPLTreeBuilder.g:96:10: ( attribute )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>=INT && LA19_0<=STRING)||LA19_0==ID) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:96:11: attribute
            	    {
            	    pushFollow(FOLLOW_attribute_in_decl_section920);
            	    attribute60=attribute();

            	    state._fsp--;

            	    stream_attribute.add(attribute60.getTree());

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

            string_literal61=(Token)match(input,END,FOLLOW_END_in_decl_section924);  
            stream_END.add(string_literal61);

            char_literal62=(Token)match(input,62,FOLLOW_62_in_decl_section926);  
            stream_62.add(char_literal62);



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
            // 96:33: -> ^( DECL_SECTION ( attribute )+ )
            {
                // src/LCPLTreeBuilder.g:96:36: ^( DECL_SECTION ( attribute )+ )
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
    // src/LCPLTreeBuilder.g:98:1: attribute : ( type ID ';' -> ^( ATTRIBUTE type ID ) | type ID EQ expression ';' -> ^( ATTRIBUTE type ID expression ) );
    public final LCPLTreeBuilderParser.attribute_return attribute() throws RecognitionException {
        LCPLTreeBuilderParser.attribute_return retval = new LCPLTreeBuilderParser.attribute_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ID64=null;
        Token char_literal65=null;
        Token ID67=null;
        Token EQ68=null;
        Token char_literal70=null;
        LCPLTreeBuilderParser.type_return type63 = null;

        LCPLTreeBuilderParser.type_return type66 = null;

        LCPLTreeBuilderParser.expression_return expression69 = null;


        CommonTree ID64_tree=null;
        CommonTree char_literal65_tree=null;
        CommonTree ID67_tree=null;
        CommonTree EQ68_tree=null;
        CommonTree char_literal70_tree=null;
        RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // src/LCPLTreeBuilder.g:99:2: ( type ID ';' -> ^( ATTRIBUTE type ID ) | type ID EQ expression ';' -> ^( ATTRIBUTE type ID expression ) )
            int alt20=2;
            switch ( input.LA(1) ) {
            case INT:
                {
                int LA20_1 = input.LA(2);

                if ( (LA20_1==ID) ) {
                    int LA20_4 = input.LA(3);

                    if ( (LA20_4==EQ) ) {
                        alt20=2;
                    }
                    else if ( (LA20_4==62) ) {
                        alt20=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 20, 4, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 1, input);

                    throw nvae;
                }
                }
                break;
            case STRING:
                {
                int LA20_2 = input.LA(2);

                if ( (LA20_2==ID) ) {
                    int LA20_4 = input.LA(3);

                    if ( (LA20_4==EQ) ) {
                        alt20=2;
                    }
                    else if ( (LA20_4==62) ) {
                        alt20=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 20, 4, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 2, input);

                    throw nvae;
                }
                }
                break;
            case ID:
                {
                int LA20_3 = input.LA(2);

                if ( (LA20_3==ID) ) {
                    int LA20_4 = input.LA(3);

                    if ( (LA20_4==EQ) ) {
                        alt20=2;
                    }
                    else if ( (LA20_4==62) ) {
                        alt20=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 20, 4, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // src/LCPLTreeBuilder.g:99:4: type ID ';'
                    {
                    pushFollow(FOLLOW_type_in_attribute947);
                    type63=type();

                    state._fsp--;

                    stream_type.add(type63.getTree());
                    ID64=(Token)match(input,ID,FOLLOW_ID_in_attribute949);  
                    stream_ID.add(ID64);

                    char_literal65=(Token)match(input,62,FOLLOW_62_in_attribute951);  
                    stream_62.add(char_literal65);



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
                    // 99:16: -> ^( ATTRIBUTE type ID )
                    {
                        // src/LCPLTreeBuilder.g:99:19: ^( ATTRIBUTE type ID )
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
                    // src/LCPLTreeBuilder.g:100:4: type ID EQ expression ';'
                    {
                    pushFollow(FOLLOW_type_in_attribute966);
                    type66=type();

                    state._fsp--;

                    stream_type.add(type66.getTree());
                    ID67=(Token)match(input,ID,FOLLOW_ID_in_attribute968);  
                    stream_ID.add(ID67);

                    EQ68=(Token)match(input,EQ,FOLLOW_EQ_in_attribute970);  
                    stream_EQ.add(EQ68);

                    pushFollow(FOLLOW_expression_in_attribute972);
                    expression69=expression();

                    state._fsp--;

                    stream_expression.add(expression69.getTree());
                    char_literal70=(Token)match(input,62,FOLLOW_62_in_attribute974);  
                    stream_62.add(char_literal70);



                    // AST REWRITE
                    // elements: ID, expression, type
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 100:30: -> ^( ATTRIBUTE type ID expression )
                    {
                        // src/LCPLTreeBuilder.g:100:33: ^( ATTRIBUTE type ID expression )
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
    // src/LCPLTreeBuilder.g:102:1: statement : ( IF cond= expression 'then' ifBlock= block ( 'else' elseBlock= block )? END -> ^( IF $cond $ifBlock ( $elseBlock)? ) | WHILE expression 'loop' block END -> ^( WHILE expression block ) );
    public final LCPLTreeBuilderParser.statement_return statement() throws RecognitionException {
        LCPLTreeBuilderParser.statement_return retval = new LCPLTreeBuilderParser.statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IF71=null;
        Token string_literal72=null;
        Token string_literal73=null;
        Token END74=null;
        Token WHILE75=null;
        Token string_literal77=null;
        Token END79=null;
        LCPLTreeBuilderParser.expression_return cond = null;

        LCPLTreeBuilderParser.block_return ifBlock = null;

        LCPLTreeBuilderParser.block_return elseBlock = null;

        LCPLTreeBuilderParser.expression_return expression76 = null;

        LCPLTreeBuilderParser.block_return block78 = null;


        CommonTree IF71_tree=null;
        CommonTree string_literal72_tree=null;
        CommonTree string_literal73_tree=null;
        CommonTree END74_tree=null;
        CommonTree WHILE75_tree=null;
        CommonTree string_literal77_tree=null;
        CommonTree END79_tree=null;
        RewriteRuleTokenStream stream_WHILE=new RewriteRuleTokenStream(adaptor,"token WHILE");
        RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
        RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // src/LCPLTreeBuilder.g:103:2: ( IF cond= expression 'then' ifBlock= block ( 'else' elseBlock= block )? END -> ^( IF $cond $ifBlock ( $elseBlock)? ) | WHILE expression 'loop' block END -> ^( WHILE expression block ) )
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
                    // src/LCPLTreeBuilder.g:103:4: IF cond= expression 'then' ifBlock= block ( 'else' elseBlock= block )? END
                    {
                    IF71=(Token)match(input,IF,FOLLOW_IF_in_statement996);  
                    stream_IF.add(IF71);

                    pushFollow(FOLLOW_expression_in_statement1002);
                    cond=expression();

                    state._fsp--;

                    stream_expression.add(cond.getTree());
                    string_literal72=(Token)match(input,70,FOLLOW_70_in_statement1004);  
                    stream_70.add(string_literal72);

                    pushFollow(FOLLOW_block_in_statement1008);
                    ifBlock=block();

                    state._fsp--;

                    stream_block.add(ifBlock.getTree());
                    // src/LCPLTreeBuilder.g:103:46: ( 'else' elseBlock= block )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==71) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:103:47: 'else' elseBlock= block
                            {
                            string_literal73=(Token)match(input,71,FOLLOW_71_in_statement1011);  
                            stream_71.add(string_literal73);

                            pushFollow(FOLLOW_block_in_statement1017);
                            elseBlock=block();

                            state._fsp--;

                            stream_block.add(elseBlock.getTree());

                            }
                            break;

                    }

                    END74=(Token)match(input,END,FOLLOW_END_in_statement1021);  
                    stream_END.add(END74);



                    // AST REWRITE
                    // elements: IF, elseBlock, ifBlock, cond
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
                    // 104:3: -> ^( IF $cond $ifBlock ( $elseBlock)? )
                    {
                        // src/LCPLTreeBuilder.g:104:6: ^( IF $cond $ifBlock ( $elseBlock)? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_IF.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_cond.nextTree());
                        adaptor.addChild(root_1, stream_ifBlock.nextTree());
                        // src/LCPLTreeBuilder.g:104:26: ( $elseBlock)?
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
                    // src/LCPLTreeBuilder.g:105:4: WHILE expression 'loop' block END
                    {
                    WHILE75=(Token)match(input,WHILE,FOLLOW_WHILE_in_statement1045);  
                    stream_WHILE.add(WHILE75);

                    pushFollow(FOLLOW_expression_in_statement1047);
                    expression76=expression();

                    state._fsp--;

                    stream_expression.add(expression76.getTree());
                    string_literal77=(Token)match(input,72,FOLLOW_72_in_statement1049);  
                    stream_72.add(string_literal77);

                    pushFollow(FOLLOW_block_in_statement1051);
                    block78=block();

                    state._fsp--;

                    stream_block.add(block78.getTree());
                    END79=(Token)match(input,END,FOLLOW_END_in_statement1053);  
                    stream_END.add(END79);



                    // AST REWRITE
                    // elements: WHILE, expression, block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 105:38: -> ^( WHILE expression block )
                    {
                        // src/LCPLTreeBuilder.g:105:41: ^( WHILE expression block )
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
    // src/LCPLTreeBuilder.g:107:1: cast : '{' type expression '}' -> ^( CAST type expression ) ;
    public final LCPLTreeBuilderParser.cast_return cast() throws RecognitionException {
        LCPLTreeBuilderParser.cast_return retval = new LCPLTreeBuilderParser.cast_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal80=null;
        Token char_literal83=null;
        LCPLTreeBuilderParser.type_return type81 = null;

        LCPLTreeBuilderParser.expression_return expression82 = null;


        CommonTree char_literal80_tree=null;
        CommonTree char_literal83_tree=null;
        RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // src/LCPLTreeBuilder.g:107:6: ( '{' type expression '}' -> ^( CAST type expression ) )
            // src/LCPLTreeBuilder.g:107:8: '{' type expression '}'
            {
            char_literal80=(Token)match(input,73,FOLLOW_73_in_cast1071);  
            stream_73.add(char_literal80);

            pushFollow(FOLLOW_type_in_cast1072);
            type81=type();

            state._fsp--;

            stream_type.add(type81.getTree());
            pushFollow(FOLLOW_expression_in_cast1074);
            expression82=expression();

            state._fsp--;

            stream_expression.add(expression82.getTree());
            char_literal83=(Token)match(input,74,FOLLOW_74_in_cast1076);  
            stream_74.add(char_literal83);



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
            // 107:31: -> ^( CAST type expression )
            {
                // src/LCPLTreeBuilder.g:107:34: ^( CAST type expression )
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
    // src/LCPLTreeBuilder.g:108:1: expression : assignmentExpression -> ^( EXPRESSION assignmentExpression ) ;
    public final LCPLTreeBuilderParser.expression_return expression() throws RecognitionException {
        LCPLTreeBuilderParser.expression_return retval = new LCPLTreeBuilderParser.expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        LCPLTreeBuilderParser.assignmentExpression_return assignmentExpression84 = null;


        RewriteRuleSubtreeStream stream_assignmentExpression=new RewriteRuleSubtreeStream(adaptor,"rule assignmentExpression");
        try {
            // src/LCPLTreeBuilder.g:109:2: ( assignmentExpression -> ^( EXPRESSION assignmentExpression ) )
            // src/LCPLTreeBuilder.g:109:4: assignmentExpression
            {
            pushFollow(FOLLOW_assignmentExpression_in_expression1094);
            assignmentExpression84=assignmentExpression();

            state._fsp--;

            stream_assignmentExpression.add(assignmentExpression84.getTree());


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
            // 109:25: -> ^( EXPRESSION assignmentExpression )
            {
                // src/LCPLTreeBuilder.g:109:28: ^( EXPRESSION assignmentExpression )
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
    // src/LCPLTreeBuilder.g:111:1: assignmentExpression : equalityExpression ( EQ assignmentExpression )? ;
    public final LCPLTreeBuilderParser.assignmentExpression_return assignmentExpression() throws RecognitionException {
        LCPLTreeBuilderParser.assignmentExpression_return retval = new LCPLTreeBuilderParser.assignmentExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EQ86=null;
        LCPLTreeBuilderParser.equalityExpression_return equalityExpression85 = null;

        LCPLTreeBuilderParser.assignmentExpression_return assignmentExpression87 = null;


        CommonTree EQ86_tree=null;

        try {
            // src/LCPLTreeBuilder.g:112:2: ( equalityExpression ( EQ assignmentExpression )? )
            // src/LCPLTreeBuilder.g:112:4: equalityExpression ( EQ assignmentExpression )?
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_equalityExpression_in_assignmentExpression1112);
            equalityExpression85=equalityExpression();

            state._fsp--;

            adaptor.addChild(root_0, equalityExpression85.getTree());
            // src/LCPLTreeBuilder.g:112:23: ( EQ assignmentExpression )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==EQ) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // src/LCPLTreeBuilder.g:112:24: EQ assignmentExpression
                    {
                    EQ86=(Token)match(input,EQ,FOLLOW_EQ_in_assignmentExpression1115); 
                    EQ86_tree = (CommonTree)adaptor.create(EQ86);
                    root_0 = (CommonTree)adaptor.becomeRoot(EQ86_tree, root_0);

                    pushFollow(FOLLOW_assignmentExpression_in_assignmentExpression1118);
                    assignmentExpression87=assignmentExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, assignmentExpression87.getTree());

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
    // $ANTLR end "assignmentExpression"

    public static class equalityExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "equalityExpression"
    // src/LCPLTreeBuilder.g:115:1: equalityExpression : relationalExpression ( EQUAL relationalExpression )* ;
    public final LCPLTreeBuilderParser.equalityExpression_return equalityExpression() throws RecognitionException {
        LCPLTreeBuilderParser.equalityExpression_return retval = new LCPLTreeBuilderParser.equalityExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EQUAL89=null;
        LCPLTreeBuilderParser.relationalExpression_return relationalExpression88 = null;

        LCPLTreeBuilderParser.relationalExpression_return relationalExpression90 = null;


        CommonTree EQUAL89_tree=null;

        try {
            // src/LCPLTreeBuilder.g:116:2: ( relationalExpression ( EQUAL relationalExpression )* )
            // src/LCPLTreeBuilder.g:116:4: relationalExpression ( EQUAL relationalExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_relationalExpression_in_equalityExpression1132);
            relationalExpression88=relationalExpression();

            state._fsp--;

            adaptor.addChild(root_0, relationalExpression88.getTree());
            // src/LCPLTreeBuilder.g:116:25: ( EQUAL relationalExpression )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==EQUAL) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:116:26: EQUAL relationalExpression
            	    {
            	    EQUAL89=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_equalityExpression1135); 
            	    EQUAL89_tree = (CommonTree)adaptor.create(EQUAL89);
            	    root_0 = (CommonTree)adaptor.becomeRoot(EQUAL89_tree, root_0);

            	    pushFollow(FOLLOW_relationalExpression_in_equalityExpression1138);
            	    relationalExpression90=relationalExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, relationalExpression90.getTree());

            	    }
            	    break;

            	default :
            	    break loop24;
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
    // src/LCPLTreeBuilder.g:118:1: relationalExpression : additiveExpression ( ( LTEQ | LT ) additiveExpression )* ;
    public final LCPLTreeBuilderParser.relationalExpression_return relationalExpression() throws RecognitionException {
        LCPLTreeBuilderParser.relationalExpression_return retval = new LCPLTreeBuilderParser.relationalExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LTEQ92=null;
        Token LT93=null;
        LCPLTreeBuilderParser.additiveExpression_return additiveExpression91 = null;

        LCPLTreeBuilderParser.additiveExpression_return additiveExpression94 = null;


        CommonTree LTEQ92_tree=null;
        CommonTree LT93_tree=null;

        try {
            // src/LCPLTreeBuilder.g:119:2: ( additiveExpression ( ( LTEQ | LT ) additiveExpression )* )
            // src/LCPLTreeBuilder.g:119:4: additiveExpression ( ( LTEQ | LT ) additiveExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_additiveExpression_in_relationalExpression1150);
            additiveExpression91=additiveExpression();

            state._fsp--;

            adaptor.addChild(root_0, additiveExpression91.getTree());
            // src/LCPLTreeBuilder.g:119:23: ( ( LTEQ | LT ) additiveExpression )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=LTEQ && LA26_0<=LT)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:119:24: ( LTEQ | LT ) additiveExpression
            	    {
            	    // src/LCPLTreeBuilder.g:119:24: ( LTEQ | LT )
            	    int alt25=2;
            	    int LA25_0 = input.LA(1);

            	    if ( (LA25_0==LTEQ) ) {
            	        alt25=1;
            	    }
            	    else if ( (LA25_0==LT) ) {
            	        alt25=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 25, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt25) {
            	        case 1 :
            	            // src/LCPLTreeBuilder.g:119:25: LTEQ
            	            {
            	            LTEQ92=(Token)match(input,LTEQ,FOLLOW_LTEQ_in_relationalExpression1154); 
            	            LTEQ92_tree = (CommonTree)adaptor.create(LTEQ92);
            	            root_0 = (CommonTree)adaptor.becomeRoot(LTEQ92_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // src/LCPLTreeBuilder.g:119:33: LT
            	            {
            	            LT93=(Token)match(input,LT,FOLLOW_LT_in_relationalExpression1159); 
            	            LT93_tree = (CommonTree)adaptor.create(LT93);
            	            root_0 = (CommonTree)adaptor.becomeRoot(LT93_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_additiveExpression_in_relationalExpression1164);
            	    additiveExpression94=additiveExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, additiveExpression94.getTree());

            	    }
            	    break;

            	default :
            	    break loop26;
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
    // src/LCPLTreeBuilder.g:121:1: additiveExpression : multiplicativeExpression ( ( PLUS | SUB ) multiplicativeExpression )* ;
    public final LCPLTreeBuilderParser.additiveExpression_return additiveExpression() throws RecognitionException {
        LCPLTreeBuilderParser.additiveExpression_return retval = new LCPLTreeBuilderParser.additiveExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token PLUS96=null;
        Token SUB97=null;
        LCPLTreeBuilderParser.multiplicativeExpression_return multiplicativeExpression95 = null;

        LCPLTreeBuilderParser.multiplicativeExpression_return multiplicativeExpression98 = null;


        CommonTree PLUS96_tree=null;
        CommonTree SUB97_tree=null;

        try {
            // src/LCPLTreeBuilder.g:122:2: ( multiplicativeExpression ( ( PLUS | SUB ) multiplicativeExpression )* )
            // src/LCPLTreeBuilder.g:122:4: multiplicativeExpression ( ( PLUS | SUB ) multiplicativeExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression1175);
            multiplicativeExpression95=multiplicativeExpression();

            state._fsp--;

            adaptor.addChild(root_0, multiplicativeExpression95.getTree());
            // src/LCPLTreeBuilder.g:123:3: ( ( PLUS | SUB ) multiplicativeExpression )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( ((LA28_0>=PLUS && LA28_0<=SUB)) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // src/LCPLTreeBuilder.g:123:4: ( PLUS | SUB ) multiplicativeExpression
            	    {
            	    // src/LCPLTreeBuilder.g:123:4: ( PLUS | SUB )
            	    int alt27=2;
            	    int LA27_0 = input.LA(1);

            	    if ( (LA27_0==PLUS) ) {
            	        alt27=1;
            	    }
            	    else if ( (LA27_0==SUB) ) {
            	        alt27=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 27, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt27) {
            	        case 1 :
            	            // src/LCPLTreeBuilder.g:123:5: PLUS
            	            {
            	            PLUS96=(Token)match(input,PLUS,FOLLOW_PLUS_in_additiveExpression1181); 
            	            PLUS96_tree = (CommonTree)adaptor.create(PLUS96);
            	            root_0 = (CommonTree)adaptor.becomeRoot(PLUS96_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // src/LCPLTreeBuilder.g:123:13: SUB
            	            {
            	            SUB97=(Token)match(input,SUB,FOLLOW_SUB_in_additiveExpression1186); 
            	            SUB97_tree = (CommonTree)adaptor.create(SUB97);
            	            root_0 = (CommonTree)adaptor.becomeRoot(SUB97_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression1191);
            	    multiplicativeExpression98=multiplicativeExpression();

            	    state._fsp--;

            	    adaptor.addChild(root_0, multiplicativeExpression98.getTree());

            	    }
            	    break;

            	default :
            	    break loop28;
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
    // src/LCPLTreeBuilder.g:125:1: multiplicativeExpression : unaryExpression ( ( STAR | DIV ) unaryExpression )? ;
    public final LCPLTreeBuilderParser.multiplicativeExpression_return multiplicativeExpression() throws RecognitionException {
        LCPLTreeBuilderParser.multiplicativeExpression_return retval = new LCPLTreeBuilderParser.multiplicativeExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token STAR100=null;
        Token DIV101=null;
        LCPLTreeBuilderParser.unaryExpression_return unaryExpression99 = null;

        LCPLTreeBuilderParser.unaryExpression_return unaryExpression102 = null;


        CommonTree STAR100_tree=null;
        CommonTree DIV101_tree=null;

        try {
            // src/LCPLTreeBuilder.g:126:2: ( unaryExpression ( ( STAR | DIV ) unaryExpression )? )
            // src/LCPLTreeBuilder.g:126:4: unaryExpression ( ( STAR | DIV ) unaryExpression )?
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression1204);
            unaryExpression99=unaryExpression();

            state._fsp--;

            adaptor.addChild(root_0, unaryExpression99.getTree());
            // src/LCPLTreeBuilder.g:127:3: ( ( STAR | DIV ) unaryExpression )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( ((LA30_0>=STAR && LA30_0<=DIV)) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // src/LCPLTreeBuilder.g:127:4: ( STAR | DIV ) unaryExpression
                    {
                    // src/LCPLTreeBuilder.g:127:4: ( STAR | DIV )
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==STAR) ) {
                        alt29=1;
                    }
                    else if ( (LA29_0==DIV) ) {
                        alt29=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 29, 0, input);

                        throw nvae;
                    }
                    switch (alt29) {
                        case 1 :
                            // src/LCPLTreeBuilder.g:127:5: STAR
                            {
                            STAR100=(Token)match(input,STAR,FOLLOW_STAR_in_multiplicativeExpression1210); 
                            STAR100_tree = (CommonTree)adaptor.create(STAR100);
                            root_0 = (CommonTree)adaptor.becomeRoot(STAR100_tree, root_0);


                            }
                            break;
                        case 2 :
                            // src/LCPLTreeBuilder.g:127:13: DIV
                            {
                            DIV101=(Token)match(input,DIV,FOLLOW_DIV_in_multiplicativeExpression1215); 
                            DIV101_tree = (CommonTree)adaptor.create(DIV101);
                            root_0 = (CommonTree)adaptor.becomeRoot(DIV101_tree, root_0);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression1221);
                    unaryExpression102=unaryExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, unaryExpression102.getTree());

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
    // $ANTLR end "multiplicativeExpression"

    public static class unaryExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "unaryExpression"
    // src/LCPLTreeBuilder.g:129:1: unaryExpression : ( SUB unaryExpression -> ^( UNARY_MINUS unaryExpression ) | simpleUnaryExpression );
    public final LCPLTreeBuilderParser.unaryExpression_return unaryExpression() throws RecognitionException {
        LCPLTreeBuilderParser.unaryExpression_return retval = new LCPLTreeBuilderParser.unaryExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SUB103=null;
        LCPLTreeBuilderParser.unaryExpression_return unaryExpression104 = null;

        LCPLTreeBuilderParser.simpleUnaryExpression_return simpleUnaryExpression105 = null;


        CommonTree SUB103_tree=null;
        RewriteRuleTokenStream stream_SUB=new RewriteRuleTokenStream(adaptor,"token SUB");
        RewriteRuleSubtreeStream stream_unaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule unaryExpression");
        try {
            // src/LCPLTreeBuilder.g:130:2: ( SUB unaryExpression -> ^( UNARY_MINUS unaryExpression ) | simpleUnaryExpression )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==SUB) ) {
                alt31=1;
            }
            else if ( ((LA31_0>=INT && LA31_0<=STRING)||LA31_0==NEW||(LA31_0>=ID && LA31_0<=VOID_CONSTANT)||(LA31_0>=IF && LA31_0<=WHILE)||(LA31_0>=NOT && LA31_0<=STRING_LITERAL)||LA31_0==66||LA31_0==73||LA31_0==75) ) {
                alt31=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // src/LCPLTreeBuilder.g:130:4: SUB unaryExpression
                    {
                    SUB103=(Token)match(input,SUB,FOLLOW_SUB_in_unaryExpression1234);  
                    stream_SUB.add(SUB103);

                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression1236);
                    unaryExpression104=unaryExpression();

                    state._fsp--;

                    stream_unaryExpression.add(unaryExpression104.getTree());


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
                    // 130:24: -> ^( UNARY_MINUS unaryExpression )
                    {
                        // src/LCPLTreeBuilder.g:130:27: ^( UNARY_MINUS unaryExpression )
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
                    // src/LCPLTreeBuilder.g:131:4: simpleUnaryExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_simpleUnaryExpression_in_unaryExpression1249);
                    simpleUnaryExpression105=simpleUnaryExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, simpleUnaryExpression105.getTree());

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
    // src/LCPLTreeBuilder.g:133:1: simpleUnaryExpression : ( NOT unaryExpression | (e= primaryExpression -> $e) ( '[' a= expression ',' b= expression ']' -> ^( SUBSTR $simpleUnaryExpression $a $b) )* );
    public final LCPLTreeBuilderParser.simpleUnaryExpression_return simpleUnaryExpression() throws RecognitionException {
        LCPLTreeBuilderParser.simpleUnaryExpression_return retval = new LCPLTreeBuilderParser.simpleUnaryExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NOT106=null;
        Token char_literal108=null;
        Token char_literal109=null;
        Token char_literal110=null;
        LCPLTreeBuilderParser.primaryExpression_return e = null;

        LCPLTreeBuilderParser.expression_return a = null;

        LCPLTreeBuilderParser.expression_return b = null;

        LCPLTreeBuilderParser.unaryExpression_return unaryExpression107 = null;


        CommonTree NOT106_tree=null;
        CommonTree char_literal108_tree=null;
        CommonTree char_literal109_tree=null;
        CommonTree char_literal110_tree=null;
        RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_primaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule primaryExpression");
        try {
            // src/LCPLTreeBuilder.g:134:2: ( NOT unaryExpression | (e= primaryExpression -> $e) ( '[' a= expression ',' b= expression ']' -> ^( SUBSTR $simpleUnaryExpression $a $b) )* )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==NOT) ) {
                alt33=1;
            }
            else if ( ((LA33_0>=INT && LA33_0<=STRING)||LA33_0==NEW||(LA33_0>=ID && LA33_0<=VOID_CONSTANT)||(LA33_0>=IF && LA33_0<=WHILE)||(LA33_0>=INTEGER && LA33_0<=STRING_LITERAL)||LA33_0==66||LA33_0==73||LA33_0==75) ) {
                alt33=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // src/LCPLTreeBuilder.g:134:4: NOT unaryExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    NOT106=(Token)match(input,NOT,FOLLOW_NOT_in_simpleUnaryExpression1259); 
                    NOT106_tree = (CommonTree)adaptor.create(NOT106);
                    root_0 = (CommonTree)adaptor.becomeRoot(NOT106_tree, root_0);

                    pushFollow(FOLLOW_unaryExpression_in_simpleUnaryExpression1262);
                    unaryExpression107=unaryExpression();

                    state._fsp--;

                    adaptor.addChild(root_0, unaryExpression107.getTree());

                    }
                    break;
                case 2 :
                    // src/LCPLTreeBuilder.g:135:4: (e= primaryExpression -> $e) ( '[' a= expression ',' b= expression ']' -> ^( SUBSTR $simpleUnaryExpression $a $b) )*
                    {
                    // src/LCPLTreeBuilder.g:135:4: (e= primaryExpression -> $e)
                    // src/LCPLTreeBuilder.g:135:5: e= primaryExpression
                    {
                    pushFollow(FOLLOW_primaryExpression_in_simpleUnaryExpression1272);
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
                    // 135:27: -> $e
                    {
                        adaptor.addChild(root_0, stream_e.nextTree());

                    }

                    retval.tree = root_0;
                    }

                    // src/LCPLTreeBuilder.g:136:2: ( '[' a= expression ',' b= expression ']' -> ^( SUBSTR $simpleUnaryExpression $a $b) )*
                    loop32:
                    do {
                        int alt32=2;
                        int LA32_0 = input.LA(1);

                        if ( (LA32_0==66) ) {
                            alt32=1;
                        }


                        switch (alt32) {
                    	case 1 :
                    	    // src/LCPLTreeBuilder.g:136:3: '[' a= expression ',' b= expression ']'
                    	    {
                    	    char_literal108=(Token)match(input,66,FOLLOW_66_in_simpleUnaryExpression1282);  
                    	    stream_66.add(char_literal108);

                    	    pushFollow(FOLLOW_expression_in_simpleUnaryExpression1288);
                    	    a=expression();

                    	    state._fsp--;

                    	    stream_expression.add(a.getTree());
                    	    char_literal109=(Token)match(input,65,FOLLOW_65_in_simpleUnaryExpression1290);  
                    	    stream_65.add(char_literal109);

                    	    pushFollow(FOLLOW_expression_in_simpleUnaryExpression1296);
                    	    b=expression();

                    	    state._fsp--;

                    	    stream_expression.add(b.getTree());
                    	    char_literal110=(Token)match(input,68,FOLLOW_68_in_simpleUnaryExpression1298);  
                    	    stream_68.add(char_literal110);



                    	    // AST REWRITE
                    	    // elements: b, a, simpleUnaryExpression
                    	    // token labels: 
                    	    // rule labels: retval, b, a
                    	    // token list labels: 
                    	    // rule list labels: 
                    	    // wildcard labels: 
                    	    retval.tree = root_0;
                    	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    	    RewriteRuleSubtreeStream stream_b=new RewriteRuleSubtreeStream(adaptor,"rule b",b!=null?b.tree:null);
                    	    RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"rule a",a!=null?a.tree:null);

                    	    root_0 = (CommonTree)adaptor.nil();
                    	    // 136:45: -> ^( SUBSTR $simpleUnaryExpression $a $b)
                    	    {
                    	        // src/LCPLTreeBuilder.g:136:48: ^( SUBSTR $simpleUnaryExpression $a $b)
                    	        {
                    	        CommonTree root_1 = (CommonTree)adaptor.nil();
                    	        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SUBSTR, "SUBSTR"), root_1);

                    	        adaptor.addChild(root_1, stream_retval.nextTree());
                    	        adaptor.addChild(root_1, stream_a.nextTree());
                    	        adaptor.addChild(root_1, stream_b.nextTree());

                    	        adaptor.addChild(root_0, root_1);
                    	        }

                    	    }

                    	    retval.tree = root_0;
                    	    }
                    	    break;

                    	default :
                    	    break loop32;
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
    // src/LCPLTreeBuilder.g:138:1: primaryExpression : ( '(' expression ')' -> ^( PAR_EXPR expression ) | dispatch | new_object | cast | statement | literal );
    public final LCPLTreeBuilderParser.primaryExpression_return primaryExpression() throws RecognitionException {
        LCPLTreeBuilderParser.primaryExpression_return retval = new LCPLTreeBuilderParser.primaryExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal111=null;
        Token char_literal113=null;
        LCPLTreeBuilderParser.expression_return expression112 = null;

        LCPLTreeBuilderParser.dispatch_return dispatch114 = null;

        LCPLTreeBuilderParser.new_object_return new_object115 = null;

        LCPLTreeBuilderParser.cast_return cast116 = null;

        LCPLTreeBuilderParser.statement_return statement117 = null;

        LCPLTreeBuilderParser.literal_return literal118 = null;


        CommonTree char_literal111_tree=null;
        CommonTree char_literal113_tree=null;
        RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
        RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // src/LCPLTreeBuilder.g:139:2: ( '(' expression ')' -> ^( PAR_EXPR expression ) | dispatch | new_object | cast | statement | literal )
            int alt34=6;
            switch ( input.LA(1) ) {
            case 75:
                {
                alt34=1;
                }
                break;
            case 66:
                {
                alt34=2;
                }
                break;
            case NEW:
                {
                alt34=3;
                }
                break;
            case 73:
                {
                alt34=4;
                }
                break;
            case IF:
            case WHILE:
                {
                alt34=5;
                }
                break;
            case INT:
            case STRING:
            case ID:
            case VOID_CONSTANT:
            case INTEGER:
            case STRING_LITERAL:
                {
                alt34=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // src/LCPLTreeBuilder.g:139:4: '(' expression ')'
                    {
                    char_literal111=(Token)match(input,75,FOLLOW_75_in_primaryExpression1325);  
                    stream_75.add(char_literal111);

                    pushFollow(FOLLOW_expression_in_primaryExpression1327);
                    expression112=expression();

                    state._fsp--;

                    stream_expression.add(expression112.getTree());
                    char_literal113=(Token)match(input,76,FOLLOW_76_in_primaryExpression1329);  
                    stream_76.add(char_literal113);



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
                    // 139:23: -> ^( PAR_EXPR expression )
                    {
                        // src/LCPLTreeBuilder.g:139:26: ^( PAR_EXPR expression )
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
                    // src/LCPLTreeBuilder.g:140:4: dispatch
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_dispatch_in_primaryExpression1342);
                    dispatch114=dispatch();

                    state._fsp--;

                    adaptor.addChild(root_0, dispatch114.getTree());

                    }
                    break;
                case 3 :
                    // src/LCPLTreeBuilder.g:141:4: new_object
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_new_object_in_primaryExpression1347);
                    new_object115=new_object();

                    state._fsp--;

                    adaptor.addChild(root_0, new_object115.getTree());

                    }
                    break;
                case 4 :
                    // src/LCPLTreeBuilder.g:142:4: cast
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_cast_in_primaryExpression1352);
                    cast116=cast();

                    state._fsp--;

                    adaptor.addChild(root_0, cast116.getTree());

                    }
                    break;
                case 5 :
                    // src/LCPLTreeBuilder.g:143:4: statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_statement_in_primaryExpression1357);
                    statement117=statement();

                    state._fsp--;

                    adaptor.addChild(root_0, statement117.getTree());

                    }
                    break;
                case 6 :
                    // src/LCPLTreeBuilder.g:144:4: literal
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_literal_in_primaryExpression1362);
                    literal118=literal();

                    state._fsp--;

                    adaptor.addChild(root_0, literal118.getTree());

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
    // src/LCPLTreeBuilder.g:146:1: literal : ( integer | string | type -> ^( SYMBOL type ) | VOID_CONSTANT );
    public final LCPLTreeBuilderParser.literal_return literal() throws RecognitionException {
        LCPLTreeBuilderParser.literal_return retval = new LCPLTreeBuilderParser.literal_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token VOID_CONSTANT122=null;
        LCPLTreeBuilderParser.integer_return integer119 = null;

        LCPLTreeBuilderParser.string_return string120 = null;

        LCPLTreeBuilderParser.type_return type121 = null;


        CommonTree VOID_CONSTANT122_tree=null;
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // src/LCPLTreeBuilder.g:146:9: ( integer | string | type -> ^( SYMBOL type ) | VOID_CONSTANT )
            int alt35=4;
            switch ( input.LA(1) ) {
            case INTEGER:
                {
                alt35=1;
                }
                break;
            case STRING_LITERAL:
                {
                alt35=2;
                }
                break;
            case INT:
            case STRING:
            case ID:
                {
                alt35=3;
                }
                break;
            case VOID_CONSTANT:
                {
                alt35=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // src/LCPLTreeBuilder.g:146:11: integer
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_integer_in_literal1370);
                    integer119=integer();

                    state._fsp--;

                    adaptor.addChild(root_0, integer119.getTree());

                    }
                    break;
                case 2 :
                    // src/LCPLTreeBuilder.g:147:4: string
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_string_in_literal1375);
                    string120=string();

                    state._fsp--;

                    adaptor.addChild(root_0, string120.getTree());

                    }
                    break;
                case 3 :
                    // src/LCPLTreeBuilder.g:148:4: type
                    {
                    pushFollow(FOLLOW_type_in_literal1380);
                    type121=type();

                    state._fsp--;

                    stream_type.add(type121.getTree());


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
                    // 148:9: -> ^( SYMBOL type )
                    {
                        // src/LCPLTreeBuilder.g:148:12: ^( SYMBOL type )
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
                    // src/LCPLTreeBuilder.g:149:4: VOID_CONSTANT
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    VOID_CONSTANT122=(Token)match(input,VOID_CONSTANT,FOLLOW_VOID_CONSTANT_in_literal1393); 
                    VOID_CONSTANT122_tree = (CommonTree)adaptor.create(VOID_CONSTANT122);
                    adaptor.addChild(root_0, VOID_CONSTANT122_tree);


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
    // src/LCPLTreeBuilder.g:151:1: integer : value= INTEGER -> ^( CONSTANT_INT $value) ;
    public final LCPLTreeBuilderParser.integer_return integer() throws RecognitionException {
        LCPLTreeBuilderParser.integer_return retval = new LCPLTreeBuilderParser.integer_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token value=null;

        CommonTree value_tree=null;
        RewriteRuleTokenStream stream_INTEGER=new RewriteRuleTokenStream(adaptor,"token INTEGER");

        try {
            // src/LCPLTreeBuilder.g:151:9: (value= INTEGER -> ^( CONSTANT_INT $value) )
            // src/LCPLTreeBuilder.g:151:11: value= INTEGER
            {
            value=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_integer1403);  
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
            // 151:25: -> ^( CONSTANT_INT $value)
            {
                // src/LCPLTreeBuilder.g:151:28: ^( CONSTANT_INT $value)
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
    // src/LCPLTreeBuilder.g:152:1: string : STRING_LITERAL -> ^( CONSTANT_STRING STRING_LITERAL ) ;
    public final LCPLTreeBuilderParser.string_return string() throws RecognitionException {
        LCPLTreeBuilderParser.string_return retval = new LCPLTreeBuilderParser.string_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token STRING_LITERAL123=null;

        CommonTree STRING_LITERAL123_tree=null;
        RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");

        try {
            // src/LCPLTreeBuilder.g:152:8: ( STRING_LITERAL -> ^( CONSTANT_STRING STRING_LITERAL ) )
            // src/LCPLTreeBuilder.g:152:10: STRING_LITERAL
            {
            STRING_LITERAL123=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_string1419);  
            stream_STRING_LITERAL.add(STRING_LITERAL123);



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
            // 152:26: -> ^( CONSTANT_STRING STRING_LITERAL )
            {
                // src/LCPLTreeBuilder.g:152:29: ^( CONSTANT_STRING STRING_LITERAL )
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
    // src/LCPLTreeBuilder.g:153:1: new_object : NEW type -> ^( NEW type ) ;
    public final LCPLTreeBuilderParser.new_object_return new_object() throws RecognitionException {
        LCPLTreeBuilderParser.new_object_return retval = new LCPLTreeBuilderParser.new_object_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NEW124=null;
        LCPLTreeBuilderParser.type_return type125 = null;


        CommonTree NEW124_tree=null;
        RewriteRuleTokenStream stream_NEW=new RewriteRuleTokenStream(adaptor,"token NEW");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // src/LCPLTreeBuilder.g:154:2: ( NEW type -> ^( NEW type ) )
            // src/LCPLTreeBuilder.g:154:4: NEW type
            {
            NEW124=(Token)match(input,NEW,FOLLOW_NEW_in_new_object1436);  
            stream_NEW.add(NEW124);

            pushFollow(FOLLOW_type_in_new_object1438);
            type125=type();

            state._fsp--;

            stream_type.add(type125.getTree());


            // AST REWRITE
            // elements: type, NEW
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 154:13: -> ^( NEW type )
            {
                // src/LCPLTreeBuilder.g:154:16: ^( NEW type )
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
    // src/LCPLTreeBuilder.g:158:1: type : ( INT -> ^( TYPE INT ) | STRING -> ^( TYPE STRING ) | ID -> ^( TYPE ID ) );
    public final LCPLTreeBuilderParser.type_return type() throws RecognitionException {
        LCPLTreeBuilderParser.type_return retval = new LCPLTreeBuilderParser.type_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token INT126=null;
        Token STRING127=null;
        Token ID128=null;

        CommonTree INT126_tree=null;
        CommonTree STRING127_tree=null;
        CommonTree ID128_tree=null;
        RewriteRuleTokenStream stream_INT=new RewriteRuleTokenStream(adaptor,"token INT");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");

        try {
            // src/LCPLTreeBuilder.g:158:6: ( INT -> ^( TYPE INT ) | STRING -> ^( TYPE STRING ) | ID -> ^( TYPE ID ) )
            int alt36=3;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt36=1;
                }
                break;
            case STRING:
                {
                alt36=2;
                }
                break;
            case ID:
                {
                alt36=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }

            switch (alt36) {
                case 1 :
                    // src/LCPLTreeBuilder.g:158:8: INT
                    {
                    INT126=(Token)match(input,INT,FOLLOW_INT_in_type1457);  
                    stream_INT.add(INT126);



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
                    // 158:12: -> ^( TYPE INT )
                    {
                        // src/LCPLTreeBuilder.g:158:15: ^( TYPE INT )
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
                    // src/LCPLTreeBuilder.g:159:4: STRING
                    {
                    STRING127=(Token)match(input,STRING,FOLLOW_STRING_in_type1470);  
                    stream_STRING.add(STRING127);



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
                    // 159:11: -> ^( TYPE STRING )
                    {
                        // src/LCPLTreeBuilder.g:159:14: ^( TYPE STRING )
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
                    // src/LCPLTreeBuilder.g:160:4: ID
                    {
                    ID128=(Token)match(input,ID,FOLLOW_ID_in_type1483);  
                    stream_ID.add(ID128);



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
                    // 160:7: -> ^( TYPE ID )
                    {
                        // src/LCPLTreeBuilder.g:160:10: ^( TYPE ID )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPE, "TYPE"), root_1);

                        adaptor.addChild(root_1, stream_ID.nextNode());

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
    // $ANTLR end "type"

    // Delegated rules


 

    public static final BitSet FOLLOW_class_definition_in_program415 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_classdef_in_class_definition435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_classdef453 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ID_in_classdef457 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_INHERITS_in_classdef459 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ID_in_classdef463 = new BitSet(new long[]{0x0000024000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_members_in_classdef465 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_END_in_classdef468 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_classdef470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_classdef508 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ID_in_classdef512 = new BitSet(new long[]{0x0000024000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_members_in_classdef514 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_END_in_classdef517 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_classdef519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_method553 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_method555 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_END_in_method557 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_method559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_method572 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_method574 = new BitSet(new long[]{0x0720670980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_block_in_method576 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_END_in_method578 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_method580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_method595 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_method597 = new BitSet(new long[]{0x0000020180000000L});
    public static final BitSet FOLLOW_type_in_method599 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_method601 = new BitSet(new long[]{0x0720670980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_block_in_method603 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_END_in_method605 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_method607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_method624 = new BitSet(new long[]{0x0000020180000000L});
    public static final BitSet FOLLOW_formal_param_in_method631 = new BitSet(new long[]{0x8000000000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_65_in_method635 = new BitSet(new long[]{0x0000020180000000L});
    public static final BitSet FOLLOW_formal_param_in_method641 = new BitSet(new long[]{0x8000000000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_64_in_method646 = new BitSet(new long[]{0x0000020180000000L});
    public static final BitSet FOLLOW_type_in_method648 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_method652 = new BitSet(new long[]{0x0720670980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_block_in_method654 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_END_in_method656 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_method658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_member_in_members688 = new BitSet(new long[]{0x0000020000000002L,0x0000000000000020L});
    public static final BitSet FOLLOW_decl_section_in_member708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_method_in_member721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_block738 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_block740 = new BitSet(new long[]{0x0720670980000002L,0x0000000000000A04L});
    public static final BitSet FOLLOW_local_decl_in_block744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_local_decl_in_block762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOCAL_in_local_decl778 = new BitSet(new long[]{0x0000020180000000L});
    public static final BitSet FOLLOW_attribute_in_local_decl781 = new BitSet(new long[]{0x0000024180000000L});
    public static final BitSet FOLLOW_END_in_local_decl785 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_local_decl787 = new BitSet(new long[]{0x0720670980000002L,0x0000000000000A04L});
    public static final BitSet FOLLOW_block_in_local_decl789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formal_param812 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ID_in_formal_param814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_dispatch834 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_primaryExpression_in_dispatch838 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_dispatch840 = new BitSet(new long[]{0x0000020180000000L});
    public static final BitSet FOLLOW_type_in_dispatch844 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_dispatch846 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ID_in_dispatch852 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A14L});
    public static final BitSet FOLLOW_dispatch_arguments_in_dispatch854 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_dispatch857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_dispatch_arguments889 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_dispatch_arguments892 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_expression_in_dispatch_arguments896 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_decl_section917 = new BitSet(new long[]{0x0000020180000000L});
    public static final BitSet FOLLOW_attribute_in_decl_section920 = new BitSet(new long[]{0x0000024180000000L});
    public static final BitSet FOLLOW_END_in_decl_section924 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_decl_section926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_attribute947 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ID_in_attribute949 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_attribute951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_attribute966 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ID_in_attribute968 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_EQ_in_attribute970 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_expression_in_attribute972 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_attribute974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_statement996 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_expression_in_statement1002 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_statement1004 = new BitSet(new long[]{0x0720670980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_block_in_statement1008 = new BitSet(new long[]{0x0000004000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_statement1011 = new BitSet(new long[]{0x0720670980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_block_in_statement1017 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_END_in_statement1021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_statement1045 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_expression_in_statement1047 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_statement1049 = new BitSet(new long[]{0x0720670980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_block_in_statement1051 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_END_in_statement1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_cast1071 = new BitSet(new long[]{0x0000020180000000L});
    public static final BitSet FOLLOW_type_in_cast1072 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_expression_in_cast1074 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_cast1076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignmentExpression_in_expression1094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_equalityExpression_in_assignmentExpression1112 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_EQ_in_assignmentExpression1115 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_assignmentExpression_in_assignmentExpression1118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relationalExpression_in_equalityExpression1132 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_EQUAL_in_equalityExpression1135 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_relationalExpression_in_equalityExpression1138 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_relationalExpression1150 = new BitSet(new long[]{0x000C000000000002L});
    public static final BitSet FOLLOW_LTEQ_in_relationalExpression1154 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_LT_in_relationalExpression1159 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_additiveExpression_in_relationalExpression1164 = new BitSet(new long[]{0x000C000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1175 = new BitSet(new long[]{0x0030000000000002L});
    public static final BitSet FOLLOW_PLUS_in_additiveExpression1181 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_SUB_in_additiveExpression1186 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1191 = new BitSet(new long[]{0x0030000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression1204 = new BitSet(new long[]{0x00C0000000000002L});
    public static final BitSet FOLLOW_STAR_in_multiplicativeExpression1210 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_DIV_in_multiplicativeExpression1215 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression1221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUB_in_unaryExpression1234 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression1236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleUnaryExpression_in_unaryExpression1249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_simpleUnaryExpression1259 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_unaryExpression_in_simpleUnaryExpression1262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primaryExpression_in_simpleUnaryExpression1272 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_simpleUnaryExpression1282 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_expression_in_simpleUnaryExpression1288 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_simpleUnaryExpression1290 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_expression_in_simpleUnaryExpression1296 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_simpleUnaryExpression1298 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_75_in_primaryExpression1325 = new BitSet(new long[]{0x0720660980000000L,0x0000000000000A04L});
    public static final BitSet FOLLOW_expression_in_primaryExpression1327 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_primaryExpression1329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dispatch_in_primaryExpression1342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new_object_in_primaryExpression1347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cast_in_primaryExpression1352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_primaryExpression1357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_primaryExpression1362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_integer_in_literal1370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_literal1375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_literal1380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_CONSTANT_in_literal1393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_integer1403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_string1419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_new_object1436 = new BitSet(new long[]{0x0000020180000000L});
    public static final BitSet FOLLOW_type_in_new_object1438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_type1457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_type1470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type1483 = new BitSet(new long[]{0x0000000000000002L});

}