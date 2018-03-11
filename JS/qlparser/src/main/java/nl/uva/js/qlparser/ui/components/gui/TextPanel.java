package nl.uva.js.qlparser.ui.components.gui;

import javax.swing.*;
import java.awt.*;


public class TextPanel extends JPanel{

    private TextArea textArea;

    public TextPanel(int width, int height, Color color) {
        this.setBackground(Color.gray);

        textArea = new TextArea("",0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        textArea.setPreferredSize(new Dimension(width, height));
        textArea.setBackground(color);
        textArea.setForeground(Color.white);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        this.add(textArea);
        this.setVisible(true);
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public String getText() {
        return textArea.getText();
    }
}
