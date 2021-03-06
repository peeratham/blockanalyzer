package main.java.preprocess;

import java.util.ArrayList;
import java.util.List;

import main.java.visitor.VisitFailure;
import main.java.visitor.Visitor;

public class Script implements Visitable {

	private List<Block> blocks;
	private int xPos;
	private int yPos;

	
	
	public Script() {
		blocks = new ArrayList();
		xPos = 0;
		yPos = 0;
	}

	public List<Block> getBlocks(){
		return blocks;
	}

	@Override
	public String toString() {
		String header = "Script@(x:" + xPos +", y:"+yPos+ ")\n";
		String content = "";
		for(Block b: blocks){
			content += b+"\n";
		}
		return header+content;
	}

	public void setPosition(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	public int[] getPosition(){
		return new int[]{xPos,yPos};
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
		
	}

	@Override
	public void accept(Visitor v) throws VisitFailure {
		v.visitScript(this);
		
	}

}
