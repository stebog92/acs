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

import javax.crypto.SecretKey;

public class XORKey implements SecretKey {

	byte value;

	public XORKey(byte b) {
		value = b;
	}
	
	public String getAlgorithm() {
		return "XOR";
	}
	
	public String getFormat() {
		return "XOR Special Format";
	}
	
	public byte[] getEncoded() {
		byte b[] = new byte[1];
		b[0] = value;
		return b;
	}
} // end of class XORKey


