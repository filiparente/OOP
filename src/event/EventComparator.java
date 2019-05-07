package event;

import java.util.Comparator;

/**
 * EventComparator class implements the interface Comparator provided by java.util library.
 * 
 * The abstract parameter of the interface is specified as an Event object.
 * This class overrides the method compare() from the interface Comparator.
 * 
 * @author Filipa, Gonçalo, Joana.
 *
 */
public class EventComparator implements Comparator<Event>{ 
    

	/**
	 * Overriding of the method compare() from Comparator<T> interface, where T is an Event object inside this class.
	 * It compares two events in ascending order of their times.
	 * 
	 * @param e1 first event to be compared.
	 * @param e2 second event to be compared.
	 * 
	 * @return -1 if e1 occurs before e2, 1 if e2 occurs before e1, and 0 if the events are simultaneous.
	 */
	@Override
    public int compare(Event e1, Event e2) { 
        if (e1.time < e2.time) 
            return -1; 
        else if (e1.time > e2.time) 
            return 1; 
        return 0; 
    } 
} 