package ql.ast.statements;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.expressions.ExprNode;

public class ComputedQuestionNode extends ASTNode {

    public String label;
    public String id;
    public String type;
    public ExprNode expr;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitComputedQuestion(this);
    }
}
