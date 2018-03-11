package nl.uva.se.sc.niro

import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers.Answer

object Evaluator {

  type Dictionary = Map[String, Answer]

  def evaluate(qLForm: QLForm, dictionary: Dictionary): Dictionary = {
    qLForm.statements.flatMap(statement => evaluate(statement, qLForm.symbolTable, dictionary)).toMap
  }

  def evaluate(statement: Statement, symbolTable: SymbolTable, dictionary: Dictionary): Dictionary = {
    statement match {
      case q: Question    => evaluate(q, symbolTable, dictionary)
      case c: Conditional => evaluate(c, symbolTable, dictionary)
    }
  }

  def evaluate(question: Question, symbolTable: SymbolTable, dictionary: Dictionary): Dictionary = {
    val answer = evaluateExpression(question.expression, symbolTable: SymbolTable, dictionary)
    if (answer.possibleValue.isDefined) {
      dictionary + (question.id -> answer)
    } else
      dictionary - question.id
  }

  // Recursion is happening between evaluateStatement and evaluateConditional
  def evaluate(conditional: Conditional, symbolTable: SymbolTable, dictionary: Dictionary): Dictionary = {
    conditional.thenStatements.flatMap(statement => evaluate(statement, symbolTable, dictionary)).toMap
  }

  def evaluateExpression(expr: Expression, symbolTable: SymbolTable, dictionary: Dictionary): Answer = expr match {
    case answer: Answer => answer
    case Reference(questionId) =>
      evaluateExpression(
        // We can perform the unsafe get operation on the option here because we are sure there are no references in
        // expressions that are not defined in the symbol table
        dictionary.get(questionId).orElse(symbolTable.get(questionId).map(_.expression)).get,
        symbolTable,
        dictionary
      )
    case UnaryOperation(operator: Operator, expression) =>
      val answer = evaluateExpression(expression, symbolTable, dictionary)
      answer.applyUnaryOperator(operator)
    case BinaryOperation(operator: Operator, leftExpression, rightExpression) =>
      val leftAnswer = evaluateExpression(leftExpression, symbolTable, dictionary)
      val rightAnswer = evaluateExpression(rightExpression, symbolTable, dictionary)
      leftAnswer.applyBinaryOperator(operator, rightAnswer)
  }
}
