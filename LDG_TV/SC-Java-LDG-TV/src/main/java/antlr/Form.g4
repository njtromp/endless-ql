// Define a grammar called Hello
grammar Form;
/*
 * Parser Rules
 */
formBuilder  : 'form' CHARACTERS CURLY_BRACKET_OPEN formData CURLY_BRACKET_CLOSE;
formData : (questionStructure)+ (ifStructure)+?;

questionStructure:
    questionLabel
    questionVariable
    QUESTION_VARIABLE_SEPERATOR
    questionVariableType
    QUESTION_VARIABLE_VALUE_SEPERATOR?
    questionVariableValue?
;
ifStructure:
    IF
    statementBlockStructure
    CURLY_BRACKET_OPEN
    (questionStructure)+
    CURLY_BRACKET_CLOSE
;

statementBlockStructure: BRACKET_OPEN questionVariable BRACKET_CLOSE ;

questionLabel : QUESTION_LABEL;
questionVariable: CHARACTERS;
questionVariableType: CHARACTERS;
questionVariableValue: BRACKET_OPEN questionVariable (PLUS | MINUS | TIMES | DIV) questionVariable BRACKET_CLOSE;

/*
 * Lexer Rules
 */

fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;

CURLY_BRACKET_OPEN : '{';
CURLY_BRACKET_CLOSE : '}';
BRACKET_OPEN : '(';
BRACKET_CLOSE : ')';

PLUS: '+';
MINUS: '-';
TIMES: '*';
DIV: '/';

QUESTION_LABEL : '"' + ((CHARACTERS | NUMBERS | ' ' | ':' | '?')+) + '"';
QUESTION_VARIABLE_SEPERATOR : ':';
QUESTION_VARIABLE_VALUE_SEPERATOR : '=';

IF : 'if';

WHITESPACE : [ \r\n\t] + -> skip;
NEWLINE : ('\r'? '\n' | '\r')+ -> skip;

CHARACTERS : (LOWERCASE | UPPERCASE)+;

fragment NUMBERS : ('0' .. '9') + ('.' ('0' .. '9') +)? ;
