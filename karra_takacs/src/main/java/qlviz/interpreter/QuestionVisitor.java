package qlviz.interpreter;

import qlviz.QLBaseVisitor;
import qlviz.QLParser;
import qlviz.model.numericExpressions.NumericExpression;
import qlviz.model.question.*;

public class QuestionVisitor extends QLBaseVisitor<Question> {

    private final QuestionTypeTranslator questionTypeTranslator;
    private final NumericExpressionVisitor numericExpressionVisitor;

    public QuestionVisitor(QuestionTypeTranslator questionTypeTranslator, NumericExpressionVisitor numericExpressionVisitor) {
        this.questionTypeTranslator = questionTypeTranslator;
        this.numericExpressionVisitor = numericExpressionVisitor;
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        QuestionType type =
                questionTypeTranslator.translate(ctx.TYPE());
        String text = ctx.questionText().getText();
        String name = ctx.questionName().getText();
        NumericExpression computedValue = null;
        if (ctx.computedValue() != null)
        {
            computedValue = numericExpressionVisitor.visitNumericExpression(ctx.computedValue().numericExpression());
        }
        switch (type){
            case Boolean:
                return new BooleanQuestion(name, text, type);
            case Money:
                return new MoneyQuestion(name, text, type, computedValue);
            case String:
                return new StringQuestion(name, text, type);
            case Integer:
                return new IntegerQuestion(name, text, type, computedValue);
            case Date:
                return new DateQuestion(name, text, type);
            case Decimal:
                return new DecimalQuestion(name, text, type, computedValue);
        }
        return null;
    }
}
