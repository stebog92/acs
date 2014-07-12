package ro.pub.cs.lcpl;

/* if <expression> then <expression> else <expression> end; */

public class IfStatement extends Expression {
	private Expression condition;
	private Expression ifExpr;
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
	
}
