grammar Parens;

prog	:	(LPAREN prog RPAREN)* | WORD;
LPAREN	:	'(';
RPAREN	:	')';
WORD	:	('a'..'z')*;
WS	:	(' ' | '\r' | '\t' | '\n')* {skip();};

