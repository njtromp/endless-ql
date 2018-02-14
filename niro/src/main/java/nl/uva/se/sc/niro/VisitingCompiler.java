package nl.uva.se.sc.niro;

import nl.uva.se.sc.niro.ast.Node;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;
import ql.QLBaseVisitor;
import ql.QLLexer;
import ql.QLParser;
import ql.QLParser.FormContext;

import java.io.IOException;

/**
 * Just for testing the ANTLR integration. Acts mainly for Proof Of Concept. 
 * @author Nico Tromp
 */
public class VisitingCompiler extends QLBaseVisitor<Node> {

	public Object compileScriptFile(CharStream source) throws IOException {
		QLLexer lexer = new QLLexer(source);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		QLParser parser = new QLParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(new ErrorListener());

		FormContext form = parser.form();
		visit(form);
		return null;
	}

	@Override
	public Node visitLogicalExpr(QLParser.LogicalExprContext ctx) {
		System.out.printf("LogicalOpr [%s]%n", ctx.logicalOp().getText());
		TerminalNode terminalNode = (TerminalNode) ctx.logicalOp().getChild(0);
		System.out.printf("TerminalNode [%s]%n", terminalNode);
		System.out.printf("LogicalOpr LHS [%s]%n", ctx.expression(0).getText());
		System.out.printf("LogicalOpr RHS [%s]%n", ctx.expression(1).getText());
		return super.visitLogicalExpr(ctx);
	}

	@Override
	public Node visitCompExpr(QLParser.CompExprContext ctx) {
		System.out.printf("CompOpr [%s]%n", ctx.compOp().getText());
		System.out.printf("CompOpr LHS expr(0)=[%s] LHS=[%s]%n", ctx.expression(0).getText(), ctx.lhs.getText());
		System.out.printf("CompOpr RHS expr(1)=[%s] RHS=[%s]%n", ctx.expression(1).getText(), ctx.rhs.getText());
		return super.visitCompExpr(ctx);
	}
}