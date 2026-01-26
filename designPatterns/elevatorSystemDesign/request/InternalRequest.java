package designPatterns.elevatorSystemDesign.request;

import designPatterns.elevatorSystemDesign.dispatcher.Dispatcher;
import designPatterns.elevatorSystemDesign.entities.ElevatorCar;
import designPatterns.elevatorSystemDesign.enums.Direction;

import java.util.List;

public class InternalRequest extends Request{

    public InternalRequest(int currentFloorNumber, int destinationFloorNumber, int elevatorCarId) {
        super(currentFloorNumber, destinationFloorNumber, elevatorCarId, destinationFloorNumber > currentFloorNumber ? Direction.UP : Direction.DOWN);
    }
    @Override
    public ElevatorCar getElevatorCar(Dispatcher dispatcher, List<ElevatorCar> elevatorCars) {
        return elevatorCars.get(this.getElevatorCarId());
    }
}
