package designPatterns.parkingLot.entities;

import designPatterns.abstractFactoryDesignPattern.products.Vehicle;
import designPatterns.parkingLot.enums.VehicleType;

public class ParkingSpot {
    int id;
    int value;
    int floor;
    int spotNumber;
    VehicleType vehicleType;
    boolean isAvailable;
    Vehicle vehicle;
    public  ParkingSpot(int floor, int spotNumber, VehicleType vehicleType) {
        this.floor = floor;
        this.spotNumber = spotNumber;
        this.vehicleType = vehicleType;
        this.isAvailable = true;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public  Vehicle getVehicle() {
        return vehicle;
    }
}
