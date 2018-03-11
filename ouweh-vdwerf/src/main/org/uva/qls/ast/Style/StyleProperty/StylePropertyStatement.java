package org.uva.qls.ast.Style.StyleProperty;


import org.uva.ql.evaluator.value.Value;

public class StylePropertyStatement extends StyleProperty {

    private String property;
    private Value value;

    public StylePropertyStatement(String property, Value value){
        this.property = property;
        this.value = value;
    }
}
