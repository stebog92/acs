package ro.pub.cs.lcpl;

/** A formal parameter in the declaration of a method */
public class FormalParam extends TreeNode implements Variable {
	private String name;
	private String type;

	/** A reference to the type of the formal parameter. This can be
	 * <li> Program.intType - for Int parameters
	 * <li> An LCPLClass - for class parameters */
	private Type variableType;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Type getVariableType() {
		return variableType;
	}
	public void setVariableType(Type typeData) {
		this.variableType = typeData;
	}
	public FormalParam(String name, String type) {
		this.name = name;
		this.type = type;
	}

    public Type getVType() {
        return this.variableType;
    }
	public FormalParam() {}
	
    public void handleNode(Program program, LCPLClass _class, Method method,
            LocalDefinition localDefinition) {

        if (this.type.equals("Int")) {
            this.variableType = program.getIntType();
        } else if (this.type.equals("void")) {
            this.variableType = program.getNoType();
        } else {
            this.variableType = program.getClass(this.type);
        }

        if (this.variableType == null) {
            System.err.println("Error in line " + getLineNumber() + " : Class " + this.type + " not found.");
            System.exit(0);
        }
    }

}
