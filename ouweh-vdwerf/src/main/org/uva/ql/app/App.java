package org.uva.ql.app;

import org.uva.ql.ast.Form;
import org.uva.ql.evaluator.FormEvaluator;
import org.uva.ql.evaluator.data.ExpressionTable;
import org.uva.ql.evaluator.data.StatementTable;
import org.uva.ql.evaluator.data.ValueTable;
import org.uva.ql.gui.GUIHandler;
import org.uva.ql.parsing.ASTBuilder;
import org.uva.ql.validation.LogHandler;
import org.uva.ql.validation.Validator;
import org.uva.qls.QLSBuilder;
import org.uva.qls.ast.Stylesheet;

import java.util.logging.LogManager;
import java.util.logging.Logger;


public class App {

    private App() {
        Logger logger = Logger.getGlobal();
        LogManager.getLogManager().reset();
        logger.addHandler(new LogHandler());

        String input = new InputHandler().readFile("input/default.ql");
        ASTBuilder builder = new ASTBuilder();
        Form form = builder.buildAST(input);

        String qlsInput = new InputHandler().readFile("input/default.qls");
        QLSBuilder QLSBuilder = new QLSBuilder();
        Stylesheet stylesheet = QLSBuilder.buildAST(qlsInput);

        Validator validator = new Validator(form);
        validator.run();

        FormEvaluator formEvaluator = new FormEvaluator(new ExpressionTable(), new StatementTable(), new ValueTable(), form);

        GUIHandler guiHandler = new GUIHandler(formEvaluator);
    }

    public static void main(String[] args) {
        new App();
    }
}
