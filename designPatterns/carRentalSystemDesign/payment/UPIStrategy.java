package designPatterns.carRentalSystemDesign.payment;

import designPatterns.carRentalSystemDesign.entities.User;

public class UPIStrategy implements PaymentStrategy {
    @Override
    public boolean pay(int amount, User user) {
        System.out.println("📱 Processing UPI payment of $" + amount + " for user: " + user.getName());
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "UPI";
    }
}
