package ro.pub.cs.lcpl;

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

    public void handleNode(Program program, LCPLClass _class, Method method,
            LocalDefinition localDefinition) {

        LocalDefinition def = localDefinition;
        LCPLClass cls = _class;


        /* set self variable */
        if (this.name.indexOf("self.") == 0) {
            this.variable = cls.getAttribute(this.name.substring(5));
            if (this.variable == null) {
                System.err.println("Error in line " + getLineNumber() + 
                        " : Attribute " + this.name + " not found in class " + _class.getName());   
            }
        }

        /* search for variable in local defs */
        while(def != null && this.variable == null) {
            if (def.getName().equals(this.name)) {
                this.variable = def;
                break;
            } else {
                def = def.getPrevious();
            }
        }

        /* search for variable in method parameters */
        if (this.variable == null && method != null) {
            if (this.name.equals("self")) {
                this.variable = method.getSelf();
            } else {
                this.variable = method.getParam(this.name);
            }
        }

        /* search for variable in attributes */
        while(cls != null && this.variable == null) {
            this.variable = cls.getAttribute(this.name);
            cls = program.getClass(cls.getParent());
        }


        if (this.variable == null) {
            System.err.println("Error in line " + getLineNumber() + 
                        " : Attribute " + this.name + " not found in class " + _class.getName());
            System.exit(0);
        }
        setTypeData(this.variable.getVType());
    }

}
