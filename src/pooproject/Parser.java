package pooproject;

import java.util.ArrayList;

import graph.*;
import pec.PEC;
import simulation.*;
import event.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import colony.Ant;
import colony.Colony;

public class Parser extends DefaultHandler {

	protected int antcolsize, nbnodes, nestnode, targetnode, edge_cost, nodeidx, W = 0;
	protected double finalinst, plevel, alpha, beta, delta, eta, rho;
	protected Node node;
	protected Graph graph;
	protected Colony col;
	protected Simulation sim;
	
	protected ArrayList<Node> nodeList = null;
	
	public int getAntcolsize() {
		return antcolsize;
	}

	public void setAntcolsize(int antcolsize) {
		this.antcolsize = antcolsize;
	}

	public int getNbnodes() {
		return nbnodes;
	}

	public void setNbnodes(int nbnodes) {
		this.nbnodes = nbnodes;
	}

	public int getNestnode() {
		return nestnode;
	}

	public void setNestnode(int nestnode) {
		this.nestnode = nestnode;
	}

	public int getTargetnode() {
		return targetnode;
	}

	public void setTargetnode(int targetnode) {
		this.targetnode = targetnode;
	}

	public int getEdge_cost() {
		return edge_cost;
	}

	public void setEdge_cost(int edge_cost) {
		this.edge_cost = edge_cost;
	}

	public double getFinalinst() {
		return finalinst;
	}

	public void setFinalinst(double finalinst) {
		this.finalinst = finalinst;
	}

	public double getPlevel() {
		return plevel;
	}

	public void setPlevel(double plevel) {
		this.plevel = plevel;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public double getEta() {
		return eta;
	}

	public void setEta(double eta) {
		this.eta = eta;
	}

	public double getRho() {
		return rho;
	}

	public void setRho(double rho) {
		this.rho = rho;
	}

	public Node[] getNodeList() {
		return graph.getNodes();
	}

	public void setNodeList(ArrayList<Node> nodeList) {
		this.nodeList = nodeList;
	}
	
	public Colony getColony() {
		return this.col;
	}

	public Simulation getSim() {return  this.sim; }

	public int getW() {
		return this.W;
	}

	boolean bWeight = false;


	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//super.startElement(uri, localName, qName, attributes);
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

			col = new Colony(antcolsize);
			
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

			graph = new Graph(nbnodes, nestnode);
			
			//create a new node and set id
			for (int i = 1; i<=nbnodes; i++) {
				node = new Node(i);
				graph.setNode(node, i);
			}
			
			for(int i=1; i<=antcolsize; i++) {
				Ant ant = new Ant();
				ant.setNodePath( graph.getNode(nestnode)); //add the nest node to the ants path
				col.setAnt(ant, i);
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

			//event = new Move(0.0, alpha, beta, delta);
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

			
			//event = new Evaporation(0.0, eta, rho);
			Evaporation.setEta(eta);
			Evaporation.setRho(rho);
		}

		sim = new Simulation(antcolsize, finalinst, plevel, new PEC(), col, graph);
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		sim.getGraph().setW(this.W * this.plevel);
	}

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


