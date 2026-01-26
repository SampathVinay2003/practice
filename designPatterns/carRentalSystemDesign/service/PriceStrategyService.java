package designPatterns.carRentalSystemDesign.service;

import designPatterns.carRentalSystemDesign.enums.PricingType;
import designPatterns.carRentalSystemDesign.exceptions.InvalidPricingTypeException;
import designPatterns.carRentalSystemDesign.pricing.DailyPricingStrategy;
import designPatterns.carRentalSystemDesign.pricing.HourlyPricingStrategy;
import designPatterns.carRentalSystemDesign.pricing.PricingStrategy;
import designPatterns.carRentalSystemDesign.pricing.WeeklyPricingStrategy;

public class PriceStrategyService {
    private static volatile PriceStrategyService instance;
    
    private PriceStrategyService() {
    }
    
    public static PriceStrategyService getInstance() {
        if (instance == null) {
            synchronized (PriceStrategyService.class) {
                if (instance == null) {
                    instance = new PriceStrategyService();
                }
            }
        }
        return instance;
    }
    
    public PricingStrategy getPricingStrategy(PricingType pricingType) {
        return switch (pricingType) {
            case HOURLY -> HourlyPricingStrategy.getInstance();
            case DAILY -> DailyPricingStrategy.getInstance();
            case WEEKLY -> WeeklyPricingStrategy.getInstance();
            default -> throw new InvalidPricingTypeException("Invalid pricing type: " + pricingType);
        };
    }
}
