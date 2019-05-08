package event;

import simulation.HamiltonianSimulation;

/**
 * Abstract class StochasticEvent implements the interface IEvent.
 * 
 * Describes the events of the stochastic simulation. Requires the implementation of the abstract method simulateEvent().
 * @author Filipa, Goncalo, Joana.
 *
 */

public abstract class StochasticEvent implements IEvent{

	/**
	 * Time is the exact instant of the event to be simulated.
	 */
	protected double time;
	
	/**
	 * Association with the hamiltonian stochastic simulation.
	 */
	protected HamiltonianSimulation sim;
	

	/**
	 * Constructor for the abstract class Event.
	 * @param time time of the event.
	 * @param sim the hamiltonian stochastic simulation.
	 */
	public StochasticEvent(double time, HamiltonianSimulation sim) {
		this.time = time;
		this.sim = sim;
	}
	
	/**
	 * Abstract method simulateEvent() to be implemented in all subclasses of the superclass Event.
	 */

	public abstract void SimulateEvent();

	/**
	 * Getter for the time field.
	 * @return the time of the event.
	 */
	public double getTime() {
		return time;
	}

	/**
	 * Setter for the time field.
	 * @param time time of the event.
	 */
	public void setTime(double time) {
		this.time = time;
	}

}
