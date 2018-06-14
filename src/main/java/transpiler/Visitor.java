package transpiler;

import gen.C.CBaseVisitor;
import gen.C.CParser;
import org.antlr.v4.runtime.tree.ParseTree;


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
        final ParseTree assignment = ctx.getChild(1).getChild(0);
        final ParseTree declarator = assignment.getChild(0);
        if (ctx.getChildCount() > 2 && ctx.getChild(0).getText().equals("char") &&
                declarator.getChild(0).getChildCount() > 2){
            return "let " + declarator.getChild(0).getChild(0).getText()
                    + " = " + assignment.getChild(2).getText() + ";\n";
        }
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
    public String visitInitializer(CParser.InitializerContext ctx) {
        if (ctx.getChildCount()==1) return ctx.getText();
        else return "[" + visit(ctx.getChild(1)) + "]";
    }

    @Override
    public String visitDirectDeclarator(CParser.DirectDeclaratorContext ctx) {
        if (ctx.getParent().getParent().getClass().equals(CParser.FunctionDefinitionContext.class) && ctx.getChildCount()==3) return ctx.getText();
        else if(ctx.getChildCount()==1) return ctx.getText();
        else return visit(ctx.getChild(0));
    }

    @Override
    public String visitInitializerList(CParser.InitializerListContext ctx) {
        if (ctx.getChildCount()==3) return visit(ctx.getChild(0)) + ", " + visit(ctx.getChild(2));
        else return  visitChildren(ctx);
    }

    @Override
    public String visitSelectionStatement(CParser.SelectionStatementContext ctx) {
        String ifStatement = ctx.getChild(0).getText() + " " + visit(ctx.getChild(2)) + " " + visit(ctx.getChild(4));
        if (ctx.getChildCount() == 5) {
            return ifStatement;
        }
        else if (ctx.getChildCount() == 7) {
            String elseStatement = ctx.getChild(5).getText() + " " + visit(ctx.getChild(6));
            return ifStatement + "\n" + elseStatement;
        }
        return visitChildren(ctx);
    }

    @Override
    public String visitEqualityExpression(CParser.EqualityExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            return visit(ctx.getChild(0)) + " " + ctx.getChild(1).getText() + " " + visit(ctx.getChild(2));
        }
        return visitChildren(ctx);
    }

    @Override
    public String visitRelationalExpression(CParser.RelationalExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            return visit(ctx.getChild(0)) + " " + ctx.getChild(1).getText() + " " + visit(ctx.getChild(2));
        }
        return visitChildren(ctx);
    }

    @Override
    public String visitPrimaryExpression(CParser.PrimaryExpressionContext ctx) {
        if (ctx.getChildCount() == 1) return ctx.getText();
        else if (ctx.getChildCount() == 3) return ctx.getChild(0) + visit(ctx.getChild(1)) + ctx.getChild(2);
        return visitChildren(ctx);
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
    public String visitAdditiveExpression(CParser.AdditiveExpressionContext ctx) {
        if (ctx.getChildCount()==1){
            return visitChildren(ctx);
        }
        return ctx.getText();
    }

    @Override
    public String visitPostfixExpression(CParser.PostfixExpressionContext ctx) {
        if(ctx.getChild(0).getText().equals("strcat")){
            return "let new" + ctx.getChild(2).getChild(0).getText() + " = " + ctx.getChild(2).getChild(0).getText() +
                    ".to_string() + " +  ctx.getChild(2).getChild(2).getText();
        }
        if (ctx.getChildCount()==4){
            return visit(ctx.getChild(0)) + ctx.getChild(1).getText()
                    + visit(ctx.getChild(2)) + ctx.getChild(3).getText();
        }
        else return visitChildren(ctx);
    }

    @Override
    public String visitLogicalAndExpression(CParser.LogicalAndExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            return visit(ctx.getChild(0)) + " " + ctx.getChild(1).getText() + " " + visit(ctx.getChild(2));
        }
        return visitChildren(ctx);
    }

    @Override
    public String visitLogicalOrExpression(CParser.LogicalOrExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            return visit(ctx.getChild(0)) + " " + ctx.getChild(1).getText() + " " + visit(ctx.getChild(2));
        }
        return visitChildren(ctx);
    }

    @Override
    public String visitIterationStatement(CParser.IterationStatementContext ctx) {
        if (ctx.getChild(0).getText().equals("while")) {
            return ctx.getChild(0).getText() + " " + visit(ctx.getChild(2)) + visit(ctx.getChild(4)) + "\n";
        }
        else if (ctx.getChild(0).getText().equals("for")) {
            return ctx.getChild(0).getText() + " " + visit(ctx.getChild(2)) + visit(ctx.getChild(4)) + "\n";
        }
        return visitChildren(ctx);
    }

    @Override
    public String visitForCondition(CParser.ForConditionContext ctx) {
        return visit(ctx.getChild(0)) + visit(ctx.getChild(2));
    }

    @Override
    public String visitForExpression(CParser.ForExpressionContext ctx) {
        return getForRange(ctx.getChild(0));
    }

    private String getForRange(ParseTree child) {
        if (child == null) return "";
        if (child.getClass().equals(CParser.RelationalExpressionContext.class) && child.getChildCount() > 2) {
            if (child.getChild(1).getText().equals("<")) return child.getChild(2).getText();
            else if (child.getChild(1).equals("<=")) return Integer.parseInt(child.getChild(2).getText())+1+"";
        }
        return getForRange(child.getChild(0));
    }


    @Override
    public String visitForDeclaration(CParser.ForDeclarationContext ctx) {
        return ctx.getChild(1).getChild(0).getChild(0).getText() + " in "
                + ctx.getChild(1).getChild(0).getChild(2).getText() + "..";
    }


}
