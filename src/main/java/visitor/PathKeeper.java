package main.java.visitor;

import java.util.Stack;

public interface PathKeeper {
	public void setPath(Stack<String> path);
	public Stack<String> getPath();
}
