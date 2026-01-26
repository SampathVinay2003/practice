package designPatterns.abstractFactoryDesignPattern.factory;

import designPatterns.abstractFactoryDesignPattern.products.Engine;
import designPatterns.abstractFactoryDesignPattern.products.Transmission;
import designPatterns.abstractFactoryDesignPattern.service.ThreeWheelerVehicles;

/**
 * Concrete Factory for Three Wheeler vehicles
 * Creates a family of related designPatterns.abstractFactoryDesignPattern.products specific to three-wheelers
 */
public class ThreeWheelerFactory implements VehicleFactory {
    
    private static final ThreeWheelerFactory instance = new ThreeWheelerFactory();

    public ThreeWheelerVehicles createVehicle() {
        return new ThreeWheelerVehicles();
    }
    private ThreeWheelerFactory(){}
    public static ThreeWheelerFactory getInstance() {
        return instance;
    }
    
    
    public Engine createEngine() {
        return new SingleCylinderEngine();
    }
    
    
    public Transmission createTransmission() {
        return new SemiAutomaticTransmission();
    }
    
    
    private static class SingleCylinderEngine implements Engine {
        
        public void start() {
            System.out.println("Single Cylinder Engine: Starting with moderate power");
        }
        
        
        public void stop() {
            System.out.println("Single Cylinder Engine: Stopping");
        }
        
        
        public String getEngineType() {
            return "Single Cylinder 4-Stroke";
        }
        
        
        public int getHorsepower() {
            return 10;
        }
    }
    
    private static class SemiAutomaticTransmission implements Transmission {
        private int currentGear = 1;
        
        
        public void shiftGear(int gear) {
            this.currentGear = gear;
            System.out.println("Semi-Automatic Transmission: Shifted to gear " + gear);
        }
        
        
        public String getTransmissionType() {
            return "Semi-Automatic 4-Speed";
        }
        
        
        public int getCurrentGear() {
            return currentGear;
        }
    }
}
