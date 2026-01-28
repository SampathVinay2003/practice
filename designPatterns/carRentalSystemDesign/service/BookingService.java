package designPatterns.carRentalSystemDesign.service;

import designPatterns.carRentalSystemDesign.entities.Booking;
import designPatterns.carRentalSystemDesign.entities.User;
import designPatterns.carRentalSystemDesign.entities.Vehicle;
import designPatterns.carRentalSystemDesign.enums.BookingStatus;
import designPatterns.carRentalSystemDesign.enums.VehicleStatus;
import designPatterns.carRentalSystemDesign.exceptions.BookingNotFoundException;
import designPatterns.carRentalSystemDesign.exceptions.InvalidBookingException;
import designPatterns.carRentalSystemDesign.exceptions.PaymentFailedException;
import designPatterns.carRentalSystemDesign.exceptions.VehicleNotAvailableException;
import designPatterns.carRentalSystemDesign.payment.PaymentStrategy;
import designPatterns.carRentalSystemDesign.pricing.PricingStrategy;
import designPatterns.carRentalSystemDesign.repository.BookingRepository;
import designPatterns.carRentalSystemDesign.repository.VehicleRepository;
import designPatterns.carRentalSystemDesign.state.ActiveState;
import designPatterns.carRentalSystemDesign.state.BookingState;
import designPatterns.carRentalSystemDesign.state.ConfirmedState;
import designPatterns.carRentalSystemDesign.state.PendingPaymentState;
import designPatterns.carRentalSystemDesign.validation.BookingValidator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class BookingService {
    private static volatile BookingService instance;
    private final BookingRepository bookingRepository;
    private final VehicleRepository vehicleRepository;
    private final BookingValidator validator;

    private BookingService() {
        this.bookingRepository = BookingRepository.getInstance();
        this.vehicleRepository = VehicleRepository.getInstance();
        this.validator = BookingValidator.getInstance();
    }

    public static BookingService getInstance() {
        if (instance == null) {
            synchronized (BookingService.class) {
                if (instance == null) {
                    instance = new BookingService();
                }
            }
        }
        return instance;
    }

    public Booking createBooking(User user, Vehicle vehicle, LocalDateTime startTime,
                                 LocalDateTime endTime, String locationId,
                                 PricingStrategy pricingStrategy) {
        validator.validateBookingRequest(user, vehicle, startTime, endTime);

        if (!vehicle.isAvailable()) {
            throw new VehicleNotAvailableException("Vehicle " + vehicle.getName() + " is not available");
        }

        double estimatedCost = pricingStrategy.calculatePrice(vehicle, startTime, endTime);
        estimatedCost += vehicle.getAddonPrice();

        Booking booking = new Booking.Builder()
                .user(user)
                .vehicle(vehicle)
                .startTime(startTime)
                .endTime(endTime)
                .estimatedCost(estimatedCost)
                .locationId(locationId)
                .returnLocationId(locationId)
                .build();

        vehicle.setStatus(VehicleStatus.RESERVED);
        vehicleRepository.save(vehicle);
        bookingRepository.save(booking);

        return booking;
    }

    public boolean confirmBooking(String bookingId, PaymentStrategy paymentStrategy) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found: " + bookingId));

        if (booking.getStatus() != BookingStatus.PENDING_PAYMENT) {
            throw new InvalidBookingException("Booking is not in pending payment state");
        }

        boolean paymentSuccess = paymentStrategy.pay((int) booking.getEstimatedCost(), booking.getUser());

        if (!paymentSuccess) {
            booking.getVehicle().setStatus(VehicleStatus.AVAILABLE);
            booking.getVehicle().notifyObservers();
            vehicleRepository.save(booking.getVehicle());
            throw new PaymentFailedException("Payment failed for booking: " + bookingId);
        }

        BookingState state = new PendingPaymentState();
        state.confirm(booking);
        bookingRepository.save(booking);

        return true;
    }

    public void activateBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found: " + bookingId));

        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new InvalidBookingException("Booking must be confirmed before activation");
        }

        BookingState state = new ConfirmedState();
        state.activate(booking);

        booking.getVehicle().setStatus(VehicleStatus.RENTED);
        booking.getVehicle().setUserAssigned(booking.getUser());

        vehicleRepository.save(booking.getVehicle());
        bookingRepository.save(booking);
    }

    public double completeBooking(String bookingId, LocalDateTime actualReturnTime,
                                  PricingStrategy pricingStrategy, PaymentStrategy paymentStrategy) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found: " + bookingId));

        if (booking.getStatus() != BookingStatus.ACTIVE) {
            throw new InvalidBookingException("Only active bookings can be completed");
        }

        booking.setActualReturnTime(actualReturnTime);

        double actualCost = pricingStrategy.calculatePrice(
                booking.getVehicle(),
                booking.getStartTime(),
                actualReturnTime
        );
        actualCost += booking.getVehicle().getAddonPrice();

        if (actualReturnTime.isAfter(booking.getEndTime())) {
            Duration lateBy = Duration.between(booking.getEndTime(), actualReturnTime);
            double lateFee = pricingStrategy.calculateLateFee(booking.getVehicle(), lateBy);
            actualCost += lateFee;
            System.out.println("⚠️ Late return! Late fee: $" + lateFee);
        }

        booking.setActualCost(actualCost);

        double additionalPayment = actualCost - booking.getEstimatedCost();
        if (additionalPayment > 0) {
            boolean paymentSuccess = paymentStrategy.pay((int) additionalPayment, booking.getUser());
            if (!paymentSuccess) {
                throw new PaymentFailedException("Additional payment failed");
            }
        }

        BookingState state = new ActiveState();
        state.complete(booking);

        booking.getVehicle().setStatus(VehicleStatus.AVAILABLE);
        booking.getVehicle().notifyObservers();
        booking.getVehicle().setUserAssigned(null);

        vehicleRepository.save(booking.getVehicle());
        bookingRepository.save(booking);

        return actualCost;
    }

    public void cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found: " + bookingId));

        if (booking.getStatus() == BookingStatus.ACTIVE) {
            throw new InvalidBookingException("Cannot cancel active booking");
        }

        if (booking.getStatus() == BookingStatus.COMPLETED || booking.getStatus() == BookingStatus.CANCELLED) {
            throw new InvalidBookingException("Booking is already " + booking.getStatus());
        }

        booking.setStatus(BookingStatus.CANCELLED);
        booking.getVehicle().setStatus(VehicleStatus.AVAILABLE);
        booking.getVehicle().notifyObservers();

        vehicleRepository.save(booking.getVehicle());
        bookingRepository.save(booking);
    }

    public Booking getBooking(String bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found: " + bookingId));
    }

    public List<Booking> getUserBookings(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getActiveBookings() {
        return bookingRepository.findByStatus(BookingStatus.ACTIVE);
    }
}
