package Nodes.Operator;

import Nodes.Term.Boolean;
import Nodes.Term.Float;

public class GreaterEq extends Operator {
    public Boolean calculate(Float left, Float right) {
        return new Boolean(left.getFloat() >= right.getFloat());
    }
}
