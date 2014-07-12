import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.security.KeyStore;
import java.security.cert.X509Certificate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import javax.crypto.*;
import java.security.*;


public class Server implements Runnable {

    // variabila ce este folosita pentru testarea conditiei de oprire
    protected volatile boolean hasToRun = true;
    // socketul server
    protected ServerSocket ss = null;
    private SSLSocket authorization_socket;

    private BufferedReader as_in;
    private PrintWriter as_out;

    // un pool de threaduri ce este folosit pentru executia secventelor de operatii corespunzatoare
    // conextiunilor cu fiecare client
    final protected ExecutorService pool;
    final private ThreadFactory tfactory;

    private Cipher eCipher;
    private Cipher dCipher;
    private Key encryptionKey;
    private String address = "localhost";


    private String fileTable = "file2clients";

    /**
     * Constructor.
     * @param port Portul pe care va asculta serverul
     * @throws Exception
     */
    public Server(int port) throws Exception {
        // set up key manager to do server authentication
        String store=System.getProperty("KeyStore");
        String passwd =System.getProperty("KeyStorePass");
        ss = createServerSocket(port, store, passwd);
        tfactory = new DaemonThreadFactory();
        pool = Executors.newCachedThreadPool(tfactory);		

        /* set encryption cipher */
        encryptionKey = generateEncryptionKey();

        eCipher = Cipher.getInstance("DES");
        dCipher = Cipher.getInstance("DES");
        eCipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
        dCipher.init(Cipher.DECRYPT_MODE, encryptionKey);

        connectToAS(store, passwd, 5001);
    }


    /* connect to Authorization server */
    public void connectToAS(String store, String passwd, int port) {
        try {
            SSLContext ctx;
            KeyManagerFactory kmf;
            KeyStore ks;
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
            authorization_socket = (SSLSocket)ssf.createSocket(address, port);
            authorization_socket.startHandshake();

            as_out = new PrintWriter(new OutputStreamWriter(authorization_socket.getOutputStream()));
            as_in = new BufferedReader(new InputStreamReader(authorization_socket.getInputStream()));
        } catch (Exception e) {
            System.out.println("Cannot authenticate to AuthorizationServer!");
            System.exit(0);
        }
    }


    /* collect rights for client*/
    public boolean getRight(String msg) {
        as_out.println(msg);
        as_out.flush();

        try {
            if (as_in.readLine().equals("approved")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /* ban client */
    public void banClient(String msg) {
        try {
            as_out.println("ban " + msg);
            as_out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda ce creaza un nou server socket folosind un anumit keystore si parola
     * @param port: port to listen on
     * @param store: the path to keystore file containing server key pair (private/public key); if <code>null</code> is passed 
     * @param passwd: password needed to access keystore file
     * @return a SSL Socket bound on port specified
     * @throws IOException
     */
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



    /* generate encryption key */
    public Key generateEncryptionKey() {
        File encryptionFile = new File("encryptionKey");
        Key key = null;

        try {
            if (encryptionFile.exists()) {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(encryptionFile));
                key = (Key) in.readObject();
                in.close();
            } else {
                KeyGenerator kg = KeyGenerator.getInstance("DES");
                kg.init(new SecureRandom());
                key = kg.generateKey();
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(encryptionFile));
                out.writeObject(key);
                out.close();
            }
        } catch (Exception e) {

        }
        return key;
    }
    

    /* get owner dept of a file from encrypted table */
    public String getOwnerDept(String filename) {
        File encrypted_file = new File(fileTable);
        File decrypted_file = new File("_" + fileTable);
        String file;
        try {

            // decrypt file
            if (encrypted_file.exists()) {
                decryptAndWrite(encrypted_file, decrypted_file);
                BufferedReader in = new BufferedReader(new FileReader(decrypted_file));
                while ((file = in.readLine()) != null) {
                    String bits[] = file.split(" ");
                    if (bits[0].equals(filename)) {
                        decrypted_file.delete();
                        return bits[2];
                    }
                }
                decrypted_file.delete();
            } else {
                return "missing";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "missing";
    }

    /* list files */
    public void list(PrintWriter pw) {
        File encrypted_file = new File(fileTable);
        File decrypted_file = new File("_" + fileTable);
        String file;
        try {

            // decrypt file
            if (encrypted_file.exists()) {
                decryptAndWrite(encrypted_file, decrypted_file);
                BufferedReader in = new BufferedReader(new FileReader(decrypted_file));
                while ((file = in.readLine()) != null) {
                    pw.println(file);
                    pw.flush();
                }
                in.close();
            }

            pw.println("eof");
            pw.flush();
            decrypted_file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* insert a file in encrypted table */
    public void insertFileEntry(String msg) {
        File encrypted_file = new File(fileTable);
        File decrypted_file = new File("_" + fileTable);
        try {

            // decrypt file
            if (encrypted_file.exists()) {
                decryptAndWrite(encrypted_file, decrypted_file);
            }

            // insert entry
            FileWriter writer = new FileWriter(decrypted_file, true);
            writer.write(msg + "\n", 0, msg.length() + 1);
            writer.flush();
            writer.close();

            //encrypt file
            encryptAndWrite(decrypted_file, encrypted_file); 
            decrypted_file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* encrypt file */
    public void encryptAndWrite(File input, File output) {
        int bytes_read = -1;
        try {
            FileInputStream in = new FileInputStream(input);
            OutputStream out = new FileOutputStream(output);


            OutputStream cos = new CipherOutputStream(out, eCipher);
            byte [] data = new byte[1024];

            while ((bytes_read = in.read(data)) >= 0) {
                cos.write(data, 0, bytes_read);
                cos.flush();
            }
            cos.close();
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(bytes_read);
        }
    }


    /* decrypt file */
    public void decryptAndWrite(File input, File output) {
        try {
            FileInputStream in = new FileInputStream(input);
            FileOutputStream out = new FileOutputStream(output);

            CipherInputStream cis = new CipherInputStream(in, dCipher);

            int bytes_read;
            byte [] data = new byte[1024];

            while ((bytes_read = cis.read(data)) >= 0) {
                out.write(data, 0, bytes_read);
                out.flush();
            }
            in.close();
            out.close();
            cis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * Metoda run ... accepta conexiuni si initiaza noi threaduri pentru fiecare conexiune in parte
     */
    public void run() {
        while (hasToRun) {
            try {
                Socket s = ss.accept();								
                s.setTcpNoDelay(true);	
                //add the client connection to connection pool
                pool.execute(new ClientThread(s));
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    /**
     * Metoda poate fi folosita pentru oprirea serverului
     */
    public void stop() {
        hasToRun = false;
        try {
            ss.close();

            as_out.println("exit");
            as_out.close();
            as_in.close();
            authorization_socket.close();
        } catch (Exception ex) {}
        ss = null;
    }

    /**
     * Custom thread factory used in connection pool
     */
    private final class DaemonThreadFactory implements ThreadFactory {
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        }
    }

    /**
     * Clasa ce implementeaza functionalitatea conexiunii cu un anumit client
     *
     */
    private final class ClientThread implements Runnable {

        private BufferedReader br;
        private PrintWriter pw;
        private Socket s;
        private X509Certificate client_cert;
        private String CN;
        private String OU;

        public ClientThread(Socket s) {
            try {
                pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                this.s = s;
                client_cert = ((X509Certificate[])(((SSLSocket)s).getSession().getPeerCertificates()))[0];
                String DN = client_cert.getIssuerX500Principal().getName();
                String[] bits = DN.split(",");
                CN = bits[0].substring(3);
                OU = bits[1].substring(3);
                System.out.println(CN + " logged in!");
            } catch (Exception e) { }
        }

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
            if (CN != null) {
                System.out.println(CN + " logged out!");
            }
        }

        public void run() {
            // run indefinetely until exception

            while (true) {
                try {
                    String msg = br.readLine();
                    if (msg.startsWith("upload")) {
                        upload(msg);
                    } else if (msg.startsWith("download")) {
                        download(msg);
                    } else if (msg.startsWith("list")) {
                        list(pw);
                    }
                } catch (Exception e) {
                    break;
                }
            }
            close();
        }


        /* send file to client */
        public void download(String msg) {
            String filename = msg.substring(9);
            String ownerDept = getOwnerDept(filename);

            if (ownerDept.equals("missing")) {
                pw.println("missing");
                pw.flush();
                return;
            }

            if (getRight("get_right " + ownerDept + " " + OU + " " + CN)) {
                pw.println("true");
                pw.flush();
            } else {
                pw.println("false");
                pw.flush();
                return;
            }
            System.out.println("Send " + filename);
            File file = new File("files/" + filename);
            try {
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

        /* receive file from client */
        public void upload(String msg) {
            String filename = msg.substring(7);
            if (filename.contains("bomba") || filename.contains("greva")) {
                banClient(CN);
                pw.println("false");
                pw.flush();
                return;
            }

            System.out.println("Uploading");
            File files = new File("files");
            if (!files.exists()) {
                files.mkdir();
            }
            File file = new File("files/" + filename);
            try {

                pw.println("true");
                pw.flush();
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
            System.out.println("File " + filename + " uploaded successfully");
            insertFileEntry(filename + " " + CN + " " + OU);
        }
    }

    public static void main(String args[]) {
        if (args == null || args.length < 1) {
            System.out.println("Nu a fost furnizat ca argument portul");
            return;
        }
        try {
            int port = Integer.parseInt(args[0]);
            (new Thread(new Server(port))).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
