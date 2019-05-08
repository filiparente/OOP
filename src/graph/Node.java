package graph;

import java.util.LinkedList;
import java.util.List;

/**
 * Class Node represents a node of the graph.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
public class Node {
	/**
	 * The index of the node.
	 */
    private int index;
    
    /**
     * The adjacent nodes of this node as a collection of edges.
     */
    private List<Edge> edges;

    /**
     * Constructor for the class Node.
     * 
     * @param index the index of the node.
     * 
     * Allocates memory for a linked list of edges.
     */
    public Node(int index) {
        this.index = index;
        this.edges = new LinkedList<Edge>();
    }
    
    /**
     * Override of the method toString() to print the index of the node and its adjacent nodes through its edges.
     */
    @Override
    public String toString() {
        return "" + index + ":" + edges;
    }

    /**
     * Getter for the index field.
     * @return the index of the node.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Setter for the index field.
     * @param index the index of the node.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Getter for the edges field.
     * @return the adjacent nodes of this node as a list of edges.
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * Method to add an edge to the list of edges of this node.
     * @param edge the edge to be added.
     */
    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }
}
