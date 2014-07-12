package ro.pub.cs.lcpl;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

/** A complete LCPL program
 * <i>class</i> ... 
 */
public class Program extends TreeNode {
    /** List of all classes in the program.
     * Add the built-in classes below to this list: 
     * Program.objectType, Program.stringType, Program.ioType. */
    private List<LCPLClass> classes;

    /** Create a new IntType object representing the type of all Int expressions in the program. */
    private IntType intType;

    /** Create a new NoType object representing the type of all expressions in the program that do not return a value, such as 'while'. */
    private NoType noType;

    /** Create a new NullType object representing the type of all expressions in the program that are evaluated to the constant 'void'. */
    private NullType nullType;

    /** Create a new LCPLClass object representing the Object class */
    private LCPLClass objectType;

    /** Create a new LCPLClass object representing the String class */
    private LCPLClass stringType;

    /** Create a new LCPLClass object representing the IO class */
    private LCPLClass ioType;


    private HashMap<String, LCPLClass> _classes;

    public List<LCPLClass> getClasses() {
        return classes;
    }

    public void setClasses(List<LCPLClass> classes) {
        this.classes = classes;
    }

    public IntType getIntType() {
        return intType;
    }

    public void setIntType(IntType intType) {
        this.intType = intType;
    }

    public NoType getNoType() {
        return noType;
    }

    public void setNoType(NoType noType) {
        this.noType = noType;
    }

    public NullType getNullType() {
        return nullType;
    }

    public void setNullType(NullType nullType) {
        this.nullType = nullType;
    }

    public LCPLClass getObjectType() {
        return objectType;
    }

    public void setObjectType(LCPLClass objectType) {
        this.objectType = objectType;
    }

    public LCPLClass getStringType() {
        return stringType;
    }

    public void setStringType(LCPLClass stringType) {
        this.stringType = stringType;
    }

    public LCPLClass getIoType() {
        return ioType;
    }

    public void setIoType(LCPLClass ioType) {
        this.ioType = ioType;
    }

    public Program(int lineNumber, List<LCPLClass> classes) {
        super(lineNumber);
        this.classes = classes;
    }
    public Program () {}

    public LCPLClass getClass(String className) {
        return _classes.get(className);
    }

    public void putClass(LCPLClass _class) {
        _classes.put(_class.getName(), _class);
    }

    public Type returnSmallerClass(Type t1, Type t2, int times) {
        if (times == 2) {
            return getNoType();
        }
        if (t1 == t2) {
            return t1;
        }
        if (t1 == getNullType() && t2 instanceof LCPLClass) {
            return t2;
        }

        if (t1 instanceof LCPLClass && t2 instanceof LCPLClass) {
            LCPLClass c1 = (LCPLClass)t1;
            LCPLClass c2 = (LCPLClass)t2;
            while(c1 != null) {
                if (c1 == c2) {
                    return t2;
                }
                c1 = c1.getParentData();
            }
        }
        return returnSmallerClass(t2, t1, times + 1);
    }

    public boolean canConvert(Type t1, Type t2) {

        if (t1 == t2) {
            return true;
        }
        if (t2 == getNoType()) {
            return true;
        }
        if (t1 == getNoType() && t2 != getNoType()) {
            return false;
        }
        if (t1 == getNullType() && t2 instanceof LCPLClass) {
            return true;
        }

        if (t1 == getStringType() && t2 == getIntType()) {
            return false;
        }
        if (t1 == getIntType() && t2 == getStringType()) {
            return true;
        }
        if (t1 instanceof LCPLClass && t2 instanceof LCPLClass) {
            LCPLClass c1 = (LCPLClass)t1;
            LCPLClass c2 = (LCPLClass)t2;

            while (c1 != null) {
                if (c2 == c1) {
                    return true;
                }
                c1 = c1.getParentData();
            }
        }
        return false;
    }

    public void handleClasses() {

        _classes = new HashMap<String, LCPLClass>();
        LinkedList<Feature> objectFeatures;
        this.noType = new NoType();
        this.nullType = new NullType();
        this.intType = new IntType();

        /* create String class */
        objectFeatures = new LinkedList<Feature>();
        objectFeatures.add(new Method(0, "length", new LinkedList<FormalParam>(), "Int", null));
        objectFeatures.add(new Method(0, "toInt", new LinkedList<FormalParam>(), "Int", null));
        this.stringType = new LCPLClass(0, "String", null, objectFeatures);

        /* create Object class*/
        objectFeatures = new LinkedList<Feature>();
        objectFeatures.add(new Method(0, "abort", new LinkedList<FormalParam>(), "void", null));
        objectFeatures.add(new Method(0, "typeName", new LinkedList<FormalParam>(), "String", null));
        objectFeatures.add(new Method(0, "copy", new LinkedList<FormalParam>(), "Object", null));
        this.objectType = new LCPLClass(0, "Object", null, objectFeatures);

        /* create IO class */
        objectFeatures = new LinkedList<Feature>();
        LinkedList<FormalParam> params = new LinkedList<FormalParam>();
        params.add(new FormalParam("msg", "String"));
        objectFeatures.add(new Method(0, "out", params, "IO", null));
        objectFeatures.add(new Method(0, "in", new LinkedList<FormalParam>(), "String", null));
        this.ioType = new LCPLClass(0, "IO", null, objectFeatures);

        this.classes.add(this.objectType);
        this.classes.add(this.ioType);
        this.classes.add(this.stringType);

        /* check class duplicates */
        for (LCPLClass _class : classes) {
            if(getClass(_class.getName()) != null) {
                System.err.println("Error in line " + _class.getLineNumber() + 
                        " : A class with the same name already exists : " + _class.getName());
                System.exit(0);
            }
            putClass(_class);
        }

        /* check if main class exists */
        if (getClass("Main") == null) {
            System.err.println("Error in line 1 : Class Main not found.");
            System.exit(0);
        }

        /* set parents */
        for(LCPLClass _class : classes) {
            if (!_class.getName().equals("Object")) {
                if (_class.getParent() == null) {
                    _class.setParent("Object");
                    _class.setParentData(this.objectType);
                } else {
                    _class.setParentData(getClass(_class.getParent()));
                } 
            }
        }

        /* collect features */
        for (LCPLClass _class : classes) {
            _class.collectFeatures(this);
        }

        /* check for errors */
        for (LCPLClass _class : classes) {
            _class.checkForErrors(this);
        }

        /* collect additional info */
        for (LCPLClass _class : classes) {
            _class.handleNode(this, null, null, null);
        } 
    }
}
