package designPatterns.strategyDesignPattern.vehicles;

import designPatterns.strategyDesignPattern.strategyPattern.NormalDriveStrategy;

public class PassengerVehicle extends  Vehicle{
    public PassengerVehicle(){
        super(new NormalDriveStrategy());
    }
}
