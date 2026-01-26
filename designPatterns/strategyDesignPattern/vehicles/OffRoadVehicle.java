package designPatterns.strategyDesignPattern.vehicles;

import designPatterns.strategyDesignPattern.strategyPattern.SportDriveStrategy;

public class OffRoadVehicle extends Vehicle{

//    @Override
//    void  drive(){
//        System.out.println("SportVehicle drive");
//    } ----> Code duplicate

    public OffRoadVehicle(){
        super(new SportDriveStrategy());
    }
}
