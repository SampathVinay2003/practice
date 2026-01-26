package designPatterns.elevatorDesignPractice;

import designPatterns.elevatorDesignPractice.entities.ElevatorCar;
import designPatterns.elevatorDesignPractice.strategy.SelectionStrategy;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    private List<ElevatorCar> elevatorCars = new ArrayList<>();
    private SelectionStrategy selectionStrategy;
    private static ElevatorController instance;

    private ElevatorController(SelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    public static ElevatorController getInstance(SelectionStrategy selectionStrategy) {
        if (instance == null) {
            synchronized (ElevatorController.class) {
                return instance = new ElevatorController(selectionStrategy);
            }
        }
        return instance;
    }

    public void addElevatorCar(ElevatorCar elevatorCar) {
        elevatorCars.add(elevatorCar);
    }
    
    public List<ElevatorCar> getElevators() {
        return elevatorCars;
    }
    
    public SelectionStrategy getSelectionStrategy() {
        return selectionStrategy;
    }
}
