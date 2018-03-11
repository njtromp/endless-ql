package nl.uva.se.sc.niro.model.ql.expressions

import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, BooleanAnswer }

import scala.language.implicitConversions

object Logicals {
  trait BooleanAnswerCanDoLogicals extends Logicals[BooleanAnswer] {
    def and(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ && _))
    def or(x: BooleanAnswer, y: BooleanAnswer): BooleanAnswer = BooleanAnswer(x.combine(y)(_ || _))
    def neg(x: BooleanAnswer): BooleanAnswer = BooleanAnswer(x.possibleValue.map(!_))
  }
  implicit object BooleanAnswerCanDoLogicals extends BooleanAnswerCanDoLogicals
}

trait Logicals[SubType <: Answer] {
  def and(x: SubType, y: SubType): BooleanAnswer
  def or(x: SubType, y: SubType): BooleanAnswer
  def neg(x: SubType): BooleanAnswer

  class Ops(left: SubType) {
    def &&(right: SubType): BooleanAnswer = and(left, right)
    def ||(right: SubType): BooleanAnswer = or(left, right)
    def unary_!(): BooleanAnswer = neg(left)
  }

  implicit def mkLogicalsOp(left: SubType): Ops = new Ops(left)
}
