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

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.DSAPrivateKeySpec;

/**
 * Clasa ce demonstreaza importarea unei chei.
 * @author Dobre Ciprian
 *
 */
public class Import {
	public static void main(String args[]) {
		try {
			FileInputStream fis = new FileInputStream("exportedKey");
			ObjectInputStream ois = new ObjectInputStream(fis);
			DSAPrivateKeySpec ks = new DSAPrivateKeySpec(
					(BigInteger) ois.readObject(),
					(BigInteger) ois.readObject(),
					(BigInteger) ois.readObject(),
					(BigInteger) ois.readObject());
			KeyFactory kf = KeyFactory.getInstance("DSA");
			PrivateKey pk = kf.generatePrivate(ks);
			System.out.println("Got private key");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

