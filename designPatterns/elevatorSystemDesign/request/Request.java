package designPatterns.elevatorSystemDesign.request;

import designPatterns.elevatorSystemDesign.dispatcher.Dispatcher;
import designPatterns.elevatorSystemDesign.entities.ElevatorCar;
import designPatterns.elevatorSystemDesign.enums.Direction;

import java.util.List;

public class Request {
    int currentFloorNumber;
    int destinationFloorNumber;
    int elevatorCarId;
    Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public int getCurrentFloorNumber() {
        return currentFloorNumber;
    }

    public void setCurrentFloorNumber(int currentFloorNumber) {
        this.currentFloorNumber = currentFloorNumber;
    }

    public int getDestinationFloorNumber() {
        return destinationFloorNumber;
    }

    public void setDestinationFloorNumber(int destinationFloorNumber) {
        this.destinationFloorNumber = destinationFloorNumber;
    }

    public int getElevatorCarId() {
        return elevatorCarId;
    }

    public void setElevatorCarId(int elevatorCarId) {
        this.elevatorCarId = elevatorCarId;
    }

    public Request(int currentFloorNumber, int destinationFloorNumber, int elevatorCarId, Direction direction) {
        this.currentFloorNumber = currentFloorNumber;
        this.destinationFloorNumber = destinationFloorNumber;
        this.elevatorCarId = elevatorCarId;
        this.direction = direction;
    }

    public ElevatorCar getElevatorCar(Dispatcher dispatcher, List<ElevatorCar> elevatorCars) {
        return elevatorCars.get(elevatorCarId);
    }


}
