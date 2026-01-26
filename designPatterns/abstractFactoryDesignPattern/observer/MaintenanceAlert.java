package designPatterns.abstractFactoryDesignPattern.observer;

/**
 * Concrete Observer - Monitors vehicle maintenance events
 */
public class MaintenanceAlert implements VehicleObserver {
    
    @Override
    public void update(String vehicleName, String event) {
        System.out.println("🔧 [MAINTENANCE ALERT] " + vehicleName + ": " + event);
    }
}
