package designPatterns.carRentalSystemDesign.state;

import designPatterns.carRentalSystemDesign.entities.Booking;
import designPatterns.carRentalSystemDesign.enums.BookingStatus;
import designPatterns.carRentalSystemDesign.exceptions.InvalidBookingException;

public class ConfirmedState implements BookingState {
    @Override
    public void confirm(Booking booking) {
        throw new InvalidBookingException("Booking is already confirmed");
    }

    @Override
    public void activate(Booking booking) {
        booking.setStatus(BookingStatus.ACTIVE);
    }

    @Override
    public void complete(Booking booking) {
        throw new InvalidBookingException("Cannot complete booking that hasn't started");
    }

    @Override
    public void cancel(Booking booking) {
        booking.setStatus(BookingStatus.CANCELLED);
    }
}
