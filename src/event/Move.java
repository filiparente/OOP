package event;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import colony.Ant;
import graph.*;
import simulation.HamiltonianSimulation;
import utils.Utils;
import utils.Multinomial;

public class Move extends StochasticEvent{	
	private static double alpha, beta, delta;
	private Ant ant;

	public Move(double time, Ant ant, HamiltonianSimulation sim) {
		super(time, sim);
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

	public Ant getAnt() {
		return ant;
	}


	public void setAnt(Ant ant) {
		this.ant = ant;
	}


	@Override
	public void SimulateEvent() {
		
		ShowResults.setMevents(ShowResults.getMevents() + 1);
		
		//Get the ants current node
		Node ant_currnode = ant.getLastNode();
		
		//Get the adjacents list of the current node (from the graph)
		List<Edge> adj_list = ant_currnode.getEdges();
		
		//create an auxiliar list (valid move nodes) which is the adjacents list except the intersection
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
									edge.setPheromones(edge.getPheromones() + (sim.getGraph().getW())/cycle_weight);
								}else{
									edge.setPheromones(edge.getPheromones() + (sim.getGraph().getW())/cycle_weight);
									Evaporation new_evaporation = new Evaporation(this.time, edge, sim);
									sim.getPec().addEvPEC(new_evaporation);
								}
							}
						}
					}

					ant.getPath().subList(ant.getPath().size()-1, ant.getPath().size()).clear();
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
	

}
