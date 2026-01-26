package designPatterns.elevatorDesignPractice.entities;

import designPatterns.elevatorDesignPractice.enums.Direction;
import designPatterns.elevatorDesignPractice.enums.ElevatorState;
import designPatterns.elevatorDesignPractice.movingStrategies.MovingStrategy;
import designPatterns.elevatorDesignPractice.observer.ElevatorObserver;
import designPatterns.elevatorDesignPractice.observer.RequestObserver;
import designPatterns.elevatorDesignPractice.request.InternalRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ElevatorCar {
    private static int ID = 1;
    private MovingStrategy movingStrategy;
    private int currentFloor = 0;
    private int id;
    private ElevatorState elevatorState;
    private Direction direction;
    private PriorityQueue<Integer> upQueue;
    private PriorityQueue<Integer> downQueue;
    private int minFloor = 0;
    private int maxFloor = 10;
    private List<ElevatorObserver> observers = new ArrayList<>();
    private RequestObserver requestObserver;
    
    public ElevatorCar(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
        this.elevatorState = ElevatorState.IDLE;
        this.direction = Direction.UP;
        this.upQueue = new PriorityQueue<>();
        this.downQueue = new PriorityQueue<>((a, b) -> b - a);
        this.id = ID++;
    }
    
    public int getMinFloor() {
        return minFloor;
    }
    
    public void setMinFloor(int minFloor) {
        this.minFloor = minFloor;
    }
    
    public int getMaxFloor() {
        return maxFloor;
    }
    
    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        int oldFloor = this.currentFloor;
        this.currentFloor = currentFloor;
        notifyFloorChanged(oldFloor, currentFloor);
    }
    
    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(ElevatorObserver observer) {
        observers.remove(observer);
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public ElevatorState getElevatorState() {
        return elevatorState;
    }
    
    public void setElevatorState(ElevatorState elevatorState) {
        ElevatorState oldState = this.elevatorState;
        this.elevatorState = elevatorState;
        notifyStateChanged(oldState, elevatorState);
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction direction) {
        Direction oldDirection = this.direction;
        this.direction = direction;
        notifyDirectionChanged(oldDirection, direction);
    }
    
    public int getUpQueueSize() {
        return upQueue.size();
    }
    
    public int getDownQueueSize() {
        return downQueue.size();
    }
    
    public Integer peekNextUpRequest() {
        return upQueue.peek();
    }
    
    public Integer peekNextDownRequest() {
        return downQueue.peek();
    }
    
    public PriorityQueue<Integer> getUpQueue() {
        return upQueue;
    }
    
    public PriorityQueue<Integer> getDownQueue() {
        return downQueue;
    }

    public void setRequestObserver(RequestObserver requestObserver) {
        this.requestObserver = requestObserver;
    }
    
    public void pressButton(int destinationFloor) {
        InternalRequest request = new InternalRequest(currentFloor, destinationFloor, id);
        System.out.println("Button pressed in Elevator " + id + " for floor " + destinationFloor);
        if (requestObserver != null) {
            requestObserver.onInternalRequest(request);
        }
    }

    public void addFloorToQueue(int floor, Direction direction) {
        if (direction == Direction.UP) {
            upQueue.add(floor);
            System.out.println("Elevator " + id + ": Added floor " + floor + " to UP queue");
        } else {
            downQueue.add(floor);
            System.out.println("Elevator " + id + ": Added floor " + floor + " to DOWN queue");
        }
        notifyRequestAdded(floor, direction);
    }
    
    private void notifyFloorChanged(int oldFloor, int newFloor) {
        if (oldFloor != newFloor) {
            for (ElevatorObserver observer : observers) {
                observer.onFloorChanged(this, oldFloor, newFloor);
            }
        }
    }
    
    private void notifyStateChanged(ElevatorState oldState, ElevatorState newState) {
        if (oldState != newState && oldState != null) {
            for (ElevatorObserver observer : observers) {
                observer.onStateChanged(this, oldState, newState);
            }
        }
    }
    
    private void notifyDirectionChanged(Direction oldDirection, Direction newDirection) {
        if (oldDirection != newDirection && oldDirection != null) {
            for (ElevatorObserver observer : observers) {
                observer.onDirectionChanged(this, oldDirection, newDirection);
            }
        }
    }
    
    private void notifyRequestAdded(int floor, Direction direction) {
        for (ElevatorObserver observer : observers) {
            observer.onRequestAdded(this, floor, direction);
        }
    }
    
    public void move() {
        if (movingStrategy != null) {
            movingStrategy.move(this);
        } else {
            System.out.println("Elevator " + id + ": No movement strategy set");
        }
    }
    
    public boolean hasRequests() {
        return !upQueue.isEmpty() || !downQueue.isEmpty();
    }
}
