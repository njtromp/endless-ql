package org.uva.sea.ql.parser.elements.types;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.traverse.Traverse;

public class Var implements ASTNode {
    private String variableName;

    private Question linkedQuestion = null;

    public Var(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Question getLinkedQuestion() {
        return linkedQuestion;
    }

    public void setLinkedQuestion(Question linkedQuestion) {
        this.linkedQuestion = linkedQuestion;
    }

    public void traverse(Traverse traverse) {
        traverse.doVar(this);
    }

    public Type getType() {
        if(this.linkedQuestion == null) {
            System.out.println("Variable information should be set before requesting type information");
            return new Type("undefined");
        }

        return this.linkedQuestion.getType();
    }
}