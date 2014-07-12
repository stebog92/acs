package ro.pub.cs.lcpl;

/** An explicit cast
 * { <i>type</i> <i>expression</i> }
 */

import java.io.PrintStream;
import java.util.*;
public class Cast extends Expression {
	private String type;
	private Expression e1;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Expression getE1() {
		return e1;
	}
	public void setE1(Expression e1) {
		this.e1 = e1;
	}
	public Cast(int lineNumber, String type, Expression e1) {
		super(lineNumber);
		this.type = type;
		this.e1 = e1;
	}
	public Cast() {}

    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar, LCPLClass _class, Program program) {
        e1.setObjIndex(getObjIndex());
        String t = e1.emitIR(os, s,  nextVar, _class, program);
        if (!(e1 instanceof IntConstant)) {
            nextVar = Integer.parseInt(t.substring(1)) + 1;
        }
        
        //System.out.println(e1.getType() + " " +type);
        String _type = "";
        if (e1 instanceof StaticDispatch) {
            _type = ((StaticDispatch)e1).getMethod().getReturnType();
        } else {
            _type = e1.getTypeData().getName();
        }
        if (type.equals("String") && e1 instanceof IntConstant) {
            os.println("\t%" + nextVar + " = call %struct.TString* @__lcpl_intToString(i32 " + t + ")");
        } else if (type.equals("String") && _type.equals("Int")) {
            os.println("\t%" + nextVar + " = call %struct.TString* @__lcpl_intToString(i32 %" + (nextVar - 1) + ")");
        } else {
            os.println("\t%" + nextVar + " = bitcast %struct.T" + _type + "* %" + (nextVar - 1) + " to i8*");
            nextVar++;
            os.println("\t%" + nextVar + " = call i8* @__lcpl_cast(i8* %" + (nextVar - 1) 
                    + ", %struct.__lcpl_rtti* bitcast (%struct.R" + type + "* @R" + type + " to %struct.__lcpl_rtti*))");
            nextVar++;
            os.println("\t%" + nextVar + " = bitcast i8* %" + (nextVar - 1) + " to %struct.T" + type + "*");
        }
        return "%" + nextVar;
    }
}
