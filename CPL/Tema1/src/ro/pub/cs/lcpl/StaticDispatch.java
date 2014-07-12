package ro.pub.cs.lcpl;

import java.util.List;

/* A method call with a know static type
 * [<object>.<type>.<name> <expression> , ... ]
 */

public class StaticDispatch extends Expression {
	private Expression object;
	private String type;
	private String name;
	private List<Expression> arguments;
	public Expression getObject() {
		return object;
	}
	public void setObject(Expression object) {
		this.object = object;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Expression> getArguments() {
		return arguments;
	}
	public void setArguments(List<Expression> arguments) {
		this.arguments = arguments;
	}
	public StaticDispatch(int lineNumber, Expression object, String type,
			String name, List<Expression> arguments) {
		super(lineNumber);
		this.object = object;
		this.type = type;
		this.name = name;
		this.arguments = arguments;
	}
	
}
