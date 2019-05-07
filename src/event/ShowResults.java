package event;
import colony.Colony;
import graph.Graph;
import pec.PEC;

/**
 * The ShowResults class is a subclass of the abstract class Event, from which it inherits
 * the fields time, graph and pec.
 * 
 * The ShowResults class implements the method simulateEvent() in which
 * the results are shown to the user through a print. The print shows the present instant, i.e., the
 * time at which the print is being shown, the number of move and evaporation events simulated so far in the
 * execution of the stochastic simulation
 * and it also displays the hamiltonian cycle found so far, if it exists.
 * It simulates 20 times during one run of the simulation, i.e., 20 prints are shown to the user from the start to
 * the end of the simulation.
 * 
 * @authors Filipa, Goncalo, Joana.
 *
 */
public class ShowResults extends Event{	
	/**
	 * static field finalinst defines the final instant when the stochastic simulation must stop running.
	 * static field observation represents the number of the print observation (1 up to 20).
	 * static field mevents specifies the number of move events, whereas eevents specifies the number of evaporation events.
	 */
	static double finalinst;
	static int observation = 0, mevents = 0, eevents = 0;
	
	/**
	 * Association with the Colony of the stochastic simulation.
	 */
	Colony colony;
	
	/**
	 * Constructor for the ShowResults class.
	 * 
	 * @param time time of the event, in this case, the print observation.
	 * @param graph graph of the simulation. 
	 * @param pec pec of the simulation.
	 * @param colony associated colony.
	 * @param finalinst final instant when the simulation must stop.
	 */
	public ShowResults(double time, Graph graph, PEC pec, Colony colony, double finalinst) {
		super(time, graph, pec);
		this.colony = colony;
		ShowResults.finalinst = finalinst;
	}

	/**
	 * Method that simulates one observation of showResults.
	 * 
	 * Firstly, counter of observations is incremented.
	 * The print is executed.
	 * Finally, the next ShowResults event is scheduled in a deterministic way, i.e., it will occur finalinst/20 instants from
	 * the current time, so that in the total runtime 20 observations are simulated.
	 */
	@Override
	public void SimulateEvent() {
		ShowResults.observation += 1;
		
		System.out.println(this.toString());
		
		//simulate next ShowResults event
		pec.addEvPEC(new ShowResults(time+finalinst/20, graph, pec, colony, finalinst));
		
	}

	@Override
	public String toString() {
		String my_str ="";
		
		my_str += "Observation " + observation + ":\n\t\t Present instant: " + time + "\n\t\t Number of move events: " + mevents + "\n\t\t Number of evaporation events: " + eevents + "\n\t\t Hamiltonian cycle: ";
		
		if(!graph.getShortest_path().isEmpty()) {
			my_str += "{";
			for (graph.Node node: graph.getShortest_path()) {
				my_str += node.getIndex() + ",";
			}
			
			//erase the last comma
			my_str = (String) my_str.subSequence(0, my_str.length()-1);
			my_str +="}";
			
		}
		
		return my_str;
	
	
	}
	
	
}