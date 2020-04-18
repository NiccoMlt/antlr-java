package it.maltoni.parsing;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class App {

    public static void main(final String[] args) {
        final CharStream inputStream = CharStreams.fromString(
            "I would like to [b]emphasize[/b] this and [u]underline [b]that[/b][/u]. " +
                "Let's not forget to quote: [quote author=\"John\"]You're wrong![/quote]");
        final MarkupLexer markupLexer = new MarkupLexer(inputStream);
        final CommonTokenStream commonTokenStream = new CommonTokenStream(markupLexer);
        final MarkupParser markupParser = new MarkupParser(commonTokenStream);

        final MarkupParser.FileContext fileContext = markupParser.file();
        final MarkupVisitor visitor = new MarkupVisitor();
        visitor.visit(fileContext);
    }
}
