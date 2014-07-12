package encryption;

public class Dictionary implements java.io.Serializable {

    private String[] words;
    public Dictionary(String[] words) {
        this.words = words;
    }

    public boolean contains(String word) {
        for (int i = 0; i < this.words.length; i++) {
            if (word.equals(this.words[i])) {
                return true;
            }
        }
        return false;
    }

}
