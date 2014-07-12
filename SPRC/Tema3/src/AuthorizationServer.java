import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.security.KeyStore;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import java.util.*;
public class AuthorizationServer {


    protected static ServerSocket ss = null;
    private static HashMap<String, Integer> dept;
    private static BufferedReader in;
    private static PrintWriter pw;
    private static String banfilename = "bannedclients";
    private static long banTime = 30000;

    public AuthorizationServer(int port) throws Exception {
        String store=System.getProperty("KeyStore");
        String passwd =System.getProperty("KeyStorePass");
        ss = createServerSocket(port, store, passwd);
        dept = new HashMap<String, Integer> ();
        dept.put("HR", 1);
        dept.put("IT", 2);
        dept.put("Accounting", 3);
    }

    /* send client rights for a certain operation */
    public static void getRight(String msg) {
        System.out.println(msg);
        String[] bits = msg.split(" ");
        int ownerId = dept.get(bits[1]);
        int clientId = dept.get(bits[2]);
        String entry, toBeDeleted = null;
        ArrayList<String> entries = new ArrayList<String>();
        boolean banned = false;
        boolean exists = false;


        /* check if client is in banned list */
        System.out.println("Check if client is in banned list");
        try {

            File banfile = new File(banfilename);
            if (banfile.exists()) {
                BufferedReader filereader = new BufferedReader(new FileReader(banfile));
                while((entry = filereader.readLine()) != null) {
                    entries.add(entry);
                    if (entry.startsWith(bits[3])) {
                        exists = true;
                        toBeDeleted = entry;
                        if ((new Date()).getTime() < Long.parseLong(entry.split(" ")[1]) + banTime) {
                            banned = true;
                        }
                    }
                }
            }

            if (exists) {
                if (banned) {
                    System.out.println("Client is still banned!");
                    pw.println("denied");
                    pw.flush();
                    return;
                } else {
                    System.out.println("Ban period expired! Removed client from banned list!");
                    banfile.delete();
                    entries.remove(toBeDeleted);
                    PrintWriter filewriter = new PrintWriter(banfile);
                    for (String _entry : entries) {
                        filewriter.println(_entry);
                        filewriter.flush();
                    }
                    filewriter.close();
                    pw.println("approved");
                    pw.flush();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Client is not in banned list!");
        System.out.println("Requesting rights for client from "+ bits[2] +" and file owner " + bits[1]);
        if (ownerId <= clientId) {
            pw.println("approved");
        } else {
            pw.println("denied");
        }
        pw.flush();
    }

    /* insert client into banned list */
    public static void banClient(String msg) {
        try {
            File banfile = new File(banfilename);
            PrintWriter filewriter = new PrintWriter(new FileWriter(banfile, true));
            filewriter.println(msg.split(" ")[1] + " " + new Date().getTime());
            filewriter.flush();
            filewriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /* create server socket */
    public static SSLServerSocket createServerSocket(int port, String keystore, String password) throws IOException {
        SSLServerSocketFactory ssf = null;
        SSLServerSocket ss = null;
        try {
            SSLContext ctx;
            KeyManagerFactory kmf;
            KeyStore ks;
            TrustManagerFactory trustManager;

            ctx = SSLContext.getInstance("TLS");
            kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            ks = KeyStore.getInstance(KeyStore.getDefaultType());
            trustManager = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            FileInputStream is = new FileInputStream(keystore);
            ks.load(is, password.toCharArray());
            kmf.init(ks, password.toCharArray());
            trustManager.init(ks);


            ctx.init(kmf.getKeyManagers(), trustManager.getTrustManagers(), new java.security.SecureRandom());
            ssf = ctx.getServerSocketFactory();
            ss = (SSLServerSocket) ssf.createServerSocket(port);
            ss.setNeedClientAuth(true);
        } catch (Throwable t) {

            t.printStackTrace();
            throw new IOException(t.getMessage());
        }
        return ss;
    }

    public static void main(String[] args) {
        Socket serverSocket;
        try{
            AuthorizationServer as = new AuthorizationServer(5001);
            serverSocket = ss.accept();
            System.out.println("Server connected");

            in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            pw = new PrintWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
            while (true) {
                String msg = in.readLine();
                if (msg.startsWith("get_right")) {
                    getRight(msg);
                } else if (msg.startsWith("ban")) {
                    banClient(msg);
                } else if (msg.equals("exit")) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Server disconnected");
        }

    }

}
