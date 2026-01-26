package designPatterns.abstractFactoryDesignPattern.factory;

import designPatterns.abstractFactoryDesignPattern.products.Engine;
import designPatterns.abstractFactoryDesignPattern.products.Transmission;
import designPatterns.abstractFactoryDesignPattern.service.FourWheelerVehicles;

/**
 * Concrete Factory for Four Wheeler vehicles
 * Creates a family of related designPatterns.abstractFactoryDesignPattern.products specific to four-wheelers
 */
public class FourWheelerFactory implements VehicleFactory {

    private static final FourWheelerFactory instance = new FourWheelerFactory();

    public static FourWheelerFactory getInstance() {
        return instance;
    }

    private FourWheelerFactory() {

    }

    public FourWheelerVehicles createVehicle() {
        return new FourWheelerVehicles();
    }


    public Engine createEngine() {
        return new FourStrokeEngine();
    }


    public Transmission createTransmission() {
        return new AutomaticTransmission();
    }

    // Concrete product implementations for Four Wheeler family

    private static class FourStrokeEngine implements Engine {

        public void start() {
            System.out.println("Four Stroke Engine: Starting smoothly");
        }


        public void stop() {
            System.out.println("Four Stroke Engine: Stopping gracefully");
        }


        public String getEngineType() {
            return "Four Stroke V6";
        }


        public int getHorsepower() {
            return 300;
        }
    }

    private static class AutomaticTransmission implements Transmission {
        private int currentGear = 1;


        public void shiftGear(int gear) {
            this.currentGear = gear;
            System.out.println("Automatic Transmission: Auto-shifted to gear " + gear);
        }


        public String getTransmissionType() {
            return "Automatic CVT";
        }


        public int getCurrentGear() {
            return currentGear;
        }
    }
}
