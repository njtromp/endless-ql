package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql.Operator

final case class MoneyAnswer(possibleValue: Option[BigDecimal]) extends Answer {

  type T = BigDecimal

  def applyUnaryOperator(unaryOperator: Operator): Answer = ???
  def applyBinaryOperator(binaryOperator: Operator, other: Answer): Answer = ???
}

object MoneyAnswer {
  def apply() = new MoneyAnswer(None)
  def apply(value: BigDecimal) = new MoneyAnswer(Some(value))
}
