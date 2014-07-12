package ro.pub.cs.lcpl;

import java.io.PrintStream;
import java.util.*;
/** <i>expression</i> == <i>expression</i> */
public class EqualComparison extends BinaryOp {

	public EqualComparison(int lineNumber, Expression e1, Expression e2) {
		super(lineNumber, e1, e2);
	}
	public EqualComparison() {}

    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar, LCPLClass _class, Program program) {
        getE1().setObjIndex(getObjIndex());
        String ex1 = "";
        if (getE1().getType().equals("void")) {
            ex1 = "null";
        } else {
            ex1 = getE1().emitIR(os, s, nextVar, _class, program);
        }
        if (!(getE1() instanceof StringConstant || getE1() instanceof IntConstant || getE1().getType().equals("void"))) {
            nextVar = Integer.parseInt(ex1.substring(1)) + 1;
        }
        
        getE2().setObjIndex(getObjIndex());
        String ex2 = "";
        if (getE2().getType().equals("void")) {
            ex2 = "null";
        } else {
            ex2 = getE2().emitIR(os, s, nextVar, _class, program);
        }
        if (!(getE2() instanceof StringConstant || getE2() instanceof IntConstant || getE2().getType().equals("void"))) {
            nextVar = Integer.parseInt(ex2.substring(1)) + 1;
        }
       
        if (ex1.equals("null") || ex2.equals("null")) {
            if (!ex1.equals("null")) {
                os.println("\t%" + nextVar + " = bitcast %struct.T" + getE1().getType() + "* " + ex1 + " to i8*");
                nextVar++;
                ex1 = "%" + (nextVar - 1);
            }
            if (!ex2.equals("null")) {
                os.println("\t%" + nextVar + " = bitcast %struct.T" + getE2().getType() + "* " + ex2 + " to i8*");
                nextVar++;
                ex2 = "%" + (nextVar - 1);
            }
            os.print("\t%" + nextVar + " = icmp eq i8* " + ex1 + ", " + ex2 + "\n");
        } else {
            os.print("\t%" + nextVar + " = icmp eq i32 " + ex1 + ", " + ex2 + "\n");
        }
        nextVar++;
        os.println("\t%" + nextVar + " = zext i1 %" + (nextVar - 1) + " to i32");
        return "%" + nextVar;
    }
	
}
