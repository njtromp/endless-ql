package nl.uva.se.sc.niro.model.ql.expressions

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, DecimalAnswer, IntegerAnswer }

import scala.language.implicitConversions

object BasicArithmetics {
  trait IntAnswerCanDoBasicArithmetics extends BasicArithmetics[IntegerAnswer] {
    def plus(x: IntegerAnswer, y: IntegerAnswer): IntegerAnswer = IntegerAnswer(x.combine(y)(_ + _))
    def minus(x: IntegerAnswer, y: IntegerAnswer): IntegerAnswer = IntegerAnswer(x.combine(y)(_ - _))
    def times(x: IntegerAnswer, y: IntegerAnswer): IntegerAnswer = IntegerAnswer(x.combine(y)(_ * _))
    def div(x: IntegerAnswer, y: IntegerAnswer): IntegerAnswer = IntegerAnswer(x.combine(y)(_ / _))
    def negate(x: IntegerAnswer) = IntegerAnswer(x.possibleValue.map(-_))
  }
  implicit object IntAnswerCanDoBasicArithmetics extends IntAnswerCanDoBasicArithmetics

  trait DecAnswerCanDoBasicArithmetics extends BasicArithmetics[DecimalAnswer] {
    def plus(x: DecimalAnswer, y: DecimalAnswer): DecimalAnswer = DecimalAnswer(x.combine(y)(_ + _))
    def minus(x: DecimalAnswer, y: DecimalAnswer): DecimalAnswer = DecimalAnswer(x.combine(y)(_ - _))
    def times(x: DecimalAnswer, y: DecimalAnswer): DecimalAnswer = DecimalAnswer(x.combine(y)(_ * _))
    def div(x: DecimalAnswer, y: DecimalAnswer): DecimalAnswer = DecimalAnswer(x.combine(y)(_ / _))
    def negate(x: DecimalAnswer) = DecimalAnswer(x.possibleValue.map(-_))
  }
  implicit object DecAnswerCanDoBasicArithmetics extends DecAnswerCanDoBasicArithmetics
}

trait BasicArithmetics[SubType <: Answer] {
  def plus(x: SubType, y: SubType): SubType
  def minus(x: SubType, y: SubType): SubType
  def times(x: SubType, y: SubType): SubType
  def div(x: SubType, y: SubType): SubType
  def negate(x: SubType): SubType

  class Ops(left: SubType) {
    def +(right: SubType): SubType = plus(left, right)
    def -(right: SubType): SubType = minus(left, right)
    def *(right: SubType): SubType = times(left, right)
    def /(right: SubType): SubType = div(left, right)
    def unary_-(): SubType = negate(left)
  }

  implicit def mkNumericOps(left: SubType): Ops = new Ops(left)
}
