package designPatterns.libraryManagementSystem.dtoLayer.interfaces;

import designPatterns.libraryManagementSystem.dtoLayer.entities.Booking;
import designPatterns.libraryManagementSystem.dtoLayer.enums.BookingState;

public class CreditCardPayment implements PaymentProcess {
    private static volatile CreditCardPayment instance;

    private CreditCardPayment() {
    }

    public static CreditCardPayment getInstance() {
        if (instance == null) {
            synchronized (CreditCardPayment.class) {
                if (instance == null) {
                    return instance = new CreditCardPayment();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean pay(Booking booking) {
        System.out.println("Amount paid successfully");
        booking.setStatus(BookingState.Paid);
        return true;
    }
}
