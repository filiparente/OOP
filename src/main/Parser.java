package main;

import java.util.ArrayList;

import pec.PEC;
import simulation.HamiltonianSimulation;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import colony.Ant;
import colony.AntColony;
import event.Evaporation;
import event.Move;
import graph.*;

/**
 * Parser class is a subclass of the DefaultHandler abstract class provided by the org.xml.sax.helpers library.
 * 
 * The class is responsible for parsing a xml file and validating its structure against a provided dtd.
 * The information is stored in the respective data structures.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
public class Parser extends DefaultHandler {
	/**
	 * antcolsize: size of the colony of ants.
	 * nbnodes: number of nodes of the graph.
	 * nestnode: index of the nest node of the graph.
	 * targetnode: index of a node belonging to an edge.
	 * edge_cost: cost of the respective edge.
	 * nodeidx: index of the other node of the edge.
	 * W: total weight of the graph as the sum of all edge costs.
	 */
	private int antcolsize, nbnodes, nestnode, targetnode, edge_cost, nodeidx, W = 0;
	
	/**
	 * finalinst: final instant of the simulation.
	 * plevel: reduction rate of the pheromone level of the edges.
	 * alpha, beta, delta: parameters of the move events.
	 * eta, rho: parameters of the evaporation events.
	 */
	private double finalinst, plevel, alpha, beta, delta, eta, rho;
	
	/**
	 * Current node.
	 */
	private Node node;
	
	/**
	 * Graph of the simulation.
	 */
	private WGraph graph;
	
	/**
	 * Colony of ants of the simulation.
	 */
	private AntColony col;
	
	/**
	 * Hamiltonian stochastic simulation.
	 */
	private HamiltonianSimulation sim;
	
	/**
	 * List of the nodes of the graph, initialized to null.
	 */
	protected ArrayList<Node> nodeList = null;
	
	/**
	 * Getter for the antcolsize field.
	 * @return the size of the colony of ants.
	 */
	public int getAntcolsize() {
		return antcolsize;
	}
	
	/**
	 * Setter for the antcolsize field.
	 * @param antcolsize size of the colony of ants.
	 */
	public void setAntcolsize(int antcolsize) {
		this.antcolsize = antcolsize;
	}

	/**
	 * Getter for the nbnodes field.
	 * @return the number of nodes in the graph.
	 */
	public int getNbnodes() {
		return nbnodes;
	}

	/**
	 * Setter for the nbnodes field.
	 * @param nbnodes number of nodes in the graph.
	 */
	public void setNbnodes(int nbnodes) {
		this.nbnodes = nbnodes;
	}

	/**
	 * Getter for the nestnode field.
	 * @return the index of the nest node.
	 */
	public int getNestnode() {
		return nestnode;
	}
	
	/**
	 * Setter for the nestnode field.
	 * @param nestnode index of the nest node.
	 */
	public void setNestnode(int nestnode) {
		this.nestnode = nestnode;
	}

	/**
	 * Getter for the targetnode field.
	 * @return index of the target node.
	 */
	public int getTargetnode() {
		return targetnode;
	}

	/**
	 * Setter for the targetnode field.
	 * @param targetnode index of the target node.
	 */
	public void setTargetnode(int targetnode) {
		this.targetnode = targetnode;
	}

	/**
	 * Getter for the edge_cost field.
	 * @return the cost of the respective edge.
	 */
	public int getEdge_cost() {
		return edge_cost;
	}

	/**
	 * Setter for the edge_cost field.
	 * @param edge_cost cost of the respective edge.
	 */
	public void setEdge_cost(int edge_cost) {
		this.edge_cost = edge_cost;
	}

	/**
	 * Getter for the finalinst field.
	 * @return the final instant of the simulation.
	 */
	public double getFinalinst() {
		return finalinst;
	}

	/**
	 * Setter for the finalinst field.
	 * @param finalinst final instant of the simulation.
	 */
	public void setFinalinst(double finalinst) {
		this.finalinst = finalinst;
	}

	/**
	 * Getter for the plevel field.
	 * @return reduction rate of the pheromone level of the edges.
	 */
	public double getPlevel() {
		return plevel;
	}

	/**
	 * Setter for the plevel field.
	 * @param plevel reduction rate of the pheromone level of the edges.
	 */
	public void setPlevel(double plevel) {
		this.plevel = plevel;
	}
	
	/**
	 * Getter for the alpha field.
	 * @return alpha.
	 */
	public double getAlpha() {
		return alpha;
	}

	/**
	 * Setter for the alpha field.
	 * @param alpha alpha.
	 */
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	/**
	 * Getter for the beta field.
	 * @return beta.
	 */
	public double getBeta() {
		return beta;
	}

	/**
	 * Setter for the beta field.
	 * @param beta beta.
	 */
	public void setBeta(double beta) {
		this.beta = beta;
	}

	/**
	 * Getter for the delta field.
	 * @return delta.
	 */
	public double getDelta() {
		return delta;
	}

	/**
	 * Setter for the delta field.
	 * @param delta delta.
	 */
	public void setDelta(double delta) {
		this.delta = delta;
	}

	/**
	 * Getter for the eta field.
	 * @return eta.
	 */
	public double getEta() {
		return eta;
	}

	/**
	 * Setter for the eta field.
	 * @param eta eta.
	 */
	public void setEta(double eta) {
		this.eta = eta;
	}

	/**
	 * Getter for the rho field.
	 * @return rho.
	 */
	public double getRho() {
		return rho;
	}

	/**
	 * Setter for the rho field.
	 * @param rho rho.
	 */
	public void setRho(double rho) {
		this.rho = rho;
	}

	/**
	 * Getter for the vector of nodes of the graph.
	 * @return vector of nodes of the graph.
	 */
	public Node[] getNodeList() {
		return graph.getNodes();
	}

	/**
	 * Setter for the nodeList.
	 * @param nodeList list of nodes.
	 */
	public void setNodeList(ArrayList<Node> nodeList) {
		this.nodeList = nodeList;
	}
	
	/**
	 * Getter for the col field.
	 * @return the colony.
	 */
	public AntColony getColony() {
		return this.col;
	}

	/**
	 * Getter for the sim field.
	 * @return the hamiltonian stochastic simulation.
	 */
	public HamiltonianSimulation getSim() {return  this.sim; }

	/**
	 * Getter for the W field.
	 * @return W.
	 */
	public int getW() {
		return this.W;
	}

	/**
	 * Flag to read the weight of an edge.
	 */
	boolean bWeight = false;


	/**
	 * Override of the method startElement.
	 * For the element simulation, the attributes finalinst, antcolsize and plevel are read. The colony is promptly allocated as a colony with antcolsize ants.
	 * For the element graph, the attributes nbnodes and nestnode are read. The weighted graph is promptly created. The nodes with indexes from 1 up to nbnodes are created
	 * and added to the graph. The ants of the colony are created, the nest node is added as visited to the ant's path and finally the ant is added to the colony.
	 * For the element node, the attribute nodeidx is read and the node is created and added to the nodeList.
	 * For the element weight, the attribute targetnode is read and the flag bWeight is triggered so that the weight of the edge from nodeidx to target node can be read in the characters method.
	 * For the element move, the attributes alpha, beta and delta are read and the respective static fields of the Move class are assigned.
	 * For the element evaporation, the attributes eta and rho are read and the respective static fields of the Evaporation class are assigned.
	 * 
	 * The program ends if an error is found.
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("simulation")) {
			if((finalinst = Double.parseDouble(attributes.getValue("finalinst"))) <= 0){
				System.out.println("Input finalinst value needs to be bigger than 0!");
				System.exit(1);
			}

			if((antcolsize = Integer.parseInt(attributes.getValue("antcolsize"))) <= 0){
				System.out.println("Input antcolsize value needs to be bigger than 0!");
				System.exit(1);
			}

			if((plevel = Double.parseDouble(attributes.getValue("plevel"))) <= 0){
				System.out.println("Input plevel value needs to be bigger than 0!");
				System.exit(1);
			}

			col = new AntColony(antcolsize);
			
		} else if (qName.equalsIgnoreCase("graph")) {

			if((nbnodes = Integer.parseInt(attributes.getValue("nbnodes"))) <= 0){
				System.out.println("Input nbnodes value needs to be bigger than 0!");
				System.exit(1);
			}

			nestnode = Integer.parseInt(attributes.getValue("nestnode"));
			if(nestnode <= 0 || nestnode > nbnodes ){
				System.out.println("Input nestnode value needs to be a node of the graph!");
				System.exit(1);
			}

			graph = new WGraph(nbnodes, nestnode);
			
			//create a new node and set id
			for (int i = 1; i<=nbnodes; i++) {
				node = new Node(i);
				graph.setNode(node, i);
			}
			
			for(int i=1; i<=antcolsize; i++) {
				Ant ant = new Ant(nbnodes);
				ant.setNodePath( graph.getNode(nestnode)); //add the nest node to the ants path
				ant.setPathcheck(nestnode, true);
				col.setElem(ant, i);
			}
			
		} else if (qName.equalsIgnoreCase("node")) {
			nodeidx = Integer.parseInt(attributes.getValue("nodeidx"));
			if(nodeidx <= 0 || nodeidx > nbnodes ){
				System.out.println("Input nodeidx (" + nodeidx + ") doesn't belong to the graph!");
				System.exit(1);
			}

			// initialize list
			if (nodeList == null)
				nodeList = new ArrayList<Node>();
			
			nodeList.add(node);
			
		} else if (qName.equalsIgnoreCase("weight")) {
			targetnode = Integer.parseInt(attributes.getValue("targetnode"));
			if(targetnode <= 0 || targetnode > nbnodes ){
				System.out.println("Input targetnode (" + targetnode + ") doesn't belong to the graph!");
				System.exit(1);
			}
			bWeight = true;
		} else if (qName.equalsIgnoreCase("move")) {

			if((alpha = Double.parseDouble(attributes.getValue("alpha"))) <= 0){
				System.out.println("Input alpha value needs to be bigger than 0!");
				System.exit(1);
			}

			if((beta = Double.parseDouble(attributes.getValue("beta"))) <= 0){
				System.out.println("Input beta value needs to be bigger than 0!");
				System.exit(1);
			}

			if((delta = Double.parseDouble(attributes.getValue("delta"))) <= 0){
				System.out.println("Input delta value needs to be bigger than 0!");
				System.exit(1);
			}

			Move.setAlpha(alpha);
			Move.setBeta(beta);
			Move.setDelta(delta);
			
			
		} else if (qName.equalsIgnoreCase("evaporation")) {

			if((eta = Double.parseDouble(attributes.getValue("eta"))) <= 0){
				System.out.println("Input eta value needs to be bigger than 0!");
				System.exit(1);
			}

			if((rho = Double.parseDouble(attributes.getValue("rho"))) <= 0){
				System.out.println("Input rho value needs to be bigger than 0!");
				System.exit(1);
			}

			
			Evaporation.setEta(eta);
			Evaporation.setRho(rho);
		}
	}

	/**
	 * Override of the method endDocument.
	 * In the end, the simulation is created. The weight of the graph is set as the product between W (sum of the cost of all edges constituting the graph) and plevel
	 * because the weight is always used in this context.
	 */
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		sim = new HamiltonianSimulation(antcolsize, finalinst, plevel, new PEC(), col, graph);
		sim.getGraph().setW(this.W * this.plevel);
	}

	/**
	 * Override of the method characters.
	 * If the flag to read the weight of an edge is triggered, the edge cost is read, the edge is finally created and added to
	 * the adjacents list of the target node (with index targetnode) and the original node (with index nodeidx). The total weight
	 * of the graph, W, is incremented by the weight of this edge. The flag is deactivated. 
	 */
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if(bWeight) {
			edge_cost = Integer.parseInt(new String(ch, start, length));
			Edge edge = new Edge(nodeidx, targetnode, edge_cost, 0.0);
			graph.getNode(nodeidx).addEdge(edge);
			graph.getNode(targetnode).addEdge(edge);
			
			this.W += edge_cost;
			bWeight = false;
		}
	}
}


