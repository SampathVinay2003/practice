package designPatterns.abstractFactoryDesignPattern.observer;

/**
 * Concrete Observer - Monitors vehicle diagnostics events
 */
public class DiagnosticsAlert implements VehicleObserver {
    
    @Override
    public void update(String vehicleName, String event) {
        System.out.println("📊 [DIAGNOSTICS] " + vehicleName + ": " + event);
    }
}
