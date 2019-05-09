/**
 * The package event contains the interface IEvent, the abstract classes DeterministicEevent and StochasticEvent that implement the interface, two
 * subclasses of the StochasticEvent class - Move and Evaporation classes - and one subclass of the DeterministicEvent class - ShowResults class.
 * It also contains an EventComparator which compares two events in ascending order of their timestamps.
 * 
 * The simulateEvent() method in the Move class handles the ArrayIndexOutOfBoundsException exception because it is the only method where
 * the memory position of a deleted object might be accessed or removed.
 */
package event;