package nl.uva.se.sc.niro;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.Before;
import org.junit.Test;

public class ListeningCompilerTest {
	@Before
	public void clearErrors() {
		ErrorListener.errorReported = false;
	}
	
	@Test
	public void simpleQuestionaireTest() throws IOException {
		ListeningCompiler compiler = new ListeningCompiler();
		compiler.compileScriptFile(toCharStream("/simple.ql"));
		if (ErrorListener.errorReported) {
			fail("Somehting went wrong, see the console for more information!");
		}
	}

	@Test
	public void conditionalQuestionaireTest() throws IOException {
		ListeningCompiler compiler = new ListeningCompiler();
		compiler.compileScriptFile(toCharStream("/simple-conditional.ql"));
		if (ErrorListener.errorReported) {
			fail("Somehting went wrong, see the console for more information!");
		}
	}

	@Test
	public void nestedConditionalQuestionaireTest() throws IOException {
		ListeningCompiler compiler = new ListeningCompiler();
		compiler.compileScriptFile(toCharStream("/nested-conditional.ql"));
		if (ErrorListener.errorReported) {
			fail("Somehting went wrong, see the console for more information!");
		}
	}

	@Test
	public void expressionConditionalQuestionaireTest() throws IOException {
		ListeningCompiler compiler = new ListeningCompiler();
		compiler.compileScriptFile(toCharStream("/expression-conditional.ql"));
		if (ErrorListener.errorReported) {
			fail("Somehting went wrong, see the console for more information!");
		}
	}

	private CharStream toCharStream(String fileName) throws IOException {
		return CharStreams.fromStream(getClass().getResourceAsStream(fileName));
	}
	
}