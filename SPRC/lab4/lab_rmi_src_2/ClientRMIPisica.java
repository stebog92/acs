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
public class ClientRMIPisica {
	
	static public void main(String args[]) {
		System.setSecurityManager(new RMISecurityManager());
		String unde = "rmi://kermit.cs.pub.ro/ServerPisica";
		
		try {
			Remote robj = Naming.lookup(unde);
			System.out.println("S-a obtinut referinta la " +  robj.getClass());
			
			ServerPisicaInterf spi = (ServerPisicaInterf) robj;
			
			PisicaInterf pi = (PisicaInterf) spi.referintaPisica();
			System.out.println("S-a obtinut referinta la " +  pi.getClass());
			
			PisicaObisnuita po = (PisicaObisnuita) pi.obtinePisica();
			po.Afisaza();
			
		} catch (Exception e) {
			System.out.println("Eroare " + e);
			System.exit(0);
		}
	}

}
