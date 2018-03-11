package ast.visitors.evaluators;

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
import ast.visitors.AbstractASTTraverse;

import java.util.HashMap;

public class ExpressionEvaluator extends AbstractASTTraverse<ExpressionResult> {

    HashMap<String, ExpressionResult> variablesValues;

    public ExpressionEvaluator(HashMap<String, ExpressionResult> variablesValues) {
        this.variablesValues = variablesValues;
    }

    @Override
    public ExpressionResult visit(Negation negation) {
        ExpressionResult result = negation.getExpression().accept(this);
        return result.negate();
    }

    @Override
    public ExpressionResult visit(Minus minus) {
        ExpressionResult result = minus.getExpression().accept(this);
        return result.negate();
    }

    @Override
    public ExpressionResult visit(Addition addition) {
        ExpressionResult lhs = addition.getLeftSide().accept(this);
        ExpressionResult rhs = addition.getRightSide().accept(this);
        return lhs.add(rhs);
    }

    @Override
    public ExpressionResult visit(Subtraction subtraction) {
        ExpressionResult lhs = subtraction.getLeftSide().accept(this);
        ExpressionResult rhs = subtraction.getRightSide().accept(this);
        return lhs.min(rhs);
    }

    @Override
    public ExpressionResult visit(Division division) {
        ExpressionResult lhs = division.getLeftSide().accept(this);
        ExpressionResult rhs = division.getRightSide().accept(this);
        return lhs.divide(rhs);
    }

    @Override
    public ExpressionResult visit(Multiplication multiplication) {
        ExpressionResult lhs = multiplication.getLeftSide().accept(this);
        ExpressionResult rhs = multiplication.getRightSide().accept(this);
        return lhs.multiply(rhs);
    }

    @Override
    public ExpressionResult visit(Equal equal) {
        equal.getLeftSide().accept(this);
        equal.getRightSide().accept(this);
        return null;
    }

    @Override
    public ExpressionResult visit(GreaterEqual greaterEqual) {
        greaterEqual.getLeftSide().accept(this);
        greaterEqual.getRightSide().accept(this);
        return null;
    }

    @Override
    public ExpressionResult visit(GreaterThan greaterThan) {
        greaterThan.getLeftSide().accept(this);
        greaterThan.getRightSide().accept(this);
        return null;
    }

    @Override
    public ExpressionResult visit(LessEqual lessEqual) {
        lessEqual.getLeftSide().accept(this);
        lessEqual.getRightSide().accept(this);
        return null;
    }

    @Override
    public ExpressionResult visit(LessThan lessThan) {
        lessThan.getLeftSide().accept(this);
        lessThan.getRightSide().accept(this);
        return null;
    }

    @Override
    public ExpressionResult visit(NotEqual notEqual) {
        notEqual.getLeftSide().accept(this);
        notEqual.getRightSide().accept(this);
        return null;
    }

    @Override
    public ExpressionResult visit(LogicalAnd logicalAnd) {
        ExpressionResult lhs = logicalAnd.getLeftSide().accept(this);
        ExpressionResult rhs = logicalAnd.getRightSide().accept(this);
        return lhs.and(rhs);
    }

    @Override
    public ExpressionResult visit(LogicalOr logicalOr) {
        ExpressionResult lhs = logicalOr.getLeftSide().accept(this);
        ExpressionResult rhs = logicalOr.getRightSide().accept(this);
        return lhs.or(rhs);
    }

    @Override
    public ExpressionResult visit(VariableReference variableReference) {
        return variablesValues.get(variableReference.getName());
    }

    @Override
    public ExpressionResult visit(Literal literal) {
        return ExpressionResult.createExpressionResult(literal.getType(), literal.getValue());
    }

}
