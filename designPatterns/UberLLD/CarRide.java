public class CarRide extends Ride {
    public CarRide(Passenger passenger, Route route, Driver driver) {
        super(passenger, route, driver, RideType.CAR);
    }

    @Override
    public double calculateBaseFare(double distance) {
        return 50 + distance * 10;
    }
}
