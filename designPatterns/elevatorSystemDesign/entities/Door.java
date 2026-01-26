package designPatterns.elevatorSystemDesign.entities;

import designPatterns.elevatorSystemDesign.enums.DoorState;

public class Door {
    private DoorState doorState;
    
    public Door() {
        this.doorState = DoorState.CLOSED;
    }
    
    public void open() {
        if (doorState == DoorState.CLOSED) {
            doorState = DoorState.OPENING;
            System.out.println("Door opening...");
            doorState = DoorState.OPEN;
            System.out.println("Door opened");
        }
    }
    
    public void close() {
        if (doorState == DoorState.OPEN) {
            doorState = DoorState.CLOSING;
            System.out.println("Door closing...");
            doorState = DoorState.CLOSED;
            System.out.println("Door closed");
        }
    }
    
    public DoorState getDoorState() {
        return doorState;
    }
}
