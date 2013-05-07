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
	
	ArrayList<RollingStock> train = new ArrayList<RollingStock>();
	private int currentCarNum;
	
	
	public RollingStock firstCarriage(){
		RollingStock firstCar = train.get(FIRST_CAR);
		currentCarNum = FIRST_CAR;
		return firstCar;
	}
	
	public RollingStock nextCarriage(){
		
		RollingStock nextCar;
		
		if ((currentCarNum) == train.size()){
			nextCar = null;
			currentCarNum = FIRST_CAR;
		} else {
			nextCar = train.get(currentCarNum + 1);
			currentCarNum++;
		}
		return nextCar;
	}
	
	public Integer numberOnBoard(){
		
		int numberOnBoard = 0;		
		
		for (int i = 0; i < train.size(); i++){
			RollingStock currentCar = nextCarriage();
			if (currentCar instanceof PassengerCar){
				numberOnBoard += ((PassengerCar) currentCar).numberOnBoard();
			}
		}
		
		return numberOnBoard;
	}
	
	public Integer numberOfSeats(){
		
		int numberOfSeats = 0;
		
		for (int i = 0; i < train.size(); i++){
			RollingStock currentCar = nextCarriage();
			if (currentCar instanceof PassengerCar){
				numberOfSeats += ((PassengerCar) currentCar).numberOfSeats();
			}
		}
		
		return numberOfSeats;
	}
	
	public Integer board(Integer newPassengers) throws TrainException{
		
		int passengersToBoard = newPassengers;
		
		for (int i = 0; i < train.size(); i++){
			RollingStock currentCar = nextCarriage();
			if (currentCar instanceof PassengerCar){
				passengersToBoard -= ((PassengerCar) currentCar).board(passengersToBoard);
			}
		}
		
		return passengersToBoard;
	}
	
	public boolean trainCanMove(){
		
		int trainWeight = 0;
		int power = ((Locomotive) firstCarriage()).power();
		
		for (int i = 0; i < train.size(); i++){
			RollingStock currentCar = nextCarriage();
			trainWeight += currentCar.getGrossWeight();
		}
		
		return power > trainWeight;
	}
	
	public void addCarriage(RollingStock newCarriage) throws TrainException{
		
		if (numberOnBoard() < ONE_PASSENGER){ // has no passengers
			if (newCarriage instanceof PassengerCar){
				train.add(getLastPassengerCar(), newCarriage); //add car after last passenger car (don't know if we have to show "shunting")
			} else if (newCarriage instanceof FreightCar){
				train.add(newCarriage); //add to end of the train
			} else if(newCarriage instanceof Locomotive){
				if (!locomotiveAtFront()){
					train.add(FIRST_CAR, newCarriage); //add to front if no locomotive  (don't know if we have to show "shunting")
				} else {
					throw new TrainException("Invalid Configuration : Cant have two locomotives");
				}
			}
		} else {
			throw new TrainException("Passengers on board");
		}
	}
	
	public void removeCarriage(){
		train.remove(train.size());
	}
	
	@Override
	public String toString(){
		
		String trainString = "";
		
		trainString += firstCarriage().toString() + " - ";
		
		for (int i = 0; i < train.size(); i++){
			trainString += nextCarriage().toString();
			
			if (i < train.size() - 1){
				trainString += " - ";
			}
		}
		
		return trainString;
	}

	private int getLastPassengerCar() {

		int lastCar = 0;
		
		firstCarriage(); //skip the locomotive
		for (int i = 0; i < train.size(); i++){
			RollingStock currentCar = nextCarriage();
			if (!(currentCar instanceof PassengerCar)){
				lastCar = i - 1;
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
		
		if (firstCarriage() instanceof Locomotive){
			atFront = true;
		}
		
		return atFront;
	}
}

	
