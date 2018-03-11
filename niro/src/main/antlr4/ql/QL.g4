grammar QL;

FORM         : 'form' ;
IF           : 'if' ;
ELSE         : 'else' ;
ASSIGN       : '=' ;

BOOLEAN      : 'boolean' ;
INTEGER      : 'integer' ;
STRING       : 'string' ;
DECIMAL      : 'decimal' ;
MONEY        : 'money' ;
DATE         : 'date' ;

FALSE        : 'false' ;
TRUE         : 'true' ;

CURLY_LEFT   : '{' ;
CURLY_RIGHT  : '}' ;

BRACK_LEFT   : '(' ;
BRACK_RIGHT  : ')' ;

DOUBLE_COLON : ':' ;

LESS_THEN          : '<' ;
LESS_THEN_EQUAL    : '<=' ;
EQUAL              : '==' ;
NOT_EQUAL          : '!=' ;
GREATER_THEN_EQUAL : '>=' ;
GREATER_THEN       : '>' ;

MIN          : '-' ;
PLUS         : '+' ;
DIV          : '/' ;
MUL          : '*' ;

OR           : '||' ;
AND          : '&&' ;
NOT          : '!' ;
PERIOD       : '.' ;

WHITESPACE   : [ \t\r\n]+ -> skip ;
COMMENT      : '//' .*? '\n' -> skip ;

DateValue    : [0-9][0-9][0-9][0-9] '-' [0-9][0-9] '-' [0-9][0-9] ;
IntegerValue : [1-9][0-9]* ;
DecimalValue : [1-9][0-9]* PERIOD [0-9]+ ;
Identifier   : [a-zA-Z0-9_]+ ;
Text         : '"' .*? '"' { setText(getText().substring(1, getText().length() - 1)); }; // excluding double quotes

form        : FORM Identifier CURLY_LEFT statement+ CURLY_RIGHT EOF ;

statement   : question
            | conditional ;

// Precedence as specified by: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html
expression : BRACK_LEFT expression BRACK_RIGHT                                # GroupExpression
           | operator=unaryOperator expression                                # UnaryExpression
           | left=expression operator=multiplicativeOperator right=expression # MultiplicativeExpression
           | left=expression operator=additiveOperator right=expression       # AdditiveExpression
           | left=expression operator=relationalOperator right=expression     # RelationalExpression
           | left=expression operator=equalityOperator right=expression       # EqualityExpression
           | left=expression operator=AND right=expression                    # LogicalAndExpression
           | left=expression operator=OR right=expression                     # LogicalOrExpression
           | Identifier                                                       # VariableName
           | booleanValue                                                     # BooleanConstant
           | IntegerValue                                                     # IntegerConstant
           | DecimalValue                                                     # DecimalConstant
           | DateValue                                                        # DateConstant
           | Text                                                             # StringConstant ;

block      : CURLY_LEFT statement+ CURLY_RIGHT
           | statement ;

question    : label=Text Identifier DOUBLE_COLON answerType ( ASSIGN expression )?;
conditional : IF BRACK_LEFT condition=expression BRACK_RIGHT thenBlock+=block ( ELSE elseBlock+=block )? ;

answerType  : BOOLEAN | INTEGER | DECIMAL | MONEY | DATE | STRING ;

booleanValue           : FALSE | TRUE ;
unaryOperator          : MIN | NOT ;
multiplicativeOperator : MUL | DIV ;
additiveOperator       : PLUS | MIN ;
relationalOperator     : LESS_THEN | GREATER_THEN | LESS_THEN_EQUAL | GREATER_THEN_EQUAL ;
equalityOperator       : EQUAL | NOT_EQUAL ;
