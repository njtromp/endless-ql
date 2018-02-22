package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.QLValueEvaluator;

import java.math.BigDecimal;

public class IntValue extends Value {
    private int intValue;

    public IntValue(int intValue) {
        this.intValue = intValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public Value add(IntValue value) {
        return new IntValue(this.intValue + value.getIntValue());
    }

    public Value add(MoneyValue value) {
        return new MoneyValue(value.getCurrency(), value.getAmount().add(new BigDecimal(this.intValue)));
    }

    public Value add(DecimalValue value) {
        return new DecimalValue(value.getDecimalValue() + this.intValue);
    }

    public Value add(BooleanValue value) {
        return new ErrorValue("Cannot add boolean to integer");
    }

    public Value add(DateValue value) {
        return new ErrorValue("Cannot add date to integer");
    }

    public Value add(StringValue value) {
        return new ErrorValue("Cannot add string to integer");
    }

    @Override
    public <T> T accept(QLValueEvaluator<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Value negate() {
        return new IntValue(this.intValue * (-1));
    }

    @Override
    public Value not() {
        return new ErrorValue("Not operator cannot be applied on a integer value");
    }

    @Override
    public Value positive() {
        return new IntValue(this.intValue);
    }
}