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

package cap5.example4;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * Demonstratie de criptografie folosind parola.
 * @author Dobre Ciprian
 *
 */
public class PBenc {

	/**
	 * Reads user password from given input stream.
	 */
	public static char[] readPasswd(InputStream in) throws IOException {
		char[] lineBuffer;
		char[] buf;
		int i;
		buf = lineBuffer = new char[128];
		int room = buf.length;
		int offset = 0;
		int c;
		loop: while (true) {
			switch (c = in.read()) {
			case -1:
			case '\n':
				break loop;
			case '\r':
				int c2 = in.read();
				if ((c2 != '\n') && (c2 != -1)) {
					if (!(in instanceof PushbackInputStream)) {
						in = new PushbackInputStream(in);
					}
					((PushbackInputStream) in).unread(c2);
				} else
					break loop;
			default:
				if (--room < 0) {
					buf = new char[offset + 128];
					room = buf.length - offset - 1;
					System.arraycopy(lineBuffer, 0, buf, 0, offset);
					Arrays.fill(lineBuffer, ' ');
					lineBuffer = buf;
				}
			buf[offset++] = (char) c;
			break;
			}
		}
		if (offset == 0) {
			return null;
		}
		char[] ret = new char[offset];
		System.arraycopy(buf, 0, ret, 0, offset);
		Arrays.fill(buf, ' ');
		return ret;
	}

	public static void main(String[] args) {
		PBEKeySpec pbeKeySpec;
		PBEParameterSpec pbeParamSpec;
		SecretKeyFactory keyFac;
		//		Salt
		byte[] salt = { (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xee, (byte) 0x99 };
		//		Iteration count
		int count = 20;
		//		Create PBE parameter set
		pbeParamSpec = new PBEParameterSpec(salt, count);
		//		Prompt user for encryption password.
		//		Collect user password as char array (using the
		//		"readPasswd" method from above), and convert
		//		it into a SecretKey object, using a PBE key
		//		factory.
		System.out.print("Enter encryption password: ");
		System.out.flush();
		try {
			pbeKeySpec = new PBEKeySpec(readPasswd(System.in));
			keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
			//			Create PBE Cipher
			Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
			//			Initialize PBE Cipher with key and parameters
			pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);
			//			Our cleartext
			byte[] cleartext = "plaintext".getBytes();
			//			Encrypt the cleartext
			byte[] ciphertext = pbeCipher.doFinal(cleartext);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
