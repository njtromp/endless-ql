package gui.model;

import ast.model.expressions.Expression;

public class FormFieldModel {

    private String label;
    private String variableName;
    private Expression.DataType variableType;
    private Expression visibilityCondition;
    private Expression valueExpression;
    private String value;

    public FormFieldModel(String label, String variableName, Expression.DataType variableType, Expression visibilityCondition, Expression valueExpression) {
        this.label = label;
        this.variableName = variableName;
        this.variableType = variableType;
        this.visibilityCondition = visibilityCondition;
        this.valueExpression = valueExpression;
    }

    public String getLabel() {
        return label;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Expression.DataType getVariableType() {
        return variableType;
    }

    public void setVariableType(Expression.DataType variableType) {
        this.variableType = variableType;
    }

    public Expression getVisibilityCondition() {
        return visibilityCondition;
    }

    public void setVisibilityCondition(Expression visibilityCondition) {
        this.visibilityCondition = visibilityCondition;
    }

    public Expression getValueExpression() {
        return valueExpression;
    }

    public void setValueExpression(Expression valueExpression) {
        this.valueExpression = valueExpression;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
