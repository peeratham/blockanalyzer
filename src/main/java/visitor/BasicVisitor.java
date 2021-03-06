package main.java.visitor;

import java.util.List;

import main.java.preprocess.Block;
import main.java.preprocess.ScratchProject;
import main.java.preprocess.Script;
import main.java.preprocess.Scriptable;

public class BasicVisitor implements Visitor{

//	@Override
//	public void visit(Object node) throws VisitFailure {
		
//		if(node instanceof Block){
//			Block b= (Block) node;
//			this.visit(b.getArgs());
//			
//		}else if(node instanceof List<?>){
//			List<Object> args = (List<Object>) node;
//			for (Object o : args) {
//				this.visit(o);
//			}
//		}else{
//			String value = node.toString();
//			System.out.println(value);
//		}
//	}


	@Override
	public void visitProject(ScratchProject scratchProject) throws VisitFailure {
		for (String name : scratchProject.getScriptables().keySet()) {
			scratchProject.getScriptable(name).accept(this);;
		}
	}

	@Override
	public void visitScriptable(Scriptable scriptable) throws VisitFailure {
		for (int i = 0; i < scriptable.getNumScripts(); i++) {
			scriptable.getScript(i).accept(this);
		}
	}


	@Override
	public void visitScript(Script script) throws VisitFailure {
		for (int i = 0; i < script.getBlocks().size(); i++) {
			script.getBlocks().get(i).accept(this);
		}
	}

	@Override
	public void visitBlock(Block block) {
		System.out.println(block);
	}

}
