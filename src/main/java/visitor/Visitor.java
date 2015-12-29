package main.java.visitor;

import main.java.preprocess.Block;
import main.java.preprocess.ScratchProject;
import main.java.preprocess.Script;
import main.java.preprocess.Scriptable;

public interface Visitor {
//	public void visit(Object node )throws VisitFailure; //leaf node
	public void visitProject(ScratchProject scratchProject) throws VisitFailure;
	public void visitScriptable(Scriptable scriptable) throws VisitFailure;
	public void visitScript(Script script) throws VisitFailure;
	public void visitBlock(Block block) throws VisitFailure;
}
