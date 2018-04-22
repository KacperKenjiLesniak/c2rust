package transpiler;

import gen.C.CBaseVisitor;
import gen.C.CParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

public class Visitor extends CBaseVisitor<String> {


    @Override
    public String visitDeclaration(CParser.DeclarationContext ctx) {
//        return super.visitFunctionDefinition(ctx);
        System.out.println("DECLARATION CONTEXT: " + ctx.getText());
        return null;
    }

    @Override
    public String visitConditionalExpression(CParser.ConditionalExpressionContext ctx) {
//        return super.visitFunctionDefinition(ctx);
        System.out.println("CONDITIONAL EXPRESSION CONTEXT: " + ctx.getText());
        
        return null;
    }

}
