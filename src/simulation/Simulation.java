package simulation;

/**
 * Abstract class that implements the interface ISimulation.
 * The method run is left for the implementation in the subclasses.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
public abstract class Simulation implements ISimulation{
	
	/**
	 * Final instant of the simulation.
	 */
	double finalinst;
	
	/**
	 * Constructor for the Simulation class.
	 * @param finalinst
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
