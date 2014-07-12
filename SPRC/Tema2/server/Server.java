import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import encryption.*;
import interfaces.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Comparator;


public class Server implements IServer {


    public static String text = "I love the smell of napalm in the morning";
    public static String encryptedText;
    public String[] dict = {"analyse", "analysis", "analyst", "analytic",
        "analytical",  "analytically", "analyze", "approach","approachable", "area", "assess", "assessable", "assessment", "assume", "assumed",
        "assuming", "assumption", "authoritative", "authoritatively", "authority", "availability", "available", "beneficial", "beneficiary",
        "benefit", "blinker", "concept", "conception", "conceptual", "conceptualize", "conceptually", "consist", "consistency", "consistent",
        "consistently", "constituency", "constituent", "constitute", "constitution", "constitutional", "constitutionally", "constitutive",
        "context", "contextual", "contextualization", "contextualize", "contextually", "contract", "contractor", "create", "creation", "creative",
        "creatively", "creativity", "creator", "data", "definable", "define", "definition", "derivation", "derivative", "derive", "disestablish",
        "disestablishment", "dissimilar", "dissimilarity", "distribute", "distribution", "distributional", "distributive", "distributor",
        "economic", "economical", "economically", "economics", "economist", "economy", "environment", "environmental", "environmentalism",
        "environmentalist", "environmentally", "establish", "established", "establishment", "estimate", "estimation", "evidence", "evident",
        "evidential", "evidently", "export", "exporter", "factor", "finance", "financial", "financially", "financier", "financing", "formula",
        "formula milk", "formulate", "formulation","full stop","function","functional","functionally", "I", "ID", "identifiable", "identification",
        "identify", "identity", "illegal", "illegality", "illegally", "in","income", "inconsistency", "inconsistent", "inconsistently", "indicate",
        "indication", "indicative", "indicator", "indiscreet", "indiscreetly", "individual", "individualism", "individualist", "individualistic",
        "individuality", "individually", "insignificance", "insignificant", "insignificantly", "interpret", "interpretable", "interpretation",
        "interpretative", "interpretive", "invariable", "invariably", "involve", "involved", "involvement", "isolating", "issue", "issuer",
        "love", "labor", "labour", "legal", "legality", "legally", "legislate", "legislation", "legislative", "legislator", "legislature", "major",
        "morning", "majority", "method", "methodical", "methodically", "methodological", "methodologically", "methodology", "misinterpret",
        "misinterpretation", "napalm", "occur", "occurrence", "of", "overestimate", "overestimation", "percentage", "period", "periodic",
        "periodical", "periodically", "policy", "principle", "principled", "procedural", "procedure", "proceed", "proceeding", "proceeds",
        "process",
        "processing", "reassess", "reassessment", "recreate", "recreation", "redefine", "redefinition", "redistribute", "redistribution",
        "redistributive", "reformulate", "reformulation", "reinterpret", "reinterpretation", "reoccur", "require", "requirement",
        "research", "researcher", "researches", "respond", "respondent", "response", "responsive", "responsively", "responsiveness", "restructure",
        "restructuring", "role", "section", "sector", "significance", "significant", "significantly", "signify", "similar", "similarity",
        "similarly", "source", "smell", "specific", "specifically", "specification", "specificity", "specifics", "structural", "structurally",
        "structure", "the","theoretical", "theoretically", "theoretician", "theorist", "theory", "turn signal", "unapproachable", "unavailability",
        "unavailable", "unconstitutional", "unconstitutionally", "undefined", "underestimate", "underestimation", "uneconomic", "uneconomical",
        "unidentifiable", "uninvolved", "unprincipled", "unresponsive", "unstructured", "variability", "variable", "variably", "variance",
        "variant", "variation", "varied", "vary"};
    public static Caesar caesar;
    public static Registry registry;
    public Dictionary dictionary;
    public ArrayList<MonoKey> caesarKeys;
    public boolean foundDecryption;
    public int beginKeys, endKeys;
    public  ArrayList<Map.Entry<MonoKey, Double>> decryptions;
    public HashMap<String, Map.Entry<Integer, Integer>> jobs;
    public ArrayList<Map.Entry<Integer, Integer>> interval;
    int jobsTaken = 0, allJobs = 5;
    public Server() {
        super();
        this.dictionary = new Dictionary(dict);
        this.foundDecryption = false;
        this.beginKeys = 0;
        this.endKeys = 5;
        decryptions = new ArrayList<Map.Entry<MonoKey, Double>>();
        this.caesarKeys = new ArrayList<MonoKey>();
        this.jobs = new HashMap<String, Map.Entry<Integer, Integer>>();
        this.interval = new ArrayList<Map.Entry<Integer, Integer>>();
        for (int i = 1; i < 26; i++) {
            this.caesarKeys.add(new CaesarKey(i));
        }

        for (int i = 0; i < 5; i++) {
            interval.add(new AbstractMap.SimpleEntry<Integer, Integer>(beginKeys, endKeys));
            beginKeys = endKeys;
            endKeys += 5;
        }

        /* check status of clients */
        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    try {
                        for (Map.Entry<String, Map.Entry<Integer, Integer>> client : jobs.entrySet()) {
                            try {
                                IClient c = (IClient) registry.lookup(client.getKey());
                                System.out.println("Checking " + client.getKey() + "still active!");
                                c.getCompletionStatus();
                            } catch (Exception e) {
                                System.out.println("Client " + client.getKey() + " is not active anymore!");
                                jobs.remove(client.getKey());
                                interval.add(client.getValue());
                            }
                        }
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        System.err.println("Checking exception:");
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    /* return a task for a client */
    public Task getTask(String id) {
        if (interval.isEmpty()) {
            return null;
        }

        beginKeys = interval.get(0).getKey();
        endKeys = interval.get(0).getValue();
        interval.remove(0);
        MonoCypher localCypher = new Caesar(0);
        Task task = new Task(encryptedText, localCypher, new ArrayList<MonoKey> (this.caesarKeys.subList(beginKeys, endKeys)));
        System.out.println("Sent task with keys between: " + beginKeys + " : " + endKeys + " to client " + id);
        jobs.put(id, new AbstractMap.SimpleEntry<Integer, Integer>(beginKeys, endKeys));


        return task;
    }

    /* receive result from clients */
    public void haveResult(Result r) {
        System.out.println("Received result!");
        Result result = r;
        System.out.println(r.getName());
        ArrayList<Double> p;
        p = r.getProbabilities();
        ArrayList<MonoKey> keys;
        keys = r.getKeys();

        jobs.remove(r.getClient());

        for (int i = 0; i < p.size(); i++) {
            System.out.println(p.get(i));
            decryptions.add(new AbstractMap.SimpleEntry<MonoKey, Double>(keys.get(i), p.get(i)));
            if (p.get(i) > 0.8) {
                stopAll();
                foundDecryption = true;
            }
        }
        if (foundDecryption || (jobs.isEmpty() && interval.isEmpty())) {
            printStats();
        }
    }

    /* print results */
    public void printStats() {

        System.out.println("All keys were used or key was found! Below are the results:");
        Collections.sort(decryptions, new Comparator<Map.Entry<MonoKey, Double>> () {
            public int compare(Map.Entry<MonoKey, Double> x, Map.Entry<MonoKey, Double> y) {
                return y.getValue().compareTo(x.getValue());
            }
        });

        int stop = 0;
        for (Map.Entry<MonoKey, Double> entry : decryptions) {
            System.out.println("Probability: " + entry.getValue() + " with key " + entry.getKey().getKey()); 
            stop++;
            if (stop == 3) {
                break;
            }
        }
    }

    /* stop all clients */ 
    public void stopAll() {
        for (Map.Entry<String, Map.Entry<Integer, Integer>> client : jobs.entrySet()) {
            try {
                IClient c = (IClient) registry.lookup(client.getKey());
                System.out.println("Stoping " + client.getKey());
                c.stop();
            } catch (Exception e) {
                System.out.println("Client " + client.getKey() + " is not active!");
                jobs.remove(client.getKey());
                interval.add(client.getValue());
            }
        }
    }

    public void test() {
        System.out.println("test");
    }

    /* return local dictionary */
    public Dictionary getDictionary() {
        return this.dictionary;
    }

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        caesar = new Caesar(7);

        encryptedText = caesar.encrypt(text);
        System.out.println("The text encryption is : " + encryptedText);

        try {
            IServer server = new Server();
            IServer stub =
                (IServer) UnicastRemoteObject.exportObject(server, 0);
            registry = LocateRegistry.getRegistry();
            registry.rebind("Server", stub);
            System.out.println("Server bound");
        } catch (Exception e) {
            System.err.println("Server exception:");
            e.printStackTrace();
        }
    }
}
