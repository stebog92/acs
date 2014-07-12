package ro.pub.cs.lcpl;

import java.util.List;

import java.io.PrintStream;
import java.util.*;
/** A method call with a know static type
 * [<i>object</i>.<i>type</i>.<i>name</i> <i>expression</i> , ... ]
 */
public class StaticDispatch extends BaseDispatch {
	private String type;
	private Type selfType;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Type getSelfType() {
		return selfType;
	}
	public void setSelfType(Type selfType) {
		this.selfType = selfType;
	}

    
	public StaticDispatch(int lineNumber, Expression object, String type,
			String name, List<Expression> arguments) {
		setLineNumber(lineNumber);
		setObject(object);
		setType(type);
		setName(name);
		setArguments(arguments);
	}

    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar, LCPLClass _class, Program program) {
        super.setStaticType(type);
        return super.emitIR(os, s, nextVar, _class, program);
    }
    
	public StaticDispatch() {}
	

}
