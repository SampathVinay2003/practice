package designPatterns.elevatorDesignPractice.request;

import designPatterns.elevatorDesignPractice.entities.ElevatorCar;
import designPatterns.elevatorDesignPractice.enums.Direction;

import java.util.List;

public class ExternalRequest {

    int currentFloor;
    Direction direction;

    public ExternalRequest(int currentFloor, Direction direction) {
        this.currentFloor = currentFloor;
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
