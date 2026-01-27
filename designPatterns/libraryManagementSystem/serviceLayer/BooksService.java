package designPatterns.libraryManagementSystem.serviceLayer;

import designPatterns.libraryManagementSystem.dtoLayer.entities.Book;
import designPatterns.libraryManagementSystem.dtoLayer.enums.Department;
import designPatterns.libraryManagementSystem.dtoLayer.repositories.BookRepository;

import java.util.List;

public class BooksService {
    private static volatile BooksService instance;
    private static BookRepository bookRepository;
    private BooksService() {
        bookRepository = BookRepository.getInstance();
    }
    public static BooksService getInstance() {
        if (instance == null) {
            synchronized (BooksService.class) {
                if (instance == null) {
                    return instance = new BooksService();
                }
            }
        }
        return instance;
    }

    public Book newBook(String name, String authorName, Department department){
        return new Book(name, authorName, department);
    }

    public List<Book> getAvailableBooks(Department department) {
        return bookRepository.findByDepartment(department);
    }

}
