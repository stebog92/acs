package ro.pub.cs.lcpl;

public class LCPLException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String message;
	public TreeNode node;
	public LCPLException(String message, TreeNode node) {
		this.message = message;
		this.node = node;
	}	
}
