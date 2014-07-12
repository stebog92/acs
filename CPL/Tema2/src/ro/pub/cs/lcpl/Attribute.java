package ro.pub.cs.lcpl;

import java.util.HashMap;
import java.util.LinkedList;


/** An attribute of a class, declared as
 * <i>type</i> <i>name</i> = <i>expression</i> ;
 */
public class Attribute extends TreeNode implements Feature, Variable {
    private String name;

    /** Initialization expression for the attribute, read from the AST. Can be null. */
    private Expression init;
    private String type;

    /** A reference to the type of the attribute. This can be
     * <li> Program.intType - for Int attributes
     * <li> An LCPLClass - for class attributes */
    private Type typeData;

    /** 
     * The initialization of attributes can contain method calls, attributes, or "self".
     * These references have to be bound to a variable that points to the object being initialized.
     * 
     *   var
     *     Object a = [self.doSomething];   # The expression here references "self"
     *   end;
     *   
     * Create here a formal parameter that will be the target of this binding. */	
    private FormalParam attrInitSelf;

    public Type getTypeData() {
        return typeData;
    }
    public void setTypeData(Type typeData) {
        this.typeData = typeData;
    }
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

    public Type getVType() {
        return this.typeData;
    }
    public FormalParam getAttrInitSelf() {
        return attrInitSelf;
    }
    public void setAttrInitSelf(FormalParam attrInitSelf) {
        this.attrInitSelf = attrInitSelf;
    }
    public Attribute(int lineNumber, String name, String type, Expression init) {
        super(lineNumber);
        this.name = name;
        this.type = type;
        this.init = init;
    }
    public Attribute() {}

    public void collectFeature(LCPLClass _class, Program program) {
        if (type.equals("Int")) {
            this.typeData = program.getIntType();
        } else if (type.equals("void")) {
            this.typeData = program.getNoType();
        } else {
            this.typeData = program.getClass(this.type);
        }

        /* check attribute duplication */
        if (_class.getAttribute(this.name) != null) {
            System.err.println("Error in line " + this.getLineNumber() + " : An attribute with the same name already exists in class "+ 
                    _class.getName() + " : "  + this.name);
        }
        _class.addAttribute(this);
    }

    public void checkForErrors(LCPLClass _class, Program program) {
        LCPLClass c = _class.getParentData();

        /* check type existence */
        if (this.typeData == null) {
            System.err.println("Error in line " + getLineNumber() + " : Class " + this.type + " not found.");
            System.exit(0);
        }

        /* check attribute redefinition */
        while (c != null) {
            if (c.getAttribute(this.name) != null) {
                System.err.println("Error in line " + c.getLineNumber() + " : Attribute " + this.name + " is redefined.");   
                break;
            }
            c = c.getParentData();
        }
    }

    public void handleNode(Program program, LCPLClass _class, Method method,
            LocalDefinition localDefinition) {
        Cast cast;
        if (init != null) {
            attrInitSelf = new FormalParam("self", this.type);
            attrInitSelf.setVariableType(_class);
            if (method == null) {
                method = new Method(getLineNumber(), "", new LinkedList<FormalParam>(), null, this.init);
                method.setParent(_class);
                method.setReturnTypeData(program.getNoType());
                method.setSelf(this.attrInitSelf);
                method.initParam();
            }
            init.handleNode(program, _class, method, localDefinition);
            if (!init.getType().equals(this.type)) {
                cast = new Cast(init.getLineNumber(), this.type, init);
                cast.setTypeData(this.typeData);
                init = cast;
            }
        }
    }

}
