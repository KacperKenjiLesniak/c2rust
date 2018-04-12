package parser;

import gen.MarkupLexer;
import gen.MarkupParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Parser {
    public void parse(){
        CharStream inputStream = new ANTLRInputStream(
                "I would like to [b]emphasize[/b] this and [u]underline [b]that[/b][/u]. " +
                        "Let's not forget to quote: [quote author=\"John\"]You're wrong![/quote]");
        MarkupLexer markupLexer = new MarkupLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(markupLexer);
        MarkupParser markupParser = new MarkupParser(commonTokenStream);

        MarkupParser.FileContext fileContext = markupParser.file();
        MarkupVisitor visitor = new MarkupVisitor(System.out);
        visitor.visit(fileContext);
    }
}
