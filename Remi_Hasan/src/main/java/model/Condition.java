package model;

import expression.Expression;

import java.util.ArrayList;

// TODO rename to something like conditionblock for consistency
public class Condition extends BlockElement {
    public Expression condition;
    public ArrayList<BlockElement> elements;

    // TODO force condition to be of evaluated type ExpressionVariableBoolean
    public Condition(Expression condition, ArrayList<BlockElement> elements){
        this.condition = condition;
        this.elements = elements;
    }

    @Override
    public boolean isCondition() {
        return true;
    }
}