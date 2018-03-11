package gui;

import gui.model.FormFieldModel;
import gui.view.TextForm;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// http://www.java2s.com/Code/Java/Swing-JFC/Asimplelabelforfieldformpanel.htm
// eventually
// https://www.callicoder.com/javafx-registration-form-gui-tutorial/

public class QLGui {
    public QLGui(List<FormFieldModel> formFieldModels) {

        final TextForm form = new TextForm(formFieldModels);

        JButton submit = new JButton("Submit Form");

//        submit.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(form.getText(0) + " " + form.getText(1) + ". " + form.getText(2)
//                        + ", age " + form.getText(3));
//            }
//        });

        JFrame f = new JFrame("Text Form Example");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(form, BorderLayout.NORTH);
        JPanel p = new JPanel();
        p.add(submit);
        f.getContentPane().add(p, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
