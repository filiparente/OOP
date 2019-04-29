package event;

import graph.Graph;
import pec.PEC;

public abstract class Event {
	double time;
	Graph graph;
	PEC pec;

	public Event(double time, Graph graph, PEC pec) {
		this.time = time;
		this.graph = graph;
		this.pec = pec;
	}

	public void SimulateEvent(){}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}
}