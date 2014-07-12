package ro.pub.cs.lcpl;

/* A generic Abstract Syntax Tree node */

public class TreeNode {
	private int lineNumber;

	public TreeNode(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
}
