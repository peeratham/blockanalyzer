package main.java.preprocess;

import java.util.ArrayList;
import java.util.List;

import main.java.visitor.VisitFailure;
import main.java.visitor.Visitor;



public class Block implements Visitable {

	private String spec;
	private String category;
	private String command;
	private ArrayList<Object> args;
	private String shape;
	private List<Object> defaults;
	private BlockSpec blockSpec;

	public Block(ArrayList<String> commandArray) {
		this.spec = commandArray.get(0);
		this.category = commandArray.get(1);
		this.command = commandArray.get(2);
	}

	public Block(String command, BlockSpec blockSpec, ArrayList<Object> args) {
		this.command = command;
		this.args = args;
		this.blockSpec = blockSpec;
	}

	@Override
	public String toString() {
		return "Block [command="+command+",spec="+blockSpec+",args="+args + "]";
	}
	
	public boolean hasCommand(String command){
		return this.command.equals(command);
	}

	public String getSpec() {
		return spec;
	}

	public String getCommand() {
		return command;
	}

	public Object getArgs() {
		return this.args;
	}

	@Override
	public void accept(Visitor v) throws VisitFailure {
		v.visitBlock(this);
		
	}

}
