package main.java.preprocess;

import java.util.List;

public class BlockSpec {
	private String category;
	private String shape;
	private String command;
	private String spec;
	private List<Object> defaults;

	public BlockSpec(String category, String shape, String name, String spec, List<Object> defaults) {
		this.category = category;
		this.shape = shape;
		this.command = name;
		this.spec = spec;
		this.defaults = defaults;
	}

	public String getShape() {
		return shape;
	}

	public String getSpec() {
		return spec;
	}
	
	

	@Override
	public String toString() {
		return "BlockSpec [category=" + category + ", shape=" + shape
				+ ", spec=" + spec + "]";
	}
}
