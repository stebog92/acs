package ro.pub.cs.lcpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/** A declaration of a method in a class
 * <i>name</i> <i>parameters</i> -> <i>returnType</i> : <i>body</i> end;
 */
public class Method extends TreeNode implements Feature {
	private String name;
	private List<FormalParam> parameters = new LinkedList<FormalParam>();
	
	/** Type returned by the method, or "void" if the method does not return anything. */
	private String returnType;
	
	private Expression body;
	
	/** Reference to the LCPL class that contains the current method. */
	private LCPLClass parent;
	
	/** Create a new FormalParam object for the shadow parameter "self". 
	 * This object will be referred by the self symbol in this method. 
	 * Do not add it to the parameters list. */
	private FormalParam self;
	
	/** A reference to the type of the value returned by the method. This can be
	 * <li> Program.intType - for Int parameters
	 * <li> An LCPLClass - for class parameters
	 * <li> Program.noType - for methods that do not return any type */
	private Type returnTypeData;	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LCPLClass getParent() {
		return parent;
	}
	public void setParent(LCPLClass parent) {
		this.parent = parent;
	}
	public List<FormalParam> getParameters() {
		return parameters;
	}
	public void setParameters(List<FormalParam> parameters) {
		this.parameters = parameters;
	}
	public FormalParam getSelf() {
		return self;
	}
	public void setSelf(FormalParam self) {
		this.self = self;
	}
	public String getReturnType() {
		return returnType;
	}	
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public Type getReturnTypeData() {
		return returnTypeData;
	}
	public void setReturnTypeData(Type returnTypeData) {
		this.returnTypeData = returnTypeData;
	}
	public Expression getBody() {
		return body;
	}
	public void setBody(Expression body) {
		this.body = body;
	}
	public Method(int lineNumber, String name, List<FormalParam> parameters,
			String returnType, Expression body) {
		super(lineNumber);
		this.name = name;
		this.parameters = parameters;
		this.returnType = returnType;
		this.body = body;
	}	
	public Method() {}
	

}
