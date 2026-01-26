package designPatterns.elevatorSystemDesign.selectionStrategies;

import designPatterns.elevatorSystemDesign.entities.ElevatorCar;
import designPatterns.elevatorSystemDesign.request.ExternalRequest;

import java.util.List;

public interface ElevatorSelectionStrategy {
    ElevatorCar selectBestElevator(ExternalRequest request, List<ElevatorCar> elevatorCars);
}
