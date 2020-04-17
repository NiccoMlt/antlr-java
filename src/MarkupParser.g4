parser grammar MarkupParser;

// Since the tokens we need are defined in the lexer grammar, we need to use an option to say to ANTLR where it can find them.
// This is not necessary in combined grammars, since the tokens are defined in the same file.
options { tokenVocab=MarkupLexer; }

file        : element* ;

attribute   : ID '=' STRING ;

// We define a content rule so that we can manage more easily the text that we find later in the program.
content     : TEXT ;

// The element rule is sort of transparent in the tree; we define it to:
// - avoid repetition in our grammar
// - simplify managing the results of the parsing
element     : (content | tag) ;

// we don’t need to explicitly use the tokens every time, but instead we can use the corresponding text
// ANTLR will automatically transform the text in the corresponding token, but this can happen only if they are already defined
tag         : '[' ID attribute? ']' element* '[' '/' ID ']' ;
