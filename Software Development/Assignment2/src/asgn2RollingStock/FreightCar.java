package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * Freight cars are designed to handle a variety of goods. For the purposes of this
 * assignment we assume there are three freight car types of interest, characterised by
 * the kinds of goods they are designed to carry:
 * 
 * @author Connor Livsey - n8510873
 */
public class FreightCar extends RollingStock {

    private static final int GOODS_CODE = 0;
    private String goodsType;

    /**
     * Constructs a freight car object.
     * 
     * @param grossWeight
     *            Integer: the freight car's gross weight (fully-laden), in tonnes.
     * @param goodsType
     *            String: the type of goods the car is designed to carry (either "G", "R"
     *            or "D").
     * @throws TrainException
     *             if the gross weight is not positive or if the goods' type is invalid
     */
    public FreightCar(Integer grossWeight, String goodsType)
	    throws TrainException {

	// Inherited grossWeight.
	super(grossWeight);

	if (!classificationCheck(goodsType)) { // Invalid goods type
	    throw new TrainException("Invalid Classification: Goods Type.");
	} else { // Valid goods type
	    this.goodsType = goodsType.toUpperCase();
	}
    }

    /**
     * Checks inputed goodsCode to determine valid input.
     * 
     * @param goodsCode
     *            String: The one character goods classification code.
     * @return boolean: True if goodsCode is matched.
     */
    private boolean classificationCheck(String goodsCode) {

	// Must be uppercase
	switch (goodsCode.toUpperCase().charAt(GOODS_CODE)) {
	case 'G': // General Goods
	    return true;
	case 'R': // Refrigerated Goods
	    return true;
	case 'D': // Dangerous Goods
	    return true;
	default: // Invalid Goods
	    return false;
	}
    }

    /**
     * Returns the type of goods this carriage was designed to carry. (Simulates someone
     * checking the label on the freight car to determine what's inside.)
     * 
     * @return String: the goodsType (G", "R" or "D").
     */
    public String goodsType() {
	return goodsType;
    }

    /**
     * Returns a human-readable description of the freight car. This has the form
     * "Freight(x)" where x is a character ("G", "R" or "D") indicating the type of goods
     * the car is designed to carry.
     * 
     * @see asgn2RollingStock.RollingStock#toString
     * @return String: a human-readable description of the freight car.
     */
    @Override
    public String toString() {
	return "Freight(" + goodsType + ")";
    }

}
