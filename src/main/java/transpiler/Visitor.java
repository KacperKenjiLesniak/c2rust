package transpiler;

import gen.C.CBaseVisitor;
import gen.C.CParser;


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
        return visit(ctx.getChild(0)) + " = " + visit(ctx.getChild(2));
    }

    @Override
    public String visitFunctionDefinition(CParser.FunctionDefinitionContext ctx){
        return "fn " + visitChildren(ctx);
    }

    @Override
    public String visitDeclarator(CParser.DeclaratorContext ctx){
        return visitChildren(ctx);
    }

    @Override
    public String visitCompoundStatement(CParser.CompoundStatementContext ctx){
        return "{\n" + visitChildren(ctx) + "\n}";
    }

    @Override
    public String visitJumpStatement(CParser.JumpStatementContext ctx){
        return ctx.getChild(1).getText() + "\n";
    }

    @Override
    public String visitInitializer(CParser.InitializerContext ctx) {
        if (ctx.getChildCount()==1) return ctx.getText();
        else return "[" + visit(ctx.getChild(1)) + "]";
    }

    @Override
    public String visitDirectDeclarator(CParser.DirectDeclaratorContext ctx) {
        if(ctx.getChildCount()==1) return ctx.getText();
        else return visit(ctx.getChild(0));
    }

    @Override
    public String visitInitializerList(CParser.InitializerListContext ctx) {
        if (ctx.getChildCount()==3) return visit(ctx.getChild(0)) + ", " + visit(ctx.getChild(2));
        else return  visitChildren(ctx);
    }

    @Override
    public String visitSelectionStatement(CParser.SelectionStatementContext ctx) {
        return ctx.getChild(0).getText() + " " + visit(ctx.getChild(2)) + " " + visit(ctx.getChild(4));
    }

    @Override
    public String visitEqualityExpression(CParser.EqualityExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            return visit(ctx.getChild(0)) + " " + ctx.getChild(1).getText() + " " + visit(ctx.getChild(2));
        }
        return visitChildren(ctx);
    }

    @Override
    public String visitPrimaryExpression(CParser.PrimaryExpressionContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitAssignmentOperator(CParser.AssignmentOperatorContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitExpressionStatement(CParser.ExpressionStatementContext ctx) {
        return visit(ctx.getChild(0)) + ctx.getChild(1).getText();
    }

    @Override
    public String visitPostfixExpression(CParser.PostfixExpressionContext ctx) {
        if (ctx.getChildCount()==4){
            return visit(ctx.getChild(0)) + ctx.getChild(1).getText()
                    + visit(ctx.getChild(2)) + ctx.getChild(3).getText();
        }
        else return visitChildren(ctx);
    }
}
