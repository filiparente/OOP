package pec;
import graph.*;
import utils.Utils;
import colony.*;

public class Evaporation extends Event{	
	static double eta, rho;

	public Evaporation(double time, Ant ant) {
		super(time, ant);
	}


	public static double getEta() {
		return eta;
	}


	public static void setEta(double eta) {
		Evaporation.eta = eta;
	}


	public static double getRho() {
		return rho;
	}


	public static void setRho(double rho) {
		Evaporation.rho = rho;
	}
	
	@Override
	public void SimulateEvent() {
		/* TODO: In the simulation of the n-th evaporation, one new event might be added to the PEC:
		if (after the n-th evaporation) the pheromones level of the respective edge is greater than zero, 
		the (n+1)-th evaporation of the respective edge.*/
		
		//Get all edges of the graph with a positive pheromone level
		//reduce the level of all previous edges by rho, if possible (pheromone level must be positive, otherwise throw an exception?)
		for(Node node: this.sim.getGraph().getNodes()) {
			for(Edge edge: node.getEdges()) {
				edge.setPheromones(edge.getPheromones()-Evaporation.rho);
			}
		}
		
		
		//calculate the time between the current evaporation and the next evaporation (evaporationTime) ruled by an exponential distribution with mean eta
		double evaporationTime = Utils.expRandom(Evaporation.eta);
		
		//schedule new Evaporation event in currentTime + evaporationTime
		Evaporation new_evaporation = new Evaporation(this.time+evaporationTime, this.ant);
		
		//add it to the PEC
		Event.sim.getPec().addEvPEC(new_evaporation);
	}
}