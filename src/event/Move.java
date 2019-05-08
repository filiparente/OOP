package event;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import colony.Ant;
import graph.*;

import pec.PEC;
import utils.Utils;
import utils.Multinomial;



public class Move extends Event{	
	static double alpha, beta, delta;
	Ant ant;

	public Move(double time, Ant ant, Graph graph, PEC pec) {
		super(time, graph, pec);
		this.ant = ant;
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
		
		ShowResults.mevents += 1;
		
		//Get the ants current node
		Node ant_currnode = ant.getLastNode();
		
		//Get the adjacents list of the current node (from the graph)
		List<Edge> adj_list = ant_currnode.getEdges();
		
		//create an auxiliar list (valid move nodes) which is the adjacents list except the intersection
		List<Edge> valid_list = new LinkedList<Edge>();
		
		//Check the intersection between the adjacents list and the path of the ant
		for(int i=0; i<adj_list.size(); i++) {
			Edge e = adj_list.get(i);
			//int check = ant.getPathcheck()[e.getAdj(ant_currnode)-1];
			//if(check < 1){
			int idx = e.getAdj(ant_currnode);
			
			//AND of bitmask of nodes already visited by the ant and a byte of zeros 
			//and only 1 in the index of the node gives 0 if the node was not visited yet by the ant
			/*if((byte) ((byte) (1<<idx) & this.ant.getBitmask()) == 0)*/
			if(this.ant.getPathcheckOne(idx)== 0){ //not visited yet
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
			if(chosen_node.getIndex() == graph.getNestnode()) {

				//check if after adding the nest node, the ant completes a Hamiltonian cycle
				if(check_hamiltonian_cycle(this.ant.getPath(), graph)) {
					
					double cycle_weight = 0.0;
					ant.setNodePath(chosen_node);
					List<Node> ant_path = ant.getPath();
					
					for(int i=0; i<ant.getPath().size()-1; i++) {
						List<Edge> edges = graph.getNodes()[ant_path.get(i).getIndex()-1].getEdges();
						
						//find the edge which has a node with index equal to the next node in the ants path
						for(Edge edge: edges) {
							if(edge.getAdj(ant_path.get(i)) == ant_path.get(i+1).getIndex())
							{
								cycle_weight += edge.getCost();
							}
						}
					}
					
					for(int i=0; i<ant.getPath().size()-1; i++) {
						List<Edge> edges = graph.getNodes()[ant_path.get(i).getIndex()-1].getEdges();
						
						//find the edge which has a node with index equal to the next node in the ants path
						for(Edge edge: edges) {
							if(edge.getAdj(ant_path.get(i)) == ant_path.get(i+1).getIndex())
							{
								if(edge.getPheromones() > 0) {
									edge.setPheromones(edge.getPheromones() + (graph.getW())/cycle_weight);
								}else{
									edge.setPheromones(edge.getPheromones() + (graph.getW())/cycle_weight);
									Evaporation new_evaporation = new Evaporation(this.time, edge, graph, pec);
									pec.addEvPEC(new_evaporation);
								}
							}
						}
					}

					ant.getPath().subList(ant.getPath().size()-1, ant.getPath().size()).clear();
					if(cycle_weight < graph.getShortest_path_weight()) {
						graph.setShortest_path(ant.getPath());
						graph.setShortest_path_weight(cycle_weight);
					}

				}

				//ant restarts traversing the graph from the nest, i.e, path is updated
				ant.getPath().subList(1, ant.getPath().size()).clear();
				for (int i = 1; i <= ant.getPathcheck().length ; i++) {
					ant.setPathcheck(i, 0);
				}
				//reset mask: only the nest node is already visited
				ant.setPathcheck(graph.getNestnode(), 1);
				
				//reset mask: only the nest node is already visited
				//this.ant.setBitmask(1 << graph.getNestnode());
				

			}else {
			
				//update the ants path accordingly by removing the cycle created in the last move
				int last_idx = ant.getPath().lastIndexOf(chosen_node);

				for (Node node : ant.getPath().subList(last_idx, ant.getPath().size())) {
					ant.setPathcheck(node.getIndex(), 0);
				}
				
				//reset from bitmask
				/*for(Node node: ant.getPath().subList(last_idx, ant.getPath().size())) {
					int idx = node.getIndex();
					
					this.ant.setBitmask(this.ant.getBitmask()&~(1 << idx));
					
				}*/
				
				//reset from ant's path
				ant.getPath().subList(last_idx, ant.getPath().size()).clear();
				
			}
			
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
			this.ant.setNodePath(chosen_node);
			
			//update the ants mask accordingly: put the index of the node to 1 in the mask
			//this.ant.setBitmask(this.ant.getBitmask()| (1<<chosen_node.getIndex()));
			ant.setPathcheck(chosen_node.getIndex(), 1);
			
			//calculate the time to traverse (traversalTime) the edge from the current node to the result node (needs the delta and the cost present in the edge) ruled by an exponential distribution with mean delta*cost
			traversalTime = Utils.expRandom(Move.delta*valid_list.get(chosen_index).getCost());

		}
		
		//schedule new Move in currentTime + traversalTime
		Move new_move = new Move(this.time+traversalTime, ant, graph, pec);
		
		//add it to the PEC
		pec.addEvPEC(new_move);

		//System.out.println(ant.getPath());

	}


	private boolean check_hamiltonian_cycle(List<Node> path, Graph graph) {
		int n_nodes = graph.getNbnode();
		int nest_node = graph.getNestnode();
		
		if ((path.size() == n_nodes) && path.get(0).getIndex() == nest_node)
		{
			//number of elements is correct and nest node is the first and the last node
			//check the frequencies of the rest of the nodes in the path
			Set<Node> set = Set.copyOf(path);
			if (set.size() == n_nodes)
	        	return true;
			
		}
		return false;
	}
	
	/*
	 * import java.util.Map;
	 * import java.util.HashMap;
	private Map<Node,Integer> countFrequencies(List<Node> path) 
    { 
        // hashmap to store the frequency of the nodes in the path 
        Map<Node, Integer> hm = new HashMap<Node, Integer>(); 
  
        for (Node node : path) { 
            Integer count = hm.get(node); 
            hm.put(node, (count == null) ? 1 : count + 1); 
        } 
        
        return hm;
 
    } */
	

}
