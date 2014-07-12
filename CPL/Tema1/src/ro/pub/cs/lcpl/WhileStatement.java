package ro.pub.cs.lcpl;

/* while <condition> loop <loopBody> end; */

public class WhileStatement extends Expression {
	private Expression condition;
	private Expression loopBody;
	public Expression getCondition() {
		return condition;
	}
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	public Expression getLoopBody() {
		return loopBody;
	}
	public void setLoopBody(Expression loopBody) {
		this.loopBody = loopBody;
	}
	public WhileStatement(int lineNumber, Expression condition,
			Expression loopBody) {
		super(lineNumber);
		this.condition = condition;
		this.loopBody = loopBody;
	}
	
}
