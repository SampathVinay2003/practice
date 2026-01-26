package designPatterns.carRentalSystemDesign.repository;

import designPatterns.carRentalSystemDesign.entities.Booking;
import designPatterns.carRentalSystemDesign.enums.BookingStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookingRepository {
    private static volatile BookingRepository instance;
    private final Map<String, Booking> bookings;

    private BookingRepository() {
        this.bookings = new ConcurrentHashMap<>();
    }

    public static BookingRepository getInstance() {
        if (instance == null) {
            synchronized (BookingRepository.class) {
                if (instance == null) {
                    instance = new BookingRepository();
                }
            }
        }
        return instance;
    }

    public void save(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
    }

    public Optional<Booking> findById(String bookingId) {
        return Optional.ofNullable(bookings.get(bookingId));
    }

    public List<Booking> findAll() {
        return new ArrayList<>(bookings.values());
    }

    public List<Booking> findByUserId(String userId) {
        return bookings.values().stream()
                .filter(b -> b.getUser().getName().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Booking> findByStatus(BookingStatus status) {
        return bookings.values().stream()
                .filter(b -> b.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Booking> findByVehicleId(String vehicleId) {
        return bookings.values().stream()
                .filter(b -> b.getVehicle().getVehicleId().equals(vehicleId))
                .collect(Collectors.toList());
    }

    public void delete(String bookingId) {
        bookings.remove(bookingId);
    }

    public void clear() {
        bookings.clear();
    }
}
