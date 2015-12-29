package main.java.visitor;

import main.java.preprocess.Block;
import main.java.preprocess.ScratchProject;
import main.java.preprocess.Script;
import main.java.preprocess.Scriptable;

public class Printer implements Visitor {

	@Override
	public void visitProject(ScratchProject scratchProject) throws VisitFailure {
		System.out.println("project");
	}

	@Override
	public void visitScript(Script script) throws VisitFailure {
		System.out.println("script");
	}

	@Override
	public void visitScriptable(Scriptable scriptable) throws VisitFailure {
		System.out.println(scriptable.getName());
	}

	@Override
	public void visitBlock(Block block) throws VisitFailure {
		System.out.println(block);
		
	}

}
