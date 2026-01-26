package designPatterns.abstractFactoryDesignPattern.factory;

import designPatterns.abstractFactoryDesignPattern.products.Engine;
import designPatterns.abstractFactoryDesignPattern.products.Transmission;
import designPatterns.abstractFactoryDesignPattern.service.TwoWheelerVehicles;

/**
 * Concrete Factory for Two Wheeler vehicles
 * Creates a family of related designPatterns.abstractFactoryDesignPattern.products specific to two-wheelers
 */
public class TwoWheelerFactory implements VehicleFactory {

    private static final TwoWheelerFactory instance = new TwoWheelerFactory();

    public TwoWheelerVehicles createVehicle() {
        return new TwoWheelerVehicles();
    }

    private TwoWheelerFactory() {

    }

    public static TwoWheelerFactory getInstance() {
        return instance;
    }

    public Engine createEngine() {
        return new TwoStrokeEngine();
    }


    public Transmission createTransmission() {
        return new ManualTransmission();
    }

    private static class TwoStrokeEngine implements Engine {

        public void start() {
            System.out.println("Two Stroke Engine: Starting with high RPM");
        }


        public void stop() {
            System.out.println("Two Stroke Engine: Stopping");
        }


        public String getEngineType() {
            return "Two Stroke";
        }


        public int getHorsepower() {
            return 150;
        }
    }

    private static class ManualTransmission implements Transmission {
        private int currentGear = 0;


        public void shiftGear(int gear) {
            this.currentGear = gear;
            System.out.println("Manual Transmission: Shifted to gear " + gear);
        }


        public String getTransmissionType() {
            return "Manual 6-Speed";
        }


        public int getCurrentGear() {
            return currentGear;
        }
    }
}
