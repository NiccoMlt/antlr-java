package it.maltoni.parsing;

public class MarkupVisitor extends MarkupParserBaseVisitor<String> {
    @Override
    public String visitFile(MarkupParser.FileContext context) {
        visitChildren(context);

        System.out.println();

        return null;
    }

    @Override
    public String visitContent(MarkupParser.ContentContext context) {
        System.out.print(context.TEXT().getText());

        return visitChildren(context);
    }
}
