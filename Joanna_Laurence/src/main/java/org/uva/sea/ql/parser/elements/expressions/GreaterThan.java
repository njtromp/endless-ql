package org.uva.sea.ql.parser.elements.expressions;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.nodeTypes.BinaryOperator;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.visitor.IASTVisitor;

public class GreaterThan extends BinaryOperator {
    public GreaterThan(Token token, ASTNode leftHandSide, ASTNode rightHandSide) {
        super(token, leftHandSide, rightHandSide);
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
