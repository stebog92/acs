package ro.pub.cs.lcpl;

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
	
    public void handleNode(Program program, LCPLClass _class, Method method,
            LocalDefinition localDefinition) {
        this.e1.handleNode(program, _class, method, localDefinition);
        this.e2.handleNode(program, _class, method, localDefinition);
        if (!program.canConvert(this.e1.getTypeData(), program.getIntType())) {
            System.err.println("Error in line " + this.getLineNumber() + " : Cannot convert a value of type " + 
                    this.e1.getTypeData().getName() + " into Int");
            System.exit(0);
        }
        
        if (!program.canConvert(this.e2.getTypeData(), program.getIntType())) {
            System.err.println("Error in line " + this.getLineNumber() + " : Cannot convert a value of type " + 
                    this.e2.getTypeData().getName() + " into Int");
            System.exit(0);
        }
        setTypeData(program.getIntType());
    }

}
