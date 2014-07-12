package ro.pub.cs.lcpl;

import java.util.HashMap;
import java.io.PrintStream;
/** <i>stringExpression</i> [ <i>start</i> , <i>stop</i> ] */
public class SubString extends Expression {
	private Expression stringExpr;
	private Expression startPosition;
	private Expression endPosition;
	public Expression getStringExpr() {
		return stringExpr;
	}
	public void setStringExpr(Expression stringExpr) {
		this.stringExpr = stringExpr;
	}
	public Expression getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(Expression startPosition) {
		this.startPosition = startPosition;
	}
	public Expression getEndPosition() {
		return endPosition;
	}
	public void setEndPosition(Expression endPosition) {
		this.endPosition = endPosition;
	}
	public SubString(int lineNumber, Expression stringExpr,
			Expression startPosition, Expression endPosition) {
		super(lineNumber);
		this.stringExpr = stringExpr;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}
	public SubString() {}
    
    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar, LCPLClass _class, Program program) {
        //startPosition.setObjIndex(getObjIndex());
        String ex1 = startPosition.emitIR(os, s, nextVar, _class, program);
        if (!(startPosition instanceof IntConstant)) {
            nextVar = Integer.parseInt(ex1.substring(1)) + 1;
        }
        
        String ex2 = endPosition.emitIR(os, s, nextVar, _class, program);
        if (!(endPosition instanceof IntConstant)) {
            nextVar = Integer.parseInt(ex2.substring(1)) + 1;
        }
        
        String string = stringExpr.emitIR(os, s, nextVar, _class, program);
        if (!(stringExpr instanceof StringConstant)) {
            nextVar = Integer.parseInt(string.substring(1)) + 1;
        }

        os.print("\t%" + nextVar  + " = call %struct.TString* @M6_String_substring(%struct.TString* ");
        if (stringExpr instanceof StringConstant) {
            os.print(string);
        } else {
            os.print("%" + string);
        }

        if (startPosition instanceof IntConstant) {
            os.print(", i32 " + ex1); 
        } else {
            os.print(", i32 %" + ex1); 
        }
        
        if (endPosition instanceof IntConstant) {
            os.print(", i32 " + ex2); 
        } else {
            os.print(", i32 " + ex2); 
        }
        os.println(")");
        
        return "%" + getVarIndex();
    }
	

}
