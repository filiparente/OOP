package event;
import graph.*;

import utils.Utils;
import utils.Multinomial;

import java.util.LinkedList;
import java.util.List;

import colony.*;

public class Move extends Event{	
	static double alpha, beta, delta;


	public Move(double time, Ant ant) {
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
	public void SimulateEvent() {
		// TODO
		//Graph graph = this.sim.getGraph();
		
		//Get the ants current node
		Node ant_currnode = this.ant.getLastNode();
		
		//Get the adjacents list of the current node (from the graph)
		Graph graph = Event.sim.getGraph();
		List<Edge> adj_list = graph.getNodes()[ant_currnode.getIndex()-1].getEdges();
			
		//create an auxiliar list (valid move nodes) which is the adjacents list except the intersection
		List<Edge> valid_list = new LinkedList<Edge>();
		
		//Check the intersection between the adjacents list and the path of the ant
		for(Node node: this.ant.getPath()) {
			for(int i=0; i<adj_list.size(); i++) {
				Edge e = adj_list.get(i);
				if(!e.equals(node)) {
					valid_list.add(e);
				}
			}
		}

		double cross_cost;
		double total_cost = 0.0;
		double[] probabilities = new double[valid_list.size()];
		int idx = 0;
		

		//******************** A **************************
		//if the auxiliar list is empty, there are no valid moves for the ant (see section 4)
		// (...)
		
		
		
		
		//******************** B **************************
		//calculate the probability of the ant moving from the current node to each node in the auxiliar list (needs alpha, beta, and the cost and pheromone level which is in the edge structure)
		for(Edge edge: valid_list) {
			cross_cost = (Move.alpha+edge.getPheromones())/(Move.beta+edge.getCost());
			probabilities[idx++] = cross_cost;	
			total_cost += cross_cost;
		}
		
		for(int i=0; i<valid_list.size();i++) {
			probabilities[i] /= total_cost;
		}
		
		//sample one time a multinomial with the probabilities obtained in the last step
		Multinomial m = new Multinomial(probabilities);
		
		//the result is the node where the ant is going to move to
		Node chosen_node = graph.getNode(valid_list.get(m.sample()).getNode2());
		
		//update the ants path accordingly
		this.ant.getPath().add(chosen_node);
		
		//calculate the time to traverse (traversalTime) the edge from the current node to the result node (needs the delta and the cost present in the edge) ruled by an exponential distribution with mean delta*cost
		double traversalTime = Utils.expRandom(Move.delta*valid_list.get(m.sample()).getCost());
		
		//schedule new Move in currentTime + traversalTime
		Move new_move = new Move(this.time+traversalTime, this.ant);
		
		//add it to the PEC
		Event.sim.getPec().addEvPEC(new_move);
		
		//TODO: In the simulation of the n-th move, new event must be added to the PEC: if (after the n-th move) the ant contains a Hamiltonian cycle, it should lay down pheromones in all edges constituting the cycle. Therefore, one evaporation event for each edge (constituting the cycle) must be scheduled. 
		
		
	}
	

}
