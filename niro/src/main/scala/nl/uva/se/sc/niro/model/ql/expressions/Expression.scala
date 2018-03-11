package nl.uva.se.sc.niro.model.ql.expressions

import nl.uva.se.sc.niro.model.ql.Operator

abstract class Expression

final case class Reference(value: String) extends Expression

final case class BinaryOperation(binaryOperator: Operator, left: Expression, right: Expression) extends Expression

final case class UnaryOperation(unaryOperator: Operator, left: Expression) extends Expression

object Expression {
  def collectAllReferences(expression: Expression): Seq[Reference] = expression match {
    case r: Reference                       => Seq(r)
    case UnaryOperation(_, rightExpression) => collectAllReferences(rightExpression)
    case BinaryOperation(_, leftExpression, rightExpression) =>
      collectAllReferences(leftExpression) ++ collectAllReferences(rightExpression)
    case _ => Seq.empty
  }
}