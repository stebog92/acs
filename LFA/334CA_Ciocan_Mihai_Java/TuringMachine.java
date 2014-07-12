import java.util.HashMap;
import java.util.LinkedList;


public class TuringMachine implements ITuring {

	public String name;
	State initialState;
	LinkedList<State> states;
	public HashMap<String, State> variables;
	public HashMap<String, Integer> variable_position;
	public TuringMachine(String name) {
		this.name = name;
		initialState = new State(this);
		states = new LinkedList<State>();
		states.addFirst(initialState);
		variables = new HashMap<String, State>();
		variable_position = new HashMap<String, Integer>();
	}
	@Override
	public void execute() {
		State state = initialState;
		state.executeTM();
		while ((state = state.nextState()) != null) {
			state.executeTM();
		}
	}
	public void pushState(State state) {
		states.addFirst(state);
	}
	
	public void popState() {
		states.removeFirst();
	}
	@Override
	public State getLastState() {
		return states.peekFirst();
	}
	
	@Override
	public String toString() {
		return name;//+ "\n" + variables.toString();
	}
	
	public void declareVar(String s) {
		variables.put(s, getLastState());
		variable_position.put(s, getLastState().turingMachines.size());
	}
	
	public String getName() {
		return name;
	}
}
