package graph;

import java.util.LinkedList;
import java.util.List;

public class Node {

    int index;
    List<Edge> edges;

    public Node(int index) {
        this.index = index;
        this.edges = new LinkedList<Edge>();
    }

    @Override
    public String toString() {
        return "Node{" +
                "index=" + index +
                ", edges=" + edges +
                '}';
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }
}
