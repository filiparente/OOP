package colony;

/**
 * The Colony class that represents a colony of Ants and implements the interface IColony T.
 * The generic element T from the interface is parameterized by the Ant class.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
public class Colony implements IColony<Ant> {
	
	/**
	 * The collection of Ants as a vector.
	 */
	private Ant[] ants;


	/**
	 * Constructor for a Colony of Ants.
	 * @param size the size of the Colony, i.e., the number of Ants.
	 * It allocates memory for the ants field as a vector of #size Ants.
	 */
	public Colony(int size) {
		this.ants = new Ant[size];
	}

	/**
	 * Getter for the ants field.
	 * @return ants the vector of Ants.
	 */
	public Ant[] getElems() {
		return ants;
	}
	
	
	/**
	 * Setter for the ants field.
	 * @param ants the vector of Ants.
	 */
	public void setElems(Ant[] ants) {
		this.ants = ants;
	}
	
	/**
	 * Method to add a new Ant to the ants field vector.
	 * @param ant the Ant to be added.
	 * @param pos the position, i.e., the index in the vector of ants where we want to add the new Ant.
	 */
	public void setElem(Ant ant, int pos){
		this.ants[pos-1] = ant;
	}


}
