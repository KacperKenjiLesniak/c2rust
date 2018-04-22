package transpiler;


import gen.C.CParser;
import gen.C.CLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Transpiler {

    public String transpile(String cCode) {
//        CharStream inputStream = new ANTLRInputStream(
//                "I would like to [b]emphasize[/b] this and [u]underline [b]that[/b][/u]. " +
//                        "Let's not forget to quote: [quote author=\"John\"]You're wrong![/quote]");
//        MarkupLexer markupLexer = new MarkupLexer(inputStream);
        CharStream charStream = CharStreams.fromString(cCode);
        CLexer cLexer = new CLexer(charStream);
//        CommonTokenStream commonTokenStream = new CommonTokenStream(markupLexer);
        CommonTokenStream commonTokenStream = new CommonTokenStream(cLexer);
//        MarkupParser markupParser = new MarkupParser(commonTokenStream);
        CParser cParser = new CParser(commonTokenStream);

//        MarkupParser.FileContext fileContext = markupParser.file();
        CParser.ConditionalExpressionContext context = cParser.conditionalExpression();
//        MarkupVisitor visitor = new MarkupVisitor(System.out);
        Visitor visitor = new Visitor();
//        visitor.visit(fileContext);
        return visitor.visit(context);
    }
}
