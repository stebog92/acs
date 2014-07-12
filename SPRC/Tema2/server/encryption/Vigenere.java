package encryption;

import java.util.*;
/**
 * Implementeaza cifrul monoalfabetic Vigenere.
 *
 * Cifrul Vigenere este bazat pe cifrul Caesar (foloseste cate un cifru Caesar pentru
 * fiecare caracter din cheia de criptare). Pentru simplitate, am preferat o
 * implementare ca atare a cifrului, desi suboptimala.
 */
public class Vigenere extends MonoCypher {
    /**
     * Lista de cifruri Caesar care vor fi folosite efectiv in (de)criptare.
     * Practic, se va instantia cate un cifru Caesar pentru fiecare
     * caracter din cheia de criptare.
     */
    List<Caesar> caesars;
    /**
     * @param key Cheia cifrului trebuie sa contina doar caractere din setul
     * {@link CharSet}
     *
     * @throws IllegalArgumentException Daca <code>key</code> contine caractere
     * care nu sunt in {@link CharSet}.
     */
    public Vigenere(String key) {
        super("vigenere" + "/" + key.length(), new VigenereKey(key));
        // creez doar cifrurile Caesar corespunzatoare caracterelor din cheie
        this.caesars = new ArrayList<Caesar>(key.length());
        for (int idx = 0; idx < key.length(); idx++) {
            // de exemplu, daca(,) caracterul curent din cheie este 'b',
            // vom folosi cifrul Caesar cu translatie de CharSet.indexOf('b') caractere
            char keyChar = key.charAt(idx);
            int keyCharIdx = CharSet.indexOf(keyChar);
            if (keyCharIdx == -1) {
                throw new IllegalArgumentException("Illegal character in key: "+ keyChar);
            }
            this.caesars.add(idx, new Caesar(keyCharIdx));
        } // for()
    } // Vigenere()
    public String encrypt(String plainText) {
        StringBuilder sb = new StringBuilder();
        int keyLen = ((VigenereKey)(this.key)).key.length();
        // aplic cheia succesiv peste blocuri de text de lungime keyLen
        for (int leftBound = 0, rightBound = keyLen; leftBound < plainText.length();
                leftBound += keyLen, rightBound += keyLen) {
            for (int idx = 0; (idx < keyLen) && (leftBound +
                        idx < plainText.length()); idx++) {
                char plainChar = plainText.charAt(leftBound + idx);
                char cryptoChar = this.caesars.get(idx).encrypt(plainChar);
                sb.append(cryptoChar);
                        }
                } // for()
        return sb.toString();
    } // encrypt()
    public String decrypt(String cryptoText) {
        StringBuilder sb = new StringBuilder();
        int keyLen = ((VigenereKey)this.key).key.length();
        // aplic cheia succesiv peste blocuri de text de lungime keyLen
        for (int leftBound = 0, rightBound = keyLen;
                leftBound < cryptoText.length();
                leftBound += keyLen, rightBound += keyLen) {
            for (int idx = 0; (idx < keyLen) && (leftBound
                        + idx < cryptoText.length()); idx++) {
                char cryptoChar = cryptoText.charAt(leftBound + idx);
                char plainChar = this.caesars.get(idx).decrypt(cryptoChar);
                sb.append(plainChar);
                        }
                } // for()
        return sb.toString();
    } // decrypt()
} // class Vigenere/**


class VigenereKey implements MonoKey {
    public final String key;
    VigenereKey(String key) {
        this.key = key;
    } // VigenereKey()
    public int compareTo(MonoKey obj) {
        return this.key.compareTo(((VigenereKey)obj).key);
    } // compareTo()

    public String getKey() {
        return this.key;
    }
} // class VigenereKey
