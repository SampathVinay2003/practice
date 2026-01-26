package designPatterns.carRentalSystemDesign.pricing;

import designPatterns.carRentalSystemDesign.entities.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;

public class DailyPricingStrategy implements PricingStrategy {
    private static volatile DailyPricingStrategy instance;
    private static final double LATE_FEE_PER_DAY = 200.0;
    
    private DailyPricingStrategy() {
    }
    
    public static DailyPricingStrategy getInstance() {
        if (instance == null) {
            synchronized (DailyPricingStrategy.class) {
                if (instance == null) {
                    instance = new DailyPricingStrategy();
                }
            }
        }
        return instance;
    }

    @Override
    public double calculatePrice(Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        long days = duration.toDays();
        if (days == 0) {
            days = 1;
        }
        return vehicle.getDailyRate() * days;
    }

    @Override
    public double calculateLateFee(Vehicle vehicle, Duration lateBy) {
        long lateDays = lateBy.toDays();
        if (lateDays == 0 && !lateBy.isZero()) {
            lateDays = 1;
        }
        return lateDays * LATE_FEE_PER_DAY;
    }
}
