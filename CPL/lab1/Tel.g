grammar Tel;

stat	:	VARIABLE '='expr;
expr	:	 mult('+'mult|'-'mult)*;
mult	:	VARIABLE ('*' VARIABLE)*;
prod	:	VARIABLE
	|	(VARIABLE'*');
	
VARIABLE	:	'a'..'z'*;