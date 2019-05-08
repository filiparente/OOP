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