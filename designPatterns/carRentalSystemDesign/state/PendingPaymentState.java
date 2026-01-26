package designPatterns.carRentalSystemDesign.state;

import designPatterns.carRentalSystemDesign.entities.Booking;
import designPatterns.carRentalSystemDesign.enums.BookingStatus;
import designPatterns.carRentalSystemDesign.exceptions.InvalidBookingException;

public class PendingPaymentState implements BookingState {
    @Override
    public void confirm(Booking booking) {
        booking.setStatus(BookingStatus.CONFIRMED);
    }

    @Override
    public void activate(Booking booking) {
        throw new InvalidBookingException("Cannot activate booking without payment confirmation");
    }

    @Override
    public void complete(Booking booking) {
        throw new InvalidBookingException("Cannot complete booking without payment");
    }

    @Override
    public void cancel(Booking booking) {
        booking.setStatus(BookingStatus.CANCELLED);
    }
}
