package ro.pub.cs.lcpl;

import java.util.*;

import java.io.PrintStream;
/** Common code for Dispatch and StaticDispatch */
public class BaseDispatch extends Expression {
	private Expression object;
	private String name;
	private List<Expression> arguments;
    private String staticType;
	
	/** A reference to the method invoked by the dispatch expression.
	 * 
	 * In case of dynamic dispatch, use the type of the <i>object</i> expression to 
	 * identify which method the dispatch refers to.
	 * 
	 * The actual method invoked at runtime could be different due to polymorphism, 
	 * because a derived class can override methods of the base class. 
	 *  */
	private Method method;
	
	public Expression getObject() {
		return object;
	}
	public void setObject(Expression object) {
		this.object = object;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public List<Expression> getArguments() {
		return arguments;
	}
	public void setArguments(List<Expression> arguments) {
		this.arguments = arguments;
	}
    
	public void setStaticType(String type) {
		this.staticType = type;
	}
    
	public BaseDispatch() {}

    public String emitIR(PrintStream os, HashMap<String, Integer> s, int nextVar, LCPLClass _class, Program program) {
        String name = "";
        //get Vtable
        if (this.object.getTypeData() instanceof LCPLClass) {
            if (object.getType() == null || (object.getType() != null && object.getType().equals("IO"))) {
                name = _class.getName();
            } else {
                name = ((LCPLClass)this.object.getTypeData()).getName();
            }
        }


        int objectVar = nextVar;
        String objIndex = object.emitIR(os, s, nextVar, _class, program);
        String objectStringConstant = "";

        if (!(object instanceof Symbol && ((Symbol)object).getName().equals("self"))) {
            if (object instanceof StringConstant) {
                objectStringConstant = ((StringConstant)object).emitIR(os, s, nextVar, _class, program);
            } else {
                nextVar = Integer.parseInt(objIndex.substring(1)) + 1;
                objectVar = nextVar - 1;
            }
        }
        if (object.getType() == null && ((Symbol)object).getName().equals("self")) {
            //os.println("\t%" + nextVar + " = load %struct.T" + name + "** %1                                ; Load object\n");
            nextVar++;
        }
        int obj = nextVar;
        
        if(object instanceof StringConstant) {
            os.println("\t%" + nextVar + " = bitcast %struct.T" + object.getTypeData().getName() +
                    "* " + objectStringConstant + " to i8*         ; Null ptr check");
        } else {
            os.println("\t%" + nextVar + " = bitcast %struct.T" 
                    + object.getTypeData().getName() + "* %" + objectVar + " to i8*         ; Null ptr check");
        }
        os.println("\tcall void @__lcpl_checkNull(i8* %" + nextVar + ")");
        nextVar++;
        if (object instanceof StringConstant) {
            os.println("\t%" + nextVar + " = getelementptr %struct.T" 
                    + object.getTypeData().getName() + "* " + objectStringConstant + ", i32 0, i32 0   ; Locate address of RTTI pointer");
        } else {
            os.println("\t%" + nextVar + " = getelementptr %struct.T" 
                    + object.getTypeData().getName() + "* %" + objectVar + ", i32 0, i32 0   ; Locate address of RTTI pointer");
        }

        nextVar++;
        os.println("\t%" + nextVar + " = load %struct.__lcpl_rtti** %" + (nextVar - 1) + "         ; Load RTTI pointer");
        nextVar++;
        os.print("\t%" + nextVar + " = getelementptr %struct.__lcpl_rtti* %" + (nextVar - 1) + ", i32 0, i32 3, ");
        nextVar++;
        

        int methodIndex = 0;
        String mType = "";
        //get method index
        if (object.getType() == null) {
            methodIndex = program.getMethodIndex(_class.getName(), this.name, staticType);
            mType = this.method.getParent().getName();
        } else {
            if (object.getType().equals("IO")) {
                methodIndex = program.getMethodIndex(_class.getName(), this.name, staticType);
                if (methodIndex == -1) {
                    methodIndex = program.getMethodIndex(object.getTypeData().getName(), this.name, staticType);
                }
            } else {
                String t = program.getTypeObject(object);
                methodIndex = program.getMethodIndex(t, this.name, staticType);
            }
            mType = object.getType();
        }
        /*os.println("\t%" + nextVar + " = getelementptr i8** %" + (nextVar - 1) + ", i32 " + methodIndex);
        nextVar++;*/
        os.println("i32 " + methodIndex);
        
        os.println("\t%" + nextVar + " = load i8** %" + (nextVar - 1)); 
        nextVar++;
        if (method.getReturnType().equals("Int")) {
            os.print("\t%" + nextVar + " = bitcast i8* %" + (nextVar - 1) +" to i32 (%struct.T" + mType + "*");
        } else if (method.getReturnType().equals("void")) {
            os.print("\t%" + nextVar + " = bitcast i8* %" + (nextVar - 1) +" to void (%struct.T" + mType + "*");
        } else {
            os.print("\t%" + nextVar + " = bitcast i8* %" + (nextVar - 1) +" to %struct.T" + method.getReturnType() + "* (%struct.T" + mType + "*");
        }
        int functionPointer = nextVar;
        nextVar++;
        for (Expression e : this.arguments) {
            if (e.getType().equals("Int")) {
                os.print(", i32");
            } else {
                os.print(", %struct.T" + e.getType() + "*");
            }
        }
        os.println(")*  ; Convert char * into function pointer");
        //cast


        if (!object.getTypeData().getName().equals(mType)) {
            
            os.println("\t%" + nextVar + " = bitcast %struct.T" + _class.getName() + "* %" + objectVar + " to %struct.T" + mType +"*");
            objectVar = nextVar;
            nextVar++;
        }

        ArrayList<String> args = new ArrayList<String>();
        for (Expression e : this.arguments) {
            e.setObjIndex(obj);
            String temp = e.emitIR(os, s, nextVar, _class, program);
            args.add(temp);
            if (e instanceof StringConstant || e instanceof IntConstant) {
            } else {
                nextVar = Integer.parseInt(temp.substring(1)) + 1;
            }
        }

       
        if (method.getReturnType().equals("Int")) {
            if (object instanceof StringConstant) {
                os.print("\t%" + nextVar + " = call i32 %" + functionPointer + "(%struct.T" + mType + "* " + objectStringConstant);
            } else {
                os.print("\t%" + nextVar + " = call i32 %" + functionPointer + "(%struct.T" + mType + "* %" + objectVar);
            }
        } else if(method.getReturnType().equals("void")) {
            os.print("\tcall void %" + functionPointer + "(%struct.T" + mType + "* %" + objectVar);
            nextVar--;
        } else {
            os.print("\t%" + nextVar + " = call %struct.T" + method.getReturnType() + "* %" + functionPointer + "(%struct.T" + mType + "* %" + objectVar);
        }

        int i = 0;
        for (Expression e: this.arguments) {
            if (e instanceof IntConstant) {
                os.print(", i32 " + ((IntConstant)e).getValue());
            } else if (e.getType().equals("Int")) {
                os.print(", i32 " + args.get(i));
            } else {
                os.print(", %struct.T" + e.getType() + "* " + args.get(i));
            }
            i++;
        }
        os.println(")\n");
        return "%" + nextVar;
    }
	
}
