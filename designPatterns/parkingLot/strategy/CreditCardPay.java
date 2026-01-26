package designPatterns.parkingLot.strategy;

public class CreditCardPay implements PaymentGateway{
    @Override
    public boolean pay(int amount){
        System.out.println("Credit Card Payment of ₹" + amount + " successful");
        return true;
    }
}
