/**
 * 
 */
package curs4;

import java.io.Serializable;
import java.net.URL;

/**
 * @author FlorinPop
 *
 */
public class GrafOb implements Serializable {

	private static final long serialVersionUID = 241020081745L;
	public URL o1;
	public URL o2;
	
	public GrafOb(URL o1, URL o2) {
		this.o1 = o1;
		this.o2 = o2;
	}
	
	public String toString() {
		return new String("o1:" + o1 + " o2:" + o2);
	}
}
