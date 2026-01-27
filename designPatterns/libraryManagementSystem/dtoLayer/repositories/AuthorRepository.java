package designPatterns.libraryManagementSystem.dtoLayer.repositories;

import designPatterns.libraryManagementSystem.dtoLayer.entities.Author;

import java.util.HashMap;
import java.util.Map;

public class AuthorRepository {
    private static volatile AuthorRepository instance;
    private static Map<String, String> authorsMap;
    private AuthorRepository() {
        authorsMap = new HashMap<>();
    }
    public static AuthorRepository getInstance() {
        if (instance == null) {
            synchronized (AuthorRepository.class) {
                if (instance == null) {
                    return instance = new AuthorRepository();
                }
            }
        }
        return instance;
    }

    public void save(Author author) {
        if(!authorsMap.containsKey(author.getId())) {
            authorsMap.put(author.getId(), author.getName());
        }
    }

    public String getAuthorId(String authorName) {
        for(Map.Entry<String, String> entry: authorsMap.entrySet()){
            if(entry.getValue().equals(authorName)){
                return entry.getKey();
            }
        }
        return null;
    }
}
