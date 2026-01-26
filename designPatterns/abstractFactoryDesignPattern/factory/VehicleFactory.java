package designPatterns.abstractFactoryDesignPattern.factory;

import designPatterns.abstractFactoryDesignPattern.products.Engine;
import designPatterns.abstractFactoryDesignPattern.products.Transmission;
import designPatterns.abstractFactoryDesignPattern.products.Vehicle;

/**
 * Abstract Factory interface for creating families of related vehicle components
 * This is the core of the Abstract Factory pattern
 */
public interface VehicleFactory {
    Vehicle createVehicle();

    Engine createEngine();

    Transmission createTransmission();
}
