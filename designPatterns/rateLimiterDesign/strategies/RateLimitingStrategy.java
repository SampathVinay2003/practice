package designPatterns.rateLimiterDesign.strategies;

import designPatterns.rateLimiterDesign.entities.User;

public interface RateLimitingStrategy {
    boolean allowRequest(User user);
}
