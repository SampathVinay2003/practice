package designPatterns.elevatorDesignPractice.observer;

import designPatterns.elevatorDesignPractice.entities.ElevatorCar;
import designPatterns.elevatorDesignPractice.enums.Direction;
import designPatterns.elevatorDesignPractice.enums.ElevatorState;

public interface ElevatorObserver {
    default void onFloorChanged(ElevatorCar elevator, int oldFloor, int newFloor) {}
    default void onStateChanged(ElevatorCar elevator, ElevatorState oldState, ElevatorState newState) {}
    default void onDirectionChanged(ElevatorCar elevator, Direction oldDirection, Direction newDirection) {}
    default void onRequestAdded(ElevatorCar elevator, int floor, Direction direction) {}
}
