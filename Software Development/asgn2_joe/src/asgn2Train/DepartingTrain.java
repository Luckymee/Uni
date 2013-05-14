package asgn2Train;

import java.util.ArrayList;
import java.util.List;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;

/**
 * A train is a sequence of carriages. This class defines various operations that can be
 * performed to prepare a long-distance train for departure.
 * 
 * @author Joe Maher - n8571520
 * @author Connor Livsey - n8510873
 */
public class DepartingTrain {

    private final int FIRST_CAR = 0;
    private final int ONE_CAR = 1;
    private final int NO_PASSENGERS = 0;

    private List<RollingStock> train;
    private Locomotive locomotive;
    private int currentCarNum; // tracks the last train carriage that was called with
			       // firstCarriage/nextCarriage

    public DepartingTrain() {
	this.train = new ArrayList<RollingStock>();
	this.currentCarNum = FIRST_CAR;
    }

    /**
     * Returns the first carriage on the train (which must be a locomotive)
     * 
     * @return RollingStock: the first carriage in the train, or null if there are no
     *         carriages.
     */
    public RollingStock firstCarriage() {

	if (locomotive != null) {
	    currentCarNum = FIRST_CAR;
	    return locomotive;
	} else {
	    if (train.size() > FIRST_CAR) {
		currentCarNum = ONE_CAR;
		return train.get(FIRST_CAR);
	    } else {
		currentCarNum = ONE_CAR;
		return null;
	    }
	}

    }

    /**
     * Returns the next carriage in the train after the one returned by the immediately
     * preceding call to either this method or method firstCarriage
     * 
     * @return RollingStock: the train's next carriage after the one returned by the
     *         immediately preceding call to either firstCarriage or nextCarriage, or null
     *         if there is no such carriage
     */
    public RollingStock nextCarriage() {
	if (++currentCarNum == 0) {
	    return locomotive;
	} else {
	    return currentCarNum > train.size() ? null : train.get(currentCarNum - 1);
	}
    }

    /**
     * Returns the total number of passengers currently on the train, counting all
     * passenger cars.
     * 
     * @return Integer: the number of passengers on the train
     */
    public Integer numberOnBoard() {

	Integer numberOnBoard = 0;
	for (RollingStock rollingStock : train) { // Iterate through train
	    if (rollingStock instanceof PassengerCar) { // Determine if Passenger Car
		numberOnBoard += ((PassengerCar) rollingStock).numberOnBoard();
	    }
	}

	return numberOnBoard;
    }

    /**
     * Returns the total number of seats on the train (whether occupied or not), counting
     * all passenger cars.
     * 
     * @return Integer: the number of seats on the train
     */
    public Integer numberOfSeats() {
	Integer totalSeats = 0;
	for (RollingStock rollingStock : train) { // Iterate through train
	    if (rollingStock instanceof PassengerCar) { // Determine if Passenger Car
		totalSeats += ((PassengerCar) rollingStock).numberOfSeats();
	    }
	}
	return totalSeats;
    }

    /**
     * Adds the given number of people to passenger carriages on the train.
     * 
     * @param newPassengers
     *            Integer: the number of people wish to board the train.
     * 
     * @return Integer: the number of people who were unable to board the train because
     *         they couldn't get a seat
     * 
     * @throws TrainException
     *             if the number of new passengers is negative.
     */
    public Integer board(Integer newPassengers) throws TrainException {
	// TODO - Should a negative number exception be thrown here. It is thrown in
	// passenger car
	Integer passengersToBoard = newPassengers;
	for (RollingStock rollingStock : train) { // Iterate through train
	    if (rollingStock instanceof PassengerCar) { // Found passenger car
		if (((PassengerCar) rollingStock).numberOnBoard() < ((PassengerCar) rollingStock).numberOfSeats()) {
		    // Number on board < Number of seats
		    passengersToBoard = ((PassengerCar) rollingStock).board(passengersToBoard);
		}
	    }

	    if (passengersToBoard == NO_PASSENGERS) { // No passengers left
		break;
	    }
	}

	return passengersToBoard; // returns how many didn't board
    }

    /**
     * Returns whether or not the train is capable of moving
     * 
     * @return Boolean: true if the train can move (or contains no carriages), false
     *         otherwise
     */
    public boolean trainCanMove() {
	if (locomotive == null) { // No locomotive
	    return false;
	} else if (train.size() == FIRST_CAR) {
	    if (locomotive.power() >= getTotalWeight()) {
		return true;
	    } else {
		return false;
	    }
	}

	return locomotive.power() >= getTotalWeight();
    }

    /**
     * Adds a new carriage to the end of the train
     * 
     * @param newCarriage
     *            RollingStock: the new carriage to be added.
     * 
     * @throws TrainException
     *             if adding the new carriage would produce an invalid train
     *             configuration, or if there are passengers on the train
     */
    public void addCarriage(RollingStock newCarriage) throws TrainException {
	// Special case for determining if a train instance already exits -- particularly
	// a Passenger car -- enabling it to exist in multiple places and be boarded
	// simultaneously.
	if (train.contains(newCarriage)) {
	    throw new TrainException("Quantum Mechanics Detected: Instance of Rolling Stock already exists.");
	}

	if (passengersOnBoard()) { // Passengers have boarded
	    throw new TrainException("Invalid Shunt: Passengers on board.");

	} else if (newCarriage instanceof Locomotive) { // Locomotive

	    if (locomotive != null) { // Locomotive exists
		throw new TrainException("Invalid Configuration: Locomotive already exists");
	    } else { // Add Locomotive
		locomotive = (Locomotive) newCarriage;
	    }

	} else if (newCarriage instanceof FreightCar) { // Freight Car
	    if (locomotive == null) { // No
				      // Locomotive
		throw new TrainException("Invalid Configuration: No Locomotive");
	    } else { // Valid Configuration
		train.add(newCarriage);
	    }

	} else if (newCarriage instanceof PassengerCar) { // Passenger Car
	    if (locomotive == null) { // No Locomotive
		throw new TrainException("Invalid Configuration: No Locomotive");

	    } else {
		if (train.size() == ONE_CAR && train.get(train.size() - ONE_CAR) instanceof FreightCar) {
		    // Freight Car found
		    throw new TrainException("Invalid Configuration: Passenger car placement");
		} else { // Add Passenger Car
		    train.add(newCarriage);
		}
	    }
	}
    }

    /**
     * Removes the last carriage from the train.
     * 
     * @throws TrainException
     *             if there is no rolling stock on the "train", or if there are passengers
     *             on the train.
     */
    public void removeCarriage() throws TrainException {
	if (locomotive == null && train.size() == 0) { // No Rolling stock
	    throw new TrainException("Invalid Shunt: No rolling stock to remove");
	} else if (passengersOnBoard()) { // Passengers have boarded
	    throw new TrainException("Invalid Shunt: Passengers on board.");
	} else if (train.size() == FIRST_CAR && locomotive != null) { // Remove locomotive
	    locomotive = null;
	} else {
	    train.remove(train.size() - ONE_CAR);
	}
    }

    /**
     * Returns a human-readable description of the entire train. This has the form of a
     * hyphen-separated list of carriages, starting with the locomotive on the left. The
     * description is thus a string "a-b-...-z"
     * 
     * @see asgn2RollingStock.RollingStock#toString
     * @return String: a human-readable description of the passenger car.
     */
    @Override
    public String toString() {
	String trainString = "";
	if (locomotive != null) {
	    trainString += locomotive;
	}

	for (RollingStock rollingStock : train) {
	    trainString += "-" + rollingStock;
	}

	return trainString;
    }

    /**
     * TODO
     * 
     * @return boolean: True if passengers on board
     */
    private boolean passengersOnBoard() {
	for (RollingStock rollingStock : train) { // Iterate through Train
	    if (rollingStock instanceof PassengerCar) { // Found Passenger Car
		if (((PassengerCar) rollingStock).numberOnBoard() > NO_PASSENGERS) {
		    // Has passengers
		    return true;
		}
	    }
	}
	return false;
    }

    /**
     * TODO
     * 
     * @return Integer: totalWeight
     */
    private Integer getTotalWeight() {
	int totalWeight = 0;

	if (locomotive != null) { // Locomotive exists
	    totalWeight += locomotive.getGrossWeight();
	}

	for (RollingStock rollingStock : train) { // Iterate through train
	    totalWeight += rollingStock.getGrossWeight();
	}

	return totalWeight;
    }
}
