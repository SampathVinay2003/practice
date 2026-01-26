# Car Rental System - SDE-2 Level Implementation

## 🎯 Overview
A production-ready car rental system demonstrating advanced software engineering principles, design patterns, and best practices suitable for SDE-2 level interviews and real-world applications.

## 🏗️ Architecture

### **Layered Architecture**
```
┌─────────────────────────────────────┐
│     Presentation Layer (Demo)       │
├─────────────────────────────────────┤
│        Service Layer                │
│  (BookingService, VehicleService)   │
├─────────────────────────────────────┤
│      Business Logic Layer           │
│  (State, Pricing, Validation)       │
├─────────────────────────────────────┤
│     Data Access Layer (Repository)  │
├─────────────────────────────────────┤
│        Domain Entities              │
└─────────────────────────────────────┘
```

## 🎨 Design Patterns Implemented

### 1. **Singleton Pattern** (Thread-Safe)
- All services: `BookingService`, `VehicleService`, `LocationService`
- All repositories: `VehicleRepository`, `BookingRepository`, `LocationRepository`
- Factory: `VehicleFactory`
- **Implementation**: Double-checked locking with volatile keyword

### 2. **Builder Pattern**
- `Booking.Builder` - Fluent API for complex booking construction
- Ensures immutability of booking ID and timestamps

### 3. **Factory Pattern**
- `VehicleFactory` - Creates different vehicle types with proper initialization
- Centralizes vehicle creation logic

### 4. **Strategy Pattern**
- **Pricing Strategies**: `HourlyPricingStrategy`, `DailyPricingStrategy`, `WeeklyPricingStrategy`
- **Payment Strategies**: `CardPaymentStrategy`, `UPIStrategy`
- Enables runtime algorithm selection

### 5. **State Pattern**
- Booking lifecycle: `PendingPaymentState` → `ConfirmedState` → `ActiveState` → Completed
- Enforces valid state transitions
- Prevents invalid operations (e.g., can't complete non-active booking)

### 6. **Repository Pattern**
- Abstracts data access layer
- Thread-safe using `ConcurrentHashMap`
- Provides query methods for complex filtering

## 📦 Package Structure

```
carRentalSystemDesign/
├── entities/              # Domain models
│   ├── Booking.java
│   ├── Location.java
│   ├── Vehicle.java
│   ├── bike/
│   │   └── Bike.java
│   ├── car/
│   │   └── Car.java
│   └── user/
│       └── User.java
├── enums/                 # Enumerations
│   ├── BookingStatus.java
│   ├── VehicleStatus.java
│   ├── VehicleType.java
│   └── PricingType.java
├── exceptions/            # Custom exceptions
│   ├── BookingNotFoundException.java
│   ├── InvalidBookingException.java
│   ├── PaymentFailedException.java
│   └── VehicleNotAvailableException.java
├── factory/               # Factory pattern
│   └── VehicleFactory.java
├── payment/               # Payment strategies
│   ├── PaymentStrategy.java
│   ├── CardPaymentStrategy.java
│   └── UPIStrategy.java
├── pricing/               # Pricing strategies
│   ├── PricingStrategy.java
│   ├── HourlyPricingStrategy.java
│   ├── DailyPricingStrategy.java
│   └── WeeklyPricingStrategy.java
├── repository/            # Data access layer
│   ├── BookingRepository.java
│   ├── VehicleRepository.java
│   └── LocationRepository.java
├── service/               # Business logic layer
│   ├── BookingService.java
│   ├── VehicleService.java
│   └── LocationService.java
├── state/                 # State pattern
│   ├── BookingState.java
│   ├── PendingPaymentState.java
│   ├── ConfirmedState.java
│   └── ActiveState.java
├── validation/            # Validation logic
│   └── BookingValidator.java
└── RentalSystemDemo.java  # Main demonstration
```

## ✨ Key Features

### **1. Time-Based Pricing**
- Multiple pricing strategies (hourly, daily, weekly)
- Duration tracking from start to end time
- Late return fee calculation
- Addon pricing (helmet for bikes, GPS/child seat for cars)

### **2. Booking Lifecycle Management**
- State transitions: Pending → Confirmed → Active → Completed
- Cancellation support (with state validation)
- Automatic vehicle status updates

### **3. Multi-Location Support**
- Multiple rental branches
- Location-based vehicle inventory
- Operating hours validation

### **4. Thread Safety**
- All singletons use double-checked locking
- Repositories use `ConcurrentHashMap`
- Safe for concurrent access

### **5. Comprehensive Validation**
- User validation
- Booking request validation
- Time validation (no past bookings)
- Vehicle availability checks

### **6. Exception Handling**
- Custom exceptions for different error scenarios
- Proper error messages
- Rollback on payment failure

### **7. Transaction-Like Operations**
- Booking creation reserves vehicle
- Payment failure releases vehicle
- Atomic state transitions

## 🚀 How to Run

```bash
# Compile
javac designPatterns/carRentalSystemDesign/RentalSystemDemo.java

# Run
java designPatterns.carRentalSystemDesign.RentalSystemDemo
```

## 📊 Demonstration Scenarios

### Scenario 1: Hourly Bike Rental
- User books a bike for 3 hours
- Payment confirmation
- Vehicle pickup (activation)
- On-time return
- Final cost calculation

### Scenario 2: Daily Car Rental with Late Return
- User books a car for 2 days
- Late return by 1 day
- Late fee calculation
- Additional payment processing

### Scenario 3: Booking Cancellation
- User creates and confirms booking
- Cancels before pickup
- Vehicle becomes available again

## 🎯 SDE-2 Level Competencies Demonstrated

### **Design & Architecture**
✅ Proper separation of concerns (layers)
✅ SOLID principles adherence
✅ Design pattern mastery (6 patterns)
✅ Scalable architecture

### **Code Quality**
✅ Thread-safe implementations
✅ Proper exception handling
✅ Comprehensive validation
✅ Clean code principles

### **Business Logic**
✅ Complex state management
✅ Time-based calculations
✅ Transaction-like operations
✅ Edge case handling

### **Data Management**
✅ Repository pattern
✅ In-memory data store (production would use DB)
✅ Efficient querying and filtering

### **Extensibility**
✅ Easy to add new vehicle types
✅ Easy to add new payment methods
✅ Easy to add new pricing strategies
✅ Easy to add new locations

## 🔄 Future Enhancements (SDE-3 Level)

- Database integration (JPA/Hibernate)
- REST API layer (Spring Boot)
- Caching layer (Redis)
- Event-driven architecture (Kafka)
- Microservices decomposition
- Distributed transactions
- Rate limiting
- Circuit breakers
- Monitoring & observability
- Multi-tenancy support

## 📝 Notes

This implementation focuses on demonstrating:
1. **Architectural thinking** - Proper layering and separation
2. **Design patterns** - Correct usage and understanding
3. **Production readiness** - Thread safety, validation, error handling
4. **Scalability** - Easy to extend and maintain
5. **Best practices** - Clean code, SOLID principles

Perfect for SDE-2 level system design interviews and coding assessments.
