package pec;
import java.util.PriorityQueue;

public class PEC {
	PriorityQueue<Event> PEC_q = new PriorityQueue<Event>(new EventComparator());

	public void addEvPEC(Event e) {
		PEC_q.add(e);	
	}
	
	public Event nextEvPEC() {
		return PEC_q.remove(); //event to be simulated
		
	}
}