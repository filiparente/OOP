package colony;
import graph.Graph;
import event.Event;
import pec.PEC;

public class Colony {
	double finalinst, plevel;
	int antcolsize;
	Graph graph;
	Event current_event;
	Ant[] ants;
	PEC pec;
	
	public Colony(double finalinst, int antcolsize, double plevel, Graph graph) {
		super();
		this.finalinst = finalinst;
		this.antcolsize = antcolsize;
		this.plevel = plevel;
		this.graph = graph;
		this.ants = new Ant[antcolsize];
		this.pec = new PEC();
	}

	public double getFinalinst() {
		return finalinst;
	}

	public void setFinalinst(double finalinst) {
		this.finalinst = finalinst;
	}

	public int getAntcolsize() {
		return antcolsize;
	}

	public void setAntcolsize(int antcolsize) {
		this.antcolsize = antcolsize;
	}

	public double getPlevel() {
		return plevel;
	}

	public void setPlevel(double plevel) {
		this.plevel = plevel;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
	public void setAnt(Ant ant, int idx) {
		this.ants[idx-1] = ant;
	}
	
	public Ant[] getAnts() {
		return this.ants;
	}

	public Event getCurrent_event() {
		return current_event;
	}
	
	public PEC getPec() {
		return pec;
	}

	public void setCurrent_event(Event current_event) {
		this.current_event = current_event;
	}

	
	
	

}
