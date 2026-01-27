package designPatterns.libraryManagementSystem.serviceLayer;

import designPatterns.libraryManagementSystem.dtoLayer.enums.PaymentStrategy;
import designPatterns.libraryManagementSystem.dtoLayer.interfaces.CreditCardPayment;
import designPatterns.libraryManagementSystem.dtoLayer.interfaces.PaymentProcess;
import designPatterns.libraryManagementSystem.dtoLayer.interfaces.UPIPayment;
import designPatterns.libraryManagementSystem.exceptions.InvalidPricingTypeException;

public class PaymentService {
    private static volatile PaymentService instance;

    private PaymentService() {
    }

    public static PaymentService getInstance() {
        if (instance == null) {
            synchronized (PaymentService.class) {
                if (instance == null) {
                    return instance = new PaymentService();
                }
            }
        }
        return instance;
    }

    public PaymentProcess getWay(PaymentStrategy paymentStrategy) {
        return switch (paymentStrategy) {
            case CreditCard -> CreditCardPayment.getInstance();
            case UPI -> UPIPayment.getInstance();
            default -> throw new InvalidPricingTypeException("Invalid pricing type: " + paymentStrategy);
        };
    }
}
