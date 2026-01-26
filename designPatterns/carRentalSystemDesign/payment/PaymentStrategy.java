package designPatterns.carRentalSystemDesign.payment;

import designPatterns.carRentalSystemDesign.entities.User;

public interface PaymentStrategy {
    boolean pay(int amount, User user);
    String getPaymentMethod();
}
