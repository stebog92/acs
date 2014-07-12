package ro.pub.cs.lcpl;

/** while <i>condition</i> loop <i>loopBody</i> end; */
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
	public WhileStatement() {}
	
    public void handleNode(Program program, LCPLClass _class, Method method,
            LocalDefinition localDefinition) {
        this.condition.handleNode(program, _class, method, localDefinition);
        if (this.condition.getTypeData() != program.getIntType()) {
            System.err.println("Error in line " + getLineNumber() + " : While condition must be Int");
            System.exit(0);
        }
        this.loopBody.handleNode(program, _class, method, localDefinition);

        setTypeData(program.getNoType());
    }

}
