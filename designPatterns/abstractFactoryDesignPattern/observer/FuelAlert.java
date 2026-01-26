package designPatterns.abstractFactoryDesignPattern.observer;

/**
 * Concrete Observer - Monitors vehicle fuel events
 */
public class FuelAlert implements VehicleObserver {
    
    @Override
    public void update(String vehicleName, String event) {
        System.out.println("⛽ [FUEL ALERT] " + vehicleName + ": " + event);
    }
}
