package designPatterns.carRentalSystemDesign.entities;

import designPatterns.carRentalSystemDesign.enums.VehicleStatus;
import designPatterns.carRentalSystemDesign.enums.VehicleType;

public class Car extends Vehicle {
    private static final double GPS_CHARGE = 100.0;
    private static final double CHILD_SEAT_CHARGE = 75.0;
    
    private int seatingCapacity;
    private boolean hasGPS;
    private boolean hasChildSeat;

    public Car(String vehicleId, String name, String model, int year, String registrationNumber, int seatingCapacity) {
        this.vehicleId = vehicleId;
        this.name = name;
        this.model = model;
        this.year = year;
        this.registrationNumber = registrationNumber;
        this.vehicleType = VehicleType.CAR;
        this.status = VehicleStatus.AVAILABLE;
        this.seatingCapacity = seatingCapacity;
        this.hasGPS = false;
        this.hasChildSeat = false;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public boolean hasGPS() {
        return hasGPS;
    }

    public void setHasGPS(boolean hasGPS) {
        this.hasGPS = hasGPS;
    }

    public boolean hasChildSeat() {
        return hasChildSeat;
    }

    public void setHasChildSeat(boolean hasChildSeat) {
        this.hasChildSeat = hasChildSeat;
    }

    @Override
    public boolean requiresInspection() {
        return mileage > 15000;
    }

    @Override
    public double getAddonPrice() {
        double addon = 0.0;
        if (hasGPS) addon += GPS_CHARGE;
        if (hasChildSeat) addon += CHILD_SEAT_CHARGE;
        return addon;
    }
}
