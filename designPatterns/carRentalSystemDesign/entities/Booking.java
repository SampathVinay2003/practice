package designPatterns.carRentalSystemDesign.entities;

import designPatterns.carRentalSystemDesign.enums.BookingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {
    private final String bookingId;
    private final User user;
    private final Vehicle vehicle;
    private final LocalDateTime bookingTime;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private LocalDateTime actualReturnTime;
    private BookingStatus status;
    private double estimatedCost;
    private double actualCost;
    private String locationId;
    private String returnLocationId;

    private Booking(Builder builder) {
        this.bookingId = UUID.randomUUID().toString();
        this.user = builder.user;
        this.vehicle = builder.vehicle;
        this.bookingTime = LocalDateTime.now();
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.status = BookingStatus.PENDING_PAYMENT;
        this.estimatedCost = builder.estimatedCost;
        this.locationId = builder.locationId;
        this.returnLocationId = builder.returnLocationId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getActualReturnTime() {
        return actualReturnTime;
    }

    public void setActualReturnTime(LocalDateTime actualReturnTime) {
        this.actualReturnTime = actualReturnTime;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public double getEstimatedCost() {
        return estimatedCost;
    }

    public double getActualCost() {
        return actualCost;
    }

    public void setActualCost(double actualCost) {
        this.actualCost = actualCost;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getReturnLocationId() {
        return returnLocationId;
    }

    public static class Builder {
        private User user;
        private Vehicle vehicle;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private double estimatedCost;
        private String locationId;
        private String returnLocationId;

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder vehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public Builder startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder estimatedCost(double estimatedCost) {
            this.estimatedCost = estimatedCost;
            return this;
        }

        public Builder locationId(String locationId) {
            this.locationId = locationId;
            return this;
        }

        public Builder returnLocationId(String returnLocationId) {
            this.returnLocationId = returnLocationId;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}
