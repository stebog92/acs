package ro.pub.cs.lcpl;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/** A generic Abstract Syntax Tree node */
public  class TreeNode {
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
	
	public void emitIR(PrintStream os){};
	public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar, LCPLClass _class, Program program){return null;};
	
	public static final String KWD_INT = "Int";

}
