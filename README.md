# ANTLR4-Java Tutorial - Parsing Markup

We are defining a sort of BBCode markup, with tags delimited by square brackets.

Project set up following Java Setup section of the [ANTLR Mega Tutorial](https://tomassetti.me/antlr-mega-tutorial/).
Reference to original repo at: <https://github.com/unosviluppatore/antlr-mega-tutorial>.

The project can be built and run with ```gradle run``` and JUnit test can be run with ```gradle test```.

## Parsing Markup

ANTLR can parse many things, including binary data, in that case tokens are made up of non printable characters.
But a more common problem is parsing markup languages such as XML or HTML.
Markup is also a useful format to adopt for your own creations, because it allows to mix unstructured text content with structured annotations.
They fundamentally represent a form of smart document, containing both text and structured data.
The technical term that describe them is _island languages_.
This type is not restricted to include only markup, and sometimes it’s a matter of perspective.

For example, you may have to build a parser that ignores preprocessor directives.
In that case, you have to find a way to distinguish proper code from directives, which obeys different rules.

In any case, the problem for parsing such languages is that there is a lot of text that we don’t actually have to parse, but we cannot ignore or discard, because the text contain useful information for the user and it is a structural part of the document.
The solution is _lexical modes_, a way to parse structured content inside a larger sea of free text.

Other than for markup languages, lexical modes are typically used to deal with string interpolation. That is when a string literal can contain more than simple text, for instance arbitrary expressions.
