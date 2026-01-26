package designPatterns.elevatorDesignPractice.observer;

import designPatterns.elevatorDesignPractice.entities.ElevatorCar;
import designPatterns.elevatorDesignPractice.enums.Direction;
import designPatterns.elevatorDesignPractice.enums.ElevatorState;

public class DisplayPanelObserver implements ElevatorObserver {
    private String panelLocation;
    
    public DisplayPanelObserver(String panelLocation) {
        this.panelLocation = panelLocation;
    }
    
    @Override
    public void onFloorChanged(ElevatorCar elevator, int oldFloor, int newFloor) {
        System.out.println("[Display Panel - " + panelLocation + "] Elevator " + elevator.getId() + 
                         " moved: Floor " + oldFloor + " → " + newFloor);
    }
    
    @Override
    public void onStateChanged(ElevatorCar elevator, ElevatorState oldState, ElevatorState newState) {
        System.out.println("[Display Panel - " + panelLocation + "] Elevator " + elevator.getId() + 
                         " state: " + oldState + " → " + newState);
    }
    
    @Override
    public void onDirectionChanged(ElevatorCar elevator, Direction oldDirection, Direction newDirection) {
        System.out.println("[Display Panel - " + panelLocation + "] Elevator " + elevator.getId() + 
                         " direction: " + oldDirection + " → " + newDirection);
    }
    
    @Override
    public void onRequestAdded(ElevatorCar elevator, int floor, Direction direction) {
        System.out.println("[Display Panel - " + panelLocation + "] Elevator " + elevator.getId() + 
                         " added request: Floor " + floor + " (" + direction + ")");
    }
}
