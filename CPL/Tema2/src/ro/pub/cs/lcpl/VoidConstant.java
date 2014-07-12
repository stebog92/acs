package ro.pub.cs.lcpl;

/** the constant "void" */
public class VoidConstant extends Expression {

    public VoidConstant(int lineNumber) {
        super(lineNumber);
    }

    public VoidConstant() {}

    public void handleNode(Program program, LCPLClass _class, Method method,
            LocalDefinition localDefinition) {
        setTypeData(program.getNullType());
    }
}
