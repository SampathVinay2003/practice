package designPatterns.rateLimiterDesign.service;

import designPatterns.rateLimiterDesign.entities.User;
import designPatterns.rateLimiterDesign.enums.SubscriptionType;

public class UserService {
    private static UserService instance;

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

    public User addUser(String name, SubscriptionType subscriptionType) {
        return new User(name, subscriptionType);
    }
}
