/**
 * 
 */
package curs5;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author FlorinPop
 *
 */
public class ServerCalcul extends UnicastRemoteObject implements ServerCalculInterf {

	private static final long serialVersionUID = 131120082127L;
	CalculInterf c = null;

	ServerCalcul() throws java.rmi.RemoteException { }
	
	/* (non-Javadoc)
	 * @see curs5.ServerCalculInterf#obtineCalcul()
	 */
	@Override
	public CalculInterf obtineCalcul() throws RemoteException {
		// TODO Auto-generated method stub
		this.c = new CalculUnu();
	    return this.c;
	}
	
	public int obtineRezultat(int x, int y) throws java.rmi.RemoteException  {
		return this.c.efectueazaCalcul(x, y);
	}

}
