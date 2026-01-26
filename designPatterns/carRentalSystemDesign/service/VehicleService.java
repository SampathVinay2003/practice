package designPatterns.carRentalSystemDesign.service;

import designPatterns.carRentalSystemDesign.entities.Vehicle;
import designPatterns.carRentalSystemDesign.enums.VehicleStatus;
import designPatterns.carRentalSystemDesign.enums.VehicleType;
import designPatterns.carRentalSystemDesign.exceptions.VehicleNotAvailableException;
import designPatterns.carRentalSystemDesign.factory.VehicleFactory;
import designPatterns.carRentalSystemDesign.repository.VehicleRepository;

import java.util.List;

public class VehicleService {
    private static volatile VehicleService instance;
    private final VehicleRepository vehicleRepository;
    private final VehicleFactory vehicleFactory;

    private VehicleService() {
        this.vehicleRepository = VehicleRepository.getInstance();
        this.vehicleFactory = VehicleFactory.getInstance();
    }

    public static VehicleService getInstance() {
        if (instance == null) {
            synchronized (VehicleService.class) {
                if (instance == null) {
                    instance = new VehicleService();
                }
            }
        }
        return instance;
    }

    public Vehicle addVehicle(VehicleType type, String name, String model, int year, 
                             String registrationNumber, String locationId) {
        Vehicle vehicle = vehicleFactory.createVehicle(type, name, model, year, registrationNumber);
        vehicle.setCurrentLocationId(locationId);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public List<Vehicle> getAvailableVehicles(VehicleType type, String locationId) {
        return vehicleRepository.findAvailableByTypeAndLocation(type, locationId);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(String vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotAvailableException("Vehicle not found: " + vehicleId));
    }

    public void markVehicleForMaintenance(String vehicleId) {
        Vehicle vehicle = getVehicleById(vehicleId);
        vehicle.setStatus(VehicleStatus.UNDER_MAINTENANCE);
        vehicleRepository.save(vehicle);
    }

    public void markVehicleAvailable(String vehicleId) {
        Vehicle vehicle = getVehicleById(vehicleId);
        vehicle.setStatus(VehicleStatus.AVAILABLE);
        vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getVehiclesByLocation(String locationId) {
        return vehicleRepository.findByLocation(locationId);
    }
}
