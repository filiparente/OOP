package colony;

public class Colony {

	Ant[] ants;

	public Colony(int size) {
		this.ants = new Ant[size];
	}

	public Ant[] getAnts() {
		return ants;
	}

	public void setAnts(Ant[] ants) {
		this.ants = ants;
	}

	public void setAnt(Ant ant, int pos){
		this.ants[pos-1] = ant;
	}


}
