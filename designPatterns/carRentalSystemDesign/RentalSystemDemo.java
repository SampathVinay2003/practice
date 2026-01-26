package designPatterns.carRentalSystemDesign;

import designPatterns.carRentalSystemDesign.entities.Booking;
import designPatterns.carRentalSystemDesign.entities.Location;
import designPatterns.carRentalSystemDesign.entities.Vehicle;
import designPatterns.carRentalSystemDesign.entities.User;
import designPatterns.carRentalSystemDesign.enums.PricingType;
import designPatterns.carRentalSystemDesign.enums.VehicleType;
import designPatterns.carRentalSystemDesign.payment.CardPaymentStrategy;
import designPatterns.carRentalSystemDesign.payment.PaymentStrategy;
import designPatterns.carRentalSystemDesign.payment.UPIStrategy;
import designPatterns.carRentalSystemDesign.pricing.DailyPricingStrategy;
import designPatterns.carRentalSystemDesign.pricing.HourlyPricingStrategy;
import designPatterns.carRentalSystemDesign.pricing.PricingStrategy;
import designPatterns.carRentalSystemDesign.service.BookingService;
import designPatterns.carRentalSystemDesign.service.LocationService;
import designPatterns.carRentalSystemDesign.service.PriceStrategyService;
import designPatterns.carRentalSystemDesign.service.VehicleService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * SDE-2 Level Car Rental System Demonstration
 * 
 * Design Patterns Implemented:
 * 1. Singleton Pattern - All services, repositories, and factories
 * 2. Builder Pattern - Booking entity construction
 * 3. Factory Pattern - Vehicle creation
 * 4. Strategy Pattern - Payment and Pricing strategies
 * 5. State Pattern - Booking lifecycle management
 * 6. Repository Pattern - Data access layer
 * 
 * Key Features:
 * - Thread-safe singleton implementations
 * - Time-based pricing with multiple strategies
 * - Proper state management for bookings
 * - Multi-location support
 * - Comprehensive validation and exception handling
 * - Service layer separation
 * - Late return fee calculation
 * - Vehicle addon pricing (helmet, GPS, child seat)
 */
public class RentalSystemDemo {
    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("🚗 CAR RENTAL SYSTEM - SDE-2 LEVEL DEMONSTRATION 🏍️");
        System.out.println("=".repeat(80));
        
        // Initialize services (all thread-safe singletons)
        LocationService locationService = LocationService.getInstance();
        VehicleService vehicleService = VehicleService.getInstance();
        BookingService bookingService = BookingService.getInstance();
        
        // Setup locations
        System.out.println("\n📍 Setting up rental locations...");
        Location downtown = new Location("LOC001", "Downtown Branch", 
                "123 Main St", "New York", 
                LocalTime.of(8, 0), LocalTime.of(20, 0));
        Location airport = new Location("LOC002", "Airport Branch", 
                "Airport Terminal 2", "New York", 
                LocalTime.of(6, 0), LocalTime.of(23, 0));
        
        locationService.addLocation(downtown);
        locationService.addLocation(airport);
        System.out.println("✅ Locations added: " + downtown.getName() + ", " + airport.getName());
        
        // Add vehicles using Factory pattern
        System.out.println("\n🚗 Adding vehicles to inventory...");
        Vehicle bike1 = vehicleService.addVehicle(VehicleType.BIKE, "Royal Enfield", 
                "Classic 350", 2023, "NY-01-AB-1234", "LOC001");
        Vehicle bike2 = vehicleService.addVehicle(VehicleType.BIKE, "Honda", 
                "CBR 250R", 2024, "NY-01-CD-5678", "LOC001");
        Vehicle car1 = vehicleService.addVehicle(VehicleType.CAR, "Toyota", 
                "Camry", 2023, "NY-02-EF-9012", "LOC001");
        Vehicle car2 = vehicleService.addVehicle(VehicleType.CAR, "Honda", 
                "Accord", 2024, "NY-02-GH-3456", "LOC002");
        
        System.out.println("✅ Added 2 bikes and 2 cars to inventory");
        
        // Create users
        System.out.println("\n👤 Creating users...");
        User user1 = new User("John Doe", "+1-555-0101", "john.doe@email.com");
        User user2 = new User("Jane Smith", "+1-555-0102", "jane.smith@email.com");
        System.out.println("✅ Users created: " + user1.getName() + ", " + user2.getName());
        
        // Scenario 1: Hourly bike rental
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📋 SCENARIO 1: Hourly Bike Rental");
        System.out.println("=".repeat(80));
        
        try {
            PriceStrategyService priceStrategyService = PriceStrategyService.getInstance();
            PricingStrategy hourlyPricing = priceStrategyService.getPricingStrategy(PricingType.HOURLY);
            PaymentStrategy cardPayment = new CardPaymentStrategy();
            
            LocalDateTime startTime1 = LocalDateTime.now().plusHours(1);
            LocalDateTime endTime1 = startTime1.plusHours(3);
            
            System.out.println("\n🔍 Checking available bikes at Downtown...");
            List<Vehicle> availableBikes = vehicleService.getAvailableVehicles(VehicleType.BIKE, "LOC001");
            System.out.println("✅ Found " + availableBikes.size() + " available bikes");
            
            System.out.println("\n📝 Creating booking for " + user1.getName() + "...");
            Booking booking1 = bookingService.createBooking(user1, bike1, startTime1, endTime1, 
                    "LOC001", hourlyPricing);
            System.out.println("✅ Booking created: " + booking1.getBookingId());
            System.out.println("   Vehicle: " + bike1.getName() + " " + bike1.getModel());
            System.out.println("   Duration: 3 hours");
            System.out.println("   Estimated Cost: $" + booking1.getEstimatedCost());
            
            System.out.println("\n💰 Confirming booking with payment...");
            bookingService.confirmBooking(booking1.getBookingId(), cardPayment);
            System.out.println("✅ Booking confirmed! Status: " + booking1.getStatus());
            
            System.out.println("\n🚀 Activating booking (vehicle pickup)...");
            bookingService.activateBooking(booking1.getBookingId());
            System.out.println("✅ Booking activated! Vehicle is now RENTED");
            
            System.out.println("\n🏁 Completing booking (vehicle return)...");
            LocalDateTime actualReturn1 = endTime1;
            double finalCost1 = bookingService.completeBooking(booking1.getBookingId(), 
                    actualReturn1, hourlyPricing, cardPayment);
            System.out.println("✅ Booking completed!");
            System.out.println("   Final Cost: $" + finalCost1);
            System.out.println("   Status: " + booking1.getStatus());
            
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        
        // Scenario 2: Daily car rental with late return
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📋 SCENARIO 2: Daily Car Rental with Late Return");
        System.out.println("=".repeat(80));
        
        try {
            PriceStrategyService priceStrategyService = PriceStrategyService.getInstance();
            PricingStrategy dailyPricing = priceStrategyService.getPricingStrategy(PricingType.DAILY);
            PaymentStrategy upiPayment = new UPIStrategy();
            
            LocalDateTime startTime2 = LocalDateTime.now().plusDays(1);
            LocalDateTime endTime2 = startTime2.plusDays(2);
            
            System.out.println("\n🔍 Checking available cars at Downtown...");
            List<Vehicle> availableCars = vehicleService.getAvailableVehicles(VehicleType.CAR, "LOC001");
            System.out.println("✅ Found " + availableCars.size() + " available cars");
            
            System.out.println("\n📝 Creating booking for " + user2.getName() + "...");
            Booking booking2 = bookingService.createBooking(user2, car1, startTime2, endTime2, 
                    "LOC001", dailyPricing);
            System.out.println("✅ Booking created: " + booking2.getBookingId());
            System.out.println("   Vehicle: " + car1.getName() + " " + car1.getModel());
            System.out.println("   Duration: 2 days");
            System.out.println("   Estimated Cost: $" + booking2.getEstimatedCost());
            
            System.out.println("\n💰 Confirming booking with UPI payment...");
            bookingService.confirmBooking(booking2.getBookingId(), upiPayment);
            System.out.println("✅ Booking confirmed!");
            
            System.out.println("\n🚀 Activating booking...");
            bookingService.activateBooking(booking2.getBookingId());
            System.out.println("✅ Booking activated!");
            
            System.out.println("\n🏁 Completing booking with 1 day late return...");
            LocalDateTime actualReturn2 = endTime2.plusDays(1);
            double finalCost2 = bookingService.completeBooking(booking2.getBookingId(), 
                    actualReturn2, dailyPricing, upiPayment);
            System.out.println("✅ Booking completed!");
            System.out.println("   Final Cost (including late fee): $" + finalCost2);
            System.out.println("   Additional charges: $" + (finalCost2 - booking2.getEstimatedCost()));
            
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        
        // Scenario 3: Booking cancellation
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📋 SCENARIO 3: Booking Cancellation");
        System.out.println("=".repeat(80));
        
        try {
            PriceStrategyService priceStrategyService = PriceStrategyService.getInstance();
            PricingStrategy hourlyPricing = priceStrategyService.getPricingStrategy(PricingType.HOURLY);
            PaymentStrategy cardPayment = new CardPaymentStrategy();
            
            LocalDateTime startTime3 = LocalDateTime.now().plusHours(5);
            LocalDateTime endTime3 = startTime3.plusHours(2);
            
            System.out.println("\n📝 Creating booking...");
            Booking booking3 = bookingService.createBooking(user1, bike2, startTime3, endTime3, 
                    "LOC001", hourlyPricing);
            System.out.println("✅ Booking created: " + booking3.getBookingId());
            
            System.out.println("\n💰 Confirming booking...");
            bookingService.confirmBooking(booking3.getBookingId(), cardPayment);
            System.out.println("✅ Booking confirmed!");
            
            System.out.println("\n❌ Cancelling booking...");
            bookingService.cancelBooking(booking3.getBookingId());
            System.out.println("✅ Booking cancelled! Vehicle is now available again");
            System.out.println("   Status: " + booking3.getStatus());
            
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        
        // Display system statistics
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📊 SYSTEM STATISTICS");
        System.out.println("=".repeat(80));
        
        System.out.println("\n🚗 Total Vehicles: " + vehicleService.getAllVehicles().size());
        System.out.println("📍 Total Locations: " + locationService.getAllLocations().size());
        System.out.println("📋 Active Bookings: " + bookingService.getActiveBookings().size());
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("✅ DEMONSTRATION COMPLETED SUCCESSFULLY!");
        System.out.println("=".repeat(80));
        
        System.out.println("\n🎯 SDE-2 Level Features Demonstrated:");
        System.out.println("   ✓ Thread-safe Singleton pattern (double-checked locking)");
        System.out.println("   ✓ Builder pattern for complex object construction");
        System.out.println("   ✓ Factory pattern for vehicle creation");
        System.out.println("   ✓ Strategy pattern for payments and pricing");
        System.out.println("   ✓ State pattern for booking lifecycle");
        System.out.println("   ✓ Repository pattern for data access");
        System.out.println("   ✓ Service layer separation");
        System.out.println("   ✓ Comprehensive validation and exception handling");
        System.out.println("   ✓ Time-based pricing with duration tracking");
        System.out.println("   ✓ Late return fee calculation");
        System.out.println("   ✓ Multi-location support");
        System.out.println("   ✓ Proper state management");
        System.out.println("   ✓ Transaction-like booking flow");
    }
}
