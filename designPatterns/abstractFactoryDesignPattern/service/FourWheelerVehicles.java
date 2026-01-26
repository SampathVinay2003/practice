package designPatterns.abstractFactoryDesignPattern.service;

import designPatterns.abstractFactoryDesignPattern.products.AbstractVehicle;

public class FourWheelerVehicles extends AbstractVehicle {

    @Override
    public void start() {
        System.out.println("Four Wheeler: Starting the car with ignition");
        notifyObservers("Vehicle started");
    }

    @Override
    public void stop() {
        System.out.println("Four Wheeler: Stopping the car");
        notifyObservers("Vehicle stopped");
    }

    @Override
    public String getType() {
        return "Car";
    }

    public void run() {
        System.out.println("Four Wheeler is running");
    }
}
