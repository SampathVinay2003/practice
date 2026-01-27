package designPatterns.libraryManagementSystem.dtoLayer.entities;

import designPatterns.libraryManagementSystem.dtoLayer.enums.BookingState;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Booking {
    String bookingId;
    String userId;
    List<String> bookIds;
    LocalDateTime borrowDate;
    LocalDateTime dueDate;
    LocalDateTime returnDate; // null until returned
    BookingState status;
    double fineAmount;

    public Booking() {
        this.borrowDate = LocalDateTime.now();
        this.status = BookingState.Pending;
        this.bookingId = UUID.randomUUID().toString();
        this.dueDate = borrowDate.plusDays(4);
    }

    public void setUser(String userId) {
        this.userId = userId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public BookingState getStatus() {
        return status;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public void setStatus(BookingState bookingState) {
        this.status = bookingState;
    }

    public void setBooksBorrowed(List<String> bookIds) {
        this.bookIds = bookIds;
    }

    public List<String> getBookIds() {
        return bookIds;
    }
}
