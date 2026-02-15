package designPatterns.rateLimiterDesign.entities;

import designPatterns.rateLimiterDesign.strategies.RateLimitingStrategy;

public class RateLimiter {
    RateLimitingStrategy rateLimitingStrategy;

    public RateLimiter( RateLimitingStrategy rateLimitingStrategy) {
        this.rateLimitingStrategy = rateLimitingStrategy;
    }

    public RateLimitingStrategy getRateLimitingStrategy() {
        return rateLimitingStrategy;
    }
}
