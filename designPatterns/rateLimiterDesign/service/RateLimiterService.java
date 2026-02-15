package designPatterns.rateLimiterDesign.service;

import designPatterns.rateLimiterDesign.entities.RateLimiter;
import designPatterns.rateLimiterDesign.entities.User;
import designPatterns.rateLimiterDesign.enums.SubscriptionType;
import designPatterns.rateLimiterDesign.strategies.RateLimitingStrategy;
import designPatterns.rateLimiterDesign.strategies.SlidingWindowLimitingStrategy;
import designPatterns.rateLimiterDesign.strategies.TokenBucketLimitingStrategy;
import designPatterns.rateLimiterDesign.strategies.WindowStrategy;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterService {
    private Map<SubscriptionType, RateLimiter> rateLimiterMap;
    private static RateLimiterService instance;

    private RateLimiterService() {
        rateLimiterMap = new HashMap<>();
        rateLimiterMap.put(SubscriptionType.BASIC, new RateLimiter(new TokenBucketLimitingStrategy()));
        rateLimiterMap.put(SubscriptionType.PREMIUM, new RateLimiter(new WindowStrategy()));
        rateLimiterMap.put(SubscriptionType.ULTRA_PREMIUM, new RateLimiter(new SlidingWindowLimitingStrategy()));
    }

    public static RateLimiterService getInstance() {
        if (instance == null) {
            synchronized (RateLimiterService.class) {
                if (instance == null) {
                    return instance = new RateLimiterService();
                }
            }
        }
        return instance;
    }

    public boolean allowRequest(User user) {
        RateLimiter rateLimiter = rateLimiterMap.get(user.getSubscriptionType());
        if (rateLimiter == null) {
            throw new IllegalArgumentException("Invalid subscription type");
        }
        RateLimitingStrategy rateLimitingStrategy = rateLimiter.getRateLimitingStrategy();
        return rateLimitingStrategy.allowRequest(user);
    }
}
