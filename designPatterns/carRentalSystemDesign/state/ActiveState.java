package designPatterns.carRentalSystemDesign.state;

import designPatterns.carRentalSystemDesign.entities.Booking;
import designPatterns.carRentalSystemDesign.enums.BookingStatus;
import designPatterns.carRentalSystemDesign.exceptions.InvalidBookingException;

public class ActiveState implements BookingState {
    @Override
    public void confirm(Booking booking) {
        throw new InvalidBookingException("Booking is already active");
    }

    @Override
    public void activate(Booking booking) {
        throw new InvalidBookingException("Booking is already active");
    }

    @Override
    public void complete(Booking booking) {
        booking.setStatus(BookingStatus.COMPLETED);
    }

    @Override
    public void cancel(Booking booking) {
        throw new InvalidBookingException("Cannot cancel active booking. Complete it first.");
    }
}
