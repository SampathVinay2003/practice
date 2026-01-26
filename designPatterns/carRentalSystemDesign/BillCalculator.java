package designPatterns.carRentalSystemDesign;

import designPatterns.carRentalSystemDesign.entities.Bike;
import designPatterns.carRentalSystemDesign.entities.User;

public class BillCalculator {
    private static BillCalculator instance;
    private BillCalculator(){
    }
    public static BillCalculator getInstance(){
        if(instance == null){
            return instance = new BillCalculator();
        }
        return instance;
    }

    public void generateBill(User person1, Bike bike) {
        System.out.println("Bill for " + person1.getName() + " for " + bike.getName());
    }
}
