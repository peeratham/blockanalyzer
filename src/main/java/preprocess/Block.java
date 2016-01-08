package main.java.preprocess;

import java.util.ArrayList;
import java.util.List;

import main.java.visitor.VisitFailure;
import main.java.visitor.Visitor;



public class Block implements Visitable {
	
	private String command;
	private ArrayList<Object> args;
	private BlockSpec blockSpec;



	public Block(String command, BlockSpec blockSpec, ArrayList<Object> args) {
		this.command = command;
		this.args = args;
		this.blockSpec = blockSpec;
	}

	public BlockSpec getBlockSpec() {
		return blockSpec;
	}

	@Override
	public String toString() {
		return "Block [command="+command+",spec="+blockSpec+",args="+args + "]";
	}
	
	public String stringify() {
//		System.out.println(String.format("%s is %d years old, er, young", "Al", 45));
//		blockSpec.getSpec()
		return null;
		
	}
	
	public boolean hasCommand(String command){
		return this.command.equals(command);
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
