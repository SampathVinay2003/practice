package designPatterns.libraryManagementSystem.serviceLayer;

import designPatterns.libraryManagementSystem.dtoLayer.entities.Book;
import designPatterns.libraryManagementSystem.dtoLayer.repositories.InventoryRepository;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    private static volatile InventoryService instance;
    private InventoryRepository inventoryRepository;

    private InventoryService() {
        inventoryRepository = InventoryRepository.getInstance();
    }

    public static InventoryService getInstance() {
        if (instance == null) {
            synchronized (BooksService.class) {
                if (instance == null) {
                    return instance = new InventoryService();
                }
            }
        }
        return instance;
    }

    public void borrowBooks(List<Book> books, int i) {
        if (i == 0) {
            System.out.println("0 books cant be borrowed!");
            return;
        }
        inventoryRepository.borrowBooks(books, i);
    }

    public List<Book> getBookByName(String... name) {
        List<Book> books = new ArrayList<>();
        for (String n : name) {
            books.addAll(inventoryRepository.findByBookName(n));
        }
        return books;
    }

    public void addBooksToInventory(List<String> bookIds) {
        inventoryRepository.addBooksToInventory(bookIds);
    }
}
