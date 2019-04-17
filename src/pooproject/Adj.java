package pooproject;

public class Adj {
	protected int nodeidx;
	protected int cost_edge;
	
	public Adj(int nodeidx, int cost_edge) {
		super();
		this.nodeidx = nodeidx;
		this.cost_edge = cost_edge;
	}

	@Override
	public String toString() {
		return "id: " + nodeidx + "; cost: " + cost_edge;
		
	}
	
	
	
}
