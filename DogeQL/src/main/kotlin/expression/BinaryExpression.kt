package expression

import data.value.BaseSymbolValue
import expression.operation.BinaryOperation
import expression.visitor.evaluation.EvaluationVisitor

class BinaryExpression(
        val left: Expression,
        val right: Expression,
        val operation: BinaryOperation
) : Expression {

    override fun accept(visitor: EvaluationVisitor): BaseSymbolValue {
        return visitor.visit(this)
    }

}