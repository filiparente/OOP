/**
 * The package simulation contains the inteface for the simulation ISimulation where the run method is left for implementation.
 * The Simulation class is an abstract class where the method run is still abstract but the final instant of the simulation is a field.
 * The Hamiltonian class is a subclass of the Simulation class where the data structures required to run the program are associated (such
 * as the graph, pec and colony) and the run method is implemented.
 */
package simulation;

/**
 * Abstract class that implements the interface ISimulation.
 * The method run is left for the implementation in the subclasses.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
 abstract class Simulation implements ISimulation{
	
	/**
	 * Final instant of the simulation.
	 */
	double finalinst;
	
	/**
	 * Constructor for the Simulation class.
	 * @param finalinst final instant of the simulation.
	 */
	public Simulation(double finalinst) {
		
		this.finalinst = finalinst;
	}

	/**
	 * Method run left for implementation in the subclasses.
	 */
	public abstract void run();

	/**
	 * Getter for the finalinst field.
	 * @return the final instant when the simulation must stop.
	 */
	public double getFinalinst() {
		return finalinst;
	}

	/**
	 * Setter for the finalinst field.
	 * @param finalinst the final instant of the simulation.
	 */
	public void setFinalinst(double finalinst) {
		this.finalinst = finalinst;
	}

	
}
