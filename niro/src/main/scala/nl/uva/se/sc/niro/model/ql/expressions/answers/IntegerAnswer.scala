package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions.BasicArithmetics.IntAnswerCanDoBasicArithmetics._
import nl.uva.se.sc.niro.model.ql.expressions.Orderings.IntAnswerCanDoOrderings._

final case class IntegerAnswer(possibleValue: Option[Int]) extends Answer {

  type T = Int

  def toDecAnswer = DecimalAnswer(possibleValue.map(BigDecimal(_)))

  def applyBinaryOperator(operator: Operator, that: Answer): Answer = that match {
    case that: IntegerAnswer =>
      operator match {
        case Add => this + that
        case Sub => this - that
        case Mul => this * that
        case Div => this / that
        case Lt  => this < that
        case Lte => this <= that
        case Gte => this >= that
        case Gt  => this > that
        case Ne  => this !== that
        case Eq  => this === that
        case _   => throw new UnsupportedOperationException(s"Unsupported operator: $operator")
      }
    case that: DecimalAnswer => toDecAnswer.applyBinaryOperator(operator, that)
    case _                   => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  def applyUnaryOperator(operator: Operator): Answer = operator match {
    case Sub => -this
    case _   => throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
  }
}

object IntegerAnswer {
  def apply() = new IntegerAnswer(None)
  def apply(value: Int) = new IntegerAnswer(Some(value))
}
