package ro.pub.cs.lcpl;

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

    public void handleNode(Program program, LCPLClass _class, Method method,
            LocalDefinition localDefinition) {
        stringExpr.handleNode(program, _class, method, localDefinition);
        startPosition.handleNode(program, _class, method, localDefinition);
        endPosition.handleNode(program, _class, method, localDefinition);

        setTypeData(program.getStringType());
    }
	

}
