package designPatterns.elevatorSystemDesign.entities;

import designPatterns.elevatorSystemDesign.enums.Direction;
import designPatterns.elevatorSystemDesign.enums.ElevatorState;
import designPatterns.elevatorSystemDesign.movingStrategies.ElevatorMovingStrategy;
import designPatterns.elevatorSystemDesign.request.Request;

import java.util.PriorityQueue;

public class ElevatorCar {
    int elevatorCarId;
    ElevatorMovingStrategy elevatorMovingStrategy;
    ElevatorState elevatorState;
    Direction direction;
    int currentFloor;
    InternalButton internalButton;
    PriorityQueue<Integer> upQueue;
    PriorityQueue<Integer> downQueue;
    Door door;
    Display display;
    
    public ElevatorCar() {
        this.upQueue = new PriorityQueue<>();
        this.downQueue = new PriorityQueue<>((a, b) -> b - a);
        this.elevatorState = ElevatorState.STATIC;
        this.currentFloor = 0;
        this.door = new Door();
        this.display = new Display();
    }
    
    public Door getDoor() {
        return door;
    }
    
    public Display getDisplay() {
        return display;
    }
    
    public int getElevatorCarId() {
        return elevatorCarId;
    }

    public void setElevatorCarId(int elevatorCarId) {
        this.elevatorCarId = elevatorCarId;
    }

    public ElevatorMovingStrategy getElevatorMovingStrategy() {
        return elevatorMovingStrategy;
    }

    public void setElevatorMovingStrategy(ElevatorMovingStrategy elevatorMovingStrategy) {
        this.elevatorMovingStrategy = elevatorMovingStrategy;
    }

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

    public InternalButton getInternalButton() {
        return internalButton;
    }

    public void setInternalButton(InternalButton internalButton) {
        this.internalButton = internalButton;
    }

    public void addRequestToQueue(Request request) {
        int destinationFloor = request.getDestinationFloorNumber();
        
        if (request.getDirection() == Direction.UP) {
            upQueue.add(destinationFloor);
        } else {
            downQueue.add(destinationFloor);
        }
        
        System.out.println("Request added to Elevator " + elevatorCarId + 
                         " queue: Floor " + destinationFloor + " (" + request.getDirection() + ")");
    }
    
    public int getPendingRequestCount() {
        return upQueue.size() + downQueue.size();
    }
    
    public PriorityQueue<Integer> getUpQueue() {
        return upQueue;
    }
    
    public PriorityQueue<Integer> getDownQueue() {
        return downQueue;
    }
    
    public void move() {
        if (elevatorMovingStrategy != null) {
            elevatorMovingStrategy.move(this);
        } else {
            System.out.println("No movement strategy set for Elevator " + elevatorCarId);
        }
    }
}
