package designPatterns.elevatorDesignPractice.strategy;

import designPatterns.elevatorDesignPractice.entities.ElevatorCar;

import java.util.List;

public class LeastLoadedStrategy implements SelectionStrategy {
    
    @Override
    public ElevatorCar selectBestElevator(List<ElevatorCar> elevatorCars, int requestFloor) {
        if (elevatorCars.isEmpty()) {
            return null;
        }
        
        ElevatorCar leastLoaded = null;
        int minRequests = Integer.MAX_VALUE;
        
        for (ElevatorCar car : elevatorCars) {
            int totalRequests = car.getUpQueue().size() + car.getDownQueue().size();
            
            if (totalRequests < minRequests) {
                minRequests = totalRequests;
                leastLoaded = car;
            }
            if (totalRequests == 0) {
                return car;
            }
        }
        
        return leastLoaded != null ? leastLoaded : elevatorCars.get(0);
    }
}
