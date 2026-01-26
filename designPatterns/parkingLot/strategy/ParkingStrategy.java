package designPatterns.parkingLot.strategy;

import designPatterns.abstractFactoryDesignPattern.products.Vehicle;
import designPatterns.parkingLot.entities.ParkingSpot;
import designPatterns.parkingLot.enums.VehicleType;

import java.util.List;

public interface ParkingStrategy {
    ParkingSpot findSpace(VehicleType vehicleType, List<ParkingSpot> parkingSpotList, int parkingHallId, Vehicle vehicle);
}
