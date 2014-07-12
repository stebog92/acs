package ro.pub.cs.lcpl;


/** A method or attribute of a class */
public interface Feature {
    public abstract void collectFeature(LCPLClass _class, Program program);
    public abstract void checkForErrors(LCPLClass _class, Program program);
}
