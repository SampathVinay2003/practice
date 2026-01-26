package designPatterns.parkingLot.entities;

import designPatterns.abstractFactoryDesignPattern.products.Vehicle;
import designPatterns.parkingLot.enums.VehicleType;
import designPatterns.parkingLot.observer.ParkingSpotObserver;
import designPatterns.parkingLot.strategy.ParkingStrategy;
import designPatterns.parkingLot.strategy.PaymentGateway;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton ParkingLotManager
 * Manages all parking halls and provides centralized parking operations
 */
public class ParkingLotManager {
    private static ParkingLotManager instance;
    private List<ParkingHall> parkingHallList;
    private final List<ParkingSpotObserver> parkingSpotObserverList = new ArrayList<>();
    private ParkingLotManager() {
        parkingHallList = new ArrayList<>();
    }

    public static ParkingLotManager getInstance() {
        if (instance == null) {
            synchronized (ParkingLotManager.class) {
                if (instance == null) {
                    instance = new ParkingLotManager();
                }
            }
        }
        return instance;
    }

    public void addParkingHall(ParkingHall parkingHall) {
        parkingHallList.add(parkingHall);
    }

    public ParkingSpot findParkingSpot(ParkingStrategy parkingStrategy, VehicleType vehicleType, Vehicle vehicle) {
        for (ParkingHall parkingHall : parkingHallList) {
            if (parkingHall.getTotalSpots() > 0) {
                ParkingSpot parkingSpot = parkingHall.hasAvailableSpots(vehicleType, parkingStrategy, vehicle);
                if (parkingSpot != null) {
                    return parkingSpot;
                }
            }
        }
        return null;
    }

    public List<ParkingHall> getParkingHallList() {
        return parkingHallList;
    }

    public boolean doPayment(ParkingSpot parkingSpot, PaymentGateway paymentGateway) {
        return paymentGateway.pay(parkingSpot.getValue());
    }

    public void setParkingSpotFree(ParkingSpot parkingSpot) {
        VehicleType vehicleType = parkingSpot.getVehicleType();
        parkingSpot.setVehicle(null);
        parkingSpot.setAvailable(true);
        
        // OBSERVER PATTERN: Notify interested observers about available spot
        notifyObservers(parkingSpot, vehicleType);
    }

    public void registerObserver(ParkingSpotObserver parkingSpotObserver) {
        parkingSpotObserverList.add(parkingSpotObserver);
    }
    
    public void unregisterObserver(ParkingSpotObserver parkingSpotObserver) {
        parkingSpotObserverList.remove(parkingSpotObserver);
    }
    
    private void notifyObservers(ParkingSpot parkingSpot, VehicleType vehicleType) {
        for (ParkingSpotObserver observer : parkingSpotObserverList) {
            if (observer.getInterestedVehicleType() == vehicleType) {
                observer.update(parkingSpot);
            }
        }
    }

    public ParkingHall getParkingHall(int floor) {
        return parkingHallList.stream().filter(parkingHall -> parkingHall.getId() == floor).findFirst().orElse(null);
    }

    public Ticket issueTicket(ParkingSpot parkingSpot, String vehicleNumber) {
        return TicketManager.getInstance().issueTicket(parkingSpot, vehicleNumber);
    }
}
