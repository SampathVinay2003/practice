package designPatterns.carRentalSystemDesign.factory;

import designPatterns.carRentalSystemDesign.entities.Vehicle;
import designPatterns.carRentalSystemDesign.entities.Bike;
import designPatterns.carRentalSystemDesign.entities.Car;
import designPatterns.carRentalSystemDesign.enums.VehicleType;

import java.time.LocalDate;
import java.util.UUID;

public class VehicleFactory {
    private static volatile VehicleFactory instance;

    private VehicleFactory() {
    }

    public static VehicleFactory getInstance() {
        if (instance == null) {
            synchronized (VehicleFactory.class) {
                if (instance == null) {
                    instance = new VehicleFactory();
                }
            }
        }
        return instance;
    }

    public Vehicle createVehicle(VehicleType type, String name, String model, int year, String registrationNumber) {
        String vehicleId = UUID.randomUUID().toString();
        Vehicle vehicle;

        switch (type) {
            case BIKE:
                vehicle = new Bike(vehicleId, name, model, year, registrationNumber);
                vehicle.setHourlyRate(50.0);
                vehicle.setDailyRate(300.0);
                vehicle.setWeeklyRate(1800.0);
                vehicle.setMonthlyRate(6000.0);
                break;
            case CAR:
                vehicle = new Car(vehicleId, name, model, year, registrationNumber, 5);
                vehicle.setHourlyRate(100.0);
                vehicle.setDailyRate(800.0);
                vehicle.setWeeklyRate(5000.0);
                vehicle.setMonthlyRate(18000.0);
                break;
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }

        vehicle.setMileage(0);
        vehicle.setLastServiceDate(LocalDate.now());
        return vehicle;
    }
}
