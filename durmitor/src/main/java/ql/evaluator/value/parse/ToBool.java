package ql.evaluator.value.parse;

import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.visitors.interfaces.ValueVisitor;

public class ToBool implements ValueVisitor {

    @Override
    public Literal<?> visit(BoolLiteral value) {
        return value;
    }

    @Override
    public Literal<?> visit(StrLiteral value) {
        return new BoolLiteral(value.getValue().trim().length() > 0);
    }

    @Override
    public Literal<?> visit(IntLiteral value) {
        return new BoolLiteral(value.getValue() > 0);
    }

    @Override
    public Literal<?> visit(DecimalLiteral value) {
        return new BoolLiteral(value.getValue() > 0);
    }

    @Override
    public Literal<?> visit(MoneyLiteral value) {
        return new BoolLiteral(Double.valueOf(value.getValue()) > 0);
    }

    @Override
    public Literal<?> visit(DateLiteral value) {
        return new UndefinedLiteral();
    }

    @Override
    public Literal<?> visit(UndefinedLiteral value) {
        return new BoolLiteral(false);
    }
}
