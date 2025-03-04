/**
 * The package colony contains the abstract generic class Colony, which represents a colony of generic elements, the subclass AntColony which
 * represents a colony of Ants and the class Ant.
 */
package colony;

/**
 * Abstract class for colonies of generic elements T.
 * The subclasses must parameterize T and must store a vector of elements.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 * @param <T> the generic elements stored in the colony.
 */

public abstract class Colony<T> {

	/**
	 * Getter for the elements field.
	 * @return the vector of generic elements.
	 */
	protected abstract T[] getElems();
	
	/**
	 * Setter for the elements field.
	 * @param elems the vector of generic elements.
	 */
	protected abstract void setElems(T[] elems);
	
	/**
	 * Method to add a new element to the elements field vector.
	 * @param elem the element to be added to the colony.
	 * @param pos the position, i.e., the index in the vector of elements where we want to add the new element.
	 */
	protected abstract void setElem(T elem, int pos);

}
