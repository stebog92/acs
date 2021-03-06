package ro.pub.cs.lcpl;

/** Marker for a variable - formal parameter, attribute or local variable
 */
public interface Variable {
	public String getName();
    public Type getVType();
}
