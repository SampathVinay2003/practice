package designPatterns.carRentalSystemDesign.state;

import designPatterns.carRentalSystemDesign.entities.Booking;

public interface BookingState {
    void confirm(Booking booking);
    void activate(Booking booking);
    void complete(Booking booking);
    void cancel(Booking booking);
}
