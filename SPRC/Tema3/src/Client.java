import java.io.*;
import java.net.InetSocketAddress;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.security.cert.X509Certificate;
import java.util.*;
/**
 * Clasa ce implementeaza functionalitatea unui client ce se conecteaza la un server folosind TLS.
 */
public class Client {

    private BufferedReader br;
    private PrintWriter pw;
    private SSLSocket s;
    private static boolean hasToRun;
    private KeyStore ks;
    private static String name;

    public void createSSLConnection (String address, int port) throws Exception{

        // set up key manager to do server authentication
        String store=System.getProperty("KeyStore");
        String passwd =System.getProperty("KeyStorePass");

        SSLContext ctx;
        KeyManagerFactory kmf;
        TrustManagerFactory trustManager;
        char[] storepswd = passwd.toCharArray(); 
        ctx = SSLContext.getInstance("TLS");

        kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        ks = KeyStore.getInstance(KeyStore.getDefaultType());
        trustManager = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

        ks.load(new FileInputStream(store), storepswd);
        trustManager.init(ks);
        kmf.init(ks,storepswd);
        ctx.init(kmf.getKeyManagers(), trustManager.getTrustManagers(), new java.security.SecureRandom());
        SSLSocketFactory ssf = ctx.getSocketFactory();
        try {
            s = (SSLSocket)ssf.createSocket(address, port);
        } catch (java.net.ConnectException e) {
            System.out.println("Server is unreachable!");
            System.exit(0);
        }
        try {
            s.startHandshake();
        } catch (IOException e) {
            System.out.println("Logging failed due to lack of certificates!");
            System.exit(0);
        }

        pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        //get name
        for (Enumeration<String> e = ks.aliases(); e.hasMoreElements();) {
            String alias = e.nextElement();
            if (!alias.equals("server")) {
               name = (((X509Certificate)ks.getCertificate(alias)).getSubjectX500Principal().getName().split(",")[0]).substring(3);
               break;
            }
        }

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
    } //sendCommand

    public String receiveResponseLine() throws Exception {
        return br.readLine() ;
    } //receiveResponseLine

    /* send file to server */
    public void upload(String msg) {
        String filename = msg.substring(7);
        File file = new File(filename);
        try {
            if (!file.exists()) {
                System.out.println("File " + filename + " is missing!");
                return;
            }
            sendCommand(msg);
            System.out.println("Send " + filename);
            if (!br.readLine().equals("true")) {
                System.out.println("You were banned for 30 secs!");
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            char[] buffer = new char[1024];
            int size = (int)file.length();
            pw.println(size);
            pw.flush();
            int pos = 0;

            while (pos != size) {
                int bytes_read = reader.read(buffer, 0, 1024);
                pw.write(buffer, 0, bytes_read);
                pw.flush();
                pos += bytes_read;
            }
            System.out.println("File sended");
            reader.close();
        } catch(Exception e) {
        }
    }

    /* receive file from server */
    public void download(String msg) {

        String filename = msg.substring(9);
        File dl = new File("download");
        if (!dl.exists()) {
            dl.mkdir();
        }
        File file = new File("download/" + filename);
        sendCommand(msg);
        try {
            String status = br.readLine();
            if (status.equals("missing")) {
                System.out.println("File " + filename + " is missing!");
                return;
            } else if (status.equals("true")) {
                System.out.println("Downloading...");
            } else {
                System.out.println("Access denied!");
                return;
            }

            PrintWriter fileWriter = new PrintWriter(new FileWriter(file));
            char[] buffer = new char[1024];
            int size = Integer.parseInt(br.readLine());

            System.out.println("File length is " + size);
            int pos = 0;

            while (pos != size) {
                int bytes_read = br.read(buffer, 0, 1024);
                fileWriter.write(buffer, 0, bytes_read);
                fileWriter.flush();
                pos += bytes_read;
            }

            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("File " + filename + " downloaded successfully");

    }

    /* get files list from server */
    public void list () {
        sendCommand("list");
        try {
            while (true) {
                String file = receiveResponseLine();
                if (file.equals("eof")) {
                    break;
                }
                System.out.println(file);
            }
        } catch(Exception e) {

        }
    }

    /* process commands */
    public void processCommand(String cmd) {
        if (cmd.startsWith("download")) {
            download(cmd);
        } else if (cmd.startsWith("upload")) {
            upload(cmd);
        } else if (cmd.startsWith("list")) {
            list();
        }
    }

    public static void main(String args[]) {

        boolean hasToRun = true;
        if (args == null || args.length < 2) {
            System.out.println("Nu au fost furnizate adresa si portul serverului");
            return;
        }
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String cmd;
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
            while(hasToRun) {
                System.out.print(name + " > ");
                cmd = input.readLine();
                if (cmd.equals("exit")) {
                    c.close();
                    break;
                } else {
                    c.processCommand(cmd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

} // end of class Client
