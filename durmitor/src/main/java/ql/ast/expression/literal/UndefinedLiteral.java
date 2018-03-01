package ql.ast.expression.literal;

import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;
import ql.visitors.interfaces.ValueVisitor;

public class UndefinedLiteral extends Literal<String> {

    private final String value;
    
    public UndefinedLiteral() { 
        this.value = null;
    }
    
    @Override
    public String toString() {
        return this.value;
    }
    
    @Override
    public String getValue() {
        return value;
    }
    
    @Override
    public Type getType() {
        return new ql.ast.type.Undefined();
    }
    
    @Override
    public Literal<?> accept(ValueVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }
}