public class AutoRide extends Ride {
    public AutoRide(Passenger passenger, Route route, Driver driver) {
        super(passenger, route, driver, RideType.AUTO);
    }

    @Override
    public double calculateBaseFare(double distance) {
        return 30 + distance * 7;
    }
}
