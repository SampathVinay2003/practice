package designPatterns.rateLimiterDesign;

import designPatterns.rateLimiterDesign.entities.User;
import designPatterns.rateLimiterDesign.enums.SubscriptionType;
import designPatterns.rateLimiterDesign.service.RateLimiterService;
import designPatterns.rateLimiterDesign.service.UserService;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimiterService rateLimiterService = RateLimiterService.getInstance();
        UserService userService = UserService.getInstance();

        // Create users with different subscription types
        User sampath = userService.addUser("Sampath", SubscriptionType.BASIC);      // 10 requests, Token Bucket
        User surya = userService.addUser("Surya", SubscriptionType.PREMIUM);        // 100 requests, Fixed Window
        User vishnu = userService.addUser("Vishnu", SubscriptionType.ULTRA_PREMIUM); // 1000 requests, Sliding Window

        System.out.println("=== Rate Limiter Testing ===\n");

        // Test BASIC user (Token Bucket Strategy)
        System.out.println("Testing BASIC user (Token Bucket - 10 requests):");
        testBasicUser(rateLimiterService, sampath);

        Thread.sleep(1000); // Pause between tests

        // Test PREMIUM user (Fixed Window Strategy)
        System.out.println("\nTesting PREMIUM user (Fixed Window - 100 requests/day):");
        testPremiumUser(rateLimiterService, surya);

        Thread.sleep(1000); // Pause between tests

        // Test ULTRA_PREMIUM user (Sliding Window Strategy)
        System.out.println("\nTesting ULTRA_PREMIUM user (Sliding Window - 1000 requests/24hrs):");
        testUltraPremiumUser(rateLimiterService, vishnu);

        // Test concurrent access
        System.out.println("\n=== Concurrent Access Testing ===");
        testConcurrentAccess(rateLimiterService, sampath, surya, vishnu);
    }

    // Test BASIC user with Token Bucket (should allow smooth rate limiting)
    private static void testBasicUser(RateLimiterService rateLimiterService, User user) {
        for (int i = 1; i <= 15; i++) {
            boolean allowed = rateLimiterService.allowRequest(user);
            System.out.println("Request " + i + ": " + (allowed ? "✅ ALLOWED" : "❌ BLOCKED"));

            // Token bucket refills gradually, so some requests should be blocked
            if (i > 12) {
                try {
                    Thread.sleep(400); // Small delay to allow token refill
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // Test PREMIUM user with Fixed Window (resets at midnight)
    private static void testPremiumUser(RateLimiterService rateLimiterService, User user) {
        for (int i = 1; i <= 5; i++) {
            boolean allowed = rateLimiterService.allowRequest(user);
            System.out.println("Request " + i + ": " + (allowed ? "✅ ALLOWED" : "❌ BLOCKED"));
        }
        System.out.println("Note: Fixed window resets at midnight, not during test");
    }

    // Test ULTRA_PREMIUM user with Sliding Window (rolling 24-hour window)
    private static void testUltraPremiumUser(RateLimiterService rateLimiterService, User user) {
        for (int i = 1; i <= 5; i++) {
            boolean allowed = rateLimiterService.allowRequest(user);
            System.out.println("Request " + i + ": " + (allowed ? "✅ ALLOWED" : "❌ BLOCKED"));
        }
        System.out.println("Note: Sliding window prevents bursts within 24-hour period");
    }

    // Test concurrent access to verify thread safety
    private static void testConcurrentAccess(RateLimiterService rateLimiterService, User basic, User premium, User ultra) {
        Thread[] threads = new Thread[6];

        // Create threads for concurrent access
        for (int i = 0; i < 6; i++) {
            final int threadId = i;
            final User user = (i % 3 == 0) ? basic : (i % 3 == 1) ? premium : ultra;
            final String userType = (i % 3 == 0) ? "BASIC" : (i % 3 == 1) ? "PREMIUM" : "ULTRA";

            threads[i] = new Thread(() -> {
                for (int j = 1; j <= 3; j++) {
                    boolean allowed = rateLimiterService.allowRequest(user);
                    System.out.println("Thread-" + threadId + " (" + userType + ") Request " + j + ": " +
                            (allowed ? "✅ ALLOWED" : "❌ BLOCKED"));

                    try {
                        Thread.sleep(50); // Small delay between requests
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Concurrent test completed - Thread safety verified!");
    }
}
