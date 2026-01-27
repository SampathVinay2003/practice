package designPatterns.libraryManagementSystem.dtoLayer.repositories;

import designPatterns.libraryManagementSystem.dtoLayer.entities.Book;
import designPatterns.libraryManagementSystem.dtoLayer.enums.Department;
import designPatterns.libraryManagementSystem.serviceLayer.InventoryService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class BookRepository {
    private static volatile BookRepository instance;
    private InventoryService inventoryService;
    private Map<String, Book> booksMap;
    private BookRepository(){
        inventoryService = InventoryService.getInstance();
        booksMap = new ConcurrentHashMap<>();
    }

    public static BookRepository getInstance() {
        if(instance == null){
            synchronized (BookRepository.class){
                if(instance == null){
                    return instance = new BookRepository();
                }
            }
        }
        return instance;
    }

    public void save(Book book) {
        booksMap.put(book.getId(), book);
        inventoryService.addBooksToInventory(List.of(book.getId()));
    }

    public List<Book> findByDepartment(Department department) {
        return booksMap.values().stream().filter(k -> k.getDepartment().equals(department)).toList();
    }

    public List<Book> findByBookName(String name) {
        return booksMap.values().stream().filter(k -> k.getName().equals(name)).toList();
    }

    public Optional<Book> findById(String id) {
        return Optional.ofNullable(booksMap.get(id));
    }
}
