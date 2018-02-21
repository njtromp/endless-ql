import expression.ReturnType;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import model.BlockElement;
import model.Condition;
import model.Form;
import model.Question;
import org.yorichan.formfx.control.Input;
import org.yorichan.formfx.control.option.OptionList;
import org.yorichan.formfx.field.FieldGroup;
import org.yorichan.formfx.form.GridForm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("QL form file selector");

        // Build file selector
        Button fileSelectorButton = createFileSelectorButton(stage);

        // Put button inside a box with spacing
        VBox vBox = new VBox(35);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(fileSelectorButton);

        // Create entire scene
        Scene scene = new Scene(vBox, 300, 100);
        stage.setScene(scene);
        stage.show();
    }

    private void renderForm(Stage stage, File file) {
        Form form = parseFormFromFile(file);

        if(form == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not found");
            alert.showAndWait();
        }

        // Create a group of fields composed of our form questions
        FieldGroup fieldGroup = createFieldGroup(form);

        // Create our form grid
        GridForm gridForm = new GridForm(fieldGroup);
        gridForm.setPadding(new Insets(10, 10, 10, 10));
        gridForm.setFieldOrientation(Orientation.HORIZONTAL);
        gridForm.setAlignment(Pos.CENTER);

        // Build file selector
        Button fileSelectorButton = createFileSelectorButton(stage);

        // Build submit button
        Button submitButton = createSubmitButton(null);

        // Create box with form and submit button
        VBox vBox = new VBox(35);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(fileSelectorButton, gridForm, submitButton);

        // Create entire scene
        Scene scene = new Scene(vBox);
        stage.setTitle(form.identifier + " form");
        stage.setScene(scene);
        stage.show();
    }

    private Form parseFormFromFile(File file) {
        InputStream stream;
        try {
            stream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            return null;
        }
        return FormParser.parseForm(stream);
    }

    private Button createFileSelectorButton(Stage stage) {
        final FileChooser fileChooser = new FileChooser();
        final Button openButton = new Button("Browse files...");

        openButton.setOnAction((event) -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                renderForm(stage, file);
            }
        });

        return openButton;
    }

    private FieldGroup createFieldGroup(Form form) {
        FieldGroup fieldGroup = new FieldGroup();
        HashMap<String, Control> fields = new HashMap<>();
        addQuestionsToFieldGroup(fields, form, form.elements, fieldGroup);
        changeEditableFields(fields, form.elements, true);

        return fieldGroup;
    }

    private void addQuestionsToFieldGroup(HashMap<String, Control> fields, Form form, ArrayList<BlockElement> elements, FieldGroup fieldGroup) {
        for (BlockElement blockElement : elements) {
            if (blockElement.isQuestion()) {
                addQuestionsToFieldGroup(fields, form, (Question) blockElement, fieldGroup);
            } else if (blockElement.isCondition()) {
                addQuestionsToFieldGroup(fields, form, ((Condition) blockElement).elements, fieldGroup);
            }
        }
    }

    private void addQuestionsToFieldGroup(HashMap<String, Control> fields, Form form, Question question, FieldGroup fieldGroup) {
        // Only show questions that have answers you can set a value to
        if (question.answer.getReturnType() == ReturnType.Boolean) {
            addBooleanQuestionToFieldGroup(fields, form, question, fieldGroup);
        } else if (question.answer.getReturnType() == ReturnType.Integer || question.answer.getReturnType() == ReturnType.Decimal || question.answer.getReturnType() == ReturnType.String) {
            addNumberQuestionToFieldGroup(fields, form, question, fieldGroup);
        }

        // Test from: https://o7planning.org/en/11185/javafx-spinner-tutorial
//            Label label = new Label("Select Level:");
//            final Spinner<Integer> spinner = new Spinner<Integer>();
//            final int initialValue = 3;
//            // Value factory.
//            SpinnerValueFactory<Integer> valueFactory = //
//                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, initialValue);
//            spinner.setValueFactory(valueFactory);
//            fieldGroup.join("dummy", "dummy2", spinner);

        // separator, might be useful to visually make groups apparent
//            fieldGroup.separate();
    }

    private void addBooleanQuestionToFieldGroup(HashMap<String, Control> fields, Form form, Question question, FieldGroup fieldGroup) {
        ComboBox<String> input = Input.comboBox(
                new OptionList() {{
                    add("", true);
                    add("true");
                    add("false");
                }});

        // TODO implement observer pattern?
        // If input changes some questions might need to be enabled/disabled
        input.setOnAction(e -> {
            if (input.isEditable() || !input.isDisabled()) {
                // Change answer
                changeQuestionAnswer(input, question);
                changeEditableFields(fields, form.elements, true);
            }
        });

        fields.put(question.name, input);
        fieldGroup.join(question.name, question.text, input);
    }

    private void addNumberQuestionToFieldGroup(HashMap<String, Control> fields, Form form, Question question, FieldGroup fieldGroup) {
        TextInputControl input = Input.textField("");

        if (question.answer.getReturnType() == ReturnType.Integer || question.answer.getReturnType() == ReturnType.Decimal) {
            // NumberStringConverter
            // CurrencyStringConverter
            // DoubleStringConverter
            // https://docs.oracle.com/javase/8/javafx/api/javafx/util/StringConverter.html
            input.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
        }

        // If input changes some questions might need to be enabled/disabled
        input.setOnKeyTyped(e -> {
            if (input.isEditable()) {
                changeQuestionAnswer(input, question);
                changeEditableFields(fields, form.elements, true);
            }

//                    System.out.println(form);
        });

        fields.put(question.name, input);
        fieldGroup.join(question.name, question.text, input);
    }

    private void addStringQuestionToFieldGroup(HashMap<String, Control> fields, Form form, Question question, FieldGroup fieldGroup) {
        // TODO
    }

    private void changeQuestionAnswer(TextInputControl input, Question question) {
        question.answer.setValue(input.getText());
    }

    //
    private void changeQuestionAnswer(ComboBox input, Question question) {
        question.answer.setValue(input.getSelectionModel().getSelectedItem().toString());
    }

    private void changeEditableFields(HashMap<String, Control> fields, ArrayList<BlockElement> elements, boolean inEditableBlock) {
        for (BlockElement blockElement : elements) {
            changeEditableFields(fields, blockElement, inEditableBlock);
        }
    }

    private void changeEditableFields(HashMap<String, Control> fields, BlockElement blockElement, boolean inEditableBlock) {
        if (blockElement.isQuestion()) {
            Control field = fields.get(((Question) blockElement).name);
            field.setVisible(inEditableBlock);
        } else if (blockElement.isCondition()) {
            changeEditableFields(fields, (Condition) blockElement, inEditableBlock);
        }
    }

    private void changeEditableFields(HashMap<String, Control> fields, Condition condition, boolean inEditableBlock) {
        boolean inEditableSubBlock = inEditableBlock && Boolean.TRUE.equals(condition.condition.evaluate().get());
        for (BlockElement blockElement : condition.elements) {
            changeEditableFields(fields, blockElement, inEditableSubBlock);
        }
    }

    private Button createSubmitButton(Form form) {
        // TODO save answers to file?
        Button submitButton = new Button("Submit (see output in console)");
        submitButton.setOnAction(e -> {

            // Debug output, shows answer to every question in console
//            form.elements.forEach(x -> printQuestionAnswers(x));
        });
        return submitButton;
    }

    private void printQuestionAnswers(BlockElement blockElement) {
        if (blockElement.isQuestion()) {
            printQuestionAnswers((Question) blockElement);
        } else if (blockElement.isCondition()) {
            printQuestionAnswers((Condition) blockElement);
        }
    }

    private void printQuestionAnswers(Condition condition) {
        for (BlockElement blockElement : condition.elements) {
            printQuestionAnswers(blockElement);
        }
    }


    private void printQuestionAnswers(Question question) {
        System.out.println("\t" + question.name + " => " + question.answer.evaluate());
    }
}