package designPatterns.elevatorDesignPractice.strategy;

import designPatterns.elevatorDesignPractice.entities.ElevatorCar;

import java.util.List;

public class OddEvenFloorStrategy implements SelectionStrategy {
    
    @Override
    public ElevatorCar selectBestElevator(List<ElevatorCar> elevatorCars, int requestFloor) {
        if (elevatorCars.isEmpty()) {
            return null;
        }
        
        boolean isOddFloor = requestFloor % 2 != 0;
        
        for (ElevatorCar car : elevatorCars) {
            boolean isOddElevator = car.getId() % 2 != 0;
            
            if (isOddFloor == isOddElevator) {
                return car;
            }
        }
        
        return elevatorCars.get(0);
    }
}
