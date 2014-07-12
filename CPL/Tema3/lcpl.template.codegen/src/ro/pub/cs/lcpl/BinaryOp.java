package ro.pub.cs.lcpl;

import java.io.PrintStream;
import java.util.*;
/** A generic two-operand operation
 * <i>expression</i> <i>operand</i> <i>expression</i>
 */
public class BinaryOp extends Expression {
	private Expression e1;
	private Expression e2;
	public Expression getE1() {
		return e1;
	}
	public void setE1(Expression e1) {
		this.e1 = e1;
	}
	public Expression getE2() {
		return e2;
	}
	public void setE2(Expression e2) {
		this.e2 = e2;
	}
	public BinaryOp(int lineNumber, Expression e1, Expression e2) {
		super(lineNumber);
		this.e1 = e1;
		this.e2 = e2;
	}
	public BinaryOp() {}
	

}
