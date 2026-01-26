package designPatterns.strategyDesignPattern.strategyPattern;

public class NormalDriveStrategy implements DriveStrategy{
    public  void drive(){
        System.out.println("NormalDriveStrategy drive");
    }
}
