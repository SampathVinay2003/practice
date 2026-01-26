package designPatterns.elevatorDesignPractice.strategy;

import designPatterns.elevatorDesignPractice.entities.ElevatorCar;
import designPatterns.elevatorDesignPractice.enums.ElevatorState;

import java.util.List;


public class NearestStrategy implements SelectionStrategy {
    private static NearestStrategy instance;
    private NearestStrategy(){}
    public static NearestStrategy getInstance(){
        if (instance == null){
            return instance = new NearestStrategy();
        }
        return instance;
    }
    @Override
    public ElevatorCar selectBestElevator(List<ElevatorCar> elevatorCars, int requestFloor) {
        ElevatorCar nearest = null;
        int minDistance = Integer.MAX_VALUE;

        for (ElevatorCar car : elevatorCars) {
            if (car.getElevatorState() == ElevatorState.IDLE) {
                int distance = Math.abs(car.getCurrentFloor() - requestFloor);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearest = car;
                }
                if (distance == 0) {
                    return car;
                }
            }
        }

        if (nearest == null && !elevatorCars.isEmpty()) {
            nearest = elevatorCars.get(0);
        }

        return nearest;
    }
}
