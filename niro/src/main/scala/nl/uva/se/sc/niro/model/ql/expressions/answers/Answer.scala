package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql.Operator
import nl.uva.se.sc.niro.model.ql.expressions.Expression

abstract class Answer extends Expression {

  type T

  val possibleValue: Option[T]

  def applyUnaryOperator(operator: Operator): Answer

  def applyBinaryOperator(operator: Operator, right: Answer): Answer

  def isTrue: Boolean = false

  def combine[R](that: Answer)(f: (T, that.T) => R): Option[R] =
    for {
      thisValue <- possibleValue
      thatValue <- that.possibleValue
    } yield f(thisValue, thatValue)
}

object Answer {
  def apply(answerType: String): Answer = answerType match {
    case "boolean" => BooleanAnswer()
    case "integer" => IntegerAnswer()
    case "string"  => StringAnswer()
    case "decimal" => DecimalAnswer()
    case "money"   => MoneyAnswer()
    case "date"    => DateAnswer()
    case _         => throw new IllegalArgumentException(s"Unsupported answer type: $answerType")
  }
}
