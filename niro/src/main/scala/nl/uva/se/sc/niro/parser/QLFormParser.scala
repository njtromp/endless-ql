package nl.uva.se.sc.niro.parser

import nl.uva.se.sc.niro.model.Ast.Expression.Operator._
import nl.uva.se.sc.niro.model.Ast.Expression._
import nl.uva.se.sc.niro.model.Ast._
import org.antlr.v4.runtime.{ CharStream, CommonTokenStream }
import ql.{ QLBaseVisitor, QLLexer, QLParser }

import scala.collection.JavaConverters

object QLFormParser {

  def parse(formSource: CharStream): QLForm = {
    val parser = new QLParser(new CommonTokenStream(new QLLexer(formSource)))
    parser.removeErrorListeners()
    parser.addErrorListener(new ErrorListener)
    FormCompiler.visit(parser.form)
  }

  object FormCompiler extends QLBaseVisitor[QLForm] {
    override def visitForm(ctx: QLParser.FormContext): QLForm = {
      val statements = JavaConverters.asScalaBuffer(ctx.statement).toList
      QLForm(ctx.Ident().getText, statements.map(StatementCompiler.visit))
    }
  }

  object StatementCompiler extends QLBaseVisitor[Statement] {
    override def visitQuestion(ctx: QLParser.QuestionContext): Statement = {
      Question(Ident(ctx.Ident().getText), ctx.TEXT().getText, AnswerType(ctx.answerType().getText))
    }

    override def visitConditional(ctx: QLParser.ConditionalContext): Statement = {
      val thenStatements = JavaConverters.asScalaBuffer(ctx.thenBlock).toList
      val elseStatements = JavaConverters.asScalaBuffer(ctx.elseBlock).toList
      Conditional(ExpressionCompiler.visit(ctx.condition), thenStatements.map(StatementCompiler.visit), elseStatements.map(StatementCompiler.visit))
    }
  }

  object ExpressionCompiler extends QLBaseVisitor[Expression] {
    override def visitIntConst(ctx: QLParser.IntConstContext): Expression = {
      IntConst(ctx.IntValue().getText.toInt)
    }

    override def visitDecConst(ctx: QLParser.DecConstContext): Expression = {
      DecConst(ctx.DecValue().getText.toDouble)
    }

    override def visitBoolConst(ctx: QLParser.BoolConstContext): Expression = {
      BoolConst(ctx.getText.toBoolean)
    }

    override def visitVar(ctx: QLParser.VarContext): Expression = {
      Ident(ctx.Ident().getText)
    }

    override def visitUnaryExpr(ctx: QLParser.UnaryExprContext): Expression = {
      UnaryOp(ctx.unaryOp().getText, visit(ctx.expression))
    }

    override def visitArithmExpr(ctx: QLParser.ArithmExprContext): Expression = {
      ArithmOp(ctx.arithmOp().getText, visit(ctx.lhs), visit(ctx.rhs))
    }

    override def visitCompExpr(ctx: QLParser.CompExprContext): Expression = {
      CompOp(ctx.compOp().getText, visit(ctx.lhs), visit(ctx.rhs))
    }

    override def visitLogicalExpr(ctx: QLParser.LogicalExprContext): Expression = {
      LogicalOp(ctx.logicalOp().getText, visit(ctx.lhs), visit(ctx.rhs))
    }

    override def visitGroupExpr(ctx: QLParser.GroupExprContext): Expression = {
      visit(ctx.expression())
    }
  }

}