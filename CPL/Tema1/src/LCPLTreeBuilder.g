grammar LCPLTreeBuilder;

options {
    language = Java;
    output = AST;
    ASTLabelType = CommonTree;
}

tokens {
    PROGRAM;
    ATTRIBUTE_ARRAY;
    CLASSDEFINITION;
    CONSTANT_INT;
    FORMAL_PARAM;
    CONSTANT_STRING;
    SYMBOL;
    EQ_COMP;
    DECL_SECTION;
    UNARY_MINUS;
    PAR_EXPR;
    BLOCK;
    OBJ;
    EXPR;
    EMPTY_DISPATCH;
    LOCAL_DECL;
    DISPATCH;
    DISPATCH_ARGUMENTS;
    DECLARATION_SECTION;
    EMPTY_METHOD;
    ARRAY_TYPE;
    DISPATCH_ARGS;
    UNARY_MINUS;
    MEMBER;
    MEMBERS;
    ATTRIBUTE;
    ASSIGNMENT;
    ATTR;
    EXPRESSION;
    TYPE;
    INT = 'Int';
    STRING = 'String';
    CLASS = 'class';
    INHERITS = 'inherits';
    NEW = 'new';
    METHOD;
    STATEMENT;
    END = 'end';
    NULL = 'null';
    LOCAL = 'local';
    DISPATCH_S;
    ID;
    VOID_CONSTANT = 'void';
    CAST;
    STRING_CONST;
    IF = 'if';
    WHILE = 'while';
    SELF = 'self';
    SUBSTR;
    ARRAY;
    UNARY_BRACKET;
}

program :   (class_definition+) -> ^(PROGRAM class_definition+)
	;

class_definition
	:	classdef  -> ^(CLASSDEFINITION classdef)
	;

classdef :  CLASS name=ID INHERITS parent=ID members? END ';' -> ^(CLASS $name $parent members?)
        |   CLASS name=ID members? END ';' -> ^(CLASS $name members?)
        ;

method  : ID ':' END ';' -> ^(EMPTY_METHOD ID)
	| ID ':' block END ';' -> ^(METHOD ID block)
	| ID '->' type ':' block END ';' -> ^(METHOD ID type block)
	| ID (f += formal_param) (',' f += formal_param)* ('->' type)? ':' block END ';' -> ^(METHOD ID $f+ type? block)
	;
    
members	:	(member+) -> ^(MEMBERS member+)
	;


member	:	decl_section -> ^(MEMBER decl_section)
	|	method -> ^(MEMBER method)
	;

block	:	(expression ';')+ local_decl? -> ^(BLOCK expression+ local_decl?)
	|	local_decl -> ^(BLOCK local_decl)
	;
	
local_decl
	:	LOCAL (attribute)+ END ';' block ? -> ^(LOCAL attribute+ block?)
	;

formal_param
	:	type ID -> ^(FORMAL_PARAM type ID)
	;
	
dispatch
	:	'[' ((primaryExpression'.') (type '.')? )? ID dispatch_arguments? ']'
	 -> ^(DISPATCH primaryExpression? type?  ID dispatch_arguments?)
	 |	'[' 'self.' (type '.')? ID dispatch_arguments? ']' -> ^(DISPATCH_S ID type? dispatch_arguments?)
	;

dispatch_arguments
	:	ar+=expression (',' ar+=expression)* -> ^(DISPATCH_ARGUMENTS $ar+)
	;

decl_section
	:	'var' (attribute)+ 'end' ';' -> ^(DECL_SECTION (attribute)+)
	;
	
attribute
	:	(type ID ';' -> ^(ATTRIBUTE type ID)
		|type '[]' ID ';' -> ^(ATTRIBUTE_ARRAY type ID))
	|	(type ID EQ expression ';' -> ^(ATTRIBUTE type ID expression)
		|type '[]' ID EQ expression ';' -> ^(ATTRIBUTE_ARRAY type ID expression))
	;
	
statement
	:	IF cond = expression 'then' ifBlock=block ('else' elseBlock = block)? END 
		-> ^(IF $cond $ifBlock $elseBlock?)
	|	WHILE expression 'loop' block END -> ^(WHILE expression block)
	;

cast	:	'{'type expression '}' -> ^(CAST type expression)
	;

expression
	:	assignmentExpression -> ^(EXPRESSION assignmentExpression)
	;
	
assignmentExpression
	:	 equalityExpression (EQ^ assignmentExpression)?
	|	'self.' ID (EQ^ assignmentExpression)
	;
	
	
equalityExpression
	:	relationalExpression (EQUAL^ relationalExpression)*
	;
	
relationalExpression
	:	additiveExpression ((LTEQ^ | LT^ ) additiveExpression)*
	;

additiveExpression
	:	multiplicativeExpression
		((PLUS^ | SUB^ )	multiplicativeExpression)*
	;
	
multiplicativeExpression
	:	unaryExpression
		((STAR^ | DIV^ ) 	unaryExpression)*
	;
		
unaryExpression
	:	SUB unaryExpression -> ^(UNARY_MINUS unaryExpression)
	|	simpleUnaryExpression
	;
	
simpleUnaryExpression
	:	NOT^ unaryExpression
	|	(e = primaryExpression -> $e)
	('[' (a = expression -> ^(UNARY_BRACKET $simpleUnaryExpression $a))
	( ',' b = expression -> ^(SUBSTR $simpleUnaryExpression $b))?
	 ']' -> $simpleUnaryExpression)*
	 ;
	
primaryExpression
	:	'(' expression ')' -> ^(PAR_EXPR expression)
	|	dispatch
	|	new_object
	|	cast
	|	statement
	|	literal
	;

literal	:	integer
	|	string
	|	type -> ^(SYMBOL type)
	|	VOID_CONSTANT
	|	SELF
	;

integer :	value=INTEGER -> ^(CONSTANT_INT $value)
	;

string	:	STRING_LITERAL  -> ^(CONSTANT_STRING STRING_LITERAL)
	;

new_object
	:	NEW type -> ^(NEW type)
	;

type	:	(INT -> ^(TYPE INT)
	|	STRING -> ^(TYPE STRING)
	|	a = ID -> ^(TYPE $a))
	;

STRING_LITERAL
    :  '"' ( ESCAPE_SEQUENCE | ~('\\'|'"') )* '"'
    ;

ESCAPE_SEQUENCE
    :   '\\' .
    ;

    
SUB	:	'-';
PLUS	:	'+';
STAR	:	'*';
DIV	:	'/';
EQ	:	'=';
LT	:	'<';
NOT	:	'!';
EQUAL	:	'==';
LTEQ	:	'<=';

LINE_COMMENT
    : '#' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;
    
INTEGER	: '0'
	| ('1'..'9') ('0'..'9')*
	;
	
ID  :   ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;       
 
WS  :   (' ' | '\t' | '\n' | '\r') {$channel=HIDDEN;}
    ;