package expression;

public class ExpressionLT extends Expression<Boolean>{

    private final Expression left;
    private final Expression right;

    public ExpressionLT(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean evaluate() {
        if(isEvaluable()){
            return (double)this.left.evaluate() < (double)this.right.evaluate();
        }
        return null;
    }

    @Override
    public boolean isEvaluable() {
        return this.left.isEvaluable() && this.right.isEvaluable();
    }

    @Override
    public String toString() {
        return left.toString() + " < " + right.toString();
    }
}