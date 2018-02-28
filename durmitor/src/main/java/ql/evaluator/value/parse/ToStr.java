package ql.evaluator.value.parse;

import ql.evaluator.value.Bool;
import ql.evaluator.value.Date;
import ql.evaluator.value.Decimal;
import ql.evaluator.value.Int;
import ql.evaluator.value.Money;
import ql.evaluator.value.Str;
import ql.evaluator.value.Undefined;
import ql.evaluator.value.Value;
import ql.visitors.interfaces.ValueVisitor;

public class ToStr implements ValueVisitor {

    @Override
    public Value<?> visit(Bool value) {
        return new Str(String.valueOf(value.getValue()));
    }

    @Override
    public Value<?> visit(Str value) {
        return value;
    }

    @Override
    public Value<?> visit(Int value) {
        return new Str(String.valueOf(value.getValue()));
    }

    @Override
    public Value<?> visit(Decimal value) {
        return new Str(String.valueOf(value.getValue()));
    }

    @Override
    public Value<?> visit(Money value) {
        return new Str(String.valueOf(value.getValue()));
    }

    @Override
    public Value<?> visit(Date value) {
        return new Str(String.valueOf(value.getValue()));
    }

    @Override
    public Value<?> visit(Undefined value) {
        return value;
    }
}
