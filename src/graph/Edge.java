package graph;

public class Edge {

    int node1, node2, cost;
    double pheromones;

    public Edge(int node1, int node2, int cost, double pheromones) {
        this.node1 = node1;
        this.node2 = node2;
        this.cost = cost;
        this.pheromones = pheromones;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "node1=" + node1 +
                ", node2=" + node2 +
                ", cost=" + cost +
                ", pheromones=" + pheromones +
                '}';
    }
}
