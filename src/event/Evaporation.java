package event;
import graph.*;
import pec.PEC;
import utils.Utils;

public class Evaporation extends Event{	
	static double eta, rho;
	Edge edge;

	public Evaporation(double time, Edge edge, Graph graph, PEC pec) {


		super(time + Utils.expRandom(Evaporation.eta), graph , pec );
		this.edge = edge;
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
		
		ShowResults.eevents += 1;
		
		/* TODO: In the simulation of the n-th evaporation, one new event might be added to the PEC:
		if (after the n-th evaporation) the pheromones level of the respective edge is greater than zero, 
		the (n+1)-th evaporation of the respective edge.*/

		//Get all edges of the graph with a positive pheromone level
		//reduce the level of all previous edges by rho, if possible (pheromone level must be positive, otherwise throw an exception?)
		edge.setPheromones(edge.getPheromones()-Evaporation.rho);

		if(edge.getPheromones() <= 0)
			edge.setPheromones(0);
		else {
			//schedule new Evaporation event in currentTime + evaporationTime
			Evaporation new_evaporation = new Evaporation(this.time, edge, graph, pec);

			//add it to the PEC
			pec.addEvPEC(new_evaporation);
		}
	}
}