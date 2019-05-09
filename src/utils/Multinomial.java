/**
 * The package utils contains two classes: Multinomial and Utils. The Multinomial class defines a multinomial distribution and has a method to sample
 * from it. The Utils class has a method to sample from an exponential distribution.
 */
package utils;

import java.util.Random;

/**
 * Class Multinomial defines a multinomial distribution and a method to sample from it.
 * 
 * @author Filipa, Goncalo, Joana.
 *
 */
public class Multinomial {
	/**
	 * Random number generator.
	 */
	static Random generator = new Random();
	
	/**
	 * Cummulative distribution.
	 */
	double[] distribution;
	
	/**
	 * Number of classes of the multinomial distribution.
	 */
	int range;
	
	/**
	 * Constructor for the Multinomial class.
	 * @param probabilities vector of the multinomial probabilities, not yet normalized.
	 * 
	 * The vector of probabilities is normalized through the division by its summation.
	 * The cummulative distribution vector is obtained.
	 */
	public Multinomial(double[] probabilities){
		range = probabilities.length;
		distribution = new double[range];
		double sumProb = 0;
		for (double value : probabilities){
			sumProb += value;
		}
		double position = 0;
		for (int i = 0; i < range; ++i){
			position += probabilities[i] / sumProb;
			distribution[i] = position;
		}
		distribution[range-1] = 1.0;
	}
	
	/**
	 * Method to sample from a multinomial distribution according to its cummulative distribution.
	 * The algorithm used is the inverse transform sampling.
	 * 
	 * @return the resulted sample.
	 */
	public int sample() {
		double uniform = generator.nextDouble();
		for (int i = 0; i < range; ++i){
			if (uniform < distribution[i]){
				return i;
			}
		}
		return range - 1;
	}

}