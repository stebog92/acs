package ro.pub.cs.lcpl;

import java.util.List;

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
	public StaticDispatch() {}

    public void handleNode(Program program, LCPLClass _class, Method method,
            LocalDefinition localDefinition) {
        
        if (type.equals("Int")) {
            this.selfType = program.getIntType();
        } else if (type.equals("void")) {
            this.selfType = program.getNoType();
        } else {
            this.selfType = program.getClass(this.type);
        }

        /* check if type is defined */
        if (this.selfType == null) {
            System.err.println("Error in line " + getLineNumber() + 
                        " : Class " + this.type + " not found.");
            System.exit(0);
        }

         /* set static type */
        super.setStaticDispatchType(this.selfType);
        super.handleNode(program, _class, method, localDefinition);

    }
	

}
