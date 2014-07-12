/**
 * 
 */
package curs5;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;

/**
 * @author FlorinPop
 *
 */
public class ClientRMICalcul {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CalculInterf ci = null;
	    System.setSecurityManager(new RMISecurityManager());
	    try {
	    	Remote robj = Naming.lookup("ServerCalcul");
	    	System.out.println("S-a obtinut referinta la server " + robj.getClass());
	    	ServerCalculInterf sci = (ServerCalculInterf)robj;
	    	ci = (CalculInterf)sci.obtineCalcul();
	    	System.out.println("S-a obtinut referinta la " + ci.getClass());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    int z = ci.efectueazaCalcul(5, 3);
	    System.out.println("Rezultat = " + z);
	}
}
