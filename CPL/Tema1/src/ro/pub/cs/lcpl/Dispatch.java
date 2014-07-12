package ro.pub.cs.lcpl;

import java.util.List;

/* A virtual method call (dispatch)
 * [<object>.<name> <expression> , ... ]
 */

public class Dispatch extends Expression {
	private Expression object;
	private String name;
	private List<Expression> arguments;
	public Expression getObject() {
		return object;
	}
	public void setObject(Expression object) {
		this.object = object;
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
	public Dispatch(int lineNumber, Expression object, String name,
			List<Expression> arguments) {
		super(lineNumber);
		this.object = object;
		this.name = name;
		this.arguments = arguments;
	}

}
