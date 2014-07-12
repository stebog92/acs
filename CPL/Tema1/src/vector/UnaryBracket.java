package vector;

import ro.pub.cs.lcpl.*;

public class UnaryBracket extends Expression {
	
	private Expression value;
	private Expression expression;
	public Expression getValue() {
		return this.value;
	}
	public Expression getExpression() {
		return this.expression;
	}
	public UnaryBracket(int lineNumber, Expression e, Expression value) {
		super(lineNumber);
		this.value = value;
		this.expression = e;
	}

}
