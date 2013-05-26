package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * Rolling stock are the individual carriages from which a train is constructed.
 * This abstract class defines characteristics which they all share, most
 * notably having a known gross weight, measured here in tonnes.
 * 
 * @author Joe Maher - n8571520
 */
public abstract class RollingStock {

	private final int MIN_WEIGHT = 0;
	private int grossWeight;

	/**
	 * Constructs a railway carriage with a specific gross weight (i.e., the
	 * carriage's weight when fully laden).
	 * 
	 * @param grossWeight
	 *            Integer: the carriage's gross weight in tonnes
	 * @throws TrainException
	 *             If the gross weight is not positive.
	 */
	public RollingStock(Integer grossWeight) throws TrainException {

		if (grossWeight < MIN_WEIGHT) { // negative weight
			throw new TrainException("Gross weight is not positive");
		}
		this.grossWeight = grossWeight;
	}

	/**
	 * Returns the railway carriage's gross weight.
	 * 
	 * @return Integer: The carriage's gross weight, in tonnes.
	 */
	public Integer getGrossWeight() {

		return grossWeight;
	}

	/**
	 * Returns a human-readable description of this railway carriage.
	 * 
	 * @return String: a printable description of the rolling stock.
	 */
	@Override
	public abstract String toString();

}
