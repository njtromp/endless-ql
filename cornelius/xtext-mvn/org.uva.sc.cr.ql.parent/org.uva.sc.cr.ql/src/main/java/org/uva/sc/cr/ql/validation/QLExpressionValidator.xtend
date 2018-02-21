package org.uva.sc.cr.ql.validation

import org.eclipse.xtext.validation.Check
import org.uva.sc.cr.ql.qL.Block
import org.uva.sc.cr.ql.qL.Expression
import org.uva.sc.cr.ql.qL.ExpressionAnd
import org.uva.sc.cr.ql.qL.ExpressionComparison
import org.uva.sc.cr.ql.qL.ExpressionEquality
import org.uva.sc.cr.ql.qL.ExpressionLiteralBoolean
import org.uva.sc.cr.ql.qL.ExpressionLiteralInteger
import org.uva.sc.cr.ql.qL.ExpressionLiteralString
import org.uva.sc.cr.ql.qL.ExpressionMulOrDiv
import org.uva.sc.cr.ql.qL.ExpressionNot
import org.uva.sc.cr.ql.qL.ExpressionOr
import org.uva.sc.cr.ql.qL.ExpressionPlusOrMinus
import org.uva.sc.cr.ql.qL.ExpressionQuestionRef
import org.uva.sc.cr.ql.qL.Question
import org.uva.sc.cr.ql.util.MissingCaseException
import org.uva.sc.cr.ql.qL.QLPackage
import org.uva.sc.cr.ql.util.QLUtil

/**
 * This class contains custom validation rules. 
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class QLExpressionValidator extends AbstractQLValidator {

	public static val TYPE_NOT_ALLOWED = 'typeNotAllowed'
	public static val TYPE_NOT_ALLOWED_MESSAGE = "this type is not allowed for the specified operation"

	public static val TYPE_NOT_SAME = 'typeNotSame'
	public static val TYPE_NOT_SAME_MESSAGE = "The provided types for this operation have to be the same"

	public static val BLOCK_INVALID_EXPRESSION = 'blockInvalidExpression'
	public static val BLOCK_INVALID_EXPRESSION_MESSAGE = "Not a boolean expression"

	public static val TYPE_NOT_EXPECTED = 'typeNotExpected'
	public static val TYPE_NOT_EXPECTED_MESSAGE = "The resulting type does not match the expected type"

	def String computeType(Expression exp) {
		switch exp {
			ExpressionOr:
				QLUtil.TYPE_BOOLEAN
			ExpressionAnd:
				QLUtil.TYPE_BOOLEAN
			ExpressionEquality:
				QLUtil.TYPE_BOOLEAN
			ExpressionComparison:
				QLUtil.TYPE_BOOLEAN
			ExpressionPlusOrMinus: {
				computeType(exp.left)
			}
			ExpressionMulOrDiv: {
				computeType(exp.left)
			}
			ExpressionNot:
				QLUtil.TYPE_BOOLEAN
			ExpressionLiteralString:
				QLUtil.TYPE_STRING
			ExpressionLiteralInteger:
				QLUtil.TYPE_INTEGER
			ExpressionLiteralBoolean:
				QLUtil.TYPE_BOOLEAN
			ExpressionQuestionRef:
				QLUtil.getTypeForQuestionType(exp.question.type)
			default:
				throw new MissingCaseException
		}
	}

	@Check
	def checkExpressionOr(ExpressionOr exp) {

		var leftType = computeType(exp.left)
		var rightType = computeType(exp.right)

		var allowedTypes = QLUtil.allowedTypesForOperations.get(exp.op)
		if (!allowedTypes.contains(leftType))
			error(TYPE_NOT_ALLOWED_MESSAGE, QLPackage.Literals.EXPRESSION_OR__LEFT, TYPE_NOT_ALLOWED)

		if (!allowedTypes.contains(rightType))
			error(TYPE_NOT_ALLOWED_MESSAGE, QLPackage.Literals.EXPRESSION_OR__RIGHT, TYPE_NOT_ALLOWED)

		if (leftType != rightType)
			error(TYPE_NOT_SAME_MESSAGE, QLPackage.Literals.EXPRESSION_OR__RIGHT, TYPE_NOT_SAME)

	}

	@Check
	def checkExpressionAnd(ExpressionAnd exp) {

		var leftType = computeType(exp.left)
		var rightType = computeType(exp.right)

		var allowedTypes = QLUtil.allowedTypesForOperations.get(exp.op)
		if (!allowedTypes.contains(leftType))
			error(TYPE_NOT_ALLOWED_MESSAGE, QLPackage.Literals.EXPRESSION_AND__LEFT, TYPE_NOT_ALLOWED)

		if (!allowedTypes.contains(rightType))
			error(TYPE_NOT_ALLOWED_MESSAGE, QLPackage.Literals.EXPRESSION_AND__RIGHT, TYPE_NOT_ALLOWED)

		if (leftType != rightType)
			error(TYPE_NOT_SAME_MESSAGE, QLPackage.Literals.EXPRESSION_AND__RIGHT, TYPE_NOT_SAME)

	}

	@Check
	def checkExpressionEquality(ExpressionEquality exp) {

		var leftType = computeType(exp.left)
		var rightType = computeType(exp.right)

		var allowedTypes = QLUtil.allowedTypesForOperations.get(exp.op)
		if (!allowedTypes.contains(leftType))
			error(TYPE_NOT_ALLOWED_MESSAGE, QLPackage.Literals.EXPRESSION_EQUALITY__LEFT, TYPE_NOT_ALLOWED)

		if (!allowedTypes.contains(rightType))
			error(TYPE_NOT_ALLOWED_MESSAGE, QLPackage.Literals.EXPRESSION_EQUALITY__RIGHT, TYPE_NOT_ALLOWED)

		if (leftType != rightType)
			error(TYPE_NOT_SAME_MESSAGE, QLPackage.Literals.EXPRESSION_EQUALITY__RIGHT, TYPE_NOT_SAME)

	}

	@Check
	def checkExpressionComparison(ExpressionComparison exp) {

		var leftType = computeType(exp.left)
		var rightType = computeType(exp.right)

		var allowedTypes = QLUtil.allowedTypesForOperations.get(exp.op)
		if (!allowedTypes.contains(leftType))
			error(TYPE_NOT_ALLOWED_MESSAGE, QLPackage.Literals.EXPRESSION_COMPARISON__LEFT, TYPE_NOT_ALLOWED)

		if (!allowedTypes.contains(rightType))
			error(TYPE_NOT_ALLOWED_MESSAGE, QLPackage.Literals.EXPRESSION_COMPARISON__RIGHT, TYPE_NOT_ALLOWED)

		if (leftType != rightType)
			error(TYPE_NOT_SAME_MESSAGE, QLPackage.Literals.EXPRESSION_COMPARISON__RIGHT, TYPE_NOT_SAME)

	}

	@Check
	def checkExpressionPlusOrMinus(ExpressionPlusOrMinus exp) {

		var leftType = computeType(exp.left)
		var rightType = computeType(exp.right)

		var allowedTypes = QLUtil.allowedTypesForOperations.get(exp.op)
		if (!allowedTypes.contains(leftType))
			error(TYPE_NOT_ALLOWED_MESSAGE, QLPackage.Literals.EXPRESSION_PLUS_OR_MINUS__LEFT, TYPE_NOT_ALLOWED)

		if (!allowedTypes.contains(rightType))
			error(TYPE_NOT_ALLOWED_MESSAGE, QLPackage.Literals.EXPRESSION_PLUS_OR_MINUS__RIGHT, TYPE_NOT_ALLOWED)

		if (leftType != rightType)
			error(TYPE_NOT_SAME_MESSAGE, QLPackage.Literals.EXPRESSION_PLUS_OR_MINUS__RIGHT, TYPE_NOT_SAME)

	}

	@Check
	def checkExpressionMulOrDiv(ExpressionMulOrDiv exp) {

		var leftType = computeType(exp.left)
		var rightType = computeType(exp.right)

		var allowedTypes = QLUtil.allowedTypesForOperations.get(exp.op)
		if (!allowedTypes.contains(leftType))
			error(TYPE_NOT_ALLOWED_MESSAGE, QLPackage.Literals.EXPRESSION_MUL_OR_DIV__LEFT, TYPE_NOT_ALLOWED)

		if (!allowedTypes.contains(rightType))
			error(TYPE_NOT_ALLOWED_MESSAGE, QLPackage.Literals.EXPRESSION_MUL_OR_DIV__RIGHT, TYPE_NOT_ALLOWED)

		if (leftType != rightType)
			error(TYPE_NOT_SAME_MESSAGE, QLPackage.Literals.EXPRESSION_MUL_OR_DIV__RIGHT, TYPE_NOT_SAME)

	}

	@Check
	def checkExpressionNot(ExpressionNot exp) {

		var type = computeType(exp.expression)

		var allowedTypes = QLUtil.allowedTypesForOperations.get(QLUtil.OPERATION_NOT)
		if (!allowedTypes.contains(type))
			error(TYPE_NOT_ALLOWED_MESSAGE, QLPackage.Literals.EXPRESSION_NOT__EXPRESSION, TYPE_NOT_ALLOWED)

	}

	@Check
	def checkBlockExpression(Block block) {

		if (computeType(block.expression) != QLUtil.TYPE_BOOLEAN) {
			error(BLOCK_INVALID_EXPRESSION_MESSAGE, QLPackage.Literals.BLOCK__EXPRESSION, BLOCK_INVALID_EXPRESSION)
		}

	}

	@Check
	def checkComputedQuestion(Question question) {

		if (question.expression !== null) {
			var expectedType = QLUtil.getTypeForQuestionType(question.type)
			var computedType = computeType(question.expression)
			if (expectedType != computedType)
				error(TYPE_NOT_EXPECTED_MESSAGE, QLPackage.Literals.QUESTION__EXPRESSION, TYPE_NOT_EXPECTED)
		}

	}

}
