/**
 * The package simulation contains the inteface for the simulation ISimulation where the run method is left for implementation.
 * The Simulation class is an abstract class where the method run is still abstract but the final instant of the simulation is a field.
 * The Hamiltonian class is a subclass of the Simulation class where the data structures required to run the program are associated (such
 * as the graph, pec and colony) and the run method is implemented.
 */
package simulation;

/**
 * Interface for the Simulation.
 * Requires the implementation of the method run() to run the simulation.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
public interface ISimulation {
	/**
	 * Method to run the simulation.
	 */
	public abstract void run();

}
