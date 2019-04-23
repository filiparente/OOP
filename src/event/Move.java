package event;
import graph.*;
import colony.*;

public class Move extends Event{	
	static double alpha, beta, delta;


	public Move(double time, Ant ant, double alpha, double beta, double delta) {
		super(time, ant);
	}

	
	public static double getAlpha() {
		return alpha;
	}


	public static void setAlpha(double alpha) {
		Move.alpha = alpha;
	}


	public static double getBeta() {
		return beta;
	}


	public static void setBeta(double beta) {
		Move.beta = beta;
	}


	public static double getDelta() {
		return delta;
	}


	public static void setDelta(double delta) {
		Move.delta = delta;
	}


	@Override
	protected void SimulateEvent() {
		// TODO
		//Graph graph = this.sim.getGraph();
		
		//Get the ants current node
		Node ant_currnode = this.ant.getLastNode();
		
		//Get the adjacents list of the current node (from the graph)
		
		//Check the intersection between the adjacents list and the path of the ant
		
		//create an auxiliar list (valid move nodes) which is the adjacents list except the intersection
		
		//******************** A **************************
		//if the auxiliar list is empty, there are no valid moves for the ant (see section 4)
		// (...)
		
		//******************** B **************************
		//calculate the probability of the ant moving from the current node to each node in the auxiliar list (needs alpha, beta, and the cost and pheromone level which is in the edge structure)
		
		//sample one time a multinomial with the probabilities obtained in the last step
		
		//the result is the node where the ant is going to move to
		
		//update the ants path accordingly
		
		//calculate the time to traverse (traversalTime) the edge from the current node to the result node (needs the delta and the cost present in the edge) ruled by an exponential distribution with mean delta*cost
		
		//schedule new Move in currentTime + traversalTime
		
		//add it to the PEC
		
	}
	

}
