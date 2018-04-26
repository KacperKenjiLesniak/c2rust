package transpiler;

import app.Controller;
import gen.C.CBaseVisitor;
import gen.C.CParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

public class Visitor extends CBaseVisitor<String> {

    @Override
    public String defaultResult(){
        return "";
    }

    @Override
    public String aggregateResult(String aggregate, String nextResult) {
        if (aggregate == null) {
            return nextResult;
        }

        if (nextResult == null) {
            return aggregate;
        }

        StringBuilder sb = new StringBuilder(aggregate);
        sb.append("");
        sb.append(nextResult);

        return sb.toString();
    }

    @Override
    public String visitCompilationUnit(CParser.CompilationUnitContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public String visitConditionalExpression(CParser.ConditionalExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public String visitExpression(CParser.ExpressionContext ctx){
        return visitChildren(ctx);
    }

    @Override
    public String visitAssignmentExpression(CParser.AssignmentExpressionContext ctx){
        return visitChildren(ctx);
    }

    @Override
    public String visitDeclaration(CParser.DeclarationContext ctx){
        return "let " + visitChildren(ctx) + ";\n";
    }


    @Override
    public String visitInitDeclaratorList(CParser.InitDeclaratorListContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public String visitInitDeclarator(CParser.InitDeclaratorContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitFunctionDefinition(CParser.FunctionDefinitionContext ctx){
        return "fn " + visitChildren(ctx);
    }

    @Override
    public String visitDeclarator(CParser.DeclaratorContext ctx){
        return ctx.getText();
    }

    @Override
    public String visitCompoundStatement(CParser.CompoundStatementContext ctx){
        return "{\n" + visitChildren(ctx) + "}";
    }

    @Override
    public String visitJumpStatement(CParser.JumpStatementContext ctx){
        return ctx.getChild(1).getText() + "\n";
    }
}
