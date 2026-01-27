package designPatterns.libraryManagementSystem.serviceLayer;

import designPatterns.libraryManagementSystem.dtoLayer.entities.Author;

public class AuthorService {
    private static volatile AuthorService instance;

    private AuthorService() {

    }

    public static AuthorService getInstance() {
        if (instance == null) {
            synchronized (AuthorService.class) {
                if (instance == null) {
                    return instance = new AuthorService();
                }
            }
        }
        return instance;
    }

    public Author newAuthor(String name) {
        return new Author(name);
    }
}
