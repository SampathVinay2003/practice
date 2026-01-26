package designPatterns.elevatorDesignPractice.strategy;

import designPatterns.elevatorDesignPractice.entities.ElevatorCar;

import java.util.List;

public interface SelectionStrategy {
    ElevatorCar selectBestElevator(List<ElevatorCar> elevatorCars, int requestFloor);
}
