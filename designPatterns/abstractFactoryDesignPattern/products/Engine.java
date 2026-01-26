package designPatterns.abstractFactoryDesignPattern.products;

/**
 * Product interface for Engine
 * Part of the product family in Abstract Factory pattern
 */
public interface Engine {
    void start();
    void stop();
    String getEngineType();
    int getHorsepower();
}
