package encryption;

import java.nio.*;
/**
 * Implementeaza cifrul monoalfabetic Caesar.
 *
 * Este supus contractului MonoCypher privind setul de caractere suportate.
 * Spatiul (' ') poate aparea ca si caracter in urma criptarii, fara a mai fi,
 * in textul criptat, separator intre cuvinte. Cu alte cuvinte, translarea
 * se poate face inclusiv peste caracterul ' ' al alfabetului, chiar daca,
 * intr-un text clar, acesta este separator intre cuvinte.
 */
public class Caesar extends MonoCypher {
    /**
     * Creeaza un cifru care opereaza translatii de <code>shift</code> caractere
     *
     * @param shift Numarul de pozitii cu care se transleaza (la dreapta, pentru
     * criptare si la stanga, pentru decriptare) caracterele individuale ale textului.
     */
    public Caesar(int shift) {
        super("Caesar", new CaesarKey(shift));
    } // Caesar()
    public String encrypt(String plainText) {
        // textul criptat va avea aceeasi lungime ca si textul clar
        CharBuffer buf = CharBuffer.allocate(plainText.length());
        for (int idx = 0; idx < plainText.length(); idx++) {
            char plainChar = plainText.charAt(idx);
            char cryptoChar = encrypt(plainChar);
            buf.append(cryptoChar);
        }
        buf.flip();
        return buf.toString();
    } // encrypt()
    char encrypt(char plainChar) {
        int mod = CharSet.length();
        int plainCharIdx = CharSet.indexOf(plainChar);
        if (plainCharIdx == -1) {
            throw new IllegalArgumentException("Illegal character: " + plainChar);
        }
        int cryptoCharIdx = (plainCharIdx + ((CaesarKey)this.key).shift) % mod;
        char cryptoChar = CharSet.charAt(cryptoCharIdx);
        return cryptoChar;
    } // encrypt()
    public String decrypt(String cryptoText) {
        // textul clar va avea aceeasi lungime ca si textul criptat
        CharBuffer buf = CharBuffer.allocate(cryptoText.length());
        for (int idx = 0; idx < cryptoText.length(); idx++) {
            char cryptoChar = cryptoText.charAt(idx);
            char plainChar = decrypt(cryptoChar);
            buf.append(plainChar);
        }
        buf.flip();
        return buf.toString();
    } // decrypt()
    char decrypt(char cryptoChar) {
        int mod = CharSet.length();
        int cryptoCharIdx = CharSet.indexOf(cryptoChar);
        if (cryptoCharIdx == -1) {
            throw new IllegalArgumentException("Illegal character: " + cryptoChar);
        }
        int plainCharIdx = (cryptoCharIdx + CharSet.length() -
                ((CaesarKey)this.key).shift) % mod;
        char plainChar = CharSet.charAt(plainCharIdx);
        return plainChar;
    } // decrypt()
} // class Caesar
