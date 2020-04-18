package it.maltoni.parsing;

/**
 * Our visitor prints all the text and ignore all the tags.
 */
public class MarkupVisitor extends MarkupParserBaseVisitor<String> {
    @Override
    public String visitFile(final MarkupParser.FileContext context) {
        visitChildren(context);
        System.out.println();
        return null;
    }

    @Override
    public String visitContent(final MarkupParser.ContentContext context) {
        // now return its text instead of printing it
        return context.getText();
    }

    /**
     * {@inheritDoc}
     * <p>
     * It prints the text of its child,
     * but only if it’s a top element,
     * and not inside a tag.
     *
     * @param context {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String visitElement(final MarkupParser.ElementContext context) {
        if (context.parent instanceof MarkupParser.FileContext) {
            if (context.content() != null) {
                System.out.print(visitContent(context.content()));
            }
            if (context.tag() != null) {
                System.out.print(visitTag(context.tag()));
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <ol>
     * <li>every top element visit each child
     * <ul>
     * <li>if it’s a content node, it directly returns the text
     * <li>if it’s a tag, it setups the correct delimiters and then it checks its children.
     * It repeats step 2 for each children and then it returns the gathered text
     * </ul>
     * <li>it prints the returned text
     * </ol>
     * @param context {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String visitTag(MarkupParser.TagContext context) {
        final StringBuilder text = new StringBuilder();
        String startDelimiter = "", endDelimiter = "";

        final String id = context.ID(0).getText();

        switch (id) {
            case "b":
                startDelimiter = endDelimiter = "**";
                break;
            case "u":
                // convert the BBCode's underline to Markdown's italic
                startDelimiter = endDelimiter = "*";
                break;
            case "quote":
                // We can’t maintain the information about the author of the quote ...
                String attribute = context.attribute().STRING().getText();
                attribute = attribute.substring(1, attribute.length() - 1);
                startDelimiter = System.lineSeparator() + "> ";
                endDelimiter = System.lineSeparator()
                    + "> "
                    + System.lineSeparator()
                    + "> – "
                    + attribute
                    + System.lineSeparator();
                // ... so we print the information in a human reader way
                break;
        }

        text.append(startDelimiter);
        // We visit the children and gather their text ...
        for (final MarkupParser.ElementContext node : context.element()) {
            if (node.tag() != null) {
                text.append(visitTag(node.tag()));
            }
            if (node.content() != null) {
                text.append(visitContent(node.content()));
            }
        }
        // ... then we close with the endDelimiter ...
        text.append(endDelimiter);
        // ... finally we return the text that we have created
        return text.toString();
    }
}
