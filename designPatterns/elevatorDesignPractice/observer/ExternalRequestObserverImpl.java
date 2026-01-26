package designPatterns.elevatorDesignPractice.observer;

import designPatterns.elevatorDesignPractice.entities.ElevatorCar;
import designPatterns.elevatorDesignPractice.request.ExternalRequest;
import designPatterns.elevatorDesignPractice.strategy.SelectionStrategy;

import java.util.List;

public class ExternalRequestObserverImpl implements RequestObserver{
    private SelectionStrategy selectionStrategy;
    private List<ElevatorCar> elevatorCars;

    public ExternalRequestObserverImpl(SelectionStrategy selectionStrategy, List<ElevatorCar> elevatorCars) {
        this.selectionStrategy = selectionStrategy;
        this.elevatorCars = elevatorCars;
    }
    
    @Override
    public void onExternalRequest(ExternalRequest request) {
        ElevatorCar carToBeAssigned = selectionStrategy.selectBestElevator(elevatorCars, request.getCurrentFloor());

        if (carToBeAssigned == null) {
            System.out.println("No elevator available for external request at floor " + request.getCurrentFloor());
            return;
        }

        System.out.println("External request: Floor " + request.getCurrentFloor() +
                " going " + request.getDirection() + " -> Assigned to Elevator " + carToBeAssigned.getId());

        carToBeAssigned.addFloorToQueue(request.getCurrentFloor(), request.getDirection());
        carToBeAssigned.move();
    }
}
