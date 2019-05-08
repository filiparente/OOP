package event;
import simulation.HamiltonianSimulation;

/**
 * The ShowResults class is a subclass of the abstract class DeterministicEvent, from which it inherits
 * the field time, and the hamiltonian simulation.
 * 
 * The ShowResults class implements the method simulateEvent() in which
 * the results are shown to the user through a print. The print shows the present instant, i.e., the
 * time at which the print is being shown, the number of move and evaporation events simulated so far in the
 * execution of the hamiltonian stochastic simulation
 * and it also displays the hamiltonian cycle found so far, if it exists.
 * It simulates 20 times during one run of the simulation, i.e., 20 prints are shown to the user from the start to
 * the end of the simulation.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
public class ShowResults extends DeterministicEvent{
	
	/**
	 * static field finalinst defines the final instant when the hamiltonian stochastic simulation must stop running.
	 * static field observation represents the number of the print observation (1 up to 20).
	 * static field mevents specifies the number of move events, whereas eevents specifies the number of evaporation events.
	 */
	private static double finalinst;
	private static int observation = 0, mevents = 0, eevents = 0;
	
	/**
	 * Association with the hamiltonian stochastic simulation.
	 */
	private HamiltonianSimulation sim;
	

	/**
	 * Constructor for the ShowResults class.
	 * 
	 * @param time time of the event, in this case, the print observation.
	 * @param sim the hamiltonian stochastic simulation, from where the graph, pec, colony and finalinst can be accessed.
	 */
	public ShowResults(double time, HamiltonianSimulation sim) {
		super(time);
		this.sim = sim;
		ShowResults.finalinst = sim.getFinalinst();
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
		System.out.println(toString());
		
		//simulate next ShowResults event
		sim.getPec().addEvPEC(new ShowResults(time+finalinst/n_obs, sim));
		
	}

	/**
	 * Override of the method toString() to print in a given format the:
	 * - number of observation;
	 * - present instant;
	 * - number of evaporation events;
	 * - number of move events;
	 * - hamiltonian cycle, if found;
	 * - cost of the hamiltonian cycle found;
	 */
	@Override
	public String toString() {
		String my_str ="";
		
		my_str += "Observation " + observation + ":\n\t\t Present instant: " + time + "\n\t\t Number of move events: " + mevents + "\n\t\t Number of evaporation events: " + eevents + "\n\t\t Hamiltonian cycle: ";
		
		if(!sim.getGraph().getShortest_path().isEmpty()) {
			my_str += "{";
			for (graph.Node node: sim.getGraph().getShortest_path()) {
				my_str += node.getIndex() + ",";
			}
			
			//erase the last comma
			my_str = (String) my_str.subSequence(0, my_str.length()-1);
			my_str +="}\n";
			
		}
		return my_str;
	}

	/**
	 * Getter for the finalinst field.
	 * @return the final instant of the simulation.
	 */
	public static double getFinalinst() {
		return finalinst;
	}

	/**
	 * Setter for the finalinst field.
	 * @param finalinst the final instant of the simulation.
	 */
	public static void setFinalinst(double finalinst) {
		ShowResults.finalinst = finalinst;
	}

	/**
	 * Getter for the observation field.
	 * @return the number of observations printed so far.
	 */
	public static int getObservation() {
		return observation;
	}

	/**
	 * Setter for the observation field.
	 * @param observation the number of observations.
	 */
	public static void setObservation(int observation) {
		ShowResults.observation = observation;
	}

	/**
	 * Getter for the mevents field. 
	 * @return the number of move events.
	 */
	public static int getMevents() {
		return mevents;
	}

	/**
	 * Setter for the mevents field.
	 * @param mevents the number of move events.
	 */
	public static void setMevents(int mevents) {
		ShowResults.mevents = mevents;
	}

	/**
	 * Getter for the eevents field.
	 * @return the number of evaporation events.
	 */
	public static int getEevents() {
		return eevents;
	}

	/**
	 * Setter for the eevents field.
	 * @param eevents the number of evaporation events.
	 */
	public static void setEevents(int eevents) {
		ShowResults.eevents = eevents;
	}
	
	/**
	 * Getter for the sim field.
	 * @return the hamiltonian stochastic simulation.
	 */
	public HamiltonianSimulation getSim() {
		return sim;
	}

	/**
	 * Setter for the sim field.
	 * @param sim the hamiltonian stochastic simulation.
	 */
	public void setSim(HamiltonianSimulation sim) {
		this.sim = sim;
	}
	
	
}