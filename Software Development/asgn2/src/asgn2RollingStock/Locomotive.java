package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * A locomotive is a railway carriage with the ability to propel itself and pull
 * (or push) other carriages. Thus the primary distinguishing characteristic of
 * a locomotive is how much weight it can pull.
 * 
 * @author Joe Maher - n8571520
 */
public class Locomotive extends RollingStock {

	private static final int POWER = 0;
	private static final int ENGINE = 1;
	private static final int PULL_POWER = 100;
	private static final int MIN_POWER = 1;
	private static final int MAX_POWER = 9;
	
	private String classification;
	private String classificationError;

	/**
	 * Constructs a new locomotive object with a fixed gross weight and
	 * classification code.
	 * 
	 * @param grossWeight
	 *            Integer: the locomotive's (fully-laden) weight in tonnes.
	 * @param classification
	 *            String: the locomotive's two-character classification code.
	 * @throws TrainException
	 */
	public Locomotive(Integer grossWeight, String classification)
			throws TrainException {

		super(grossWeight);

		if (!checkClassificationCode(classification)) { // invalid classification
			throw new TrainException("Invalid classification: "
					+ classificationError);
		}

		this.classification = classification.toUpperCase();

	}

	/**
	 * Returns how much total weight the locomotive can pull (including itself).
	 * 
	 * @return Integer: the locomotive's "pulling power" in tonnes.
	 */
	public Integer power() {

		return Character.getNumericValue(classification.charAt(POWER))
				* PULL_POWER;
	}

	/**
	 * Returns a human-readable description of the locomotive. This has the form
	 * "Loco(x)" where x is the locomotive's two-character classification code.
	 * 
	 * @see asgn2RollingStock.RollingStock#toString
	 * @return String: a human-readable description of the locomotive.
	 */
	@Override
	public String toString() {

		return "Locomotive(" + classification + ")";
	}

	/**
	 * Checks inputed classification to determine if it is valid.
	 * 
	 * @param classification
	 *            String: the locomotive's two-character classification code.
	 * @return boolean: True if the classification is valid.
	 */
	private boolean checkClassificationCode(String classification) {

		boolean isValid = false;

		if (Character.isDigit(classification.charAt(POWER))) { // first char is digit (0-9)
			int powerCode = Character.getNumericValue(classification
					.charAt(POWER));
			char engineCode = classification.toUpperCase().charAt(ENGINE);
			boolean validPower = false;
			boolean validEngine = false;

			switch (engineCode) {
				case 'E':
					validEngine = true;
					break;
				case 'D':
					validEngine = true;
					break;
				case 'S':
					validEngine = true;
					break;
				default:
					classificationError = "Engine Type"; // specifying error type
			}

			if (powerCode >= MIN_POWER && powerCode <= MAX_POWER) { // is valid
																	// range
				validPower = true;
			} else { // specifying error type
				classificationError = "Power Range";
			}
			// true when both validEngine and validPower are true
			isValid = ((validEngine == validPower) == true);

			if (!validPower && !validEngine) { // specifying error type
				classificationError = "Engine Type and Power Range";
			}

		} else { // specifying error type
			classificationError = "Power Type";
		}

		return isValid;
	}

}
