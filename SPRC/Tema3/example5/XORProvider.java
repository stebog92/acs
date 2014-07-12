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

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;

/**
 * Exemplu de specificare a unui nou Security Provider ce mapeaza nume de algoritmi de 
 * criptografie versus clase ce implementeaza respectivele functionalitati criptografice.
 * @author Dobre Ciprian
 *
 */
public class XORProvider extends Provider {

	public XORProvider() {
		// specify the provider's short name, version and long name
		super ("student", 1.2, "Ciprian's Cryptography Provider");

		put("Cipher.XOR", "capV.example5.XORCipher");
		put("KeyGenerator.XOR", "capV.example5.XORKeyGenerator");
	}
} // end of class XORProvider

