package event;
import graph.*;
import colony.*;

public class Evaporation extends Event{	
	static double eta, rho;

	public Evaporation(double time, Ant ant, double eta, double rho) {
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
	protected void SimulateEvent() {
		// TODO
		//Graph graph = this.sim.getGraph();
		
		//Get all edges of the graph with a positive pheromone level
		
		//reduce the level of all previous edges by rho, if possible (pheromone level must be greater or equal than rho, otherwise throw an exception)
		
		//update the graph accordingly (in principle by updating the edges it's already done, if everything is working properly)
		
		//calculate the time between the current evaporation and the next evaporation (evaporationTime) ruled by an exponential distribution with mean eta
		
		//schedule new evaporation event in currentTime + evaporationTime
		
		//add it to the PEC
		
	}
}