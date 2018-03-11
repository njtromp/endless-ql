package nl.uva.se.sc.niro.parser

import _root_.ql.{ QLBaseVisitor, QLLexer, QLParser }
import nl.uva.se.sc.niro.errors.Errors._
import nl.uva.se.sc.niro.model.ql._
import nl.uva.se.sc.niro.model.ql.expressions._
import nl.uva.se.sc.niro.model.ql.expressions.answers._
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.{ CharStream, CommonTokenStream }
import org.apache.logging.log4j.scala.Logging

import scala.collection.JavaConverters
import scala.collection.mutable.ListBuffer

object QLFormParser extends Logging {
  private val errorListener = new ErrorListener

  def getParseErrors: ListBuffer[Error] = errorListener.parseErrors

  def parse(formSource: CharStream): QLForm = {
    logger.traceEntry()
    errorListener.parseErrors.clear()
    val parser = new QLParser(new CommonTokenStream(new QLLexer(formSource)))
    parser.removeErrorListeners()
    parser.addErrorListener(errorListener)
    logger.traceExit(FormVisitor.visit(parser.form))
  }

  object FormVisitor extends QLBaseVisitor[QLForm] {
    override def defaultResult(): QLForm = QLForm("Unparseable form definition!", Seq.empty)

    override def shouldVisitNextChild(node: RuleNode, currentResult: QLForm): Boolean = {
      errorListener.parseErrors.isEmpty
    }

    override def visitForm(ctx: QLParser.FormContext): QLForm = {
      val formName = ctx.Identifier().getText
      val statements: Seq[Statement] =
        JavaConverters.asScalaBuffer(ctx.statement).toList.flatMap(StatementVisitor.visit)

      QLForm(formName, statements)
    }
  }

  object StatementVisitor extends QLBaseVisitor[Seq[Statement]] {
    override def defaultResult(): Seq[Statement] =
      Seq(
        Question("error", "There is a serious error in a question or if-else statement!", BooleanType, BooleanAnswer()))

    override def shouldVisitNextChild(node: RuleNode, currentResult: Seq[Statement]): Boolean = {
      errorListener.parseErrors.isEmpty
    }

    override def visitBlock(ctx: QLParser.BlockContext): Seq[Statement] = {
      JavaConverters.asScalaBuffer(ctx.statement()).toList.flatMap(StatementVisitor.visit)
    }

    override def visitQuestion(ctx: QLParser.QuestionContext): Seq[Statement] = {
      val questionId = ctx.Identifier().getText
      val questionLabel = ctx.label.getText
      val answerType = AnswerType(ctx.answerType().getText)
      val expression = Option(ctx.expression)
        .map(ExpressionVisitor.visit)
        .getOrElse(Answer(ctx.answerType.getText))
      Seq(Question(questionId, questionLabel, answerType, expression))
    }

    override def visitConditional(ctx: QLParser.ConditionalContext): Seq[Statement] = {
      val predicate: Expression = ExpressionVisitor.visit(ctx.condition)
      val negatedPredicate: Expression = UnaryOperation(Neg, predicate)

      val thenStatements: Seq[Statement] =
        JavaConverters.asScalaBuffer(ctx.thenBlock).toList.flatMap(StatementVisitor.visit)
      val elseStatements: Seq[Statement] =
        JavaConverters.asScalaBuffer(ctx.elseBlock).toList.flatMap(StatementVisitor.visit)

      val ifConditional = Conditional(predicate, thenStatements)
      val elseConditional = Conditional(negatedPredicate, elseStatements)

      Seq(ifConditional, elseConditional).filter(_.thenStatements.nonEmpty)
    }
  }

  object ExpressionVisitor extends QLBaseVisitor[Expression] {
    override def defaultResult(): Expression = BooleanAnswer(false)

    override def shouldVisitNextChild(node: RuleNode, currentResult: Expression): Boolean = {
      errorListener.parseErrors.isEmpty
    }

    override def visitGroupExpression(ctx: QLParser.GroupExpressionContext): Expression = {
      visit(ctx.expression())
    }
    override def visitUnaryExpression(ctx: QLParser.UnaryExpressionContext): Expression = {
      UnaryOperation(Operator(ctx.operator.getText), visit(ctx.expression))
    }
    override def visitMultiplicativeExpression(ctx: QLParser.MultiplicativeExpressionContext): Expression = {
      BinaryOperation(Operator(ctx.operator.getText), visit(ctx.left), visit(ctx.right))
    }
    override def visitAdditiveExpression(ctx: QLParser.AdditiveExpressionContext): Expression = {
      BinaryOperation(Operator(ctx.operator.getText), visit(ctx.left), visit(ctx.right))
    }
    override def visitRelationalExpression(ctx: QLParser.RelationalExpressionContext): Expression = {
      BinaryOperation(Operator(ctx.operator.getText), visit(ctx.left), visit(ctx.right))
    }
    override def visitEqualityExpression(ctx: QLParser.EqualityExpressionContext): Expression = {
      BinaryOperation(Operator(ctx.operator.getText), visit(ctx.left), visit(ctx.right))
    }
    override def visitLogicalAndExpression(ctx: QLParser.LogicalAndExpressionContext): Expression = {
      BinaryOperation(Operator(ctx.operator.getText), visit(ctx.left), visit(ctx.right))
    }
    override def visitLogicalOrExpression(ctx: QLParser.LogicalOrExpressionContext): Expression = {
      BinaryOperation(Operator(ctx.operator.getText), visit(ctx.left), visit(ctx.right))
    }
    override def visitIntegerConstant(ctx: QLParser.IntegerConstantContext): Expression = {
      IntegerAnswer(ctx.IntegerValue().getText.toInt)
    }
    override def visitDecimalConstant(ctx: QLParser.DecimalConstantContext): Expression = {
      DecimalAnswer(BigDecimal(ctx.DecimalValue().getText))
    }
    override def visitBooleanConstant(ctx: QLParser.BooleanConstantContext): Expression = {
      BooleanAnswer(ctx.getText.toBoolean)
    }
    override def visitDateConstant(ctx: QLParser.DateConstantContext): Expression = {
      DateAnswer(ctx.DateValue().getText)
    }
    override def visitStringConstant(ctx: QLParser.StringConstantContext): Expression = {
      StringAnswer(ctx.Text().getText)
    }
    override def visitVariableName(ctx: QLParser.VariableNameContext): Expression = {
      Reference(ctx.Identifier().getText)
    }
  }

}
