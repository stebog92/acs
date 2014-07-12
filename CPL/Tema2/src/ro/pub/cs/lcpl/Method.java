package ro.pub.cs.lcpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/** A declaration of a method in a class
 * <i>name</i> <i>parameters</i> -> <i>returnType</i> : <i>body</i> end;
 */
public class Method extends TreeNode implements Feature {
    private String name;
    private List<FormalParam> parameters = new LinkedList<FormalParam>();

    protected HashMap<String, FormalParam> _parameters;

    /** Type returned by the method, or "void" if the method does not return anything. */
    private String returnType;

    private Expression body;

    /** Reference to the LCPL class that contains the current method. */
    private LCPLClass parent;

    /** Create a new FormalParam object for the shadow parameter "self". 
     * This object will be referred by the self symbol in this method. 
     * Do not add it to the parameters list. */
    private FormalParam self;

    /** A reference to the type of the value returned by the method. This can be
     * <li> Program.intType - for Int parameters
     * <li> An LCPLClass - for class parameters
     * <li> Program.noType - for methods that do not return any type */
    private Type returnTypeData;	

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LCPLClass getParent() {
        return parent;
    }
    public void setParent(LCPLClass parent) {
        this.parent = parent;
    }
    public List<FormalParam> getParameters() {
        return parameters;
    }
    public void setParameters(List<FormalParam> parameters) {
        this.parameters = parameters;
    }
    public FormalParam getSelf() {
        return self;
    }
    public void setSelf(FormalParam self) {
        this.self = self;
    }
    public String getReturnType() {
        return returnType;
    }	
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
    public Type getReturnTypeData() {
        return returnTypeData;
    }
    public void setReturnTypeData(Type returnTypeData) {
        this.returnTypeData = returnTypeData;
    }
    public Expression getBody() {
        return body;
    }
    public void setBody(Expression body) {
        this.body = body;
    }

    protected FormalParam getParam(String name) {
        return _parameters.get(name);
    }

    protected void initParam() {
        _parameters = new HashMap<String, FormalParam>();
    }

    public Method(int lineNumber, String name, List<FormalParam> parameters,
            String returnType, Expression body) {
        super(lineNumber);
        this.name = name;
        this.parameters = parameters;
        this.returnType = returnType;
        this.body = body;
    }	
    public Method() {}

    
    public void collectFeature(LCPLClass _class, Program program) {

        _parameters = new HashMap<String, FormalParam>();
        this.self = new FormalParam("self", _class.getName());
        this.self.setVariableType(_class);
        this.parent = _class;

        if (this.returnType.equals("Int")) {
            this.returnTypeData = program.getIntType();
        } else if (this.returnType.equals("void")) {
            this.returnTypeData = program.getNoType();
        } else {
            this.returnTypeData = program.getClass(this.returnType);
        }

        for(FormalParam param : this.parameters) {
            param.handleNode(program, _class, this, null);
            _parameters.put(param.getName(), param);
        }
        
        if (_class.containsMethod(this.name)) {
            System.err.println("Error in line " + this.getLineNumber() + " : A method with the same name already exists in class "+ 
                    _class.getName() + " : "  + this.name);
            System.exit(0);
        }

        _class.addMethod(this);
    }

    public void checkForErrors(LCPLClass _class, Program program) {

        /* check overloaded method */
        LCPLClass cls = _class.getParentData();
        while (cls != null) {
            if (cls.containsMethod(this.name)) {
                Method m = cls.getMethod(this.name);
                if (m.getParameters().size() != this.parameters.size()) {
                    System.err.println("Error in line " + this.getLineNumber() + " : Overloaded method has a different number of parameters");
                    System.exit(0);
                }

                if (!m.getReturnType().equals(this.returnType)) {
                    System.err.println("Error in line " + this.getLineNumber() + " : Return type changed in overloaded method.");
                }

                FormalParam param2;
                int i = 0;
                for (FormalParam param : this.parameters) {
                    param2 = m.getParameters().get(i);
                    if (!param2.getType().equals(param.getType())) {
                        System.err.println("Error in line " + this.getLineNumber() + " : Parameter " + param.getName() +
                                " has a different type in overloaded method.");            
                    }
                    i++;
                }
            }
            cls = cls.getParentData();
        }
    }


    /* handle body node */
    public void handleNode(Program program, LCPLClass _class, Method method,
            LocalDefinition localDefinition) { 
        if (this.body == null) {
            return;
        }
        this.body.handleNode(program, _class, this, localDefinition);
        if (this.returnTypeData != this.body.getTypeData()) {
            if (!program.canConvert(this.body.getTypeData(), this.returnTypeData)) {
                System.err.println("Error in line " + this.body.getLineNumber() + 
                        " : Cannot convert a value of type " + this.body.getTypeData().getName() + " into " + this.returnTypeData.getName());   
                System.exit(0);
            }
        }

    }
}
