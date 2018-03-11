package nl.uva.se.sc.niro.model.ql

import nl.uva.se.sc.niro.Evaluator
import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers.Answer

sealed trait Statement

case class Question(id: String, label: String, answerType: AnswerType, expression: Expression) extends Statement

case class Conditional(predicate: Expression, thenStatements: Seq[Statement]) extends Statement

object Statement {

  def collectAllQuestions(statements: Seq[Statement]): Seq[Question] = {
    statements.flatMap {
      case q: Question    => Seq(q)
      case c: Conditional => collectAllQuestions(c.thenStatements)
    }
  }

  def collectAllConditionals(statements: Seq[Statement]): Seq[Conditional] = {
    statements.flatMap {
      case _: Question    => Seq.empty
      case c: Conditional => Seq(c) ++ collectAllConditionals(c.thenStatements)
    }
  }

  def collectAllVisibleQuestions(statements: Seq[Statement], symbolTable: SymbolTable): Seq[Question] = {
    statements.flatMap {
      case q: Question => Seq(q)
      case c: Conditional if Evaluator.evaluateExpression(c.predicate, symbolTable, Map.empty).isTrue =>
        collectAllVisibleQuestions(c.thenStatements, symbolTable)
    }
  }

  def collectAllReferences(questions: Seq[Question]): Seq[Reference] = {
    questions.flatMap(question => Expression.collectAllReferences(question.expression))
  }

  def saveAnswer(questionId: String, answer: Answer, statements: Seq[Statement]): Seq[Statement] = {
    statements.flatMap {
      case q: Question if q.id == questionId => Seq(q.copy(expression = answer))
      case q: Question                       => Seq(q)
      case c: Conditional                    => Seq(c.copy(thenStatements = saveAnswer(questionId, answer, c.thenStatements)))
    }
  }
}
