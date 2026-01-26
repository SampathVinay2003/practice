package designPatterns.elevatorSystemDesign.entities;

import designPatterns.elevatorSystemDesign.ElevatorController;
import designPatterns.elevatorSystemDesign.enums.Direction;
import designPatterns.elevatorSystemDesign.request.ExternalRequest;

public class ExternalButton extends ButtonBase{
    private Direction direction;

    public ExternalButton(int floorNumber, Direction direction){
        super(floorNumber);
        this.direction = direction;
    }

    @Override
    public void press() {
        this.isPressed = true;
        System.out.println("External button pressed at floor " + floorNumber + " for direction " + direction);
        
        ElevatorController controller = ElevatorController.getInstance();
        ExternalRequest request = new ExternalRequest(floorNumber, 
                                                      direction == Direction.UP ? floorNumber + 1 : floorNumber - 1, 
                                                      0);
        controller.processRequest(request);
    }
}
