package ro.pub.cs.lcpl;

/** <i>expression</i> <= <i>expression</i> */
public class LessThanEqual extends BinaryOp {

	public LessThanEqual(int lineNumber, Expression e1, Expression e2) {
		super(lineNumber, e1, e2);
	}

	public LessThanEqual() {}
	
}
