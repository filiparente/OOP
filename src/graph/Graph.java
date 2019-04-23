package graph;

public class Graph {

    int nbnode;
    int nestnode;
    Node[] nodes;

    public Graph(int nbnode, int nestnode) {
        this.nbnode = nbnode;
        this.nestnode = nestnode;
        this.nodes = new Node[nbnode];
    }

    public int getNbnode() {
        return nbnode;
    }

    public void setNbnode(int nbnode) {
        this.nbnode = nbnode;
    }

    public int getNestnode() {
        return nestnode;
    }

    public void setNestnode(int nestnode) {
        this.nestnode = nestnode;
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
}
