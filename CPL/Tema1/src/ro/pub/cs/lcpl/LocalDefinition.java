package ro.pub.cs.lcpl;

/* A local variable definition in a "local <name> <type>; ... end;" area
 * <scope> contains the expression where this definition is valid
 * 
 * For example
 *   if x == 0; then local Int a = 0; Int b = a; end; a = x; b = x; end;
 *   
 * The scope for the local definition "Int a = 0" is the local definition "Int b = a", which includes its own scope.
 * The scope for the local definition "Int b = a" is the block "a = x; b = x;"
 */

public class LocalDefinition extends Expression {
	private String name;
	private String type;
	private Expression init;
	private Expression scope;
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
	public Expression getScope() {
		return scope;
	}
	public void setScope(Expression scope) {
		this.scope = scope;
	}
	public LocalDefinition(int lineNumber, String name, String type,
			Expression init, Expression scope) {
		super(lineNumber);
		this.name = name;
		this.type = type;
		this.init = init;
		this.scope = scope;
	}
	
}
