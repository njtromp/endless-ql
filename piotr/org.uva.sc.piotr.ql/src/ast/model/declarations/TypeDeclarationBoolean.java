package ast.model.declarations;

import ast.model.expressions.Expression;
import ast.visitors.ASTNodeVisitor;

public class TypeDeclarationBoolean extends TypeDeclaration {
    public TypeDeclarationBoolean(String identifier, MetaInformation metaInformation) {
        super(identifier, metaInformation);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Expression.DataType toDataType() {
        return Expression.DataType.BOOLEAN;
    }
}
