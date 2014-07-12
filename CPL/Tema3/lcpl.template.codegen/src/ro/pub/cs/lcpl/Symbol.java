package ro.pub.cs.lcpl;
import java.io.PrintStream;
import java.util.*;
/** A symbol - local variable or class attribute */
public class Symbol extends Expression {
	/** The name of the symbol being evaluated.
	 * Take into account the syntax "self.sym" to specify explicitly class attributes.
	 */
	private String name;
	
	/** Reference to the variable corresponding to the symbol.
	 * The variable can be:
	 *   <li> an Attribute of the current class
	 *   <li> a FormalParam of the current method. It can be any formal parameter, except for self. 
	 *   <li> a LocalDefinition. The Assignment must be inside the scope of the LocalDefinition.
	 *  */
	private Variable variable;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	public Symbol(int lineNumber, String name) {
		super(lineNumber);
		this.name = name;
	}
	public Symbol() {}
    
    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar, LCPLClass _class, Program program) {
        if (getName().equals("self")) {
            os.println("\t%" + nextVar + " = load %struct.T" + _class.getName() + "** %1");
            return "%" + nextVar;
        }
        if (getVariable() instanceof Attribute) {
            
            nextVar = Integer.parseInt(((Attribute)getVariable()).emitIR(os, s, nextVar, _class, program).substring(1));
            
            nextVar++;
            if (getType() == null) {
                os.println("\t%" + nextVar + " = load %struct.T" + ((Attribute)getVariable()).getType() + "** %" + (nextVar-1));
            } else if (getType().equals("Int")) {
                os.println("\t%" + nextVar + " = load i32* %" + (nextVar-1));
            } else {
                os.println("\t%" + nextVar + " = load %struct.T" + getType() + "** %" + (nextVar-1));
            }
            setVarIndex(nextVar);
        } else if (getVariable() instanceof LocalDefinition) {
            int temp = ((LocalDefinition)getVariable()).getVarIndex();
            if (((LocalDefinition)getVariable()).getType().equals("Int")) {
                os.println("\t%" + nextVar + " = load i32* %" + temp);
            } else {
                if (getType() == null) {
                    os.println("\t%" + nextVar + " = load %struct.T" + ((LocalDefinition)getVariable()).getType() + "** %" + temp);
                } else {
                    os.println("\t%" + nextVar + " = load %struct.T" + getType() + "** %" + temp);
                }
            }
            setVarIndex(nextVar);
        } else if (getVariable() instanceof FormalParam) {
            int temp = ((FormalParam)getVariable()).getVarIndex(); 
            if (temp == 0) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (((FormalParam)getVariable()).getType().equals("Int")) {
                os.println("\t%" + nextVar + " = load i32* %" + temp);
            } else {
                os.println("\t%" + nextVar + " = load %struct.T" + ((FormalParam)getVariable()).getType() + "** %" + temp);
            }

            setVarIndex(nextVar);
        }
        return "%" + getVarIndex();
    }

	
}
