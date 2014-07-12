package ro.pub.cs.lcpl;

/* A generic class for all expressions in the program */

public class Expression extends TreeNode {
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Expression(int lineNumber) {
		super(lineNumber);
	}	
	
}
