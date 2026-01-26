package designPatterns.strategyDesignPattern.vehicles;

import designPatterns.strategyDesignPattern.strategyPattern.DriveStrategy;

public class Vehicle {
    DriveStrategy driveStrategy;
    public Vehicle(DriveStrategy driveStrategy){
        this.driveStrategy = driveStrategy;
    }
    public void drive(){
        driveStrategy.drive();
    }
}
