package designPatterns.libraryManagementSystem.dtoLayer.interfaces;

import designPatterns.libraryManagementSystem.dtoLayer.entities.Booking;

public interface PaymentProcess {

    boolean pay(Booking booking);
}
