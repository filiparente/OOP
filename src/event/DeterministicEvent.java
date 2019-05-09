/**
 * The package event contains the interface IEvent, the abstract classes DeterministicEevent and StochasticEvent that implement the interface, two
 * subclasses of the StochasticEvent class - Move and Evaporation classes - and one subclass of the DeterministicEvent class - ShowResults class.
 * It also contains an EventComparator which compares two events in ascending order of their timestamps.
 * 
 * The simulateEvent() method in the Move class handles the ArrayIndexOutOfBoundsException exception because it is the only method where
 * the memory position of a deleted object might be accessed or removed.
 */
package event;

/**
 * DeterministicEvent is an abstract class that implements the interface IEvent.
 * 
 * Here events occur in a deterministic way over time. The total number of observations is specified.
 * The abstract method simulateEvent() must be provided.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */

  abstract class DeterministicEvent implements IEvent{
	
	/**
	 * Time at which the event occurs.
	 */
	protected double time;
	
	/**
	 * Number of observations.
	 * 
	 */
	protected static final int n_obs = 20;
	
	public DeterministicEvent(double time) {
		super();
		this.time = time;
	}

	/**
	 * Abstract method simulateEvent() to be implemented in all subclasses of the superclass DeterministicEvent.
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
