package ro.pub.cs.lcpl;

/* An attribute of a class, declared as
 * <type> <name> = <expression> ;
 */

public class Attribute extends TreeNode implements Feature {
	private String name;
	private String type;
	private Expression init;
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
	public Attribute(int lineNumber, String name, String type, Expression init) {
		super(lineNumber);
		this.name = name;
		this.type = type;
		this.init = init;
	}
	
}
