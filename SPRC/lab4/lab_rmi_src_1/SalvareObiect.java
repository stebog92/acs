/**
 * 
 */
package curs4;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

/**
 * @author FlorinPop
 *
 */
public class SalvareObiect {
	static GrafOb go;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("Salvare obiect");
			FileOutputStream fout = new FileOutputStream("test");
			ObjectOutputStream sout = new ObjectOutputStream(fout);
			
			URL o1 = new URL("http://www.acs.pub.ro");
			URL o2 = o1, o3 = o1;
			
			go = new GrafOb(o1, o2);
			sout.writeObject(go);
			sout.writeObject(o3);
			sout.flush();
			
			System.out.println("S-a scris obiectul " + go);
			System.out.println("S-a scris obiectul " + o3);
			System.out.println("(go.o1==go.o2)&&(go.o1==o3) este " + ((go.o1==go.o2)&&(go.o1==o3)));
			
			sout.close();
			fout.close();
		}catch (Exception e) {
			System.out.println("Eroare:"  + e);
		}
	}
}
