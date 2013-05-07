package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * A passenger car is designed to carry people and has a fixed seating capacity. We assume
 * that the train is a long-distance one in which all passengers are assigned a seat.
 * 
 * @author Joe Maher - n8571520
 */
public class PassengerCar extends RollingStock {
	
	private final int MIN_SEATS = 0;
	private final int MIN_WEIGHT = 0;
	private final int MIN_PASSENGERS = 0;
	private int numberOfSeats;
	private int numberOnBoard;
		
	/**
     * Constructs a passenger car with a known weight and a fixed number of seats. (We
     * allow a passenger car to have zero seats, although it would not be very useful.)
     * 
     * @param grossWeight
     *            Integer: the carriage's gross weight in tonnes (ignoring the weight of
     *            passengers, which we treat as negligible)
     * @param numberOfSeats
     *            Integer: how many seats are available in the carriage.
     * @throws TrainException
     *             if the gross weight is not positive or if the number of seats is
     *             negative
     */
	public PassengerCar(Integer grossWeight, Integer numberOfSeats) throws TrainException{
		
		super(grossWeight);
		
		if (grossWeight < MIN_WEIGHT){ //Negative weight
			throw new TrainException("Gross weight is not positive");
		} else if (numberOfSeats < MIN_SEATS){ // negative seats
			throw new TrainException("Number of seats cannot be negative");
		}
		
		this.numberOfSeats = numberOfSeats;
	}
	
	/**
     * Adds the given number of new passengers to the number on board the carriage. If
     * there are too many new passengers for the number of spare seats the left over
     * people are not boarded.
     * 
     * @param newPassengers
     *            Integer: the number of people who wish to board the carriage
     * @return Integer: the number of people who were unable to board the carriage because
     *         they couldn't get a seat.
     * @throws TrainException
     *             if the number of new passengers is negative.
     */
	public int board(Integer newPassengers) throws TrainException{
		
		if (newPassengers < MIN_PASSENGERS){ //negative new passengers
			throw new TrainException("Number of passengers cannot be negative");
		}
		
		int numberCantFit = 0;
		int seatsLeft = numberOfSeats - numberOnBoard;
		
		if (newPassengers < seatsLeft) { //all new passengers fit
			numberOnBoard += newPassengers;
		} else { //some wont fit
			numberCantFit = newPassengers - seatsLeft; //get how many wont fit
			numberOnBoard += newPassengers - numberCantFit;
		}
		
		return numberCantFit;
	}
	
	/**
     * Removes the given number of passengers from this carriage. Attempting to remove
     * more passengers than are on board is not allowed.
     * 
     * @param departingPassengers
     *            Integer: departingPassengers - the number of passengers alighting from
     *            the carriage.
     * @throws TrainException
     *             if the number of departing passengers is negative or if the number of
     *             departing passengers exceeds the number on board.
     */
	public void alight(Integer departingPassengers) throws TrainException{
		
		if (departingPassengers < MIN_PASSENGERS){ //negative passengers leaving
			throw new TrainException("Number of passengers cannot be negative");
		} else if (departingPassengers > numberOnBoard){ // to many departing
			throw new TrainException("Departing passengers exceeds the number on board");
		}
		
		numberOnBoard -= departingPassengers;
	}
	
	/**
     * Returns the number of passengers currently on board this carriage.
     * 
     * @return Integer: the number of passengers on board
     */
	public Integer numberOnBoard(){
		return numberOnBoard;
	}
	
	/**
     * Returns the number of seats installed on this carriage.
     * 
     * @return Integer: the number of seats on this carriage
     */
	public Integer numberOfSeats(){
		return numberOfSeats;
	}
	
	/**
     * Returns a human-readable description of the passenger car. This has the form
     * "Passenger(x/y)" where x is the number of passengers currently on board and y is
     * the number of seats in the carriage.
     * 
     * @see asgn2RollingStock.RollingStock#toString
     * @return String: a human-readable description of the passenger car.
     */
	public String toString(){
		return "Passenger(" + numberOnBoard + "|" + numberOfSeats + ")";
	}
}
