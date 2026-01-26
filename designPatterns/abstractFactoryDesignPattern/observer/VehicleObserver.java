package designPatterns.abstractFactoryDesignPattern.observer;

/**
 * Observer interface for vehicle state monitoring
 */
public interface VehicleObserver {
    void update(String vehicleName, String event);
}
