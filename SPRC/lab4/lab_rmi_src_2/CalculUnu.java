/**
 * 
 */
package curs5;

import java.io.Serializable;

/**
 * @author FlorinPop
 *
 */
public class CalculUnu implements CalculInterf, Serializable {

	private static final long serialVersionUID = 131120082124L;

	/* (non-Javadoc)
	 * @see curs5.CalculInterf#efectueazaCalcul(int, int)
	 */
	@Override
	public int efectueazaCalcul(int x, int y) {
		// TODO Auto-generated method stub
		return x+y;
	}

}
