package designPatterns.elevatorDesignPractice.observer;

import designPatterns.elevatorDesignPractice.entities.ElevatorCar;
import designPatterns.elevatorDesignPractice.request.InternalRequest;

import java.util.List;

public class InternalRequestObserverImpl implements RequestObserver{
    private List<ElevatorCar> elevatorCars;

    public InternalRequestObserverImpl(List<ElevatorCar> elevatorCars) {
        this.elevatorCars = elevatorCars;
    }
    
    @Override
    public void onInternalRequest(InternalRequest request) {
        ElevatorCar elevatorCar = elevatorCars.stream().filter(car -> car.getId() == request.getElevatorCarId()).findFirst().orElse(null);

        if (elevatorCar == null) {
            System.out.println("Invalid elevator ID: " + request.getElevatorCarId());
            return;
        }

        System.out.println("Internal request: Elevator " + request.getElevatorCarId() +
                " from floor " + request.getCurrentFloor() +
                " to floor " + request.getDestinationFloor());

        elevatorCar.addFloorToQueue(request.getDestinationFloor(), request.getDirection());
        elevatorCar.move();
    }
}
