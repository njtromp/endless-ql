package ql.gui.widget;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.ComponentOrientation;

import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;

import ql.visiting.value.StringValue;
import ql.visiting.value.Value;


public class Label implements WidgetInterface {
	private JLabel label;

	// Configuration
	private WidgetConfiguration config =  new WidgetConfiguration(
			UIManager.getDefaults().getFont("Label.font"), Color.BLACK, new Dimension(120, 60));
	
	// Constructor
	public Label(String str) {
		label = new JLabel(str);
		setStyle(config);
		//label.setComponentPopupMenu(new JPopupMenu());
		label.setToolTipText("info");
		label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	}
	
	@Override
	public JComponent getJComponent() {
		return label;
	}
	
	@Override
	public WidgetConfiguration getStyle() {
		return config;
	}

	@Override
	public void setStyle(WidgetConfiguration config) {
		label.setPreferredSize(new Dimension(config.getWidth(), config.getHeight()));
		label.setForeground(config.getColor());
		label.setFont(config.getFont());
	}
	
	
	@Override
	public StringValue getValue() {
		return new StringValue(label.getText());
	}

	@Override
	public void setValue(Value value) {
		label.setText(value.getValueString());
	}

	@Override
	public void setVisibility(boolean visible) {
		label.setVisible(visible);
	}

	@Override
	public void setEditability(boolean editable) {
		//TODO review this
	}
}