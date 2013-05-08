package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * Freight cars are designed to handle a variety of goods. For the purposes of this
 * assignment we assume there are three freight car types of interest, characterised by
 * the kinds of goods they are designed to carry:
 * 
 * @author Joe Maher - n8571520
 */
public class FreightCar extends RollingStock {
	
	private String goodsType;
	final int MIN_WEIGHT = 0;
	final int GOODS_TYPE = 0;
	
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
	public FreightCar(Integer grossWeight, String goodsType) throws TrainException{
		
		super(grossWeight);
		
		if (grossWeight < MIN_WEIGHT){ //invalid weight
			throw new TrainException("The weight must be positive");
		} else if (!checkGoodsCode(goodsType)){ //invalid goods
			throw new TrainException("Invalid goods code");
		}
		
		this.goodsType = goodsType.toUpperCase();
	}
	
	 /**
     * Checks inputed goodsCode to determine valid input.
     * 
     * @param goodsCode
     *            String: The one character goods classification code.
     * @return boolean: True if goodsCode is matched.
     */
	@Override
	public String toString(){
		return "Freight(" + goodsType + ")";
		
	}
	
	/**
     * Returns the type of goods this carriage was designed to carry. (Simulates someone
     * checking the label on the freight car to determine what's inside.)
     * 
     * @return String: the goodsType (G", "R" or "D").
     */
	public String goodsType(){
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
	private boolean checkGoodsCode(String goodsType){;
		
	boolean validCode = false;
		
		switch(goodsType.toUpperCase().charAt(GOODS_TYPE)){
			case 'G' : validCode = true; break;
			case 'R' : validCode = true; break;
			case 'D' : validCode = true; break;
		}
		
		return validCode;
		
	}
	

}
