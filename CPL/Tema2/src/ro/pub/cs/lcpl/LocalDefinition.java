package ro.pub.cs.lcpl;

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


    protected LocalDefinition previous;

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

    public Type getVType() {
        return this.variableType;
    }

    protected LocalDefinition getPrevious() {
        return previous;
    }

    protected void setPrevious(LocalDefinition def) {
        previous = def;
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

    public void handleNode(Program program, LCPLClass _class, Method method,
            LocalDefinition localDefinition) {
        Cast cast;

        /* set type variable */ 
        this.previous = localDefinition;
        if (type.equals("Int")) {
            this.variableType = program.getIntType();
        } else if (type.equals("void")) {
            this.variableType = program.getNoType();
        } else {
            this.variableType = program.getClass(this.type);
        }

        if (this.variableType == null) {
            System.err.println("Error in line " + getLineNumber() + " : Class " + this.type + " not found.");
            System.exit(0);
        }

        if (init != null) {
            init.handleNode(program, _class, method, localDefinition);
            if (!this.type.equals(init.getType())) {
                cast = new Cast(init.getLineNumber(), this.type, this.init);
                cast.setTypeData(this.variableType);
                this.init = cast;
            }
        }
        scope.handleNode(program, _class, method, this);
        setTypeData(scope.getTypeData());
    }

}
