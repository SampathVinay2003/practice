package designPatterns.rateLimiterDesign.strategies;

import designPatterns.rateLimiterDesign.entities.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class TokenBucketLimitingStrategy implements RateLimitingStrategy {
    static final int MAX_TOKENS = 10;
    static final int refillRate = 500; // Refill every 500ms for testing
    Map<String, Integer> userTokenMap = new ConcurrentHashMap<>();// Reason being when same user trues from different devices we need synchronized.
    Map<String, Long> userLastRequestTimeMap = new ConcurrentHashMap<>();

    public TokenBucketLimitingStrategy() {

    }

    @Override
    public boolean allowRequest(User user) {
        AtomicBoolean isAllowed = new AtomicBoolean(false);
        userTokenMap.compute(user.getId(), (userId, currentTokens) -> {
            long timeNow = System.currentTimeMillis();
            Long lastRequestTime = userLastRequestTimeMap.get(userId);

            // Initialize tokens for new user
            if (currentTokens == null) {
                currentTokens = MAX_TOKENS;
                userLastRequestTimeMap.put(userId, timeNow);
            }

            // Calculate tokens to add based on time elapsed
            if (lastRequestTime != null) {
                long timeElapsed = timeNow - lastRequestTime;
                int tokensToAdd = (int) (timeElapsed / refillRate);
                currentTokens = Math.min(MAX_TOKENS, currentTokens + tokensToAdd);
            }

            // Check if request can be allowed
            if (currentTokens > 0) {
                userLastRequestTimeMap.put(userId, timeNow);
                isAllowed.set(true);
                return currentTokens - 1;
            }

            return currentTokens;
        });
        return isAllowed.get();
    }
}
