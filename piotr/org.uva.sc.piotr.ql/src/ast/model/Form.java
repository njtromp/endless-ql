package ast.model;

import ast.model.statements.Statement;
import ast.visitors.ASTNodeVisitor;

import java.util.ArrayList;

public class Form extends ASTNode {

    private String name;
    private ArrayList<Statement> statementList = new ArrayList<>();

    public Form(String name, MetaInformation metaInformation) {
        super(metaInformation);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Statement> getStatementList() {
        return statementList;
    }

    public void setStatementList(ArrayList<Statement> statementList) {
        this.statementList = statementList;
    }

    public boolean addStatement(Statement statement) {
        return this.statementList.add(statement);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
