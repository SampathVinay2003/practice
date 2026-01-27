package designPatterns.libraryManagementSystem.dtoLayer.repositories;

import designPatterns.libraryManagementSystem.dtoLayer.entities.Book;
import designPatterns.libraryManagementSystem.dtoLayer.enums.Department;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InventoryRepository {
    private static volatile InventoryRepository instance;
    private Map<String, Map<Department, Integer>> inventoryMap;

    private InventoryRepository() {
        inventoryMap = new ConcurrentHashMap<>();
    }

    public static InventoryRepository getInstance() {
        if (instance == null) {
            synchronized (InventoryRepository.class) {
                if (instance == null) {
                    return instance = new InventoryRepository();
                }
            }
        }
        return instance;
    }

    public void save(Book book) {
        if( book == null)return;
        Map<Department, Integer> value = inventoryMap.get(book.getId());
        if (value == null) {
            value = new HashMap<>();
        }
        value.put(book.getDepartment(), value.getOrDefault(book.getDepartment(), 0) + 1);
        inventoryMap.put(book.getId(), value);
    }


    public void borrow(Book book, int i) {
        Map<Department, Integer> value = inventoryMap.get(book.getId());
        value.put(book.getDepartment(), value.getOrDefault(book.getDepartment(), 0) - i);
        if (value.get(book.getDepartment()) == 0) {
            inventoryMap.remove(book.getDepartment());
            return;
        }
        inventoryMap.put(book.getId(), value);
    }

    public List<Book> findByDepartment(Department department) {
        BookRepository bookRepository = BookRepository.getInstance();
        return bookRepository.findByDepartment(department).stream()
                .filter(book -> inventoryMap.containsKey(book.getId()))
                .collect(Collectors.toList());
    }

    public List<Book> findByBookName(String name) {
        BookRepository bookRepository = BookRepository.getInstance();
        return bookRepository.findByBookName(name).stream()
                .filter(book -> inventoryMap.containsKey(book.getId()))
                .collect(Collectors.toList());
    }

    public void borrowBooks(List<Book> books, int i) {
        for (Book book : books) {
            if (inventoryMap.containsKey(book.getId()) && inventoryMap.get(book.getId()).get(book.getDepartment()) >= i) {
                borrow(book, i);
            } else {
                System.out.println("Sorry the " + book.getName() + " is not available. Count is low in Inventory.");
            }
        }
    }

    public void addBooksToInventory(List<String> bookIds) {
        for (String id : bookIds) {
            Optional<Book> book = BookRepository.getInstance().findById(id);
            save(book.orElse(null));
        }
    }
}
