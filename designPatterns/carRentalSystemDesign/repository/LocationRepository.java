package designPatterns.carRentalSystemDesign.repository;

import designPatterns.carRentalSystemDesign.entities.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LocationRepository {
    private static volatile LocationRepository instance;
    private final Map<String, Location> locations;

    private LocationRepository() {
        this.locations = new ConcurrentHashMap<>();
    }

    public static LocationRepository getInstance() {
        if (instance == null) {
            synchronized (LocationRepository.class) {
                if (instance == null) {
                    instance = new LocationRepository();
                }
            }
        }
        return instance;
    }

    public void save(Location location) {
        locations.put(location.getLocationId(), location);
    }

    public Optional<Location> findById(String locationId) {
        return Optional.ofNullable(locations.get(locationId));
    }

    public List<Location> findAll() {
        return new ArrayList<>(locations.values());
    }

    public void delete(String locationId) {
        locations.remove(locationId);
    }

    public void clear() {
        locations.clear();
    }
}
