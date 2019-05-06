package pec;
import event.*;

import java.util.PriorityQueue;

public class PEC implements PECInterface {
	
	PriorityQueue<Event> PEC_q = new PriorityQueue<Event>(new EventComparator());

	@Override
	public void addEvPEC(Event e) {
		PEC_q.add(e);	
	}

	@Override
	public Event nextEvPEC() {
		return PEC_q.remove(); //event to be simulated
		
	}
}