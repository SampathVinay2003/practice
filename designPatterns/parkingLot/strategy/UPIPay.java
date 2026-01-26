package designPatterns.parkingLot.strategy;

public class UPIPay implements PaymentGateway{
    @Override
    public boolean pay(int amount) {
        System.out.println("UPI Payment of ₹" + amount + " successful");
        return true;
    }
}
