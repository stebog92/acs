/**
 * 
 */
package curs4;

/**
 * @author FlorinPop
 *
 */
public class TestClone {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CevaC cc1 = new CevaC("AAAA");
		CevaC cc2 = (CevaC)cc1.clone();
		System.out.println("Info despre cc1");
		cc1.afiseaza();
		cc1.modifica("CCCCCCCCCC");
		System.out.println("Info despre cc1");
		cc1.afiseaza();
		System.out.println("Info despre cc2");
		cc2.afiseaza();
	}

}
