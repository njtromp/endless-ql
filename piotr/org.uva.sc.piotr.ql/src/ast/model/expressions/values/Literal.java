package ast.model.expressions.values;

import ast.model.expressions.Expression;
import ast.visitors.ASTNodeVisitor;

public class Literal extends Expression {

    private String value;
    private Expression.DataType type;

    public Literal(String value, Expression.DataType type, MetaInformation metaInformation) {
        super(metaInformation);
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Expression.DataType getType() {
        return type;
    }

    public void setType(Expression.DataType type) {
        this.type = type;
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
