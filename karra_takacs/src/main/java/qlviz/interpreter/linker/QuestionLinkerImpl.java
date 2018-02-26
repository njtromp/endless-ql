package qlviz.interpreter.linker;

import qlviz.interpreter.TypedQuestionCollector;
import qlviz.model.ConditionalBlock;
import qlviz.model.Form;
import qlviz.model.NumericQuestion;
import qlviz.model.QuestionBlock;
import qlviz.model.booleanExpressions.BinaryBooleanOperation;
import qlviz.model.booleanExpressions.BooleanLiteral;
import qlviz.model.booleanExpressions.Negation;
import qlviz.model.question.BooleanQuestion;
import qlviz.model.question.BooleanQuestionReference;
import qlviz.model.question.Question;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class QuestionLinkerImpl implements QuestionLinker {

    private final TypedQuestionCollector typedQuestionCollector;


    public QuestionLinkerImpl(TypedQuestionCollector typedQuestionCollector) {
        this.typedQuestionCollector = typedQuestionCollector;
    }



    private void linkQuestionsInBlock(QuestionBlock questionBlock, BooleanExpressionLinker booleanExpressionLinker) {
        for (ConditionalBlock conditionalBlock : questionBlock.getBlocks()) {
            conditionalBlock.getCondition().accept(booleanExpressionLinker);
            for (QuestionBlock innerBlock : conditionalBlock.getQuestionBlocks()) {
                this.linkQuestionsInBlock(innerBlock, booleanExpressionLinker);
            }
        }
    }

    @Override
    public void linkQuestionStubs(Form form) {
        List<BooleanQuestion> booleanQuestions = this.typedQuestionCollector.collectBooleanQuestions(form);
        List<NumericQuestion> numericQuestions = this.typedQuestionCollector.collectNumericQuestions(form);

        BooleanExpressionLinker booleanLinker = new BooleanExpressionLinker(
                booleanQuestions
                        .stream()
                        .collect(Collectors.toMap(BooleanQuestion::getName, Function.identity())));

        for (QuestionBlock block : form.getQuestions()) {
            this.linkQuestionsInBlock(block, booleanLinker);
        }
    }
}
