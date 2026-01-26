package designPatterns.elevatorSystemDesign.movingStrategies;

import designPatterns.elevatorSystemDesign.entities.ElevatorCar;
import designPatterns.elevatorSystemDesign.enums.Direction;
import designPatterns.elevatorSystemDesign.enums.ElevatorState;

public class LOOKStrategy implements ElevatorMovingStrategy {
    @Override
    public void move(ElevatorCar car) {
        if (car.getElevatorState() == ElevatorState.STATIC) {
            if (!car.getUpQueue().isEmpty()) {
                car.setDirection(Direction.UP);
                car.setElevatorState(ElevatorState.MOVING);
            } else if (!car.getDownQueue().isEmpty()) {
                car.setDirection(Direction.DOWN);
                car.setElevatorState(ElevatorState.MOVING);
            } else {
                System.out.println("Elevator " + car.getElevatorCarId() + " is idle");
                return;
            }
        }
        
        if (car.getDirection() == Direction.UP) {
            if (!car.getUpQueue().isEmpty()) {
                int nextFloor = car.getUpQueue().poll();
                car.setCurrentFloor(nextFloor);
                System.out.println("Elevator " + car.getElevatorCarId() + 
                                 " arrived at floor " + nextFloor + " (UP)");
            } else {
                car.setDirection(Direction.DOWN);
                car.setElevatorState(ElevatorState.STATIC);
            }
        } else {
            if (!car.getDownQueue().isEmpty()) {
                int nextFloor = car.getDownQueue().poll();
                car.setCurrentFloor(nextFloor);
                System.out.println("Elevator " + car.getElevatorCarId() + 
                                 " arrived at floor " + nextFloor + " (DOWN)");
            } else {
                car.setDirection(Direction.UP);
                car.setElevatorState(ElevatorState.STATIC);
            }
        }
    }
}
