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

import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.KeyGeneratorSpi;
import javax.crypto.SecretKey;

public class XORKeyGenerator extends KeyGeneratorSpi {

	SecureRandom sr;
	
	public void engineInit(SecureRandom sr) {
		this.sr = sr;
	}
	
	public void engineInit(AlgorithmParameterSpec ap, SecureRandom sr) throws InvalidAlgorithmParameterException {
		throw new InvalidAlgorithmParameterException("No parameters supported in this class");
	}
	
	public SecretKey engineGenerateKey() {
		if (sr == null)
			sr = new SecureRandom();
		byte b[] = new byte[1];
		sr.nextBytes(b);
		return new XORKey(b[0]);
	}

	protected void engineInit(int nr, SecureRandom sr) {
		this.sr = sr;
		
	}
} // end of class XORKeyGenerator


