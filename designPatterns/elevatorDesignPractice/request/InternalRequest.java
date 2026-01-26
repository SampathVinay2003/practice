package designPatterns.elevatorDesignPractice.request;

import designPatterns.elevatorDesignPractice.enums.Direction;

public class InternalRequest{
    private final int currentFloor;
    private final int destinationFloor;
    private final int elevatorCarId;
    private final Direction direction;
    
    public InternalRequest(int currentFloor, int destinationFloor, int elevatorCarId) {
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.elevatorCarId = elevatorCarId;
        this.direction = destinationFloor > currentFloor ? Direction.UP : Direction.DOWN;
    }
    
    public int getCurrentFloor() {
        return currentFloor;
    }
    
    public int getDestinationFloor() {
        return destinationFloor;
    }
    
    public int getElevatorCarId() {
        return elevatorCarId;
    }
    
    public Direction getDirection() {
        return direction;
    }
}
