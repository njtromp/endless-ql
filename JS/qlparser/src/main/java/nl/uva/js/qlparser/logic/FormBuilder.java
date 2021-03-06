package nl.uva.js.qlparser.logic;

import lombok.SneakyThrows;
import nl.uva.js.qlparser.antlr.QLLexer;
import nl.uva.js.qlparser.antlr.QLParser;
import nl.uva.js.qlparser.models.expressions.Form;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FormBuilder {

    @SneakyThrows(IOException.class)
    public static Form parseFormFromLocation(String location) {
        String content = new String(Files.readAllBytes(Paths.get(location)));
        return parseFormFromString(content);
    }

    public static Form parseFormFromString(String qlInput) {
        QLLexer lexer = new QLLexer(CharStreams.fromString(qlInput));
        lexer.addErrorListener(new ErrorListener());
        QLParser parser = new QLParser(new CommonTokenStream(lexer));

        Form form = new QLVisitor().visitForm(parser.form());
        form.checkType();

        return form;
    }
}
