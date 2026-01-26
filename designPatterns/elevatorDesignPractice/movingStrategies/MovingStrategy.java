package designPatterns.elevatorDesignPractice.movingStrategies;

import designPatterns.elevatorDesignPractice.entities.ElevatorCar;

public interface MovingStrategy {
    void move(ElevatorCar elevatorCar);

    default void moveToFloor(ElevatorCar car, int targetFloor) {
        int currentFloor = car.getCurrentFloor();
        System.out.println("Elevator " + car.getId() + " moving from floor " + currentFloor + " to floor " + targetFloor);

        car.setCurrentFloor(targetFloor);
        System.out.println("Elevator " + car.getId() + " arrived at floor " + targetFloor + " [Door OPEN -> Door CLOSE]");
    }
}
