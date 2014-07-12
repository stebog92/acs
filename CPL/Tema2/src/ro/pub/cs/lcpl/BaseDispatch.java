package ro.pub.cs.lcpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

/** Common code for Dispatch and StaticDispatch */
public class BaseDispatch extends Expression {
    private Expression object;
    private String name;
    private List<Expression> arguments;

    /** A reference to the method invoked by the dispatch expression.
     * 
     * In case of dynamic dispatch, use the type of the <i>object</i> expression to 
     * identify which method the dispatch refers to.
     * 
     * The actual method invoked at runtime could be different due to polymorphism, 
     * because a derived class can override methods of the base class. 
     *  */
    private Method method;
    protected Type staticDispatchType;

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
    public BaseDispatch() {}

    public void handleNode(Program program, LCPLClass _class, Method method,
            LocalDefinition localDefinition) {

        int index;
        FormalParam param;
        if (object != null) {
            object.handleNode(program, _class, method, localDefinition);
        }

        /* create self object if null*/
        if (this.object == null)
        {
            this.method = _class.getMethod(this.name);
            this.object = new Symbol(method.getLineNumber(), "self");
            this.object.setTypeData(_class);
            ((Symbol)this.object).setVariable(method.getSelf());
        } else {
            if (this.staticDispatchType != null) {
                /*check if object inherits type */
                LCPLClass o = (LCPLClass) this.object.getTypeData();
                LCPLClass t = (LCPLClass) this.staticDispatchType;
                boolean inherits = false;
                while (o != null) {
                    if (o == t) {
                        inherits = true;
                        break;
                    }
                    o = o.getParentData();
                }
                if (!inherits) {
                    System.err.println("Error in line " + getLineNumber() + 
                            " : Cannot convert from " + this.object.getTypeData().getName() + " to " + this.staticDispatchType.getName() + 
                            " in StaticDispatch");       
                    System.exit(0);
                }

                this.method =  ((LCPLClass)this.staticDispatchType).getMethod(this.name);
            } else {
                this.method = ((LCPLClass)object.getTypeData()).getMethod(this.name);
            }
        }

        /* check if type class contains method */
        if (this.method == null) {
            System.err.println("Error in line " + getLineNumber() + 
                    " : Method " + this.name + " not found in class " + object.getTypeData().getName());
            System.exit(0);
        }

        if (this.arguments.size() != 0) {

            /* check arguments number in dispatch */
            ArrayList<Expression> newArguments = new ArrayList<Expression>();
            if (this.arguments.size() < this.method.getParameters().size()) {
                System.err.println("Error in line " + getLineNumber() +" : Not enough arguments in method call " + this.method.getName());
                System.exit(0);
            }
            if (this.arguments.size() > this.method.getParameters().size()) {
                System.err.println("Error in line " + getLineNumber() +" : Too many arguments in method call " + this.method.getName());
                System.exit(0);
            }

            /* check if argument has the right type */
            index = 0;
            for (Expression arg : this.arguments) {
                arg.handleNode(program, _class, method, localDefinition);
                param = this.method.getParameters().get(index);
                if (param.getVType() != arg.getTypeData()) {
                    if (!program.canConvert(arg.getTypeData(), param.getVType())) {
                        System.err.println("Error in line " + getLineNumber() +" : Cannot convert a value of type " + 
                                arg.getTypeData().getName() + " into " + param.getVType().getName());
                        System.exit(0);
                    } else {
                        Cast cast = new Cast(arg.getLineNumber(), param.getType(), arg);
                        cast.setTypeData(param.getVType());
                        newArguments.add(cast);
                    }
                } else {
                    newArguments.add(arg);
                }
                index++;
            }
            this.arguments = newArguments;
        }
        setTypeData(this.method.getReturnTypeData());
    }

    protected void setStaticDispatchType(Type type) {
        this.staticDispatchType = type;
    }

}
