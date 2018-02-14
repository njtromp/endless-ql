/* https://tomassetti.me/antlr-mega-tutorial/ */

grammar QL;

/*
 * Parser rules
 */

form: 'form' form_id block EOF;
block: '{' statement* '}';
statement: question
	     | conditional
	     | assignment
	     ;
assignment: STR var ':' type '=' '(' expression ')';
question: STR var ':' type;
conditional: 'if' '(' expression ')' block;
expression: BOOL 
		  | STR
		  | INT
		  | var
		  | '(' expression ')' 
		  | NOT expression
		  | expression COMPARER expression
		  | expression OPERATOR expression
		  | expression AND expression
		  | expression OR expression
		  ;

form_id: NAME;
var: NAME;
type: 'int'
    | 'boolean';

/*
 * Lexer rules
 */

BOOL: TRUE | FALSE;
INT: NUMBER;
NOT: '!';
COMPARER: '<'
	    | '>'
	    | '<='
	    | '>='
	    | '!='
	    | '=='
	    ;
OPERATOR: ADD
        | DIV
        | SUB
        | TIMES
        ;
ADD: '+';
DIV: '/';
SUB: '-';
TIMES: '*';
AND: '&&';
OR: 'OR';
TRUE: 'true';
FALSE: 'false';

WS:	[ \t\n\r]+ -> skip;
COMMENT: '/*' .*? '*/' -> skip;

NUMBER: [0-9]+;
STR: '"' .*? '"';
NAME: ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;