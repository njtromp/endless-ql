package expression.binary;

import expression.Expression;
import expression.variable.ExpressionVariable;

public class ExpressionArithmeticSum extends ExpressionArithmetic {

    public ExpressionArithmeticSum(Expression left, Expression right) {
        super(left, right, "+");
    }

    @Override
    public ExpressionVariable evaluate() {
        ExpressionVariable leftEvaluated = this.left.evaluate();
        ExpressionVariable rightEvaluated = this.right.evaluate();
        return leftEvaluated.sum(rightEvaluated);
    }
}