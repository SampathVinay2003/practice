package designPatterns.parkingLot.strategy;

import designPatterns.abstractFactoryDesignPattern.products.Vehicle;
import designPatterns.parkingLot.entities.ParkingSpot;
import designPatterns.parkingLot.enums.VehicleType;

import java.util.List;

public class NearByExitParkingStrategy implements ParkingStrategy{
    @Override
    public ParkingSpot findSpace(VehicleType vehicleType, List<ParkingSpot> parkingSpotList, int parkingHallId, Vehicle vehicle) {
        List<ParkingSpot>listNearByExit = parkingSpotList.stream().filter(parkingSpot -> parkingSpot.getVehicleType() == vehicleType && parkingSpot.isAvailable()).toList();

        if(!listNearByExit.isEmpty()){
            ParkingSpot parkingSpot = listNearByExit.getFirst();
            parkingSpot.setAvailable(false);
            parkingSpot.setVehicleType(vehicleType);
            parkingSpot.setFloor(parkingHallId);
            parkingSpot.setVehicle(vehicle);
            parkingSpot.setValue(getPriceForVehicleType(vehicleType));
            return parkingSpot;
        }
        return null;
    }

    private int getPriceForVehicleType(VehicleType vehicleType) {
        return switch (vehicleType) {
            case TwoWheeler -> 50;
            case FourWheeler -> 100;
            case SixWheeler -> 150;
        };
    }
}
