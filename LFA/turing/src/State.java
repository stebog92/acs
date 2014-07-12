import java.util.ArrayList;
import java.util.HashMap;

public class State {
	ArrayList<ITuring> turingMachines;
	HashMap<String, State> transitions;
	HashMap<Character, String> charToString;
	public Integer begin_execute = 0;
	TuringMachine mt;
	public String var = "";
	
	public String loop_name = "";

	public State(TuringMachine mt) {
		turingMachines = new ArrayList<ITuring>();
		transitions = new HashMap<String, State>();
		charToString = new HashMap<Character, String>();
		this.mt = mt;
	}

	public void addTransition(String c, State state) {
		transitions.put(c, state);
	}
	
	public void mapCharToTransition (Character c, String s) {
		charToString.put(c, s);
	}

	public void executeTM() {
		/*try {
			Turing.out.write(mt.name + "\n");
		} catch (IOException e) {
			
			e.printStackTrace();
		}*/
		for (int i = begin_execute; i < turingMachines.size(); i++) {
			turingMachines.get(i).execute();
			/*try {
				Turing.out.write (turingMachines.get(i) + " " + Turing.bus.toString() + " " + Turing.position + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
		begin_execute = 0;
	}

	public void addTuringMachine(ITuring m) {
		turingMachines.add(m);
	}

	public State nextState() {
		if (loop_name.length() > 0) {
			if (mt.variable_position.containsKey(loop_name)) {
				mt.variables.get(loop_name).begin_execute = mt.variable_position
						.get(loop_name);
				return mt.variables.get(loop_name);
			}
		}
		if (!var.isEmpty()) {
			Turing.variables.put(var, Turing.bus.charAt(Turing.position) + "");
		}
		return transitions.get(charToString.get(Turing.bus.charAt(Turing.position)));
	}

	public String toString() {
		return "Machines : " + turingMachines.toString();
	}
}
