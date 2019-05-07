package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    int nbnode;
    int nestnode;
    double W;
    List<Node> shortest_path;
    double shortest_path_weight;
    Node[] nodes;

    public Graph(int nbnode, int nestnode) {
        this.nbnode = nbnode;
        this.nestnode = nestnode;
        this.nodes = new Node[nbnode];
        this.shortest_path = new LinkedList<Node>();
        this.shortest_path_weight = Double.POSITIVE_INFINITY;
    }

    public int getNbnode() {
        return nbnode;
    }

    public void setNbnode(int nbnode) {
        this.nbnode = nbnode;
    }
    
    public double getShortest_path_weight() {
		return shortest_path_weight;
	}

	public void setShortest_path_weight(double shortest_path_weight) {
		this.shortest_path_weight = shortest_path_weight;
	}

	public void setW(double W) {
    	this.W = W;
    }
    
    public double getW() {
    	return this.W;
    }

    public int getNestnode() {
        return nestnode;
    }

    public void setNestnode(int nestnode) {
        this.nestnode = nestnode;
    }

    public List<Node> getShortest_path() {
		return shortest_path;
	}
    
	public void setShortest_path(List<Node> shortest_path) {
		this.shortest_path = List.copyOf(shortest_path);
	}

	public Node[] getNodes() {
        return nodes;
    }

    public Node getNode(int idx){
        return this.nodes[idx-1];
    }

    public void setNode(Node node, int idx) {
        this.nodes[idx-1] = node;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + Arrays.toString(nodes) +
                '}';
    }
}
