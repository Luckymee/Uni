package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * Rolling stock are the individual carriages from which a train is constructed. This
 * abstract class defines characteristics which they all share, most notably having a
 * known gross weight, measured here in tonnes.
 * 
 * @author Connor Livsey - n8510873
 */
public abstract class RollingStock {
    private int _grossWeight;

    /**
     * Constructs a railway carriage with a specific gross weight (i.e., the carriage's
     * weight when fully laden).
     * 
     * @param grossWeight
     *            Integer: the carriage's gross weight in tonnes
     * @throws TrainException
     *             If the gross weight is not positive.
     */
    public RollingStock(Integer grossWeight) throws TrainException {
	if (grossWeight < 0) { // Gross weight not positive
	    throw new TrainException(
		    "Invalid Weight: Gross Weight must be more a positive number.");
	} else { // Valid gross weight
	    _grossWeight = grossWeight;
	}
    }

    /**
     * Returns the railway carriage's gross weight.
     * 
     * @return Integer: The carriage's gross weight, in tonnes.
     */
    public Integer getGrossWeight() {
	return _grossWeight;
    }

    /**
     * Returns a human-readable description of this railway carriage.
     * 
     * @return String: a printable description of the rolling stock.
     */
    @Override
    public abstract String toString();
}
