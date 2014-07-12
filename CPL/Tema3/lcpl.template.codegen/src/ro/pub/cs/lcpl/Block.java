package ro.pub.cs.lcpl;

import java.util.*;
import java.io.PrintStream;

/** A block of zero, one or more expressions, executed sequentially */
public class Block extends Expression {
	List<Expression> expressions;

	public List<Expression> getExpressions() {
		return expressions;
	}

	public void setExpressions(List<Expression> expressions) {
		this.expressions = expressions;
	}

	public Block(int lineNumber, List<Expression> expressions) {
		super(lineNumber);
		this.expressions = expressions;
	}
	public Block() {}

    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar,LCPLClass _class, Program program) {
        boolean ok = false;
        String value = "";
        for(Expression expr : this.expressions) {
            String t = expr.emitIR(os, s, nextVar, _class, program);
            if(!(expr instanceof IntConstant)) {
                nextVar = Integer.parseInt(t.substring(1)) + 1;
            } else {
                value = t;
                ok = true;
            }
        }
        String returnValue = "";
        if (ok) {
            returnValue = "%" + (nextVar - 1);
        } else {
            returnValue = "%" + (nextVar -1) ;
        }
        returnValue += "_" + value;
        return returnValue;
    }

}
