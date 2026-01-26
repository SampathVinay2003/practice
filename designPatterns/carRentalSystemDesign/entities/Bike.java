package designPatterns.carRentalSystemDesign.entities;

import designPatterns.carRentalSystemDesign.enums.VehicleStatus;
import designPatterns.carRentalSystemDesign.enums.VehicleType;

public class Bike extends Vehicle {
    private static final double HELMET_CHARGE = 50.0;
    private boolean hasHelmet;

    public Bike(String vehicleId, String name, String model, int year, String registrationNumber) {
        this.vehicleId = vehicleId;
        this.name = name;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.vehicleType = VehicleType.BIKE;
        this.status = VehicleStatus.AVAILABLE;
        this.hasHelmet = true;
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
}
