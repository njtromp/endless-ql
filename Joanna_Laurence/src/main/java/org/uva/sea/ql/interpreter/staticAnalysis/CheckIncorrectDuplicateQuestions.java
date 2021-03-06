package org.uva.sea.ql.interpreter.staticAnalysis;

import org.uva.sea.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.ql.parser.visitor.BaseASTVisitor;

import java.util.HashMap;

public class CheckIncorrectDuplicateQuestions extends BaseASTVisitor<Void> implements IStaticAnalysis {


    private Messages errors = new Messages(MessageTypes.ERROR);

    /**
     * Labels that are associated with variables
     */
    private HashMap<String, NodeType> variables = new HashMap<>();

    /**
     * Hide constructor
     */
    private CheckIncorrectDuplicateQuestions() {

    }

    /**
     * Hide the visitor, make only doCheck visible
     */
    public static class Checker implements IStaticAnalysis {
        @Override
        public Messages doCheck(Form node) {
            IStaticAnalysis checker = new CheckIncorrectDuplicateQuestions();
            return checker.doCheck(node);
        }
    }

    /**
     * Report warning
     *
     * @param node The node that caused the warning
     */
    private void error(Question node) {
        this.errors.addMessage(node.getVariable().getVariableName() + " is different defined on line: " + node.getLine() + " column: " + node.getColumn());
    }

    /**
     * Perform the check
     *
     * @param node The form node
     * @return Warnings
     */
    public Messages doCheck(Form node) {
        node.accept(this);
        return this.errors;
    }

    /**
     * Check the questions
     *
     * @param node
     * @return
     */
    public Void visit(Question node) {
        super.visit(node);

        NodeType linkedNodeType = this.variables.get(node.getVariable().getVariableName());
        boolean incorrectNodeTypeLinked = linkedNodeType != null && linkedNodeType != node.getNodeType().getNodeType();

        if (incorrectNodeTypeLinked) {
            this.error(node);
            return null;
        }

        linkNodeTypeToVariable(node);
        return null;
    }

    /**
     * Link label to variable
     *
     * @param node
     */
    private void linkNodeTypeToVariable(Question node) {
        this.variables.put(node.getLabel(), node.getNodeType().getNodeType());
    }
}
