package main.java.visitor;

public class DownUp extends Sequence {
	public DownUp(Visitor down, Visitor stop, Visitor up) {
		super(null, up);
		first = new Sequence(down, new Choice(stop, new All(this)));
	}
}
