package ql.gui.widget;

import ql.visiting.value.Value;

public class FieldOption {
	private String name;
	private Value value;

	// constructor
	public FieldOption(String name, Value value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Value getValue() {
		return value;
	}
	
	public boolean equals(FieldOption option) {
		return (name == option.name) && value.eq(option.value).getValue();
	}
	
	@Override
	public String toString() {
		return "Field option: (" + name + ", " + value + ")";
	}
}
