package ro.pub.cs.lcpl;


/** new <i>type</i> */
public class NewObject extends Expression {
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public NewObject(int lineNumber, String type) {
		super(lineNumber);
		this.type = type;
	}
	public NewObject() {}

    public void handleNode(Program program, LCPLClass _class, Method method,
            LocalDefinition localDefinition) {
        Type t;
        if (type.equals("Int")) {
            t = program.getIntType();
        } else if (type.equals("void")) {
            t = program.getNoType();
        } else {
            t = program.getClass(this.type);
        }
        if (t == null) {
            System.err.println("Error in line " + this.getLineNumber() + " : Class " + type + " not found.");
            System.exit(0);
        }

        setTypeData(t);
    }
}
