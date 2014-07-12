package ro.pub.cs.lcpl;

import java.util.LinkedList;
import java.util.List;

/* A declaration of a method in a class
 * <name> <parameters> -> <returnType> <body> end;
 */

public class Method extends TreeNode implements Feature {
	private String name;
	private List<FormalParam> parameters = new LinkedList<FormalParam>();
	private String returnType;
	private Expression body;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<FormalParam> getParameters() {
		return parameters;
	}
	public void setParameters(List<FormalParam> parameters) {
		this.parameters = parameters;
	}
	public String getReturnType() {
		return returnType;
	}	
	public void setReturnType(String returnType) {
		this.returnType = returnType;
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
}
