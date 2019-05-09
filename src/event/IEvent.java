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
