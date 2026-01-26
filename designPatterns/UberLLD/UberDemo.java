//import java.util.List;
//
//public class UberDemo {
//    public static void main(String[] args) {
//        Passenger passenger = new Passenger("P1", "Alice");
//        Route route = new Route("Location A", "Location B");
//
//        // Use RideService with NearestDriverMatcher
//        RideService service1 = new RideService(new DriverMatcher_Impl_NearestDriverMatcher());
//        Ride carRide = service1.createRide(passenger, route, RideType.CAR);
//        double carFinalFare = FareCalculator.calculateFinalFare(
//                carRide, 10,
//                List.of(new PricingStrategy_Impl_SurgePricing(1.5),
//                        new PricingStrategy_Impl_DiscountPricing(0.10)));
//        carRide.printDetails(10, carFinalFare);
//
//        // Use RideService with CheapestDriverMatcher
//        RideService service2 = new RideService(new DriverMatcher_Impl_CheapestDriverMatcher());
//        Ride bikeRide = service2.createRide(passenger, route, RideType.BIKE);
//        double bikeFinalFare = FareCalculator.calculateFinalFare(bikeRide, 10, List.of(new FirstRideFree()));
//        bikeRide.printDetails(10, bikeFinalFare);
//    }
//}
