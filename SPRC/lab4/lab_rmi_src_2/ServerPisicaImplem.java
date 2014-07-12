/**
 * 
 */
package curs5;

import java.rmi.server.UnicastRemoteObject;

/**
 * @author FlorinPop
 *
 */
public class ServerPisicaImplem extends UnicastRemoteObject implements ServerPisicaInterf {

	private static final long serialVersionUID = 51120082335L;
	Pisica p = null;
	
	ServerPisicaImplem(Pisica p) throws java.rmi.RemoteException {
		super();
		this.p = p;
	}
	
	public PisicaInterf referintaPisica() throws java.rmi.RemoteException{
		System.out.println("se transmite " + p.getClass());
		return p;
	}
}
