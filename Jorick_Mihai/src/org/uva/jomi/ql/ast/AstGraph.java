package org.uva.jomi.ql.ast;

import java.util.List;

import org.uva.jomi.ql.ast.expressions.BinaryExpr;
import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.ast.expressions.GroupingExpr;
import org.uva.jomi.ql.ast.expressions.IndentifierExpr;
import org.uva.jomi.ql.ast.expressions.PrimaryExpr;
import org.uva.jomi.ql.ast.statements.BlockStmt;
import org.uva.jomi.ql.ast.statements.FormStmt;
import org.uva.jomi.ql.ast.statements.IfStmt;
import org.uva.jomi.ql.ast.statements.QuestionStmt;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.ast.statements.UnaryExpr;

public class AstGraph implements Stmt.Visitor<String>, Expr.Visitor<String> {

	public String getGraph(List<Stmt> statements) {
		String header = "digraph G {\n" + "  node [shape=\"box\"]\n";
		
		for (Stmt statement : statements) {
			String result = statement.accept(this);
			header += result;
		}

		return header + "}";
	}

	@Override
	public String visitIndetifierExpr(IndentifierExpr expr) {
		return String.format("  %s [label=\"[Ident]\nName: %s\nType: %s\nUndefined: %s\"]\n",
				expr.getId(),
				expr.token.getLexeme(),
				expr.getType(),
				expr.isUndefined());
	}

	@Override
	public String visitPrimaryExpr(PrimaryExpr expr) {
		String value = null;
		
		switch (expr.getType()) {
		case BOOLEAN:
			value = String.format("[%s] %s", expr.getType(), expr.token.getLexeme());
			break;
		case STRING:
			// Remove double quotes at from the start and end of the string
			value = expr.token.getLexeme().substring(1, expr.token.getLexeme().length() - 1);
			value = String.format("[%s] %s", expr.getType(), value);
			break;
		case INTEGER:
			value = String.format("[%s] %s", expr.getType(), expr.token.getLexeme());
			break;
		default:
			// TODO Improve error by displaying the location of the offending token.
			System.err.println("[AstGraph] Unexpected literal expression: " + expr.token.getLexeme().toString());
			break;
		}
		
		return String.format("  %s [label=\"%s\"]\n", expr.getId(), value);
	}

	@Override
	public String visitFormStmt(FormStmt stmt) {
		return stmt.blockStmt.accept(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.blockStmt.getId()) +
				stmt.identifier.accept(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.identifier.getId()) +
				String.format("  %s [label=\"FormStmt\nName: %s\"]\n", stmt.getId(), stmt.identifier.token.getLexeme());
	}

	@Override
	public String visitBlockStmt(BlockStmt stmt) {
		String header = String.format("  %s [label=\"BlockStmt\"]\n", stmt.getId());
		
		for (Stmt statement : stmt.statements) {
			header += String.format("  %s -> %s\n", stmt.getId(), statement.getId());
			String result = statement.accept(this);
			header += result;
		}
		
		return header;
	}

	@Override
	public String visitQuestionStmt(QuestionStmt stmt) {
		String header = String.format("  %s [label=\"QuestionStmt\nName: %s\nType: %s\"]\n",
			   stmt.getId(),
			   stmt.identifier.token.getLexeme(),
			   stmt.type.getName());
		// Visit the optional expression statement
		if (stmt.expression != null) {
			header += stmt.expression.accept(this);
			header += String.format("  %s -> %s\n", stmt.getId(), stmt.expression.getId());
		}
		// Visit the identifier expression
		header += stmt.identifier.accept(this);
		header += String.format("  %s -> %s\n", stmt.getId(), stmt.identifier.getId());
		
		return header;
	}

	@Override
	public String visitBinaryExpr(BinaryExpr expr) {
		return expr.left.accept(this) +
				expr.right.accept(this) +
				String.format("  %s [label=\"%s\"]\n", expr.getId(), expr.operator.getLexeme()) +
				String.format("  %s -> %s\n", expr.getId(), expr.left.getId()) +
				String.format("  %s -> %s\n", expr.getId(), expr.right.getId());
	}

	@Override
	public String visitGroupingExpr(GroupingExpr expr) {
		return expr.expression.accept(this) +
				String.format("  %s [label=\"()\"]\n", expr.getId())+
				String.format("  %s -> %s\n", expr.getId(), expr.expression.getId());
	}

	@Override
	public String visitUnaryExpr(UnaryExpr expr) {
		return expr.right.accept(this) + String.format("  %s [label=\"%s\"]\n", expr.getId(), expr.operator.getLexeme())
		+ String.format("  %s -> %s\n", expr.getId(), expr.right.getId());
	}

	@Override
	public String visitIfStmt(IfStmt stmt) {
		return stmt.expression.accept(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.expression.getId()) +
				stmt.blockStmt.accept(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.blockStmt.getId()) +
				String.format("  %s [label=\"IfStmt\"]\n", stmt.getId());
	}
}