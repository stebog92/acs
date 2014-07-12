import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

	static int NT;
	static int NC;
	static int D;
	static int N;
	static int X;
	static int ND;
	static ArrayList<String> keywords;
	static ArrayList<String> files;
	private static File output;
	private static HashMap<String, Future<HashMap<String, Double>>> file_results;

	public static void main(String[] args) {
		NT = Integer.parseInt(args[0]);
		ExecutorService map_pool = Executors.newFixedThreadPool(NT);
		ExecutorService reduce_pool = Executors.newFixedThreadPool(NT);
		output = new File(args[2]);

		file_results = new HashMap<String, Future<HashMap<String, Double>>>();
		// citirea datelor de intrare
		try {
			FileInputStream fstream = new FileInputStream(args[1]);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			NC = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			keywords = new ArrayList<String>();
			while (st.hasMoreTokens()) {
				keywords.add(st.nextToken());
			}
			D = Integer.parseInt(br.readLine());
			N = Integer.parseInt(br.readLine());
			X = Integer.parseInt(br.readLine());
			ND = Integer.parseInt(br.readLine());
			files = new ArrayList<String>();
			for (int i = 0; i < ND; i++) {
				files.add(br.readLine());
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (String file_name : files) {

			File file = new File(file_name);
			// lista de rezultate partiale ale fiecarui fisier
			ArrayList<Future<HashMap<String, Integer>>> partial_results = new ArrayList<Future<HashMap<String, Integer>>>();

			// calculare rezultate partiale cu ajutorul MapFile pentru o
			// secventa de marime D
			for (long begin = 0; begin < file.length(); begin += D) {
				partial_results.add(map_pool
						.submit((new MapFile(file, begin, D))));
			}
			// operatie de reduce a rezultatelor partiale cu ajutorul ReduceFile
			file_results.put(file_name,
					reduce_pool.submit(new ReduceFile(partial_results, N)));
		}
		HashMap<String, Future<Boolean>> documents = new HashMap<String, Future<Boolean>>();
		for (Map.Entry<String, Future<HashMap<String, Double>>> words : file_results
				.entrySet()) {
			// System.out.println(words.getKey());
			try {
				HashMap<String, Double> res = words.getValue().get();
				// System.out.println(res.toString());

				// pentru fiecare fisier se verifica daca exista cuvintele
				// cautate
				// documents este un hashmap ce retine numele fisierului si o
				// variabila , true sau false care arata daca fisierul contine
				// sau nu cuvintele cautate
				documents.put(words.getKey(),
						reduce_pool.submit(new FindWords(keywords, res)));
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		reduce_pool.shutdown();
		map_pool.shutdown();

		// printarea rezultatelor in fisier
		PrintWriter out = null;
		try {
			out = new PrintWriter(output);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		out.print("Rezultate pentru: (");
		for (int i = 0; i < keywords.size(); i++) {
			out.print(keywords.get(i));
			if (i != keywords.size() - 1) {
				out.print(", ");
			}
		}
		out.println(")");
		out.println();

		for (String file : files) {
			try {
				if (documents.get(file).get().equals(Boolean.TRUE)) {
					out.print(file);
					out.print(" (");
					int s = 0;
					for (String word : keywords) {
						out.printf("%.2f",
								file_results.get(file).get().get(word));
						s++;
						if (s != keywords.size()) {
							out.print(", ");
						}
					}
					out.println(")");
					Main.X--;
					if (Main.X == 0) {
						break;
					}
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		out.close();
	}
}