package ro.pub.cs.lcpl;
import java.util.List;

/* A complete LCPL program
 * <program> = <class> ... 
 */

public class Program extends TreeNode {
	private List<LCPLClass> classes;

	public List<LCPLClass> getClasses() {
		return classes;
	}

	public void setClasses(List<LCPLClass> classes) {
		this.classes = classes;
	}

	public Program(int lineNumber, List<LCPLClass> classes) {
		super(lineNumber);
		this.classes = classes;
	}

}
