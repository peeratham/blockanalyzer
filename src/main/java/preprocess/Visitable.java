package main.java.preprocess;

import main.java.visitor.VisitFailure;
import main.java.visitor.Visitor;

public interface Visitable {
	public void accept(Visitor v) throws VisitFailure;
}
