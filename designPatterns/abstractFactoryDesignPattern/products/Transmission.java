package designPatterns.abstractFactoryDesignPattern.products;

/**
 * Product interface for Transmission
 * Part of the product family in Abstract Factory pattern
 */
public interface Transmission {
    void shiftGear(int gear);
    String getTransmissionType();
    int getCurrentGear();
}
