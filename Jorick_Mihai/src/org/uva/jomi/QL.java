package org.uva.jomi;

import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.jomi.ql.ast.AstBuilder;
import org.uva.jomi.ql.ast.analysis.DuplicatedLabelChecker;
import org.uva.jomi.ql.ast.analysis.IdentifierResolver;
import org.uva.jomi.ql.ast.analysis.TypeResolver;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.parser.antlr.*;
import org.uva.jomi.ql.parser.antlr.QLParser.ParseContext;
import org.uva.jomi.ui.elements.ElementBuilder;
import org.uva.jomi.ui.elements.core.Frame;
import org.uva.jomi.ui.elements.core.Panel;

public class QL {

	public static void main(String[] args) {
		// Report an error if the the user does not supply a source file.
		if (args.length < 1) {
			System.err.println("Usage QL <script>");
			System.exit(1);
		}

		try {
			// Create a character stream form the source file
			CharStream inputStream = CharStreams.fromFileName(args[0]);
			// Create a lexer instance
			QLLexer lexer = new QLLexer(inputStream);
			// Create a token stream using the lexer
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			// Create a lexer instance
			QLParser parser = new QLParser(tokens);

			ParseContext cst = parser.parse();

			AstBuilder astBuilder = new AstBuilder();
			List<Stmt> ast = astBuilder.visit(cst);

			// Make sure there are no parsing errors before we use the Ast.
			// TODO - Extend the Antlr lexer in order to identify if lexical errors occurred.
			if (parser.getNumberOfSyntaxErrors() == 0) {
				
				// Check for duplicated labels
				DuplicatedLabelChecker labelChecker = new DuplicatedLabelChecker(true);
				labelChecker.check(ast);
				
				// Create a new identifier resolver
				IdentifierResolver identifierResolver = new IdentifierResolver(true);
				// Resolve the Ast
				identifierResolver.resolve(ast);

				if (identifierResolver.getNumberOfErrors() == 0) {
					TypeResolver typeResolver = new TypeResolver(true);
					typeResolver.resolve(ast);

					if (typeResolver.getNumberOfErrors() == 0) {
						ElementBuilder builder = new ElementBuilder();
						List<Panel>panels = builder.build(ast);

						Frame frame = new Frame();

						for (Panel panel : panels) {
							frame.add(panel);
						}
						
						frame.setVisible(true);
					}

					// Output the Ast in GraphViz dot format.
//					java.io.PrintStream outStream = new java.io.PrintStream("graph.txt");
//					outStream.println(new AstGraph().getGraph(ast));
//					outStream.close();
				}
			}

		} catch (IOException e) {
			System.err.println("Source file was not found: " + e.getMessage());
		}
	}

}
