package designPatterns.carRentalSystemDesign.repository;

import designPatterns.carRentalSystemDesign.entities.Vehicle;
import designPatterns.carRentalSystemDesign.enums.VehicleStatus;
import designPatterns.carRentalSystemDesign.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class VehicleRepository {
    private static volatile VehicleRepository instance;
    private final Map<String, Vehicle> vehicles;

    private VehicleRepository() {
        this.vehicles = new ConcurrentHashMap<>();
    }

    public static VehicleRepository getInstance() {
        if (instance == null) {
            synchronized (VehicleRepository.class) {
                if (instance == null) {
                    instance = new VehicleRepository();
                }
            }
        }
        return instance;
    }

    public void save(Vehicle vehicle) {
        vehicles.put(vehicle.getVehicleId(), vehicle);
    }

    public Optional<Vehicle> findById(String vehicleId) {
        return Optional.ofNullable(vehicles.get(vehicleId));
    }

    public List<Vehicle> findAll() {
        return new ArrayList<>(vehicles.values());
    }

    public List<Vehicle> findByType(VehicleType type) {
        return vehicles.values().stream()
                .filter(v -> v.getVehicleType() == type)
                .collect(Collectors.toList());
    }

    public List<Vehicle> findByStatus(VehicleStatus status) {
        return vehicles.values().stream()
                .filter(v -> v.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Vehicle> findByTypeAndStatus(VehicleType type, VehicleStatus status) {
        return vehicles.values().stream()
                .filter(v -> v.getVehicleType() == type && v.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Vehicle> findByLocation(String locationId) {
        return vehicles.values().stream()
                .filter(v -> locationId.equals(v.getCurrentLocationId()))
                .collect(Collectors.toList());
    }

    public List<Vehicle> findAvailableByTypeAndLocation(VehicleType type, String locationId) {
        return vehicles.values().stream()
                .filter(v -> v.getVehicleType() == type 
                        && v.getStatus() == VehicleStatus.AVAILABLE
                        && locationId.equals(v.getCurrentLocationId()))
                .collect(Collectors.toList());
    }

    public void delete(String vehicleId) {
        vehicles.remove(vehicleId);
    }

    public void clear() {
        vehicles.clear();
    }
}
