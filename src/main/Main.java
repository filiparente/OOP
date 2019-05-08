package main;

import java.io.File;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import simulation.HamiltonianSimulation;

/**
 * Main class contains only the method main which parses the xml file received as argument 
 * and runs the Hamiltonian stochastic simulation.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
public class Main {
	public static void main(String[] args) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			try {
				try {
						SAXParser saxParser = saxParserFactory.newSAXParser();
						Parser handler = new Parser();
						saxParser.parse(new File(args[0]), handler);
	
						//get simulation from the parser
						HamiltonianSimulation sim = handler.getSim();
			
						//run the simulation
						sim.run();	
	
					} catch (ParserConfigurationException e) {
						
						System.out.println("XML DOESN'T COMBINE WITH DTD. TERMINATING PROGRAM");
						System.exit(2);
						
					}
				} catch (SAXException e) {
					
					System.out.println("WRONG XML FORMAT. TERMINATING PROGRAM");
					System.exit(2);
					
				}
			} catch (IOException | ArrayIndexOutOfBoundsException e) {
				
				System.out.println("NO INPUT FILE DETECTED. TERMINATING PROGRAM");
				System.exit(2);
				
		}
	}

}
