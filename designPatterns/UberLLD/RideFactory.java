public class RideFactory {
    public static Ride createRide(RideType type, Passenger passenger, Route route, Driver driver) {
        return switch (type) {
            case CAR -> new CarRide(passenger, route, driver);
            case BIKE -> new BikeRide(passenger, route, driver);
            case AUTO -> new AutoRide(passenger, route, driver);
        };
    }
}
