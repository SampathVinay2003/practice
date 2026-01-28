package designPatterns.carRentalSystemDesign.entities;

import designPatterns.carRentalSystemDesign.enums.VehicleStatus;
import designPatterns.carRentalSystemDesign.enums.VehicleType;
import designPatterns.carRentalSystemDesign.observer.RequestObserver;

import java.util.ArrayList;
import java.util.List;

public class Bike extends Vehicle {
    private static final double HELMET_CHARGE = 50.0;
    private boolean hasHelmet;
    private List<RequestObserver> observers = new ArrayList<>();

    public Bike(String vehicleId, String name, String model, int year, String registrationNumber) {
        this.vehicleId = vehicleId;
        this.name = name;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.vehicleType = VehicleType.BIKE;
        this.status = VehicleStatus.AVAILABLE;
        this.hasHelmet = true;
        notify();
    }

    public boolean hasHelmet() {
        return hasHelmet;
    }

    public void setHasHelmet(boolean hasHelmet) {
        this.hasHelmet = hasHelmet;
    }

    @Override
    public boolean requiresInspection() {
        return mileage > 10000;
    }

    @Override
    public double getAddonPrice() {
        return hasHelmet ? HELMET_CHARGE : 0.0;
    }

    public void notifyObservers() {
        for (RequestObserver observer : observers) {
            observer.update();
        }
    }

    @Override
    public void addObserver(RequestObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(RequestObserver observer) {
        observers.remove(observer);
    }
}
