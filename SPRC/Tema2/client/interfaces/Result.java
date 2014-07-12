package interfaces;

import encryption.*;
import java.util.ArrayList;

public class Result implements java.io.Serializable {

    private String name;
    private ArrayList<MonoKey> keys;
    private ArrayList<Double> probabilities;
    private String client;

    public Result(String name, ArrayList<MonoKey> keys, ArrayList<Double> probabilities, String client) {
        this.name = name;
        this.keys = keys;
        this.probabilities = probabilities;
        this.client = client;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<MonoKey> getKeys() {
        return this.keys;
    }

    public ArrayList<Double> getProbabilities() {
        return this.probabilities;
    }
    
    public String getClient() {
        return this.client;
    }


}
