package pooproject;
import java.util.ArrayList;

public class Node {
	protected int nodeidx;
	protected ArrayList<Adj> adjList;
	
	public Node(int nodeidx, ArrayList<Adj> adjList) {
		super();
		this.nodeidx = nodeidx;
		this.adjList = adjList;
	}

	@Override
	public String toString() {
		String str = " ";
		for(Adj adj: adjList) {
			str += adj.toString()+ " ; ";
		}
		
		return nodeidx + str;
		
	}
	
	
}
