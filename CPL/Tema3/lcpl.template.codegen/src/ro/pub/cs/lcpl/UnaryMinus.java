package ro.pub.cs.lcpl;


import java.util.*;
import java.io.PrintStream;
/** - <i>expression</i>, in unary operator context */
public class UnaryMinus extends UnaryOp {

	public UnaryMinus(int lineNumber, Expression e1) {
		super(lineNumber, e1);
	}
	public UnaryMinus() {}


    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar, LCPLClass _class, Program program) {
        getE1().setObjIndex(getObjIndex());
        String ex1 = getE1().emitIR(os, s, nextVar, _class, program);
        if (!(getE1() instanceof StringConstant || getE1() instanceof IntConstant)) {
            nextVar = Integer.parseInt(ex1.substring(1)) + 1;
        }
        
        os.print("\t%" + nextVar + " = sub i32 0, " + ex1 + "\n");
        return "%" + nextVar;
    }
	
}
