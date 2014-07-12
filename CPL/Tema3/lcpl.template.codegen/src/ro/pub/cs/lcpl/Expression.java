package ro.pub.cs.lcpl;

/** A generic class for any expression in the program */
public class Expression extends TreeNode {

	/** The name of the type of the expression (e.g. "Int", "String"...) */
	private String type;
    private int index;	

    private int objIndex;
	/** A reference to the type of the expression. This can be :
	 * <li> An LCPLClass - for expressions returning an object.
	 * <li> Program.intType - for expressions returning an Int.
	 * <li> Program.nullType - for expressions returning the void constant.
	 * <li> Program.noType - for expressions that do not return a value (e.g. 'while').
	 * */
	private Type typeData;

	public Type getTypeData() {
		return typeData;
	}

	public void setTypeData(Type typeData) {
		this.typeData = typeData;
		this.type = typeData.getName();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    public void setVarIndex(int index) {
        this.index = index;
    }

    public int getVarIndex() {
        return this.index;
    }

	public Expression(int lineNumber) {
		super(lineNumber);
	}

    public void setObjIndex(int index) {
        this.objIndex = index;
    }

    public int getObjIndex() {
        return this.objIndex;
    }

	
	public Expression() {}


	
}
