package designPatterns.abstractFactoryDesignPattern.service;

import designPatterns.abstractFactoryDesignPattern.products.AbstractVehicle;

public class ThreeWheelerVehicles extends AbstractVehicle {

    @Override
    public void start() {
        System.out.println("Three Wheeler: Starting the auto-rickshaw");
        notifyObservers("Vehicle started");
    }

    @Override
    public void stop() {
        System.out.println("Three Wheeler: Stopping the auto-rickshaw");
        notifyObservers("Vehicle stopped");
    }

    @Override
    public String getType() {
        return "Auto-Rickshaw";
    }

    public void run() {
        System.out.println("Three Wheeler is running");
    }
}
