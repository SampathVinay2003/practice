package designPatterns.libraryManagementSystem.dtoLayer.interfaces;

import designPatterns.libraryManagementSystem.dtoLayer.entities.Booking;

public class UPIPayment implements PaymentProcess {
    private static volatile UPIPayment instance;

    private UPIPayment() {
    }

    public static UPIPayment getInstance() {
        if (instance == null) {
            synchronized (UPIPayment.class) {
                if (instance == null) {
                    return instance = new UPIPayment();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean pay(Booking booking) {
        return false;
    }
}
