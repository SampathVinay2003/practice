package designPatterns.libraryManagementSystem;

import designPatterns.libraryManagementSystem.dtoLayer.entities.Author;
import designPatterns.libraryManagementSystem.dtoLayer.entities.Book;
import designPatterns.libraryManagementSystem.dtoLayer.entities.Booking;
import designPatterns.libraryManagementSystem.dtoLayer.entities.User;
import designPatterns.libraryManagementSystem.dtoLayer.enums.Department;
import designPatterns.libraryManagementSystem.dtoLayer.enums.PaymentStrategy;
import designPatterns.libraryManagementSystem.dtoLayer.interfaces.PaymentProcess;
import designPatterns.libraryManagementSystem.dtoLayer.repositories.AuthorRepository;
import designPatterns.libraryManagementSystem.dtoLayer.repositories.BookRepository;
import designPatterns.libraryManagementSystem.dtoLayer.repositories.UserRepository;
import designPatterns.libraryManagementSystem.exceptions.BookingException;
import designPatterns.libraryManagementSystem.serviceLayer.*;

import java.util.List;

public class LibrarySystem {
    public static void main(String[] args) {
        BookRepository bookRepository = BookRepository.getInstance();
        UserRepository userRepository = UserRepository.getInstance();
        BooksService booksService = BooksService.getInstance();
        InventoryService inventoryService = InventoryService.getInstance();
        BookingService bookingService = BookingService.getInstance();
        PaymentService paymentService = PaymentService.getInstance();
        AuthorService authorService = AuthorService.getInstance();
        UserService userService = UserService.getInstance();
        Author author = authorService.newAuthor("Oliver");
        AuthorRepository authorRepository = AuthorRepository.getInstance();
        authorRepository.save(author);
        Book book1 = booksService.newBook("Ramayana", "Valmiki", Department.History);
        Book book2 = booksService.newBook("Mahabharatha", "VedaVyasa", Department.History);
        Book book3 = booksService.newBook("Gulliver Travels", "Oliver", Department.Story);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        User user = userService.newUser("Sampath", "1234567890");
        userRepository.save(user);
        try {
            List<Book> ramayanaBooks = inventoryService.getBookByName("Ramayana");
            if (!ramayanaBooks.isEmpty()) {
                try {
                    Booking booking = bookingService.lendBook("Ramayana", 1, user);
                    if (booking != null) {
                        try {
                            PaymentProcess paymentProcess = paymentService.getWay(PaymentStrategy.CreditCard);
                            boolean bookedStatus = paymentProcess.pay(booking);
                            if (bookedStatus) {
                                bookingService.generateReceipt(booking);
                            } else {
                                bookingService.revertBooking(booking);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } catch (BookingException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Not a normal Booking Exception");
                    System.out.println(e.getMessage());
                }

            } else {
                System.out.println("Sorry we do not have that book!!");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
