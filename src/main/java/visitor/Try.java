package main.java.visitor;

public class Try extends Choice {

	public Try(Visitor v) {
		super(v, new Identity());
	}

}

