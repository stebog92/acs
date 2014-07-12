package ro.pub.cs.lcpl;

import java.util.HashMap;
import java.util.List;

/** An LCPL class
 * class <i>name</i> inherits <i>parent</i> <i>feature</i>... end;
 */
public class LCPLClass extends TreeNode implements Type {
    private String name;

    /** Name of the superclass, it can be null */
    private String parent;
    private List<Feature> features;
    private HashMap<String, Method> methods;
    private HashMap<String, Attribute> attributes;

    /** A reference to the superclass of this class, or "null" for the class hierarchy root (Object) */
    private LCPLClass parentData;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getParent() {
        return parent;
    }
    public void setParent(String parent) {
        this.parent = parent;
    }
    public LCPLClass getParentData() {
        return parentData;
    }
    public void setParentData(LCPLClass parentData) {
        this.parentData = parentData;
    }
    public List<Feature> getFeatures() {
        return features;
    }
    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public LCPLClass(int lineNumber, String name, String parent,
            List<Feature> features) {
        super(lineNumber);
        this.name = name;
        this.parent = parent;
        this.features = features;
    }
    public LCPLClass() {}


    public void addMethod(Method method) {
        this.methods.put(method.getName(), method);
    }

    public Method getMethod(String name) {
        Method method = this.methods.get(name);
        LCPLClass parent;
        if (method == null) {
            parent = this.getParentData();
            if (parent != null) {
                method = parent.getMethod(name);
            }
        }
        return method;
    }

    /* add attribute */
    public void addAttribute(Attribute attribute) {
        this.attributes.put(attribute.getName(), attribute);
    }

    /* search and return attribute */
    public Attribute getAttribute(String name) {
        return this.attributes.get(name);
    }

    /* check if method exists */
    public boolean containsMethod(String name) {
        return this.methods.containsKey(name);
    }

    /* collect features */
    public void collectFeatures(Program program) {
        methods = new HashMap<String, Method>();
        attributes = new HashMap<String, Attribute>();
        for (Feature feature : features) {
            feature.collectFeature(this, program);
        }
    }

    /* check for errors */
    public void checkForErrors(Program program) {

        /* check if parent class exists */
        if (this.parent != null && program.getClass(this.parent) == null) {
            System.err.println("Error in line " + this.getLineNumber() + 
                        " : Class " + this.parent + " not found.");
            System.exit(0);
        }

        /* check for parent loop */
        LCPLClass c = this.getParentData();
        while (c != null) {
            if (c.getName().equals(this.name)) {
                System.err.println("Error in line " + this.getLineNumber() + 
                        " : Class " + this.name + " recursively inherits itself.");
                System.exit(0);
            }
            c = c.getParentData();
        }
        
        /* check if method main exists in a Main class */
        if (this.name.equals("Main") && getMethod("main") == null) {
            System.err.println("Error in line " + this.getLineNumber() + 
                " : Method main not found in class Main");
            System.exit(0);
        }

        /* check if parent is int */
        if (this.parent != null && this.parent.equals("Int")) {
            System.err.println("Error in line " + this.getLineNumber() + " : Class Int not found.");
            System.exit(0);
        }
        
        /* check if parent is String */
        if (this.parent != null && this.parent.equals("String")) {
            System.err.println("Error in line " + this.getLineNumber() + " : A class cannot inherit a String");
            System.exit(0);
        }

        /* check features for errors */
        for (Feature feature : features) {
            feature.checkForErrors(this, program);
        }
    }

    /* handle feature nodes */
    public void handleNode(Program program, LCPLClass _class, Method method,
        LocalDefinition localDefinition) {

        for (Feature feature : features) {
            ((TreeNode)feature).handleNode(program, this, method, localDefinition);
        }
    }

}
