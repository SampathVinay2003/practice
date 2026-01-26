package designPatterns.parkingLot.entities;

public class TicketManager {
    private static TicketManager instance = new TicketManager();

    private TicketManager(){
    }
    
    public static TicketManager getInstance() {
        return instance;
    }

    public Ticket issueTicket(ParkingSpot parkingSpot, String vehicleNumber) {
        Ticket ticket = new Ticket();  // ID and inTime set in constructor
        ticket.setVehicleNumber(vehicleNumber);
        ticket.setVehicleType(parkingSpot.getVehicleType());
        ticket.setParkingSpotId(parkingSpot.getId());
        ticket.setParkingHallId(parkingSpot.getFloor());
        return ticket;
    }
}
