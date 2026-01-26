package designPatterns.abstractFactoryDesignPattern.decorator;

import designPatterns.abstractFactoryDesignPattern.products.Engine;
import designPatterns.abstractFactoryDesignPattern.products.Transmission;
import designPatterns.abstractFactoryDesignPattern.products.Vehicle;

/**
 * Abstract Decorator for Vehicle
 * Allows adding features dynamically to vehicles
 * Note: Observer methods are NOT exposed through decorators - they're encapsulated in AbstractVehicle
 */
public abstract class VehicleDecorator implements Vehicle {
    protected Vehicle vehicle;

    public VehicleDecorator(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void getName() {
        vehicle.getName();
    }

    @Override
    public void start() {
        vehicle.start();
    }

    @Override
    public void stop() {
        vehicle.stop();
    }

    @Override
    public String getType() {
        return vehicle.getType();
    }

    @Override
    public Vehicle setEngine(Engine engine) {
        return vehicle.setEngine(engine);
    }

    @Override
    public Vehicle setTransmission(Transmission transmission) {
        return vehicle.setTransmission(transmission);
    }

    @Override
    public Engine getEngine() {
        return vehicle.getEngine();
    }

    @Override
    public Vehicle build() {
        return vehicle.build();
    }

    @Override
    public Vehicle setName(String name) {
        return vehicle.setName(name);
    }
}
