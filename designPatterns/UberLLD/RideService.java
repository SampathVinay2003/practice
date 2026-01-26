public class RideService {
    private DriverMatcher driverMatcher;

    public RideService(DriverMatcher matcher) {
        this.driverMatcher = matcher;
    }

    public Ride createRide(Passenger p, Route r, RideType type) {
        Driver driver = driverMatcher.findDriver(r);
        return RideFactory.createRide(type, p, r, driver);
    }
}
