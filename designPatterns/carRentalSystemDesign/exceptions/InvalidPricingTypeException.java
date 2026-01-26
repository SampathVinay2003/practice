package designPatterns.carRentalSystemDesign.exceptions;

public class InvalidPricingTypeException extends RuntimeException{
    public InvalidPricingTypeException(String message) {
        super(message);
    }
}
