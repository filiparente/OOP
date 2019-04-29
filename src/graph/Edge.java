package graph;

public class Edge {

    int node1, node2;
    double cost, pheromones;

    public Edge(int node1, int node2, double cost, double pheromones) {
        this.node1 = node1;
        this.node2 = node2;
        this.cost = cost;
        this.pheromones = pheromones;
    }

    @Override
    public String toString() {
        /*return "Edge{" +
                "node1=" + node1 +
                ", node2=" + node2 +
                ", cost=" + cost +
                ", pheromones=" + pheromones +
                '}';*/

        return "" + pheromones;
    }

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Node node = (Node) obj;
		
		return this.node2 == node.index;
	}
	
	public int getAdj(Object obj) {
		// TODO Auto-generated method stub
		Node node = (Node) obj;
		
		return ((node.index == this.node1)? this.node2 : this.node1);
	}

	public int getNode1() {
		return node1;
	}

	public void setNode1(int node1) {
		this.node1 = node1;
	}

	public int getNode2() {
		return node2;
	}

	public void setNode2(int node2) {
		this.node2 = node2;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getPheromones() {
		return pheromones;
	}

	public void setPheromones(double pheromones) {
		this.pheromones = pheromones;
	}
	
}
