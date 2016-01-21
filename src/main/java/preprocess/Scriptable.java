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
		StringBuilder sb = new StringBuilder();
		for (Script scrpt : scripts) {
			sb.append(scrpt);
			sb.append("\n");
		}
		return sb.toString();
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
