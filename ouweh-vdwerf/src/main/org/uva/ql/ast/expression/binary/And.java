package main.org.uva.ql.ast.expression.binary;

import main.org.uva.ql.ast.expression.Expression;
import main.org.uva.ql.visitor.ExpressionVisitor;

public class And extends BinaryOperation {

    public And(Expression left, Expression right){
        super(left,right);
    }

    @Override
    public String toString() {
        return String.format("(%s AND %s)", this.getLeft(), this.getRight());
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
