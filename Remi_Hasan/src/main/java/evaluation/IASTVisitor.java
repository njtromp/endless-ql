package evaluation;

import model.expression.Expression;
import model.expression.ExpressionIdentifier;
import model.expression.binary.*;
import model.expression.unary.ExpressionUnaryNeg;
import model.expression.unary.ExpressionUnaryNot;
import model.expression.variable.*;

public interface IASTVisitor<T> {
    T visit(Expression expression);

    T visit(ExpressionArithmeticDivide expression);

    T visit(ExpressionArithmeticMultiply expression);

    T visit(ExpressionArithmeticSubtract expression);

    T visit(ExpressionArithmeticSum expression);

    T visit(ExpressionComparisonEq expression);

    T visit(ExpressionComparisonGE expression);

    T visit(ExpressionComparisonGT expression);

    T visit(ExpressionComparisonLE expression);

    T visit(ExpressionComparisonLT expression);

    T visit(ExpressionLogicalAnd expression);

    T visit(ExpressionLogicalOr expression);

    T visit(ExpressionUnaryNot expression);

    T visit(ExpressionUnaryNeg expression);

    T visit(ExpressionVariableBoolean expression);

    T visit(ExpressionVariableDate expression);

    T visit(ExpressionVariableInteger expression);

    T visit(ExpressionVariableDecimal expression);

    T visit(ExpressionVariableMoney expression);

    T visit(ExpressionVariableString expression);

    T visit(ExpressionVariableUndefined expression);

    T visit(ExpressionIdentifier expression);
}