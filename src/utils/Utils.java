/**
 * The package utils contains two classes: Multinomial and Utils. The Multinomial class defines a multinomial distribution and has a method to sample
 * from it. The Utils class has a method to sample from an exponential distribution.
 */
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
