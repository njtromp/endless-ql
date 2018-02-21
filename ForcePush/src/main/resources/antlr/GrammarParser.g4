<<<<<<< HEAD:ForcePush/src/main/resources/antlr/GrammarParser.g4
parser grammar GrammarParser;

options { tokenVocab=Grammar; }

//TESTING RULES
a : b INT ;
b : VAR VAR;
r : VAR {System.out.println("invoke "+$VAR.text);} ;

//RULES
variable:VAR
        |NOT VAR;

expression: variable arithmetic=(PLUS|MINUS|ASTERISK|DIVISION) variable
          | variable decisions=(AND|OR) variable
          | variable comparison=(LESS|HIGHER|EQUALHIGHER|EQUALLESS|DIFF|ISEQUAL) variable;

question:variable ASSIGN LABEL type=(BOOL|STR|DATE|DECIMAL|MONEY)
        |question EQUAL LPAREN* expression RPAREN*;

conditional: operator=(IF|ELSE|IFELSE) LPAREN (variable|expression) RPAREN LCURLYBRAKET (question|conditional)+ RCURLYBRAKET;

form: FORM variable LCURLYBRAKET (question|conditional)* RCURLYBRAKET;
=======
parser grammar GrammarParser;

options { tokenVocab=Grammar; }

//TESTING RULES
a : b INT;
b   : ID ID;

//RULES
variable:VAR
        |NOT VAR;

expression: variable arithmetic=(PLUS|MINUS|ASTERISK|DIVISION) variable
          | variable boolean=(AND|OR) variable
          | variable comparison=(LESS|HIGHER|EQUALHIGHER|EQUALLESS|DIFF|ISEQUAL) variable;

question:variable ASSIGN LABEL type=(BOOL|STR|DATE|DECIMAL|MONEY)
        |question EQUAL LPAREN* expression RPAREN*;

conditional: operator=(IF|ELSE|IFELSE) LPAREN (variable|expression) RPAREN LCURLYBRAKET (question|conditional)+ RCURLYBRAKET;

form: FORM variable LCURLYBRAKET (question|conditional)* RCURLYBRAKET;
>>>>>>> 4e9549a66df631f508566cf71b749537c31c0ae7:ForcePush/src/parser/antlr/GrammarParser.g4
