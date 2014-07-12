package ro.pub.cs.lcpl;

/* A string constant literal */

public class StringConstant extends Expression {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public StringConstant(int lineNumber, String value) {
		super(lineNumber);
		this.value = value;
	}
	
}
