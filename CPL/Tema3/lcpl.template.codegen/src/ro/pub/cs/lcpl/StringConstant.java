package ro.pub.cs.lcpl;


import java.io.PrintStream;
import java.util.*;
/** A string constant literal */
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
	public StringConstant() {}

    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar, LCPLClass _class, Program program) {

        value = value.replace("\\", "\\5C").replace("\n", "\\0A").replace("\r", "\\0D").replace("\"", "\\22").replace("\0", "\\00");
        return "@_string_constant." +  s.get(value);
    }

}
