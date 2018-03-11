package nl.uva.se.sc.niro.model.ql.expressions

import nl.uva.se.sc.niro.model.ql.expressions.answers._

import scala.language.implicitConversions

object Orderings {
  trait IntAnswerCanDoOrderings extends Orderings[IntegerAnswer] {
    def lt(x: IntegerAnswer, y: IntegerAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ < _))
    def lte(x: IntegerAnswer, y: IntegerAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ <= _))
    def gte(x: IntegerAnswer, y: IntegerAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ >= _))
    def gt(x: IntegerAnswer, y: IntegerAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ > _))
    def neq(x: IntegerAnswer, y: IntegerAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ != _))
    def equ(x: IntegerAnswer, y: IntegerAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ == _))
  }
  implicit object IntAnswerCanDoOrderings extends IntAnswerCanDoOrderings

  trait DecAnswerCanDoOrderings extends Orderings[DecimalAnswer] {
    def lt(x: DecimalAnswer, y: DecimalAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ < _))
    def lte(x: DecimalAnswer, y: DecimalAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ <= _))
    def gte(x: DecimalAnswer, y: DecimalAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ >= _))
    def gt(x: DecimalAnswer, y: DecimalAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ > _))
    def neq(x: DecimalAnswer, y: DecimalAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ != _))
    def equ(x: DecimalAnswer, y: DecimalAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ == _))
  }
  implicit object DecAnswerCanDoOrderings extends DecAnswerCanDoOrderings

  trait BooleanAnswerCanDoOrderings extends Orderings[BooleanAnswer] {
    def lt(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ < _))
    def lte(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ <= _))
    def gte(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ >= _))
    def gt(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ > _))
    def neq(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ != _))
    def equ(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ == _))
  }
  implicit object BooleanAnswerCanDoOrderings extends BooleanAnswerCanDoOrderings

  trait StringAnswerCanDoOrderings extends Orderings[StringAnswer] {
    def lt(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ < _))
    def lte(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ <= _))
    def gte(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ >= _))
    def gt(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ > _))
    def neq(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ != _))
    def equ(x: StringAnswer, y: StringAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ == _))
  }
  implicit object StringAnswerCanDoOrderings extends StringAnswerCanDoOrderings

  trait DateAnswerCanDoOrderings extends Orderings[DateAnswer] {
    def lt(x: DateAnswer, y: DateAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_.isBefore(_)))
    def lte(x: DateAnswer, y: DateAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(!_.isAfter(_)))
    def gte(x: DateAnswer, y: DateAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(!_.isBefore(_)))
    def gt(x: DateAnswer, y: DateAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_.isAfter(_)))
    def neq(x: DateAnswer, y: DateAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(!_.isEqual(_)))
    def equ(x: DateAnswer, y: DateAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_.isEqual(_)))
  }
  implicit object DateAnswerCanDoOrderings extends DateAnswerCanDoOrderings
}

trait Orderings[SubType <: Answer] {
  def lt(x: SubType, y: SubType): BooleanAnswer
  def lte(x: SubType, y: SubType): BooleanAnswer
  def gte(x: SubType, y: SubType): BooleanAnswer
  def gt(x: SubType, y: SubType): BooleanAnswer
  def neq(x: SubType, y: SubType): BooleanAnswer
  def equ(x: SubType, y: SubType): BooleanAnswer

  class Ops(left: SubType) {
    def <(right: SubType): BooleanAnswer = lt(left, right)
    def <=(right: SubType): BooleanAnswer = lte(left, right)
    def >=(right: SubType): BooleanAnswer = gte(left, right)
    def >(right: SubType): BooleanAnswer = gt(left, right)
    def !==(right: SubType): BooleanAnswer = neq(left, right)
    def ===(right: SubType): BooleanAnswer = equ(left, right)
  }

  implicit def mkOrderingOps(left: SubType): Ops = new Ops(left)
}
