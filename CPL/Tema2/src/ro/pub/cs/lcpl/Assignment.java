package ro.pub.cs.lcpl;


/** Expression of type <i>symbol</i> = <i>e1</i> */
public class Assignment extends Expression {
    /** Left hand side of the assignment.
     * Take into account the syntax "self.sym = ..." to specify explicitly assignments to attributes.
     */
    private String symbol;
    private Expression e1;

    /** Reference to the variable corresponding to the symbol on the left side of the assignment.
     * The variable can be:
     *   <li> an Attribute of the current class
     *   <li> a FormalParam of the current method. It can be any formal parameter, except for self. 
     *   <li> a LocalDefinition. The Assignment must be inside the scope of the LocalDefinition.
     *  */
    private Variable symbolData;

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public Expression getE1() {
        return e1;
    }
    public void setE1(Expression e1) {
        this.e1 = e1;
    }
    public Variable getSymbolData() {
        return symbolData;
    }
    public void setSymbolData(Variable symbolData) {
        this.symbolData = symbolData;
    }
    public Assignment(int lineNumber, String symbol, 
            Expression e1) {
        super(lineNumber);
        this.symbol = symbol;
        this.e1 = e1;
    }
    public Assignment() {}

    public void handleNode(Program program, LCPLClass _class, Method method,
            LocalDefinition localDefinition) {


        e1.handleNode(program, _class, method, localDefinition);


        LocalDefinition def = localDefinition;
        LCPLClass cls = _class;

        /* set symbol data */
        if (this.symbol.indexOf("self.") != -1) {
            this.symbol = this.symbol.substring(5);
            this.symbolData = cls.getAttribute(this.symbol);
            if (this.symbolData == null) {
                System.err.println("Error in line " + getLineNumber() + 
                        " : Attribute " + this.symbol + " not found in class " + _class.getName());   
            }
        }
        /* find var in local definitions */
        while(def != null && this.symbolData == null) {
            if (def.getName().equals(this.symbol)) {
                this.symbolData = def;
                break;
            } else {
                def = def.getPrevious();
            }
        }

        /* find var in parameters of method */
        if (this.symbolData == null) {
            if (this.symbol.equals("self")) {
                this.symbolData = method.getSelf();
            } else {
                this.symbolData = method.getParam(this.symbol);
            }
        }

        /* find var in attributes and parents attributes*/
        while(cls != null && this.symbolData == null) {
            this.symbolData = cls.getAttribute(this.symbol);
            cls = program.getClass(cls.getParent());
        }

        /* check if var exists */
        if (this.symbolData == null) {
            System.err.println("Error in line " + getLineNumber() + 
                        " : Attribute " + this.symbol + " not found in class " + _class.getName());
            System.exit(0);
        }

        /* check conversion */
        if (symbolData.getVType() != this.e1.getTypeData()) {
            if (!program.canConvert(this.e1.getTypeData(), symbolData.getVType())) {
                System.err.println("Error in line " + this.getLineNumber() + " : Cannot convert a value of type " + this.e1.getType() + " into " +
                        this.symbolData.getVType().getName());
                System.exit(0);
            }

            if (symbolData instanceof LCPLClass && this.e1.getTypeData() instanceof LCPLClass) {
                /* check if assigned value extends var type */
                LCPLClass v1 = (LCPLClass)symbolData.getVType();
                LCPLClass v2 = (LCPLClass)this.e1.getTypeData();
                boolean inherits = false;
                while(v2 != null) {
                    if (v1 == v2) {
                        inherits = true;
                        break;
                    }
                    v2 = v2.getParentData();
                }
                if (!inherits) {
                    System.err.println("Error in line " + this.getLineNumber() + " : Cannot convert a value of type " +
                            this.e1.getType() + " into " + this.symbolData.getVType().getName());
                    System.exit(0);
                }
            }
        }
        setTypeData(this.symbolData.getVType());
    }

}
