package event;

/**
 * Interface for Events.
 * The interface must provide three methods: the method simulateEvent() to simulate a new event, 
 * and the setters and getters of the time field of an event.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */

public interface IEvent {
	/**
	 * Abstract method simulateEvent().
	 */
	public abstract void SimulateEvent();

	/**
	 * Getter for the time field.
	 * @return the time of the event.
	 */
	public abstract double getTime();

	/**
	 * Setter for the time field.
	 * @param time time of the event.
	 */
	public abstract void setTime(double time);
}
