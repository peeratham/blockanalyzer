package main.java.visitor;

import main.java.preprocess.Block;
import main.java.preprocess.ScratchProject;
import main.java.preprocess.Script;
import main.java.preprocess.Scriptable;

public class Sequence implements Visitor{
	protected Visitor first;
	protected Visitor then;
	
	public Sequence(Visitor first, Visitor then){
		this.first = first;
		this.then = then;
	}
	
//	@Override
//	public void visit(Object node) throws VisitFailure {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void visitProject(ScratchProject scratchProject) throws VisitFailure {
		scratchProject.accept(first);
		scratchProject.accept(then);
		
	}

	@Override
	public void visitScript(Script script) throws VisitFailure {
		script.accept(first);
		script.accept(then);
		
	}

	@Override
	public void visitScriptable(Scriptable scriptable) throws VisitFailure {
		scriptable.accept(first);
		scriptable.accept(then);
	}

	@Override
	public void visitBlock(Block block) throws VisitFailure {
		block.accept(first);
		block.accept(then);
	}

}
