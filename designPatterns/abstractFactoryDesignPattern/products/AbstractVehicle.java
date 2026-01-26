package designPatterns.abstractFactoryDesignPattern.products;

import designPatterns.abstractFactoryDesignPattern.observer.VehicleObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for Vehicle implementations
 * Encapsulates observer management - decorators cannot access these methods
 */
public abstract class AbstractVehicle implements Vehicle {
    protected String name;
    protected Engine engine;
    protected Transmission transmission;
    private final List<VehicleObserver> observers = new ArrayList<>();

    @Override
    public void getName() {
        if (name != null) System.out.println(name);
    }

    @Override
    public Vehicle setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    @Override
    public Vehicle setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    @Override
    public Engine getEngine() {
        return engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    @Override
    public Vehicle build() {
        return this;
    }

    @Override
    public Vehicle setName(String name) {
        this.name = name;
        return this;
    }

    // Observer management - encapsulated, not accessible through decorators
    public void addObserver(VehicleObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(VehicleObserver observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String event) {
        for (VehicleObserver observer : observers) {
            observer.update(name != null ? name : getType(), event);
        }
    }

    // Abstract methods that concrete vehicles must implement
    @Override
    public abstract void start();

    @Override
    public abstract void stop();

    @Override
    public abstract String getType();
}
