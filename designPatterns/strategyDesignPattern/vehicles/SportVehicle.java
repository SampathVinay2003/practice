package designPatterns.strategyDesignPattern.vehicles;

import designPatterns.strategyDesignPattern.strategyPattern.SportDriveStrategy;

public class SportVehicle extends Vehicle{
   public  SportVehicle(){
       super(new SportDriveStrategy());
   }
}
