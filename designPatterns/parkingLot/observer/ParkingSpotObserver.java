package designPatterns.parkingLot.observer;

import designPatterns.parkingLot.entities.ParkingSpot;
import designPatterns.parkingLot.enums.VehicleType;

public interface ParkingSpotObserver {
    void update(ParkingSpot parkingSpot);
    VehicleType getInterestedVehicleType();
}
