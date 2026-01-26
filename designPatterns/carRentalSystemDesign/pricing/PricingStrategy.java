package designPatterns.carRentalSystemDesign.pricing;

import designPatterns.carRentalSystemDesign.entities.Vehicle;
import designPatterns.carRentalSystemDesign.enums.PricingType;

import java.time.Duration;
import java.time.LocalDateTime;

public interface PricingStrategy {
    double calculatePrice(Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime);
    double calculateLateFee(Vehicle vehicle, Duration lateBy);
}
