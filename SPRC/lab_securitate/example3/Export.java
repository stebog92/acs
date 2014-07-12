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

package cap5.example3;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.DSAPrivateKeySpec;

/**
 * Clasa ce realizeaza exportarea unui certificat
 * @author Dobre Ciprian
 *
 */
public class Export {
	public static void main(String args[]) {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
			kpg.initialize(512, new SecureRandom());
			KeyPair kp = kpg.generateKeyPair();
			Class spec = Class.forName(
			"java.security.spec.DSAPrivateKeySpec");
			KeyFactory kf = KeyFactory.getInstance("DSA");
			DSAPrivateKeySpec ks = (DSAPrivateKeySpec)
			kf.getKeySpec(kp.getPrivate(), spec);
			FileOutputStream fos = new FileOutputStream("exportedKey");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(ks.getX());
			oos.writeObject(ks.getP());
			oos.writeObject(ks.getQ());
			oos.writeObject(ks.getG());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
