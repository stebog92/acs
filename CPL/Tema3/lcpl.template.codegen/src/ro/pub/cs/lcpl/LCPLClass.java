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
	

}
