package main.java.analyzer;

import java.util.Map;

import main.java.preprocess.Visitable;
import main.java.visitor.AbstractPattern;
import main.java.visitor.VisitFailure;
import main.java.visitor.Visitor;

public class VisitablePattern extends AbstractPattern implements Visitable {

	public VisitablePattern(Visitable visitable) {
		
	}

	@Override
	public void accept(Visitor v) throws VisitFailure {
	}

	@Override
	public boolean match(Object term, Map bindings) {
		return false;
	}


}
