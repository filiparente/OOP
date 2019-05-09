/**
 * The package pec contains the PEC interface and the PEC class which implements the PEC interface as a priority queue of IEvent objects.
 */
package pec;

import event.IEvent;

/**
 * Interface for the PEC (Pending event container).
 * It requires the implementation of two methods: the addEvPEC, to add
 * new events to the PEC as interface event (IEvent) objects; and the nextEvPEC,
 * to remove from the PEC the next event to be simulated.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
public interface PECInterface {
	
	/**
	 * Method to add a new event to the PEC.
	 * @param e the IEvent object to add.
	 */
	public abstract void addEvPEC(IEvent e);
	
	/**
	 * Method to remove from the PEC the next event to be simulated.
	 * @return the next event to be simulated.
	 */
	public abstract IEvent nextEvPEC();
}
