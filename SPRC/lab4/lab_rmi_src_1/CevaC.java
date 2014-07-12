/**
 * 
 */
package curs4;

import java.io.Serializable;

/**
 * @author FlorinPop
 *
 */
public class CevaC extends Clonare implements Serializable {

	private static final long serialVersionUID = 241020081756L;
	private String name;

	public CevaC(String s) {
		name = s;
		System.out.println("CevaC: eu extind clasa Clonare, sunt serializabil si am numele: " + name);
	}
	
	public void afiseaza(){
		System.out.println(name);
	}
	
	public void modifica(String s){
		name = s;
	}
}
