/**
 * The package graph contains the generic abstract class GenericGraph which represents a graph with generic elements.
 * The Graph class is a subclass of GenericGraph where the elements are Nodes from the Node class. The WGraph class is a subclass
 * of Graph where structures related to the weight of the edges are stored, such as the graph's shortest cycle.
 * The Node structure contain a list of edge objects, defined in the Edge class.
 */
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
 abstract class GenericGraph<T> {
	/**
	 * Requires the implementation of the getter for the number of nodes in the graph.
	 * @return number of nodes in the graph.
	 */
	protected abstract int getNbnode(); 

	/**
	 * Requires the implementation of the setter for the number of nodes in the graph.
	 * @param nbnode number of nodes.
	 */
    protected abstract void setNbnode(int nbnode);
    
    /**
	 * Requires the implementation of the getter for the index of the nest node of the graph.
	 * @return index of the nest node.
	 */
    protected abstract int getNestnode();

    /**
	 * Requires the implementation of the setter for the index of the nest node of the graph.
	 * @param nestnode index of the nest node.
	 */
    protected abstract void setNestnode(int nestnode);

    /**
	 * Requires the implementation of the getter for all nodes in the graph.
	 * @return graph nodes.
	 */
	protected abstract T[] getNodes();

	/**
	 * Requires the implementation of the getter for a given node in the graph.
	 * @param idx node index.
	 * @return graph node.
	 */
    protected abstract T getNode(int idx);
    
    /**
	 * Requires the implementation of the setter for a given node in the graph.
	 * @param node node.
	 * @param idx node index.
	 */
    protected abstract void setNode(T node, int idx);

}
