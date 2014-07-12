/**
 * 
 */
package curs5;

/**
 * @author FlorinPop
 *
 */
public interface ServerCalculInterf extends java.rmi.Remote {
	public CalculInterf obtineCalcul() throws java.rmi.RemoteException;
	public int obtineRezultat(int x, int y) throws java.rmi.RemoteException;
}
