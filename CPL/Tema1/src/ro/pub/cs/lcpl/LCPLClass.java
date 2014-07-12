package ro.pub.cs.lcpl;

import java.util.List;

/* An LCPL class
 * class <name> inherits <parent> <feature>... end;
 */

public class LCPLClass extends TreeNode {
	private String name;
	private String parent;
	private List<Feature> features;
	
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
}
