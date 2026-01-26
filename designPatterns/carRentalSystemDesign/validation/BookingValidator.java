package designPatterns.carRentalSystemDesign.validation;

import designPatterns.carRentalSystemDesign.entities.Vehicle;
import designPatterns.carRentalSystemDesign.entities.User;
import designPatterns.carRentalSystemDesign.exceptions.InvalidBookingException;

import java.time.LocalDateTime;

public class BookingValidator {
    private static volatile BookingValidator instance;
    
    private BookingValidator() {
    }
    
    public static BookingValidator getInstance() {
        if (instance == null) {
            synchronized (BookingValidator.class) {
                if (instance == null) {
                    instance = new BookingValidator();
                }
            }
        }
        return instance;
    }
    
    public void validateBookingRequest(User user, Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime) {
        if (user == null) {
            throw new InvalidBookingException("User cannot be null");
        }
        
        if (vehicle == null) {
            throw new InvalidBookingException("Vehicle cannot be null");
        }
        
        if (startTime == null || endTime == null) {
            throw new InvalidBookingException("Start time and end time cannot be null");
        }
        
        if (startTime.isAfter(endTime)) {
            throw new InvalidBookingException("Start time cannot be after end time");
        }
        
        if (startTime.isBefore(LocalDateTime.now())) {
            throw new InvalidBookingException("Start time cannot be in the past");
        }
        
        if (!vehicle.isAvailable()) {
            throw new InvalidBookingException("Vehicle is not available for booking");
        }
    }
    
    public void validateUser(User user) {
        if (user == null) {
            throw new InvalidBookingException("User cannot be null");
        }
        
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new InvalidBookingException("User name is required");
        }
        
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new InvalidBookingException("User email is required");
        }
    }
}
