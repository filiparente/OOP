package pooproject;
import javax.xml.parsers.*;

import graph.*;
import event.*;
import simulation.*;
import utils.Utils;
import colony.*;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

//import static sun.java2d.cmm.ColorTransform.Simulation;


public class Main {
	
	public static void main(String[] args) {
	    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	    try {
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	        Parser handler = new Parser();
	        saxParser.parse(new File("tests/data1.xml"), handler);

	        //get simulation
			Simulation sim = handler.getSim();
	        
	        //Get colony
	        Colony c = sim.getColony();

	        //print number of ants in the colony: just to check if the parser is OK!
	        System.out.println("Number of ants in the colony: " + c.getAnts().length);
	        System.out.println("Antcolsize = " + sim.getAntcolsize());
	        
	        //print nodes list: just to check if the parser is OK!
	        for(Node n : sim.getGraph().getNodes())
	            System.out.println(n);
	        
	        Event currentEvent = null;
	        
	        //At the beginning, for each ant in the colony one new event must be added to the PEC: 1st ant move
	        for(Ant ant: c.getAnts()) {
		        currentEvent = new Move(0.0, ant , sim.getGraph(), sim.getPec() );
				sim.getPec().addEvPEC(currentEvent);
	        }
				
				
			double currentTime = 0.0;
			
			//simulation cycle
			while(true) {
				currentEvent = sim.getPec().nextEvPEC();
				currentTime = currentEvent.getTime();
				if(currentTime >= sim.getFinalinst()) {
					break;
				}
				currentEvent.SimulateEvent();
				System.out.printf("%f\n", currentTime);
			}

			System.out.println(sim.getColony().getAnts()[0].getShortest());
			System.out.println("finished");
	        
	        
	       
	    } catch (ParserConfigurationException | SAXException | IOException e) {
	    	//DO THIS PROPERLY (SEE SLIDES)!!!!!!!!!
	        e.printStackTrace();
	    }
	}
}
