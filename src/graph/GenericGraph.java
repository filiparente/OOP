package graph;

/**
 * Abstract class for the graph with generic nodes T.
 * The subclasses should parameterize T. 
 * Assumes that implementation of the graph will provide a vector of nodes.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 * @param <T> generic object T
 */
public abstract class GenericGraph<T> {
	/**
	 * Requires the implementation of the getter for the number of nodes in the graph.
	 * @return number of nodes in the graph.
	 */
	public abstract int getNbnode(); 

	/**
	 * Requires the implementation of the setter for the number of nodes in the graph.
	 * @param nbnode number of nodes.
	 */
    public abstract void setNbnode(int nbnode);
    
    /**
	 * Requires the implementation of the getter for the index of the nest node of the graph.
	 * @return index of the nest node.
	 */
    public abstract int getNestnode();

    /**
	 * Requires the implementation of the setter for the index of the nest node of the graph.
	 * @param nestnode index of the nest node.
	 */
    public abstract void setNestnode(int nestnode);

    /**
	 * Requires the implementation of the getter for all nodes in the graph.
	 * @return graph nodes.
	 */
	public abstract T[] getNodes();

	/**
	 * Requires the implementation of the getter for a given node in the graph.
	 * @param idx node index.
	 * @return graph node.
	 */
    public abstract T getNode(int idx);
    
    /**
	 * Requires the implementation of the setter for a given node in the graph.
	 * @param node node.
	 * @param idx node index.
	 */
    public abstract void setNode(T node, int idx);

}
