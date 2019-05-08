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

public abstract class DeterministicEvent implements IEvent{
	
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
