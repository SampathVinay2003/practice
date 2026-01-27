package designPatterns.libraryManagementSystem.dtoLayer.repositories;

import designPatterns.libraryManagementSystem.dtoLayer.entities.Booking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookingRepository {
    private static volatile BookingRepository instance;
    private Map<String, Booking> bookingMap;

    private BookingRepository() {
        bookingMap = new ConcurrentHashMap<>();
    }

    public static BookingRepository getInstance() {
        if (instance == null) {
            synchronized (BookingRepository.class) {
                if (instance == null) {
                    return instance = new BookingRepository();
                }
            }
        }
        return instance;
    }
}
