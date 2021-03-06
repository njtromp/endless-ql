package org.uva.sea.qls.parser.elements.style;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.elements.types.Int;
import org.uva.sea.qls.parser.visitor.IStyleASTVisitor;

public class Width extends StyleSpecification {

    private int width;

    public Width(Token token, String width) {
        super(token);
        this.width = Integer.parseInt(width);
    }

    public int getWidth() {
        return width;
    }

    @Override
    public <T> T accept(IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
