package main.java.analyzer;

import main.java.preprocess.Block;
import main.java.preprocess.ScratchProject;
import main.java.preprocess.Script;
import main.java.preprocess.Scriptable;
import main.java.visitor.VisitFailure;
import main.java.visitor.Visitor;

public class MetadataVisitor implements Visitor {

	@Override
	public void visitProject(ScratchProject scratchProject) throws VisitFailure {
		

	}

	@Override
	public void visitScriptable(Scriptable scriptable) throws VisitFailure {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitScript(Script script) throws VisitFailure {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitBlock(Block block) throws VisitFailure {
		// TODO Auto-generated method stub

	}

}
