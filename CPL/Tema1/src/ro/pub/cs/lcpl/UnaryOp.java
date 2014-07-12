package ro.pub.cs.lcpl;

public class UnaryOp extends Expression {
	private Expression e1;

	public Expression getE1() {
		return e1;
	}

	public void setE1(Expression e1) {
		this.e1 = e1;
	}

	public UnaryOp(int lineNumber, Expression e1) {
		super(lineNumber);
		this.e1 = e1;
	}
	
}
