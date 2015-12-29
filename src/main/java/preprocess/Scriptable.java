package main.java.preprocess;

import java.util.ArrayList;
import java.util.List;

import main.java.visitor.VisitFailure;
import main.java.visitor.Visitor;

public class Scriptable implements Visitable {
	String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	List<Script> scripts;
	
	
	public Scriptable(){
		scripts = new ArrayList<Script>();
	}

	public void addScript(Script script) {
		scripts.add(script);
		
	}

	@Override
	public String toString() {
		return "Scriptable [scripts=" + scripts + "]";
	}

	public Script getScript(int index){
		return scripts.get(index);
	}

	@Override
	public void accept(Visitor v) throws VisitFailure {
		v.visitScriptable(this);
		
	}

	public int getNumScripts() {
		return scripts.size();
	}
	

}
