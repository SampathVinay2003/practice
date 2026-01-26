package designPatterns.strategyDesignPattern;

import designPatterns.strategyDesignPattern.vehicles.OffRoadVehicle;
import designPatterns.strategyDesignPattern.vehicles.PassengerVehicle;
import designPatterns.strategyDesignPattern.vehicles.SportVehicle;
import designPatterns.strategyDesignPattern.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;
// This is Behavioral Design Pattern
public class Main {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new PassengerVehicle());
        vehicles.add(new SportVehicle());
        vehicles.add(new OffRoadVehicle());
        vehicles.forEach(Vehicle::drive);
    }
}
