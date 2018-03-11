package nl.uva.se.sc.niro.typechecking

import nl.uva.se.sc.niro.Evaluator
import nl.uva.se.sc.niro.errors.Errors.TypeCheckError
import nl.uva.se.sc.niro.model.ql.SymbolTable.SymbolTable
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers._
import nl.uva.se.sc.niro.typechecking.CycleDetection._
import org.apache.logging.log4j.scala.Logging

object TypeChecker extends Logging {

  /** Performs all type checking tasks. Early aborts when one of the tasks returns a TypeCheckError
    * */
  // Order of execution is important here to avoid infinite loops in later tasks
  def pipeline(qLForm: QLForm): Either[TypeCheckError, QLForm] =
    for {
      _ <- checkReferences(qLForm)
      _ <- checkCyclicDependenciesBetweenQuestions(qLForm)
      _ <- checkOperandsOfInvalidTypeToOperators(qLForm)
      _ <- checkNonBooleanPredicates(qLForm)
      _ <- checkDuplicateQuestionDeclarationsWithDifferentTypes(qLForm)
      _ <- checkDuplicateLabels(qLForm)
    } yield qLForm

  // TODO implement checkOperandsOfInvalidTypeToOperators
  private def checkOperandsOfInvalidTypeToOperators(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 3 - Checking operands of invalid type to operators ...")

    val questions = Statement.collectAllQuestions(qLForm.statements)
    val conditionals = Statement.collectAllConditionals(qLForm.statements)
    val expressions = questions.map(_.expression) ++ conditionals.map(_.predicate)

    expressions
      .map(expression => checkExpression(expression, qLForm.symbolTable))
      .foldLeft(Right(qLForm): Either[TypeCheckError, QLForm])(
        (acc: Either[TypeCheckError, QLForm], either: Either[TypeCheckError, Answer]) =>
          acc.flatMap(form => either.map(_ => qLForm)))
  }

  // TODO clean up this mess
  def checkExpression(expr: Expression, symbolTable: SymbolTable): Either[TypeCheckError, Answer] = expr match {
    case a: Answer => Right(a)
    case Reference(questionId) =>
      checkExpression(symbolTable(questionId).expression, symbolTable)
    case UnaryOperation(operator: Operator, expression) =>
      checkExpression(expression, symbolTable).flatMap(
        answer =>
          Either.cond(
            checkOperandsAndOperator(operator, answer).isEmpty,
            answer,
            checkOperandsAndOperator(operator, answer).get))
    case BinaryOperation(operator: Operator, leftExpression, rightExpression) =>
      val leftAnswer: Either[TypeCheckError, Answer] = checkExpression(leftExpression, symbolTable).flatMap(
        answer =>
          Either.cond(
            checkOperandsAndOperator(operator, answer).isEmpty,
            answer,
            checkOperandsAndOperator(operator, answer).get))
      val rightAnswer: Either[TypeCheckError, Answer] = checkExpression(rightExpression, symbolTable).flatMap(
        answer =>
          Either.cond(
            checkOperandsAndOperator(operator, answer).isEmpty,
            answer,
            checkOperandsAndOperator(operator, answer).get))
      leftAnswer.flatMap(la => rightAnswer.flatMap(ra => Right(la.applyBinaryOperator(operator, ra))))
  }

  // TODO make typecheckable type class
  private def checkOperandsAndOperator(operator: Operator, operand: Answer): Option[TypeCheckError] = {
    operator match {
      case _: ArithmeticOperator =>
        operand match {
          case _: IntegerAnswer => None
          case _: DecimalAnswer => None
          case _                => Some(TypeCheckError(message = "Operand of invalid type"))
        }
      case _: BooleanOperator =>
        operand match {
          case _: IntegerAnswer => None
          case _: DecimalAnswer => None
          case _: MoneyAnswer   => None
          case _: BooleanAnswer => None
          case _: DateAnswer    => None
          case _                => Some(TypeCheckError(message = "Operand of invalid type"))
        }
      case _: LogicalOperator =>
        operand match {
          case _: BooleanAnswer => None
          case _                => Some(TypeCheckError(message = "Operand of invalid type"))
        }
      case _ => Some(TypeCheckError(message = "Operator not implemented yet"))
    }
  }

  private def checkReferences(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 1 - Checking references to undefined questions ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)
    val references: Seq[Reference] = Statement.collectAllReferences(questions)
    val undefinedReferences: Seq[String] = references.map(_.value).filterNot(qLForm.symbolTable.contains)

    if (undefinedReferences.nonEmpty) {
      Left(TypeCheckError(message = s"Undefined references: $undefinedReferences"))
    } else {
      Right(qLForm)
    }
  }

  private def checkNonBooleanPredicates(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 3 - Checking predicates that are not of the type boolean ...")

    val conditionals: Seq[Conditional] = Statement.collectAllConditionals(qLForm.statements)
    val conditionalsWithNonBooleanPredicates: Seq[Conditional] = conditionals filter { conditional =>
      Evaluator.evaluateExpression(conditional.predicate, qLForm.symbolTable, Map.empty) match {
        case _: BooleanAnswer => false
        case _                => true
      }
    }

    if (conditionalsWithNonBooleanPredicates.nonEmpty) {
      Left(TypeCheckError(message = s"Non boolean predicate: $conditionalsWithNonBooleanPredicates"))
    } else {
      Right(qLForm)
    }
  }

  private def checkDuplicateQuestionDeclarationsWithDifferentTypes(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 4 - Checking duplicate question declarations with different types ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)
    val duplicateQuestions: Iterator[Seq[Question]] = questions.groupBy(_.id).valuesIterator.filter(_.size > 1)

    val duplicateQuestionsWithDifferentTypes: Seq[Seq[Question]] = duplicateQuestions
      .filter(questionsWithMultipleOccurrences => questionsWithMultipleOccurrences.map(_.answerType).toSet.size > 1)
      .toList

    if (duplicateQuestionsWithDifferentTypes.nonEmpty) {
      Left(
        TypeCheckError(
          message = s"Duplicate question declarations with different types: $duplicateQuestionsWithDifferentTypes"))
    } else {
      Right(qLForm)
    }
  }

  private def checkCyclicDependenciesBetweenQuestions(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 2 - Checking cyclic dependencies between questions ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)

    val dependencyGraph: Graph = buildDependencyGraph(questions)

    val cyclicDependencies: Seq[Graph] =
      dependencyGraph.flatMap(element => detectCycles(dependencyGraph, Seq(element)))

    if (cyclicDependencies.nonEmpty) {
      Left(TypeCheckError(message = s"Found cyclic dependencies: ${cyclicDependencies.map(graphToString)}"))
    } else {
      Right(qLForm)
    }
  }

  private def buildDependencyGraph(questions: Seq[Question]): Graph = {
    questions.flatMap {
      case q @ Question(_, _, _, r @ Reference(_)) => Seq(Edge(q.id, r.value))
      case q @ Question(_, _, _, expression) =>
        Expression.collectAllReferences(expression).map(r => Edge(q.id, r.value))
    }
  }

  // TODO this function should not throw an error. Somehow we should give a warning when duplicate labels are detected
  private def checkDuplicateLabels(qLForm: QLForm): Either[TypeCheckError, QLForm] = {
    logger.info("Phase 6 - Checking duplicate question labels ...")

    val questions: Seq[Question] = Statement.collectAllQuestions(qLForm.statements)
    val questionsWithDuplicateLabels: Seq[Seq[Question]] =
      questions.groupBy(_.label).valuesIterator.filter(_.size > 1).toList

    if (questionsWithDuplicateLabels.nonEmpty) {
      Left(TypeCheckError(message = s"Found questions with duplicate labels"))
    } else {
      Right(qLForm)
    }
  }
}
