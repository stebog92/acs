package ro.pub.cs.lcpl;

import java.util.List;

/* A block of zero, one or more expressions, executed sequentially */

public class Block extends Expression {
	List<Expression> expressions;

	public List<Expression> getExpressions() {
		return expressions;
	}

	public void setExpressions(List<Expression> expressions) {
		this.expressions = expressions;
	}

	public Block(int lineNumber, List<Expression> expressions) {
		super(lineNumber);
		this.expressions = expressions;
	}
	
}
