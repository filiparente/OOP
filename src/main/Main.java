/**
 * The package main contains two classes: Main and Parser. The Main class only contains a method main which runs the program, where 4
 * exceptions are handled: ParserConfigurationException, the user is informed that the xml provided does not agree with the dtd; SAXException, 
 * the user is informed that the xml has an error; IOException, the user is informed that the file has an error or is not found;
 *  and ArrayIndexOutOfBoundsException.
 * The Parser class is responsible for parsing the xml passed to the program by argument.
 */
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
						if(args.length < 1) {
							System.out.println("NO INPUT FILE. USAGE: [test_name.xml]");
							System.exit(2);
						}
						
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
