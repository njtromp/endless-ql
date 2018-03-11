package nl.uva.se.sc.niro.gui.factory

import nl.uva.se.sc.niro.model.gui.GUIQuestion
import nl.uva.se.sc.niro.model.ql.Question
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers.Answer
import org.apache.logging.log4j.scala.Logging

object GUIQuestionFactory extends Logging {

  def makeGUIQuestion(visible: Expression, question: Question): GUIQuestion = {
    GUIQuestion(question.id, question.answerType, question.label, isReadOnly(question.expression), visible)
  }

  private def isReadOnly(expression: Expression): Boolean =
    expression match {
      case answer: Answer => answer.possibleValue.isDefined
      case _              => true
    }
}