package designPatterns.parkingLot.observer;

import designPatterns.parkingLot.entities.ParkingSpot;
import designPatterns.parkingLot.enums.VehicleType;

public class FourWheelerObserver implements ParkingSpotObserver{
    @Override
    public void update(ParkingSpot parkingSpot) {
        System.out.println("🔔 [FOUR WHEELER ALERT] Spot available - Hall: " + parkingSpot.getFloor() + ", Spot: " + parkingSpot.getId());
    }

    @Override
    public VehicleType getInterestedVehicleType() {
        return VehicleType.FourWheeler;
    }
}
