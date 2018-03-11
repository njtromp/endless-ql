package ast.model.expressions.values;

import ast.model.expressions.Expression;
import ast.visitors.ASTNodeVisitor;

public class VariableReference extends Expression {

    private String name;

    public VariableReference(String name, MetaInformation metaInformation) {
        super(metaInformation);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
