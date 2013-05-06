package asgn2RollingStock;

import asgn2Exceptions.TrainException;

public class PassengerCar extends RollingStock {
    private static final int MIN_PASSENGERS = 0;
    private int numOnBoard, numOfSeats;

    public PassengerCar(Integer grossWeight, Integer numberOfSeats)
	    throws TrainException {
	super(grossWeight);
	// TODO Auto-generated constructor stub
    }

    public Integer board(Integer newPassengers) throws TrainException {
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

    public void alight(Integer departingPassengers) throws TrainException {
	// TODO
    }

    public Integer numberOnBoard() {
	// TODO
	return numOnBoard;
    }

    public Integer numberOfSeats() {
	// TODO
	return numOfSeats;
    }

    @Override
    public String toString() {
	return "Passenger(" + numOnBoard + "|" + numOfSeats + ")";
    }

}
