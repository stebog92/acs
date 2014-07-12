package ro.pub.cs.lcpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/** A generic Abstract Syntax Tree node */
public class TreeNode {
	private int lineNumber;

	public TreeNode(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	public TreeNode() {}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

    public void handleNode(Program program, LCPLClass _class, Method method,
            LocalDefinition def) {
    }
	
	public static final String KWD_INT = "Int";

}
