public class BikeRide extends Ride {
    public BikeRide(Passenger passenger, Route route, Driver driver) {
        super(passenger, route, driver, RideType.BIKE);
    }

    @Override
    public double calculateBaseFare(double distance) {
        return distance * 5;
    }
}
