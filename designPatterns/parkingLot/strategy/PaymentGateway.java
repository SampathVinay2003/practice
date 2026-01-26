package designPatterns.parkingLot.strategy;

public interface PaymentGateway {
    boolean pay(int amount);
}
