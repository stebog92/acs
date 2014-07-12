package interfaces;

import encryption.*;
import java.util.ArrayList;

public class Task implements java.io.Serializable {

    private String encryptedText;
    private MonoCypher cypher;
    private ArrayList<MonoKey> keys;

    public Task (String encryptedText, MonoCypher cypher, ArrayList<MonoKey> keys) {
        this.encryptedText = encryptedText;
        this.cypher = cypher;
        this.keys = keys;
    }

    public MonoCypher getCypher() {
        return this.cypher;
    }

    public ArrayList<MonoKey> getKeys() {
        return this.keys;
    }

    public String getEncryptedText() {
        return this.encryptedText;
    }


}
