from pyql.antlr.QLVisitor import QLVisitor
from pyql.antlr.QLParser import QLParser
from pyql.ast.form.block import Block
from pyql.ast.code_location import CodeLocation
from pyql.ast.form.form import Form
from pyql.ast.form.ql_statements import *
from pyql.ast.expression.expressions import *
from pyql.ast.expression.literals import *
from pyql.util.types import *

# TODO check if can get rid of 'if getChildCount() > 1'


class ParseTreeVisitor(QLVisitor):

    def visitForm(self, ctx: QLParser.FormContext):
        identifier = ctx.identifier().accept(self)
        location = self.location(ctx)
        block = ctx.block().accept(self)
        return Form(identifier, location, block)

    def visitIfStatement(self, ctx: QLParser.IfStatementContext):
        return If(self.location(ctx), ctx.expression().accept(self), ctx.block().accept(self))

    def visitIfElseStatement(self, ctx: QLParser.IfElseStatementContext):
        return IfElse(self.location(ctx), ctx.expression().accept(self), ctx.block(0).accept(self),
                      ctx.block(1).accept(self))

    def visitBlock(self, ctx: QLParser.BlockContext):
        return Block(self.location(ctx), [s.accept(self) for s in ctx.statement()])

    def visitStatement(self, ctx: QLParser.StatementContext):
        return self.visitChildren(ctx)

    def visitQuestion(self, ctx: QLParser.QuestionContext):
        questionType = ctx.questionType().accept(self)
        if isinstance(questionType, Money) and questionType.expression is not None:
            return ComputedQuestion(self.location(ctx), ctx.identifier().accept(self), ctx.STRING(), Money(), questionType.expression)
        return Question(self.location(ctx), ctx.identifier().accept(self), ctx.STRING(), questionType)

    def visitBooleanType(self, ctx: QLParser.BooleanTypeContext):
        return Boolean()

    def visitStringType(self, ctx: QLParser.StringTypeContext):
        return String()

    def visitIntegerType(self, ctx: QLParser.IntegerTypeContext):
        return Integer()

    def visitDateType(self, ctx: QLParser.DateTypeContext):
        return Date()

    def visitDecimalType(self, ctx: QLParser.DecimalTypeContext):
        return Decimal()

    def visitBasicMoneyType(self, ctx: QLParser.BasicMoneyTypeContext):
        return Money()

    def visitComputedMoneyType(self, ctx: QLParser.ComputedMoneyTypeContext):
        return Money(ctx.addExpression().accept(self))

    def visitExpression(self, ctx: QLParser.ExpressionContext):
        return self.visitChildren(ctx)

    def visitOrExpression(self, ctx: QLParser.OrExpressionContext):
        if ctx.getChildCount() > 1:
            left = ctx.andExpression(0).accept(self)
            right = ctx.andExpression(1).accept(self)
            return Or(self.location(ctx), left, right)
        return self.visitChildren(ctx)

    def visitAndExpression(self, ctx: QLParser.AndExpressionContext):
        if ctx.getChildCount() > 1:
            left = ctx.relExpression(0).accept(self)
            right = ctx.relExpression(1).accept(self)
            return And(self.location(ctx), left, right)
        return self.visitChildren(ctx)

    def visitRelExpression(self, ctx: QLParser.RelExpressionContext):
        if ctx.getChildCount() > 1:
            left = ctx.addExpression(0).accept(self)
            right = ctx.addExpression(1).accept(self)
            location = self.location(ctx)
            return self.binaryExpressionFactory(location, left, right, self.binaryOperator(ctx))
        return self.visitChildren(ctx)

    def visitAddExpression(self, ctx: QLParser.AddExpressionContext):
        if ctx.getChildCount() > 1:
            mulExpressions = [m.accept(self) for m in ctx.mulExpression()]
            operators = [o.accept(self) for o in ctx.addOperator()]
            location = self.location(ctx)
            return self.buildMultiaryExpression(location, mulExpressions, operators)
        return self.visitChildren(ctx)

    def visitAddOperator(self, ctx: QLParser.AddOperatorContext):
        return ctx.getText()

    def visitMulExpression(self, ctx: QLParser.MulExpressionContext):
        if ctx.getChildCount() > 1:
            unExpressions = [m.accept(self) for m in ctx.unExpression()]
            operators = [o.accept(self) for o in ctx.mulOperator()]
            location = self.location(ctx)
            return self.buildMultiaryExpression(location, unExpressions, operators)
        return self.visitChildren(ctx)

    def visitMulOperator(self, ctx: QLParser.MulOperatorContext):
        return ctx.getText()

    def visitUnExpression(self, ctx: QLParser.UnExpressionContext):
        expression = ctx.expression()
        if expression:
            return expression.accept(self)
        return self.visitChildren(ctx)

    def visitMoneyLiteral(self, ctx: QLParser.MoneyLiteralContext):
        return MoneyLiteral(self.location(ctx), ctx.getText())

    def visitDecimalLiteral(self, ctx: QLParser.DecimalLiteralContext):
        return DecimalLiteral(self.location(ctx), ctx.getText())

    def visitIntLiteral(self, ctx: QLParser.IntLiteralContext):
        return IntegerLiteral(self.location(ctx), ctx.getText())

    def visitStringLiteral(self, ctx: QLParser.StringLiteralContext):
        return StringLiteral(self.location(ctx), ctx.getText())

    def visitBoolLiteral(self, ctx: QLParser.BoolLiteralContext):
        return BooleanLiteral(self.location(ctx), ctx.getText())

    def visitIdentifier(self, ctx: QLParser.IdentifierContext):
        return Identifier(self.location(ctx), ctx.getText())

    def location(self, context):
        return CodeLocation(context.start.line, context.start.column)

    def buildMultiaryExpression(self, location, expressions, operators):
        if len(expressions) == 2:
            return self.binaryExpressionFactory(location, expressions[0], expressions[1], operators[0])
        operator = operators.pop()
        expression = expressions.pop()
        return self.binaryExpressionFactory(location, self.buildMultiaryExpression(location, expressions, operators),
                                            expression, operator)

    def binaryOperator(self, ctx):
        return ctx.getChild(1).getText()

    def binaryExpressionFactory(self, location, left, right, operator):
        switcher = {
            "*": Multiplication(location, left, right),
            "/": Division(location, left, right),
            "+": Addition(location, left, right),
            "-": Subtraction(location, left, right),
            "<": LessThan(location, left, right),
            ">": GreaterThan(location, left, right),
            "<=": LessThanOrEqual(location, left, right),
            ">=": GreaterThanOrEqual(location, left, right),
            "==": Equals(location, left, right),
            "!=": NotEquals(location, left, right)
        }
        return switcher.get(operator)
