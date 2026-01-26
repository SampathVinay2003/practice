package designPatterns.carRentalSystemDesign.pricing;

import designPatterns.carRentalSystemDesign.entities.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;

public class WeeklyPricingStrategy implements PricingStrategy {
    private static volatile WeeklyPricingStrategy instance;
    private static final double LATE_FEE_PER_WEEK = 1000.0;
    
    private WeeklyPricingStrategy() {
    }
    
    public static WeeklyPricingStrategy getInstance() {
        if (instance == null) {
            synchronized (WeeklyPricingStrategy.class) {
                if (instance == null) {
                    instance = new WeeklyPricingStrategy();
                }
            }
        }
        return instance;
    }

    @Override
    public double calculatePrice(Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        long weeks = duration.toDays() / 7;
        if (weeks == 0) {
            weeks = 1;
        }
        return vehicle.getWeeklyRate() * weeks;
    }

    @Override
    public double calculateLateFee(Vehicle vehicle, Duration lateBy) {
        long lateWeeks = lateBy.toDays() / 7;
        if (lateWeeks == 0 && !lateBy.isZero()) {
            lateWeeks = 1;
        }
        return lateWeeks * LATE_FEE_PER_WEEK;
    }
}
