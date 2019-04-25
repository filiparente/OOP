package pooproject;
import javax.xml.parsers.*;

import graph.*;
import event.*;
import utils.Utils;
import colony.*;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;



public class Main {
	
	public static void main(String[] args) {
	    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	    try {
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	        Parser handler = new Parser();
	        saxParser.parse(new File("..\\tests\\data1.xml"), handler);
	        
	        //Get nodes List
	        Node[] nodeList = handler.getNodeList();
	        
	        //Get colony
	        Colony c = handler.getColony();

	        //print number of ants in the colony: just to check if the parser is OK!
	        System.out.println("Number of ants in the colony: " + c.getAnts().length);
	        
	        //print nodes list: just to check if the parser is OK!
	        for(Node n : nodeList)
	            System.out.println(n);
	        
	        Event currentEvent = null;
	        
	        //At the beginning, for each ant in the colony one new event must be added to the PEC: 1st ant move
	        for(Ant ant: c.getAnts()) {
		        currentEvent = new Move(0.0, ant);
				c.getPec().addEvPEC(currentEvent);
	        }
				
				
			double currentTime = 0.0;
			
			//simulation cycle
			while(currentTime < c.getFinalinst()) {
				currentEvent = c.getPec().nextEvPEC();
				currentTime = currentEvent.getTime();
				currentEvent.SimulateEvent();
				System.out.printf("%f\n", currentTime);
			}
			
			System.out.println("finished");
	        
	        
	       
	    } catch (ParserConfigurationException | SAXException | IOException e) {
	    	//DO THIS PROPERLY (SEE SLIDES)!!!!!!!!!
	        e.printStackTrace();
	    }
	}
}
