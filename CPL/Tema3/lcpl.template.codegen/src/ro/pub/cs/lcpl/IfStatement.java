package ro.pub.cs.lcpl;

import java.util.*;
import java.io.PrintStream;
/** if <i>expression</i> then <i>expression</i> else <i>expression</i> end; */
public class IfStatement extends Expression {
	/** Conditional expression : if <i>expression</i> then */
	private Expression condition;
	
	/** Expression evaluated if condition is true */
	private Expression ifExpr;

	/** Expression evaluated if condition is false; or null if the else branch is missing. */
	private Expression thenExpr;
	
	public Expression getCondition() {
		return condition;
	}
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	public Expression getIfExpr() {
		return ifExpr;
	}
	public void setIfExpr(Expression ifExpr) {
		this.ifExpr = ifExpr;
	}
	public Expression getThenExpr() {
		return thenExpr;
	}
	public void setThenExpr(Expression thenExpr) {
		this.thenExpr = thenExpr;
	}
	public IfStatement(int lineNumber, Expression condition, Expression ifExpr,
			Expression thenExpr) {
		super(lineNumber);
		this.condition = condition;
		this.ifExpr = ifExpr;
		this.thenExpr = thenExpr;
	}
	public IfStatement() {}

    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar, LCPLClass _class, Program program) {
        int l1 = -1, l2 = -1, l3 = -1, lastLabel1 = -1, lastLabel2 = -1;
        String v1 = "", v2 = "";
        String cond = condition.emitIR(os, s, nextVar, _class, program);
        nextVar = Integer.parseInt(cond.substring(1)) + 1;

        os.println("\t%" + nextVar +" = icmp ne i32 %" + (nextVar - 1) + ", 0");
        nextVar++;
        l1 = program.getNextLabel();
        l2 = program.getNextLabel();
        l3 = program.getNextLabel();
        os.println("\tbr i1 %" + (nextVar - 1) + ", label %L" + l1 + ", label %L" + l2);
        os.println("L" + l1 + ":");

        String _ifExpr = ifExpr.emitIR(os, s, nextVar, _class, program);
        System.out.println("if" + _ifExpr);
        lastLabel1 = program.getCurrentLabel() - 1; 

        v1 = _ifExpr.substring(_ifExpr.indexOf("_") + 1);
        nextVar = Integer.parseInt(_ifExpr.substring(1, _ifExpr.indexOf("_"))) + 1;
        if (v1.equals("")) {
            v1 = "%" + (nextVar - 1);
        }
        
        os.println("L" + l2 + ":");
        String _thenExpr = thenExpr.emitIR(os, s, nextVar, _class, program);
        lastLabel2 = program.getCurrentLabel() - 1; 
        nextVar = Integer.parseInt(_thenExpr.substring(1, _thenExpr.indexOf("_"))) + 1;
        v2 = "%" + (nextVar - 1);

        if (thenExpr != null) {
            os.println("\t%" + nextVar + " = phi i32 [ " + v1 + ", %L" + lastLabel1 + " ], [ " + v2 + ", %L" + lastLabel2 +" ]");
        }
        return "%" + nextVar;

    }

}
