package designPatterns.elevatorSystemDesign.selectionStrategies;

import designPatterns.elevatorSystemDesign.entities.ElevatorCar;
import designPatterns.elevatorSystemDesign.enums.ElevatorState;
import designPatterns.elevatorSystemDesign.request.ExternalRequest;

import java.util.List;

public class NearestCarStrategy implements ElevatorSelectionStrategy {
    @Override
    public ElevatorCar selectBestElevator(ExternalRequest request, List<ElevatorCar> elevatorCars) {
        int requestFloor = request.getCurrentFloorNumber();
        ElevatorCar nearest = null;
        int minDistance = Integer.MAX_VALUE;
        
        for (ElevatorCar car : elevatorCars) {
            if (car.getElevatorState() == ElevatorState.STATIC) {
                int distance = Math.abs(car.getCurrentFloor() - requestFloor);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearest = car;
                }
            }
        }
        
        if (nearest == null && !elevatorCars.isEmpty()) {
            nearest = elevatorCars.get(0);
        }
        
        return nearest;
    }
}
