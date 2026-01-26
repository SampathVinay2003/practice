package designPatterns.parkingLot.entities;

import designPatterns.abstractFactoryDesignPattern.products.Vehicle;
import designPatterns.parkingLot.enums.VehicleType;
import designPatterns.parkingLot.strategy.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;

public class ParkingHall {
    private static int idCounter = 1;
    private int id;
    private int totalSpots;
    private List<ParkingSpot> parkingSpotList;
    
    public ParkingHall(int totalSpots) {
        this.id = idCounter++;
        this.totalSpots = totalSpots;
        parkingSpotList = new ArrayList<>(totalSpots);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalSpots() {
        return totalSpots;
    }

    public void setTotalSpots(int totalSpots) {
        this.totalSpots = totalSpots;
    }

    public List<ParkingSpot> getAvailableParkingSpotList() {
        return parkingSpotList;
    }

    public void setParkingSpotList(List<ParkingSpot> parkingSpotList) {
        this.parkingSpotList = parkingSpotList;
    }

    public ParkingSpot hasAvailableSpots(VehicleType vehicleType, ParkingStrategy parkingStrategy, Vehicle vehicle) {
        return parkingStrategy.findSpace(vehicleType, parkingSpotList, this.id, vehicle);
    }
}
