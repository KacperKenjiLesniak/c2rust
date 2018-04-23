package transpiler;

import app.Controller;
import gen.C.CBaseVisitor;
import gen.C.CParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

public class Visitor extends CBaseVisitor<String> {


    @Override
    public String visitCompilationUnit(CParser.CompilationUnitContext ctx) {
//        return super.visitFunctionDefinition(ctx);
        System.out.println("Compilation unit: " + ctx.getText());
        visitChildren(ctx);
        return null;
    }

    @Override
    public String visitConditionalExpression(CParser.ConditionalExpressionContext ctx) {
//        return super.visitFunctionDefinition(ctx);
        System.out.println("CONDITIONAL EXPRESSION CONTEXT: " + ctx.getText());
        visitChildren(ctx);
        return null;
    }

    @Override
    public String visitExpression(CParser.ExpressionContext ctx){
        System.out.println("EXPRESSION CONTEXT: " + ctx.getText());
        visitChildren(ctx);
        return null;
    }

    @Override
    public String visitAssignmentExpression(CParser.AssignmentExpressionContext ctx){
        System.out.println("Assignment expression CONTEXT: " + ctx.getText());
        if (ctx.unaryExpression()!=null) {
            System.out.println("LETS BREAK IT DOWN");
            System.out.println("Left: " + ctx.unaryExpression().getText());
            System.out.println("Sign: " + ctx.assignmentOperator().getText());
            System.out.println("Right: " + ctx.assignmentExpression().getText());
        }
        visitChildren(ctx);
        return null;
    }

    @Override
    public String visitDeclaration(CParser.DeclarationContext ctx){
        System.out.println("Assignment expression CONTEXT: " + ctx.getText());
        if (ctx.declarationSpecifiers()!=null) {
            System.out.println("LETS BREAK IT DOWN");
            System.out.println("Left: " + ctx.declarationSpecifiers().getText());
        }
        if (ctx.initDeclaratorList()!=null){
            System.out.println(ctx.initDeclaratorList().getText());
        }
        visitChildren(ctx);
        return null;
    }

    @Override
    public String visitInitDeclaratorList(CParser.InitDeclaratorListContext ctx) {
        System.out.println("Init Declaration List CONTEXT " + ctx.getText());
        System.out.println(ctx.initDeclarator().getText());
        return visitChildren(ctx);
    }

    @Override
    public String visitInitDeclarator(CParser.InitDeclaratorContext ctx) {
        System.out.println("Init Declaration CONTEXT " + ctx.getText());
        Controller.rustCodeStaticString += ctx.declarator().getText() + " ABRAKADABRA ROWNA SIE " + ctx.initializer().getText();
        return visitChildren(ctx);
    }

}
