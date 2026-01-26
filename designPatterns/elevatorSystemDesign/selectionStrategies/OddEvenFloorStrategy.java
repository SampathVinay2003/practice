package designPatterns.elevatorSystemDesign.selectionStrategies;

import designPatterns.elevatorSystemDesign.entities.ElevatorCar;
import designPatterns.elevatorSystemDesign.request.ExternalRequest;

import java.util.List;

public class OddEvenFloorStrategy implements ElevatorSelectionStrategy {
    @Override
    public ElevatorCar selectBestElevator(ExternalRequest request, List<ElevatorCar> elevatorCars) {
        int requestFloor = request.getCurrentFloorNumber();
        
        for (ElevatorCar car : elevatorCars) {
            int carId = car.getElevatorCarId();
            
            if (requestFloor % 2 == 0 && carId % 2 == 0) {
                return car;
            } else if (requestFloor % 2 != 0 && carId % 2 != 0) {
                return car;
            }
        }
        
        return elevatorCars.isEmpty() ? null : elevatorCars.get(0);
    }
}
