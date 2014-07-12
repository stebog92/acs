package ro.pub.cs.lcpl;

import java.io.PrintStream;
import java.util.*;
/** A local variable definition in a "local <i>name</i> <i>type</i>; ... end;" area
 */
public class LocalDefinition extends Expression implements Variable {
	private String name;
	
	/** Type of the variable, as a String */
	private String type;
	
	/** Initialization expression, can be null */
	private Expression init;
	
	/** Contains the expression where this definition is valid
	 * 
	 * For example
	 * <pre>
	 *   if x == 0; then local Int a = 0; Int b = a; end; a = x; b = x; end;
	 * </pre>
	 * The scope for the local definition "Int a = 0" is the local definition "Int b = a", which includes its own scope.
	 * The scope for the local definition "Int b = a" is the block "a = x; b = x;"
	 */
	private Expression scope;
	
	/** A reference to the type of the variable. This can be
	 * <li> Program.intType - for Int variables.
	 * <li> An LCPLClass - for class variables. */
	private Type variableType;
	
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
	public Type getVariableType() {
		return variableType;
	}
	public void setVariableType(Type variableType) {
		this.variableType = variableType;
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
	public LocalDefinition() {}
    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar,LCPLClass _class, Program program) {
        if (this.type.equals("Int")) {
            os.println("\t%" + nextVar + " = alloca i32");
        } else {
            os.println("\t%" + nextVar + " = alloca %struct.T" + this.type + "*");
        }
        setVarIndex(nextVar);
        if (init == null) {
            if (this.type.equals("Int")) {
                os.println("\tstore i32 0, i32* %" + nextVar);
            } else {
                os.println("\tstore %struct.T" + this.type + "* null, %struct.T" + this.type + "** %" + nextVar);
            }
        } else if (init instanceof StringConstant) {
            os.println("\tstore %struct.T" + this.type + "* @_string_constant." + s.get(((StringConstant)init).getValue())
                    + ", %struct.TString** %" + nextVar);
        } else if (init instanceof IntConstant) {
            os.println("\tstore i32 " + ((IntConstant)init).getValue() + ", i32* %" + nextVar);
        } else if (init.getType().equals("Int")) {
            String t = init.emitIR(os, s, nextVar + 1, _class, program);
            os.println("\tstore i32 " + t + ", i32* %" + nextVar);
            nextVar = Integer.parseInt(t.substring(1));
        } else {
            String t = init.emitIR(os, s, nextVar + 1, _class, program);
            os.println("\tstore %struct.T" + this.type + "* " + t + ", %struct.T" + this.type + "** %" + nextVar);
            nextVar = Integer.parseInt(t.substring(1));
        }
        String _scope = this.scope.emitIR(os, s, nextVar + 1, _class, program);
        if(_scope.indexOf("_") < 0) {
            nextVar = Integer.parseInt(_scope.substring(1));
        } else {
            nextVar = Integer.parseInt(_scope.substring(1, _scope.indexOf("_")));
        }


        return "%" + nextVar;
    } 
	

}
