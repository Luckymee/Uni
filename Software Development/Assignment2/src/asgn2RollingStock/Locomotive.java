package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * A locomotive is a railway carriage with the ability to propel itself and pull (or push)
 * other carriages. Thus the primary distinguishing characteristic of a locomotive is how
 * much weight it can pull.
 * 
 * @author Connor Livsey - n8510873
 */
public class Locomotive extends RollingStock {

    private static final int BASE_POWER = 100;
    private static final int POWER_RATING = 0;
    private static final int CLASS_CODE = 1;
    private static final int MIN_POWER = 1;
    private static final int MAX_POWER = 9;
    private String _classification;
    private boolean validPower;

    /**
     * Constructs a new locomotive object with a fixed gross weight and classification
     * code.
     * 
     * @param grossWeight
     *            Integer: the locomotive's (fully-laden) weight in tonnes.
     * @param classification
     *            String: the locomotive's two-character classification code.
     * @throws TrainException
     *             the locomotive's (fully-laden) weight in tonnes
     */
    public Locomotive(Integer grossWeight, String classification)
	    throws TrainException {

	// Inherited grossWeight.
	super(grossWeight);

	// Invalid Classification or Power
	if (!classificationCheck(classification) || !validPower) {

	    if (!classificationCheck(classification) && !validPower) { // Invalid power
								       // and Engine Type
		if (classification.charAt(POWER_RATING) == '-') {
		    throw new TrainException( // Negative number
			    "Invalid Classifcation: Power Range.");
		} else {
		    throw new TrainException(
			    "Invalid Classifcation: Engine Type and Power Range.");
		}
	    } else if (!validPower) { // Invalid power < MIN_POWER
		throw new TrainException("Invalid Classification: Power Type.");
	    } else { // Invalid engine type
		if (!isInteger(classification.charAt(CLASS_CODE))) { // Invalid engine
								     // character
		    throw new TrainException(
			    "Invalid Classification: Engine Type.");
		} else { // Invalid power > MAX_POWER
		    throw new TrainException(
			    "Invalid Classification: Power Type.");
		}
	    }
	} else { // Valid Classification: Must be upperCase
	    _classification = classification.toUpperCase();
	}
    }

    /**
     * Checks if given input character is an integer.
     * 
     * @param input
     *            char: the position of CLASS_CODE
     * @return boolean: True if integer is matched.
     */
    public static boolean isInteger(char input) {
	try {
	    String testInput = Character.toString(input);
	    Integer.parseInt(testInput);
	} catch (NumberFormatException e) { // Not a number
	    return false;
	}
	return true;
    }

    /**
     * Checks inputed classCode to determine valid input.
     * 
     * @param classCode
     *            String: the locomotive's two-character classification code.
     * @return boolean: True if classCode is matched.
     */
    private boolean classificationCheck(String classCode) {
	validPower = true;
	int power = Character.getNumericValue(classCode.charAt(POWER_RATING));

	if (power < MIN_POWER || power > MAX_POWER) { // Incorrect power
	    validPower = false;
	}

	// Must be uppercase
	switch (classCode.toUpperCase().charAt(CLASS_CODE)) {
	case 'E': // Electric Locomotive
	    return true;
	case 'D': // Diesel Locomotive
	    return true;
	case 'S': // Steam Locomotive
	    return true;
	default: // Invalid Input
	    return false;
	}
    }

    /**
     * Returns how much total weight the locomotive can pull (including itself).
     * 
     * @return Integer: the locomotive's "pulling power" in tonnes.
     */
    public Integer power() {
	return Character.getNumericValue(_classification.charAt(POWER_RATING))
		* BASE_POWER;
    }

    /**
     * Returns a human-readable description of the locomotive. This has the form "Loco(x)"
     * where x is the locomotive's two-character classification code.
     * 
     * @see asgn2RollingStock.RollingStock#toString
     * @return String: a human-readable description of the locomotive.
     */
    @Override
    public String toString() {
	return "Locomotive(" + _classification + ")";
    }

}
