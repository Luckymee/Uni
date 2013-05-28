package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * Freight cars are designed to handle a variety of goods. For the purposes of
 * this assignment we assume there are three freight car types of interest,
 * characterised by the kinds of goods they are designed to carry:
 * 
 * @author Joe Maher - n8571520
 */
public class FreightCar extends RollingStock {

	
	private static final int GOODS_TYPE = 0;
	private static final int ONE_CHAR = 1;

	private String goodsType;
	
	/**
	 * Constructs a freight car object.
	 * 
	 * @param grossWeight
	 *            Integer: the freight car's gross weight (fully-laden), in
	 *            tonnes.
	 * @param goodsType
	 *            String: the type of goods the car is designed to carry (either
	 *            "G", "R" or "D").
	 * @throws TrainException
	 *             if the gross weight is not positive or if the goods' type is
	 *             invalid
	 */
	public FreightCar(Integer grossWeight, String goodsType)
			throws TrainException {

		super(grossWeight);

		if (!checkGoodsCode(goodsType)) { // invalid goods
			throw new TrainException("Invalid goods code.");
		}

		String test = Character.toString(goodsType.toUpperCase().charAt(
				GOODS_TYPE));
		this.goodsType = test;

	}

	/**
	 * Returns a human-readable description of the freight car. This has the
	 * form "Freight(x)" where x is a character ("G", "R" or "D") indicating the
	 * type of goods the car is designed to carry.
	 * 
	 * @see asgn2RollingStock.RollingStock#toString
	 * @return String: a human-readable description of the freight car.
	 */
	@Override
	public String toString() {

		return "Freight(" + goodsType + ")";
	}

	/**
	 * Returns the type of goods this carriage was designed to carry. (Simulates
	 * someone checking the label on the freight car to determine what's
	 * inside.)
	 * 
	 * @return String: the goodsType (G", "R" or "D").
	 */
	public String goodsType() {

		return goodsType.toString();
	}

	/**
	 * Checks inputed goodsType to determine valid input.
	 * 
	 * @param goodsType
	 *            String: The one character goods classification code.
	 * @return boolean: True if goodsType is matched.
	 */
	private boolean checkGoodsCode(String goodsType) {

		boolean validCode = false;
		String editedGoodsType = Character.toString(goodsType
				.charAt(GOODS_TYPE));

		if (editedGoodsType.length() == ONE_CHAR) {
			switch (goodsType.toUpperCase().charAt(GOODS_TYPE)) {
				case 'G':
					validCode = true;
					break;
				case 'R':
					validCode = true;
					break;
				case 'D':
					validCode = true;
					break;
			}
		}

		return validCode;

	}

}
