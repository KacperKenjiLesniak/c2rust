package transpiler;

import gen.C.CParser;
import gen.C.CLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Transpiler {

    public String transpile(String cCode) {
        System.out.println("Transpiling:\n" + cCode);
        CharStream charStream = CharStreams.fromString(cCode);
        CLexer cLexer = new CLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(cLexer);
        CParser cParser = new CParser(commonTokenStream);
        cParser.setBuildParseTree(true);
        ParseTree tree = cParser.compilationUnit();
        Visitor visitor = new Visitor();
        return visitor.visit(tree);
    }
}
