package main.java.preprocess;

import java.util.List;

import main.java.visitor.VisitFailure;
import main.java.visitor.Visitor;

public class Script implements Visitable {

	private List<Block> blocks;
	private String position;
	private int xPos;
	private int yPos;

	
	
	public Script() {}

	public List<Block> getBlocks(){
		return blocks;
	}

	@Override
	public String toString() {
		return "Script [script=" + blocks + ", position=" + xPos +","+yPos+ "]";
	}

	public void setPosition(int x, int y) {
		this.xPos = x;
		this.yPos = y;
		
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
		
	}

	@Override
	public void accept(Visitor v) throws VisitFailure {
		v.visitScript(this);
		
	}

}
