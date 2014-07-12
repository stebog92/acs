/**
	Sisteme de programe pentru retele de calculatoare
	
	Copyright (C) 2008 Ciprian Dobre & Florin Pop
	Univerity Politehnica of Bucharest, Romania

	This program is free software; you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
 */

package cap5.example5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.rmi.RMISecurityManager;
import java.security.AllPermission;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.Policy;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Clasa test pentru noul nostru algoritm criptografic (contine main)
 * @author Dobre Ciprian
 *
 */
public class Main {

	public static void main(String args[]) {
		
		// for some JDK versions this steps are necessary
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		Policy.setPolicy(new Policy() {
			public PermissionCollection getPermissions(CodeSource codesource) {
				Permissions perms = new Permissions();
				perms.add(new AllPermission());
				return (perms);
			}
			public void refresh() {
			}
		});

		Provider p = new XORProvider();
		// astfel se poate instala dinamic noul nostru Security Provider
		Security.addProvider(p);
		// generam un test pentru a verifica daca providerul a fost sau nu instalat ...
//		ProviderInformation.printProviders();
		
		/** In prima parte criptam un text folosind un output stream si algoritmul nostru propriu */
		
		try {
			// generatorul de chei se poate obtine plecand de la numele atasat definit in providerul atasat
			KeyGenerator kg = KeyGenerator.getInstance("XOR", p);
			// si la fel se poate obtine si obiectul Cipher ...
			Cipher c = Cipher.getInstance("XOR", p);
			// initializam generatorul de chei
			kg.init(new SecureRandom());
			// generam o noua cheie
			SecretKey key = kg.generateKey();
			// initializam obiectul Cipher cu cheia anterior obtinuta si specificarea modului Encrypt
			c.init(Cipher.ENCRYPT_MODE, key);
			// Initializarea unui stream de iesire criptat
			CipherOutputStream cos = new CipherOutputStream(new FileOutputStream("crypt.dat"), c);
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(cos));
			pw.println("Acesta este textul pe care dorim sa il criptam");
			pw.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
		/** In a doua parte incercam sa decriptam textul nostru anterior scris in fisier folosind un input stream */
		
		try {
			// primele etape sunt similare cu cele prezentate anterior
			KeyGenerator kg = KeyGenerator.getInstance("XOR", p);
			Cipher c = Cipher.getInstance("XOR", p);
			kg.init(new SecureRandom());
			SecretKey key = kg.generateKey();
			
			// initializam obiectul Cipher cu cheia anterior obtinuta si specificarea modului Decrypt
			c.init(Cipher.DECRYPT_MODE, key);
			// Initializarea unui stream de intrare criptat
			CipherInputStream cis = new CipherInputStream(new FileInputStream("crypt.dat"), c);
			BufferedReader br = new BufferedReader(new InputStreamReader(cis));
			System.out.println("Am citit: "+br.readLine());
			br.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
} // end of class Main


