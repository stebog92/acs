package ro.pub.cs.lcpl;

import java.io.PrintStream;
import java.util.*;
/** ! <i>expression</i> */
public class LogicalNegation extends UnaryOp {

	public LogicalNegation(int lineNumber, Expression e1) {
		super(lineNumber, e1);
	}

	public LogicalNegation() {}

    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar, LCPLClass _class, Program program) {
        getE1().setObjIndex(getObjIndex());
        String ex1 = getE1().emitIR(os, s, nextVar, _class, program);
        if (!(getE1() instanceof StringConstant || getE1() instanceof IntConstant)) {
            nextVar = Integer.parseInt(ex1.substring(1)) + 1;
        }
        os.println("\t%" + nextVar + " = icmp ne i32 " + ex1 + ", 0");
        nextVar++;
        os.print("\t%" + nextVar + " = xor i1 %" + (nextVar - 1) + ", true \n");
        nextVar++;
        os.println("\t%" + nextVar + " = zext i1 %" + (nextVar - 1) + " to i32");
        return "%" + nextVar;
    }
	
}
