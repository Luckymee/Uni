package asgn2RollingStock;

import asgn2Exceptions.TrainException;

public class PassengerCar extends RollingStock {
    private static final int MIN_PASSENGERS = 0;
    private int numOnBoard;
    private final int numOfSeats;

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
