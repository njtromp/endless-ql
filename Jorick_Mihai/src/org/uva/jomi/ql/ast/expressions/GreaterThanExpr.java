package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;

public class GreaterThanExpr extends BinaryExpr {
	
	public GreaterThanExpr(Expr left, QLToken operator, Expr right) {
		super(left, operator, right);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}