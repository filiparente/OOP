package utils;

import java.util.Random;

/**
 * Class Utils implements the method expRandom.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
public class Utils {
	/**
	 * Random number generator.
	 */
	static Random random = new Random();
	
	/**
	 * Method to sample from an exponential distribution with parameter m,
	 * according to the inverse transform method.
	 * 
	 * @param m parameter of the exponential distribution.
	 * @return the resulted sample.
	 */
	public static double expRandom(double m) {
		double next = random.nextDouble();
		return -m*Math.log(1.0-next);
	}
}
