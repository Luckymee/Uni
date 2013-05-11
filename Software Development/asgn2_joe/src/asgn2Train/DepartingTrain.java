package asgn2Train;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;

import java.util.ArrayList;


/**
 * A train is a sequence of carriages. This class defines various operations 
 * that can be performed to prepare a long-distance train for departure.
 * 
 * @author Joe Maher - n8571520
 */
public class DepartingTrain {
	
	private final int FIRST_CAR = 0;
	private final int ONE_CAR = 1;
	private final int ONE_PASSENGER = 1;
	private final int NEXT_CARRIAGE = 1;
	
	ArrayList<RollingStock> train;
	private int currentCarNum; //tracks the last train carriage that was called with firstCarriage/nextCarriage
	
	
	public DepartingTrain(){
		train = new ArrayList<RollingStock>(); 
	}
	
	/**
     * Returns the first carriage on the train (which must be a locomotive)
     * 
     * @return RollingStock: the first carriage in the train, or null 
     * 			if there are no carriages.
     */
	public RollingStock firstCarriage(){
		
		RollingStock firstCar;
		
		if (train.size() < ONE_CAR){
			firstCar = null;
		} else {
			firstCar = train.get(FIRST_CAR);
		}
		
		currentCarNum = FIRST_CAR;
		
		return firstCar;
	}
	
	/**
     * Returns the next carriage in the train after the one returned by 
     * the immediately preceding call to either this method or method firstCarriage
     * 
     * @return RollingStock: the train's next carriage after the one returned 
     * 			by the immediately preceding call to either firstCarriage 
     * 			or nextCarriage, or null if there is no such carriage
     */
	public RollingStock nextCarriage(){
		
		RollingStock nextCar;
		
		if (currentCarNum == FIRST_CAR){ //is first carriage
			nextCar = firstCarriage();
		} else if (currentCarNum == train.size() - 1){ //is last carriage
			nextCar = null;			
		} else {
			nextCar = train.get(currentCarNum + NEXT_CARRIAGE); //get next carriage in line
			currentCarNum++;
		}
		
		return nextCar;
	}
	
	/**
     * Returns the total number of passengers currently on the train,
     * counting all passenger cars.
     * 
     * @return Integer: the number of passengers on the train
     */
	public Integer numberOnBoard(){
		
		Integer numberOnBoard = 0;		
		RollingStock currentCar = firstCarriage(); //go to locomotive
		
		while (currentCar!= null){
			currentCar = nextCarriage(); //get next car
			if (currentCar instanceof PassengerCar){ //each passenger car in train
				numberOnBoard += ((PassengerCar) currentCar).numberOnBoard(); //add passenger number onto carriage to total
			}
		}
		
		return numberOnBoard;
	}
	
	/**
     * Returns the total number of seats on the train (whether occupied or not), 
     * counting all passenger cars.
     * 
     * @return Integer: the number of seats on the train
     */
	public Integer numberOfSeats(){
		
		Integer numberOfSeats = 0;		
		RollingStock currentCar = firstCarriage(); //go to locomotive
		
		while (currentCar!= null){
			currentCar = nextCarriage(); //get next car
			if (currentCar instanceof PassengerCar){ //each passenger car in train
				numberOfSeats += ((PassengerCar) currentCar).numberOfSeats(); //add capacity of carriage to total
			}
		}
		
		return numberOfSeats;
	}
	
	/**
     * Adds the given number of people to passenger carriages on the train.
     * 
     * @param newPassengers
     *        	Integer: the number of people wish to board the train.
     *            
     * @return Integer: the number of people who were unable to board the 
     * 			train because they couldn't get a seat
     *  
     * @throws TrainException
     *      	if the number of new passengers is negative.
     */
	public Integer board(Integer newPassengers) throws TrainException{
		
		Integer passengersToBoard = newPassengers;		
		RollingStock currentCar = firstCarriage(); //go to locomotive
		
		if (newPassengers < ONE_PASSENGER){
			throw new TrainException("Number of passengers cannot be negative");
		}
		
		while (currentCar!= null){
			currentCar = nextCarriage(); //get next car
			if (currentCar instanceof PassengerCar){ //each passenger car in train
				passengersToBoard -= ((PassengerCar) currentCar).board(passengersToBoard); //add passengers to train car
			}
		}
		
		return passengersToBoard; //returns how many didn't board
	}
	
	/**
     * Returns whether or not the train is capable of moving
     * 
     * @return Boolean: true if the train can move (or contains no carriages),
     * 			 false otherwise
     */
	public boolean trainCanMove(){
		
		int trainWeight = 0;
		int power = ((Locomotive) firstCarriage()).power(); //get power of locomotive
		RollingStock currentCar = firstCarriage(); //go to locomotive
		
		while (currentCar!= null){ //each car in train
			trainWeight += currentCar.getGrossWeight(); //add weight of car to total
			currentCar = nextCarriage(); //get next car
		}
		
		return power > trainWeight;
	}
	
	/**
     * Adds a new carriage to the end of the train
     * 
     * @param newCarriage
     *        	RollingStock: the new carriage to be added.
     *        
     * @throws TrainException
     *      	if adding the new carriage would produce an invalid train 
     *      	configuration, or if there are passengers on the train
     */
	public void addCarriage(RollingStock newCarriage) throws TrainException{
		
		if (numberOnBoard() >= ONE_PASSENGER){ //passengers on board
			throw new TrainException("Cant shunt with passengers on board");
		}
		
		if (newCarriage instanceof Locomotive){
			if (train.isEmpty()) { //no cars
				train.add(newCarriage);
			} else {
				throw new TrainException("Invalid Configuration: Locomotive placement");
			}
		} else if (newCarriage instanceof PassengerCar){
			if (canAddPassengerCar()){ //refer to method
				train.add(newCarriage);
			} else {
				throw new TrainException("Invalid Configuration: Passenger car placement");
			}
		} else if (newCarriage instanceof FreightCar){
			if (locomotiveAtFront()){ //freight car can be added except when no locomotive
				train.add(newCarriage);
			} else {
				throw new TrainException("Invalid Configuration: Freight car placement");
			}
		}
	}
	
	/**
     * Removes the last carriage from the train.
     *       
     * @throws TrainException
     *      	if there is no rolling stock on the "train", or if 
     *      	there are passengers on the train.
     */
	public void removeCarriage() throws TrainException{
		
		if (train.size() < ONE_CAR){
			throw new TrainException("No rolling stock to remove");
		} else if (numberOnBoard() >= ONE_PASSENGER){ //passengers on board
			throw new TrainException("Cant shunt with passengers on board");
		}
		
		train.remove(train.size()); //remove last carriage
	}
	
	/**
     * Returns a human-readable description of the entire train. This has the 
     * form of a hyphen-separated list of carriages, starting with the locomotive
     * on the left. The description is thus a string "a-b-...-z"
     * 
     * @see asgn2RollingStock.RollingStock#toString
     * @return String: a human-readable description of the passenger car.
     */
	@Override
	public String toString(){
		
		String trainString = "";		
		RollingStock currentCar = firstCarriage(); //go to locomotive
		
		while (currentCar!= null){
			trainString += currentCar.toString(); //add car string to train string
			currentCar = nextCarriage(); //get next car
			if (currentCar != null){
				trainString += "-"; //add hyphen in between if not last car
			}		
		}
		
		return trainString;
	}
	
	/**
     * returns whether or not a passenger car can be added
     * 
     * @return boolean: true if a passenger can be added.
     */
	private boolean canAddPassengerCar() {

		boolean canAdd = false;
		
		if (!(locomotiveAtFront() && train.size() == ONE_CAR)){ //train consists of only a locomotive
			canAdd = true;
		} else {
			for (int i = 0; i < train.size(); i++){ //each carriage in train
				RollingStock currentCar = nextCarriage();
				if (currentCar instanceof PassengerCar){ //if car is not passenger
					canAdd = true; 
				} else {
					canAdd = false;
				}
			}
		}
		
		return canAdd;
	}
	
	/**
     * Returns whether or not a locomotive is at the front of the train
     * 
     * @return Boolean: true if the train has a locomotive at the front
     */
	private boolean locomotiveAtFront(){
		
		boolean atFront = false;
		
		if (firstCarriage() instanceof Locomotive){ //first car is a locomotive
			atFront = true;
		}
		
		return atFront;
	}
}

	
