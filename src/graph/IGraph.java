package graph;

/**
 * Interface for the graph with generic nodes <T>.
 * The implementation should parameterize <T>. 
 * Assumes that implementation of the graph will provide a vector of nodes.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 * @param <T>
 */
public interface IGraph<T> {
	/**
	 * Requires the implementation of the getter for the number of nodes in the graph.
	 */
	public abstract int getNbnode(); 

	/**
	 * Requires the implementation of the setter for the number of nodes in the graph.
	 */
    public abstract void setNbnode(int nbnode);
    
    /**
	 * Requires the implementation of the getter for the index of the nest node of the graph.
	 */
    public abstract int getNestnode();

    /**
	 * Requires the implementation of the setter for the index of the nest node of the graph.
	 */
    public abstract void setNestnode(int nestnode);

    /**
	 * Requires the implementation of the getter for all nodes in the graph.
	 */
	public abstract T[] getNodes();

	/**
	 * Requires the implementation of the getter for a given node in the graph.
	 */
    public abstract T getNode(int idx);
    
    /**
	 * Requires the implementation of the setter for a given node in the graph.
	 */
    public void setNode(T node, int idx);

}
