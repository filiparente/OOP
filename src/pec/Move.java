package pec;
import graph.*;
import simulation.*;
import utils.Utils;
import utils.Multinomial;

import java.util.LinkedList;
import java.util.List;

import colony.*;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Map.Entry; 

public class Move extends Event{	

	static double alpha, beta, delta;
	
	public Move(double time, Ant ant, Simulation sim) {
		super(time, ant);
		// TODO Auto-generated constructor stub
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
		
		//Get the ants current node
		Node ant_currnode = this.ant.getLastNode();
		
		//Get the adjacents list of the current node (from the graph)
		Graph graph = this..getGraph();
		List<Edge> adj_list = ant_currnode.getEdges();
		
		//create an auxiliar list (valid move nodes) which is the adjacents list except the intersection
		List<Edge> valid_list = new LinkedList<Edge>();
		
		//Check the intersection between the adjacents list and the path of the ant
		for(int i=0; i<adj_list.size(); i++) {
			Edge e = adj_list.get(i);
			if(!this.ant.getPath().contains(Event.sim.getGraph().getNode(e.getAdj(ant_currnode)))) {
				valid_list.add(e);
				
			}
		}
		
		
		double traversalTime = 0.0;
		
		if(valid_list.isEmpty()) {
			//******************** A **************************
			//if the auxiliar list is empty, there are no valid moves for the ant (see section 4)
			
			//the ant chooses randomly any adjacent node with a uniform distribution
			int n_adj = adj_list.size();
			double[] probabilities = new double[n_adj];
			
			for(int i=0; i<n_adj;i++) {
				probabilities[i] = 1.0/n_adj;
			}
			
			//sample one time a multinomial with the probabilities obtained in the last step
			Multinomial m = new Multinomial(probabilities);
			
			int chosen_index = m.sample();

			
			//the result is the node where the ant is going to move to
			Edge chosen_edge = adj_list.get(chosen_index);
			
			//the result is the node where the ant is going to move to
			Node chosen_node = graph.getNode((chosen_edge.getNode1() == ant_currnode.getIndex())? chosen_edge.getNode2() : chosen_edge.getNode1());

			
			//first check if the ant moved to the nest node
			if(chosen_node.getIndex() == this.sim.getGraph().getNestnode()) {
				
				//if so, update the ants path accordingly
				//this.ant.getPath().add(chosen_node);
				
				//check if after adding the nest node, the ant completes a Hamiltonian cycle
				if(check_hamiltonian_cycle(this.ant.getPath())) {
					
					//the ant lays down pheromones in all edges constituting the cycle
					double cycle_weight = 0.0;
					List<Node> ant_path = this.ant.getPath();
					
					for(int i=0; i<this.ant.getPath().size()-1; i++) {
						List<Edge> edges = this.sim.getGraph().getNodes()[ant_path.get(i).getIndex()].getEdges();
						
						//find the edge which has a node with index equal to the next node in the ants path
						//TODO:
						//edge = edges.get(ant_path.get(i+1).getIndex()) ESTÁ MAL!!!
						//cycle_weight += edge.getCost();
					}
					
					for(int i=0; i<this.ant.getPath().size()-1; i++) {
						Edge edge = this.sim.getGraph().getNodes()[ant_path.get(i).getIndex()].getEdges().get(ant_path.get(i+1).getIndex());
						edge.setCost(edge.getCost() + (Move.delta*this.sim.getGraph().getW())/cycle_weight);
					}
					
					
				}
				
			}
			
			//update the ants path accordingly by removing the cycle created in the last move
			int last_idx = this.ant.getPath().lastIndexOf(chosen_node);			
			
			this.ant.getPath().subList(last_idx, this.ant.getPath().size()).clear();
			
			//calculate the time to traverse (traversalTime) the edge from the current node to the result node (needs the delta and the cost present in the edge) ruled by an exponential distribution with mean delta*cost
			traversalTime = Utils.expRandom(Move.delta*adj_list.get(chosen_index).getCost());

			
		}else {
			
			double cross_cost;
			double total_cost = 0.0;
			double[] probabilities = new double[valid_list.size()];
			int idx = 0;
			
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
			
			int chosen_index = m.sample();
			
			//the result is the node where the ant is going to move to
			Edge chosen_edge = valid_list.get(chosen_index);
			
			Node chosen_node = graph.getNode((chosen_edge.getNode1() == ant_currnode.getIndex())? chosen_edge.getNode2() : chosen_edge.getNode1());
			
			//update the ants path accordingly
			this.ant.getPath().add(chosen_node);
			
			//calculate the time to traverse (traversalTime) the edge from the current node to the result node (needs the delta and the cost present in the edge) ruled by an exponential distribution with mean delta*cost
			traversalTime = Utils.expRandom(Move.delta*valid_list.get(chosen_index).getCost());

		}
		
		
		//schedule new Move in currentTime + traversalTime
		Move new_move = new Move(this.time+traversalTime, this.ant);
		
		//add it to the PEC
		Event.sim.getPec().addEvPEC(new_move);
		
		//TODO: In the simulation of the n-th move, new event must be added to the PEC: if (after the n-th move) the ant contains a Hamiltonian cycle, it should lay down pheromones in all edges constituting the cycle. Therefore, one evaporation event for each edge (constituting the cycle) must be scheduled. 
		
		
	}


	private boolean check_hamiltonian_cycle(List<Node> path) {
		int n_nodes = Event.sim.getGraph().getNbnode();
		int nest_node = Event.sim.getGraph().getNestnode();
		int last_idx = path.size()-1;
		
		if ((path.size() == n_nodes) && path.get(0).getIndex() == nest_node)
		{
			//number of elements is correct and nest node is the first and the last node
			//check the frequencies of the rest of the nodes in the path
			List<Node> rest_path = path.subList(1, last_idx-1);
			Map<Node, Integer> map = countFrequencies(rest_path);
			
			//checking if all elements appear only once
	        for (Map.Entry<Node, Integer> val : map.entrySet()) { 
	        	if(val.getValue() != 1) return false;
	        }
	        return true;
			
		}
		return false;
	}
	
	private Map<Node,Integer> countFrequencies(List<Node> path) 
    { 
        // hashmap to store the frequency of the nodes in the path 
        Map<Node, Integer> hm = new HashMap<Node, Integer>(); 
  
        for (Node node : path) { 
            Integer count = hm.get(node); 
            hm.put(node, (count == null) ? 1 : count + 1); 
        } 
        
        return hm;
 
    } 
	

}
