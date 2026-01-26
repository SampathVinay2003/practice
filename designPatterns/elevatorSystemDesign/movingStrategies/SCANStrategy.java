package designPatterns.elevatorSystemDesign.movingStrategies;

import designPatterns.elevatorSystemDesign.entities.ElevatorCar;
import designPatterns.elevatorSystemDesign.enums.Direction;
import designPatterns.elevatorSystemDesign.enums.ElevatorState;

public class SCANStrategy implements ElevatorMovingStrategy {
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
                car.getDoor().open();
                car.getDoor().close();
            } else if (!car.getDownQueue().isEmpty()) {
                car.setDirection(Direction.DOWN);
                move(car);
            } else {
                car.setElevatorState(ElevatorState.STATIC);
                System.out.println("Elevator " + car.getElevatorCarId() + " is now idle");
            }
        } else {
            if (!car.getDownQueue().isEmpty()) {
                int nextFloor = car.getDownQueue().poll();
                car.setCurrentFloor(nextFloor);
                System.out.println("Elevator " + car.getElevatorCarId() + 
                                 " arrived at floor " + nextFloor + " (DOWN)");
                car.getDoor().open();
                car.getDoor().close();
            } else if (!car.getUpQueue().isEmpty()) {
                car.setDirection(Direction.UP);
                move(car);
            } else {
                car.setElevatorState(ElevatorState.STATIC);
                System.out.println("Elevator " + car.getElevatorCarId() + " is now idle");
            }
        }
    }
}
