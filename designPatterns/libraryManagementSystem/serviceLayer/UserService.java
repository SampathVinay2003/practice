package designPatterns.libraryManagementSystem.serviceLayer;

import designPatterns.libraryManagementSystem.dtoLayer.entities.User;

public class UserService {
    private static volatile UserService instance;

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    return instance = new UserService();
                }
            }
        }
        return instance;
    }

    public User newUser(String name, String phoneNumber) {
        return new User(name, phoneNumber);
    }
}
