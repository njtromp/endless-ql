package org.uva.sea.ql.parser.elements.expressions;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.nodeTypes.BinaryOperator;
import org.uva.sea.ql.visitor.IASTVisitor;

public class LessOrEqual extends BinaryOperator {
    public LessOrEqual(Token token, ASTNode lhs, ASTNode rhs) {
        super(token, lhs, rhs);
    }

    /**
     * @return True or false is returned
     */
    public Type getType() {
        return new Type(NodeType.BOOLEAN);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
