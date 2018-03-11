package nl.uva.se.sc.niro.model.ql

import nl.uva.se.sc.niro.model.ql.expressions.Expression

object SymbolTable {
  def createSymbolTable(qLForm: QLForm): Map[String, Symbol] = {
    Statement
      .collectAllQuestions(qLForm.statements)
      .map(q => q.id -> Symbol(q.answerType, q.expression))
      .toMap
  }

  type SymbolTable = Map[String, Symbol]
}

case class Symbol(answerType: AnswerType, expression: Expression)
