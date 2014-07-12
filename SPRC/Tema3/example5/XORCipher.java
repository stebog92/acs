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

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.CipherSpi;
import javax.crypto.NoSuchPaddingException;

public class XORCipher extends CipherSpi {
	
	byte xorByte;

	public void engineInit(int i, Key k, SecureRandom sr) throws InvalidKeyException {
		if (!(k instanceof XORKey))
			throw new InvalidKeyException("XOR requires an XOR key");
		xorByte = k.getEncoded()[0];
	}

	public void engineInit(int i, Key k, AlgorithmParameterSpec aps, SecureRandom sr) throws InvalidKeyException, 
			InvalidAlgorithmParameterException {
		throw new InvalidAlgorithmParameterException("Algorithm parameters not supported in this class");
	}

	public void engineInit(int i, Key k, AlgorithmParameters ap, SecureRandom sr) throws InvalidKeyException,
			InvalidAlgorithmParameterException {
		throw new InvalidAlgorithmParameterException("Algorithm parameters not supported in this class");
	}

	public byte[] engineUpdate(byte in[], int off, int len) {
		return engineDoFinal(in, off, len);
	}

	public int engineUpdate(byte in[], int inoff, int length, byte out[], int outoff) {
		for (int i = 0; i < length; i++)
			out[outoff + i] = (byte) (in[inoff + i] ^ xorByte);
		return length;
	}

	public byte[] engineDoFinal(byte in[], int off, int len) {
		byte out[] = new byte[len - off];
		engineUpdate(in, off, len, out, 0);
		return out;
	}

	public int engineDoFinal(byte in[], int inoff, int len, byte out[], int outoff) {
		return engineUpdate(in, inoff, len, out, outoff);
	}

	public int engineGetBlockSize() {
		return 1;
	}

	public byte[] engineGetIV() {
		return null;
	}

	public int engineGetOutputSize(int sz) {
		return sz;
	}

	public void engineSetMode(String s) throws NoSuchAlgorithmException {
		throw new NoSuchAlgorithmException("Unsupported mode " + s);
	}

	public void engineSetPadding(String s) throws NoSuchPaddingException {
		throw new NoSuchPaddingException("Unsupported padding " + s);
	}

	protected AlgorithmParameters engineGetParameters() {
		return null;
	}
} // end of class XORCipher

