/**
 * The package graph contains the generic abstract class GenericGraph which represents a graph with generic elements.
 * The Graph class is a subclass of GenericGraph where the elements are Nodes from the Node class. The WGraph class is a subclass
 * of Graph where structures related to the weight of the edges are stored, such as the graph's shortest cycle.
 * The Node structure contain a list of edge objects, defined in the Edge class.
 */
package graph;

/**
 * Class Edge represents an edge of the weighted graph, belonging to the WGraph class.
 * @author Filipa, Goncalo, Joana.
 *
 */
public class Edge {
	/**
	 * Index of the the two nodes that delimit an edge.
	 * Since the graph is undirected, the edges have no direction and node1 and node2 are interchangeable.
	 */
    private int node1, node2;
    
    /**
     * Cost of the edge and level of pheromones of the edge.
     * Cost is fixed. The level of pheromons changes over time during the hamiltonian stochastic simulation.
     */
    private double cost, pheromones;

    /**
     * Constructor for the Edge class.
     * 
     * @param node1 index of one of the nodes of the edge.
     * @param node2 index of the other node of the edge.
     * @param cost cost of the edge.
     * @param pheromones pheromone level of the edge.
     */
    public Edge(int node1, int node2, double cost, double pheromones) {
        this.node1 = node1;
        this.node2 = node2;
        this.cost = cost;
        this.pheromones = pheromones;
    }

    /**
     * Override of the toString() method to print an edge with the index of the two nodes that constitute
     * the edge, its cost and its pheromone level.
     */
    @Override
    public String toString() {
        return "Edge{" +
                "node1=" + node1 +
                ", node2=" + node2 +
                ", cost=" + cost +
                ", pheromones=" + pheromones +
                '}';
    }

    /**
     * Method to get the other node of this edge.	
     * @param obj one of the nodes of this edge.
     * @return the index of the other node of this edge.
     */
	public int getAdj(Object obj) {
		Node node = (Node) obj;
		
		return ((node.getIndex() == this.node1)? this.node2 : this.node1);
	}

	/**
	 * Getter for the node1 field.
	 * @return the index of one of the nodes of the edge.
	 */
	public int getNode1() {
		return node1;
	}

	/**
	 * Setter for the node1 field.
	 * @param node1 the index of one of the nodes of the edge.
	 */
	public void setNode1(int node1) {
		this.node1 = node1;
	}

	/**
	 * Getter for the node2 field.
	 * @return the index of one of the nodes of the edge.
	 */
	public int getNode2() {
		return node2;
	}
	
	/**
	 * Setter for the node2 field.
	 * @param node2 the index of one of the nodes of the edge.
	 */
	public void setNode2(int node2) {
		this.node2 = node2;
	}

	/**
	 * Getter for the cost field.
	 * @return the cost of the edge.
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * Setter for the cost field.
	 * @param cost cost of the edge.
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * Getter for the pheromones field.
	 * @return level of pheromones of the edge.
	 */
	public double getPheromones() {
		return pheromones;
	}

	/**
	 * Setter for the pheromones field.
	 * @param pheromones level of pheromones of the edge.
	 */
	public void setPheromones(double pheromones) {
		this.pheromones = pheromones;
	}
	
}
