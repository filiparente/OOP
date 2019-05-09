/**
 * The package event contains the interface IEvent, the abstract classes DeterministicEevent and StochasticEvent that implement the interface, two
 * subclasses of the StochasticEvent class - Move and Evaporation classes - and one subclass of the DeterministicEvent class - ShowResults class.
 * It also contains an EventComparator which compares two events in ascending order of their timestamps.
 * 
 * The simulateEvent() method in the Move class handles the ArrayIndexOutOfBoundsException exception because it is the only method where
 * the memory position of a deleted object might be accessed or removed.
 */
package event;

import simulation.HamiltonianSimulation;

/**
 * Abstract class StochasticEvent implements the interface IEvent.
 * 
 * Describes the events of the stochastic simulation. Requires the implementation of the abstract method simulateEvent().
 * @author Filipa, Goncalo, Joana.
 *
 */

 abstract class StochasticEvent implements IEvent{

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
