package nl.khonraad;

import nl.khonraad.domain.Question;
import nl.khonraad.domain.Questions;

public class ValuationVisitor extends ExpressionLanguageBaseVisitor<Integer> {

	public Questions questions = new Questions();
	
	@Override
	public Integer visitBlock(ExpressionLanguageParser.BlockContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Integer visitForm(ExpressionLanguageParser.FormContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Integer visitLBL_Expression_AddSub_Expression(
			ExpressionLanguageParser.LBL_Expression_AddSub_ExpressionContext ctx) {
		int left = visit(ctx.expression(0));
		int right = visit(ctx.expression(1));

		if (ctx.operator.getType() == ExpressionLanguageParser.OPERATOR_ADD) {
			return left + right;
		}
		return left - right;
	}

	@Override
	public Integer visitLBL_Expression_MulDiv_Expression(
			ExpressionLanguageParser.LBL_Expression_MulDiv_ExpressionContext ctx) {
		int left = visit(ctx.expression(0));
		int right = visit(ctx.expression(1));
		if (ctx.operator.getType() == ExpressionLanguageParser.OPERATOR_MUL) {
			return left * right;
		}
		return left / right;
	}

	@Override
	public Integer visitLBL_Id_Expression(ExpressionLanguageParser.LBL_Id_ExpressionContext ctx) {

		String id = ctx.ID().getText();

		if ( questions.containsKey(id)) {
			return questions.get(id).getValue();
		}
		return 0;

	}

	@Override
	public Integer visitLBL_Integer_Expression(ExpressionLanguageParser.LBL_Integer_ExpressionContext ctx) {
		return Integer.valueOf(ctx.INTEGER().getText());
	}

	@Override
	public Integer visitLBL_LParen_Expression_RParen(ExpressionLanguageParser.LBL_LParen_Expression_RParenContext ctx) {
		return visitChildren(ctx.expression());
	}

	@Override
	public Integer visitLBL_Min_Expression(ExpressionLanguageParser.LBL_Min_ExpressionContext ctx) {
		int value = visit(ctx.expression());
		return -value;
	}

	@Override
	public Integer visitLBL_Not_Expression(ExpressionLanguageParser.LBL_Not_ExpressionContext ctx) {
		int value = visit(ctx.expression());
		if (value != 0)
			return 0;
		return 1;
	}

	@Override
	public Integer visitLBL_Question(ExpressionLanguageParser.LBL_QuestionContext ctx) {

		if ( !questions.containsKey(ctx.variable.getText())) {
			Question question = new Question(ctx.variable.getText(), ctx.label.getText(), ctx.type.getText() );
			questions.put(ctx.variable.getText(), question);
		}
		return visitChildren(ctx);
	}

	@Override
	public Integer visitLBL_ComputedQuestion(ExpressionLanguageParser.LBL_ComputedQuestionContext ctx)  {
		
		Question question = new Question(ctx.variable.getText(), ctx.label.getText(), ctx.type.getText() );
		question.setValue(visit(ctx.expression()).toString());
		questions.put(ctx.variable.getText(), question);
		return visitChildren(ctx);
	}

	@Override
	public Integer visitLBL_ConditionalBlock(ExpressionLanguageParser.LBL_ConditionalBlockContext ctx) {
		// TODO implement logic
		return visitChildren(ctx);
	}

}
