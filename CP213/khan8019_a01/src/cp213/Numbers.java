package cp213;

/**
 * @author Basit Khan 169058019
 * @version 2024-09-01
 */
public class Numbers {

    /**
     * Determines closest value of two values to a target value.
     *
     * @param target the target value
     * @param v1     first comparison value
     * @param v2     second comparison value
     * @return one of v1 or v2 that is closest to target, v1 is the value chosen if
     *         v1 and v2 are an equal distance from target
     */
    public static double closest(final double target, final double v1, final double v2) {

	double temp1 = v1 - target;
	double temp2 = v2 - target;
	double rv;
	
	if (temp1 > temp2) {
	    rv = v1;
	}
	else if (temp1 < temp2) {
	    rv = v2;
	}
	else {
	    rv = v1;
	}

	return rv;
    }

    /**
     * Determines if n is a prime number. Prime numbers are whole numbers greater
     * than 1, that have only two factors - 1 and the number itself. Prime numbers
     * are divisible only by the number 1 or itself.
     *
     * @param n an integer
     * @return true if n is prime, false otherwise
     */
    public static boolean isPrime(final int n) {
	int count = 0;
	Boolean isP = true;
	
	if (n <= 1) {
	    isP = false;
	}
	else {
		for (int i = 1; i < n+1; i++) {
		    if (n%i == 0) {
			count += 1;
		    }
		}
		
		if(count > 2) {
		    isP = false;
		}	    
	}

	return isP;
    }

    /**
     * Sums and returns the total of a partial harmonic series. This series is the
     * sum of all terms 1/i, where i ranges from 1 to n (inclusive). Ex:
     *
     * n = 3: sum = 1/1 + 1/2 + 1/3 = 1.8333333333333333
     *
     * @param n an integer
     * @return sum of partial harmonic series from 1 to n
     */
    public static double sumPartialHarmonic(final int n) {
	
	double result = 0.0;
	
	for (double i = 1; i < n+1; i++) {
	    result += 1/i;
	}

	return result;
    }

}
