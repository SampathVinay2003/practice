package designPatterns.elevatorSystemDesign.movingStrategies;

import designPatterns.elevatorSystemDesign.entities.ElevatorCar;
import designPatterns.elevatorSystemDesign.enums.Direction;
import designPatterns.elevatorSystemDesign.enums.ElevatorState;

import java.util.LinkedList;
import java.util.Queue;

public class FCFSStrategy implements ElevatorMovingStrategy {
    private Queue<Integer> requestQueue = new LinkedList<>();
    
    @Override
    public void move(ElevatorCar car) {
        if (requestQueue.isEmpty()) {
            while (!car.getUpQueue().isEmpty()) {
                requestQueue.add(car.getUpQueue().poll());
            }
            while (!car.getDownQueue().isEmpty()) {
                requestQueue.add(car.getDownQueue().poll());
            }
        }
        
        if (requestQueue.isEmpty()) {
            car.setElevatorState(ElevatorState.STATIC);
            System.out.println("Elevator " + car.getElevatorCarId() + " is idle");
            return;
        }
        
        car.setElevatorState(ElevatorState.MOVING);
        int nextFloor = requestQueue.poll();
        
        if (nextFloor > car.getCurrentFloor()) {
            car.setDirection(Direction.UP);
        } else {
            car.setDirection(Direction.DOWN);
        }
        
        car.setCurrentFloor(nextFloor);
        System.out.println("Elevator " + car.getElevatorCarId() + 
                         " arrived at floor " + nextFloor + " (" + car.getDirection() + ")");
    }
}
