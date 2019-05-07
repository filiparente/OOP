package colony;

import java.util.LinkedList;
import java.util.List;

import graph.Graph;
import graph.Node;

/**
 * The Ant class represents ants that are created and used during a stochastic simulation execution.
 * The ants are responsible for traversing a weighted graph in order to find the cycle with no repeated nodes with the shortest weight possible.
 * Therefore, the implementation of Ant provided assumes that it is used in a problem using a graph represented by nodes, and the ants store the path they traverse in the graph
 * as a list of nodes.
 * 
 * @authors Filipa, Goncalo, Joana
 *
 */
public class Ant {
	
	/**
	 * The path traversed by the Ant as a list of Nodes.
	 */
	List<Node> path;
	
	int bitmask;
	
	/**
	 * The weight associated with the Ant's path.
	 */
	double weight;

	public int getBitmask() {
		return bitmask;
	}

	public void setBitmask(int bitmask) {
		this.bitmask = bitmask;
	}

	//int[] pathcheck;

	/**
	 * Constructor for an Ant.
	 * The path is set as a linked list of Nodes.
	 * The weight of the path is initialized with infinity.
	 */
	public Ant(int nbnode) {
		this.path = new LinkedList<Node>();
		this.weight = Double.POSITIVE_INFINITY;
		this.bitmask = 0b00;
		//this.pathcheck = new int[nbnode];
	}

	/**
	 * Getter for the path field.
	 * @return the ant's path.
	 */
	public List<Node> getPath() {
		return path;
	}
	
	/**
	 * Setter for the path field.
	 * @param path the ant's path.
	 */
	public void setPath(LinkedList<Node> path) {
		this.path = path;
	}
	
	/**
	 * Method to return last node visited by the ant.
	 * @return the last node visited by the ant.
	 */
	public Node getLastNode() {
		return this.path.get(this.path.size()-1);
	}
	
	/**
	 * Method to add a node to the ant's path.
	 * @param node the node to add to the ant's path.
	 */
	public void setNodePath(Node node) {
		this.path.add(node);
		//this.pathcheck[node.getIndex()-1] = 1;
	}
	
	/**
	 * Method to remove a given node from the ant's path.
	 * @param node the node to be removed from the ant's path.
	 * @return true if the node is present, false otherwise.
	 */
	public boolean removeNodePath(Node node) {
		if(this.path.remove(node)) {
			//this.pathcheck[node.getIndex() - 1] = 0;
			return true;
		}
		return false;
		//TODO: throw exception if the node is not found.
	}

	/**
	 * Getter for the weight field.
	 * @return the ant's path weight.
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Setter for the weight field.
	 * @param weight the ant's path weight.
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/*public int[] getPathcheck() {
		return pathcheck;
	}

	public void setPathcheck(int[] pathcheck) {
		this.pathcheck = pathcheck;
	}*/
}
