package designPatterns.carRentalSystemDesign.payment;

import designPatterns.carRentalSystemDesign.entities.User;

public class CardPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(int amount, User user) {
        System.out.println("💳 Processing card payment of $" + amount + " for user: " + user.getName());
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "Credit/Debit Card";
    }
}
