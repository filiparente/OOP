package event;
import colony.*;

public abstract class Event {
	double time;
	protected static Colony sim;
	protected Ant ant; //the ant that triggers the event

	public Event(double time, Ant ant) {
		this.time = time;
		this.ant = ant;
	}

	public static void setSim(Colony sim) {
		Event.sim = sim;
	}
	
	protected abstract void SimulateEvent();
	
	
}