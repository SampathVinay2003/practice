package designPatterns.elevatorDesignPractice;

import designPatterns.elevatorDesignPractice.entities.ElevatorCar;
import designPatterns.elevatorDesignPractice.entities.FloorButton;
import designPatterns.elevatorDesignPractice.enums.Direction;
import designPatterns.elevatorDesignPractice.movingStrategies.LOOK;
import designPatterns.elevatorDesignPractice.movingStrategies.SCAN;
import designPatterns.elevatorDesignPractice.observer.DisplayPanelObserver;
import designPatterns.elevatorDesignPractice.observer.ExternalRequestObserverImpl;
import designPatterns.elevatorDesignPractice.observer.InternalRequestObserverImpl;
import designPatterns.elevatorDesignPractice.observer.MonitoringSystemObserver;
import designPatterns.elevatorDesignPractice.strategy.NearestStrategy;

public class ElevatorSystem {
    public static void main(String[] args) {
        // Initialize system
        NearestStrategy nearestStrategy = NearestStrategy.getInstance();
        ElevatorController elevatorController = ElevatorController.getInstance(nearestStrategy);

        SCAN scan = SCAN.getInstance();
        LOOK look = LOOK.getInstance();

        // Create elevators
        ElevatorCar elevatorCar1 = new ElevatorCar(scan);
        ElevatorCar elevatorCar2 = new ElevatorCar(look);
        elevatorCar2.setCurrentFloor(2);

        // Add observers to elevators (for monitoring state changes)
        elevatorCar1.addObserver(new DisplayPanelObserver("Lobby"));
        elevatorCar1.addObserver(new MonitoringSystemObserver());
        elevatorCar2.addObserver(new DisplayPanelObserver("Floor 5"));
        elevatorCar2.addObserver(new MonitoringSystemObserver());

        // Add elevators to controller
        elevatorController.addElevatorCar(elevatorCar1);
        elevatorController.addElevatorCar(elevatorCar2);

        // Create request observers
        ExternalRequestObserverImpl externalRequestObserver = new ExternalRequestObserverImpl(
            elevatorController.getSelectionStrategy(), 
            elevatorController.getElevators()
        );
        
        InternalRequestObserverImpl internalRequestObserver = new InternalRequestObserverImpl(
            elevatorController.getElevators()
        );

        System.out.println("=== Scenario 1: External Request (Floor Button) ===");
        // Create floor button and register external request observer
        FloorButton floorButton = new FloorButton(3, Direction.UP);
        floorButton.setRequestObserver(externalRequestObserver);

        // Press button - automatically notifies observer!
        floorButton.press();

        System.out.println("\n=== Scenario 2: Internal Request (Elevator Button) ===");
        // Set internal request observer for elevators
        elevatorCar1.setRequestObserver(internalRequestObserver);
        elevatorCar2.setRequestObserver(internalRequestObserver);
        
        // Press button inside elevator - automatically notifies observer!
        elevatorCar1.pressButton(8);

        System.out.println("\n=== Scenario 3: Another Floor Button ===");
        FloorButton floorButton2 = new FloorButton(5, Direction.DOWN);
        floorButton2.setRequestObserver(externalRequestObserver);
        floorButton2.press();
    }
}
