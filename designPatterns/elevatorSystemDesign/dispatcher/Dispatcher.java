package designPatterns.elevatorSystemDesign.dispatcher;

import designPatterns.elevatorSystemDesign.entities.ElevatorCar;
import designPatterns.elevatorSystemDesign.request.ExternalRequest;
import designPatterns.elevatorSystemDesign.selectionStrategies.ElevatorSelectionStrategy;

import java.util.List;

public class Dispatcher {
    private ElevatorSelectionStrategy selectionStrategy;
    
    public Dispatcher(ElevatorSelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }
    
    public ElevatorCar selectElevator(ExternalRequest request, List<ElevatorCar> elevatorCars) {
        return selectionStrategy.selectBestElevator(request, elevatorCars);
    }
    
    public void setSelectionStrategy(ElevatorSelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }
}
