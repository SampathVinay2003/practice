package designPatterns.libraryManagementSystem.serviceLayer;

import designPatterns.libraryManagementSystem.dtoLayer.entities.Book;
import designPatterns.libraryManagementSystem.dtoLayer.entities.Booking;
import designPatterns.libraryManagementSystem.dtoLayer.entities.User;
import designPatterns.libraryManagementSystem.dtoLayer.enums.BookingState;
import designPatterns.libraryManagementSystem.dtoLayer.repositories.BookRepository;
import designPatterns.libraryManagementSystem.dtoLayer.repositories.BookingRepository;

import java.util.List;

public class BookingService {
    private static volatile BookingService instance;
    private static BookingRepository bookingRepository;
    private static BookRepository bookRepository;
    private static InventoryService inventoryService;
    private BookingService() {
        inventoryService = InventoryService.getInstance();
        bookRepository = BookRepository.getInstance();
        bookingRepository = BookingRepository.getInstance();
    }
    public static BookingService getInstance() {
        if (instance == null) {
            synchronized (BooksService.class) {
                if (instance == null) {
                    return instance = new BookingService();
                }
            }
        }
        return instance;
    }

    public Booking lendBook(String bookName, int i, User user) {
        List<Book> books = bookRepository.findByBookName(bookName);
        if(books.size() < i){
            System.out.println("Sorry!! Required Number of copies are not there. We will notify when available. Thanks.");
        }else{
            inventoryService.borrowBooks(books, i);
            Booking booking = new Booking();
            booking.setUser(user.getId());
            booking.setBooksBorrowed(books.stream().map(Book::getId).toList());
            return booking;
        }
        return null;
    }

    public void generateReceipt(Booking booking) {
        System.out.println("Receipt Generated successfully.");
        for(String bookId: booking.getBookIds()){
            System.out.println(bookId);
        }
        System.out.println("-----------END OF Receipt------------");
    }

    public void revertBooking(Booking booking) {
        List<String> books = booking.getBookIds();
        inventoryService.addBooksToInventory(books);
        booking.setStatus(BookingState.Pending);
    }
}
