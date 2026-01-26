package designPatterns.abstractFactoryDesignPattern.decorator;

import designPatterns.abstractFactoryDesignPattern.products.Vehicle;

/**
 * Concrete Decorator - Adds Premium Sound System feature
 */
public class PremiumSoundDecorator extends VehicleDecorator {

    public PremiumSoundDecorator(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    public void start() {
        System.out.println("🔊 Premium Sound: Bose surround sound system activated");
    }

    @Override
    public String getType() {
        return vehicle.getType() + " with Premium Sound";
    }
}
