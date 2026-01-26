package designPatterns.elevatorDesignPractice.observer;

import designPatterns.elevatorDesignPractice.entities.ElevatorCar;
import designPatterns.elevatorDesignPractice.enums.ElevatorState;

public class MonitoringSystemObserver implements ElevatorObserver {
    
    @Override
    public void onFloorChanged(ElevatorCar elevator, int oldFloor, int newFloor) {
        System.out.println("[Monitoring System] Tracking: Elevator " + elevator.getId() + 
                         " at floor " + newFloor);
    }
    
    @Override
    public void onStateChanged(ElevatorCar elevator, ElevatorState oldState, ElevatorState newState) {
        if (newState == ElevatorState.IDLE) {
            System.out.println("[Monitoring System] Alert: Elevator " + elevator.getId() + 
                             " is now available");
        }
    }
}
