/**
 * The package graph contains the generic abstract class GenericGraph which represents a graph with generic elements.
 * The Graph class is a subclass of GenericGraph where the elements are Nodes from the Node class. The WGraph class is a subclass
 * of Graph where structures related to the weight of the edges are stored, such as the graph's shortest cycle.
 * The Node structure contain a list of edge objects, defined in the Edge class.
 */
package graph;

import java.util.Arrays;

/**
 * Graph class is a subclass of the abstract class GenericGraph by parameterizing the generic node T as a type Node class.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
 class Graph extends GenericGraph<Node>{
	/**
	 * Number of nodes in the graph.
	 */
    private int nbnode;
    
    /**
     * Index of the nest node.
     */
    private int nestnode;
    
    /**
     * Collection of all nodes of the graph as a vector.
     */
    private Node[] nodes;
    
    /**
     * Constructor for the Graph.
     * 
     * @param nbnode the number of nodes in the graph.
     * @param nestnode the index of the nest node.
     * 
     * Allocates memory for the nodes field with size equal to nbnode, the number of nodes in the graph.
     */
    public Graph(int nbnode, int nestnode) {
        this.nbnode = nbnode;
        this.nestnode = nestnode;
        this.nodes = new Node[nbnode];
    }
    
    /**
     * Getter for the nbnode field.
     * @return the number of nodes in the graph.
     */
	public int getNbnode() {
        return nbnode;
    }

	/**
	 * Setter for the nbnode field.
	 * @param nbnode the number of nodes in the graph.
	 */
    public void setNbnode(int nbnode) {
        this.nbnode = nbnode;
    }
    
    /**
     * Getter for the nestnode field.
     * @return the index of the nest node.
     */
    public int getNestnode() {
        return nestnode;
    }

    /**
     * Setter for the nestnode field.
     * @param nestnode the index of the nest node.
     */
    public void setNestnode(int nestnode) {
        this.nestnode = nestnode;
    }
    
    /**
     * Getter for the nodes field.
     * @return the vector containing all nodes of the graph.
     */
	public Node[] getNodes() {
        return nodes;
    }

	/**
	 * Setter for the nodes field.
	 * @param nodes the vector containing all nodes of the graph.
	 */
	 public void setNodes(Node[] nodes) {
			this.nodes = nodes;
	}
	 
	/**
	 * Getter for a specific node of the nodes field.
	 * @param idx the index of the node.
	 */
    public Node getNode(int idx){
        return this.nodes[idx-1];
    }
    
    /**
     * Setter for a specific node in the nodes field.
     * @param node the node to add.
     * @param idx the index of the node.
     */
    public void setNode(Node node, int idx) {
        this.nodes[idx-1] = node;
    }
    
    /**
     * Override of the method toString() that prints the graph as a collection of nodes.
     */
    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + Arrays.toString(nodes) +
                '}';
    }
}
