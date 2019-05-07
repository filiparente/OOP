package event;

import graph.Graph;
import pec.PEC;

/**
 * Abstract class Event.
 * Describes the events of the stochastic simulation. Requires the implementation of the abstract method simulateEvent().
 * @author Filipa, Gonçalo, Joana.
 *
 */
public abstract class Event {
	/**
	 * Time is the exact instant of the event to be simulated.
	 */
	double time;
	
	/**
	 * Association with the graph of the stochastic simulation.
	 */
	Graph graph;
	
	/**
	 * Association with the pec of the stochastic simulation.
	 */
	PEC pec;

	/**
	 * Constructor for the abstract class Event.
	 * @param time time of the event.
	 * @param graph graph of the simulation.
	 * @param pec pec of the simulation.
	 */
	public Event(double time, Graph graph, PEC pec) {
		this.time = time;
		this.graph = graph;
		this.pec = pec;
	}
	
	/**
	 * Abstract method simulateEvent() to be implemented in all subclasses of the superclass Event.
	 */

	public void SimulateEvent(){}

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