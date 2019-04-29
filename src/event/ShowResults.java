package event;
import colony.Ant;
import colony.Colony;
import graph.Graph;
import pec.PEC;


public class ShowResults extends Event{	
	static double finalinst;
	static int observation = 0, mevents = 0, eevents = 0;
	Colony colony;
	

	public ShowResults(double time, Graph graph, PEC pec, Colony colony, double finalinst) {
		super(time, graph, pec);
		this.colony = colony;
		this.finalinst = finalinst;
	}

	@Override
	public void SimulateEvent() {
		ShowResults.observation += 1;
		
		System.out.println(this.toString());
		
		//simulate next ShowResults event
		pec.addEvPEC(new ShowResults(time+finalinst/20, graph, pec, colony, finalinst));
		
	}

	@Override
	public String toString() {
		double min_weight = 10000;
		colony.Ant chosen_ant = new Ant();
		String my_str ="";
		for(colony.Ant ant: colony.getAnts()) {
			if(ant.getWeight() < min_weight) {
				min_weight = ant.getWeight();
				chosen_ant = ant;
			}	
		}
		
		my_str += "Observation " + observation + ":\n\t\t Present instant: " + time + "\n\t\t Number of move events: " + mevents + "\n\t\t Number of evaporation events: " + eevents + "\n\t\t Hamiltonian cycle: {";
	
		for (graph.Node node: chosen_ant.getShortest()) {
			my_str += node.getIndex() + ",";
		
		}
		
		my_str = (String) my_str.subSequence(0, my_str.length()-1);
		my_str +="}";
		
		return my_str;
	
	
	}
	
	
}