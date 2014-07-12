package ro.pub.cs.lcpl;

/* An integer constant */

public class IntConstant extends Expression {
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public IntConstant(int lineNumber, int value) {
		super(lineNumber);
		this.value = value;
	}
	
}
