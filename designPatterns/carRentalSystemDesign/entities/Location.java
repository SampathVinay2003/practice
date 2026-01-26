package designPatterns.carRentalSystemDesign.entities;

import java.time.LocalTime;

public class Location {
    private final String locationId;
    private final String name;
    private final String address;
    private final String city;
    private final LocalTime openingTime;
    private final LocalTime closingTime;

    public Location(String locationId, String name, String address, String city, 
                   LocalTime openingTime, LocalTime closingTime) {
        this.locationId = locationId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public boolean isOperational(LocalTime time) {
        return !time.isBefore(openingTime) && !time.isAfter(closingTime);
    }
}
