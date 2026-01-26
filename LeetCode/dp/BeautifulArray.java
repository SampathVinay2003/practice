package LeetCode.dp;

import java.util.*;

public class BeautifulArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(minIncrements(arr, k));
    }

    static long minIncrements(int[] nums, int k) {
        int n = nums.length;
        if (n < 3) return 0L;

        long[] need = new long[n];
        for (int i = 0; i < n; i++) need[i] = Math.max(0L, (long)k - nums[i]);

        long[] dp = new long[n];
        dp[0] = 0L;
        dp[1] = 0L;

        // i = 2: one of {0,1,2} must be ≥ k
        dp[2] = Math.min(need[2], Math.min(need[1], need[0]));

        for (int i = 3; i < n; i++) {
            long a = dp[i-1] + need[i];     // raise i
            long b = dp[i-2] + need[i-1];   // raise i-1
            long c = dp[i-3] + need[i-2];   // raise i-2
            dp[i] = Math.min(a, Math.min(b, c));
        }
        return dp[n-1];
    }
}

//5
//        2
//        3
//        0
//        0
//        2
//        4