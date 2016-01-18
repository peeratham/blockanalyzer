package main.java.analyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import main.java.preprocess.Block;
import main.java.preprocess.BlockAnalyzer;
import main.java.preprocess.ScratchProject;
import main.java.preprocess.Script;
import main.java.preprocess.Scriptable;
import main.java.visitor.Identity;
import main.java.visitor.PathKeeper;
import main.java.visitor.PathRecorder;
import main.java.visitor.Sequence;
import main.java.visitor.Stop;
import main.java.visitor.TopDown;
import main.java.visitor.VisitFailure;
import main.java.visitor.Visitor;

public class UnreachableCodeAnalyzer extends BaseAnalyzer {
	
	private BlockAnalyzer blockAnalyzer;
	private TopDown analysisVisitor;
	private Visitor detector;
	private List<String> messages = new ArrayList<String>();
	private Stack<String> path = new Stack();
	
	public UnreachableCodeAnalyzer(ScratchProject project){
		super(project, null);
		
		class BroadCastCollector implements Visitor{
			@Override
			public void visitProject(ScratchProject scratchProject)
					throws VisitFailure {}

			@Override
			public void visitScriptable(Scriptable scriptable)
					throws VisitFailure {}

			@Override
			public void visitScript(Script script) throws VisitFailure {}
			
			@Override
			public void visitBlock(Block block) throws VisitFailure {
				if(block.getCommand().contains("broadcast")){
					List<String> args = (List<String>) block.getArgs();
					messages.add(args.get(0));	
				}
			}
		}
	
		class UnreachableScriptCollector extends Stop implements PathKeeper{
			Stack<String> path;
			
			public UnreachableScriptCollector(){}
			
			public void setPath(Stack path){
				this.path = path;
			}

			@Override
			public void visitBlock(Block block) throws VisitFailure{
				if(block.getCommand().contains("whenIReceive")){
					List<String> args = (List<String>) block.getArgs();
					if (!messages.contains(args.get(0))){
						
						String fullPath = "";
						for(String elm: path) { 
							fullPath +=elm+"/";
						}
						System.out.println(fullPath);
					}
				}
				throw new VisitFailure();
				
			}

			@Override
			public Stack<String> getPath() {
				return path;
			}
			
		}

		PathRecorder pathRecorder = new PathRecorder(new UnreachableScriptCollector());
		detector = new Sequence(new TopDown(new BroadCastCollector()), pathRecorder);
		this.path = pathRecorder.getPath();
		super.analysisVisitor = detector;
		
		
	}
	


	public void getReport() {
		
		
	}

}
