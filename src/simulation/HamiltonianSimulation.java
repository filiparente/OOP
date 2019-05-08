package simulation;

import event.IEvent;
import event.Move;
import event.ShowResults;
import graph.WGraph;
import pec.PEC;
import colony.*;

/**
 * HamiltonianSimulation is a subclass of the Simulation class.
 * Here the method run is implemented, and runs the simulation of the Ant Colony Optimization
 * (ACO) problem of a weighted graph. The goal is to find the shortest hamiltonian cycle of the graph.
 * An hamiltonian cycle is a cycle starting and ending in the nest node, where all nodes of the graph
 * are included only once. The shortest hamiltonian cycle is the one where the sum of the cost its edges is
 * the smallest possible.
 * For the simulation, a colony of ants is used to traverse the graph in a stochastic way.
 * The simulation is performed by triggering events with a pending event container (PEC).
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
public class HamiltonianSimulation extends Simulation{
	/**
	 * The size of the colony of ants.
	 */
	private int antcolsize;
	
	/**
	 * The reduction rate of the pheromone level of the edges in each evaporation event.
	 */
	private double plevel;
	
	/**
	 * The pending event container associated with the simulation.
	 */
	private PEC pec;
	
	/**
	 * The colony of ants associated with the simulation.
	 */
	private AntColony colony;
	
	/**
	 * The weighted graph associated with the simulation.
	 */
    private WGraph graph;
	
    /**
     * Constructor for the HamiltonianSimulation class.
     * 
     * @param antcolsize  size of the colony of ants (discrete number greater or equal than 1).
     * @param finalinst  final instant when the hamiltonian stochastic simulation must stop.
     * @param plevel reduction in the pheromone level of the edges in each evaporation event (double between 0 and 1).
     * @param pec pending event container.
     * @param colony colony of ants.
     * @param graph graph.
     * 
     * The superclass Simulation is initialized with the parameter finalinst.
     */
	public HamiltonianSimulation(int antcolsize, double finalinst, double plevel, PEC pec, AntColony colony, WGraph graph) {
		super(finalinst);
		this.antcolsize = antcolsize;
		this.plevel = plevel;
		this.pec = pec;
		this.colony = colony;
		this.graph = graph;
	}

	/**
	 * Method to run the hamiltonian stochastic simulation.
	 * 
	 * At the beginning, for each ant in the colony its first move is scheduled and added to the PEC. 
	 * The next ant moves and edge evaporations are (possibly) scheduled and added when the previous ones are being simulated.
	 * The first print of the results is scheduled and added to the PEC. 
	 * Similarly, the next ones are scheduled and added when the previous ones are being simulated.
	 * The simulation cycle removes the next event from the PEC and simulates it, until the final instant is reached.
	 */
	 public void run() {
		AntColony c = this.getColony();
 
		IEvent currentEvent = null;

		for(Ant ant: c.getElems()) {
			currentEvent = new Move(0.0, ant, this);
			this.getPec().addEvPEC(currentEvent);
		}

		this.getPec().addEvPEC(new ShowResults(this.getFinalinst()/20, this));


		double currentTime = 0.0;

		//simulation cycle
		while(true) {
			currentEvent = this.getPec().nextEvPEC();
			currentTime = currentEvent.getTime();
			if(currentTime > this.getFinalinst()) {
				break;
			}
			currentEvent.SimulateEvent();
		}

	}
	
	/**
	 * Getter for the graph field.
	 * @return the graph of the simulation.
	 */
	public WGraph getGraph() {
		return graph;
	}

	/**
	 * Setter for the graph field.
	 * @param graph graph of the simulation.
	 */
	public void setGraph(WGraph graph) {
		this.graph = graph;
	}

	/**
	 * Getter for the antcolsize field.
	 * @return the size of the colony of ants.
	 */
	public int getAntcolsize() {
		return antcolsize;
	}

	/**
	 * Setter for the antcolsize field.
	 * @param antcolsize size of the colony of ants.
	 */
	public void setAntcolsize(int antcolsize) {
		this.antcolsize = antcolsize;
	}

	/**
	 * Getter for the plevel field.
	 * @return the reduction rate of the pheromone level of the edges of the graph.
	 */
	public double getPlevel() {
		return plevel;
	}

	/**
	 * Setter for the plevel field.
	 * @param plevel reduction rate of the pheromone level of the edges of the graph.
	 */
	public void setPlevel(double plevel) {
		this.plevel = plevel;
	}

	/**
	 * Getter for the pec field.
	 * @return pec, pending event container.
	 */
	public PEC getPec() {
		return pec;
	}

	/**
	 * Setter for the pec field.
	 * @param pec pending event container.
	 */
	public void setPec(PEC pec) {
		this.pec = pec;
	}

	/**
	 * Getter for the colony field.
	 * @return colony of ants.
	 */
	public AntColony getColony() {
		return colony;
	}

	/**
	 * Setter for the colony field.
	 * @param colony colony of ants.
	 */
	public void setColony(AntColony colony) {
		this.colony = colony;
	}
}
