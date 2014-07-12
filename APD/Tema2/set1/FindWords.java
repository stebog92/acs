import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class FindWords implements Callable<Boolean> {

	private ArrayList<String> keywords;
	private HashMap<String, Double> words;

	public FindWords(ArrayList<String> keywords, HashMap<String, Double> words) {
		this.keywords = keywords;
		this.words = words;
	}

	//verifica daca toate cuv din keywords exista in lista words
	@Override
	public Boolean call() {
		int nFound = keywords.size();
		for (int i = 0; i < keywords.size(); i++) {
			if (words.containsKey(keywords.get(i))) {
				nFound--;
			}
		}
		if (nFound == 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

}
