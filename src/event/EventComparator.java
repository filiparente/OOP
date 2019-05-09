/**
 * The package event contains the interface IEvent, the abstract classes DeterministicEevent and StochasticEvent that implement the interface, two
 * subclasses of the StochasticEvent class - Move and Evaporation classes - and one subclass of the DeterministicEvent class - ShowResults class.
 * It also contains an EventComparator which compares two events in ascending order of their timestamps.
 * 
 * The simulateEvent() method in the Move class handles the ArrayIndexOutOfBoundsException exception because it is the only method where
 * the memory position of a deleted object might be accessed or removed.
 */
package event;

import java.util.Comparator;

/**
 * EventComparator class implements the interface Comparator provided by java.util library.
 * 
 * The abstract parameter of the interface is specified as an IEvent object.
 * This class overrides the method compare() from the interface Comparator.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
public class EventComparator implements Comparator<IEvent>{ 
    

	/**
	 * Overriding of the method compare() from ComparatorT interface, where T is an IEvent object inside this class.
	 * It compares two events in ascending order of their times.
	 * 
	 * @param e1 first event to be compared.
	 * @param e2 second event to be compared.
	 * 
	 * @return -1 if e1 occurs before e2, 1 if e2 occurs before e1, and 0 if the events are simultaneous.
	 */
	@Override
    public int compare(IEvent e1, IEvent e2) { 
        if (e1.getTime() < e2.getTime()) 
            return -1; 
        else if (e1.getTime() > e2.getTime()) 
            return 1; 
        return 0; 
    } 
} 