import designPatterns.abstractFactoryDesignPattern.factory.VehicleFactory;
import designPatterns.abstractFactoryDesignPattern.VehicleFactoryProvider;
import designPatterns.abstractFactoryDesignPattern.products.Vehicle;
import designPatterns.abstractFactoryDesignPattern.products.Engine;
import designPatterns.abstractFactoryDesignPattern.products.Transmission;

/**
 * Main class demonstrating the Abstract Factory Pattern
 * 
 * Key differences from Factory Pattern:
 * 1. Creates families of related objects (Vehicle + Engine + Transmission)
 * 2. Uses abstract factory interface with multiple factory methods
 * 3. Each concrete factory creates a complete family of compatible designPatterns.abstractFactoryDesignPattern.products
 */
class Main {
    public static void main(String[] args) {
        System.out.println("=== Abstract Factory Pattern Demo ===\n");
        
        // Demonstrate creating different vehicle families
        demonstrateVehicleFamily("TwoWheeler");
        System.out.println();
        demonstrateVehicleFamily("ThreeWheeler");
        System.out.println();
        demonstrateVehicleFamily("FourWheeler");
    }
    
    private static void demonstrateVehicleFamily(String vehicleType) {
        System.out.println("--- Creating " + vehicleType + " Family ---");
        
        // Get the appropriate factory for this vehicle type
        VehicleFactory factory = VehicleFactoryProvider.getFactory(vehicleType);
        
        // Create a complete family of related designPatterns.abstractFactoryDesignPattern.products
        Vehicle vehicle = factory.createVehicle();
        Engine engine = factory.createEngine();
        Transmission transmission = factory.createTransmission();

        Vehicle finalVehicle = vehicle.setEngine(engine).setTransmission(transmission).setName("MCG").build();
        
        // Demonstrate the designPatterns.abstractFactoryDesignPattern.products working together
        System.out.println("Vehicle Type: " + finalVehicle.getType());
        System.out.println("Engine: " + engine.getEngineType() + " (" + finalVehicle.getEngine().getHorsepower() + " HP)");
        System.out.println("Transmission: " + transmission.getTransmissionType());
        
        // Start the vehicle system
        vehicle.start();
        engine.start();
        transmission.shiftGear(2);
        
        System.out.println("Current gear: " + transmission.getCurrentGear());
        
        // Stop the vehicle system
        transmission.shiftGear(0);
        engine.stop();
        vehicle.stop();
    }
}