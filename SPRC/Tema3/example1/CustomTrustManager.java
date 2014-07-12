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

package cap5.example1;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Vector;

import javax.net.ssl.X509TrustManager;

/**
 * Un Manager folosit in handshake
 * @author Dobre Ciprian
 *
 */
public class CustomTrustManager implements X509TrustManager {
    
    KeyStore ks = null;

    public CustomTrustManager() {
        this(null);
    }
    
	public CustomTrustManager(KeyStore ks) {
		this.ks = ks;
	}

	/**
	 * @see javax.net.ssl.X509TrustManager#checkClientTrusted(X509Certificate[], String)
	 */
	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
		throws CertificateException {
	    if (arg0 == null || arg0.length == 0) throw new CertificateException();
	    try {
		    if (ks.getCertificateAlias(arg0[0]) == null) throw new CertificateException();
	    }catch(Throwable t){
	        throw new CertificateException(t.getMessage());
	    }
	}

	/**
	 * @see javax.net.ssl.X509TrustManager#checkServerTrusted(X509Certificate[], String)
	 */
	public void checkServerTrusted(X509Certificate[] arg0, String arg1)
		throws CertificateException {
	}

	/**
	 * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
	 */
	public X509Certificate[] getAcceptedIssuers() {
        if ( ks == null ) {
            return null;
        }
        
        try {
            Vector vxcerts = new Vector();
            
            for ( Enumeration en = ks.aliases(); en.hasMoreElements(); ) {
                String alias = (String)en.nextElement();
                if ( !ks.isKeyEntry(alias) ) {
                    vxcerts.add(ks.getCertificate(alias));
                }
            }
            
            if ( vxcerts.size() > 0 ) {
            	return (X509Certificate[])vxcerts.toArray(new X509Certificate[vxcerts.size()]);
            }
        } catch ( Throwable tt ){
            
        }
        return null;
	}

}
