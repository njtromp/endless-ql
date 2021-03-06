package org.uva.sea.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.model.GuiModel;
import org.uva.sea.gui.render.*;
import org.uva.sea.ql.interpreter.dataObject.InterpreterResult;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.ql.interpreter.exceptions.StaticAnalysisError;
import org.uva.sea.ql.interpreter.staticAnalysis.helpers.Messages;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FormController implements Initializable {

    private String defaultQlLocation = "/example.ql";

    private GuiModel guiModel;

    private QuestionRenderer questionRenderer;

    private WarningRenderer warningRenderer;

    private ErrorRenderer errorRenderer;

    @FXML
    private VBox questionBox;

    @FXML
    private VBox messageBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        guiModel = new GuiModel(getClass().getResource(defaultQlLocation).getFile());
        ViewRenderer renderer = new ViewRenderer(questionBox, messageBox, this);
        questionRenderer = new QuestionRenderer(renderer);
        warningRenderer = new WarningRenderer(renderer);
        errorRenderer = new ErrorRenderer(renderer);
        drawGui();
    }

    private void drawGui() {
        try {
            updateGui();
        } catch (IOException | StaticAnalysisError e) {
            errorRenderer.render(e.getMessage());
        }
    }

    private void updateGui() throws IOException, StaticAnalysisError {
        InterpreterResult interpreterResult = guiModel.getInterpreterResult();
        questionRenderer.render(interpreterResult.getQuestions());

        Messages warnings = interpreterResult.getWarnings();
        for(String warning : warnings.getMessages())
            warningRenderer.render(warning);
    }


    @FXML
    public void loadQLFile(ActionEvent actionEvent) {
        FileSelector fileSelector = new FileSelector("Load QL file", "QL", "*.ql");
        File qlFile = fileSelector.getFile();

        if (qlFile == null) {
            errorRenderer.render("File not found");
            return;
        }

        guiModel = new GuiModel(qlFile.getAbsolutePath());
        drawGui();
    }

    @FXML
    public void export(ActionEvent actionEvent) {
        //TODO: Implement Export function
        System.out.println("Export");
    }

    public void updateGuiModel(String questionName, Value value) {
        guiModel.updateQuestion(questionName, value);
        drawGui();
    }
}
