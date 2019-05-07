package simulation;

import event.Event;
import event.Move;
import event.ShowResults;
import graph.Graph;
import pec.PEC;

import org.xml.sax.SAXException;

import colony.*;
import pooproject.Parser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Simulation {
	
	int antcolsize;
	double finalinst;
	double plevel;
	PEC pec;
	Colony colony;
    Graph graph;
	
	public Simulation(int antcolsize, double finalinst, double plevel, PEC pec, Colony colony, Graph graph) {
		this.antcolsize = antcolsize;
		this.finalinst = finalinst;
		this.plevel = plevel;
		this.pec = pec;
		this.colony = colony;
		this.graph = graph;
	}

	public static void main(String[] args) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			Parser handler = new Parser();
			saxParser.parse(new File(args[0]), handler);

			//get simulation
			Simulation sim = handler.getSim();

			//Get colony
			Colony c = sim.getColony();

			//print number of ants in the colony: just to check if the parser is OK!
			//System.out.println("Number of ants in the colony: " + c.getAnts().length);
			//System.out.println("Antcolsize = " + sim.getAntcolsize());

			//print nodes list: just to check if the parser is OK!
			//for(Node n : sim.getGraph().getNodes())
			//  System.out.println(n);

			int x = 1;
	        byte a = (byte) (x<<1);
	        
	        System.out.println("Binary value: "+Integer.toBinaryString(a));
	        
	        byte b = (byte) (x<<3);
	        System.out.println("Binary value: "+Integer.toBinaryString(a|b));
	        
	        //check if position 3 is visited
	        byte visited = (byte) (a|b);
	        byte c3 = (byte) ((byte) (1<<3) & visited);
	        System.out.println(c3==0? "Position 3 not visited yet!" : "Already visited position 3!");
	        
	        
			  
			Event currentEvent = null;

			//At the beginning, for each ant in the colony one new event must be added to the PEC: 1st ant move
			for(Ant ant: c.getAnts()) {
				currentEvent = new Move(0.0, ant , sim.getGraph(), sim.getPec() );
				sim.getPec().addEvPEC(currentEvent);
			}

			sim.getPec().addEvPEC(new ShowResults(sim.getFinalinst()/20, sim.getGraph(), sim.getPec(),sim.getColony(), sim.getFinalinst()));


			double currentTime = 0.0;

			//simulation cycle
			while(true) {
				currentEvent = sim.getPec().nextEvPEC();
				currentTime = currentEvent.getTime();
				if(currentTime > sim.getFinalinst()) {
					break;
				}
				currentEvent.SimulateEvent();
				//System.out.printf("%f\n", currentTime);
			}

			//System.out.println(sim.getGraph());
			//System.out.println(sim.getColony().getAnts()[0].getShortest());
			//System.out.println("finished");



		} catch (ParserConfigurationException | SAXException | IOException e) {
			//DO THIS PROPERLY (SEE SLIDES)!!!!!!!!!
			e.printStackTrace();
		}
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public int getAntcolsize() {
		return antcolsize;
	}

	public void setAntcolsize(int antcolsize) {
		this.antcolsize = antcolsize;
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

	public PEC getPec() {
		return pec;
	}

	public void setPec(PEC pec) {
		this.pec = pec;
	}

	public Colony getColony() {
		return colony;
	}

	public void setColony(Colony colony) {
		this.colony = colony;
	}
}
