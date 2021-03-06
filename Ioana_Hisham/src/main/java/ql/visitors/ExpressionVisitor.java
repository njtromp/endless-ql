package ql.visitors;

import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.Identifier;
import ql.ast.expressions.literals.StringLiteral;

public interface ExpressionVisitor<T> {
    public T visit(Addition addition);
    public T visit(Division division);
    public T visit(Equal equal);
    public T visit(GreaterThan greaterThan);
    public T visit(GreaterThanOrEqual greaterThanOrEqual);
    public T visit(LessThan lessThan);
    public T visit(LessThanOrEqual lessThanOrEqual);
    public T visit(LogicalAnd logicalAnd);
    public T visit(LogicalOr logicalOr);
    public T visit(Multiplication multiplication);
    public T visit(NotEqual notEqual);
    public T visit(Subtraction subtraction);
    public T visit(Identifier identifier);
    public T visit(StringLiteral stringLiteral);
}
