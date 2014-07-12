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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.util.logging.Logger;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * Clasa ce implementeaza functionalitatea unui client ce se conecteaza la un server folosind TLS.
 * @author Dobre Ciprian
 *
 */
public class Client {

	/** Logger used by this class */
	private static final transient Logger logger = Logger.getLogger("capV.example3.Client");

	private BufferedReader br;
	private PrintWriter pw;
	private SSLSocket s;
	
	public void createSSLConnection (String address, int port) throws Exception{

		// set up key manager to do server authentication
		String store=System.getProperty("KeyStore");
		String passwd =System.getProperty("KeyStorePass");

		SSLContext ctx;
		KeyManagerFactory kmf;
		KeyStore ks;
		char[] storepswd = passwd.toCharArray(); 
		ctx = SSLContext.getInstance("TLS");

		/* IBM or Sun vm ? */
		if ( System.getProperty("java.vm.vendor").toLowerCase().indexOf("ibm") != -1 ){
			kmf = KeyManagerFactory.getInstance("IBMX509","IBMJSSE");
		} else {
			kmf = KeyManagerFactory.getInstance("SunX509");
		}

		ks = KeyStore.getInstance("JKS");

		ks.load(new FileInputStream(store), storepswd);
		kmf.init(ks,storepswd);
		ctx.init(kmf.getKeyManagers(), new TrustManager[] {new CustomTrustManager()}, null);
		SSLSocketFactory ssf = ctx.getSocketFactory();
		s = (SSLSocket)ssf.createSocket();

		s.connect(new InetSocketAddress(address, port));

		pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));

	} //createSSLConnection

	public void close() {
		if (br != null){
            try {
                br.close();
            }catch(Throwable tt){
            }
        }
        if (pw != null){
            try {
                pw.close();
            }catch(Throwable tt){
            }
        }
        if ( s != null){
            try {
                s.close();
            } catch (Throwable t){
            }
        }
	}
	
	public void sendCommand (String command) {
		pw.println (command);
		pw.flush();
		logger.info("Sent command "+command);
	} //sendCommand

	public String receiveResponseLine() throws Exception {
		return br.readLine() ;
	} //receiveResponseLine

	public static void main(String args[]) {
		if (args == null || args.length < 2) {
			System.out.println("Nu au fost furnizate adresa si portul serverului");
			return;
		}
		String host = args[0];
		int port = 0;
		try {
			port = Integer.parseInt(args[1]);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		Client c = new Client();
		try {
			c.createSSLConnection(host, port);
			c.sendCommand("test1");
			String r = c.receiveResponseLine();
			logger.info("Received: "+r);
			c.sendCommand("cmd");
			r = c.receiveResponseLine();
			logger.info("Received: "+r);
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class Client


