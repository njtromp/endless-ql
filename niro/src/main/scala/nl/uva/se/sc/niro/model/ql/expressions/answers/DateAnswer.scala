package nl.uva.se.sc.niro.model.ql.expressions.answers

import java.time.LocalDate

import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions.Orderings.DateAnswerCanDoOrderings._

final case class DateAnswer(possibleValue: Option[LocalDate]) extends Answer {

  type T = LocalDate

  def applyBinaryOperator(operator: Operator, that: Answer): Answer = that match {
    case that: DateAnswer =>
      operator match {
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

  def applyUnaryOperator(operator: Operator): Answer =
    throw new IllegalArgumentException(s"Can't perform operation: $operator $this")
}

object DateAnswer {
  def apply() = new DateAnswer(None)
  def apply(value: LocalDate) = new DateAnswer(Some(value))
  def apply(value: String): DateAnswer = if (value.isEmpty) DateAnswer() else DateAnswer(LocalDate.parse(value))
}
