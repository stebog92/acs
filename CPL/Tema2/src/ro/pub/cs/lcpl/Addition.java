package ro.pub.cs.lcpl;

/** <i>expression</i> + <i>expression</i> */
public class Addition extends BinaryOp {

    public Addition(int lineNumber, Expression e1, Expression e2) {
        super(lineNumber, e1, e2);
    }

    public Addition() {}

    public void handleNode(Program program, LCPLClass _class, Method method,
            LocalDefinition localDefinition) {
        Cast cast;
        this.getE1().handleNode(program, _class, method, localDefinition);
        this.getE2().handleNode(program, _class, method, localDefinition);
        if (this.getE1().getTypeData() == program.getStringType() || this.getE2().getTypeData() == program.getStringType()) {
            if (this.getE1().getTypeData() != program.getStringType()) {
                if (!program.canConvert(this.getE1().getTypeData(), program.getStringType())) {
                    System.err.println("Error in line " + this.getLineNumber() + " : Cannot convert a value of type " + 
                            getE1().getTypeData().getName() + " into String");
                }
                cast = new Cast(this.getE1().getLineNumber(), program.getStringType().getName(), this.getE1());
                cast.setTypeData(program.getStringType());
                setE1(cast);
            }

            if (this.getE2().getTypeData() != program.getStringType()) {
                if (!program.canConvert(this.getE2().getTypeData(), program.getStringType())) {
                    System.err.println("Error in line " + this.getLineNumber() + " : Cannot convert a value of type " + 
                            getE2().getTypeData().getName() + " into String");
                }
                cast = new Cast(this.getE2().getLineNumber(), program.getStringType().getName(), this.getE2());
                cast.setTypeData(program.getStringType());
                setE2(cast);
            }
        } else if (this.getE1().getTypeData() == program.getIntType() && this.getE2().getTypeData() == program.getIntType()) {

        } else {
            System.err.println("Error in line " + this.getLineNumber() + " : Cannot convert '+' expression to Int or String");
            System.exit(0);
        }

        setTypeData(this.getE1().getTypeData());
    }


}
