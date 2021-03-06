import analysis.SymbolTable;
import evaluation.ExpressionEvaluator;
import evaluation.value.Value;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import model.expression.Expression;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeThat;

@RunWith(JUnitQuickcheck.class)
public class ExpressionEvaluationTest {

    private static double DELTA = 0.001;

    // Prevents E notation to be in a double's string representation, which would be parsed incorrectly
    public String doubleString(double value) {
        return new BigDecimal(value).toPlainString();
    }

    public Value evaluateExpression(String expressionString) {
        ANTLRTester tester = new ANTLRTester(expressionString);
        Expression expression = tester.visitor.visit(tester.parser.expression());

        SymbolTable symbolTable = new SymbolTable();
        ExpressionEvaluator interpreterVisitor = new ExpressionEvaluator(symbolTable);

        return interpreterVisitor.visit(expression);
    }

    // Sum

    @Property
    public void ExpressionEvaluationSum(int left, int right) {
        Value result = evaluateExpression(left + " + " + right);
        assertEquals(Integer.valueOf(left + right), result.getIntValue());
    }

    @Property
    public void ExpressionEvaluationSum(double left, double right) {
        Value result = evaluateExpression(doubleString(left) + " + " + doubleString(right));
        assertEquals(left + right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void ExpressionEvaluationSum(int left, double right) {
        Value result = evaluateExpression(left + " + " + doubleString(right));
        assertEquals(left + right, result.getDecimalValue(), DELTA);
    }

    // Subtract

    @Property
    public void ExpressionEvaluationSub(int left, int right) {
        Value result = evaluateExpression(left + " - " + right);
        assertEquals(Integer.valueOf(left - right), result.getIntValue());
    }

    @Property
    public void ExpressionEvaluationSub(double left, double right) {
        Value result = evaluateExpression(doubleString(left) + " - " + doubleString(right));
        assertEquals(left - right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void ExpressionEvaluationSub(int left, double right) {
        Value result = evaluateExpression(left + " - " + doubleString(right));
        assertEquals(left - right, result.getDecimalValue(), DELTA);
    }

    // Multiply

    @Property
    public void ExpressionEvaluationMul(int left, int right) {
        Value result = evaluateExpression(left + " * " + right);
        assertEquals(Integer.valueOf(left * right), result.getIntValue());
    }

    @Property
    public void ExpressionEvaluationMul(double left, double right) {
        Value result = evaluateExpression(doubleString(left) + " * " + doubleString(right));
        assertEquals(left * right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void ExpressionEvaluationMul(int left, double right) {
        Value result = evaluateExpression(left + " * " + doubleString(right));
        assertEquals(left * right, result.getDecimalValue(), DELTA);
    }

    // Divide

    @Property
    public void ExpressionEvaluationDiv(int left, int right) {
        assumeThat(right, not(equalTo(0)));
        Value result = evaluateExpression(left + " / " + right);
        assertEquals(Integer.valueOf(left / right), result.getIntValue());
    }

    @Property
    public void ExpressionEvaluationDiv(double left, double right) {
        assumeThat(right, not(equalTo(0)));
        Value result = evaluateExpression(doubleString(left) + " / " + doubleString(right));
        assertEquals(left / right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void ExpressionEvaluationDiv(int left, double right) {
        assumeThat(right, not(equalTo(0)));
        Value result = evaluateExpression(left + " / " + doubleString(right));
        assertEquals(left / right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void ExpressionEvaluationNeg(int i) {
        Value result = evaluateExpression("-" + i);
        assertEquals(Integer.valueOf(-1 * i), result.getIntValue());
    }

}
