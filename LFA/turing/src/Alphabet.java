import java.util.ArrayList;


public class Alphabet {

	ArrayList<Character> elements;
	public Alphabet () {
		elements = new ArrayList<Character>();
	}
	public void add(Character a) {
		elements.add(a);
	}
	
	@Override
	public String toString() {
		return elements.toString();
	}
	
	public boolean contains(Character c) {
		return elements.contains(c);
	}
	
	public ArrayList<Character> getElements() {
		return elements;
	}
}
