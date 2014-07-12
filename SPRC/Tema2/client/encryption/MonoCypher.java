package encryption;
/**
 * Clasa de baza a cifrurilor monoalfabetice suportate de server.
 * Suporta un set redus de caractere care compun textul clar: [A-Za-z ]
 * (alfabetul latin si spatiu), ordonate exact cum au fost enumerate.
 *
 * Metodele de criptare si decriptare sunt distincte, pentru ca, desi din
 * familia metodelor de criptare simetrice, este posibil ca, in practica,
 * un cifru sa aiba nevoie de mici modificari inainte de a efectua decriptarea.
 */
public abstract class MonoCypher implements java.io.Serializable {
    /**
     * Numele unic al cifrului
     */
    protected final String name;
    /**
     * Cheia care incapsuleaza detaliile cifrului.
     */
    protected MonoKey key;
    protected MonoCypher(String name, MonoKey key) {
        this.name = name;
        this.key = key;
    } // MonoCypher()
    /**
     * Intoarce numele (unic) al cifrului*/
    public String getName() {
        return name;
    } // getName()
    /**
     * Configureaza o noua cheie pentru cifrul curent.
     *
     * @param key Noua cheie de (de)criptare. Validarea acestui argument
     * (ex.: tipul sau la run-time) se face de catre subclasele concrete.
     */
    public void setKey(MonoKey key) {
        this.key = key;
    } // setKey()
    /**
     * Cripteaza textul clar, folosind detaliile din cheia de criptare.
     *
     * @param plainText Textul clar ce urmeaza a fi criptat
     *
     * @return Textul criptat cu {@link MonoCypher#key cheia} continuta
     *
     * @throws IllegalArgumentException Daca <code>plainText</code> contine
     * caractere nesuportate de CharSet
     */
    public abstract String encrypt(String plainText);
    /**
     * Decripteaza textul criptat, folosind detaliile din cheia de criptare.
     *
     * @param cryptoText Textul criptat (obtinut folosind exact
     * cheia de criptare curenta)
     *
     * @return Textul clar din care a fost obtinut textul dat ca argument
     *
     * @throws IllegalArgumentException Daca <code>cryptoText</code> contine
     * caractere nesuportate de CharSet
     */
    public abstract String decrypt(String cryptoText);
    /**
     * @return <code>true</code> daca si numai daca numele cifrului curent
     * si cel al cifrului dat ca argument sunt egale.
     */
    public boolean equals(Object anotherObject) {
        if ((anotherObject == null) || !(anotherObject instanceof MonoCypher)) {return false;
        }
        return this.name.equals(((MonoCypher)anotherObject).name);
    } // equals()
    public int hashCode() {
        return (this.name.hashCode() | this.key.hashCode());
    } // hashCode()
} // interface MonoCypher
