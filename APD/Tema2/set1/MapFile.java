import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;

public class MapFile implements Callable<HashMap<String, Integer>> {

	private long begin;
	private int size;
	private File file;
	private HashMap<String, Integer> freq;

	public MapFile(File file, long begin, int size) {
		this.file = file;
		this.begin = begin;
		this.size = size;
		this.freq = new HashMap<String, Integer>();
	}

	@Override
	public HashMap<String, Integer> call() {
		try {
			FileInputStream fstream = new FileInputStream(file);
			// citirea a size caractere incepand de la begin
			if (begin > 0) {
				fstream.skip(begin - 1);
			}
			byte bytes[] = new byte[size + 20];
			fstream.read(bytes, 0, size + 1);
			byte c;
			int len = 1;

			// daca urmatorul caracter este un caracter alfabetic se citesc
			// caractere pana la terminarea cuvantului
			if ((bytes[size] >= 'a' && bytes[size] <= 'z')
					|| (bytes[size] >= 'A' && bytes[size] <= 'Z')) {
				while (Character.isAlphabetic((c = (byte) fstream.read()))) {
					bytes[size + (len++)] = c;
				}
			}
			fstream.close();
			
			StringTokenizer st = new StringTokenizer(new String(bytes),
					" ,-.'\n\t\r\f\0!?:()1234567890\"[]{};*/+=_&^%$#@");
			
			//daca caracterul citit inainte de begin este alfabetic, cuvantul este ignorat
			if (begin != 0 && Character.isAlphabetic(bytes[0])) {
				st.nextToken();
			}
			
			//contorizarea cuvintelor
			while (st.hasMoreTokens()) {
				String word = st.nextToken().toLowerCase();
				if (word.equals("block")) {
					word.toLowerCase();
				}
				if (freq.containsKey(word)) {
					freq.put(word, freq.get(word) + 1);
				} else {
					freq.put(word, 1);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return freq;
	}

}
