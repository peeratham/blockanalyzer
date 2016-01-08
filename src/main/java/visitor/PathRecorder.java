package main.java.visitor;

import java.util.Stack;

import main.java.preprocess.Block;
import main.java.preprocess.ScratchProject;
import main.java.preprocess.Script;
import main.java.preprocess.Scriptable;

public class PathRecorder extends Sequence implements PathKeeper{
	private Stack<String> st = new Stack<String>();
	public PathRecorder(Visitor emitter) {
		super(null, null);
				
		class PathRecorderEnter implements Visitor{

			@Override
			public void visitProject(ScratchProject scratchProject)
					throws VisitFailure {} 
			@Override
			public void visitScriptable(Scriptable scriptable)
					throws VisitFailure {
				st.push(scriptable.getName());
			}
			@Override
			public void visitScript(Script script) throws VisitFailure {
				st.push("Script@pos("+script.getPosition()[0]+","+script.getPosition()[1]+")");
			}
			@Override
			public void visitBlock(Block block) throws VisitFailure {
				st.push(block.getCommand()+":"+block.getBlockSpec().getSpec()+":"+block.getArgs());
			}
		}
		
		class PathRecorderExit implements Visitor {

			@Override
			public void visitProject(ScratchProject scratchProject)
					throws VisitFailure {}

			@Override
			public void visitScriptable(Scriptable scriptable)
					throws VisitFailure { st.pop();}

			@Override
			public void visitScript(Script script) throws VisitFailure { st.pop();}

			@Override
			public void visitBlock(Block block) throws VisitFailure { st.pop();}
			
		}
		
		((PathKeeper)emitter).setPath(st);
		first = new Sequence(new PathRecorderEnter(), new Choice(emitter, new All(this)));
		then = new PathRecorderExit();
	}
	
	public Stack<String> getPath(){
		return st;
	}

	@Override
	public void setPath(Stack<String> path) {
		this.st = path;
		
	}
	
}
