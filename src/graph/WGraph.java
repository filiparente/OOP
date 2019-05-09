/**
 * The package graph contains the generic abstract class GenericGraph which represents a graph with generic elements.
 * The Graph class is a subclass of GenericGraph where the elements are Nodes from the Node class. The WGraph class is a subclass
 * of Graph where structures related to the weight of the edges are stored, such as the graph's shortest cycle.
 * The Node structure contain a list of edge objects, defined in the Edge class.
 */
package graph;

import java.util.LinkedList;
import java.util.List;

/**
 * Weighted Graph class is a subclass of Graph where the weights of the
 * edges are used to define the shortest path of a graph and its corresponding weight.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
public class WGraph extends Graph{
	/**
	 * Total weight of a graph defined as the sum of the weights (costs) of all edges constituting the graph.
	 */
    private double W;
    
    /**
     * Path with the lowest weight defined as a ordered list nodes.
     */
    private List<Node> shortest_path;
    
    /**
     * Weight (cost) of the corresponding shortest path, as the sum of the weights (costs) of all edges constituting the shortest path.
     */
    private double shortest_path_weight;

    /**
     * Constructor for the WGraph class.
     * 
     * @param nbnode the number of nodes of the graph.
     * @param nestnode the index of the nest node.
     * 
     * Allocates memory for the shortest path as a linked list of Nodes.
     * Initializes the weight of the shortest path as infinity.
     */
    public WGraph(int nbnode, int nestnode) {
    	super(nbnode, nestnode);
        this.shortest_path = new LinkedList<Node>();
        this.shortest_path_weight = Double.POSITIVE_INFINITY;
    }

    /**
     * Setter for the shortest_path_weight field.
     * @param shortest_path_weight the weight of the shortest path found so far.
     */
	public void setShortest_path_weight(double shortest_path_weight) {
		this.shortest_path_weight = shortest_path_weight;
	}
	
	/**
	 * Getter for the shortest_path_weight field.
	 * @return the weight of the shortest path found so far.
	 */
	public double getShortest_path_weight() {
		return shortest_path_weight;
	}
	
	/**
	 * Setter for the W field.
	 * @param W the total weight of the graph.
	 */
	public void setW(double W) {
    	this.W = W;
    }
    
	/**
	 * Getter for the W field.
	 * @return the total weight of the graph.
	 */
    public double getW() {
    	return this.W;
    }

    /**
     * Getter for the shortest_path field.
     * @return the shortest path as a list of Nodes.
     */
    public List<Node> getShortest_path() {
		return shortest_path;
	}
    
    /**
     * Setter for the shortest_path field. 
     * @param shortest_path the shortest path as a list of Nodes is copied into the field.
     */
	public void setShortest_path(List<Node> shortest_path) {
		this.shortest_path = List.copyOf(shortest_path);
	}
}
