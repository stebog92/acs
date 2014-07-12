package ro.pub.cs.lcpl;

import java.util.*;
import java.io.PrintStream;
/** A virtual method call (dispatch)
 * [<i>object</i>.<i>name</i> <i>expression</i> , ... ]
 */
public class Dispatch extends BaseDispatch {
	public Dispatch(int lineNumber, Expression object, String name,
			List<Expression> arguments) {
		setLineNumber(lineNumber);
		setObject(object);
		setName(name);
		setArguments(arguments);
	}
	public Dispatch() {}

    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar,LCPLClass _class, Program program) {
        return super.emitIR(os, s, nextVar, _class, program);
    }
	

}
