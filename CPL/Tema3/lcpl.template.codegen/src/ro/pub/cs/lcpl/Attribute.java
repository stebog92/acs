package ro.pub.cs.lcpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.io.PrintStream;

/** An attribute of a class, declared as
 * <i>type</i> <i>name</i> = <i>expression</i> ;
 */
public class Attribute extends TreeNode implements Feature, Variable {
	private String name;
	
	/** Initialization expression for the attribute, read from the AST. Can be null. */
	private Expression init;
	private String type;

	/** A reference to the type of the attribute. This can be
	 * <li> Program.intType - for Int attributes
	 * <li> An LCPLClass - for class attributes */
	private Type typeData;
    private int index;
	
	/** 
	 * The initialization of attributes can contain method calls, attributes, or "self".
	 * These references have to be bound to a variable that points to the object being initialized.
	 * 
	 *   var
	 *     Object a = [self.doSomething];   # The expression here references "self"
	 *   end;
	 *   
	 * Create here a formal parameter that will be the target of this binding. */	
	private FormalParam attrInitSelf;

	public Type getTypeData() {
		return typeData;
	}
	public void setTypeData(Type typeData) {
		this.typeData = typeData;
	}
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
	public Expression getInit() {
		return init;
	}
	public void setInit(Expression init) {
		this.init = init;
	}

	public FormalParam getAttrInitSelf() {
		return attrInitSelf;
	}
	public void setAttrInitSelf(FormalParam attrInitSelf) {
		this.attrInitSelf = attrInitSelf;
	}
	public Attribute(int lineNumber, String name, String type, Expression init) {
		super(lineNumber);
		this.name = name;
		this.type = type;
		this.init = init;
	}
	public Attribute() {}

    public int getVarIndex() {
        return this.index;
    }	
    
    public void setVarIndex(int index) {
        this.index = index;
    }
	
    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar, LCPLClass _class, Program program) {
        os.println("\t%" + nextVar + " = load %struct.T" + _class.getName() + "** %1");
        nextVar++;
        os.println("\t%" + nextVar + " = getelementptr %struct.T" + _class.getName() + "* %" + (nextVar - 1) + ", i32 0, i32 " + this.index);
        return "%" + nextVar;
    }

}
