package designPatterns.carRentalSystemDesign.pricing;

import designPatterns.carRentalSystemDesign.entities.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;

public class HourlyPricingStrategy implements PricingStrategy {
    private static volatile HourlyPricingStrategy instance;
    private static final double LATE_FEE_PER_HOUR = 50.0;
    
    private HourlyPricingStrategy() {
    }
    
    public static HourlyPricingStrategy getInstance() {
        if (instance == null) {
            synchronized (HourlyPricingStrategy.class) {
                if (instance == null) {
                    instance = new HourlyPricingStrategy();
                }
            }
        }
        return instance;
    }

    @Override
    public double calculatePrice(Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        if (hours == 0) {
            hours = 1;
        }
        return vehicle.getHourlyRate() * hours;
    }

    @Override
    public double calculateLateFee(Vehicle vehicle, Duration lateBy) {
        long lateHours = lateBy.toHours();
        if (lateHours == 0 && !lateBy.isZero()) {
            lateHours = 1;
        }
        return lateHours * LATE_FEE_PER_HOUR;
    }
}
