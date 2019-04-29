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

	public int getAntcolsize() {
		return antcolsize;
	}

	public void setAntcolsize(int antcolsize) {
		this.antcolsize = antcolsize;
	}

	public double getFinalinst() {
		return finalinst;
	}

	public void setFinalinst(double finalinst) {
		this.finalinst = finalinst;
	}

	public double getPlevel() {
		return plevel;
	}

	public void setPlevel(double plevel) {
		this.plevel = plevel;
	}

	public PEC getPec() {
		return pec;
	}

	public void setPec(PEC pec) {
		this.pec = pec;
	}

	public Colony getColony() {
		return colony;
	}

	public void setColony(Colony colony) {
		this.colony = colony;
	}
}
