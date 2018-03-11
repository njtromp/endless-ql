package nl.uva.se.sc.niro.model.ql.expressions.answers

import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions.BasicArithmetics.DecAnswerCanDoBasicArithmetics._
import nl.uva.se.sc.niro.model.ql.expressions.Orderings.DecAnswerCanDoOrderings._

final case class DecimalAnswer(possibleValue: Option[BigDecimal]) extends Answer {

  type T = BigDecimal

  def applyBinaryOperator(operator: Operator, that: Answer): Answer = that match {
    case that: DecimalAnswer =>
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
    case _ => throw new IllegalArgumentException(s"Can't perform operation: $this $operator $that")
  }

  def applyUnaryOperator(operator: Operator): Answer = operator match {
    case Sub => DecimalAnswer(possibleValue.map(-_))
    case _   => throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
  }
}

object DecimalAnswer {
  def apply() = new DecimalAnswer(None)
  def apply(value: BigDecimal) = new DecimalAnswer(Some(value))
}
