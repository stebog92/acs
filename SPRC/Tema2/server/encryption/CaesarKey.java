package encryption;

public class CaesarKey implements MonoKey {
    public final int shift;
    public CaesarKey(int shift) {
        this.shift = shift;
    } // CaesarKey()
    public int compareTo(MonoKey key) {
        return this.shift - ((CaesarKey)key).shift;
    } // compareTo()

    public String getKey() {
        return new String("" + shift);
    }
} // class CaesarKey
