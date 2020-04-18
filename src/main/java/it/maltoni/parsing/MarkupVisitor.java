package it.maltoni.parsing;

/** Our visitor prints all the text and ignore all the tags. */
public class MarkupVisitor extends MarkupParserBaseVisitor<String> {
    @Override
    public String visitFile(final MarkupParser.FileContext context) {
        visitChildren(context);
        System.out.println();
        return null;
    }

    @Override
    public String visitContent(final MarkupParser.ContentContext context) {
        System.out.print(context.TEXT().getText());
        return visitChildren(context);
    }
}
