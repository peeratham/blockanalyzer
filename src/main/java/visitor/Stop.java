package main.java.visitor;

import main.java.preprocess.Block;
import main.java.preprocess.ScratchProject;
import main.java.preprocess.Script;
import main.java.preprocess.Scriptable;

public class Stop implements Visitor {

	@Override
	public void visitProject(ScratchProject scratchProject) throws VisitFailure {
		throw new VisitFailure();	//always fail to stop; need condition when stop succeed to return;
	}

	@Override
	public void visitScript(Script script) throws VisitFailure {
		throw new VisitFailure();	//always fail to stop; need condition when stop succeed to return;
	}

	@Override
	public void visitScriptable(Scriptable scriptable) throws VisitFailure {
		throw new VisitFailure();	//always fail to stop; need condition when stop succeed to return;
		
	}

	@Override
	public void visitBlock(Block block) throws VisitFailure {
		throw new VisitFailure();
	}

}
