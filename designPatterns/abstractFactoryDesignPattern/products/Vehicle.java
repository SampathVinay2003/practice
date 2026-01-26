package designPatterns.abstractFactoryDesignPattern.products;

/**
 * Product interface for Vehicle
 * Part of the product family in Abstract Factory pattern
 * Note: Observer methods are NOT in this interface - they're encapsulated in AbstractVehicle
 */
public interface Vehicle {
    void getName();

    void start();

    void stop();

    String getType();

    Vehicle setEngine(Engine engine);

    Vehicle setTransmission(Transmission transmission);

    Engine getEngine();

    Vehicle build();

    Vehicle setName(String mcg);
}
