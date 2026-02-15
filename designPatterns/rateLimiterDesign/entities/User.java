package designPatterns.rateLimiterDesign.entities;

import designPatterns.rateLimiterDesign.enums.SubscriptionType;

import java.util.UUID;

public class User {
    String id;
    String name;
    SubscriptionType subscriptionType;

    public User(String name, SubscriptionType subscriptionType) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.subscriptionType = subscriptionType;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public String getId() {
        return id;
    }
}
