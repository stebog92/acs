import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Turing {

	public static int position;
	public static StringBuilder bus;
	public static HashMap<String, ITuring> turingMachines;
	public static TuringMachine turingMachineInConstruction;
	public static HashMap<String, String> variables;

	public static void main(String[] args) {
		File file = new File(args[0]);
		bus = new StringBuilder(args[2]);
		bus.insert(0,
				"######################################################################");
		for (int i = 0; i < 10; i++) {
			bus.append("##");
		}

		position = bus.indexOf(">");
		bus.deleteCharAt(position);
		variables = new HashMap<String, String>();
		turingMachines = new HashMap<String, ITuring>();
		FileReader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TuringLex scann = new TuringLex(reader);
		try {
			scann.yylex();
		} catch (IOException e) {
		}
		turingMachines.get(args[1]).execute();
		bus.insert(position, '>');
		for (int i = bus.length() - 1; i > 1 && bus.charAt(i - 1) == '#'; i--) {
			bus.deleteCharAt(i);
		}
		while (bus.charAt(1) == '#') {
			bus.deleteCharAt(0);
		}
		System.out.println(bus);
	}

}
