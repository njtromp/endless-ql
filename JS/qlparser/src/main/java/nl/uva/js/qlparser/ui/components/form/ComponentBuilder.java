package nl.uva.js.qlparser.ui.components.form;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.models.expressions.data.Variable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ComponentBuilder {

    public static JTextField buildTextField(Variable v) {
        JTextField textField = new JTextField(1);

        NonNullRun.consumer(v.getValue(), val -> textField.setText(val.value().toString()));
        textField.getDocument().addDocumentListener(new TextChangeListener(textField));

        return textField;
    }

    @RequiredArgsConstructor
    private static class TextChangeListener implements DocumentListener {
        @NonNull
        private JTextField textField;

        private void updateValue() {
            System.out.println(textField.getText());
        }

        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            updateValue();
        }

        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            updateValue();
        }

        @Override
        public void changedUpdate(DocumentEvent documentEvent) { updateValue(); }
    }
}
