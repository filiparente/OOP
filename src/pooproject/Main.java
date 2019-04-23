package pooproject;
import javax.xml.parsers.*;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import java.util.ArrayList;


public class Main {
	
	public static void main(String[] args) {
	    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	    try {
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	        Parser handler = new Parser();
	        saxParser.parse(new File("tests/data1.xml"), handler);
	        
	        //Get nodes List
	        ArrayList<Node> nodeList = handler.getNodeList();
	        
	        //print nodes list
	        for(Node n : nodeList)
	            System.out.println(n);
	    } catch (ParserConfigurationException | SAXException | IOException e) {
	        e.printStackTrace();
	    }
	}
}
