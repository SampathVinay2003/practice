package designPatterns.abstractFactoryDesignPattern.decorator;

import designPatterns.abstractFactoryDesignPattern.products.Vehicle;

/**
 * Concrete Decorator - Adds GPS Navigation feature
 */
public class GPSDecorator extends VehicleDecorator {

    public GPSDecorator(Vehicle vehicle) {
        super(vehicle);
    }

    public void start() {
        System.out.println("🛰️  GPS Navigation: System activated and ready");
    }

    @Override
    public String getType() {
        return vehicle.getType() + " with GPS Navigation";
    }
}
