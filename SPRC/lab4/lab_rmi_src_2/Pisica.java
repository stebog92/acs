/**
 * 
 */
package curs5;

import java.rmi.RemoteException;

/**
 * @author FlorinPop
 *
 */
public class Pisica implements PisicaInterf {
	
	PisicaObisnuita po = null;
	
	public Pisica(String numePisica) throws RemoteException{
		super();
		po = new PisicaObisnuita(numePisica);
		java.rmi.server.UnicastRemoteObject.exportObject(this);
	}

	/* (non-Javadoc)
	 * @see curs5.PisicaInterf#obtinePisica()
	 */
	@Override
	public PisicaObisnuita obtinePisica() throws RemoteException {
		System.out.println("Referinta la " + po.getClass());
		return po;
	}

}
