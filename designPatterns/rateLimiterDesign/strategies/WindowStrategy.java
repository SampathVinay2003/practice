package designPatterns.rateLimiterDesign.strategies;

import designPatterns.rateLimiterDesign.entities.User;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class WindowStrategy implements RateLimitingStrategy {
    static final int MAX_REQUESTS = 7;
    Map<String, Integer> userRequestCount = new ConcurrentHashMap<>();
    Map<String, Integer> userLastDay = new ConcurrentHashMap<>();

    @Override
    public boolean allowRequest(User user) {
        AtomicBoolean isAllowed = new AtomicBoolean(false);

        userRequestCount.compute(user.getId(), (userId, currentCount) -> {
            int currentDay = LocalDateTime.now().getDayOfMonth();
            int lastDay = userLastDay.getOrDefault(userId, -1);

            // Reset count if new day
            if (lastDay != currentDay) {
                userLastDay.put(userId, currentDay);
                currentCount = 0;
            }

            // Check if request can be allowed
            if (currentCount < MAX_REQUESTS) {
                isAllowed.set(true);
                return currentCount + 1;
            }

            return currentCount;
        });

        return isAllowed.get();
    }
}
