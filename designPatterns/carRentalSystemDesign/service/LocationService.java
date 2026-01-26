package designPatterns.carRentalSystemDesign.service;

import designPatterns.carRentalSystemDesign.entities.Location;
import designPatterns.carRentalSystemDesign.repository.LocationRepository;

import java.util.List;

public class LocationService {
    private static volatile LocationService instance;
    private final LocationRepository locationRepository;

    private LocationService() {
        this.locationRepository = LocationRepository.getInstance();
    }

    public static LocationService getInstance() {
        if (instance == null) {
            synchronized (LocationService.class) {
                if (instance == null) {
                    instance = new LocationService();
                }
            }
        }
        return instance;
    }

    public void addLocation(Location location) {
        locationRepository.save(location);
    }

    public Location getLocation(String locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found: " + locationId));
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}
