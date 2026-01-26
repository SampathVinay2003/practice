package designPatterns.elevatorSystemDesign.entities;

import designPatterns.elevatorSystemDesign.enums.Direction;
import designPatterns.elevatorSystemDesign.enums.ElevatorState;

public class Display {
    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public void setElevatorState(ElevatorState elevatorState) {
        this.elevatorState = elevatorState;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    int currentFloor;
    Direction direction;
    ElevatorState elevatorState;
}
