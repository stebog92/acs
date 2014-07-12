package ro.pub.cs.lcpl;

/* <symbol> = <expression> */

public class Assignment extends Expression {
	private String symbol;
	private Expression e1;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Expression getE1() {
		return e1;
	}
	public void setE1(Expression e1) {
		this.e1 = e1;
	}
	public Assignment(int lineNumber, String symbol, 
			Expression e1) {
		super(lineNumber);
		this.symbol = symbol;
		this.e1 = e1;
	}
	
}
