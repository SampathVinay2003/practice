package designPatterns.libraryManagementSystem.dtoLayer.repositories;

import designPatterns.libraryManagementSystem.dtoLayer.entities.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class UserRepository {
    private static volatile UserRepository instance;
    private Map<String, User> userMap;
    private UserRepository(){
        userMap = new ConcurrentHashMap<>();
    }

    public static UserRepository getInstance() {
        if(instance == null){
            synchronized (UserRepository.class){
                if(instance == null){
                    return instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public void save(User user) {
        userMap.put(user.getId(), user);
    }
}
