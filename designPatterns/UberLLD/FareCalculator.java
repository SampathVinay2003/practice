import java.util.List;

public class FareCalculator {
    public static double calculateFinalFare(Ride ride, double distance, List<PricingStrategy> strategies) {
        double fare = ride.calculateBaseFare(distance);
        for (PricingStrategy strategy : strategies) {
            fare = strategy.apply(fare);
        }
        return fare;
    }
}
