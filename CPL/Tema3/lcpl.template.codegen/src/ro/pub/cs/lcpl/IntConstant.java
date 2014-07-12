package ro.pub.cs.lcpl;

import java.util.*;
import java.io.PrintStream;
/** An integer constant */
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
	public IntConstant() {}

    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar, LCPLClass _class, Program program) {
        return "" + this.value;
    }

}
