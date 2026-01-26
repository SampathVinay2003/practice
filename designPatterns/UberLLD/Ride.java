public abstract class Ride {
    protected Passenger passenger;
    protected Driver driver;
    protected Route route;
    protected RideType type;

    public Ride(Passenger passenger, Route route, Driver driver, RideType type) {
        this.passenger = passenger;
        this.route = route;
        this.driver = driver;
        this.type = type;
    }

    public RideType getType() {
        return type;
    }

    public abstract double calculateBaseFare(double distance);

    public void printDetails(double distance, double finalFare) {
        System.out.println("Ride type: " + type +
                           ", Passenger: " + passenger.getName() +
                           ", Driver: " + driver.getName() +
                           ", Route: " + route +
                           ", Base fare: " + calculateBaseFare(distance) +
                           ", Final fare: " + finalFare);
    }
}
