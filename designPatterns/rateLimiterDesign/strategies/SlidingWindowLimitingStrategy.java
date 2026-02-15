package designPatterns.rateLimiterDesign.strategies;

import designPatterns.rateLimiterDesign.entities.User;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class SlidingWindowLimitingStrategy implements RateLimitingStrategy {
    static final int MAX_REQUESTS = 8;
    static final long WINDOW_DURATION_MS = 60 * 1000L; // 60 seconds
    Map<String, Integer> userRequestCount = new ConcurrentHashMap<>();
    Map<String, Queue<Long>> userRequestTimestamps = new ConcurrentHashMap<>();

    @Override
    public boolean allowRequest(User user) {
        AtomicBoolean isAllowed = new AtomicBoolean(false);
        userRequestCount.compute(user.getId(), (userId, currentCount) -> {
            long timeNow = System.currentTimeMillis();
            if (currentCount == null) currentCount = 0;
            Queue<Long> timestamps = userRequestTimestamps.computeIfAbsent(userId, k -> new LinkedList<>());
            long windowStart = timeNow - WINDOW_DURATION_MS;

            // Remove requests outside the sliding window
            while (!timestamps.isEmpty() && timestamps.peek() < windowStart) {
                currentCount = Math.max(currentCount - 1, 0);
                timestamps.poll();
            }

            // Check if under limit and allow request
            if (currentCount < MAX_REQUESTS) {
                isAllowed.set(true);
                timestamps.add(timeNow);
                currentCount += 1;
            }
            return currentCount;
        });
        return isAllowed.get();
    }
}
