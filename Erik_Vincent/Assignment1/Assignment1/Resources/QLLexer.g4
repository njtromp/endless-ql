lexer grammar QLLexer;

FORM
	: 'form'
	;
OPEN_BR
	: '('
	;
CLOSE_BR
	: ')'
	;
OPEN_CB
	: '{'
	;
CLOSE_CB
	: '}'
	;
SEP
	: ':'
	;
SEMICOLON
	: ';'
	;
BOOLEAN
	: 'boolean'
	;
MONEY
	: 'money'
	;
ASSIGN
	: '='
	;
IF
	: 'if'
	;
ELSE
	: 'else'
	;
TRUE
	: 'true'
	;
FALSE
	: 'false'
	;
INTEGER
	: '-'? NUMBER
	;
DECIMAL
	: '-'? NUMBER '.' NUMBER
	;
fragment NUMBER
	: [0-9]+
	;
NOT
	: '!'
	;
AND
	: '&&'
	;
OR
	: '||'
	;
SUB
	: '-'
	;
ID
	: [a-zA-Z0-9]+
	;
LABEL
	: '"' ~'"'*? '"'
	;
COMMENT
	: '//' ~'\n'*? '\n' -> skip
	;
WHITESPACE
	: [ \n\t\r]+ -> skip
	;