package colony;

/**
 * The Colony class represents a colony of Ants.
 * 
 * @authors Filipa, Goncalo, Joana
 *
 */
public class Colony {
	
	/**
	 * The collection of Ants as a vector.
	 */
	Ant[] ants;

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
	public Ant[] getAnts() {
		return ants;
	}
	
	/**
	 * Setter for the ants field.
	 * @param ants the vector of Ants.
	 */
	public void setAnts(Ant[] ants) {
		this.ants = ants;
	}
	
	/**
	 * Method to add a new Ant to the ants field vector.
	 * @param ant the Ant to be added.
	 * @param pos the position, i.e., the index in the vector of ants where we want to add the new Ant.
	 */
	public void setAnt(Ant ant, int pos){
		this.ants[pos-1] = ant;
	}


}
