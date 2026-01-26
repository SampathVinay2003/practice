package designPatterns.abstractFactoryDesignPattern.service;

import designPatterns.abstractFactoryDesignPattern.products.AbstractVehicle;

public class TwoWheelerVehicles extends AbstractVehicle {

    @Override
    public void start() {
        System.out.println("Two Wheeler: Starting the motorcycle with ignition");
        notifyObservers("Vehicle started");
    }

    @Override
    public void stop() {
        System.out.println("Two Wheeler: Stopping the motorcycle");
        notifyObservers("Vehicle stopped");
    }

    @Override
    public String getType() {
        return "Motorcycle";
    }

    public void run() {
        System.out.println("Two Wheeler is running");
    }
}
