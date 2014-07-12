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

package cap5.example2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Clasa ce demonstreaza operatiile de criptare/decriptare a datelor.
 * @author Dobre Ciprian
 *
 */
public class SecretWriting {
	
	public static void main(String[] args) throws Exception {
		//	Check arguments.
		if (args.length < 2) {
			System.out.println("Usage: SecretWriting -e|-d text");
			return;
		}
		//	Get or create key.
		Key key;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("SecretKey.ser"));
			key = (Key)in.readObject();
			in.close();
		} catch (FileNotFoundException fnfe) {
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom());
			key = generator.generateKey();
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("SecretKey.ser"));
			out.writeObject(key);
			out.close();
		}
		// Get a cipher object.
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		// Encrypt or decrypt the input string.
		if (args[0].indexOf("e") != -1) {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			String amalgam = args[1];
			for (int i = 2; i < args.length; i++)
				amalgam += " " + args[i];
			byte[] stringBytes = amalgam.getBytes("UTF8");
			byte[] raw = cipher.doFinal(stringBytes);
			BASE64Encoder encoder = new BASE64Encoder();
			String base64 = encoder.encode(raw);
			System.out.println(base64);
		}
		else if (args[0].indexOf("d") != -1) {
			cipher.init(Cipher.DECRYPT_MODE, key);
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] raw = decoder.decodeBuffer(args[1]);
			byte[] stringBytes = cipher.doFinal(raw);
			String result = new String(stringBytes, "UTF8");
			System.out.println(result);
		}
	}
} // end of class SecretWriting
