package designPatterns.rateLimiterDesign.entities;

import designPatterns.rateLimiterDesign.strategies.RateLimitingStrategy;

public class RateLimiter {
    int limit;
    int windowLength;
    RateLimitingStrategy rateLimitingStrategy;

    public RateLimiter(int limit, int windowLength, RateLimitingStrategy rateLimitingStrategy) {
        this.limit = limit;
        this.windowLength = windowLength;
        this.rateLimitingStrategy = rateLimitingStrategy;
    }

    public RateLimitingStrategy getRateLimitingStrategy() {
        return rateLimitingStrategy;
    }
}
