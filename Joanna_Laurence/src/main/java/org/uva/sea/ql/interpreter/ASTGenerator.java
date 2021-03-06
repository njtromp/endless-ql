package org.uva.sea.ql.interpreter;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.ql.interpreter.exceptions.StaticAnalysisError;
import org.uva.sea.ql.interpreter.dataObject.ASTResult;
import org.uva.sea.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.ql.parser.antlr.ErrorHandler;
import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.interpreter.staticAnalysis.*;
import org.uva.sea.ql.interpreter.staticAnalysis.helpers.Messages;

import org.uva.sea.ql.antlr.QLLexer;
import org.uva.sea.ql.antlr.QLParser;

import java.util.List;

public class ASTGenerator {

    private List<IStaticAnalysis> staticAnalyses = Arrays.asList(new IStaticAnalysis[]{
            new LinkAndCheckVariableUsage.Checker(),
            new TypeCheck.Checker(),
            new CheckDuplicateLabels.Checker(),
            new CheckIncorrectDuplicateQuestions.Checker(),
            new CircularQuestionDependencies.Checker()
    });

    /**
     * Compile a form specification
     *
     * @param source Of the source location
     * @return The AST node that can be used by the interpreter
     */
    public ASTResult interpreterScriptFile(CharStream source) throws StaticAnalysisError {
new LinkAndCheckVariableUsage.Checker();
        Form AST = createAST(source);
        if (AST == null)
            return new ASTResult(null, new Messages(MessageTypes.UNKNOWN));

        Messages messages = executeStaticAnalysis(AST);
        return new ASTResult(AST, messages);
    }

    /**
     * Throws an exception when an error is present.
     *
     * @param AST
     * @return
     * @throws StaticAnalysisError
     */
    private Messages executeStaticAnalysis(Form AST) throws StaticAnalysisError {
        Messages warnings = new Messages(MessageTypes.WARNING);
        for (IStaticAnalysis staticAnalysis : this.staticAnalyses) {
            Messages messages = staticAnalysis.doCheck(AST);
            if (messages.hasMessagePresent()) {
                if (messages.getMessageTypes() == MessageTypes.ERROR) {
                    throw new StaticAnalysisError(messages);
                } else if (messages.getMessageTypes() == MessageTypes.WARNING) {
                    warnings.addMessageList(messages.getMessages());
                }
            }
        }
        return warnings;
    }

    /**
     * Create AST for specification
     *
     * @param source
     * @return
     */
    private Form createAST(CharStream source) {
        QLLexer lexer = new QLLexer(source);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tokens);

        ErrorHandler parseErrorListener = new ErrorHandler();
        parser.addErrorListener(parseErrorListener);

        QLParser.FormContext form = parser.form();

        return parseErrorListener.isError() ? null : form.result;
    }
}



