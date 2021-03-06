package main.java.preprocess;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

public class BlockSpec {
	private String category;
	private String shape;
	private String command;
	private String spec;
	private List<Object> defaults;
	private String flag;

	public BlockSpec(String category, String shape, String name, String spec, List<Object> defaults) {
		this.category = category;
		this.shape = shape;
		this.command = name;
		this.spec = spec;
		this.defaults = defaults;
	}

	public BlockSpec() {
		// TODO Auto-generated constructor stub
	}

	public BlockSpec(String category,String flag, String shape, String name, String spec, List<Object> defaults) {
		this.category = category;
		this.flag = flag;
		this.shape = shape;
		this.command = name;
		this.spec = spec;
		this.defaults = defaults;
	}

	public String getShape() {
		return shape;
	}

	public String getFlag() {
		return flag;
	}

	public String getSpec() {
		return spec;
	}
	
	public static BlockSpec parseCustomBlockSpec(JSONArray jsonCustomBlock){
		BlockSpec blockSpec = new BlockSpec();
		blockSpec.command = (String)jsonCustomBlock.get(0);
    	blockSpec.shape = CommandLoader.SHAPE_FLAGS.get("h");
    	blockSpec.spec = (String)jsonCustomBlock.get(1);
    	blockSpec.category = CommandLoader.CATEGORY_IDS.get(10);
    	blockSpec.defaults = (List<Object>) jsonCustomBlock.get(3);
		return blockSpec;
		
	}
	
	

	@Override
	public String toString() {
		return "BlockSpec [category=" + category + ", shape=" + shape
				+ ", spec=" + spec + "]";
	}

	public List<Object> getDefaults() {
		return defaults;
	}
}
