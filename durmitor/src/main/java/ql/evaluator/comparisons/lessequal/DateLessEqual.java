package ql.evaluator.comparisons.lessequal;

import ql.evaluator.AbstractEvaluator;
import ql.evaluator.value.Bool;
import ql.evaluator.value.Date;
import ql.evaluator.value.Decimal;
import ql.evaluator.value.Int;
import ql.evaluator.value.Money;
import ql.evaluator.value.Str;
import ql.evaluator.value.Undefined;
import ql.evaluator.value.Value;

public class DateLessEqual extends AbstractEvaluator<Date> {

    public DateLessEqual(Date firstOperand) {
        super(firstOperand);
    }

    @Override
    public Value<?> visit(Bool secondOperand) {
        return new Undefined();
    }

    @Override
    public Value<?> visit(Str secondOperand) {
        return new Undefined();
    }

    @Override
    public Value<?> visit(Int secondOperand) {
        return new Undefined();
    }

    @Override
    public Value<?> visit(Decimal secondOperand) {
        return new Undefined();
    }

    @Override
    public Value<?> visit(Money secondOperand) {
        return new Undefined();
    }

    @Override
    public Value<?> visit(Date secondOperand) {
        boolean isLessEqual =   firstOperand.getValue().isBefore(secondOperand.getValue()) ||
                                firstOperand.getValue().isEqual(secondOperand.getValue());
        return new Bool(isLessEqual);
    }

    @Override
    public Value<?> visit(Undefined secondOperand) {
        return new Undefined();
    }
}
