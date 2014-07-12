package ro.pub.cs.lcpl;

/* A symbol - local variable or class attribute */

public class Symbol extends Expression {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Symbol(int lineNumber, String name) {
		super(lineNumber);
		this.name = name;
	}
	
}
