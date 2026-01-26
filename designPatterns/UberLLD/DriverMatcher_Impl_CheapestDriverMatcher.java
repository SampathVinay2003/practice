public class DriverMatcher_Impl_CheapestDriverMatcher implements DriverMatcher {
    @Override
    public Driver findDriver(Route route) {
        return new Driver("D2", "Jane (Cheapest)");
    }
}
