import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.omg.CORBA.FREE_MEM;

public class ReduceFile implements Callable<HashMap<String, Double>> {

	ArrayList<Future<HashMap<String, Integer>>> partial_results;
	HashMap<String, Integer> final_result;
	ArrayList<Map.Entry<String, Integer>> first_n;
	int N;
	int total;

	public ReduceFile(ArrayList<Future<HashMap<String, Integer>>> pr, int N) {
		this.partial_results = pr;
		final_result = new HashMap<String, Integer>();
		this.N = N;
		this.total = 0;
	}

	@Override
	public HashMap<String, Double> call() {
		// se realizeaza rezultatele finale prin reuniunea rezultatelor partiale
		for (Future<HashMap<String, Integer>> result : partial_results) {
			try {
				HashMap<String, Integer> res = result.get();
				for (Map.Entry<String, Integer> word : res.entrySet()) {
					this.total += word.getValue();
					if (final_result.containsKey(word.getKey())) {
						final_result.put(
								word.getKey(),
								Integer.valueOf(final_result.get(word.getKey())
										+ word.getValue()));
					} else {
						final_result.put(word.getKey(), word.getValue());
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		first_n = new ArrayList<Map.Entry<String, Integer>>(
				final_result.entrySet());
		//sortare in ordine descrescatoare a nr de aparitii
		Collections.sort(first_n,
				new Comparator<Map.Entry<String, Integer>>() {

					@Override
					public int compare(Entry<String, Integer> o1,
							Entry<String, Integer> o2) {

						return o2.getValue() - o1.getValue();
					}
				});
		// calcularea frecventelor si retinerea primelor N
		HashMap<String, Double> word_freq = new HashMap<String, Double>();
		for (Map.Entry<String, Integer> word : first_n) {
			word_freq.put(word.getKey(),
					(Double.valueOf(word.getValue()) / Double.valueOf(total)) * 100.0);
			N--;
			if (N == 0)
				break;
		}
		return word_freq;
	}

}
