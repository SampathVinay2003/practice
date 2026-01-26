package designPatterns.parkingLot;

import designPatterns.abstractFactoryDesignPattern.VehicleFactoryProvider;
import designPatterns.abstractFactoryDesignPattern.factory.VehicleFactory;
import designPatterns.abstractFactoryDesignPattern.products.Vehicle;
import designPatterns.parkingLot.entities.ParkingHall;
import designPatterns.parkingLot.entities.ParkingSpot;
import designPatterns.parkingLot.enums.VehicleType;
import designPatterns.parkingLot.entities.ParkingLotManager;
import designPatterns.parkingLot.observer.FourWheelerObserver;
import designPatterns.parkingLot.observer.TwoWheelerObserver;
import designPatterns.parkingLot.strategy.NearByExitParkingStrategy;
import designPatterns.parkingLot.strategy.ParkingStrategy;
import designPatterns.parkingLot.strategy.UPIPay;
import designPatterns.parkingLot.entities.Ticket;

public class MainCode {
    public static void main(String[] args) {
        // SINGLETON PATTERN: Get ParkingLotManager instance
        ParkingLotManager parkingLotManager = ParkingLotManager.getInstance();
        
        // OBSERVER PATTERN: Register observers for spot availability notifications
        parkingLotManager.registerObserver(new TwoWheelerObserver());
        parkingLotManager.registerObserver(new FourWheelerObserver());
        
        // Setup parking halls (NOT singleton - we need multiple halls)
        parkingLotManager.addParkingHall(new ParkingHall(100));
        parkingLotManager.addParkingHall(new ParkingHall(150));
        parkingLotManager.addParkingHall(new ParkingHall(100));
        
        // ABSTRACT FACTORY PATTERN: Create vehicle
        VehicleFactory vehicleFactory = VehicleFactoryProvider.getFactory("twoWheeler");
        Vehicle vehicle = vehicleFactory.createVehicle();
        
        // STRATEGY PATTERN: Find parking spot using strategy
        ParkingStrategy parkingStrategy = new NearByExitParkingStrategy();
        ParkingSpot parkingSpot = parkingLotManager.findParkingSpot(parkingStrategy, VehicleType.TwoWheeler, vehicle);
        
        if (parkingSpot != null) {
            // STRATEGY PATTERN: Payment gateway
            if(parkingLotManager.doPayment(parkingSpot, new UPIPay())){
                System.out.println("✅ Payment Done - Vehicle parked in hall: " + parkingSpot.getFloor() + " spot: " + parkingSpot.getId());
                
                // Issue parking ticket
                Ticket ticket = parkingLotManager.issueTicket(parkingSpot, "KA-01-AB-1234");
                System.out.println("🎫 Ticket #" + ticket.getId() + " | Entry: " + ticket.getInTime() + " | Exit: " + ticket.getOutTime());
            } else {
                System.out.println("❌ Payment Failed - Releasing parking spot");
                // OBSERVER PATTERN: Observers automatically notified when spot is freed
                parkingLotManager.setParkingSpotFree(parkingSpot);
            }
        } else {
            System.out.println("❌ No parking spot available");
        }
    }
}
