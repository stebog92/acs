package ro.pub.cs.lcpl;

/** <i>expression</i> == <i>expression</i> */
public class EqualComparison extends BinaryOp {

	public EqualComparison(int lineNumber, Expression e1, Expression e2) {
		super(lineNumber, e1, e2);
	}
	public EqualComparison() {}

    public void handleNode(Program program, LCPLClass _class, Method method,
            LocalDefinition localDefinition) {
        getE1().handleNode(program, _class, method, localDefinition);
        getE2().handleNode(program, _class, method, localDefinition);

        if (getE1().getTypeData() == program.getNullType() && getE2().getTypeData() instanceof LCPLClass) {
            Cast cast = new Cast(getE2().getLineNumber(), program.getObjectType().getName(), getE2());
            cast.setTypeData(program.getObjectType());
            setE2(cast);
        } else if (getE2().getTypeData() == program.getNullType() && getE1().getTypeData() instanceof LCPLClass){
            Cast cast = new Cast(getE1().getLineNumber(), program.getObjectType().getName(), getE1());
            cast.setTypeData(program.getObjectType());
            setE1(cast);
        } else if (getE1().getTypeData() == program.getStringType() && getE2().getTypeData() == program.getIntType()) {
            Cast cast = new Cast(getE2().getLineNumber(), program.getStringType().getName(), getE2());
            cast.setTypeData(program.getStringType());
            setE2(cast);
        } else if (getE2().getTypeData() == program.getStringType() && getE1().getTypeData() == program.getIntType()) {
            Cast cast = new Cast(getE1().getLineNumber(), program.getStringType().getName(), getE1());
            cast.setTypeData(program.getStringType());
            setE1(cast);
        } else if (getE1().getTypeData() != getE2().getTypeData()) {
            System.err.println("Error in line " + this.getLineNumber() + " : Invalid type of parameters for == expression");
        }
        setTypeData(program.getIntType());
    }

}
