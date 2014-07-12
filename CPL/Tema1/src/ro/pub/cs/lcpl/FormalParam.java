package ro.pub.cs.lcpl;

/* A formal parameter in the declaration of a method */

public class FormalParam {
	private String name;
	private String type;
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
	public FormalParam(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	 
}
