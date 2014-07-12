package encryption;

import java.nio.*;
/**
* Defineste setul de caractere suportat de clasele de criptare implementate
*/
public class CharSet {
protected static CharBuffer chars = CharBuffer.allocate(53);
static {
for (char c = '\u0041'; c <= '\u005a'; c++) {
chars.put(c);
}
for (char c = '\u0061'; c <= '\u007a'; c++) {
chars.put(c);
}
chars.put('\u0020');
chars.flip();
} // static
/**
* Intoarce numarul de caractere (inclusiv spatiul - <code>' '</code>)
* din setul curent.
*/public static int length() {
return chars.capacity();
} // length()
/**
* Intoarce indexul caracterului dat in setul de caractere definit
* de CharSet.
*
* @param c Caracterul a carui pozitie in CharSet se cauta
*
* @return <ul>
* <li>Un index nenegativ, daca <code>c</code> este in CharSet
* <li>-1, daca <code>c</code> nu este in CharSet
* </ul>
*/
public static int indexOf(char c) {
if ((c >= '\u0041') && (c <= '\u005a')) {
return c - 0x0041;
}
if ((c >= '\u0061') && (c <= '\u007a')) {
return (0x005a - 0x0041) + (c - 0x0061) + 1;
}
if (c == '\u0020') {
return (0x005a - 0x0041) + (0x007a - 0x0061) + 2;
}
return -1;
} // indexOf()
/**
* Intoarce caracterul de pe pozitia indicata.
*
* @param index Un index intre 0 (inclusiv) si CharSet.length() (exclusiv)
*
* @return Caracterul de pe pozitia indicata din CharSet
*
* @throws IndexOutOfBoundsException Daca <code>index</code> este mai mic
* decat 0 sau mai mare sau egal cu CharSet.length().
*/
public static char charAt(int index) {
return chars.get(index);
} // charAt()
} // class CharSet
