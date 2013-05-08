package asgn2Train;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;

import java.util.ArrayList;

public class DepartingTrain {
	
	private final int FIRST_CAR = 0;
	private final int ONE_PASSENGER = 1;
	private final int NEXT_CARRIAGE = 1;
	
	ArrayList<RollingStock> train = new ArrayList<RollingStock>(); //an arraylist might not be the best way to do this
	private int currentCarNum; //tracks the last train carriage that was called with firstCarriage/nextCarriage
	
	
	public RollingStock firstCarriage(){
		RollingStock firstCar = train.get(FIRST_CAR);
		currentCarNum = FIRST_CAR;
		return firstCar;
	}
	
	public RollingStock nextCarriage(){
		
		RollingStock nextCar;
		
		if ((currentCarNum) == train.size()){ //if on last carriage return nothing 
			nextCar = null;
		} else {
			nextCar = train.get(currentCarNum + NEXT_CARRIAGE); //get next carriage in line
			currentCarNum++;
		}
		return nextCar;
	}
	
	public Integer numberOnBoard(){
		
		int numberOnBoard = 0;		
		RollingStock currentCar = firstCarriage(); //go to locomotive
		
		while (currentCar!= null){
			currentCar = nextCarriage(); //get next car
			if (currentCar instanceof PassengerCar){ //each passenger car in train
				numberOnBoard += ((PassengerCar) currentCar).numberOnBoard(); //add passenger number on carriage to total
			}
		}
		
		return numberOnBoard;
	}
	
	public Integer numberOfSeats(){
		
		int numberOfSeats = 0;		
		RollingStock currentCar = firstCarriage(); //go to locomotive
		
		while (currentCar!= null){
			currentCar = nextCarriage(); //get next car
			if (currentCar instanceof PassengerCar){ //each passenger car in train
				numberOfSeats += ((PassengerCar) currentCar).numberOfSeats(); //add capactiy of carriage to total
			}
		}
		
		return numberOfSeats;
	}
	
	public Integer board(Integer newPassengers) throws TrainException{
		
		int passengersToBoard = newPassengers;		
		RollingStock currentCar = firstCarriage(); //go to locomotive
		
		while (currentCar!= null){
			currentCar = nextCarriage(); //get next car
			if (currentCar instanceof PassengerCar){ //each passenger car in train
				passengersToBoard -= ((PassengerCar) currentCar).board(passengersToBoard); //add passengers to train car
			}
		}
		
		return passengersToBoard; //returns how many didn't board
	}
	
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
	
	//needs more work,
	public void addCarriage(RollingStock newCarriage) throws TrainException{
		
		if (numberOnBoard() < ONE_PASSENGER){ // has no passengers
			if (newCarriage instanceof PassengerCar){
				train.add(getLastPassengerCar(), newCarriage); //add car after last passenger car (don't know if we have to show "shunting")
			} else if (newCarriage instanceof FreightCar){
				train.add(newCarriage); //add to end of the train
			} else if(newCarriage instanceof Locomotive){
				if (!locomotiveAtFront()){
					train.add(FIRST_CAR, newCarriage); //add to front if no locomotive  (don't know if we have to show "shunting")
				} 
			}
		} 
	}
	
	public void removeCarriage(){
		train.remove(train.size()); //remove last carriage
	}
	
	@Override
	public String toString(){
		
		String trainString = "";		
		RollingStock currentCar = firstCarriage(); //go to locomotive
		
		while (currentCar!= null){
			trainString += currentCar.toString(); //add car string to train string
			currentCar = nextCarriage(); //get next car
			if (currentCar != null){
				trainString += " - "; //add hyphen in between if not last car
			}		
		}
		
		return trainString;
	}

	//may be useful when doing the shunting process
	private int getLastPassengerCar() {

		int lastCar = 0;
		
		firstCarriage(); //go to locomotive and skip it
		for (int i = 0; i < train.size(); i++){ //each carriage in train
			RollingStock currentCar = nextCarriage();
			if (!(currentCar instanceof PassengerCar)){ //if not a passenger car
				lastCar = i - 1; //last passenger car was one before it (assumes train in correct order)
			}
		}
		
		return lastCar;
	}
	
	
	//is of no use yet, im not sure if we actually have to demonstrate "shunting" or if its implied
	//either way this is a very dodgy method and could be better
	private void shunt(int indexToSplit, RollingStock carToAdd) throws TrainException{
		
		ArrayList<RollingStock> shuntedCars = new ArrayList<RollingStock>(); //new "train" of "shunted" cars
		
		int carsToShunt = train.size() - indexToSplit; //total number carriages to remove from end
		
		for (int i = 0; i < carsToShunt; i++){
			RollingStock carToBeShunted = train.get(train.size()); //get last car
			removeCarriage();
			shuntedCars.add(FIRST_CAR, carToBeShunted); //add it to new train
		}
		
		addCarriage(carToAdd); //add the new car to train
		
		for (int i = 0; i < carsToShunt; i++){
			RollingStock carToBeAdded = shuntedCars.get(FIRST_CAR);
			shuntedCars.remove(FIRST_CAR); //remove from shunted train
			addCarriage(carToBeAdded);		 //add it to the end of the train
		}
		
	}
	
	private boolean locomotiveAtFront(){
		
		boolean atFront = false;
		
		if (firstCarriage() instanceof Locomotive){ //first car is a locomotive
			atFront = true;
		}
		
		return atFront;
	}
}

	
