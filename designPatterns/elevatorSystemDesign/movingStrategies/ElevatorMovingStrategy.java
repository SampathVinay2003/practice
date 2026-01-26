package designPatterns.elevatorSystemDesign.movingStrategies;

import designPatterns.elevatorSystemDesign.entities.ElevatorCar;

public interface ElevatorMovingStrategy {
    void move(ElevatorCar car);
}
