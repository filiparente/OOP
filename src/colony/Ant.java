package colony;
import graph.*;
import java.util.*;


public class Ant {
	List<Node> path;

	public Ant() {
		this.path = new LinkedList<Node>();
		
	}

	public List<Node> getPath() {
		return path;
	}

	public void setPath(LinkedList<Node> path) {
		this.path = path;
	}
	
	public Node getLastNode() {
		//returns last node visited by the ant
		return this.path.get(this.path.size()-1); //done this way for lists
		//return this.path.getLast(); //done this way for linkedlists
	}
	
	public void setNodePath(Node node) {
		this.path.add(node);
	}
	
	public boolean removeNodePath(Node node) {
		return this.path.remove(node); //returns true if the node is present, false otherwise
		//TODO: CATCH ERROR IF RETURN GIVES FALSE, MEANS TRYING TO REMOVE A NODE THAT WAS NOT IN THE ANT'S PATH!!
	}
	
	

}
