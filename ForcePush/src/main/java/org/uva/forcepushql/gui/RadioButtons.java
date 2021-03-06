package org.uva.forcepushql.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioButtons extends JPanel implements ActionListener {
	
	public RadioButtons(){
		
	}
	
	public void Create_Radio()
	{
		
		JRadioButton test_button = new JRadioButton("Test1"); 
		test_button.setMnemonic(KeyEvent.VK_B);
		test_button.setActionCommand("Test1");
		test_button.setSelected(true);
		
		JRadioButton test_button2 = new JRadioButton("Test2"); 
		test_button.setMnemonic(KeyEvent.VK_B);
		test_button.setActionCommand("Test2");
		
		JRadioButton test_button3 = new JRadioButton("Test3"); 
		test_button.setMnemonic(KeyEvent.VK_B);
		test_button.setActionCommand("Test3");
		
		ButtonGroup group = new ButtonGroup();
		group.add(test_button);
		group.add(test_button2);
		group.add(test_button3);
		
		test_button.addActionListener(this);
		test_button2.addActionListener(this);
		test_button3.addActionListener(this);

		JPanel radio_Panel = new JPanel(new GridLayout(0, 1));
		radio_Panel.add(test_button);
		radio_Panel.add(test_button2);
		radio_Panel.add(test_button3);
		
		add(radio_Panel, BorderLayout.LINE_START);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}
	
	 public void actionPerformed(ActionEvent e)
	 {
		 	e.getActionCommand();
	 }
	
}
