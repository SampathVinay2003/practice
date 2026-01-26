package designPatterns.elevatorSystemDesign.entities;

import designPatterns.elevatorSystemDesign.ElevatorController;
import designPatterns.elevatorSystemDesign.request.InternalRequest;

public class InternalButton extends ButtonBase {
    private int elevatorCarId;

    public InternalButton(int floorNumber, int elevatorCarId){
        super(floorNumber);
        this.elevatorCarId = elevatorCarId;
    }

    @Override
    public void press() {
        this.isPressed = true;
        System.out.println("Internal button pressed for floor " + floorNumber);
        
        ElevatorController controller = ElevatorController.getInstance();
        ElevatorCar car = controller.getElevatorCar(elevatorCarId);
        InternalRequest request = new InternalRequest(car.getCurrentFloor(), floorNumber, elevatorCarId);
        controller.processRequest(request);
    }
}
