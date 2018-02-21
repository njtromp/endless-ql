package expression;

public class ExpressionArithmeticMultiply extends ExpressionArithmetic {

    public ExpressionArithmeticMultiply(Expression left, Expression right) {
        super(left, right, "*");
    }

    @Override
    public ExpressionVariable evaluate() {
        ExpressionVariable leftEvaluated = this.left.evaluate();
        ExpressionVariable rightEvaluated = this.right.evaluate();
        return leftEvaluated.multiply(rightEvaluated);
    }
}
