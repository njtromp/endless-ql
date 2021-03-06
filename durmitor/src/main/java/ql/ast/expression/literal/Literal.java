package ql.ast.expression.literal;

import ql.ast.expression.Primary;
import ql.ast.type.Money;
import ql.ast.type.Type;
import ql.evaluator.Operations;
import ql.visitors.interfaces.ValueVisitable;

public abstract class Literal<T>  extends Primary implements ValueVisitable, Operations<Literal<?>> {

    public abstract T getValue();
    
    public static Literal<?> create(Type type) {
        return create(type,null);
    }
    
    public static Literal<?> create(Type type, String value) {
        
        if(type.isBoolean()) {
            return new BoolLiteral(value);
        } else if(type.isString()) {
            return new StrLiteral(value);
        } else if(type.isInteger()) {
            return new IntLiteral(value);
        } else if(type.isDecimal()) {
            return new DecimalLiteral(value);
        } else if(type.isMoney()) {
            return new MoneyLiteral(((Money) type).getCurrency(),value);
        } else if(type.isDate()) {
            return new DateLiteral(value);
        } else {
            return new UndefinedLiteral();
        }
    }
    
    public boolean isUndefined() {
        return false;
    }
    
    @Override
    public Literal<?> evaluate() {
        return this;
    }
    
    public Literal<?> negation() {
        return new UndefinedLiteral();
    }
    
    public Literal<?> negative() {
        return new UndefinedLiteral();
    }
    
    public Literal<?> positive() {
        return new UndefinedLiteral();
    }
    
    public Literal<?> add(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }
    
    public Literal<?> subtract(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }
    
    public Literal<?> multiply(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }
    
    public Literal<?> divide(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }

    public Literal<?> less(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }

    public Literal<?> lessEqual(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }

    public Literal<?> greater(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }

    public Literal<?> greaterEqual(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }

    public Literal<?> equal(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }

    public Literal<?> notEqual(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }

    public Literal<?> and(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }

    public Literal<?> or(Literal<?> secondOperand) {
        return new UndefinedLiteral();
    }
}
