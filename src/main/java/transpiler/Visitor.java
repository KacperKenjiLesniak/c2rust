package transpiler;

import app.Controller;
import gen.C.CBaseVisitor;
import gen.C.CParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


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
        if (ctx.getChild(1).getText().equals(";")) return visitChildren(ctx);
        final ParseTree assignment = ctx.getChild(1).getChild(0);
        final ParseTree declarator = assignment.getChild(0);
        if (ctx.getChildCount() > 2 && ctx.getChild(0).getText().equals("char") &&
                declarator.getChild(0).getChildCount() > 2){
            return "let " + declarator.getChild(0).getChild(0).getText()
                    + " = " + assignment.getChild(2).getText() + ";\n";
        }
        if (ctx.getChildCount() == 3){
            return "let " + visit(ctx.getChild(1)) + ctx.getChild(2).getText() + "\n";
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
        if (ctx.getChild(1).getText().equals("main()")) {
            return "fn " + ctx.getChild(1).getText() + visit(ctx.getChild(2));
        }
        else{
            return "fn " + visit(ctx.getChild(1)) + " -> " + visit(ctx.getChild(0)) + "\n" + visit(ctx.getChild(2)) + "\n";
        }
    }

    @Override
    public String visitTypeSpecifier(CParser.TypeSpecifierContext ctx) {
        if (ctx.getText().equals("float")) return "f32";
        if (ctx.getText().equals("double")) return "f64";
        if (ctx.getText().equals("char")) return "char";
        if (ctx.getText().equals("int")) return "i32";
        if (ctx.getText().equals("bool")) return "bool";
        else return visitChildren(ctx);
    }

    @Override
    public String visitParameterDeclaration(CParser.ParameterDeclarationContext ctx) {
        return ctx.getChild(1).getText() + ": " + visit(ctx.getChild(0));
    }

    @Override
    public String visitDeclarator(CParser.DeclaratorContext ctx){
        return visitChildren(ctx);
    }

    @Override
    public String visitCompoundStatement(CParser.CompoundStatementContext ctx){
        return "{\n" + visitChildren(ctx) + "}\n";
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
        //variable or main function
        //function
        if(ctx.getChildCount()==4 && ctx.getParent().getParent().getClass().equals(CParser.FunctionDefinitionContext.class)){
            return ctx.getChild(0).getText() + ctx.getChild(1).getText() + visit(ctx.getChild(2)) + ctx.getChild(3).getText();
        }
        //array definition
        else if(ctx.getChildCount()==4){
            return ctx.getChild(0).getText();
        }
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
            return ifStatement + elseStatement;
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
        String newLine = "";
        if (ctx.getChildCount() > 1 && ctx.getChild(1).getText().equals(";")) newLine = "\n";
        return visit(ctx.getChild(0)) + ctx.getChild(1).getText() + newLine;
    }

    @Override
    public String visitAdditiveExpression(CParser.AdditiveExpressionContext ctx) {
        if (ctx.getChildCount()==1){
            return visitChildren(ctx);
        }
        return ctx.getText();
    }

    @Override
    public String visitMultiplicativeExpression(CParser.MultiplicativeExpressionContext ctx) {
        if (ctx.getChildCount()==1) return visitChildren(ctx);
        return ctx.getText();
    }

    @Override
    public String visitJumpStatement(CParser.JumpStatementContext ctx) {
        return visitChildren(ctx) + "\n";
    }

    @Override
    public String visitStructOrUnionSpecifier(CParser.StructOrUnionSpecifierContext ctx) {
        if (ctx.getChildCount()>4){
            return ctx.getChild(0).getText() + " " + ctx.getChild(1).getText() + " " + ctx.getChild(2).getText() + "\n" +
                    visit(ctx.getChild(3)) + ctx.getChild(4).getText() + "\n";
        }
        else return visitChildren(ctx);
    }

    @Override
    public String visitStructDeclaration(CParser.StructDeclarationContext ctx) {
        String fieldType = ctx.getChild(0).getText();
        if (fieldType.equals("char")) return findDirectDeclarator(ctx.getChild(0).getParent()) + ": String, \n";
        else return ctx.getChild(0).getChild(1).getText() + ":" + visit(ctx.getChild(0).getChild(0)) + ",\n";
    }

    private String findDirectDeclarator(ParseTree child) {
        if (child.getChildCount() < 1) return "";
        if (child.getClass().equals(CParser.DirectDeclaratorContext.class) && child.getChildCount() < 2) return child.getText();
        List<String> text = new ArrayList<>();
        IntStream.range(0, child.getChildCount()).forEach(index -> text.add(findDirectDeclarator(child.getChild(index))));
        return String.join("", text);
    }

    @Override
    public String visitPostfixExpression(CParser.PostfixExpressionContext ctx) {
        if (ctx.getChild(0).getText().equals("strcat")) {
            return "let new" + ctx.getChild(2).getChild(0).getText() + " = " + ctx.getChild(2).getChild(0).getText() +
                    ".to_string() + " +  ctx.getChild(2).getChild(2).getText();
        }
        if(ctx.getChild(0).getText().equals("printf")) {
            String printedText = ctx.getChild(2).getText();
            String newLine = "";
            if (printedText.endsWith("\\n\"")) {
                newLine = "ln";
                printedText = printedText.substring(0, printedText.length()-3)+"\"";
            }
            if (newLine.isEmpty()) Controller.rustCodeImportString.add("use std::print;\n");
            else Controller.rustCodeImportString.add("use std::println;\n");
            return "print" + newLine + "!" + "(" + printedText + ")";
        }
        if (ctx.getChildCount() == 4) {
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
            return ctx.getChild(0).getText() + " " + visit(ctx.getChild(2)) + visit(ctx.getChild(4));
        }
        else if (ctx.getChild(0).getText().equals("for")) {
            return ctx.getChild(0).getText() + " " + visit(ctx.getChild(2)) + visit(ctx.getChild(4));
        }
        return visitChildren(ctx);
    }

    @Override
    public String visitForCondition(CParser.ForConditionContext ctx) {
        String iteratorRevMethod = getIteratorRevMethod(ctx.getChild(4));
        String iteratorStepByMethod = getIteratorStepByMethod(ctx.getChild(4));
        if (iteratorStepByMethod.isEmpty() && iteratorRevMethod.isEmpty())
            return visit(ctx.getChild(0)) + ".." + visit(ctx.getChild(2)) + iteratorStepByMethod;
        List<String> forDeclarationList = visitIteratorMethodsForDeclaration(ctx.getChild(0));
        if (!iteratorStepByMethod.isEmpty() && iteratorRevMethod.isEmpty()) {
            Controller.rustCodeImportString.add("use std::iter::step_by;\n");
            return forDeclarationList.get(0) + "(" +
                    forDeclarationList.get(1) + ".." + visit(ctx.getChild(2)) + ")" + iteratorStepByMethod;
        }
        Controller.rustCodeImportString.add("use std::iter::step_by;\n");
        Controller.rustCodeImportString.add("use std::iter::rev;\n");
        return forDeclarationList.get(0) + "(" + visit(ctx.getChild(2)) + ".."
                + (Integer.parseInt(forDeclarationList.get(1))+1) + ")" + iteratorRevMethod + iteratorStepByMethod;
    }

    @Override
    public String visitForExpression(CParser.ForExpressionContext ctx) {
        return getForRange(ctx.getChild(0));
    }

    private String getIteratorStepByMethod(ParseTree child) {
        if (child == null) return "";
        else if (child.getClass().equals(CParser.AssignmentExpressionContext.class) && child.getChildCount() > 2) {
            return getIteratorStepByMethod(child.getChild(2));
        }
        else if (child.getClass().equals(CParser.AdditiveExpressionContext.class) && child.getChildCount() > 2) {
            String resultMethods = "";
            if (!child.getChild(2).getText().equals("1")) resultMethods += ".step_by(" + child.getChild(2).getText()+ ")";
            return resultMethods;
        }
        return getIteratorStepByMethod(child.getChild(0));
    }

    private String getIteratorRevMethod(ParseTree child) {
        if (child == null) return "";
        else if (child.getClass().equals(CParser.AssignmentExpressionContext.class) && child.getChildCount() > 2) {
            return getIteratorRevMethod(child.getChild(2));
        }
        else if (child.getClass().equals(CParser.AdditiveExpressionContext.class) && child.getChildCount() > 2) {
            String resultMethods = "";
            if (child.getText().contains("-")) resultMethods += ".rev()";
            return resultMethods;
        }
        return getIteratorRevMethod(child.getChild(0));
    }

    private String getForRange(ParseTree child) {
        if (child == null) return "";
        if (child.getClass().equals(CParser.RelationalExpressionContext.class) && child.getChildCount() > 2) {
            if (child.getChild(1).getText().equals("<")) return child.getChild(2).getText();
            else if (child.getChild(1).getText().equals("<=")) return Integer.parseInt(child.getChild(2).getText())+1+"";
            else if (child.getChild(1).getText().equals(">")) return Integer.parseInt(child.getChild(2).getText())+1+"";
            else if (child.getChild(1).getText().equals(">=")) return child.getChild(2).getText();
        }
        return getForRange(child.getChild(0));
    }


    @Override
    public String visitForDeclaration(CParser.ForDeclarationContext ctx) {
        return ctx.getChild(1).getChild(0).getChild(0).getText() + " in "
                + ctx.getChild(1).getChild(0).getChild(2).getText();
    }

    public List<String> visitIteratorMethodsForDeclaration(ParseTree child) {
        List<String> iteratorMethodsForDeclaration = new ArrayList<>();
        iteratorMethodsForDeclaration.add(child.getChild(1).getChild(0).getChild(0).getText() + " in ");
        iteratorMethodsForDeclaration.add(child.getChild(1).getChild(0).getChild(2).getText());
        return iteratorMethodsForDeclaration;
    }


}
