package main.java.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.analyzer.VisitablePattern;
import main.java.preprocess.Block;
import main.java.preprocess.ScratchProject;
import main.java.preprocess.Script;
import main.java.preprocess.Scriptable;
import main.java.preprocess.Visitable;

public class Match implements Visitor {
	private VisitablePattern pattern;
	private List maps = new ArrayList<>();
	public List getMaps() {
		return maps;
	}
	
	public Match(Visitable visitable){
		this.pattern = new VisitablePattern(visitable);
	}
	
	public Match(VisitableScriptPattern visitable){
		this.pattern = visitable;
	}
	
	public void addVariable(Object variable) {
		pattern.addVariable(variable);
	}
	
	@Override
	public void visitProject(ScratchProject scratchProject) throws VisitFailure {
	}

	@Override
	public void visitScriptable(Scriptable scriptable) throws VisitFailure {
	}

	@Override
	public void visitScript(Script term) throws VisitFailure {

//		Map bindings = new HashMap();
//		if(term instanceof Script){
//			if (((VisitableScriptPattern)pattern).matchScript(term, bindings)) {
//				maps.add(bindings);	//need to match all variable bindings to be added
//			} else {
//				throw new VisitFailure("No match");
//			}
//		}else{
//			throw new VisitFailure("Cannot compare objects of different types");
//		}
		Block firstBlockOfTerm = term.getBlocks().get(0);
		visitBlock(firstBlockOfTerm);

	}
	
	//TODO fix block to work with getChild()
	@Override
	public void visitBlock(Block blockTerm) throws VisitFailure {
		Map bindings = new HashMap();
		if(blockTerm instanceof Block){
			if (((VisitableScriptPattern)pattern).match(blockTerm, bindings)) {
				maps.add(bindings);	//need to match all variable bindings to be added
			} else {
				throw new VisitFailure("No match");
			}
		}else{
			throw new VisitFailure("Cannot compare objects of different types");
		}
	}
	
	public Visitable visit(Visitable term) throws VisitFailure {
		Map bindings = new HashMap();
		if (pattern.match(term, bindings)) {
			maps.add(bindings);
			return term;
			
		} else {
			throw new VisitFailure("No match");
		}
	}

}
