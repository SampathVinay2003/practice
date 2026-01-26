package designPatterns.elevatorDesignPractice.entities;

import designPatterns.elevatorDesignPractice.enums.Direction;
import designPatterns.elevatorDesignPractice.observer.RequestObserver;
import designPatterns.elevatorDesignPractice.request.ExternalRequest;

public class FloorButton {
    private int floorNumber;
    private Direction direction;
    private RequestObserver requestObserver;
    
    public FloorButton(int floorNumber, Direction direction) {
        this.floorNumber = floorNumber;
        this.direction = direction;
    }
    
    public void setRequestObserver(RequestObserver requestObserver) {
        this.requestObserver = requestObserver;
    }

    public void press() {
        ExternalRequest request = new ExternalRequest(floorNumber, direction);
        System.out.println("Floor button pressed at floor " + floorNumber + " for direction " + direction);

        if (requestObserver != null) {
            requestObserver.onExternalRequest(request);
        }
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Direction getDirection() {
        return direction;
    }
}
