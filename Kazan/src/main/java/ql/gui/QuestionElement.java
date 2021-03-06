package ql.gui;

import ql.ast.statements.Question;
import ql.evaluator.Evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionElement extends GUIElement {

    Evaluator evaluator;
    Question node;

    public QuestionElement(Question node, int yLocation, Evaluator evaluator) {
        super(yLocation);
        this.node = node;
        this.evaluator = evaluator;
        height = 70;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.drawString(node.getLabel(), 50, yLocation);
    }

}
