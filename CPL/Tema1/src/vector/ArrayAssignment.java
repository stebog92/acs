package vector;
import ro.pub.cs.lcpl.*;

public class ArrayAssignment extends Expression {
	private Expression type, index, value;
	
	public Expression getValue() {
		return this.value;
	}

	public ArrayAssignment(int lineNumber, Expression type, Expression index, Expression value) {
		super(lineNumber);
		this.type = type;
		this.index = index;
		this.value = value;
	}
	
}
