package encryption;
/**
* Incapsuleaza detaliile de implementare ale unui cifru monoalfabetic.
* De exemplu, in cazul unui cifru Caesar trebuie specificat numarul de pozitii
* cu care se face translatarea. Similar, in cazul unui cifru Vigenere, trebuie
* specificata exact cheia de criptare.
*
* Extinde {@link Comparable} pentru a putea specifica, folosind doua MonoKey,
* o plaja de valori pe care o poate lua o cheie.
*/
public interface MonoKey extends java.io.Serializable, Comparable<MonoKey> {
    public String getKey();
} // interface MonoKey
