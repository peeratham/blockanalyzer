package main.java.analyzer;


import main.java.preprocess.ScratchProject;
import main.java.visitor.VisitFailure;
import main.java.visitor.Visitor;

public class BaseAnalyzer implements Analyzer {
	public ScratchProject project;
	public Visitor analysisVisitor;
	
	public BaseAnalyzer(ScratchProject project, Visitor v){
		this.project = project; 
		this.analysisVisitor = v;
	}
	
	@Override
	public void analyze() {
		  try {
				project.accept(analysisVisitor);
			} catch (VisitFailure e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
