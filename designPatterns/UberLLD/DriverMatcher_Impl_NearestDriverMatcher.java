public class DriverMatcher_Impl_NearestDriverMatcher implements DriverMatcher {
    @Override
    public Driver findDriver(Route route) {
        return new Driver("D1", "John (Nearest)");
    }
}
