package main.java.visitor;

import main.java.preprocess.Block;
import main.java.preprocess.ScratchProject;
import main.java.preprocess.Script;
import main.java.preprocess.Scriptable;

public class Choice implements Visitor {
	Visitor first;
	Visitor then;
	public Choice(Visitor first, Visitor then){
		this.first = first;
		this.then = then;
	}
	
	@Override
	public void visitProject(ScratchProject scratchProject) throws VisitFailure {
		try { scratchProject.accept(first);}
		catch (VisitFailure f){
			scratchProject.accept(then);
		}
	}

	@Override
	public void visitScript(Script script) throws VisitFailure {
		try { script.accept(first);}
		catch (VisitFailure f){
			script.accept(then);
		}
	}

	@Override
	public void visitScriptable(Scriptable scriptable) throws VisitFailure {
		try { scriptable.accept(first);}
		catch (VisitFailure f){
			scriptable.accept(then);
		}
		
	}

	@Override
	public void visitBlock(Block block) throws VisitFailure {
		try { block.accept(first);}
		catch (VisitFailure f){
			block.accept(then);
		}
	}

}
