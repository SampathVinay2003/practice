package designPatterns.parkingLot.entities;

import designPatterns.parkingLot.enums.VehicleType;

import java.time.LocalDateTime;

public class Ticket {
    private static int idCounter = 1;
    private int id;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    private String vehicleNumber;
    private VehicleType vehicleType;
    private int parkingSpotId;
    private int parkingHallId;
    
    Ticket(){
        this.id = idCounter++;
        this.inTime = LocalDateTime.now();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getInTime() {
        return inTime;
    }

    public void setInTime(LocalDateTime inTime) {
        this.inTime = inTime;
    }

    public LocalDateTime getOutTime() {
        return outTime;
    }

    public void setOutTime(LocalDateTime outTime) {
        this.outTime = outTime;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(int parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public int getParkingHallId() {
        return parkingHallId;
    }

    public void setParkingHallId(int parkingHallId) {
        this.parkingHallId = parkingHallId;
    }
}
