package ro.pub.cs.lcpl;
import java.io.PrintStream;
import java.util.*;

/** new <i>type</i> */
public class NewObject extends Expression {
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public NewObject(int lineNumber, String type) {
		super(lineNumber);
		this.type = type;
	}
	public NewObject() {}

    
    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar, LCPLClass _class, Program program) {
        os.println("\t%" + nextVar + " = call i8* @__lcpl_new(%struct.__lcpl_rtti* bitcast (%struct.R" + type
                + "* @R" + type + " to %struct.__lcpl_rtti*))");
        nextVar++;
        os.println("\t%" + nextVar + " = bitcast i8* %" + (nextVar - 1) + " to %struct.T" + type + "*");
        return "%" + nextVar;
    }
	

}
