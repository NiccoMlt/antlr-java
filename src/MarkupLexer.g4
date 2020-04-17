// You simply can’t define a lexical mode together with a parser grammar
// You can use lexical modes only in a lexer grammar, not in a combined grammar.
// You define one or more tokens that can delimit the different modes and activate them.

// Every single token has to be defined explicitly, we cannot define tokens implicitly:
// in the lexer grammar, we need to define all tokens because they cannot be defined later in the parser grammar.

lexer grammar MarkupLexer;

OPEN                : '[' -> pushMode(BBCODE) ;
TEXT                : ~('[')+ ;

// Parsing content inside tags
mode BBCODE;

CLOSE               : ']' -> popMode ;
SLASH               : '/' ;
EQUALS              : '=' ;
STRING              : '"' .*? '"' ;
ID                  : LETTERS+ ;
WS                  : [ \t\r\n] -> skip ;

fragment LETTERS    : [a-zA-Z] ;
