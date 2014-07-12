/**
 * 
 */
package curs4;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;

/**
 * @author FlorinPop
 *
 */
public class RefacereObiect {
	static GrafOb go;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("Restaurare obiect");
			FileInputStream fin = new FileInputStream("test");
			ObjectInputStream sin = new ObjectInputStream(fin);
			
			go = (GrafOb)sin.readObject();
			System.out.println("S-a citit obiectul: " + go);
			
			URL o3 = (URL)sin.readObject();
			System.out.println("S-a citit obiectul: " + o3);
			System.out.println("(go.o1==go.o2)&&(go.o1==o3) este " + ((go.o1==go.o2)&&(go.o1==o3)));
			
			sin.close();
			fin.close();
		} catch (Exception e) {
			System.err.println("Eroare: " + e);
		}
	}
}
