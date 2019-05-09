/**
 * The package pec contains the PEC interface and the PEC class which implements the PEC interface as a priority queue of IEvent objects.
 */
package pec;
import event.IEvent;
import event.EventComparator;

import java.util.PriorityQueue;

/**
 * Implementation of the PECInterface where the PEC is implemented
 * as a priority queue of interface event objects. 
 * 
 * The events are prioritized according to their timestamps, i.e., the event with smallest time is in the beginning
 * of the queue while the event with largest time is in the end of the queue. The prior comparator
 * is the EventComparator class.
 * The two methods required from the interface are implemented.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
public class PEC implements PECInterface {
	/**
	 * Priority queue of IEvent objects with EventComparator prior.
	 * Parameterization of the generic PriorityQueueT from the java.util library where T is an IEvent object.
	 */
	private PriorityQueue<IEvent> PEC_q = new PriorityQueue<IEvent>(new EventComparator());

	/**
	 * Method to add a new event to the PEC, according to the prior (timestamp).
	 * @param e the IEvent object to add.
	 */
	@Override
	public void addEvPEC(IEvent e) {
		PEC_q.add(e);	
	}

	/**
	 * Method to remove from the PEC the next event to be simulated, according to the EventComparator
	 * which compares events in ascending order of their times, i.e., the next event to be simulated is the
	 * one with the smaller time in PEC.
	 * 
	 * @return the next event to be simulated.
	 */
	@Override
	public IEvent nextEvPEC() {
		return PEC_q.remove();
		
	}
	
	/**
	 * Getter for the PEC_q field.
	 * @return the priority queue of events.
	 */
	public PriorityQueue<IEvent> getPEC_q() {
		return PEC_q;
	}

	/**
	 * Setter for the PEC_q field.
	 * @param PEC_q the priority queue of events.
	 */
	public void setPEC_q(PriorityQueue<IEvent> PEC_q) {
		this.PEC_q = PEC_q;
	}
}