/**
 * The package colony contains the abstract generic class Colony, which represents a colony of generic elements, the subclass AntColony which
 * represents a colony of Ants and the class Ant.
 */
package colony;

import java.util.LinkedList;
import java.util.List;

import graph.Node;

/**
 * The Ant class represents ants that are created and used during a stochastic simulation execution.
 * The ants are responsible for traversing a weighted graph in order to find the cycle with no repeated nodes with the shortest weight possible.
 * Therefore, the implementation of Ant provided assumes that it is used in a problem using a graph represented by nodes, and the ants store the path they traverse in the graph
 * as a list of nodes.
 * 
 * @author Filipa, Goncalo, Joana
 *
 */
public class Ant {
	
	/**
	 * The path traversed by the Ant as a list of Nodes.
	 */
	private List<Node> path;
	
	/**
	 * A vector of booleans that indicate which nodes of the graph were already visited by this ant.
	 */
	private boolean[] pathcheck;
	
	/**
	 * The weight associated with the Ant's path.
	 */
	private double weight;


	/**
	 * Constructor for an Ant.
	 * @param nbnode number of nodes of the graph.
	 * The path is set as a linked list of Nodes.
	 * The weight of the path is initialized with infinity.
	 * All nodes are initialized as non-visited.
	 */
	public Ant(int nbnode) {
		this.path = new LinkedList<Node>();
		this.weight = Double.POSITIVE_INFINITY;
		this.pathcheck = new boolean[nbnode];
		
		//initialize all zeros
		for(int i=0;i<nbnode;i++) {
			this.pathcheck[i] = false;
		}
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
	 * This method also checks this node as visited in the ant's pathcheck field.
	 * @param node the node to add to the ant's path.
	 */
	public void setNodePath(Node node) {
		this.path.add(node);
		this.pathcheck[node.getIndex()-1] = true;
	}
	
	/**
	 * Method to remove a given node from the ant's path.
	 * This method also checks this node as non-visited in the ant's pathcheck field.
	 * @param node the node to be removed from the ant's path.
	 * @return true if the node is present, false otherwise.
	 */
	public boolean removeNodePath(Node node) {
		if(this.path.remove(node)) {
			this.pathcheck[node.getIndex() - 1] = false;
			return true;
		}
		return false;
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

	/**
	 * Getter for the pathcheck field.
	 * @return the ant's pathcheck.
	 */
	public boolean[] getPathcheck() {
		return pathcheck;
	}
	
	/**
	 * Getter for one node in the pathcheck field.
	 * @param idx the index of the node.
	 * @return true if the element with that index was already visited by this ant; false, otherwise.
	 */
	public boolean getPathcheckOne(int idx) {
		return pathcheck[idx-1];
	}
	
	/**
	 * Setter for one node in the pathcheck field.
	 * @param idx the index of the node.
	 * @param value the value to set the node with.
	 */
	public void setPathcheck(int idx, boolean value) {
		this.pathcheck[idx-1] = value;
	}
}
