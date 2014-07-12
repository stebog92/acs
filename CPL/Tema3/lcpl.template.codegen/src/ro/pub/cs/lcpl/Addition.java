package ro.pub.cs.lcpl;


import java.util.*;
import java.io.PrintStream;
/** <i>expression</i> + <i>expression</i> */
public class Addition extends BinaryOp {

	public Addition(int lineNumber, Expression e1, Expression e2) {
		super(lineNumber, e1, e2);
	}

	public Addition() {}


    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar, LCPLClass _class, Program program) {
        getE1().setObjIndex(getObjIndex());
        String ex1 = getE1().emitIR(os, s, nextVar, _class, program);
        if (!(getE1() instanceof StringConstant || getE1() instanceof IntConstant)) {
            nextVar = Integer.parseInt(ex1.substring(1)) + 1;
        }
        
        getE2().setObjIndex(getObjIndex());
        String ex2 = getE2().emitIR(os, s, nextVar, _class, program);
        if (!(getE2() instanceof StringConstant || getE2() instanceof IntConstant)) {
            nextVar = Integer.parseInt(ex2.substring(1)) + 1;
        }

        if (getE1().getType().equals("String")) {
            os.println("\t%" + (nextVar) + " = call %struct.TString* @M6_String_concat(%struct.TString* " + ex1 + ", %struct.TString* " + ex2 + ")");
        } else {
            os.print("\t%" + (nextVar) + " = add i32 " + ex1 + ", " + ex2 + "\n");
        }
        return "%" + (nextVar);
    }


}
