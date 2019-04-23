package pooproject;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Parser extends DefaultHandler {

	protected int antcolsize, nbnodes, nestnode, targetnode, edge_cost;
	protected double finalinst, plevel, alpha, beta, delta, eta, rho;
	protected Node node;
	
	
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

	public ArrayList<Node> getNodeList() {
		return nodeList;
	}

	public void setNodeList(ArrayList<Node> nodeList) {
		this.nodeList = nodeList;
	}

	boolean bWeight = false;


	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//super.startElement(uri, localName, qName, attributes);
		if (qName.equalsIgnoreCase("simulation")) {
			finalinst = Double.parseDouble(attributes.getValue("finalinst"));
			antcolsize = Integer.parseInt(attributes.getValue("antcolsize"));
			plevel = Double.parseDouble(attributes.getValue("plevel"));
		
		} else if (qName.equalsIgnoreCase("graph")) {
			nbnodes = Integer.parseInt(attributes.getValue("nbnodes"));
			nestnode = Integer.parseInt(attributes.getValue("nestnode"));
			
		} else if (qName.equalsIgnoreCase("node")) {
			String nodeidx = attributes.getValue("nodeidx");
			
			//create a new node and set id
			node = new Node(Integer.parseInt(nodeidx), null);
			
			// initialize list
			if (nodeList == null)
				nodeList = new ArrayList<Node>();
			if (node.adjList == null)
				node.adjList = new ArrayList<Adj>();
			
			nodeList.add(node);
			
		} else if (qName.equalsIgnoreCase("weight")) {
			targetnode = Integer.parseInt(attributes.getValue("targetnode"));
			bWeight = true;
		} else if (qName.equalsIgnoreCase("move")) {
			alpha = Double.parseDouble(attributes.getValue("alpha"));
			beta = Double.parseDouble(attributes.getValue("beta"));
			delta = Double.parseDouble(attributes.getValue("delta"));	
		} else if (qName.equalsIgnoreCase("evaporation")) {
			eta = Double.parseDouble(attributes.getValue("eta"));
			rho = Double.parseDouble(attributes.getValue("rho"));
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if(bWeight) {
			edge_cost = Integer.parseInt(new String(ch, start, length));
			Adj adj = new Adj(targetnode, edge_cost);
			node.adjList.add(adj);
			bWeight = false;
		}
	}
}


