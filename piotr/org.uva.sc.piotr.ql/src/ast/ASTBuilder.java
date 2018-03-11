package ast;

import ast.model.ASTNode;
import ast.model.Form;
import ast.model.declarations.*;
import ast.model.expressions.Expression;
import ast.model.expressions.binary.arithmetics.Addition;
import ast.model.expressions.binary.arithmetics.Division;
import ast.model.expressions.binary.arithmetics.Multiplication;
import ast.model.expressions.binary.arithmetics.Subtraction;
import ast.model.expressions.binary.comparision.*;
import ast.model.expressions.binary.logical.LogicalAnd;
import ast.model.expressions.binary.logical.LogicalOr;
import ast.model.expressions.unary.arithmetics.Minus;
import ast.model.expressions.unary.logical.Negation;
import ast.model.expressions.values.Literal;
import ast.model.expressions.values.VariableReference;
import ast.model.statements.IfStatement;
import ast.model.statements.Question;
import ast.model.statements.Statement;
import grammar.QLBaseVisitor;
import grammar.QLParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;


public class ASTBuilder extends QLBaseVisitor<ASTNode> {

    @Override
    public Form visitForm(QLParser.FormContext ctx) {

        Form form = new Form(ctx.id.getText(), this.ExtractMetaInformationFromContext(ctx));

        for (QLParser.StatementContext StatementContext : ctx.statement()) {
            Statement statement = visitStatement(StatementContext);
            form.addStatement(statement);
        }

        return form;
    }

    @Override
    public Statement visitStatement(QLParser.StatementContext ctx) {

        if (ctx.ifStatement() != null) {
            return visitIfStatement(ctx.ifStatement());
        } else if (ctx.question() != null) {
            return visitQuestion(ctx.question());
        }

        return null;
    }

    @Override
    public IfStatement visitIfStatement(QLParser.IfStatementContext ctx) {

        Expression ifConditionExpression = (Expression) visit(ctx.condition);

        List<Statement> ifStatementList = new ArrayList<>();

        for (QLParser.StatementContext StatementContext : ctx.statement()) {
            Statement statement = visitStatement(StatementContext);
            ifStatementList.add(statement);
        }

        List<Statement> elseStatementList = new ArrayList<>();

        if (ctx.elseStatement() != null) {

            for (QLParser.StatementContext StatementContext : ctx.elseStatement().statement()) {
                Statement statement = visitStatement(StatementContext);
                elseStatementList.add(statement);
            }

        }

        IfStatement ifStatement = new IfStatement(
                ifConditionExpression,
                ifStatementList,
                elseStatementList,
                this.ExtractMetaInformationFromContext(ctx)
        );

        return ifStatement;
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {

        Question question = new Question(
                ctx.label.getText().substring(1, ctx.label.getText().length() - 1),
                ctx.variableName.getText(),
                (TypeDeclaration) visit(ctx.dataType()),
                this.ExtractMetaInformationFromContext(ctx)
        );

        if (ctx.assignment != null) {
            question.setAssignedExpression((Expression) visit(ctx.assignment));
        }

        question.setMetaInformation(this.ExtractMetaInformationFromContext(ctx));

        return question;
    }

    // Data type declarations

    @Override
    public TypeDeclarationBoolean visitTypeDeclarationBoolean(QLParser.TypeDeclarationBooleanContext ctx) {
        return new TypeDeclarationBoolean(
                ctx.getText(),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    @Override
    public TypeDeclarationDecimal visitTypeDeclarationDecimal(QLParser.TypeDeclarationDecimalContext ctx) {
        return new TypeDeclarationDecimal(
                ctx.getText(),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    @Override
    public TypeDeclarationInteger visitTypeDeclarationInteger(QLParser.TypeDeclarationIntegerContext ctx) {
        return new TypeDeclarationInteger(
                ctx.getText(),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    @Override
    public TypeDeclarationString visitTypeDeclarationString(QLParser.TypeDeclarationStringContext ctx) {
        return new TypeDeclarationString(
                ctx.getText(),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    // Values

    @Override
    public Literal visitExpressionSingleValue(QLParser.ExpressionSingleValueContext ctx) {

        Expression.DataType type = Expression.DataType.STRING;

        if (ctx.BOOL_FALSE() != null || ctx.BOOL_TRUE() != null)
            type = Expression.DataType.BOOLEAN;

        if (ctx.DECIMAL() != null)
            type = Expression.DataType.DECIMAL;

        if (ctx.INTEGER() != null)
            type = Expression.DataType.INTEGER;

        return new Literal(
                ctx.value.getText().substring(1, ctx.value.getText().length() - 1),
                type,
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    // References

    @Override
    public ASTNode visitExpressionParenthesises(QLParser.ExpressionParenthesisesContext ctx) {
        return visit(ctx.subExpression);
    }

    @Override
    public VariableReference visitExpressionVariableReference(QLParser.ExpressionVariableReferenceContext ctx) {
        return new VariableReference(
                ctx.variableReference.getText(),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    @Override
    public Negation visitExpressionNegation(QLParser.ExpressionNegationContext ctx) {
        return new Negation(
                (Expression) visit(ctx.subExpression),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    // Arithmetic expressions

    @Override
    public Minus visitExpressionArithmeticMinus(QLParser.ExpressionArithmeticMinusContext ctx) {
        return new Minus(
                (Expression) visit(ctx.subExpression),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    @Override
    public Multiplication visitExpressionArithmeticMultiplication(QLParser.ExpressionArithmeticMultiplicationContext ctx) {
        return new Multiplication(
                (Expression) visit(ctx.lhs),
                (Expression) visit(ctx.rhs),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    @Override
    public Division visitExpressionArithmeticDivision(QLParser.ExpressionArithmeticDivisionContext ctx) {
        return new Division(
                (Expression) visit(ctx.lhs),
                (Expression) visit(ctx.rhs),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    @Override
    public Addition visitExpressionArithmeticAddition(QLParser.ExpressionArithmeticAdditionContext ctx) {
        return new Addition(
                (Expression) visit(ctx.lhs),
                (Expression) visit(ctx.rhs),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    @Override
    public Subtraction visitExpressionArithmeticSubtraction(QLParser.ExpressionArithmeticSubtractionContext ctx) {
        return new Subtraction(
                (Expression) visit(ctx.lhs),
                (Expression) visit(ctx.rhs),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    // Expressions comparisons

    @Override
    public GreaterThan visitExpressionComparisionGreaterThan(QLParser.ExpressionComparisionGreaterThanContext ctx) {
        return new GreaterThan(
                (Expression) visit(ctx.lhs),
                (Expression) visit(ctx.rhs),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    @Override
    public GreaterEqual visitExpressionComparisionGreaterEqual(QLParser.ExpressionComparisionGreaterEqualContext ctx) {
        return new GreaterEqual(
                (Expression) visit(ctx.lhs),
                (Expression) visit(ctx.rhs),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    @Override
    public LessThan visitExpressionComparisionLessThan(QLParser.ExpressionComparisionLessThanContext ctx) {
        return new LessThan(
                (Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    @Override
    public LessEqual visitExpressionComparisionLessEqual(QLParser.ExpressionComparisionLessEqualContext ctx) {
        return new LessEqual(
                (Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    @Override
    public Equal visitExpressionComparisionEqual(QLParser.ExpressionComparisionEqualContext ctx) {
        return new Equal(
                (Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    @Override
    public NotEqual visitExpressionComparisionNotEqual(QLParser.ExpressionComparisionNotEqualContext ctx) {
        return new NotEqual(
                (Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    // Binary logical operations

    @Override
    public LogicalAnd visitExpressionLogicalAnd(QLParser.ExpressionLogicalAndContext ctx) {
        return new LogicalAnd(
                (Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    @Override
    public LogicalOr visitExpressionLogicalOr(QLParser.ExpressionLogicalOrContext ctx) {
        return new LogicalOr(
                (Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs),
                this.ExtractMetaInformationFromContext(ctx)
        );
    }

    private ASTNode.MetaInformation ExtractMetaInformationFromContext(ParserRuleContext ctx) {
        return new ASTNode.MetaInformation(
                ctx.start.getLine(),
                ctx.stop.getLine(),
                ctx.start.getCharPositionInLine() + 1,
                ctx.getText()
        );
    }
}
