package event;
import graph.Edge;
import simulation.HamiltonianSimulation;
import utils.Utils;

/**
 * The Evaporation class is a subclass of the abstract class StochasticEvent, from which it inherits
 * the field time and the simulation.
 * 
 * The Evaporation class implements the method simulateEvent() in which
 * the pheromones associated with an edge of a graph are reduced according to
 * some parameter. This implementation assumes an association with an edge belonging to
 * a graph - the graph of the stochastic simulation. The time between two consecutive simulation of evaporations is a stochastic variable.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
public class Evaporation extends StochasticEvent{	
	/**
	 * static field eta controls the time between the current evaporation and the next one.
	 * static field rho controls the amount of pheromones being reduced.
	 */
	private static double eta, rho;
	
	/**
	 * The edge associated to the Evaporation event. Only the pheromones of this edge are evaporated.
	 */
	private Edge edge;

	/**
	 * Constructor of the Evaporation event.
	 * 
	 * @param time the current time. The field time of the superclass StochasticEvent is instantiated with this time
	 * plus the result of an exponential distribution with mean eta, the static field from the Evaporation class.
	 * Therefore, Evaporation events are invoked with the current time and the field time from the superclass is, in this case, associated with the next
	 * Evaporation event.
	 * 
	 * @param edge the edge to reduce pheromones from.
	 * @param sim the hamiltonian stochastic simulation with which the evaporation events are associated.
	 */
	public Evaporation(double time, Edge edge, HamiltonianSimulation sim) {

		super(time + Utils.expRandom(Evaporation.eta), sim);
		this.edge = edge;
	}

	/**
	 * Getter for the static field eta.
	 * @return eta.
	 */
	public static double getEta() {
		return eta;
	}

	/**
	 * Setter for the static field eta.
	 * @param eta.
	 */
	public static void setEta(double eta) {
		Evaporation.eta = eta;
	}

	/**
	 * Getter for the static field rho.
	 * @return rho.
	 */
	public static double getRho() {
		return rho;
	}

	/**
	 * Setter for the static field rho.
	 * @param rho.
	 */
	public static void setRho(double rho) {
		Evaporation.rho = rho;
	}

	/**
	 * Getter for the edge associated with the evaporation event.
	 * @return edge.
	 */
	Edge getEdge() {
		return edge;
	}

	/**
	 * Setter for the edge associated with the evaporation event.
	 * @param edge
	 */
	void setEdge(Edge edge) {
		this.edge = edge;
	}

	
	/**
	 * Simulates the Evaporation event. 
	 * 
	 * The counter for the number of evaporation events is incremented. This counter belongs to class ShowResults, subclass of the abstract class DeterministicEvent.
	 * 
	 * Performs the evaporation by reducing the pheromone level of this edge by rho.
	 * If, after the evaporation, the pheromone level of the respective edge is greater than zero, schedules the next evaporation event for this edge,
	 * and adds the event to the pec. Otherwise, sets the pheromone level to zero, because it can't be negative.
	 * 
	 */
	@Override
	public void SimulateEvent() {
		
		ShowResults.setEevents(ShowResults.getEevents()+1);

		//Evaporation
		edge.setPheromones(edge.getPheromones()-Evaporation.rho);

		if(edge.getPheromones() <= 0)
			edge.setPheromones(0);
		else {
			//schedule next Evaporation event for this edge
			Evaporation new_evaporation = new Evaporation(time, edge, sim);

			//add it to the PEC
			sim.getPec().addEvPEC(new_evaporation);
		}
	}
}