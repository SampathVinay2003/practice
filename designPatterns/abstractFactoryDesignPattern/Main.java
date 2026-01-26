package designPatterns.abstractFactoryDesignPattern;

import designPatterns.abstractFactoryDesignPattern.decorator.GPSDecorator;
import designPatterns.abstractFactoryDesignPattern.decorator.PremiumSoundDecorator;
import designPatterns.abstractFactoryDesignPattern.decorator.SunroofDecorator;
import designPatterns.abstractFactoryDesignPattern.factory.VehicleFactory;
import designPatterns.abstractFactoryDesignPattern.observer.DiagnosticsAlert;
import designPatterns.abstractFactoryDesignPattern.observer.FuelAlert;
import designPatterns.abstractFactoryDesignPattern.observer.MaintenanceAlert;
import designPatterns.abstractFactoryDesignPattern.products.AbstractVehicle;
import designPatterns.abstractFactoryDesignPattern.products.Engine;
import designPatterns.abstractFactoryDesignPattern.products.Transmission;
import designPatterns.abstractFactoryDesignPattern.products.Vehicle;

/**
 * Main class demonstrating multiple design patterns working together:
 * 1. Abstract Factory Pattern - Creates families of related objects
 * 2. Singleton Pattern - Each factory is a singleton
 * 3. Builder Pattern - Vehicle assembly using method chaining
 * 4. Decorator Pattern - Dynamically adds features to vehicles
 * 5. Observer Pattern - Monitors vehicle state changes
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Design Patterns Demo: Abstract Factory + Singleton + Builder + Decorator + Observer ===\n");

        demonstrateAllPatterns();
    }

    private static void demonstrateAllPatterns() {
        System.out.println("--- Creating Four Wheeler with All Patterns ---\n");

        // 1. ABSTRACT FACTORY + SINGLETON: Get factory instance
        System.out.println("1️⃣  ABSTRACT FACTORY + SINGLETON PATTERN:");
        VehicleFactory factory = VehicleFactoryProvider.getFactory("FourWheeler");
        System.out.println("   ✓ Factory retrieved (Singleton instance)\n");

        // 2. Create family of related products
        Vehicle baseVehicle = factory.createVehicle();
        Engine engine = factory.createEngine();
        Transmission transmission = factory.createTransmission();

        // 3. BUILDER PATTERN: Assemble vehicle using method chaining
        System.out.println("2️⃣  BUILDER PATTERN:");
        baseVehicle = baseVehicle
                .setEngine(engine)
                .setTransmission(transmission)
                .setName("Tesla Model S")
                .build();
        System.out.println("   ✓ Vehicle assembled using Builder pattern\n");

        // 4. OBSERVER PATTERN: Add observers to monitor vehicle
        // IMPORTANT: Observers must be added to the concrete vehicle (AbstractVehicle)
        // BEFORE decorating, because decorators don't expose observer methods
        System.out.println("3️⃣  OBSERVER PATTERN:");
        MaintenanceAlert maintenanceAlert = new MaintenanceAlert();
        FuelAlert fuelAlert = new FuelAlert();
        DiagnosticsAlert diagnosticsAlert = new DiagnosticsAlert();

        // Cast to AbstractVehicle to access observer management methods
        if (baseVehicle instanceof AbstractVehicle) {
            AbstractVehicle abstractVehicle = (AbstractVehicle) baseVehicle;
            abstractVehicle.addObserver(maintenanceAlert);
            abstractVehicle.addObserver(fuelAlert);
            abstractVehicle.addObserver(diagnosticsAlert);
            System.out.println("   ✓ Three observers registered (encapsulated in AbstractVehicle)\n");
        }

        // 5. DECORATOR PATTERN: Add features dynamically
        // Now we can decorate - decorators won't expose observer methods
        System.out.println("4️⃣  DECORATOR PATTERN:");
        System.out.println("   Adding features dynamically...");
        Vehicle vehicle = new GPSDecorator(baseVehicle);
        vehicle = new SunroofDecorator(vehicle);
        vehicle = new PremiumSoundDecorator(vehicle);
        System.out.println("   ✓ Features added: GPS, Sunroof, Premium Sound");
        System.out.println("   ✓ Observer methods NOT accessible through decorators (proper encapsulation)\n");

        // Display final vehicle configuration
        System.out.println("--- Final Vehicle Configuration ---");
        System.out.println("Vehicle Type: " + vehicle.getType());
        System.out.println("Engine: " + engine.getEngineType() + " (" + engine.getHorsepower() + " HP)");
        System.out.println("Transmission: " + transmission.getTransmissionType());
        System.out.println();

        // Demonstrate all patterns working together
        System.out.println("--- Starting Vehicle (Observers will be notified) ---");
        vehicle.start();
        System.out.println();

        System.out.println("--- Engine and Transmission Operations ---");
        engine.start();
        transmission.shiftGear(3);
        System.out.println("Current gear: " + transmission.getCurrentGear());
        System.out.println();

        System.out.println("--- Stopping Vehicle (Observers will be notified) ---");
        transmission.shiftGear(0);
        engine.stop();
        vehicle.stop();
        System.out.println();

        System.out.println("=== Demo Complete ===");
        System.out.println("Patterns demonstrated:");
        System.out.println("✓ Abstract Factory - Created vehicle family");
        System.out.println("✓ Singleton - Factory instances");
        System.out.println("✓ Builder - Method chaining assembly");
        System.out.println("✓ Decorator - Dynamic feature addition");
        System.out.println("✓ Observer - State change monitoring");
    }
}
