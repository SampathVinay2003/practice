package designPatterns.elevatorSystemDesign.entities;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private List<Floor> floors;
    private List<ElevatorCar> elevatorCars;
    private int totalFloors;
    
    public Building(int totalFloors, int totalElevators) {
        this.totalFloors = totalFloors;
        this.floors = new ArrayList<>();
        this.elevatorCars = new ArrayList<>();
        
        for (int i = 0; i < totalFloors; i++) {
            floors.add(new Floor(i));
        }
        
        for (int i = 0; i < totalElevators; i++) {
            elevatorCars.add(new ElevatorCar());
        }
    }
    
    public List<Floor> getFloors() {
        return floors;
    }
    
    public List<ElevatorCar> getElevatorCars() {
        return elevatorCars;
    }
    
    public int getTotalFloors() {
        return totalFloors;
    }
}
