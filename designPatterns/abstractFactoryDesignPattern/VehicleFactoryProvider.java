package designPatterns.abstractFactoryDesignPattern;

import designPatterns.abstractFactoryDesignPattern.factory.FourWheelerFactory;
import designPatterns.abstractFactoryDesignPattern.factory.ThreeWheelerFactory;
import designPatterns.abstractFactoryDesignPattern.factory.TwoWheelerFactory;
import designPatterns.abstractFactoryDesignPattern.factory.VehicleFactory;

/**
 * Factory Provider - helps select the appropriate concrete factory
 * This is a common pattern used with Abstract Factory
 */
public class VehicleFactoryProvider {

    public static VehicleFactory getFactory(String vehicleType) {

        return switch (vehicleType.toLowerCase()) {
            case "twowheeler", "motorcycle", "bike", "2" -> TwoWheelerFactory.getInstance();
            case "threewheeler", "autorickshaw", "rickshaw", "3" -> ThreeWheelerFactory.getInstance();
            case "fourwheeler", "car", "automobile", "4" -> FourWheelerFactory.getInstance();
            default -> throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        };
    }

}
