package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import model.expression.Expression;
import model.expression.binary.ExpressionLogicalAnd;
import model.expression.unary.ExpressionUnaryNot;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class VisitorCondition extends QLBaseVisitor<List<Question>> {

    private final Expression condition;

    VisitorCondition(Expression condition) {
        this.condition = condition;
    }

    @Override
    public List<Question> visitCondition(QLParser.ConditionContext ctx) {
        VisitorExpression visitorExpression = new VisitorExpression();
        Expression expression = visitorExpression.visit(ctx.expression());

        List<Question> questions = new ArrayList<>();

        // Chain nested conditional statements
        Expression trueExpression = new ExpressionLogicalAnd(ctx.getStart(), this.condition, expression);
        addQuestions(questions, ctx.conditionTrueBlock.statement(), trueExpression);

        if (ctx.conditionFalseBlock == null) {
            return questions;
        }

        // Else block, so negate condition (and again, chain nested conditional statements)
        Expression falseExpression = new ExpressionLogicalAnd(ctx.expression().getStart(),
                new ExpressionUnaryNot(ctx.expression().getStart(), expression), this.condition);
        addQuestions(questions, ctx.conditionFalseBlock.statement(), falseExpression);

        return questions;
    }

    private void addQuestions(List<Question> questions, List<QLParser.StatementContext> statements, Expression condition) {
        VisitorStatement visitorStatement = new VisitorStatement(condition);
        for (QLParser.StatementContext statementContext : statements) {
            questions.addAll(visitorStatement.visit(statementContext));
        }
    }

}