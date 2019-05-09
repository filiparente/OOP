package event;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import colony.Ant;
import graph.*;
import simulation.HamiltonianSimulation;
import utils.Utils;
import utils.Multinomial;

/**
 * The Move class is a subclass of the abstract class StochasticEvent, from which it inherits
 * the field time and the simulation.
 * 
 * The Move class implements the method simulateEvent() in which
 * an ant moves from one node of the graph to another node of the graph. This implementation assumes an association with an ant belonging to
 * the colony of ants of the hamiltonian stochastic simulation. 
 * The time between two consecutive simulation of moves is a stochastic variable.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */

public class Move extends StochasticEvent{	
	/**
	 * static fields alpha and beta used to obtain the probability of moving to a certain node.
	 * static field delta controls the time between consecutive move events.
	 */
	private static double alpha, beta, delta;
	
	/**
	 * Ant associated with the move event.
	 */
	private Ant ant;

	/**
	 * Constructor for the Move class.
	 * 
	 * @param time time of the event.
	 * @param ant ant associated with the move event.
	 * @param sim hamiltonian stochastic simulation.
	 */
	public Move(double time, Ant ant, HamiltonianSimulation sim) {
		super(time, sim);
		this.ant = ant;
	}

	/**
	 * Getter for the alpha field.
	 * @return alpha.
	 */
	public static double getAlpha() {
		return alpha;
	}

	/**
	 * Setter for the alpha field.
	 * @param alpha alpha.
	 */
	public static void setAlpha(double alpha) {
		Move.alpha = alpha;
	}

	/**
	 * Getter for the beta field.
	 * @return beta.
	 */
	public static double getBeta() {
		return beta;
	}

	/**
	 * Setter for the beta field.
	 * @param beta beta.
	 */
	public static void setBeta(double beta) {
		Move.beta = beta;
	}

	/**
	 * Getter for the delta field.
	 * @return delta.
	 */
	public static double getDelta() {
		return delta;
	}

	/**
	 * Setter for the delta field.
	 * @param delta delta.
	 */
	public static void setDelta(double delta) {
		Move.delta = delta;
	}

	/**
	 * Getter for the ant field.
	 * @return ant.
	 */
	public Ant getAnt() {
		return ant;
	}

	/**
	 * Setter for the ant field.
	 * @param ant ant.
	 */
	public void setAnt(Ant ant) {
		this.ant = ant;
	}


	/**
	 * Method to simulate a move event.
	 * 
	 * The counter for the number of move events is incremented. This counter belongs to class ShowResults, subclass of the abstract class DeterministicEvent.
	 * From the graph, the adjacents list of the ant's current node (i.e., the last node of its path) is obtained.
	 * The ant's valid moves are found as a list of nodes that belong to the adjacents list but were not yet visited by the ant (i.e., that are not in its path).
	 * If this list is empty, the ant has no valid moves and it chooses randomly any adjacent node by sampling from a uniform distribution.
	 * After choosing the next node to move to, the ant must check if the next node is the nest node.
	 * If so, the ant must check if it completed a Hamiltonian cycle.
	 * Otherwise, the ant must update its path by removing the cycle created, both from its path and from the list of visited nodes.
	 * 
	 * If the ant completes a Hamiltonian cycle, it should calculate its weight by summing the cost of all the edges constituting the cycle. It lays down pheromones in each edge and if, after that,
	 * the pheromone level is positive, it should schedule a new evaporation event for that edge and add it to the pec.
	 * If the cost of the hamiltonian cycle found is smaller than the cost of the graph's shortest path found so far, it sets the graph's shortest path to the hamiltonian cycle found.
	 * 
	 * If the next node is the nest node, even if there's no hamiltonian cycle, the ant restarts traversing the graph from the nest. Therefore its path and the list of visited nodes is erased
	 * and only the nest node is kept. The time to traverse the edge from the current node to the result node is calculated.
	 * A new move event is scheduled and added to the pec. The move is simulated after that time has passed.
	 * 
	 * On the other hand, if the ant has valid moves, then the probability of the ant moving from the current node to each valid node is calculated. The chosen node is sampled
	 * from a multinomial distribution with these probabilities. After moving, the ant's path is updated accordingly, and the chosen
	 * node is assigned visited in the ant's list of visited nodes. The time to traverse the edge from the current node to the result node is calculated.
	 * A new move event is scheduled and added to the pec. The move is simulated after that time has passed.
	 * 	
	 */
	@Override
	public void SimulateEvent() {
		
		try {
			
			ShowResults.setMevents(ShowResults.getMevents() + 1);
			
			//Get the ant's current node
			Node ant_currnode = ant.getLastNode();
			
			//Get the adjacents list of the current node (from the graph)
			List<Edge> adj_list = ant_currnode.getEdges();
			
			//Create an auxiliar list (valid move nodes) which is the adjacents list except the intersection
			List<Edge> valid_list = new LinkedList<Edge>();
			
			//Check the intersection between the adjacents list and the path of the ant
			for(int i=0; i<adj_list.size(); i++) {
				Edge e = adj_list.get(i);
	
				int idx = e.getAdj(ant_currnode);
				
				if(this.ant.getPathcheckOne(idx)== false){ //not visited yet
					valid_list.add(e);
				}
			}
			
			double traversalTime = 0.0;
			
			if(valid_list.isEmpty()) {
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
				Node chosen_node = sim.getGraph().getNode((chosen_edge.getNode1() == ant_currnode.getIndex())? chosen_edge.getNode2() : chosen_edge.getNode1());
	
				//first check if the ant moved to the nest node
				if(chosen_node.getIndex() == sim.getGraph().getNestnode()) {
	
					//check if after adding the nest node, the ant completes a Hamiltonian cycle
					if(check_hamiltonian_cycle(this.ant.getPath(), sim.getGraph())) {
						
						double cycle_weight = 0.0;
						ant.setNodePath(chosen_node);
						List<Node> ant_path = ant.getPath();
						
						//Calculate the hamiltonian cycle weight
						for(int i=0; i<ant.getPath().size()-1; i++) {
							List<Edge> edges = sim.getGraph().getNodes()[ant_path.get(i).getIndex()-1].getEdges();
							
							//find the edge which has a node with index equal to the next node in the ants path
							for(Edge edge: edges) {
								if(edge.getAdj(ant_path.get(i)) == ant_path.get(i+1).getIndex())
								{
									cycle_weight += edge.getCost();
								}
							}
						}
						
						for(int i=0; i<ant.getPath().size()-1; i++) {
							List<Edge> edges = sim.getGraph().getNodes()[ant_path.get(i).getIndex()-1].getEdges();
							
							//find the edge which has a node with index equal to the next node in the ants path
							for(Edge edge: edges) {
								if(edge.getAdj(ant_path.get(i)) == ant_path.get(i+1).getIndex())
								{
									if(edge.getPheromones() > 0) {
										//lay down pheromones
										edge.setPheromones(edge.getPheromones() + (sim.getGraph().getW())/cycle_weight);
									}else{
										//lay down pheromones
										edge.setPheromones(edge.getPheromones() + (sim.getGraph().getW())/cycle_weight);
										//schedule a new evaporation event and add it to the pec
										Evaporation new_evaporation = new Evaporation(this.time, edge, sim);
										sim.getPec().addEvPEC(new_evaporation);
									}
								}
							}
						}
						
						//Remove the nest node from the last position of the path
						ant.getPath().subList(ant.getPath().size()-1, ant.getPath().size()).clear();
						
						//Set the shortest_path of the graph to the hamiltonian cycle found, if its cost is smaller than the one already assigned
						if(cycle_weight < sim.getGraph().getShortest_path_weight()) {
							sim.getGraph().setShortest_path(ant.getPath());
							sim.getGraph().setShortest_path_weight(cycle_weight);
						}
	
					}
	
					//ant restarts traversing the graph from the nest, i.e, path is updated
					ant.getPath().subList(1, ant.getPath().size()).clear();
					for (int i = 1; i <= ant.getPathcheck().length ; i++) {
						ant.setPathcheck(i, false);
					}
					//reset mask: only the nest node is already visited
					ant.setPathcheck(sim.getGraph().getNestnode(), true);
					
	
				}else {
				
					//update the ants path accordingly by removing the cycle created in the last move
					int last_idx = ant.getPath().lastIndexOf(chosen_node);
	
					for (Node node : ant.getPath().subList(last_idx, ant.getPath().size())) {
						ant.setPathcheck(node.getIndex(), false);
					}
					
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
				
				Node chosen_node = sim.getGraph().getNode((chosen_edge.getNode1() == ant_currnode.getIndex())? chosen_edge.getNode2() : chosen_edge.getNode1());
				
				//update the ants path accordingly
				this.ant.setNodePath(chosen_node);
				
				//update the ants mask accordingly: put the index of the node to 1 in the mask
				//this.ant.setBitmask(this.ant.getBitmask()| (1<<chosen_node.getIndex()));
				ant.setPathcheck(chosen_node.getIndex(), true);
				
				//calculate the time to traverse (traversalTime) the edge from the current node to the result node (needs the delta and the cost present in the edge) ruled by an exponential distribution with mean delta*cost
				traversalTime = Utils.expRandom(Move.delta*valid_list.get(chosen_index).getCost());
	
			}
		
			//schedule new Move in currentTime + traversalTime
			Move new_move = new Move(this.time+traversalTime, ant, sim);
		
			//add it to the PEC
			sim.getPec().addEvPEC(new_move);
		
		} catch ( ArrayIndexOutOfBoundsException e) {
			
			System.out.println("CAN'T ACCESS TO THE ARRAY POSITION, NODE NOT FOUND. TERMINATING PROGRAM");
			System.exit(2);
			
		}


	}

	/**
	 * Method to check if a path is an hamiltonian cycle or not.
	 * 
	 * @param path ant's path, where the nest node is not included in the end.
	 * @param graph graph.
	 * @return true if the path constitutes an hamiltonian cycle; false, otherwise.
	 * 
	 * Checks if the size of the path is equal to the total number of nodes in the graph.
	 * If so, checks if all nodes appear only once
	 * in the ant's path by comparing the size of the set of the path with the actual size of the path. If they match,
	 * then the path is an hamiltonian cycle.
	 */
	
	private boolean check_hamiltonian_cycle(List<Node> path, WGraph graph) {
		int n_nodes = graph.getNbnode();
		
		//number of elements is correct
		if (path.size() == n_nodes)
		{
			//check if all nodes appear only once in the path
			Set<Node> set = Set.copyOf(path);
			if (set.size() == n_nodes)
	        	return true;
			
		}
		return false;
	}
	

}
