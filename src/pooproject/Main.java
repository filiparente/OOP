package pooproject;
import javax.xml.parsers.*;

import graph.*;
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
	        
	        
	       
	    } catch (ParserConfigurationException | SAXException | IOException e) {
	    	//DO THIS PROPERLY (SEE SLIDES)!!!!!!!!!
	        e.printStackTrace();
	    }
	}
}
