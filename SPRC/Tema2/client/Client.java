import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Comparator;
import java.security.SecureRandom;
import java.math.BigInteger;

import interfaces.*;
import encryption.*;

public class Client implements IClient {

    private static Dictionary dictionary;
    private static IServer server;
    private static int completionStatus;
    private static String id;
    public Client () {
        super();
        completionStatus = 0;
        /* Creating client id */
        SecureRandom random = new SecureRandom();
        id = new BigInteger(130, random).toString(32);
        System.out.println(id);
    }

    /* return completion status */
    public int getCompletionStatus() {
        return completionStatus;
    }

    /* terminate client */
    public void stop() {
        System.out.println("Stopped by the server!");
        System.exit(0);
    }

    /* get task and process it */
    public static void process() {
        try {
            Task task = server.getTask(id);
            if (task == null) {
                return;
            }
            processTask(task);
        } catch (Exception e){
            System.err.println("Client exception");
            e.printStackTrace();
        }
    }

    public static void processTask(Task task) {
        String encryptedText = task.getEncryptedText();
        String decryptedText;
        String[] words;
        int correctWords;
        double probability;
        ArrayList<Map.Entry<MonoKey, Double>> decryptions = new ArrayList<Map.Entry<MonoKey, Double>>();
        ArrayList<MonoKey> bestKeys = new ArrayList<MonoKey>();
        ArrayList<Double> bestProbabilities = new ArrayList<Double>();

        MonoCypher cypher = task.getCypher();
        ArrayList<MonoKey> keys = task.getKeys();

        /* evaluate keys */
        for (MonoKey key : keys) {
            cypher.setKey(key);
            decryptedText = cypher.decrypt(encryptedText);
            System.out.print("Decrypted: " + decryptedText);
            words = decryptedText.split(" ");
            correctWords = 0;
            for (int i = 0; i < words.length; i++) {
                if (dictionary.contains(words[i])) {
                    correctWords++;
                }
            }
            /* calculate probabilities */
            probability = (double) correctWords / words.length;
            System.out.println(" with probability: " + probability);
            decryptions.add(new AbstractMap.SimpleEntry<MonoKey, Double>(key, probability));
        }

        /* sort results */
        Collections.sort(decryptions, new Comparator<Map.Entry<MonoKey, Double>> () {
            public int compare(Map.Entry<MonoKey, Double> x, Map.Entry<MonoKey, Double> y) {
                return y.getValue().compareTo(x.getValue());
            }
        });

        int stop = 0;
        for (Map.Entry<MonoKey, Double> entry : decryptions) {
            bestKeys.add(entry.getKey());
            bestProbabilities.add(entry.getValue());
            stop++;
            if (stop == 3) {
                break;
            }
        }
        try {
            /* simulate intensive cpu for allowing server to check completion status */
            Thread.sleep(4000);

            /* send results to server */
            server.haveResult(new Result(cypher.getName(), bestKeys, bestProbabilities, id));
            completionStatus = 1;
        } catch (Exception e) {
            System.err.println("Client exception:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }


        try{
            IClient client = new Client();
            IClient stub =
                (IClient) UnicastRemoteObject.exportObject(client, 0);
            Registry registry = LocateRegistry.getRegistry(args[0]);
            registry.rebind(id, stub);

            server = (IServer) registry.lookup("Server");
            /* get dictionary */
            dictionary = server.getDictionary();
            System.out.println("Got Dictionary!");

            process();

            registry.unbind(id);
            UnicastRemoteObject.unexportObject(client, false);

        } catch (Exception e) {
            System.err.println("Client exception:");
            e.printStackTrace();
        }
    }
}
