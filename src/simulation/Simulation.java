package simulation;
import colony.*;
import graph.*;
import pec.*;

public class Simulation {
	
	int antcolsize;
	double finalinst;
	double plevel;
	PEC pec;
	Colony colony;
    Graph graph;
	
	public Simulation(int antcolsize, double finalinst, double plevel, PEC pec, Colony colony, Graph graph) {
		super();
		this.antcolsize = antcolsize;
		this.finalinst = finalinst;
		this.plevel = plevel;
		this.pec = pec;
		this.colony = colony;
		this.graph = graph;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
	
	

}
