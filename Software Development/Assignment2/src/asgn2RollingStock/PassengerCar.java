package asgn2RollingStock;

import asgn2Exceptions.TrainException;

/**
 * A passenger car is designed to carry people and has a fixed seating capacity. We assume
 * that the train is a long-distance one in which all passengers are assigned a seat.
 * 
 * @author Connor Livsey - n8510873
 */
public class PassengerCar extends RollingStock {
    private static final int MIN_PASSENGERS = 0;
    private int numOnBoard;
    private final int numOfSeats;

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
    public PassengerCar(Integer grossWeight, Integer numberOfSeats)
	    throws TrainException {

	// Inherited grossWeight.
	super(grossWeight);

	if (numberOfSeats < MIN_PASSENGERS) { // Invalid seats
	    throw new TrainException("Invalid Seats: Not enough seats.");
	} else { // Valid seats
	    numOfSeats = numberOfSeats;
	}
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
    public Integer board(Integer newPassengers) throws TrainException {
	// #warning - THINK MORE ON THIS
	int newBoarding, numSeatsLeft;
	if (newPassengers < MIN_PASSENGERS) { // Invalid Passengers
	    throw new TrainException("Invalid number of passengers boarding.");
	}

	newBoarding = numOnBoard + newPassengers;
	numSeatsLeft = numOfSeats - numOnBoard;
	if (newBoarding > numOfSeats) { // Invalid passengers and seats
	    throw new TrainException("Invalid Boarding: Too many Passengers ("
		    + numSeatsLeft + ") left.");
	} else if (newBoarding <= numOfSeats) { // Valid passengers and seats
	    numOnBoard = newBoarding;
	    return numOnBoard;
	} else {
	    return null;
	}
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
    public void alight(Integer departingPassengers) throws TrainException {
	if (departingPassengers < MIN_PASSENGERS
		|| departingPassengers > numOnBoard) { // Invalid passengers
	    if (departingPassengers < MIN_PASSENGERS) { // Not enough passengers
		throw new TrainException(
			"Invalid Passengers: Not enough passengers.");
	    } else { // Too many passengers
		throw new TrainException(
			"Invalid Passengers: Too many passengers.");
	    }
	} else { // Valid passengers
	    numOnBoard -= departingPassengers;
	}
    }

    /**
     * Returns the number of passengers currently on board this carriage.
     * 
     * @return Integer: the number of passengers on board
     */
    public Integer numberOnBoard() {
	// TODO
	return numOnBoard;
    }

    /**
     * Returns the number of seats installed on this carriage.
     * 
     * @return Integer: the number of seats on this carriage
     */
    public Integer numberOfSeats() {
	// TODO
	return numOfSeats;
    }

    /**
     * Returns a human-readable description of the passenger car. This has the form
     * "Passenger(x/y)" where x is the number of passengers currently on board and y is
     * the number of seats in the carriage.
     * 
     * @see asgn2RollingStock.RollingStock#toString
     * @return String: a human-readable description of the passenger car.
     */
    @Override
    public String toString() {
	return "Passenger(" + numOnBoard + "|" + numOfSeats + ")";
    }

}
