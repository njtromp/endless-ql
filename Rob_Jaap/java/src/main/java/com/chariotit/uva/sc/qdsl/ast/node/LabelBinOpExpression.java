package com.chariotit.uva.sc.qdsl.ast.node;

import com.chariotit.uva.sc.qdsl.ast.node.operator.Operator;
import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class LabelBinOpExpression extends Expression {

    private Label label;
    private Operator operator;
    private Expression expression;

    public LabelBinOpExpression(Label label, Operator operator, Expression expression, Integer
            lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.label = label;
        this.operator = operator;
        this.expression = expression;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        label.acceptVisitor(visitor);
        operator.acceptVisitor(visitor);
        expression.acceptVisitor(visitor);

        visitor.visitLabelBinOpExpression(this);
    }
}
