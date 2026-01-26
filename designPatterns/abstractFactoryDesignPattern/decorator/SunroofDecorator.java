package designPatterns.abstractFactoryDesignPattern.decorator;

import designPatterns.abstractFactoryDesignPattern.products.Vehicle;

/**
 * Concrete Decorator - Adds Sunroof feature
 */
public class SunroofDecorator extends VehicleDecorator {

    public SunroofDecorator(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    public void start() {
        System.out.println("☀️  Sunroof: Opening panoramic sunroof");
    }

    @Override
    public String getType() {
        return vehicle.getType() + " with Sunroof";
    }
}
